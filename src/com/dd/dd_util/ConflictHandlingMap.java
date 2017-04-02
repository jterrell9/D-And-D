package com.dd.dd_util;

import java.util.HashMap;

public class ConflictHandlingMap<V> extends HashMap<String, V>{
    public ConflictHandlingMap(){
        super();
    }

    @Override
    public V put(String name, V value){
        String key = name;
        int counter = 2;
        while(true) {
            if (containsKey(key)) {
                key = name + "_" + Integer.toString(counter);
                ++counter;
            }
            else
                break;
        }
        return super.put(key, value);
    }
}