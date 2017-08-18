package com.yuxifu.everneeds.ui.bottom_navigation;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;
import com.yuxifu.everneeds.R;
import com.yuxifu.everneeds.ui._exp.PlaceholderItemFragment;
import com.yuxifu.everneeds.ui._exp.dummy.DummyContent;
import com.yuxifu.everneeds.util.ResourceHelper;

/**
 * Created by Yuxi on 8/7/17.
 */

public class MainActivity extends AppCompatActivity
        implements HomeFragment.OnFragmentInteractionListener,
        PlanFragment.OnFragmentInteractionListener,
        TrackFragment.OnFragmentInteractionListener,
        DiscoverFragment.OnFragmentInteractionListener,
        ProfileFragment.OnFragmentInteractionListener,
        PlaceholderItemFragment.OnListFragmentInteractionListener {

    private DrawerLayout mDrawerLayout;
    private CoordinatorLayout mCoordinatorLayout;
    private Toolbar mToolbar;
    private BottomBar mBottomBar;

    @Override
    @CallSuper
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_auto_scroll);

        //
        mCoordinatorLayout = findViewById(R.id.main_content);

        // Toolbar
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        // Drawer
        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
            final ActionBar ab = getSupportActionBar();
            if (ab != null) {
                ab.setHomeAsUpIndicator(R.drawable.ic_menu);
                ab.setDisplayHomeAsUpEnabled(true);
            }
        }

        // Sliding tabs
        LinearLayout ll = findViewById(R.id.slidingTabsContainer);
        ll.setVisibility(View.GONE);

        // Bottom bar
        mBottomBar = findViewById(R.id.bottomBar);
        mBottomBar.setDefaultTab(R.id.tab_home);

        // Bottom bar listeners
        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                Fragment selectedFragment = null;
                switch (tabId) {
                    case R.id.tab_home:
                        selectedFragment = HomeFragment.newInstance("Hello from Home", "");
                        break;
                    case R.id.tab_plan:
                        selectedFragment = PlanFragment.newInstance("Hello from Plan", "");
                        break;
                    case R.id.tab_track:
                        selectedFragment = PlanFragment.newInstance("Hello from Track", "");
                        break;
                    case R.id.tab_discover:
                        selectedFragment = PlanFragment.newInstance("Hello from Discover", "");
                        break;
                    case R.id.tab_profile:
                        selectedFragment = PlanFragment.newInstance("Hello from Profile", "");
                        break;
                }
                if (selectedFragment != null) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.contentContainer, selectedFragment);
                    transaction.commit();
                }
            }
        });
        mBottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
            }
        });

        // Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contentContainer,
                HomeFragment.newInstance("Hello from Home first.", ""));
        transaction.commit();
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.contentContainer);
                        if (fragment instanceof OnDrawerNavigationItemListener) {
                            boolean val = ((OnDrawerNavigationItemListener) fragment).onNavigationItemSelected(menuItem);
                            if (val) {
                                mDrawerLayout.closeDrawers();
                                return true;
                            }
                        }

                        //
                        showSnackbarShortMessage(menuItem.getTitle());
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
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

    //
    public DrawerLayout getDrawerLayout() {
        return mDrawerLayout;
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    //
    public void enableDrawer(boolean enable) {
        if (enable) {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            mToolbar.setNavigationIcon(R.drawable.ic_menu);
        } else {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            mToolbar.setNavigationIcon(null);
        }
    }

    public void showSnackbar(Snackbar snackbar) {
        CoordinatorLayout.LayoutParams params
                = (CoordinatorLayout.LayoutParams) snackbar.getView().getLayoutParams();
        params.setMargins(params.leftMargin, params.topMargin, params.rightMargin,
                mBottomBar.getHeight());
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

    public void showSnackbarShortNotImplementedIdMessage(int id)
    {
        showSnackbarShortMessage("Not implemented: "+ ResourceHelper.idToName(this, id));
    }

    //
    public void setNightMode(@AppCompatDelegate.NightMode int nightMode) {
        AppCompatDelegate.setDefaultNightMode(nightMode);
        recreate();
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
        showSnackbarShortMessage(item.content + " clicked");
    }

    //
}
