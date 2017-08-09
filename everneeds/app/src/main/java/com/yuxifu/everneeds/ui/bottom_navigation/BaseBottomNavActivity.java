package com.yuxifu.everneeds.ui.bottom_navigation;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;
import com.roughike.bottombar.TabSelectionInterceptor;
import com.yuxifu.everneeds.R;
import com.yuxifu.everneeds.util.ResourceHelper;

/**
 * Created by Yuxi on 8/7/17.
 */

public abstract class BaseBottomNavActivity extends AppCompatActivity {

    protected BottomBar bottomBar;
    private boolean ignoreTabSelectionListener = false;
    private boolean ignoreTabReselectionListener = false;

    @Override
    @CallSuper
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

        //sliding tab colors
        SmartTabLayout slidingTab = findViewById(R.id.viewpagertab);
        if (slidingTab != null) {
            slidingTab.setBackgroundColor(ResourceHelper.getThemeColor(this, R.attr.colorPrimary));
            slidingTab.setDefaultTabTextColor(Color.WHITE);
            slidingTab.setSelectedIndicatorColors(ResourceHelper.getThemeColor(this, R.attr.colorAccent));
        }

        // add bottom bar listeners
        bottomBar = findViewById(R.id.bottomBar);
        bottomBar.setDefaultTab(getCurrentBottomNavBarTabId());
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                /*if (!ignoreTabSelectionListener) {
                    switch (tabId) {
                        case R.id.tab_home:
                            HomeActivity.start(BaseBottomNavActivity.this);
                            overridePendingTransition(0, 0);
                            break;
                        case R.id.tab_plan:
                            PlanActivity.start(BaseBottomNavActivity.this);
                            overridePendingTransition(0, 0);
                            break;
                        case R.id.tab_track:
                            TrackActivity.start(BaseBottomNavActivity.this);
                            overridePendingTransition(0, 0);
                            break;
                        case R.id.tab_discover:
                            DiscoverActivity.start(BaseBottomNavActivity.this);
                            overridePendingTransition(0, 0);
                            break;
                        case R.id.tab_profile:
                            ProfileActivity.start(BaseBottomNavActivity.this);
                            overridePendingTransition(0, 0);
                            break;
                    }
                }*/
            }
        });
        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                if (!ignoreTabReselectionListener) {
                    onNavBarTabReselect();
                }
            }
        });
        bottomBar.setTabSelectionInterceptor(new TabSelectionInterceptor() {
            @Override
            public boolean shouldInterceptTabSelection(@IdRes int oldTabId, @IdRes int newTabId) {
                switch (newTabId) {
                    case R.id.tab_home:
                        HomeActivity.start(BaseBottomNavActivity.this);
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.tab_plan:
                        PlanActivity.start(BaseBottomNavActivity.this);
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.tab_track:
                        TrackActivity.start(BaseBottomNavActivity.this);
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.tab_discover:
                        DiscoverActivity.start(BaseBottomNavActivity.this);
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.tab_profile:
                        ProfileActivity.start(BaseBottomNavActivity.this);
                        overridePendingTransition(0, 0);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    // child activity should implement how re-selecting the same (current) tab will be handled.
    protected abstract void onNavBarTabReselect();

    protected abstract int getContentViewId();

    protected abstract int getCurrentBottomNavBarTabId();

    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        overridePendingTransition(0, 0);
        /*ignoreTabSelectionListener = true;
        ignoreTabReselectionListener = true;
        bottomBar.selectTabWithId(getCurrentBottomNavBarTabId());
        ignoreTabSelectionListener = false;
        ignoreTabReselectionListener = false;*/
    }

    // Remove inter-activity transition to avoid screen tossing on tapping bottom navigation items
    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

//    TODO: uncomment if backstack behavior needs to change
//    @Override
//    public void onBackPressed() {
//        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
//        List<ActivityManager.AppTask> appTasks = activityManager.getAppTasks();
//        for (ActivityManager.AppTask task : appTasks) {
//            task.finishAndRemoveTask();
//        }
//    }

    protected void showSnackbar(Snackbar snackbar) {
        CoordinatorLayout.LayoutParams params
                = (CoordinatorLayout.LayoutParams) snackbar.getView().getLayoutParams();
        params.setMargins(params.leftMargin, params.topMargin, params.rightMargin,
                bottomBar.getHeight());
        snackbar.getView().setLayoutParams(params);
        snackbar.show();
    }

    public void setNavigationBarColor(int color) {
        bottomBar.setBackgroundColor(color);
    }

    public void hideNavigationBar() {
        bottomBar.setVisibility(View.GONE);
    }

}
