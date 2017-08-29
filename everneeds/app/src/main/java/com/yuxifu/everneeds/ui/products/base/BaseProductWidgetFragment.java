package com.yuxifu.everneeds.ui.products.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuxifu.everneeds.R;
import com.yuxifu.everneeds.ui.main.Main2Activity;

public abstract class BaseProductWidgetFragment extends Fragment implements View.OnClickListener {

    private static final String INSTANCE_STATE_COLLAPSED = "collapsed";

    public LinearLayout getHeader() {
        return header;
    }

    public View getBodyView() {
        return bodyView;
    }

    private LinearLayout header;
    private ImageView iconImageView;
    private TextView titleTextView;
    private ImageView caretImageView;
    private View bodyView;
    protected boolean collapsed;

    //
    {
        collapsed = false;
    }

    public BaseProductWidgetFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View topView = inflater.inflate(R.layout.fragment_product_widget, container, false);
        LinearLayout topLinearLayout = topView.findViewById(R.id.top_linear_layout);
        bodyView = inflater.inflate(getBodyViewId(), topLinearLayout, false);
        topLinearLayout.addView(bodyView);

        //views
        header = topView.findViewById(R.id.header);
        iconImageView = header.findViewById(R.id.icon);
        titleTextView = header.findViewById(R.id.title);
        caretImageView = header.findViewById(R.id.caret);

        //set icon and title text
        iconImageView.setImageResource(getIconResId());
        titleTextView.setText(getTitleResId());

        //
        header.setClickable(true);
        header.setOnClickListener(this);
        /*iconImageView.setClickable(true);
        iconImageView.setOnClickListener(this);
        titleTextView.setClickable(true);
        titleTextView.setOnClickListener(this);
        caretImageView.setClickable(true);
        caretImageView.setOnClickListener(this);*/

        //
        UpdateCollapsingIconAndView();

        return topView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(INSTANCE_STATE_COLLAPSED, collapsed);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            collapsed = savedInstanceState.getBoolean(INSTANCE_STATE_COLLAPSED);
            UpdateCollapsingIconAndView();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    // settings from subclasses
    protected abstract int getBodyViewId();

    protected abstract int getIconResId();

    protected abstract int getTitleResId();

    // Get parent activity
    public Main2Activity getMainActivity() {
        return (Main2Activity) getActivity();
    }

    public void UpdateCollapsingIconAndView() {
        bodyView.setVisibility(collapsed ? View.GONE : View.VISIBLE);
        caretImageView.setImageResource(collapsed ? R.drawable.ic_expand : R.drawable.ic_collapse);
    }

    public void ToggleCollapsed() {
        if(collapsed) {
            collapsed = false;
        }
        else {
            collapsed = true;
        }
        UpdateCollapsingIconAndView();
    }

    public void Expand() {
        if (collapsed) {
            collapsed = false;
            UpdateCollapsingIconAndView();
        }
    }

    public void Collapse() {
        if (!collapsed) {
            collapsed = true;
            UpdateCollapsingIconAndView();
        }
    }

    @Override
    public void onClick(View view) {
        /*if (view == iconImageView || view == caretImageView || view == titleTextView) {
            ToggleCollapsed();
        }*/
        if (view == header) {
            ToggleCollapsed();
        }
    }

    //
}
