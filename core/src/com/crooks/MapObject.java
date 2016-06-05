/*
 * Copyright (c) 2016.
 */

package com.crooks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by johncrooks on 6/2/16.
 */
public class MapObject {
    float x, y;
    TextureRegion sprite;
    ArrayList<TextureRegion> treeArray;
    boolean beenRandomized = false;


    public MapObject() {
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

    public void drawTreeObject(SpriteBatch batch) {

        Texture sheet = new Texture("tiles.png");
        TextureRegion[][] smallerTile = TextureRegion.split(sheet, 8, 8);

        sprite = smallerTile[1][0];
        sprite.setRegionHeight(16);
        sprite.setRegionWidth(16);

        //Randomizes location of object on first render and only the first render
        if (beenRandomized == false) {
            Random ran = new Random();
            int randomNumber = ran.nextInt(770);
            setX(randomNumber);
            randomNumber = ran.nextInt(770);
            setY(randomNumber);

            beenRandomized = true;
        }
        //Don't forget to actually draw your object!!
        batch.draw(sprite, getX(), getY(), 64, 64);
    }

    // Populating an Array list with trees for the map.
    public ArrayList<MapObject> populateTreeArray() {
        ArrayList<MapObject> treeArray = new ArrayList<MapObject>();

        int maxQuant = 15;  // totally arbitrary max number of trees to produce.

        while (maxQuant > 0) {
            MapObject m1 = new MapObject();
            treeArray.add(m1);
            --maxQuant;
        }
        System.out.println(treeArray.toString());
        return treeArray;
    }


    public void drawWaterObject(SpriteBatch batch){
        Texture sheet = new Texture("tiles.png");
        TextureRegion[][] tile = TextureRegion.split(sheet, 16, 16);


        sprite = tile[3][0];
        sprite.setRegionHeight(24);
        sprite.setRegionWidth(48);

        batch.draw(sprite, getX(), getY(), 96, 64);
    }
    @Override
    public String toString() {
        return "MapObject{" +
                "treeArray=" + treeArray +
                '}';
    }
}
