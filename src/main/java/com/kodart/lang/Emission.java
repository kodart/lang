package com.kodart.lang;

/**
 * User: Artur Sharipov
 */
public interface Emission<T, K> {
    /**
     * @return P(observation|state)
     */
    public double probability(T observation, K state);
}
