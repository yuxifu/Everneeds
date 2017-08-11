package com.yuxifu.everneeds.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Yuxi on 8/3/17.
 */

public final class CollectionHelper {

    public static List<String> getRandomSublist(String[] array, int amount) {
        ArrayList<String> list = new ArrayList<>(amount);
        Random random = new Random();
        while (list.size() < amount) {
            list.add(array[random.nextInt(array.length)]);
        }
        return list;
    }

}
