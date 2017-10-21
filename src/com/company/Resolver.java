package com.company;

import java.util.Objects;
import java.util.Random;

public class Resolver {

    Hanoi hanoi;

    public void run(String methodName, int... args) {
        if (Objects.equals(methodName, "random")) {
            random(args[0], args[1]);
        } else if(Objects.equals(methodName, "bkt")) {
            this.hanoi = new Hanoi();
            State state = hanoi.init(4, 4);
            hanoi.addStateToHistory(state);
            BK(state);
        } else if(Objects.equals(methodName, "star")) {
            aStar();
        }
    }

    public void random(int numberOfTowers, int numberOfDisks) {

        Hanoi hanoi = new Hanoi();
        State state = hanoi.init(numberOfTowers, numberOfDisks);

        hanoi.addStateToHistory(state);

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

    public void BK(State state) {
        if (hanoi.isFinalState(state)) {
            System.out.println(state);
        } else {
            for (int i = 0; i < state.getNumberOfDisks(); ++i) {
                for (int j = 0; j < state.getNumberOfTowers(); ++j) {
                    if (hanoi.isValidState(state, i, j)) {
                        State clonedState = state.clone();
                        clonedState.updateState(i, j);
                        hanoi.addStateToHistory(clonedState);
                        BK(clonedState);
                    }

                }
            }
        }
    }

    public void aStar() {
        this.hanoi = new Hanoi();
        State state = hanoi.init(3, 3);
        hanoi.addStateToHistory(state);
        StarNode starNode = new StarNode(state);
        BKStar(starNode);
    }

    public void BKStar(StarNode starNode) {
        if (hanoi.isFinalState(starNode.getState())) {
            System.out.println("Solution found");
//            System.out.println(starNode.getState());
//            System.out.println(starNode.neighbours.get(0).start.getState());
//            while(starNode.neighbours.size() > 0) {
//                System.out.print(starNode.getState() + " -> ");
//                if(starNode.equals(starNode.neighbours.get(0).start)) {
//                    break;
//                }
//                starNode = starNode.neighbours.get(0).start;
//            }
            System.out.println("");
        } else {
            for (int i = 0; i < starNode.getState().getNumberOfDisks(); ++i) {
                for (int j = 0; j < starNode.getState().getNumberOfTowers(); ++j) {
                    if (hanoi.isValidState(starNode.getState(), i, j)) {
                        State clonedState = starNode.getState().clone();
                        clonedState.updateState(i, j);
                        hanoi.addStateToHistory(clonedState);
                        StarNode clonedStarNode = new StarNode(clonedState);
                        StarPath connection = starNode.addExitNode(new StarNode(clonedState));
                        clonedStarNode.addEnterNode(connection);
                        BKStar(clonedStarNode);
                    }

                }
            }
        }
    }
}
