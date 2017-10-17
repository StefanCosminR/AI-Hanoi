package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Hanoi hanoi = new Hanoi();
        int numberOfTowers = 3;
        int numberOfDisks = 3;
        State state = hanoi.init(numberOfTowers, numberOfDisks);

        int loopCounter = 0;
        int failedCounter = 0;
        while (!hanoi.isFinalState(state)) {
            Random random = new Random();
            int selectedPosition = random.nextInt(numberOfDisks);
            int selectedTower = random.nextInt(numberOfTowers);
            if (hanoi.isValidState(state, selectedPosition, selectedTower)) {
                state.updateState(selectedPosition, selectedTower);
            }
            loopCounter++;

            if(loopCounter > 1000 * numberOfTowers) {
                ++failedCounter;
                System.out.println("Failted to find a solution: " + failedCounter);
                state = hanoi.init(numberOfTowers, numberOfDisks);
                loopCounter = 0;
            }

            if(failedCounter >= 10000) {
                System.out.println("Failed to find any solution");
                break;
            }
        }

        System.out.println("Finished");
        System.out.println(state);
    }
}
