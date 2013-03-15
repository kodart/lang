package com.kodart.lang.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * User: Artur Sharipov
 */
public class BigramModel<T> implements Serializable {
    private static final long serialVersionUID = -2159866209966430678L;

    private final HashMap<T, UnigramModel<T>> counts = new HashMap<T, UnigramModel<T>>();

    private int total;

    public void add(T item, T prior) {
        UnigramModel<T> model = counts.get(item);
        if (model == null) {
            model = new UnigramModel<T>();
            counts.put(item, model);
        }
        model.add(prior);
        total++;
    }

    public int getTotal() {
        return total;
    }

    public int getCount(T item, T prior) {
        UnigramModel<T> model = counts.get(item);
        return model == null ? 0 : model.getCount(prior);
    }

    public double getProbability(T item, T prior) {
        UnigramModel<T> model = counts.get(item);
        return model == null ? 0 : model.getCount(prior) / model.getTotal();
    }

    public int getTotal(T item) {
        return counts.get(item).getTotal();
    }
}
