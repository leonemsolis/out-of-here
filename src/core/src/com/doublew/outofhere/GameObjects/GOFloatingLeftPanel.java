package com.doublew.outofhere.GameObjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.doublew.outofhere.Buttons.Button;
import com.doublew.outofhere.Support.AssetLoader;

public class GOFloatingLeftPanel {

	private TextureRegion panelTexture, edgeLine;
	private boolean floating;
	private float FTPX, FTPY, FTPFinishX, FTPStep, width, height, lineSize;
	private Button homeButton;
	
	public GOFloatingLeftPanel(float FTPStep, float gameWidth, float gameHeight, float lineSize) {
		this.width = gameWidth / 2;
		this.height = gameHeight - 88f - 24f;
		panelTexture = AssetLoader.GOFLP;
		edgeLine = AssetLoader.GOFPE;
		FTPX = -width;
		FTPY = 44 + 24f;
		FTPFinishX = 0;
		this.FTPStep = FTPStep;		
		floating = false;
		this.lineSize = lineSize;
		
		float buttonWidth = 0;
		float buttonHeight = 0;
		
		if(width <= height){
			buttonWidth = width/2f;
			buttonHeight = buttonWidth;
		}else {
			buttonHeight = height/2f;
			buttonWidth = buttonHeight;
		}
		
		float buttonX = FTPX + ((width / 2f) - (buttonWidth / 2f));
		float buttonY = FTPY + ((height / 2f) - (buttonHeight / 2f));
		
		
		homeButton = new Button(buttonX, buttonY, buttonWidth, buttonHeight, AssetLoader.homeButtonUp, AssetLoader.homeButtonDown);
	}
	
	public void update(float delta) {
		if(floating){
			if(FTPX < FTPFinishX){
				if(FTPX + (FTPStep * delta) >= FTPFinishX) {
					FTPX = FTPFinishX;
				}else {
					FTPX += (FTPStep * delta);
				}
			}
		}
		homeButton.setX(FTPX + ((width / 2f) - (homeButton.getWidth() / 2f)));
	}
	
	public void draw(SpriteBatch batcher) {
        batcher.draw(panelTexture, FTPX, FTPY, width, height);
        batcher.draw(edgeLine, FTPX, FTPY, width, lineSize);
        batcher.draw(edgeLine, FTPX + width - lineSize, FTPY, lineSize, height);
        batcher.draw(edgeLine, FTPX, FTPY + height - lineSize, width, lineSize);
        homeButton.draw(batcher);
	}
	
	public void startFloating() {
		floating = true;
	}
	
	public Button getHomeButton() {
		return homeButton;
	}
}