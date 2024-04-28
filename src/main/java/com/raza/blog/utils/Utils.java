package com.raza.blog.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Utils {
    private Utils(){}

    public  static <T> List<T> nullSafeList(List<T> collection){
        return collection == null ? new ArrayList<T>() : collection;
    }

    public static <T> boolean isNullOrEmpty(Collection<T> collection) {
        return collection != null && !collection.isEmpty();
    }
}
