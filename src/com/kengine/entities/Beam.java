package com.kengine.entities;

import java.awt.*;

public class Beam extends Projectile{

    public Beam(int x, int y, int width, int height, int directionX, int directionY, int speed){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.directionX = directionX;
        this.directionY = directionY;
        this.speed = speed;
    }

    public void render(Graphics2D graphics) {
        //Draw Beam
        graphics.setColor(Color.GREEN);
        graphics.fillOval(this.x, this.y, this.width, this.height);
    }

    public void update(){
        this.x += this.speed * this.directionX;
        this.y += this.speed * this.directionY;
    }

    public boolean intersects(int x, int y, int width, int height) {
        if (this.x >= x && this.y >= y && this.y <= y + height) {
            return true;
        }
        return false;
    }
}
