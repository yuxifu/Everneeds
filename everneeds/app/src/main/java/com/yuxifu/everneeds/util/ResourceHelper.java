package com.yuxifu.everneeds.util;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.util.TypedValue;

import com.yuxifu.everneeds.R;

/**
 * Created by Yuxi on 8/3/17.
 */

public final class ResourceHelper {
    public static String idToTitle(Context context, int id) {
        switch (id) {

            //bottom tab titles
            case R.id.tab_home:
                return context.getString(R.string.bottombar_home_title);
            case R.id.tab_plan:
                return context.getString(R.string.bottombar_plan_title);
            case R.id.tab_track:
                return context.getString(R.string.bottombar_track_title);
            case R.id.tab_discover:
                return context.getString(R.string.bottombar_discover_title);
            case R.id.tab_profile:
                return context.getString(R.string.bottombar_profile_title);

            //
            case R.id.menu_filter:
                return context.getString(R.string.menu_filter);
            case R.id.menu_refresh:
                return context.getString(R.string.menu_refresh);
            case R.id.menu_settings:
                return context.getString(R.string.menu_settings);
            case R.id.menu_search:
                return context.getString(R.string.menu_search);

        }
        return Integer.toString(id);
    }

    @ColorInt
    public static int getThemeColor
            (
                    @NonNull final Context context,
                    @AttrRes final int attributeColor
            ) {
        final TypedValue value = new TypedValue();
        context.getTheme().resolveAttribute(attributeColor, value, true);
        return value.data;
    }

}
