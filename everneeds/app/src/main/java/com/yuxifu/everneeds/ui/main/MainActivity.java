package com.yuxifu.everneeds.ui.main;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
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
import android.widget.Toast;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.yuxifu.everneeds.R;
import com.yuxifu.everneeds.system.AppPreferences;
import com.yuxifu.everneeds.ui._exp.PlaceholderItemFragment;
import com.yuxifu.everneeds.ui._exp.dummy.DummyContent;
import com.yuxifu.everneeds.ui.adapters.ViewPagerAdapter;
import com.yuxifu.everneeds.ui.categories.more.MoreNavigationFragment;
import com.yuxifu.everneeds.ui.categories.plan.PlanNavigationFragment;
import com.yuxifu.everneeds.ui.custom_views.CanDisableSwipingViewPager;
import com.yuxifu.everneeds.ui.products.calendar.CalendarWidgetFragment;
import com.yuxifu.everneeds.ui.products.todolist.TodoWidgetFragment;
import com.yuxifu.everneeds.util.ResourceHelper;

/**
 * Main entrance of the application.  Main activity
 */
public class MainActivity extends AppCompatActivity implements
        CalendarWidgetFragment.OnFragmentInteractionListener,
        TodoWidgetFragment.OnFragmentInteractionListener,
        PlaceholderItemFragment.OnListFragmentInteractionListener,
        PlanNavigationFragment.OnFragmentInteractionListener,
        MoreNavigationFragment.OnFragmentInteractionListener {

    //constants
    public static int[] navTabs() {
        return new int[]{
                R.string.nav_home_title,
                R.string.nav_note_title,
                R.string.nav_plan_title,
                R.string.nav_track_title,
                R.string.nav_discover_title,
                R.string.nav_more_title
        };
    }

    //
    private MainActivity self;
    private CoordinatorLayout coordinatorLayout;
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private CanDisableSwipingViewPager viewPager;

    @Override
    @CallSuper
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //must be called before onCreate() to apply theme
        enableNightMode(AppPreferences.getNightModeOn(this));

        //super
        super.onCreate(savedInstanceState);
        self = this;
        setContentView(R.layout.activity_main_auto_scroll_toolbar);

        //
        coordinatorLayout = findViewById(R.id.main_content);
        appBarLayout = findViewById(R.id.main_appbar);

        // Toolbar
        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        enableActionBarHideOnScroll(AppPreferences.getActionBarHideOnScroll(this));

        viewPager = findViewById(R.id.main_viewpager);
        setupViewPager(viewPager);
        viewPager.disableSwiping(!AppPreferences.getMainNavigationSwipingOn(this));

        SmartTabLayout smartLayout = findViewById(R.id.main_bottom_tab);
        setupNavigationTabs(smartLayout);

        smartLayout.setViewPager(viewPager);
    }

    private void setupViewPager(final ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(PlaceholderItemFragment.newInstance(1), getResources().getString(navTabs()[0]));
        adapter.addFragment(PlaceholderItemFragment.newInstance(1), getResources().getString(navTabs()[1]));
        adapter.addFragment(new PlanNavigationFragment(), getResources().getString(navTabs()[2]));
        adapter.addFragment(PlaceholderItemFragment.newInstance(2), getResources().getString(navTabs()[3]));
        adapter.addFragment(PlaceholderItemFragment.newInstance(1), getResources().getString(navTabs()[4]));
        adapter.addFragment(new MoreNavigationFragment(), getResources().getString(navTabs()[5]));
        viewPager.setAdapter(adapter);

        viewPager.setCurrentItem(AppPreferences.getCurrentMainNavigationIndex(this, navTabs().length - 1));

        final ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0 || position == navTabs().length - 1) {
                    toolbar.setTitle(R.string.app_name);
                } else {   //display app name at the "home" page
                    toolbar.setTitle(navTabs()[position]);
                }
                AppPreferences.putCurrentMainNavigationIndex(self, position);
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
                    case 0:     //home
                        icon.setImageDrawable(res.getDrawable(R.drawable.ic_main_home_selector));
                        text.setText(navTabs()[position]);
                        break;
                    case 1:     //home
                        icon.setImageDrawable(res.getDrawable(R.drawable.ic_main_note_selector));
                        text.setText(navTabs()[position]);
                        break;
                    case 2:     //schedule/plan
                        icon.setImageDrawable(res.getDrawable(R.drawable.ic_main_schedule_selector));
                        text.setText(navTabs()[position]);
                        break;
                    case 3:     //track
                        icon.setImageDrawable(res.getDrawable(R.drawable.ic_main_track_selector));
                        text.setText(navTabs()[position]);
                        break;
                    case 4:     //discover
                        icon.setImageDrawable(res.getDrawable(R.drawable.ic_main_compass_selector));
                        text.setText(navTabs()[position]);
                        break;
                    case 5:     //more
                        icon.setImageDrawable(res.getDrawable(R.drawable.ic_main_more_selector));
                        text.setText(navTabs()[position]);
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
        //menu.findItem(R.id.nav_actionbar_options_night_mode).setChecked(isNightModeOn());

        //
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            /*case R.id.nav_actionbar_options_night_mode:
                boolean newNightMode = !item.isChecked();
                enableNightMode(newNightMode);
                item.setChecked(newNightMode);
                break;
            case R.id.nav_actionbar_options_about:*/
            case R.id.nav_actionbar_options_search:
                showSnackbarShortNotImplementedIdMessage(id);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //
    private void setNightMode(@AppCompatDelegate.NightMode int newNightMode) {
        AppCompatDelegate.setDefaultNightMode(newNightMode);
    }

    public void enableNightMode(Boolean newNightModeOn) {
        setNightMode(newNightModeOn ? AppCompatDelegate.MODE_NIGHT_YES
                : AppCompatDelegate.MODE_NIGHT_NO);
    }

    public boolean isNightModeOn() {
        return AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES;
    }

    public void ResetNightModeOn(boolean nightModeOn) {
        enableNightMode(nightModeOn);
        AppPreferences.putNightModeOn(this, (nightModeOn));

        //restart this activity
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent); // start same activity
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish(); // destroy older activity
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
        //Save SharedPreference data here unless accomplished somewhere else in more frequent manner.
        //AppPreferences.putNightModeOn(this, isNightModeOn());
        //AppPreferences.putCurrentMainNavigationIndex(this, viewPager.getCurrentItem());
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
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

    public void showSnackbarShortNotImplementedIdMessage(CharSequence message) {
        showSnackbarShortMessage("Not implemented: " + message);
    }

    private Toast toast;

    public void showToast(String message) {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
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
    public void onPreferencesChange(){
        enableActionBarHideOnScroll(AppPreferences.getActionBarHideOnScroll(this));
        viewPager.disableSwiping(!AppPreferences.getMainNavigationSwipingOn(this));
    }

    //
    public void enableActionBarHideOnScroll(boolean enable) {
        AppBarLayout.LayoutParams params =
                (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
        if (enable) {
            int newFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                    | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
                    | AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP;
            if (params.getScrollFlags() != newFlags) {
                params.setScrollFlags(newFlags);
                appBarLayout.requestLayout();
            }
        } else {
            if (params.getScrollFlags() != 0) {
                params.setScrollFlags(0);
                appBarLayout.requestLayout();
            }
        }
    }

    //
}
