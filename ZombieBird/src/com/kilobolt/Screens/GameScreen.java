package com.kilobolt.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.kilobolt.GameWorld.GameRenderer;
import com.kilobolt.GameWorld.GameWorld;
import com.kilobolt.Helpers.InputHandler;

public class GameScreen implements Screen {
	
	private GameWorld world;
	private GameRenderer renderer;
	private float runTime=0;
	
	public GameScreen(){
		
		float screenWidth = Gdx.graphics.getWidth();
	    float screenHeight = Gdx.graphics.getHeight();      
	    float gameWidth = 136;
	    float gameHeight = screenHeight / (screenWidth / gameWidth);
	        
	    int midPointY = (int) (gameHeight / 2);		
		world=new GameWorld(midPointY);
		
		System.out.println("GameScreen Attached");
	
		Gdx.input.setInputProcessor(new InputHandler(world, screenWidth / gameWidth, screenHeight / gameHeight));
		renderer = new GameRenderer(world,(int)gameHeight,midPointY);
	}

	@Override
	public void render(float delta) {
		runTime += delta;
        world.update(delta);
        renderer.render(delta, runTime);
		
	}

	@Override
	public void resize(int width, int height) {
		System.out.println("GameScreen - resizing");		
	}

	@Override
	public void show() {
		System.out.println("GameScreen - show called");
		
	}

	@Override
	public void hide() {
		System.out.println("GameScreen - hide called");
		
	}

	@Override
	public void pause() {
		System.out.println("GameScreen - pause called");
		
	}

	@Override
	public void resume() {
		System.out.println("GameScreen - resume called");
		
	}

	@Override
	public void dispose() {
		
	}

}
