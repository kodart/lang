package com.kodart;

import static java.util.Arrays.copyOf;

/**
 * User: Artur Sharipov
 */
public class Arrays {

    public static <T> T head(T[] sequence) {
        return sequence[0];
    }

    public static <T> T[] tail(T[] sequence) {
        return java.util.Arrays.copyOfRange(sequence, 1, sequence.length);
    }

    public static double head(double[] sequence) {
        return sequence[0];
    }

    public static double[] tail(double[] sequence) {
        return java.util.Arrays.copyOfRange(sequence, 1, sequence.length);
    }

    public static <T> T[] excludeLast(T[] sequence) {
        return java.util.Arrays.copyOf(sequence, sequence.length - 1);
    }

    public static double[] excludeLast(double[] sequence) {
        return java.util.Arrays.copyOf(sequence, sequence.length - 1);
    }


    public static double sum(double[] data) {
        double result = 0;
        for(double value: data) {
            result += value;
        }
        return result;
    }
}
