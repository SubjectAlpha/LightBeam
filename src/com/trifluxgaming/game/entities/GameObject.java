package com.trifluxgaming.game.entities;

import com.trifluxgaming.game.util.ID;

import java.awt.Rectangle;
import java.awt.Graphics;

public abstract class GameObject {

    protected float x, y;
    protected ID id;
    protected float velX, velY;

    float prevx, prevy, prev_velx, prev_vely;

    public GameObject(int x, int y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    public void setX(float x){
        this.x = x;
    }

    public void setY(float y){
        this.y = y;
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public void setId(ID id){
        this.id = id;
    }

    public ID getId(){
        return id;
    }

    public void setVelX(float velX){
        this.velX = velX;
    }

    public void setVelY(float velY){
        this.velY = velY;
    }

    public float getVelX(){
        return velX;
    }

    public float getVelY(){
        return velY;
    }

    public void pause(){
        prev_vely = getVelY();
        prev_velx = getVelX();
        prevy = getY();
        prevx = getX();

        setVelX(0);
        setVelY(0);
    }

    public void resume(){
        setVelY(prev_vely);
        setVelX(prev_velx);
        setX(prevx);
        setY(prevy);
    }
}
