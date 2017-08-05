package com.yuxifu.everneeds.util;

import android.content.Context;
import com.yuxifu.everneeds.R;

/**
 * Created by Yuxi on 8/3/17.
 */

public final class RootTabs {
    public static String getTitle(Context context, int menuItemId) {
        switch (menuItemId) {
            case R.id.tab_home:
                return context.getString(R.string.bottombar_home_label);
            case R.id.tab_plan:
                return context.getString(R.string.bottombar_plan_label);
            case R.id.tab_track:
                return context.getString(R.string.bottombar_track_label);
            case R.id.tab_connect:
                return context.getString(R.string.bottombar_connect_label);
        }
        return "";
    }
}
