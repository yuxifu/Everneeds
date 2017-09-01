package com.yuxifu.everneeds.ui.custom_views;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import com.yuxifu.everneeds.R;

/**
 * Created by Yuxi on 8/31/17.
 */

public class Divider {

    public static View getFullSpanDivider(Context context, int heightDimenResId, int colorResId) {
        View divider = new View(context);
        TypedValue outValue = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.listDivider,
                outValue, true)) {
            divider.setBackgroundResource(outValue.resourceId);
        }
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                (int) context.getResources().getDimension(heightDimenResId));
        divider.setLayoutParams(layoutParams);
        return divider;
    }

    public static View getFullSpanDivider(Context context, int heightDimenResId) {
        return getFullSpanDivider(context, heightDimenResId,
                R.color.list_divider_default_color_light_grey);
    }

    public static View getFullSpanDividerThick(Context context) {
        return getFullSpanDivider(context, R.dimen.list_divider_default_thickness_thick,
                R.color.list_divider_default_color_light_grey);
    }

    public static View getFullSpanDividerThin(Context context) {
        return getFullSpanDivider(context, R.dimen.list_divider_default_thickness_thin,
                R.color.list_divider_default_color_light_grey);
    }

}
