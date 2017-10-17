package com.company;

import java.util.List;

public class Hanoi {


    public State init(int numberOfTowers, int numberOfDisks) {
        return new State(numberOfTowers, numberOfDisks);
    }

    public boolean isFinalState(State state) {

        List<Integer> elements = state.getElements();

        for (Integer el : elements) {
            if (el != state.getNumberOfTowers()) {
                return false;
            }
        }
        return true;
    }

    public boolean isValidState(State state, int elementPosition, int tower) {
        if (state.getElementAt(elementPosition) == tower) {
            return false;
        }

        for (int i = 0; i < elementPosition; ++i) {
            if (state.getElementAt(i) == state.getElementAt(elementPosition)) {
                return false;
            }

            if (state.getElementAt(i) == tower) {
                return false;
            }
        }

        return true;

    }

}
