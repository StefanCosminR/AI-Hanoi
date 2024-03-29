package com.company;

import java.util.*;

public class Resolver {

    private Hanoi hanoi;

    public void run(String methodName, int... args) {
        this.hanoi = new Hanoi();
        if (Objects.equals(methodName, "random")) {
            random(args[0], args[1]);
        } else if (Objects.equals(methodName, "bkt")) {
            State state = hanoi.init(args[0], args[1]);
            hanoi.addStateToHistory(state);
            BK(state);
        } else if (Objects.equals(methodName, "star")) {
            aStar(args[0], args[1]);
        } else if ("hillclimbing".equals(methodName)) {
            hillclimbing(args[0], args[1]);
        } else if ("hillclimbing2".equals(methodName)) {
            hillclimbing2(args[0], args[1]);
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

        System.out.println("Finished in " + loopCounter + " loops");
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

    public int getMinimumDistanceNode(int distances[], boolean inShortestPath[]) {
        int min = Integer.MAX_VALUE;
        int minNode = -1;

        for (int node = 0; node < distances.length; ++node) {
            if (!inShortestPath[node] && distances[node] <= min) {
                min = distances[node];
                minNode = node;
            }
        }
        return minNode;
    }

    public HashMap<Integer, Integer> dijkstra(int[][] costGraph) {
        int minimumDistanceNode;
        int distances[] = new int[costGraph.length];
        boolean inShortestPath[] = new boolean[costGraph.length];
        HashMap<Integer, Integer> optimalSolutionPath = new HashMap<>();

        distances[1] = 0;
        for (int node = 2; node <= costGraph.length; ++node) {
            distances[node] = Integer.MAX_VALUE;
            inShortestPath[node] = false;
        }
        for (int count = 1; count < costGraph.length; ++count) {
            minimumDistanceNode = getMinimumDistanceNode(distances, inShortestPath);
            inShortestPath[minimumDistanceNode] = true;
            for (int node = 1; node <= costGraph.length; ++node) {
                if (!inShortestPath[node] &&
                        costGraph[minimumDistanceNode][node] != 0 &&
                        distances[minimumDistanceNode] != Integer.MAX_VALUE &&
                        distances[minimumDistanceNode] + costGraph[minimumDistanceNode][node] < distances[node]) {
                    distances[node] = distances[minimumDistanceNode] + costGraph[minimumDistanceNode][node];
                    optimalSolutionPath.put(minimumDistanceNode, node);     //i have minimum cost from minimumDistanceNode to node

                }
            }
        }
        return optimalSolutionPath;
    }


    public void generateStatesHeap(State state, ArrayList<State> solution, PriorityQueue<State> validStates) {
        if (hanoi.isFinalState(state)) {
            validStates.addAll(solution);
        } else {
            for (int i = 0; i < state.getNumberOfDisks(); ++i) {
                for (int j = 0; j < state.getNumberOfTowers(); ++j) {
                    if (hanoi.isValidState(state, i, j)) {
                        State clonedState = state.clone();
                        clonedState.updateState(i, j);
                        hanoi.addStateToHistory(clonedState);
                        solution.add(clonedState);
                        generateStatesHeap(clonedState, solution, validStates);
                        solution.remove(solution.size() - 1);
                        hanoi.removeFromHistory(clonedState);
                    }

                }
            }
        }
    }

    public void aStar(int numberOfDisks, int numberOfTowers) {
        this.hanoi = new Hanoi();
        State state = hanoi.init(numberOfTowers, numberOfDisks);
        PriorityQueue<State> validStates = new PriorityQueue<>(Comparator.comparingInt(State::getTransitionsCount));
        generateStatesHeap(state, new ArrayList<>(), validStates);
        state = hanoi.init(numberOfTowers, numberOfDisks);
        while (!hanoi.isFinalState(state) && validStates.size() > 0) {
            state = validStates.peek();
            validStates.poll();
        }
        System.out.println(state);
    }


//    public void BKStar(StarNode starNode) {
//        if (!hanoi.isFinalState(starNode.getState())) {
//            for (int i = 0; i < starNode.getState().getNumberOfDisks(); ++i) {
//                for (int j = 0; j < starNode.getState().getNumberOfTowers(); ++j) {
//                    if (hanoi.isValidState(starNode.getState(), i, j)) {
//                        State clonedState = starNode.getState().clone();
//                        clonedState.updateState(i, j);
//                        hanoi.addStateToHistory(clonedState);
//                        StarNode clonedStarNode = new StarNode(clonedState);
//                        StarPath connection = starNode.addExitNode(new StarNode(clonedState));
//                        clonedStarNode.addEnterNode(connection);
//                        BKStar(clonedStarNode);
//                        hanoi.removeFromHistory(starNode.getState());
//                    }
//                }
//            }
//        }
//    }

    private State getRandomSolution(int numberOfTowers, int numberOfDisks) {
        Hanoi hanoi = new Hanoi();
        State state = hanoi.init(numberOfTowers, numberOfDisks);
        Random random = new Random();

        while (!hanoi.isFinalState(state)) {
            int selectedPosition = random.nextInt(numberOfDisks);
            int selectedTower = random.nextInt(numberOfTowers);
            if (hanoi.isValidState(state, selectedPosition, selectedTower)) {
                state.updateState(selectedPosition, selectedTower);
                hanoi.addStateToHistory(state);
            }
        }
        return state;
    }

    public void hillclimbing(int numberOfTowers, int numberOfDisks) {
        int iteration, stuck;
        State candidateSolution;
        State currentSolution = getRandomSolution(numberOfTowers, numberOfDisks);

        iteration = stuck = 0;
        while (iteration < 1000 && stuck < 100) {
            candidateSolution = getRandomSolution(numberOfTowers, numberOfDisks);
            if (currentSolution.getTransitionsCount() > candidateSolution.getTransitionsCount()) {
                currentSolution = candidateSolution;
                stuck = 0;
            } else {
                ++stuck;
            }
            ++iteration;
        }
        System.out.println(currentSolution);
    }

    public int compareStates(State state1, State state2) {
        int fitness = 0;
        List<Integer> state1Positions = state1.getElements();
        List<Integer> state2Positions = state2.getElements();

        for (int index = 0; index < state1Positions.size(); ++index) {
            if (state1Positions.get(index) < state2Positions.get(index)) {
                fitness -= 1;
            } else {
                fitness += 1;
            }
        }
        return fitness;
    }

    public void hillclimbing2(int numberOfTowers, int numberOfDisks) {
        int iteration, randomDisk, randomTower;
        int MAX_ITERATIONS = 1000;
        Random random = new Random();
        State candidateState;
        State currentState = hanoi.init(numberOfTowers, numberOfDisks);

        for (iteration = 0; iteration < MAX_ITERATIONS && !hanoi.isFinalState(currentState); ++iteration) {
            randomDisk = random.nextInt(numberOfDisks);
            randomTower = random.nextInt(numberOfTowers);

            if (hanoi.isValidState(currentState, randomDisk, randomTower)) {
                candidateState = currentState.clone();
                candidateState.updateState(randomDisk, randomTower);
                if (compareStates(currentState, candidateState) > 0) {
                    currentState = candidateState;
                }
            }
        }
        if (iteration < MAX_ITERATIONS) {
            System.out.println(currentState);
        } else {
            System.out.println("Solution not found");
        }
    }
}
