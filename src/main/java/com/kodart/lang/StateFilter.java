package com.kodart.lang;

import java.util.Collection;

/**
 * User: Artur Sharipov
 */
public interface StateFilter<K> {
    public Collection<StateExtension<K>> apply(Collection<StateExtension<K>> states);
}
