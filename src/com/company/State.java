package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class State {
    private int numberOfTowers;
    private int numberOfDisks;
    private ArrayList<Integer> elements;

    public State(int numberOfTowers, int numberOfDisks) {
        this.numberOfDisks = numberOfDisks;
        this.numberOfTowers = numberOfTowers;

        for(int i = 0; i < numberOfDisks; ++i) {
            elements.add(1);
        }
    }

    public int getNumberOfDisks() {
        return this.numberOfDisks;
    }

    public int getNumberOfTowers() {
        return this.numberOfTowers;
    }

    public int getElementAt(int position) {
        return elements.get(position);
    }

    public List<Integer> getElements() {
        return Collections.unmodifiableList(elements);
    }
}
