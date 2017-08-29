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
import com.yuxifu.everneeds.ui.categories.plan.PlanNavigationFragment;
import com.yuxifu.everneeds.ui.products.calendar.CalendarWidgetFragment;
import com.yuxifu.everneeds.util.ResourceHelper;

/**
 * Main entrance of the application.  Main activity
 */
public class Main2Activity extends AppCompatActivity implements
        CalendarWidgetFragment.OnFragmentInteractionListener,
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

    private CoordinatorLayout coordinatorLayout;
    private Toolbar toolbar;

    @Override
    @CallSuper
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //
        coordinatorLayout = findViewById(R.id.main_content);

        // Toolbar
        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = findViewById(R.id.main_viewpager);
        setupViewPager(viewPager);

        SmartTabLayout smartLayout = findViewById(R.id.main_bottom_tab);
        setupNavigationTabs(smartLayout);

        smartLayout.setViewPager(viewPager);
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
                toolbar.setTitle(navTabs()[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };

        viewPager.addOnPageChangeListener(pageChangeListener);

        // do this in a runnable to make sure the viewPager's views are already instantiated
        // before triggering the onPageSelected call
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

                ImageView icon = v.findViewById(R.id.custom_tab_icon);
                TextView text = v.findViewById(R.id.custom_tab_text);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_actionbar_options_night_mode:
                boolean newNightMode = !item.isChecked();
                setNightMode(newNightMode ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
                item.setChecked(newNightMode);
                break;
            case R.id.nav_actionbar_options_about:
            case R.id.nav_actionbar_options_search:
                showSnackbarShortNotImplementedIdMessage(id);
                break;
        }
        return super.onOptionsItemSelected(item);
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
        return toolbar;
    }


    public void showSnackbar(Snackbar snackbar) {
        CoordinatorLayout.LayoutParams params
                = (CoordinatorLayout.LayoutParams) snackbar.getView().getLayoutParams();
        params.setMargins(params.leftMargin, params.topMargin, params.rightMargin,
                params.bottomMargin);
        snackbar.getView().setLayoutParams(params);
        snackbar.show();
    }

    public void showSnackbarShortMessage(CharSequence message) {
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout,
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
        showSnackbarShortMessage(item.content);
    }

    //
}
