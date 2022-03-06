package com.kengine.entities;

import com.kengine.main.Game;

import java.awt.*;

public class Paddle {
    public int x;
    public int y;
    public int speed = 10;
    public final int width = 10;
    public final int height = 100;
    public Color color;

    public Paddle(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public void render(Graphics2D graphics) {
        //Draw Paddle 1
        graphics.setColor(Color.BLACK);
        graphics.fillRect(this.x, 0, 2, Game.HEIGHT);
        graphics.setColor(this.color);
        graphics.fillRect(this.x - this.width / 2 + 1, this.y, this.width, this.height);
    }
}
