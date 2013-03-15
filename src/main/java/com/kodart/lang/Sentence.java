package com.kodart.lang;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Artur Sharipov
 */
public class Sentence {
    private final List<Word> words = new ArrayList<Word>();

    public List<Word> getWords() {
        return new ArrayList<Word>(words);
    }

    public int getLength() {
        return words.size() + 1;
    }
}
