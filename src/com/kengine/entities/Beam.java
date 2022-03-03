package com.kengine.entities;

public class Beam extends Projectile{

    public Beam(int x, int y, int width, int height, int direction, int speed){
        this.x = x;
        this.y = y;
        this.width=width;
        this.height=height;
        this.direction=direction;
        this.speed=speed;
    }
}
