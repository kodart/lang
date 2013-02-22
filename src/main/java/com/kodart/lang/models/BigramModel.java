package com.kodart.lang.models;

import java.io.Serializable;
import java.util.HashMap;

/**
 * User: Artur Sharipov
 */
public class BigramModel<T> implements Serializable {
    private static final long serialVersionUID = -2159866209966430678L;

    private final HashMap<T, UnigramModel<T>> counts = new HashMap<T, UnigramModel<T>>();

    private int total;

    public void add(T object, T prior) {
        UnigramModel<T> model = counts.get(prior);
        if (model == null) {
            model = new UnigramModel<T>();
            counts.put(prior, model);
        }
        model.add(object);
        total++;
    }

    public int getTotal() {
        return total;
    }

    public int getCount(T object, T prior) {
        UnigramModel<T> model = counts.get(prior);
        return model == null ? 0 : model.getCount(object);
    }

    public double getFrequency(T object, T prior) {
        UnigramModel<T> model = counts.get(prior);
        return model == null ? 0 : model.getCount(object) / model.getTotal();
    }
}
