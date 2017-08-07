package com.yuxifu.everneeds.util;

import android.content.Context;
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
            case R.id.menu_edit:
                return context.getString(R.string.menu_edit);
            case R.id.menu_filter:
                return context.getString(R.string.menu_filter);
            case R.id.menu_refresh:
                return context.getString(R.string.menu_refresh);
            case R.id.menu_settings:
                return context.getString(R.string.menu_settings);
        }
        return Integer.toString(id);
    }
}
