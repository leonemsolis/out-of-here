package com.doublew.outofhere.Buttons;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.doublew.outofhere.Support.AssetLoader;

public class HelpBallButton {
	
	private Circle bounds;
	private float shadowIndent;
	private TextureRegion arrowUp, arrowDown, arrowLeft, arrowRight, shadowTexture, homeTexture;
	private ArrayList<Integer> stack2;
	private int currentStatus, currentOrder;
	private boolean ready;
	private boolean isPressed;
	
	public HelpBallButton(float x, float y, float radius, float shadowIndent) {
		bounds = new Circle(x, y, radius);
		this.shadowIndent = shadowIndent;
		arrowUp = AssetLoader.arrowUp;
		arrowDown = AssetLoader.arrowDown;
		arrowLeft = AssetLoader.arrowLeft;
		arrowRight = AssetLoader.arrowRight;
		shadowTexture = AssetLoader.ballTexture;
		homeTexture = AssetLoader.homeBall;
		currentStatus = 0;
		ready = false;
		
		Random r = new Random();
		ArrayList<Integer> stack1 = new ArrayList<Integer>();
		stack2 = new ArrayList<Integer>();
		
		for(int i = 0; i < 4; ++i){
			stack1.add(new Integer(i));
		}
		
		for(int i = 0; i < 4; ++i){
			int getter = r.nextInt(stack1.size());
			stack2.add(new Integer(stack1.get(getter)));
			stack1.remove(getter);
		}
		currentOrder = stack2.get(currentStatus);
	}
	
	public void update() {
		if(currentStatus != 3) {
			currentStatus++;
			currentOrder = stack2.get(currentStatus);
		}else {
			currentStatus++;
			ready = true;
		}
	}
	
	
	public void draw(Batch batcher) {
		if(currentStatus != 4) {
			switch (currentOrder) {
			case 0:
				batcher.draw(arrowUp, bounds.x - bounds.radius, bounds.y - bounds.radius, bounds.radius * 2f,  bounds.radius * 2f);
				break;
			case 1:
				batcher.draw(arrowLeft, bounds.x - bounds.radius, bounds.y - bounds.radius, bounds.radius * 2f,  bounds.radius * 2f);
				break;
			case 2:
				batcher.draw(arrowDown, bounds.x - bounds.radius, bounds.y - bounds.radius, bounds.radius * 2f,  bounds.radius * 2f);
				break;
			case 3:
				batcher.draw(arrowRight, bounds.x - bounds.radius, bounds.y - bounds.radius, bounds.radius * 2f,  bounds.radius * 2f);
				break;
			}
		}else {
			batcher.draw(homeTexture, bounds.x - bounds.radius, bounds.y - bounds.radius, bounds.radius * 2f,  bounds.radius * 2f);
		}
	}
	
	public void drawShadow(Batch shadowBatcher) {
		shadowBatcher.draw(shadowTexture, bounds.x - bounds.radius + shadowIndent, bounds.y - bounds.radius + shadowIndent, bounds.radius * 2f,  bounds.radius * 2f);
	}
	
	public boolean isReady() {
		return ready;
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
	
	public boolean isClicked(int screenX, int screenY) {
		return bounds.contains(screenX, screenY);
	}

}
