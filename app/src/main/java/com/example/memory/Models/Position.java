package com.example.memory.Models;

public class Position {
    public int x;
    public int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o){
        if(o.getClass() != this.getClass()) return false;
        return ((Position) o).x == this.x && ((Position) o).y == this.y;
    }
}
