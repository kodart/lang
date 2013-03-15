package com.kodart.lang;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * User: Artur Sharipov
 */
public class NGramLinearInterpolationTest {

    @Test(expected = AssertionError.class)
    public void shouldFailWhenParamsNotSumToOne() {
        NGram<String> nGram = new NGram<String>();
        NGramLinearInterpolation<String> nGramLinearInterpolation =
                new NGramLinearInterpolation<String>(nGram, 0.5, 0.2, 0.4, 0.1);
    }

    @Test
    public void interpolatedValueShouldBeCorrect() {
        NGram<String> nGram = new NGram<String>();
        NGramLinearInterpolation<String> interpolation =
                new NGramLinearInterpolation<String>(nGram, 0.8, 0.2);

        String sentence = "Hello my world how are you my world";
        String prev = "*";
        for(String word: sentence.split(" ")) {
            nGram.add(word, prev);
            prev = word;
        }

        assertEquals(1 * 0.8 + 0.25 * 0.2, interpolation.probability("my", "you"));
    }
}
