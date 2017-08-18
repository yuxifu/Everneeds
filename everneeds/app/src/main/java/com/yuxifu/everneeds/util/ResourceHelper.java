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
