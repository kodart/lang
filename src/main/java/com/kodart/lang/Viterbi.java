package com.kodart.lang;

import java.util.*;

/**
 * User: Artur Sharipov
 */
public class Viterbi<T, K> {

    private static final Comparator<StateExtension> StateProbabilityComparator = new Comparator<StateExtension>() {
        @Override
        public int compare(StateExtension a, StateExtension b) {
            return Double.compare(a.getProbability(), b.getProbability());
        }
    };

    private StateFactory<K> stateFactory;
    private Transition<K> transition;
    private Emission<T, K> emission;
    private StateFilter<K> filter;

    public Viterbi(Transition<K> transition, Emission<T, K> emission, StateFactory<K> stateFactory, StateFilter<K> filter) {
        this.transition = transition;
        this.emission = emission;
        this.stateFactory = stateFactory;
        this.filter = filter;
    }

    public Viterbi(Transition<K> transition, Emission<T, K> emission, final K[] states) {
        this(transition, emission,
                new StateFactory<K>() {
                    @Override
                    public K[] next(StateExtension<K> source) {
                        return states;
                    }
                },
                new StateFilter<K>(){
                    @Override
                    public Collection<StateExtension<K>> apply(Collection<StateExtension<K>> states) {
                        return states;
                    }
                });
    }

    /**
     * Maximizes hidden states sequence likelihood
     *
     * @param observations
     * @return optimal states
     */
    public Collection<K> maximize(T[] observations, K initial) {

        Collection<StateExtension<K>> sourceStates = Arrays.asList(new StateExtension<K>(initial));
        for (int k = 1; k < observations.length; k++) {
            Collection<StateExtension<K>> nextStates = new ArrayList<StateExtension<K>>();
            for (StateExtension<K> source : sourceStates) {
                Collection<StateExtension<K>> subNextStates = extendStates(stateFactory.next(source));
                nextStates.addAll(subNextStates);
                for (StateExtension<K> state : subNextStates) {
                    double tp = transition.probability(state.getState(), source.getState());
                    double ep = emission.probability(observations[k], state.getState());

                    double probability = Math.log(tp) + Math.log(ep) + source.getProbability();
                    if (probability > state.getProbability()) {
                        state.setProbability(probability);
                        state.setPrevious(source);
                    }
                }
            }
            sourceStates = filter.apply(nextStates);
        }

        return unroll(Collections.max(sourceStates, StateProbabilityComparator));
    }

    private Collection<K> unroll(StateExtension<K> extension) {
        List<K> states = new ArrayList<K>();
        while (true) {
            K state = extension.getState();
            if (state == null) {
                break;
            }
            states.add(0, state);
            extension = extension.getPrevious();
        }
        return states;
    }

    private Collection<StateExtension<K>> extendStates(K[] states) {
        List<StateExtension<K>> result = new ArrayList<StateExtension<K>>();
        for (K state : states) {
            result.add(new StateExtension<K>(state));
        }
        return result;
    }

}
