package com.company;

public class Main {

    public static void solveHanoi(String methodName, int numberOfDisks, int numberOfTowers) {
        System.out.println("======= " + methodName + " =======");
        Resolver resolver = new Resolver();
        long startTime = System.nanoTime();
        resolver.run(methodName, numberOfDisks, numberOfTowers);
        long endTime = System.nanoTime();
        System.out.println((endTime - startTime) / 1000000000.0 + " seconds\n\n");
    }

    public static void main(String[] args) {
//        solveHanoi("hillclimbing", 3, 3);
        solveHanoi("hillclimbing2", 3, 3);
//        solveHanoi("bkt", 3, 3);
//        solveHanoi("random", 3, 3);
//        solveHanoi("star", 3,3);
    }
}
