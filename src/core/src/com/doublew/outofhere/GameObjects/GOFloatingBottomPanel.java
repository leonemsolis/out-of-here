package com.doublew.outofhere.GameObjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.doublew.outofhere.Support.AssetLoader;

public class GOFloatingBottomPanel {

	private TextureRegion panelTexture, edgeLine;
	private boolean floating;
	private float FTPX, FTPY, FTPFinishY, FTPStep, width, height, lineSize;
	
	public GOFloatingBottomPanel(float FTPStep, float gameWidth, float gameHeight, float lineSize) {
		this.width = gameWidth - 44f;
		this.height = 44f;
		panelTexture = AssetLoader.GOFBP;
		edgeLine = AssetLoader.GOFPE;
		FTPX = 22f;
		FTPY = gameHeight;
		FTPFinishY = gameHeight - height;
		this.FTPStep = FTPStep;		
		floating = false;
		this.lineSize = lineSize;
	}
	
	public void update(float delta) {
		if(floating){
			if(FTPY > FTPFinishY){
				if(FTPY - (FTPStep * delta) <= FTPFinishY) {
					FTPY = FTPFinishY;
				}else {
					FTPY -= (FTPStep * delta);
				}
			}
		}
	}
	
	public void draw(SpriteBatch batcher) {
        batcher.draw(panelTexture, FTPX, FTPY, width, height);
        batcher.draw(edgeLine, FTPX + width - lineSize, FTPY, lineSize, height);
        batcher.draw(edgeLine, FTPX, FTPY, lineSize, height);
        batcher.draw(edgeLine, FTPX, FTPY, width, lineSize);
	}
	
	public void startFloating() {
		floating = true;
	}
}
