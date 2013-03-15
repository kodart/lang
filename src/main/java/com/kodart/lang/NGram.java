package com.kodart.lang;

import java.io.Serializable;
import java.util.HashMap;

import static com.kodart.Arrays.head;
import static com.kodart.Arrays.tail;

/**
 * User: Artur Sharipov
 */
public class NGram<T> implements Serializable {

    private final HashMap<T, NGram<T>> map = new HashMap<T, NGram<T>>();
    private int count;

    public void add(T... words) {
        count++;
        if (words.length == 0)
            return;

        T word = head(words);
        NGram<T> sub = map.get(word);
        if (sub == null) {
            sub = new NGram<T>();
            map.put(word, sub);
        }

        sub.add(tail(words));
    }

    public int getCount(T... words) {
        if (words.length == 0)
            return count;

        NGram<T> sub = map.get(head(words));
        if (sub == null)
            return 0;

        return sub.getCount(tail(words));
    }
}
