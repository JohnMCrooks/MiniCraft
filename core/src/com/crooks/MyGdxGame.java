package com.crooks;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	float x,y,xv,yv;
	static final int WIDTH = 16;
	static final int HEIGHT = 16;
	static final float decelaration = .80f;
	static final float MAX_VELOCITY = 100;

	TextureRegion leftSprite,rightSprite,upSprite,downSprite;

	@Override
	public void create () {

		batch = new SpriteBatch();
		Texture sheet = new Texture("tiles.png");
		TextureRegion[][] tiles = TextureRegion.split(sheet,16,16);
		downSprite = tiles[6][0];
		upSprite = tiles[6][1];
		rightSprite = tiles[6][2];
	}

	@Override
	public void render () {
		move();
		Gdx.gl.glClearColor(.1f, .8f, .5f, 0.9f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(downSprite,x,y, WIDTH *3, HEIGHT*3);
		batch.end();
	}

	public void move(){
		if(Gdx.input.isKeyPressed(Input.Keys.UP)){
			yv = MAX_VELOCITY;
		} else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			yv= -MAX_VELOCITY;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			xv = -MAX_VELOCITY;
			//faceRight = false;
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			xv = MAX_VELOCITY;
			//faceRight = true;
		}
		float delta = Gdx.graphics.getDeltaTime();
		y += yv*delta;
		x += xv*delta;
		yv = deaccelarate(yv);
		xv = deaccelarate(xv);


	}

	public float deaccelarate(float velocity){
		velocity *= decelaration;
		if (Math.abs(velocity)<1){
			velocity = 0;
		}
		return velocity;
	}

}
