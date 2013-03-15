package com.kodart.lang;

import static com.kodart.Arrays.*;

public class NGramLinearInterpolation<T> implements NGramProbability<T> {
    private final double[] params;
    private final NGramMaxLike<T> maxLike;

    public NGramLinearInterpolation(NGram<T> nGram, double... params) {
        assert sum(params) == 1.0;
        this.params = params;
        this.maxLike = new NGramMaxLike<T>(nGram);
    }

    @Override
    public double probability(T... words) {
        return probability(words, params);
    }

    private double probability(T[] words, double[] params) {
        return words.length == 0 ? 0 : head(params) * maxLike.probability(words) +
                probability(excludeLast(words), tail(params));
    }
}
