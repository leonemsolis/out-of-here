package com.doublew.outofhere.Buttons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Button {
	private TextureRegion buttonUp;
	private TextureRegion buttonDown;
	private Rectangle bounds;
	private boolean isPressed;
	
	public Button(float x, float y, float width, float height,
			TextureRegion buttonUp, TextureRegion buttonDown) {
		this.buttonUp = buttonUp;
		this.buttonDown = buttonDown;

		bounds = new Rectangle(x, y, width, height);
		isPressed = false;
	}
	
	public boolean isClicked(int screenX, int screenY) {
		return bounds.contains(screenX, screenY);
	}

	public void draw(SpriteBatch batcher) {
		if (isPressed) {
			batcher.draw(buttonDown, bounds.x, bounds.y, bounds.width, bounds.height);
		} else {
			batcher.draw(buttonUp, bounds.x, bounds.y, bounds.width, bounds.height);
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
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public void setX(float value) {
		bounds.x = value;
	}
	
	public void setY(float value) {
		bounds.y = value;
	}
	
	public float getWidth() {
		return bounds.width;
	}
	
	public float getHeight() {
		return bounds.height;
	}
}
