package com.yuxifu.everneeds.ui.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;
import com.yuxifu.everneeds.R;
import com.yuxifu.everneeds.util.ResourceHelper;

/**
 * Created by Yuxi on 8/7/17.
 */

public abstract class BaseBottomNavActivity extends AppCompatActivity {
    @Override
    @CallSuper
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initOnCreate(savedInstanceState);

        // add bottom bar listeners
        getBottomNavigationBar().setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.tab_home:
                        //HomeActivity.start(BaseBottomNavActivity.this);
                        break;
                    case R.id.tab_plan:
                        //PlanActivity.start(BaseBottomNavActivity.this);
                        break;
                    case R.id.tab_track:
                        //TrackActivity.start(BaseBottomNavActivity.this);
                        break;
                    case R.id.tab_discover:
                        //DiscoverActivity.start(BaseBottomNavActivity.this);
                        break;
                    case R.id.tab_profile:
                        //ProfileActivity.start(BaseBottomNavActivity.this);
                        break;
                }
                Toast.makeText(getApplicationContext(),
                        ResourceHelper.idToTitle(BaseBottomNavActivity.this, tabId) + " selected",
                        Toast.LENGTH_SHORT).show();
            }
        });
        getBottomNavigationBar().setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                onNavBarReselect();
            }
        });
    }

    // child activity should implement how re-selecting the same (current) tab will be handled.
    protected abstract void onNavBarReselect();

    protected abstract int getCurrentBottomNavId();

    protected abstract BottomBar getBottomNavigationBar();

    protected abstract void initOnCreate(Bundle savedInstanceState);

    @Override
    protected void onResume() {
        super.onResume();
        getBottomNavigationBar().selectTabWithId(getCurrentBottomNavId());
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
                getBottomNavigationBar().getHeight());
        snackbar.getView().setLayoutParams(params);
        snackbar.show();
    }
}
