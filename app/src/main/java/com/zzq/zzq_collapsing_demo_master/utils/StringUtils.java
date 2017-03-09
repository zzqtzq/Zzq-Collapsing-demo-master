package com.zzq.zzq_collapsing_demo_master.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 志强 on 2017.3.9.
 */

public class StringUtils {
    
    public static Object arrayList(Object[] objects) {
        List<Object> list = new ArrayList<>();
        if (objects == null || objects.length == 0) {
            return list;
        }
        for (int i = 0; i < objects.length; i++) {
            list.add(objects[i]);
        }
        return list;
    }
}
