package com.yuxifu.everneeds.ui.bottom_navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.yuxifu.everneeds.R;
import com.yuxifu.everneeds.ui._exp.CheeseListFragment;
import com.yuxifu.everneeds.ui._exp.PlaceholderFragment;
import com.yuxifu.everneeds.ui.adapters.ViewPagerAdapter;
import com.yuxifu.everneeds.util.ResourceHelper;

public class DiscoverActivity extends BaseBottomNavActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Use toolbar as action bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set up the ViewPager with the ViewPager adapter.
        ViewPager mViewPager = findViewById(R.id.viewpager);
        if (mViewPager != null) {
            setupViewPager(mViewPager);
            SmartTabLayout viewPagerTab = findViewById(R.id.viewpagertab);
            viewPagerTab.setViewPager(mViewPager);
        }
    }

    public static void start(Activity activity) {
        Intent intent = new Intent(activity.getApplicationContext(), DiscoverActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NO_ANIMATION);
        activity.startActivity(intent);
    }

    public static Intent getStartIntent(Context context) { // if required in a service etc
        Intent intent = new Intent(context, DiscoverActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NO_ANIMATION);
        return intent;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_discover;
    }

    @Override
    protected void onNavBarTabReselect() {
        // do something if the tab has been reselected
    }

    @Override
    protected int getCurrentBottomNavBarTabId() {
        return R.id.tab_discover;
    }

    private void showItemClicked(int id, String textAppended) {
        final CoordinatorLayout coordinatorLayout = findViewById(R.id.main_content);
        if (coordinatorLayout != null) {
            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout,
                            ResourceHelper.idToTitle(DiscoverActivity.this, id) + textAppended,
                            Snackbar.LENGTH_LONG)
                    .setAction("CONFIRM", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Snackbar snackbar1 = Snackbar.make(coordinatorLayout,
                                    "Message is confirmed!", Snackbar.LENGTH_SHORT);
                            showSnackbar(snackbar1);
                        }
                    });
            showSnackbar(snackbar);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_discover_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        /*switch (item.getItemId()) {
            case R.id.menu_settings:
                return true;
            default:
                break;
        }*/
        showItemClicked(item.getItemId(), " selected.");

        //
        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CheeseListFragment(), "Cheese");
        adapter.addFragment(PlaceholderFragment.newInstance(2), "Discover");
        adapter.addFragment(PlaceholderFragment.newInstance(3), "Calendar");
        adapter.addFragment(PlaceholderFragment.newInstance(4), "Birthday");
        adapter.addFragment(PlaceholderFragment.newInstance(5), "Projects");
        adapter.addFragment(PlaceholderFragment.newInstance(6), "Misc");
        viewPager.setAdapter(adapter);
    }

}
