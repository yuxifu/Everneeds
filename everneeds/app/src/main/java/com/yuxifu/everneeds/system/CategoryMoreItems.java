package com.yuxifu.everneeds.system;

import com.yuxifu.everneeds.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuxi on 8/31/17.
 */

public class CategoryMoreItems {
    public static List<CategoryMoreItem> getCategoryMoreItems() {
        List<CategoryMoreItem> items = new ArrayList<CategoryMoreItem>();

        //add items to the More category navigational tab page

        //statistics
        CategoryMoreItem statistics = new CategoryMoreItem(R.id.list_item_statistics,
                R.string.statistics_list_item_title, R.drawable.icons8_statistics_96,
                null, true, null);
        items.add(statistics);

        //favorites
        CategoryMoreItem favorites = new CategoryMoreItem(R.id.list_item_favorites,
                R.string.favorites_list_item_title, R.drawable.icons8_favorites_80,
                null, false, null);
        items.add(favorites);

        //night mode
        CategoryMoreItem nightMode = new CategoryMoreItem(R.id.list_item_night_mode,
                R.string.night_mode_list_item_title, R.drawable.icons8_moon_stars_100,
                null, true, null);
        items.add(nightMode);

        //settings
        CategoryMoreItem settings = new CategoryMoreItem(R.id.list_item_settings,
                R.string.settings_list_item_title, R.drawable.icons8_settings_96,
                null, false, null);
        items.add(settings);

        //add more

        return items;
    }

    public static CategoryMoreItem getById(int id) {
        for (CategoryMoreItem item : getCategoryMoreItems()) {
            if (item.getViewId() == id) {
                return item;
            }
        }
        return null;
    }
}
