package com.yuxifu.everneeds.ui.bottom_navigation;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;
import com.yuxifu.everneeds.R;

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

        // add bottom bar listeners
        bottomBar = findViewById(R.id.bottomBar);
        bottomBar.setDefaultTab(getCurrentBottomNavBarTabId());
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (!ignoreTabSelectionListener) {
                    switch (tabId) {
                        case R.id.tab_home:
                            HomeActivity.start(BaseBottomNavActivity.this);
                            break;
                        case R.id.tab_plan:
                            PlanActivity.start(BaseBottomNavActivity.this);
                            break;
                        case R.id.tab_track:
                            TrackActivity.start(BaseBottomNavActivity.this);
                            break;
                        case R.id.tab_discover:
                            DiscoverActivity.start(BaseBottomNavActivity.this);
                            break;
                        case R.id.tab_profile:
                            ProfileActivity.start(BaseBottomNavActivity.this);
                            break;
                    }
                }
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
    }

    // child activity should implement how re-selecting the same (current) tab will be handled.
    protected abstract void onNavBarTabReselect();

    protected abstract int getContentViewId();

    protected abstract int getCurrentBottomNavBarTabId();

    @Override
    protected void onResume() {
        super.onResume();
        overridePendingTransition(0, 0);
        ignoreTabSelectionListener = true;
        ignoreTabReselectionListener = true;
        bottomBar.selectTabWithId(getCurrentBottomNavBarTabId());
        ignoreTabSelectionListener = false;
        ignoreTabReselectionListener = false;
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
