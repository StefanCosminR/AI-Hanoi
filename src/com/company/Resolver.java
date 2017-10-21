package com.company;

import java.util.Objects;
import java.util.Random;

public class Resolver {

    public void run(String methodName, int... args) {
        if (Objects.equals(methodName, "random")) {
            random(args[0], args[1]);
        }
    }

    public void random(int numberOfTowers, int numberOfDisks) {

        Hanoi hanoi = new Hanoi();
        State state = hanoi.init(numberOfTowers, numberOfDisks);

        int loopCounter = 0;
        int failedCounter = 0;
        Random random = new Random();
        while (!hanoi.isFinalState(state)) {
            int selectedPosition = random.nextInt(numberOfDisks);
            int selectedTower = random.nextInt(numberOfTowers);
            if (hanoi.isValidState(state, selectedPosition, selectedTower)) {
                state.updateState(selectedPosition, selectedTower);
                hanoi.addStateToHistory(state);
            }
            loopCounter++;

            if (loopCounter > 1000 * numberOfTowers) {
                ++failedCounter;
                System.out.println("Failted to find a solution: " + failedCounter);
                state = hanoi.init(numberOfTowers, numberOfDisks);
                loopCounter = 0;
            }

            if (failedCounter >= 10000) {
                System.out.println("Failed to find any solution");
                break;
            }
        }

        System.out.println("Finished in " + loopCounter);
        System.out.println(state);
    }

    public void BK(State state, Hanoi hanoi, int numberOfTowers, int numberOfDisks) {
        if (hanoi.isFinalState(state)) {
            System.out.println(state);
        }
        int[] nDisk = new int[numberOfDisks];
        for (int i = 0; i < numberOfDisks; ++i) {
            nDisk[i] = state.getElementAt(i);
        }

        for (int i : nDisk) {
            for (int j = i; j < numberOfTowers; ++j) {

            }

        }
    }
}
