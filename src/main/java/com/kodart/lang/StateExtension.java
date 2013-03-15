package com.kodart.lang;

/**
 * Extends state with probability and back-pointers (to source state)
 */
public class StateExtension<T> {

    private double probability = Double.MIN_VALUE;
    private StateExtension previous;
    private T state;

    public StateExtension(T state) {
        this.state = state;
    }

    public T getState() {
        return state;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public void setPrevious(StateExtension<T> previous) {
        this.previous = previous;
    }

    public StateExtension<T> getPrevious() {
        return previous;
    }

}
