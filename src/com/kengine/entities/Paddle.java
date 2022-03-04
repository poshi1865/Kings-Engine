package com.kengine.entities;

import com.kengine.main.Game;

import java.awt.*;

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

    public void render(Graphics2D graphics) {
        //Draw Paddle 1
        graphics.setColor(Color.RED);
        graphics.fillRect(this.x, 0, 4, Game.HEIGHT);
        graphics.setColor(Color.BLACK);
        graphics.fillRect(this.x, this.y, this.width, this.height);
    }
}
