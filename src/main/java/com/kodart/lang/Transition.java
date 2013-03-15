package com.kodart.lang;

/**
 * User: Artur Sharipov
 */
public interface Transition<T> {
    /**
     * @return transition probability from source state to new state P(state|source)
     */
    public double probability(T state, T source);
}
