package com.kengine.entities;

import java.awt.Color;

public class Paddle {
    public int x;
    public int y;
    public int speed = 5;
    public final int width = 10;
    public final int height = 100;

    public Color color;

    public Paddle(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
