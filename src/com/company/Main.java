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
//        solveHanoi("hillclimbing", 4, 4);
        solveHanoi("bkt", 4, 4);
//        solveHanoi("random", 4, 4);
//        solveHanoi("star", 4,4);
    }
}
