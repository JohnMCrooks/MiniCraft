/*
 * Copyright (c) 2016.
 */

package com.crooks;

import com.badlogic.gdx.Gdx;

/**
 * Created by johncrooks on 6/2/16.
 */
public class Character {
    float x,y,xv,yv;
    static final float decelaration = .50f;
    static final float acceleration = 3f;  	//SpaceBar modifier for speed.
    static final float MAX_VELOCITY = 100;  // initial movement speed
    float time;

    public Character( float x, float y, float xv, float yv) {

        this.time = Gdx.graphics.getDeltaTime();
        this.x = x;
        this.y = y;
        this.xv = xv;
        this.yv = yv;
    }
    public Character() {
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getXv() {
        return xv;
    }

    public void setXv(float xv) {
        this.xv = xv;
    }

    public float getYv() {
        return yv;
    }

    public void setYv(float yv) {
        this.yv = yv;
    }

    public static float getDecelaration() {
        return decelaration;
    }

    public static float getAcceleration() {
        return acceleration;
    }

    public static float getMaxVelocity() {
        return MAX_VELOCITY;
    }

    public float getTime() {
        return time;
    }

    public float accelerate(float velocity){
        velocity *= acceleration;
        return velocity;
    }
}
