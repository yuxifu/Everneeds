package com.yuxifu.everneeds.util;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.util.TypedValue;

/**
 * Created by Yuxi on 8/3/17.
 */

public final class ResourceHelper {
    
    public static String idToName(Context context, int id) {
        return context.getResources().getResourceEntryName(id);
    }

    public static String getString(Context context, int id) {
        return context.getResources().getString(id);
    }

    public static float getDimension(Context context, int id) {
        return context.getResources().getDimension(id);
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

    public static int dpToPx(Context context, float dp) {
        float density = context.getResources()
                .getDisplayMetrics()
                .density;
        return Math.round((float) dp * density);
    }

}
