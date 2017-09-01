package com.yuxifu.everneeds.ui.custom_views;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.yuxifu.everneeds.R;
import com.yuxifu.everneeds.util.ResourceHelper;

/**
 * Created by Yuxi on 8/31/17.
 */

public class Divider {

    public static View getFullSpanDivider(Context context, int heightDimenResId, int leftMargin) {
        View divider = new View(context);
        TypedValue outValue = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.listDivider,
                outValue, true)) {
            divider.setBackgroundResource(outValue.resourceId);
        }
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                (int) context.getResources().getDimension(heightDimenResId));
        divider.setLayoutParams(layoutParams);
        if (leftMargin == 0) {
            return divider;
        }

        //
        LinearLayout ll = new LinearLayout(context);
        LinearLayout.LayoutParams llLayoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        llLayoutParams.setMargins(ResourceHelper.dpToPx(context, leftMargin), 0, 0, 0);
        ll.setLayoutParams(llLayoutParams);
        ll.addView(divider);
        return ll;
    }

    public static View getFullSpanDivider(Context context, int heightDimenResId) {
        return getFullSpanDivider(context, heightDimenResId, 0);
    }

    public static View getFullSpanDividerThick(Context context) {
        return getFullSpanDivider(context, R.dimen.list_divider_default_thickness_thick, 0);
    }

    public static View getFullSpanDividerThin(Context context) {
        return getFullSpanDivider(context, R.dimen.list_divider_default_thickness_thin, 0);
    }

    public static View getDividerThin(Context context, int leftMargin) {
        return getFullSpanDivider(context, R.dimen.list_divider_default_thickness_thin,
                leftMargin);
    }

}
