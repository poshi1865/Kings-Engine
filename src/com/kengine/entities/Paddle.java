package com.kengine.entities;

public class Paddle {
    public int x;
    public int y;
    public int speed = 10;
    public final int width = 10;
    public final int height = 200;

    public Paddle(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
