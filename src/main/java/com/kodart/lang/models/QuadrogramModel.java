package com.kodart.lang.models;

import java.io.Serializable;
import java.util.HashMap;

/**
 * User: Artur Sharipov
 */
public class QuadrogramModel<T> implements Serializable {
//    private static final long serialVersionUID = -1093920201049004844L;

    private final HashMap<T, TrigramModel<T>> counts = new HashMap<T, TrigramModel<T>>();

    private int total;

    public void add(T object, T prior, T xPrior, T xxPrior) {
        TrigramModel<T> model = counts.get(xxPrior);
        if (model == null) {
            model = new TrigramModel<T>();
            counts.put(xxPrior, model);
        }
        model.add(object, prior, xPrior);
        total++;
    }

    public int getTotal() {
        return total;
    }

    public int getCount(T object, T prior, T xPrior, T xxPrior) {
        TrigramModel<T> model = counts.get(xxPrior);
        return model == null ? 0 : model.getCount(object, prior, xPrior);
    }

    public double getFrequency(T object, T prior, T xPrior, T xxPrior) {
        TrigramModel<T> model = counts.get(xxPrior);
        return model == null ? 0 : model.getCount(object, prior, xPrior) / (double) model.getTotal();
    }
}

