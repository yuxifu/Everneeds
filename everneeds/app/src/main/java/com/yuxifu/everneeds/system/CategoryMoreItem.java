package com.yuxifu.everneeds.system;

import android.app.Activity;
import android.support.annotation.Nullable;

/**
 * Created by Yuxi on 8/31/17.
 * 9/7: not used
 */

public class CategoryMoreItem {

    public int getViewId() {
        return viewId;
    }

    public boolean isNewSection() {
        return newSection;
    }

    private final int viewId;
    private final int titleId;
    private final int firstImageId;

    @Nullable
    private final Integer secondImageId;

    private final boolean newSection;

    @Nullable
    private final Class<? extends Activity> activity;

    public int getTitleId() {
        return titleId;
    }

    @Nullable
    public Class<? extends Activity> getActivity() {
        return activity;
    }

    public int getFirstImageId() {
        return firstImageId;
    }

    @Nullable
    public Integer getSecondImageId() {
        return secondImageId;
    }


    CategoryMoreItem(int viewId, int titleId, int firstImageId,
                     @Nullable Integer secondImageId, boolean newSection,
                     @Nullable Class<? extends Activity> activity) {
        this.viewId = viewId;
        this.titleId = titleId;
        this.firstImageId = firstImageId;
        this.secondImageId = secondImageId;
        this.newSection = newSection;
        this.activity = activity;
    }
}