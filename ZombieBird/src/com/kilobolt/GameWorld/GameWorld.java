package com.kilobolt.GameWorld;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.kilobolt.GameObjects.Bird;
import com.kilobolt.GameObjects.Grass;
import com.kilobolt.GameObjects.Pipe;
import com.kilobolt.GameObjects.ScrollHandler;
import com.kilobolt.Helpers.AssetLoader;

public class GameWorld {

	
	private Rectangle rect = new Rectangle(0, 0, 17, 12);
	
	private Bird bird;
	private ScrollHandler scroller;
	
	private Rectangle ground;
	
	private int score =0;
	
	private GameState currentState;
	
	private float runTime = 0;
	
	public int midPointY;
	
	public enum GameState {

	    MENU,READY, RUNNING, GAMEOVER, HIGHSCORE

	}
	
	public GameWorld(int midPointY)
	{
		this.midPointY=midPointY;
		currentState=GameState.MENU;
		bird = new Bird(33, midPointY - 5, 17, 12);
		
		// The grass should start 66 pixels below the midPointY
        scroller = new ScrollHandler(this,midPointY + 66);
        
        ground = new Rectangle(0, midPointY + 66, 136, 11);
	}
	
	  public void update(float delta) {

	        runTime += delta;

	        switch (currentState) {
	        case READY:
	        case MENU:
	            updateReady(delta);
	            break;

	        case RUNNING:
	            updateRunning(delta);
	            break;
	        default:
	            break;
	        }

	    }
	  
	  private void updateReady(float delta) {
		  	bird.updateReady(runTime);
	        scroller.updateReady(delta);
	    }
	
	   public void updateRunning(float delta) {
	        // Add a delta cap so that if our game takes too long
	        // to update, we will not break our collision detection.

	        if (delta > .15f) {
	            delta = .15f;
	        }

	        bird.update(delta);
	        scroller.update(delta);

	        if (scroller.collides(bird) && bird.isAlive()) {
	            scroller.stop();
	            bird.die();
	            AssetLoader.dead.play();
	        }

	        if (Intersector.overlaps(bird.getBoundingCircle(), ground)) {
	            scroller.stop();
	            bird.die();
	            bird.decelerate();
	            currentState = GameState.GAMEOVER;
	            
	            if (score > AssetLoader.getHighScore()) {
	                AssetLoader.setHighScore(score);
	                currentState = GameState.HIGHSCORE;
	            }
	        }
	    }
	
	
    public Bird getBird() {
        return bird;

    }
    
    public int getMidPointY() {
        return midPointY;
    }
    
    public ScrollHandler getScroller()
    {
    	return scroller;
    }
	
    public Rectangle getRect() {
        return rect;
    }
    
    public int getScore() {
        return score;
    }

    public void addScore(int increment) {
        score += increment;
    }
    
    public boolean isReady()
    {
    	return currentState == GameState.READY;
    
    }
    
    public boolean isHighScore() {
        return currentState == GameState.HIGHSCORE;
    }
    
    public void start()
    {
    	currentState=GameState.RUNNING;
    }
    
    public void ready() {
        currentState = GameState.READY;
    }
    
    public void restart() {
        currentState = GameState.READY;
        score = 0;
        bird.onRestart(midPointY - 5);
        scroller.onRestart();
        currentState = GameState.READY;
    }


    
    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }
    

    public boolean isMenu() {
        return currentState == GameState.MENU;
    }

    public boolean isRunning() {
        return currentState == GameState.RUNNING;
    }
}
