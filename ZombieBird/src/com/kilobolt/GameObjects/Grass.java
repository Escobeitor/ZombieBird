package com.kilobolt.GameObjects;

public class Grass extends Scrollable {

	// When Grass's constructor is invoked, invoke the super (Scrollable)
	public Grass(float x, float y, int width, int height, float scrollSpeed) {
		super(x, y, width, height, scrollSpeed);
		
	}
	
	public void onRestart(float x, float scrollSpeed) {
        position.x = x;
        velocity.x = scrollSpeed;
    }
	
	

}
