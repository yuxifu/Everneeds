package com.yuxifu.everneeds.ui.main._exp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.yuxifu.everneeds.R;

public abstract class BaseNavigationFragment extends Fragment implements OnDrawerNavigationItemListener {

    public BaseNavigationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(useOptionsMenu());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(getFragmentLayoutId(), container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Customize drawer and toobar
        CustomizeActivityDrawerAndToolBar();
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        // Options menu items added by this fragment
        if (useOptionsMenu()) {
            menu.clear(); // Remove existing items
            inflater.inflate(getOptionsMenuId(), menu);
        }

        // Parent activity may add extra items
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Take care items added in this fragment
        if (doOptionsItemSelected(item)) {
            return true;
        }

        // Hand over to the parent activity
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {

        // Take care items added in this fragment
        doPrepareOptionsMenu(menu);

        // Hand over to the parent activity
        super.onPrepareOptionsMenu(menu);
    }

    // settings from subclasses
    protected abstract int getFragmentLayoutId();

    protected abstract int getNavigationViewMenuId();

    protected abstract int getOptionsMenuId();

    protected abstract ViewPager getViewPager();

    protected abstract String getFragmentTitle();

    protected abstract boolean useDrawerNavigation();

    protected abstract boolean useSlidingTabs();

    protected abstract boolean useOptionsMenu();

    // Get parent activity
    public MainActivity getMainActivity() {
        return (MainActivity) getActivity();

    }

    // Customize activity drawer and toolbar
    private void CustomizeActivityDrawerAndToolBar() {
        MainActivity activity = getMainActivity();
        DrawerLayout drawerLayout = activity.getDrawerLayout();
        LinearLayout slidingTabsContainer = activity.getToolbar().findViewById(R.id.slidingTabsContainer);

        // Drawer navigation
        activity.enableDrawer(useDrawerNavigation());
        if (useDrawerNavigation()) {
            // replace drawer menu
            NavigationView navigationView = drawerLayout.findViewById(R.id.nav_view);
            navigationView.getMenu().clear();
            navigationView.inflateMenu(getNavigationViewMenuId());
        }

        // Sliding tabs
        if (useSlidingTabs()) {
            slidingTabsContainer.setVisibility(View.VISIBLE);
            TextView tv = slidingTabsContainer.findViewById(R.id.slidingTabsTitle);
            String title = getSlidingTabsTitle();
            if (title != null && !title.isEmpty()) {
                tv.setVisibility(View.VISIBLE);
                tv.setText(getSlidingTabsTitle());
            } else {
                tv.setVisibility(View.GONE);
            }
            ViewPager viewPager = getViewPager();
            if (viewPager != null) {
                SmartTabLayout tabs = slidingTabsContainer.findViewById(R.id.viewpagertab);
                //tabs.setDefaultTabTextColor(activity.getThemePrimaryColor());
                tabs.setSelectedIndicatorColors(activity.getThemeAccentColor());
                tabs.setViewPager(viewPager);
            }
        } else {
            slidingTabsContainer.setVisibility(View.GONE);
        }
    }

    // Implement OnDrawerNavigationItemListener
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Toast.makeText(getActivity(), getFragmentTitle() + ": " + menuItem.getTitle(),
                Toast.LENGTH_SHORT).show();
        return true;
    }

    public String getSlidingTabsTitle() {
        return "";
    }

    public void doPrepareOptionsMenu(Menu menu) {
    }

    public boolean doOptionsItemSelected(MenuItem item) {
        return false;
    }

    //
}
