package com.kodart.lang.models;

import java.io.Serializable;
import java.util.HashMap;

/**
 * User: Artur Sharipov
 */
public class UnigramModel<T> implements Serializable {
    private static final long serialVersionUID = 4878946008392920993L;

    private final HashMap<T, Integer> counts = new HashMap<T, Integer>();
    private int total;

    public void add(T object) {
        Integer value = counts.get(object);
        value = value == null ? 1 : value + 1;
        counts.put(object, value);
        total++;
    }

    public int getTotal() {
        return total;
    }

    public int getCount(T object) {
        Integer value = counts.get(object);
        return value == null ? 0 : value;
    }

    public double getFrequency(T object) {
        return getCount(object) / (double)total;
    }
}
