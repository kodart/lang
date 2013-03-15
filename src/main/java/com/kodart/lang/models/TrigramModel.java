package com.kodart.lang.models;

import com.kodart.lang.Corpus;
import com.kodart.lang.Sentence;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * User: Artur Sharipov
 */
public class TrigramModel<T> implements Serializable {
    private static final long serialVersionUID = -1093920201049004844L;

    private final HashMap<T, BigramModel<T>> counts = new HashMap<T, BigramModel<T>>();

    private int total;

    public void add(T item, T prior_1, T prior_2) {
        BigramModel<T> model = counts.get(item);
        if (model == null) {
            model = new BigramModel<T>();
            counts.put(item, model);
        }
        model.add(prior_1, prior_2);
        total++;
    }

    public int getTotal() {
        return total;
    }

    public int getCount(T item, T prior) {
        BigramModel<T> model = counts.get(item);
        return model == null ? 0 : model.getTotal(prior);
    }

    public int getCount(T item, T prior_1, T prior_2) {
        BigramModel<T> model = counts.get(item);
        return model == null ? 0 : model.getCount(prior_1, prior_2);
    }

    private double frequency(double count, double total) {
        return total == 0 ? 0 : count / total;
    }

    public double getProbability(T item, T prior_1, T prior_2) {

        return 0;

//        return 0.2 * frequency(getCount(item), getTotal()) +
//                0.2 * frequency(getCount(item, prior_1), getCount(prior_1)) +
//                0.6 * frequency(getCount(item, prior_1, prior_2), getCount(prior_1, prior_2));
    }

//    public double getProbability(Sentence sentence) {
//        double result = 0.0;
//        return result;
//    }
//
//    public double getPerplexity(Corpus corpus) {
//        double l = 0.0;
//        List<Sentence> sentences = corpus.getSentences();
//        for(Sentence sentence: sentences) {
//            l += Math.log(getProbability(sentence));
//        }
//        return Math.pow(2.0, -l);
//    }
}

