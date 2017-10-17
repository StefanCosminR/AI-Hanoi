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

        elements = new ArrayList<>();

        for (int i = 0; i < numberOfDisks; ++i) {
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

    public void updateState(int position, int tower) {
        elements.set(position, tower);
    }

    public String toString() {
        StringBuilder result = new StringBuilder("Number of towers: " + this.numberOfTowers + "\n");
        result.append("(");
        for (Integer el : elements) {
            result.append(el + 1).append(", ");
        }
        result.deleteCharAt(result.length() - 1);
        result.deleteCharAt(result.length() - 1);
        result.append(")\n");
        return result.toString();
    }
}
