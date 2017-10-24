package com.company;

public class StarPath {
    public StarNode start;
    public StarNode end;
    public int cost;

    public StarPath(StarNode start, StarNode end) {
        this.start = start;
        this.end = end;
        this.cost = 1;
    }
}
