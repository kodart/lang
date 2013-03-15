package com.kodart.lang;

/**
 * User: Artur Sharipov
 */
public interface NGramProbability<T> {
    public double probability(T... words);
}
