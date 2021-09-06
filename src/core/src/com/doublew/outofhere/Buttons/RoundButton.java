package com.doublew.outofhere.Buttons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;

public class RoundButton {
	private TextureRegion buttonUp;
	private TextureRegion buttonDown;
	private Circle bounds;
	private boolean isPressed;
	
	
	
	public RoundButton(float x, float y, float radius,
			TextureRegion buttonUp, TextureRegion buttonDown) {
		this.buttonUp = buttonUp;
		this.buttonDown = buttonDown;

		bounds = new Circle(x, y, radius);
		isPressed = false;
	}
	
	public boolean isClicked(int screenX, int screenY) {
		return bounds.contains(screenX, screenY);
	}

	public void draw(SpriteBatch batcher) {
		//decrementing x and y, because batcher x it's not an circle x, circle x - center x;
		if (isPressed) {
			batcher.draw(buttonDown, bounds.x - bounds.radius, bounds.y - bounds.radius, (float)(bounds.radius * 2), (float)(bounds.radius * 2));
		} else {
			batcher.draw(buttonUp, bounds.x - bounds.radius, bounds.y - bounds.radius, (float)(bounds.radius * 2), (float)(bounds.radius * 2));
		}
	}
	
	public boolean isTouchDown(int screenX, int screenY) {

		if (bounds.contains(screenX, screenY)) {
			isPressed = true;
			
			return true;
		}
		
		return false;
	}
	
	public boolean isTouchUp(int screenX, int screenY) {
		
		// It only counts as a touchUp if the button is in a pressed state.
		if (bounds.contains(screenX, screenY) && isPressed) {
			isPressed = false;
			
			return true;
		
		}
		
		// Whenever a finger is released, we will cancel any presses.
		isPressed = false;
		//Gdx.app.log("Button", "Up!");
		return false;
	}
	
	public Circle getBounds() {
		return bounds;
	}
	
}
