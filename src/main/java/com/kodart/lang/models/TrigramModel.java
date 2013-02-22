package com.kodart.lang.models;

import java.io.Serializable;
import java.util.HashMap;

/**
 * User: Artur Sharipov
 */
public class TrigramModel<T> implements Serializable {
    private static final long serialVersionUID = -1093920201049004844L;

    private final HashMap<T, BigramModel<T>> counts = new HashMap<T, BigramModel<T>>();

    private int total;

    public void add(T object, T prior, T preprior) {
        BigramModel<T> model = counts.get(prior);
        if (model == null) {
            model = new BigramModel<T>();
            counts.put(preprior, model);
        }
        model.add(object, prior);
        total++;
    }

    public int getTotal() {
        return total;
    }

    public int getCount(T object, T prior, T preprior) {
        BigramModel<T> model = counts.get(preprior);
        return model == null ? 0 : model.getCount(object, prior);
    }

    public double getFrequency(T object, T prior, T preprior) {
        BigramModel<T> model = counts.get(preprior);
        return model == null ? 0 : model.getCount(object, prior) / (double) model.getTotal();
    }
}

