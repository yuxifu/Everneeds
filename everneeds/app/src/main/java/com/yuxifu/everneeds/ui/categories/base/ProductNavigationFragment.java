package com.yuxifu.everneeds.ui.categories.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.yuxifu.everneeds.R;
import com.yuxifu.everneeds.ui.products.base.BaseProductWidgetFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class ProductNavigationFragment extends NavigationFragment  {

    NestedScrollView mNestedScrollView;
    LinearLayout mLinearLayout;

    public ProductNavigationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View mainView = inflater.inflate(R.layout.fragment_product_navigation, container, false);
        mNestedScrollView = mainView.findViewById(R.id.top_nested_scroll_view);
        mLinearLayout = mainView.findViewById(R.id.main_linear_layout);
        addProductWidgets(inflater);
        return mainView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.nav_actionbar_collapse_expand_options, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.nav_actionbar_options_expand_all_sections:
                ExpandAll();
                return true;
            case R.id.nav_actionbar_options_collapse_all_sections:
                CollapseAll();
                return true;
            default:
                return super.onOptionsItemSelected(item);
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

    private List<Fragment> productWidgetFragments;

    protected void addProductWidgets(LayoutInflater inflater) {
        if (productWidgetFragments == null) {
            productWidgetFragments = new ArrayList<Fragment>();
        } else {
            productWidgetFragments.clear();
        }

        FragmentManager fm = getChildFragmentManager();
        List<ProductUI> products = getProducts();
        for (ProductUI item : products) {
            View container = inflater.inflate(R.layout.widget_container_view, mLinearLayout, true);
            FrameLayout fragmentContainer = container.findViewById(R.id.content_container);
            int fragmentId = item.getmContainerId();
            fragmentContainer.setId(fragmentId);

            //add product fragment
            Fragment fragment = fm.findFragmentById(fragmentId);
            if (fragment == null) {
                fragment = item.getmFragment();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(fragmentId, fragment);
                ft.commit();
            }

            //
            productWidgetFragments.add(fragment);
        }
    }

    protected abstract List<ProductUI> getProducts();

    public void ExpandAll() {
        if (productWidgetFragments != null) {
            for (Fragment widget : productWidgetFragments) {
                if (widget instanceof BaseProductWidgetFragment)
                    ((BaseProductWidgetFragment) widget).Expand();
            }
        }
    }

    public void CollapseAll() {
        if (productWidgetFragments != null) {
            for (Fragment widget : productWidgetFragments) {
                if (widget instanceof BaseProductWidgetFragment)
                    ((BaseProductWidgetFragment) widget).Collapse();
            }
        }
    }

    //
}
