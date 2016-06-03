/*
 * Copyright (c) 2016.
 */

package com.crooks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by johncrooks on 6/2/16.
 */
public class Character {
    float x,y,xv,yv;
    static final float decelaration = .50f;
    static final float acceleration = 3f;  	//SpaceBar modifier for speed.
    static final float MAX_VELOCITY = 100;  // initial movement speed
    float time;
    boolean faceRight;
    static final int WIDTH = 16;
    static final int HEIGHT = 16;
    SpriteBatch batch;

    public Character( float x, float y, float xv, float yv) {

        this.time = getTime();
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

    public float deaccelarate(float velocity){
        velocity *= decelaration;
        if (Math.abs(velocity)<1){
            velocity = 0;
        }
        return velocity;
    }

    public void move() {

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            yv = MAX_VELOCITY;
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                yv = accelerate(yv);
            }

        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            yv = -MAX_VELOCITY;
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                yv = accelerate(yv);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            xv = -MAX_VELOCITY;
            faceRight = false;
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                xv = accelerate(xv);
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            xv = MAX_VELOCITY;
            faceRight = true;
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                xv = accelerate(xv);
            }
        }
        float delta = Gdx.graphics.getDeltaTime();
        y += yv * delta;
        x += xv * delta;
        yv = deaccelarate(yv);
        xv = deaccelarate(xv);
    }

    public void boundaryChecker(TextureRegion spriteState){

        if (x >Gdx.graphics.getWidth()){
            x = -14;
            batch.draw(spriteState,x,y, WIDTH *3, HEIGHT*3);
        }
        if(x<-16){
            x= Gdx.graphics.getWidth()-2;
            batch.draw(spriteState,x,y, WIDTH *3, HEIGHT*3);
        }
        if (y >Gdx.graphics.getHeight()){
            y = -8;
            batch.draw(spriteState,x,y, WIDTH *3, HEIGHT*3);
        }
        if(y<-16){
            y = Gdx.graphics.getHeight()-8;
            batch.draw(spriteState,x,y, WIDTH *3, HEIGHT*3);
        }
    }

}
