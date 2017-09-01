package com.yuxifu.everneeds.util;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Yuxi on 8/3/17.
 */

public final class ViewHelper {

    public static void setMargins (View view, int left, int top, int right, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();
        }
    }

    //
}
