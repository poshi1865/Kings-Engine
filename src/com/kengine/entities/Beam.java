package com.kengine.entities;

public class Beam extends Projectile{

    public Beam(int x, int y, int width, int height, boolean directionX, boolean directionY, int speed){
        this.x = x;
        this.y = y;
        this.width=width;
        this.height=height;
        this.directionX=directionX;
        this.directionY=directionY;
        this.speed=speed;
    }
}
