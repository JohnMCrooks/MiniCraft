package com.crooks;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;

import java.util.ArrayList;

import static com.badlogic.gdx.math.MathUtils.random;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	//float x,y,xv,yv;    //Testing a refactor
	int random = randomize();
	int random2 = randomize();
	float time;
	Character p1 = new Character();
	Zombie z1 = new Zombie();
	Zombie z2 = new Zombie();
	Zombie z3 = new Zombie();
	TextureRegion rightSprite,upSprite, upflip,downSprite,downFlip,testSprite, largeTest;
	BitmapFont font;

	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.BLACK);
		p1.drawCharacter();
		z1.drawCharacter();
		z2.drawCharacter();
		z3.drawCharacter();
	}
	@Override
	public void render () {

		time += Gdx.graphics.getDeltaTime();   //Animations will use this time to calculate frame usage;

		Gdx.gl.glClearColor(.1f, .8f, .5f, 0.9f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		font.draw(batch,"Welcome to Zombieland - Don't get too close, they bite",250,700);
		p1.move();
		z1.move();
		z2.move();
		z3.move();
		p1.boundaryChecker(p1.returnSpriteState(time, batch), batch);
		z1.boundaryChecker(z1.returnSpriteState(time,batch), batch);
		z2.boundaryChecker(z2.returnSpriteState(time,batch), batch);
		z3.boundaryChecker(z3.returnSpriteState(time,batch), batch);

		batch.end();
	}
	public int randomize(){
		int x = random(0,750);
		return x;
	}

}
