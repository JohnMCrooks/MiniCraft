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
	static final float decelaration = .50f;  //How quickly the character slides to a stop
	static final float acceleration = 2f;  //SpaceBar modifier for speed.
	static final float MAX_VELOCITY = 100;
	static final float MAX_WIDTH = 800;
	static final float MAX_HEIGHT = 600;
	boolean faceRight;

	TextureRegion rightSprite,upSprite,downSprite;

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

		TextureRegion spriteState;
		if(xv>0){
			spriteState = rightSprite;
		}else if (xv<0){
			spriteState = rightSprite;
		}else if (yv>0){
			spriteState = upSprite;
		} else if (yv<0){
			spriteState = downSprite;
		}else{
			spriteState = downSprite;
		}

		Gdx.gl.glClearColor(.1f, .8f, .5f, 0.9f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		if (faceRight){
			batch.draw(spriteState,x,y, WIDTH *3, HEIGHT*3);
		}else if(!faceRight){
			batch.draw(spriteState,(x + WIDTH * 3),y, WIDTH *-3, HEIGHT*3);
		}else{}

		//Setting Wall Boundries so Character appears on opposite side - A la PacMan
		if (x >MAX_WIDTH){
			x = -8;
			batch.draw(spriteState,x,y, WIDTH *3, HEIGHT*3);
		}
		if(x<-16){
			batch.draw(spriteState,x,y, WIDTH *3, HEIGHT*3);
		}
		if (y >MAX_HEIGHT){
			y = -8;
			batch.draw(spriteState,x,y, WIDTH *3, HEIGHT*3);
		}
		if(y<-16){
			y = MAX_HEIGHT-8;
			batch.draw(spriteState,x,y, WIDTH *3, HEIGHT*3);
		}
		batch.end();
	}

	public void move(){

		if(Gdx.input.isKeyPressed(Input.Keys.UP)){
			yv = MAX_VELOCITY;
			if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
				yv=accelerate(yv);
			}

		} else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			yv= -MAX_VELOCITY;
			if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
				yv=accelerate(yv);
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			xv = -MAX_VELOCITY;
			faceRight = false;
			if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
				xv=accelerate(xv);
			}
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			xv = MAX_VELOCITY;
			faceRight = true;
			if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
				xv=accelerate(xv);
			}
		}
		float delta = Gdx.graphics.getDeltaTime();
		y += yv*delta;
		x += xv*delta;
		yv = deaccelarate(yv);
		xv = deaccelarate(xv);


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

}
