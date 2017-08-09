package com.yuxifu.everneeds.ui.bottom_navigation;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;
import com.yuxifu.everneeds.R;
import com.yuxifu.everneeds.ui._exp.CheeseListFragment;
import com.yuxifu.everneeds.ui._exp.PlaceholderFragment;

/**
 * Created by Yuxi on 8/7/17.
 */

public class MainActivity extends AppCompatActivity {

    protected BottomBar bottomBar;

    @Override
    @CallSuper
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_auto_scroll);

        // bottom bar
        bottomBar = findViewById(R.id.bottomBar);
        bottomBar.setDefaultTab(R.id.tab_home);

        // bottom bar listeners
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                Fragment selectedFragment = null;
                switch (tabId) {
                    case R.id.tab_home:
                        selectedFragment = new CheeseListFragment();
                        break;
                    case R.id.tab_plan:
                        selectedFragment = PlaceholderFragment.newInstance(1);
                        break;
                    case R.id.tab_track:
                        selectedFragment = PlaceholderFragment.newInstance(2);
                        break;
                    case R.id.tab_discover:
                        selectedFragment = PlaceholderFragment.newInstance(3);
                        break;
                    case R.id.tab_profile:
                        selectedFragment = PlaceholderFragment.newInstance(4);
                        break;
                }
                if (selectedFragment != null) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.contentContainer, selectedFragment);
                    transaction.commit();
                }
            }
        });
        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
            }
        });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contentContainer, new CheeseListFragment());
        transaction.commit();
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

}