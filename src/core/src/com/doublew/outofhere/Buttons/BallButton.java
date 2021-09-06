package com.doublew.outofhere.Buttons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.doublew.outofhere.Support.AssetLoader;

public class BallButton {
	private float shadowIndent, steadyLabelWidth, steadyLabelHeight;
	private TextureRegion arrowUp, arrowDown, arrowLeft, arrowRight,
			ballTexture, currentArrow, steadyTexture;
	private Circle bounds;
	private boolean isPressed, transition, steady;

	private int way;

	public BallButton(float x, float y, float radius, float shadowIndent) {
		ballTexture = AssetLoader.ballTexture;
		arrowUp = AssetLoader.arrowUp;
		arrowDown = AssetLoader.arrowDown;
		arrowLeft = AssetLoader.arrowLeft;
		arrowRight = AssetLoader.arrowRight;
		steadyTexture = AssetLoader.steady;
		way = 4;
		update(way);
		bounds = new Circle(x, y, radius);
		isPressed = false;
		steadyLabelWidth = 33;
		steadyLabelHeight = 33;
		// saves way
		transition = false;
		steady = true;
		// 5 states 0 1 2 3 4
		
		this.shadowIndent = shadowIndent;
	}

	public boolean isClicked(int screenX, int screenY) {
		return bounds.contains(screenX, screenY);
	}

	public void update(int way) {
		switch (way) {
		case 0:
			currentArrow = arrowUp;
			this.way = way;
			transition = false;
			steady = false;
			break;
		case 1:
			currentArrow = arrowLeft;
			this.way = way;
			transition = false;
			steady = false;
			break;
		case 2:
			currentArrow = arrowDown;
			this.way = way;
			transition = false;
			steady = false;
			break;
		case 3:
			currentArrow = arrowRight;
			this.way = way;
			transition = false;
			steady = false;
			break;
		case 4:
			transition = true;
			steady = false;
			break;
		case 5:
			transition = false;
			steady = true;
			break;
		}

	}

	public void draw(SpriteBatch batcher) {
		batcher.draw(ballTexture, bounds.x - bounds.radius, bounds.y - bounds.radius, bounds.radius * 2,
				bounds.radius * 2);
		if (!transition) {
			if(!steady){
				batcher.draw(currentArrow, bounds.x - bounds.radius, bounds.y - bounds.radius, bounds.radius * 2,
						bounds.radius * 2);
			}else {
				batcher.draw(steadyTexture, bounds.x - (steadyLabelWidth / 2f), bounds.y - (steadyLabelHeight / 2f),
						steadyLabelWidth, steadyLabelHeight);
			}
		}
		
	}

	public void drawShadow(SpriteBatch shadowBatcher) {
		shadowBatcher.draw(ballTexture, bounds.x - bounds.radius + shadowIndent, bounds.y - bounds.radius + shadowIndent, bounds.radius * 2f, bounds.radius * 2f);
	}

	public boolean isTouchDown(int screenX, int screenY) {
		if (bounds.contains(screenX, screenY) && !transition && !steady) {
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
		// Gdx.app.log("Button", "Up!");
		return false;
	}

	public Circle getBounds() {
		return bounds;
	}

	public int getWay() {
		return way;
	}
}
