package com.kodart.lang;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Artur Sharipov
 */
public class Corpus {
    private final List<Sentence> sentences = new ArrayList<Sentence>();

    public List<Sentence> getSentences() {
        return sentences;
    }

    public int getLength() {
        int result = 0;
        for(Sentence sentence: sentences) {
            result += sentence.getLength();
        }
        return result;
    }
}
