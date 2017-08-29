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
public class Product extends Feature {
    @NonNull
    private final ProductCategory type;

    private boolean on;

    @NonNull
    public ProductCategory getType() {
        return type;
    }

    public boolean isOn() {
        return on;
    }

    public Product(@NonNull String title, @NonNull ProductCategory type, @Nullable String description) {
        this(UUID.randomUUID().toString(), title, type, description, true);
    }

    private Product(@NonNull String id, @NonNull String title, @NonNull ProductCategory type,
                   @Nullable String description, boolean on) {
        super(id, title, description);
        this.type = type;
        this.on = on;
    }

}
