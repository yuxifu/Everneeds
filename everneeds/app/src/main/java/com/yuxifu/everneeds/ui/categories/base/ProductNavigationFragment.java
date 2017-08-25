package com.yuxifu.everneeds.ui.categories.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.yuxifu.everneeds.R;
import com.yuxifu.everneeds.ui.custom_views.CollapsibleView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class ProductNavigationFragment extends Fragment {

    NestedScrollView mNestedScrollView;
    LinearLayout mLinearLayout;

    public ProductNavigationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View main_view = inflater.inflate(R.layout.fragment_product_navigation, container, false);
        mNestedScrollView = main_view.findViewById(R.id.top_nested_scroll_view);
        mLinearLayout = main_view.findViewById(R.id.main_linear_layout);
        addProductViews();
        return main_view;
    }

    private List<CollapsibleView> mCollapsibleViews;

    protected void addProductViews() {
        if (mCollapsibleViews == null) {
            mCollapsibleViews = new ArrayList<CollapsibleView>();
        } else {
            mCollapsibleViews.clear();
        }

        FragmentManager fm = getChildFragmentManager();
        List<ProductUI> products = getProducts();
        for (ProductUI item : products) {
            CollapsibleView cv = new CollapsibleView(getContext());
            cv.getmIconImageView().setImageResource(item.getmIconImageId());
            cv.getmTitleTextView().setText(item.getmProduct().getTitle());
            cv.getmFragmentFrameLayout().setId(item.getmContainerId());

            //add product fragment
            cv.getmPlaceholderContents().setVisibility(View.GONE);
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(item.getmContainerId(), item.getmFragment());
            ft.commit();

            //
            mLinearLayout.addView(cv);
            mCollapsibleViews.add(cv);
        }
    }

    protected abstract List<ProductUI> getProducts();

    public void ExpandAll() {
        if (mCollapsibleViews != null) {
            for (CollapsibleView cv : mCollapsibleViews) {
                cv.Expand();
            }
        }
    }

    public void CollapseAll() {
        if (mCollapsibleViews != null) {
            for (CollapsibleView cv : mCollapsibleViews) {
                cv.Collapse();
            }
        }
    }

    //
}
