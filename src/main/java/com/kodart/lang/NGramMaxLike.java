package com.kodart.lang;

import static com.kodart.Arrays.tail;

/**
 * Calculates basic maximum likelihood probability
 *
 * User: Artur Sharipov
 */
public class NGramMaxLike<T> implements NGramProbability<T> {
    private final NGram<T> nGram;

    public NGramMaxLike(NGram<T> nGram) {
        this.nGram = nGram;
    }

    @Override
    public double probability(T... words) {
        double count = nGram.getCount(words);
        double total = nGram.getCount(tail(words));
        return total == 0 ? 0 : count / total;
    }
}


