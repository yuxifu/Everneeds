package com.yuxifu.everneeds.ui.main;

import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.yuxifu.everneeds.R;
import com.yuxifu.everneeds.ui._exp.PlaceholderItemFragment;
import com.yuxifu.everneeds.ui._exp.dummy.DummyContent;
import com.yuxifu.everneeds.ui.adapters.ViewPagerAdapter;
import com.yuxifu.everneeds.ui.categories.base.ProductNavigationFragment;
import com.yuxifu.everneeds.ui.categories.plan.PlanNavigationFragment;
import com.yuxifu.everneeds.util.ResourceHelper;

/**
 * Created by Yuxi on 8/7/17.
 */

public class Main2Activity extends AppCompatActivity implements
        PlaceholderItemFragment.OnListFragmentInteractionListener,
        PlanNavigationFragment.OnFragmentInteractionListener {

    public static int[] navTabs() {
        return new int[]{
                R.string.nav_plan_title,
                R.string.nav_track_title,
                R.string.nav_discover_title,
                R.string.nav_profile_title
        };
    }

    private CoordinatorLayout mCoordinatorLayout;
    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private SmartTabLayout mSmartLayout;
    private Menu mOptionsMenu;

    @Override
    @CallSuper
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //
        mCoordinatorLayout = findViewById(R.id.main_content);

        // Toolbar
        mToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);

        mViewPager = findViewById(R.id.main_viewpager);
        setupViewPager(mViewPager);

        mSmartLayout = findViewById(R.id.main_bottom_tab);
        setupNavigationTabs(mSmartLayout);

        mSmartLayout.setViewPager(mViewPager);
    }

    private void setupViewPager(final ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PlanNavigationFragment(), getResources().getString(navTabs()[0]));
        adapter.addFragment(PlaceholderItemFragment.newInstance(2), getResources().getString(navTabs()[1]));
        adapter.addFragment(PlaceholderItemFragment.newInstance(1), getResources().getString(navTabs()[2]));
        adapter.addFragment(PlaceholderItemFragment.newInstance(1), getResources().getString(navTabs()[3]));
        viewPager.setAdapter(adapter);

        final ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mToolbar.setTitle(navTabs()[position]);
                UpdateOptionsMenuVisibility();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };

        viewPager.addOnPageChangeListener(pageChangeListener);

        // do this in a runnable to make sure the viewPager's views are already instantiated before triggering the onPageSelected call
        viewPager.post(new Runnable() {
            @Override
            public void run() {
                pageChangeListener.onPageSelected(viewPager.getCurrentItem());
            }
        });
    }

    public void setupNavigationTabs(SmartTabLayout layout) {

        final LayoutInflater inflater = LayoutInflater.from(layout.getContext());
        final Resources res = layout.getContext().getResources();

        layout.setCustomTabView(new SmartTabLayout.TabProvider() {
            @Override
            public View createTabView(ViewGroup container, int position, PagerAdapter adapter) {
                View v = inflater.inflate(R.layout.custom_tab_icon_and_text, container,
                        false);

                ImageView icon = (ImageView) v.findViewById(R.id.custom_tab_icon);
                TextView text = (TextView) v.findViewById(R.id.custom_tab_text);
                switch (position) {
                    case 0:     //schedule/plan
                        icon.setImageDrawable(res.getDrawable(R.drawable.ic_main_schedule_selector));
                        text.setText(navTabs()[0]);
                        break;
                    case 1:     //track
                        icon.setImageDrawable(res.getDrawable(R.drawable.ic_main_track_selector));
                        text.setText(navTabs()[1]);
                        break;
                    case 2:     //discover
                        icon.setImageDrawable(res.getDrawable(R.drawable.ic_main_compass_selector));
                        text.setText(navTabs()[2]);
                        break;
                    case 3:     //profile
                        icon.setImageDrawable(res.getDrawable(R.drawable.ic_main_person_selector));
                        text.setText(navTabs()[3]);
                        break;
                    default:    //
                        throw new IllegalStateException("Invalid position: " + position);
                }
                return v;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mOptionsMenu = menu;
        getMenuInflater().inflate(R.menu.nav_actionbar_options, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        switch (AppCompatDelegate.getDefaultNightMode()) {
            case AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM:
            case AppCompatDelegate.MODE_NIGHT_AUTO:
            case AppCompatDelegate.MODE_NIGHT_NO:
                menu.findItem(R.id.nav_actionbar_options_night_mode).setChecked(false);
                break;
            case AppCompatDelegate.MODE_NIGHT_YES:
                menu.findItem(R.id.nav_actionbar_options_night_mode).setChecked(true);
                break;
        }

        //
        return true;
    }

    private void UpdateOptionsMenuVisibility() {
        Object obj = mViewPager.getAdapter().instantiateItem(mViewPager, mViewPager.getCurrentItem());
        boolean collapsible = obj instanceof ProductNavigationFragment;
        mOptionsMenu.findItem(R.id.nav_actionbar_options_collapse_all_sections).setVisible(collapsible);
        mOptionsMenu.findItem(R.id.nav_actionbar_options_expand_all_sections).setVisible(collapsible);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_actionbar_options_night_mode:
                boolean newNightMode = !item.isChecked();
                setNightMode(newNightMode ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
                item.setChecked(newNightMode);
                break;
            case R.id.nav_actionbar_options_expand_all_sections:
                ExpandAll();
                break;
            case R.id.nav_actionbar_options_collapse_all_sections:
                CollapseAll();
                break;
            case R.id.nav_actionbar_options_about:
            case R.id.nav_actionbar_options_search:
                showSnackbarShortNotImplementedIdMessage(id);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void ExpandAll() {
        Object obj = mViewPager.getAdapter().instantiateItem(mViewPager, mViewPager.getCurrentItem());
        if (obj instanceof ProductNavigationFragment) {
            ((ProductNavigationFragment) obj).ExpandAll();
        }
    }

    public void CollapseAll() {
        Object obj = mViewPager.getAdapter().instantiateItem(mViewPager, mViewPager.getCurrentItem());
        if (obj instanceof ProductNavigationFragment) {
            ((ProductNavigationFragment) obj).CollapseAll();
        }
    }

    //
    public void setNightMode(@AppCompatDelegate.NightMode int nightMode) {
        AppCompatDelegate.setDefaultNightMode(nightMode);
        recreate();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }


    public void showSnackbar(Snackbar snackbar) {
        CoordinatorLayout.LayoutParams params
                = (CoordinatorLayout.LayoutParams) snackbar.getView().getLayoutParams();
        //params.setMargins(params.leftMargin, params.topMargin, params.rightMargin,
        //        mSmartLayout.getHeight());
        params.setMargins(params.leftMargin, params.topMargin, params.rightMargin,
                params.bottomMargin);
        snackbar.getView().setLayoutParams(params);
        snackbar.show();
    }

    public void showSnackbarShortMessage(CharSequence message) {
        Snackbar snackbar = Snackbar
                .make(mCoordinatorLayout,
                        message,
                        Snackbar.LENGTH_SHORT);
        showSnackbar(snackbar);
    }

    public void showSnackbarShortNotImplementedIdMessage(int id) {
        showSnackbarShortMessage("Not implemented: " + ResourceHelper.idToName(this, id));
    }

    //
    public int getThemePrimaryColor() {
        final TypedValue value = new TypedValue();
        this.getTheme().resolveAttribute(R.attr.colorPrimary, value, true);
        return value.data;
    }

    public int getThemePrimaryDarkColor() {
        final TypedValue value = new TypedValue();
        this.getTheme().resolveAttribute(R.attr.colorPrimaryDark, value, true);
        return value.data;
    }

    public int getThemeAccentColor() {
        final TypedValue value = new TypedValue();
        this.getTheme().resolveAttribute(R.attr.colorAccent, value, true);
        return value.data;
    }

    // Communication with child fragment
    public void onFragmentInteraction(Uri uri) {
        //
    }

    // Communication with child fragment
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

    //
}
