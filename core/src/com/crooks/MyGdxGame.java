package com.crooks;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

import static com.badlogic.gdx.math.MathUtils.random;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	ArrayList<MapObject> treeArray;
	float time;
	Character p1 = new Character();
	Zombie z1 = new Zombie();
	Zombie z2 = new Zombie();
	Zombie z3 = new Zombie();

	BitmapFont font;
	MapObject mapObject1 = new MapObject();

	@Override
	public void create () {
		treeArray = mapObject1.populateTreeArray();
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
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
			Gdx.app.exit();
		}
		font.draw(batch,"Welcome to Zombieland - Don't get too close, they bite",250,700);

		p1.move();
		z1.move();
		z2.move();
		z3.move();
		mapObject1.drawTreeObject(batch);
		mapObject1.drawWaterObject(batch);
		p1.boundaryChecker(p1.returnSpriteState(time, batch), batch);
		z1.boundaryChecker(z1.returnSpriteState(time,batch), batch);
		z2.boundaryChecker(z2.returnSpriteState(time,batch), batch);
		z3.boundaryChecker(z3.returnSpriteState(time,batch), batch);

		for (MapObject mapObject:treeArray) {
			mapObject.drawTreeObject(batch);
		}
		batch.end();
	}
	public int randomize(){
		int x = random(0,750);
		return x;
	}

}
