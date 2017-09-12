package com.yuxifu.everneeds.ui.custom_views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;


/**
 * Created by Yuxi on 9/12/17.
 */

public class CanDisableSwipingViewPager extends ViewPager {
    private Boolean disableSwiping = false;

    public CanDisableSwipingViewPager(Context context) {
        super(context);
    }

    public CanDisableSwipingViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return !disableSwiping && super.onInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return !disableSwiping && super.onTouchEvent(event);
    }

    public void disableSwiping(Boolean disableSwiping) {
        this.disableSwiping = disableSwiping;
    }
}
