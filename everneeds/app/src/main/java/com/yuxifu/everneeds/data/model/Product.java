package com.yuxifu.everneeds.data.model;

/**
 * Created by Yuxi on 8/23/17.
 */

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.UUID;

/**
 *
 */
public class Product {
    @NonNull
    private final String mId;

    @NonNull
    private final ProductType mType;

    @NonNull
    private final String mTitle;

    @Nullable
    private final String mDescription;

    private boolean mOn;

    public Product(@NonNull String title, @NonNull ProductType type, @Nullable String description) {
        this(UUID.randomUUID().toString(), title, type, description, true);
    }

    public Product(@NonNull String id, @NonNull String title, @NonNull ProductType type,
                   @Nullable String description, boolean on) {
        mId = id;
        mType = type;
        mTitle = title;
        mDescription = description;
        mOn = on;
    }

    public String getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public ProductType getType() {
        return mType;
    }

    public String getDescription() {
        return mDescription;
    }

}
