package com.yuxifu.everneeds.system;

import android.content.Context;
import android.support.annotation.NonNull;

import com.yuxifu.everneeds.util.SharedPreferencesHelper;

/**
 * Created by Yuxi on 9/7/17.
 */

public class AppPreferences {

    //region SharedPreference keys
    private static final String PREFS_NIGHT_MODE_ON = "PREFS_NIGHT_MODE_ON";
    private static final String PREFS_ACTION_BAR_HIDE_ON_SCROLL = "PREFS_ACTION_BAR_HIDE_ON_SCROLL";
    private static final String PREFS_CURRENT_MAIN_NAV_INDEX = "PREFS_CURRENT_MAIN_NAV_INDEX";
    private static final String PREFS_MAIN_NAV_SWIPING_ON = "PREFS_MAIN_NAV_SWIPING_ON";
    private static final String PREFS_WIDGET_CALENDAR_COLLAPSED = "PREFS_WIDGET_CALENDAR_COLLAPSED";
    private static final String PREFS_WIDGET_TODO_COLLAPSED = "PREFS_WIDGET_TODO_COLLAPSED";
    //endregion

    //region Night Mode On
    public static Boolean getNightModeOn(@NonNull Context context) {
        SharedPreferencesHelper sph = new SharedPreferencesHelper(context);
        return sph.getBoolean(AppPreferences.PREFS_NIGHT_MODE_ON, false);
    }

    public static void putNightModeOn(@NonNull Context context, Boolean nightModeOn) {
        SharedPreferencesHelper sph = new SharedPreferencesHelper(context);
        sph.putBoolean(PREFS_NIGHT_MODE_ON, nightModeOn);
    }
    //endregion

    //region Action Bar Hide On Scroll
    public static Boolean getActionBarHideOnScroll(@NonNull Context context) {
        SharedPreferencesHelper sph = new SharedPreferencesHelper(context);
        return sph.getBoolean(AppPreferences.PREFS_ACTION_BAR_HIDE_ON_SCROLL, false);
    }

    public static void setActionBarHideOnScroll(@NonNull Context context, Boolean nightModeOn) {
        SharedPreferencesHelper sph = new SharedPreferencesHelper(context);
        sph.putBoolean(PREFS_ACTION_BAR_HIDE_ON_SCROLL, nightModeOn);
    }
    //endregion

    //region Current Main Nav Index
    public static int getCurrentMainNavigationIndex(@NonNull Context context, int max) {
        SharedPreferencesHelper sph = new SharedPreferencesHelper(context);
        int index = sph.getInt(AppPreferences.PREFS_CURRENT_MAIN_NAV_INDEX, 0);
        if (index > max) return 0;
        return index;
    }

    public static void putCurrentMainNavigationIndex(@NonNull Context context, int currentTab) {
        SharedPreferencesHelper sph = new SharedPreferencesHelper(context);
        sph.putInt(AppPreferences.PREFS_CURRENT_MAIN_NAV_INDEX, currentTab);
    }
    //endregion

    //region Main Nav Swiping
    public static Boolean getMainNavigationSwipingOn(@NonNull Context context) {
        SharedPreferencesHelper sph = new SharedPreferencesHelper(context);
        return sph.getBoolean(AppPreferences.PREFS_MAIN_NAV_SWIPING_ON);
    }

    public static void putMainNavigationSwipingOn(@NonNull Context context, Boolean swipingOn) {
        SharedPreferencesHelper sph = new SharedPreferencesHelper(context);
        sph.putBoolean(AppPreferences.PREFS_MAIN_NAV_SWIPING_ON, swipingOn);
    }
    //endregion

    //region Widget Calendar Collapsed
    public static Boolean getWidgetCalendarCollapsed(@NonNull Context context) {
        SharedPreferencesHelper sph = new SharedPreferencesHelper(context);
        return sph.getBoolean(AppPreferences.PREFS_WIDGET_CALENDAR_COLLAPSED, false);
    }
    public static void putWidgetCalendarCollapsed(@NonNull Context context, Boolean collapsed) {
        SharedPreferencesHelper sph = new SharedPreferencesHelper(context);
        sph.putBoolean(PREFS_WIDGET_CALENDAR_COLLAPSED, collapsed);
    }
    //endregion

    //region Widget To-Do Collapsed
    public static Boolean getWidgetTodoCollapsed(@NonNull Context context) {
        SharedPreferencesHelper sph = new SharedPreferencesHelper(context);
        return sph.getBoolean(AppPreferences.PREFS_WIDGET_TODO_COLLAPSED, false);
    }
    public static void putWidgetTodoCollapsed(@NonNull Context context, Boolean collapsed) {
        SharedPreferencesHelper sph = new SharedPreferencesHelper(context);
        sph.putBoolean(PREFS_WIDGET_TODO_COLLAPSED, collapsed);
    }
    //endregion


}