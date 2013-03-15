package com.kodart.lang;

/**
 * User: Artur Sharipov
 */
public interface StateFactory<K> {

    public K[] next(StateExtension<K> source);
}
