package com.company;

/**
 * @author Dorneanu Dragos-Andrei(dragos.a.dorneanu@gmail.com) on 10/22/17
 */
public class Transition {
    private int disk;
    private int tower;

    public Transition(int disk, int tower) {
        this.disk = disk;
        this.tower = tower;
    }

    public int getDisk() {
        return disk;
    }

    public void setDisk(int disk) {
        this.disk = disk;
    }

    public int getTower() {
        return tower;
    }

    public void setTower(int tower) {
        this.tower = tower;
    }

    @Override
    public String toString() {
        String stringifyTransition = "(";
        stringifyTransition += disk + ", ";
        stringifyTransition += tower + ")";
        return stringifyTransition;
    }
}
