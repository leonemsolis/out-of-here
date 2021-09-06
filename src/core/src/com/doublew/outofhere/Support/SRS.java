package com.doublew.outofhere.Support;

import com.badlogic.gdx.Gdx;

//SRS - Screen resolution support
public class SRS {
	public static float screenWidth, screenHeight, screenScaleX, 
	screenScaleY, gameWidth, gameHeight, centerX, centerY;
	
	public static void load() {
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		gameWidth = (float) 136;
		gameHeight = (float) screenHeight / (screenWidth / gameWidth);
		screenScaleX = screenWidth / gameWidth;
		screenScaleY = screenHeight / gameHeight;
		centerX = gameWidth / 2f;
		centerY = gameHeight / 2f;
		
	}
}
