package com.yuxifu.everneeds.data.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Yuxi on 8/25/17.
 */

/**
 * Feature:
 */
public class Feature {
    @NonNull
    private final String id;

    @NonNull
    private final String title;

    @Nullable
    private final String description;

    private boolean subscribed;

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public boolean isSubscribed() {
        return subscribed;
    }

    Feature(@NonNull String id, @NonNull String title, @Nullable String description) {
        this(id,title,description,true);
    }

    private Feature(@NonNull String id, @NonNull String title, @Nullable String description,
                   boolean subscribed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.subscribed = subscribed;
    }

}
