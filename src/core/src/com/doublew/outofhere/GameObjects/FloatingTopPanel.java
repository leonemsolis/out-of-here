package com.doublew.outofhere.GameObjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.doublew.outofhere.Support.AssetLoader;

public class FloatingTopPanel {
	
	private boolean floating;
	private float FTPX, FTPY, FTPFinishY, FTPStep, width, height, gameWidth;
	private TextureRegion panelTexture;
	
	public FloatingTopPanel(float FTPStep, float gameWidth) {
		FTPX = 24f;
		FTPY = -24f;
		FTPFinishY = 0f;
		width = 112f;
		height = 24f;
		floating = false;
		this.FTPStep = FTPStep;	
		this.gameWidth = gameWidth;
		
		panelTexture = AssetLoader.floatingTopPanel;
	}
	
	public void update(float delta) {
		if(floating){
			if(FTPY < FTPFinishY){
				if(FTPY + (FTPStep * delta) >= FTPFinishY) {
					FTPY = FTPFinishY;
				}else {
					FTPY += (FTPStep * delta);
				}
			}
		}
	}
	
	public void draw(SpriteBatch batcher) {
        batcher.draw(panelTexture, FTPX, FTPY, width, height);
        AssetLoader.scoreFont.draw(batcher, "0", ((gameWidth / 2f) - (AssetLoader.scoreFont.getBounds("0").width / 2f)), FTPY + ((height / 2f) + (AssetLoader.scoreFont.getBounds("0").height) / 2f));
	}
	
	public void startFloating() {
		floating = true;
	}
	
}
