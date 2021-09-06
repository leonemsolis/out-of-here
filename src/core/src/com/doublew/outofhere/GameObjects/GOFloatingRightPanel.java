package com.doublew.outofhere.GameObjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.doublew.outofhere.Buttons.Button;
import com.doublew.outofhere.Support.AssetLoader;

public class GOFloatingRightPanel {

	private TextureRegion panelTexture, edgeLine;
	private boolean floating;
	private float FTPX, FTPY, FTPFinishX, FTPStep, width, height, lineSize;
	private Button restartButton;
	
	public GOFloatingRightPanel(float FTPStep, float gameWidth, float gameHeight, float lineSize) {
		this.width = gameWidth / 2;
		this.height = gameHeight - 88f - 24f;
		panelTexture = AssetLoader.GOFLP;
		edgeLine = AssetLoader.GOFPE;
		FTPX = gameWidth;
		FTPY = 44 + 24f;
		FTPFinishX = gameWidth / 2f;
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
		
		restartButton = new Button(buttonX, buttonY, buttonWidth, buttonHeight, AssetLoader.restartButtonUp, AssetLoader.restartButtonDown);
	}
	
	public void update(float delta) {
		if(floating){
			if(FTPX > FTPFinishX){
				if(FTPX - (FTPStep * delta) <= FTPFinishX) {
					FTPX = FTPFinishX;
				}else {
					FTPX -= (FTPStep * delta);
				}
			}
		}
		restartButton.setX(FTPX + ((width / 2f) - (restartButton.getWidth() / 2f)));
	}
	
	public void draw(SpriteBatch batcher) {
        batcher.draw(panelTexture, FTPX, FTPY, width, height);
        batcher.draw(edgeLine, FTPX, FTPY, width, lineSize);
        batcher.draw(edgeLine, FTPX, FTPY, lineSize, height);
        batcher.draw(edgeLine, FTPX, FTPY + height - lineSize, width, lineSize);
        restartButton.draw(batcher);
	}
	
	public void startFloating() {
		floating = true;
	}
	
	public Button getRestartButton() {
		return restartButton;
	}
}