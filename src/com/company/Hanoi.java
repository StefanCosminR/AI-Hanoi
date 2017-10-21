package com.company;

import java.util.ArrayList;
import java.util.List;

public class Hanoi {

    private ArrayList<State> stateHistory = new ArrayList<>();


    public State init(int numberOfTowers, int numberOfDisks) {
        return new State(numberOfTowers, numberOfDisks);
    }


    public boolean isFinalState(State state) {

        List<Integer> elements = state.getElements();

        for (Integer el : elements) {
            if (el != state.getNumberOfTowers() - 1) {
                return false;
            }
        }
        return true;
    }

    public boolean isValidState(State state, int elementPosition, int tower) {
        if (elementPosition >= state.getNumberOfTowers()) {
            return false;
        }

        State clonedState = state.clone();
        clonedState.updateState(elementPosition, tower);

        if(existsInHistory(clonedState)) {
            return false;
        }

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

    public void addStateToHistory(State state) {
        stateHistory.add(state);
    }

    private boolean existsInHistory(State state) {
        for(int i = 0; i < stateHistory.size(); ++i) {
            if(stateHistory.get(i).equals(state)) {
                return true;
            }
        }

        return false;
    }

}
