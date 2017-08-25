package com.yuxifu.everneeds.ui.custom_views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yuxifu.everneeds.R;

public class CollapsibleView extends RelativeLayout implements View.OnClickListener {

    public TextView getmTitleTextView() {
        return mTitleTextView;
    }

    public ImageView getmCaretImageView() {
        return mCaretImageView;
    }

    public ImageView getmIconImageView() {
        return mIconImageView;
    }

    public FrameLayout getmFragmentFrameLayout() {
        return mFragmentFrameLayout;
    }

    public LinearLayout getmHeader() {
        return mHeader;
    }

    public TextView getmPlaceholderContents() {
        return mPlaceholderContents;
    }

    private TextView mTitleTextView;
    private ImageView mCaretImageView;
    private ImageView mIconImageView;
    private FrameLayout mFragmentFrameLayout;
    private TextView mPlaceholderContents;
    private LinearLayout mHeader;

    private boolean mCollapsed;

    public CollapsibleView(Context context) {
        super(context);
        init();
    }

    public CollapsibleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CollapsibleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.custom_collapsible_view, this);

        //
        mHeader = findViewById(R.id.header);
        mIconImageView = findViewById(R.id.icon);
        mTitleTextView = findViewById(R.id.title);
        mCaretImageView = findViewById(R.id.caret);
        mFragmentFrameLayout = findViewById(R.id.content_container);
        mPlaceholderContents = findViewById(R.id.placeholder_contents);

        //
        mCaretImageView.setClickable(true);
        mCaretImageView.setOnClickListener(this);
        mTitleTextView.setClickable(true);
        mTitleTextView.setOnClickListener(this);

        //
        mCollapsed = false;
        UpdateCollapsingIconAndView();
    }

    private void UpdateCollapsingIconAndView() {
        mFragmentFrameLayout.setVisibility(mCollapsed ? View.GONE : View.VISIBLE);
        mCaretImageView.setImageResource(mCollapsed ? R.drawable.ic_expand : R.drawable.ic_collapse);
    }

    public void ToggleCollapsed(){
        mCollapsed = !mCollapsed;
        UpdateCollapsingIconAndView();
    }

    public void Expand(){
        if(mCollapsed) {
            mCollapsed = false;
            UpdateCollapsingIconAndView();
        }
    }

    public void Collapse(){
        if(!mCollapsed) {
            mCollapsed = true;
            UpdateCollapsingIconAndView();
        }
    }

    @Override
    public void onClick(View view) {
        if (view == mCaretImageView || view == mTitleTextView) {
            ToggleCollapsed();
        }
    }

}