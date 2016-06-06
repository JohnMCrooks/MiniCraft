/*
 * Copyright (c) 2016.
 */

package com.crooks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by johncrooks on 6/3/16.
 */
public class Zombie {
    float x,y,xv,yv;
    int duration = 10;
    static final float decelaration = .99f;
    static final float acceleration = 2f;  	//SpaceBar modifier for speed.
    static final float MAX_VELOCITY = 50;  // initial movement speed
    float time;
    boolean faceRight;
    static final int WIDTH = 16;
    static final int HEIGHT = 16;
    Animation walkRight,walkLeft, walkDown,walkUp;
    TextureRegion rightSprite,upSprite, upflip,downSprite,downFlip,testSprite, largeTest;
    SpriteBatch batch;
    boolean hasDuration = false;

    public Zombie() {
    }
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public float getXv() {
        return xv;
    }
    public float getYv() {
        return yv;
    }

    public float deaccelarate(float velocity){
        velocity *= decelaration;
        if (Math.abs(velocity)<1){
            velocity = 0;
        }
        return velocity;
    }
    public void move() {
        //Movement based on a Randomly Calculated number... Still Needs improvement to smooth out the twitch
           if (!hasDuration) {              // On the first call there is no duration so that the zombies will begin moving immediately.
               if (getRandomNum() > 0) {    //This if statement controls Left/Right movement
                   yv = MAX_VELOCITY;
                   hasDuration = true;      //after movement has been executed, change the state of duration to true
                   duration = 25;           // set the duration to a length that seems appropriate, in this case it's 25 frames worth
                   --duration;              // iterate duration
               } else if (getRandomNum() < 0) {
                   yv = -MAX_VELOCITY;
                   hasDuration = true;
                   duration = 25;
                   --duration;
               }
               if (getRandomNum() > 0) {  // this if controls Up/Down movement.
                   xv = -MAX_VELOCITY;
                   faceRight = false;
                   hasDuration = true;
                   duration = 25;
                   --duration;
               } else if (getRandomNum() > 0) {
                   xv = MAX_VELOCITY;
                   faceRight = true;
                   hasDuration = true;
                   duration = 25;
                   --duration;
               }
           } else{                      // If Duration is true, Iterate duration down. This prevents twitch movements
               --duration;
               if (duration==0){
                   hasDuration = false;
               }
           }
        float delta = Gdx.graphics.getDeltaTime();
        y += yv * delta;
        x += xv * delta;
        yv = deaccelarate(yv);
        xv = deaccelarate(xv);
    }
    public void boundaryChecker(TextureRegion spriteState, SpriteBatch batch){

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

    public void drawCharacter(){
        Texture sheet = new Texture("tiles.png");
        TextureRegion[][] tiles = TextureRegion.split(sheet,16,16);
        TextureRegion[][] smallerTiles = TextureRegion.split(sheet,8,8);
        testSprite = smallerTiles[1][0];
        testSprite.setRegionHeight(16);
        testSprite.setRegionWidth(16);


        //Creating the X coordinate mirror for the sprite walking down
        downSprite = tiles[6][4];
        downFlip = new TextureRegion(downSprite);
        downFlip.flip(true,false);

        //Creating the mirrored sprite for walking up;
        upSprite = tiles[6][5];
        upflip = new TextureRegion(upSprite);
        upflip.flip(true,false);

        //define sprite for walking to the right
        rightSprite = tiles[6][6];

        //initializing the Walking animations
        walkRight = new Animation(0.2f,rightSprite,tiles[6][7]);
        walkLeft = new Animation(0.2f,rightSprite, tiles[6][7]);
        walkUp = new Animation(0.2f, upSprite, upflip);
        walkDown = new Animation(0.2f, downSprite,downFlip);

    }

    public TextureRegion returnSpriteState(float time, SpriteBatch batch){
        TextureRegion spriteState;

        // Setting animation for walking direction based off of velocity
        if(getXv()>0){
            spriteState = walkRight.getKeyFrame(time,true);
        }else if (getXv()<0){
            spriteState = walkRight.getKeyFrame(time,true);
        }else if (getYv()>0){
            spriteState = walkUp.getKeyFrame(time,true);
        } else if (getYv()<0){
            spriteState = walkDown.getKeyFrame(time,true);
        }else{
            spriteState = downSprite;
        }

        // Making Right Sprite Face left
        if (faceRight){
            batch.draw(spriteState,getX(),getY(), WIDTH *3, HEIGHT*3);
        }else if(!faceRight){
            batch.draw(spriteState,(getX() + WIDTH * 3),getY(), WIDTH *-3, HEIGHT*3);
        }else{}
        return spriteState;

    }

    public double getRandomNum(){
        double tempVal = Math.random();
        double positive = Math.random();


        tempVal = (tempVal *1000)%5;
        if (positive <0.5d){
            tempVal = tempVal*-1;
            return tempVal;
        }
        return tempVal;
    }

}
