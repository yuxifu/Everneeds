package com.yuxifu.everneeds.ui._exp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yuxifu.everneeds.R;
import com.yuxifu.everneeds.data._exp.Cheeses;

import java.util.List;

/**
 * Created by Yuxi on 8/10/17.
 */

public class CheeseRecyclerViewAdapter
        extends RecyclerView.Adapter<CheeseListFragment.SimpleStringRecyclerViewAdapter.ViewHolder> {

    private final TypedValue mTypedValue = new TypedValue();
    private int mBackground;
    private List<String> mValues;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public String mBoundString;

        public final View mView;
        public final ImageView mImageView;
        public final TextView mTextView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImageView = (ImageView) view.findViewById(R.id.avatar);
            mTextView = (TextView) view.findViewById(android.R.id.text1);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTextView.getText();
        }
    }

    public CheeseRecyclerViewAdapter(Context context, List<String> items) {
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
        mValues = items;
    }

    @Override
    public CheeseListFragment.SimpleStringRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cheese_list_item, parent, false);
        view.setBackgroundResource(mBackground);
        return new CheeseListFragment.SimpleStringRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CheeseListFragment.SimpleStringRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.mBoundString = mValues.get(position);
        holder.mTextView.setText(mValues.get(position));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, CheeseDetailActivity.class);
                intent.putExtra(CheeseDetailActivity.EXTRA_NAME, holder.mBoundString);

                context.startActivity(intent);
            }
        });

        Glide.with(holder.mImageView.getContext())
                .load(Cheeses.getRandomCheeseDrawable())
                .fitCenter()
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
}