package com.crooks;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
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
	static final int WIDTH = 16;
	static final int HEIGHT = 16;
	static final float decelaration = .50f; //How quickly the character comes to a stop where [0 =< N <= 1]
	static final float acceleration = 3f;  	//SpaceBar modifier for speed.
	static final float MAX_VELOCITY = 100;  // initial movement speed
	float time;
	boolean faceRight;
	Animation walkRight,walkLeft, walkDown,walkUp;
	Character p1 = new Character();

	TextureRegion rightSprite,upSprite, upflip,downSprite,downFlip,testSprite, largeTest;

	@Override
	public void create () {

		batch = new SpriteBatch();
		p1.drawCharacter();
//		Texture sheet = new Texture("tiles.png");
//		TextureRegion[][] tiles = TextureRegion.split(sheet,16,16);
//		TextureRegion[][] smallerTiles = TextureRegion.split(sheet,8,8);
//		TextureRegion[][] largerTiles = TextureRegion.split(sheet,24,24);
//		testSprite = smallerTiles[1][0];
//		testSprite.setRegionHeight(16);
//		testSprite.setRegionWidth(16);
//
//
//		//Creating the X coordinate mirror for the sprite walking down
//		downSprite = tiles[6][0];
//		downFlip = new TextureRegion(downSprite);
//		downFlip.flip(true,false);
//
//		//Creating the mirrored sprite for walking up;
//		upSprite = tiles[6][1];
//		upflip = new TextureRegion(upSprite);
//		upflip.flip(true,false);
//
//
//		rightSprite = tiles[6][2];
//
//		//initializing the Walking animations
//		walkRight = new Animation(0.2f,rightSprite,tiles[6][3]);
//		walkLeft = new Animation(0.2f,rightSprite, tiles[6][3]);
//		walkUp = new Animation(0.2f, upSprite, upflip);
//		walkDown = new Animation(0.2f, downSprite,downFlip);
	}


	@Override
	public void render () {

		time += Gdx.graphics.getDeltaTime();   //Animations will use this time to calculate frame usage;

		Gdx.gl.glClearColor(.1f, .8f, .5f, 0.9f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		//mapBuilder(random);
		p1.move();
		p1.boundaryChecker(p1.returnSpriteState(time, batch), batch);

		batch.end();
	}
	public int randomize(){
		int x = random(0,750);
		return x;
	}
	/*public void mapBuilder(int random){
		batch.draw(testSprite,random,random2);
		batch.draw(testSprite,random,random2-30);

	}
	*/
	//Setting Wall Boundaries so Character appears on opposite side - A la PacMan
//	public void boundaryChecker(TextureRegion spriteState){
//
//		if (x >Gdx.graphics.getWidth()){
//			x = -14;
//			batch.draw(spriteState,x,y, WIDTH *3, HEIGHT*3);
//		}
//		if(x<-16){
//			x= Gdx.graphics.getWidth()-2;
//			batch.draw(spriteState,x,y, WIDTH *3, HEIGHT*3);
//		}
//		if (y >Gdx.graphics.getHeight()){
//			y = -8;
//			batch.draw(spriteState,x,y, WIDTH *3, HEIGHT*3);
//		}
//		if(y<-16){
//			y = Gdx.graphics.getHeight()-8;
//			batch.draw(spriteState,x,y, WIDTH *3, HEIGHT*3);
//		}
//	}


//	public void move(){
//
//		if(Gdx.input.isKeyPressed(Input.Keys.UP)){
//			yv = MAX_VELOCITY;
//			if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
//				yv=p1.accelerate(yv);
//			}
//
//		} else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
//			yv= -MAX_VELOCITY;
//			if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
//				yv=p1.accelerate(yv);
//			}
//		}
//		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
//			xv = -MAX_VELOCITY;
//			faceRight = false;
//			if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
//				xv=p1.accelerate(xv);
//			}
//		}
//		else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
//			xv = MAX_VELOCITY;
//			faceRight = true;
//			if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
//				xv=p1.accelerate(xv);
//			}
//		}
//		float delta = Gdx.graphics.getDeltaTime();
//		y += yv*delta;
//		x += xv*delta;
//		yv = p1.deaccelarate(yv);
//		xv = p1.deaccelarate(xv);
//
//
//	}

//	public float accelerate(float velocity){
//		velocity *= acceleration;
//		return velocity;
//	}

//	public float deaccelarate(float velocity){
//		velocity *= decelaration;
//		if (Math.abs(velocity)<1){
//			velocity = 0;
//		}
//		return velocity;
//	}

}
