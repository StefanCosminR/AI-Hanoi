package com.company;

import java.util.ArrayList;

public class StarNode {
    private State state;
    public ArrayList<StarPath> exitNodes;
    public ArrayList<StarPath> enterNodes;

    public StarNode(State state) {
        exitNodes = new ArrayList<>();
        enterNodes = new ArrayList<>();
        this.state = state;
    }

    public StarPath addExitNode(StarNode neighbour) {
        StarPath starPath = new StarPath(this, neighbour);
        exitNodes.add(starPath);
        return starPath;
    }

    public void addEnterNode(StarPath starPath) {
        enterNodes.add(starPath);
    }

    public State getState() {
        return this.state;
    }
}
