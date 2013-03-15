package com.kodart.lang;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * User: Artur Sharipov
 */
public class NGramTest {

    @Test
    public void unigramCountShouldBeCorrect() {
        NGram<String> nGram = new NGram<String>();
        String sentence = "Hello my world how are you my world";
        for(String word: sentence.split(" ")) {
            nGram.add(word);
        }

        assertEquals(8, nGram.getCount());
        assertEquals(1, nGram.getCount("Hello"));
        assertEquals(2, nGram.getCount("my"));
        assertEquals(1, nGram.getCount("you"));
    }

    @Test
    public void unseenSequenceCountShouldBeZero() {
        NGram<String> nGram = new NGram<String>();
        String sentence = "Hello my world how are you my world";
        for(String word: sentence.split(" ")) {
            nGram.add(word);
        }

        assertEquals(0, nGram.getCount("unseen"));
        assertEquals(0, nGram.getCount("my", "world"));
        assertEquals(0, nGram.getCount("world", "my"));
    }

    @Test
    public void bigramAndUnigramCountShouldBeCorrect() {
        NGram<String> nGram = new NGram<String>();
        String sentence = "Hello my world how are you my world";
        String prev = "*";
        for(String word: sentence.split(" ")) {
            nGram.add(word, prev);
            prev = word;
        }

        assertEquals(8, nGram.getCount());
        assertEquals(1, nGram.getCount("Hello"));
        assertEquals(2, nGram.getCount("my"));
        assertEquals(1, nGram.getCount("you"));

        assertEquals(1, nGram.getCount("Hello", "*"));
        assertEquals(2, nGram.getCount("world", "my"));
        assertEquals(1, nGram.getCount("my", "Hello"));
    }

    @Test
    public void nGramCountShouldBeCorrect() {
        NGram<String> nGram = new NGram<String>();
        String sentence = "Hello my world how are you my world";
        String prev_1 = "*", prev_2 = "*";
        for(String word: sentence.split(" ")) {
            nGram.add(word, prev_1, prev_2);
            prev_2 = prev_1;
            prev_1 = word;
        }

        assertEquals(8, nGram.getCount());
        assertEquals(1, nGram.getCount("Hello"));
        assertEquals(2, nGram.getCount("my"));
        assertEquals(1, nGram.getCount("you"));

        assertEquals(1, nGram.getCount("Hello", "*"));
        assertEquals(2, nGram.getCount("world", "my"));
        assertEquals(1, nGram.getCount("my", "Hello"));

        assertEquals(1, nGram.getCount("Hello", "*", "*"));
        assertEquals(1, nGram.getCount("you", "are", "how"));
        assertEquals(0, nGram.getCount("you", "are", "world"));
        assertEquals(0, nGram.getCount("you", "unseen", "world"));
        assertEquals(0, nGram.getCount("you", "are", "unseen"));
        assertEquals(0, nGram.getCount("unseen", "are", "how"));
    }
}
