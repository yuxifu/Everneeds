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
    private static final String PREFS_CURRENT_MAIN_NAV_INDEX = "PREFS_CURRENT_MAIN_NAV_INDEX";
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


}