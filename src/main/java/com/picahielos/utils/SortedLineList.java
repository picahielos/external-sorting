package com.picahielos.utils;

import java.util.ArrayList;

public class SortedLineList extends ArrayList<String> {
    @Override
    public boolean add(String e) {
        int index = 0;
        for (String line : this) {
            if (line.compareTo(e) >= 0) {
                break;
            }
            index++;
        }
        add(index, e);
        return true;
    }
}
