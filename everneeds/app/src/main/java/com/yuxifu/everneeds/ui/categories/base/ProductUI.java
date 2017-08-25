package com.yuxifu.everneeds.ui.categories.base;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.yuxifu.everneeds.data.model.Product;

/**
 * Created by Yuxi on 8/23/17.
 */

public class ProductUI {
    @NonNull
    private final Product mProduct;

    @NonNull
    public Product getmProduct() {
        return mProduct;
    }


    public int getmIconImageId() {
        return mIconImageId;
    }

    private final int mIconImageId;

    @NonNull
    public Fragment getmFragment() {

        return mFragment;
    }

    @NonNull
    private final Fragment mFragment;

    @Nullable
    public Activity getmActivity() {
        return mActivity;
    }

    @Nullable
    private Activity mActivity;

    public int getmContainerId() {
        return mContainerId;
    }

    private int mContainerId;


    /**
     * @param product
     * @param fragment
     * @param activity
     */
    public ProductUI(@NonNull Product product,
                     int iconImageId,
                     @NonNull Fragment fragment,
                     int fragmentContainerId,
                     @Nullable Activity activity) {
        mProduct = product;
        mIconImageId = iconImageId;
        mFragment = fragment;
        mContainerId = fragmentContainerId;
        mActivity = activity;
    }

}
