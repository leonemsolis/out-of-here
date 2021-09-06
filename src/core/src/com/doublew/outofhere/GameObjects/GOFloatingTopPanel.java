package com.doublew.outofhere.GameObjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.doublew.outofhere.Buttons.Button;
import com.doublew.outofhere.Support.AssetLoader;

public class GOFloatingTopPanel {

	private TextureRegion panelTexture, edgeLine;
	private boolean floating;
	private float FTPX, FTPY, FTPFinishY, FTPStep, width, height, gameWidth, lineSize;
	private int score, best;
	private float passed, period, comparator;
	private boolean bestScoreVissible;
	private Button rateButton;
	
	public GOFloatingTopPanel(float FTPStep, float gameWidth, float gameHeight, float lineSize) {
		this.width = gameWidth - 44f;
		this.height = 68f;
		score = 0;
		best = 0;
		
		panelTexture = AssetLoader.GOFTP;
		edgeLine = AssetLoader.GOFPE;
		FTPX = 22f;
		FTPY = -height;
		FTPFinishY = 0;
		this.FTPStep = FTPStep;		
		floating = false;
		this.gameWidth = gameWidth;
		this.lineSize = lineSize;
		period = 0.9f; // for NEW BEST SCORE flickering
		passed = 0;
		comparator = 0;
		bestScoreVissible = false;
		rateButton = new Button(136 - 24, FTPY, 24, 24, AssetLoader.rateButtonUp, AssetLoader.rateButtonDown);
	}
	
	public void update(float delta) {
		if(floating){
			if(FTPY < FTPFinishY){
				if(FTPY + (FTPStep * delta) >= FTPFinishY) {
					FTPY = FTPFinishY;
					rateButton.setY(FTPY);
					floating = false;
				}else {
					FTPY += (FTPStep * delta);
					rateButton.setY(FTPY);
				}
			}
		}
	}
	
	public void draw(SpriteBatch batcher, float delta) {
        batcher.draw(panelTexture, FTPX, FTPY, width, height);
        batcher.draw(edgeLine, FTPX + width - lineSize, FTPY, lineSize, height);
        batcher.draw(edgeLine, FTPX, FTPY, lineSize, height);
        batcher.draw(edgeLine, FTPX, FTPY + height - lineSize, width, lineSize);
        rateButton.draw(batcher);
        
        if(score <= best) {
	        AssetLoader.scoreFont.draw(batcher, "Score:", ((gameWidth / 2f) - (AssetLoader.scoreFont.getBounds("Score:").width / 2f)), FTPY + (((height / 5f) * 1) + (AssetLoader.scoreFont.getBounds("0").height) / 2f));
	        AssetLoader.scoreFont.draw(batcher, score+"", ((gameWidth / 2f) - (AssetLoader.scoreFont.getBounds(score+"").width / 2f)), FTPY + (((height / 5f) * 2) + (AssetLoader.scoreFont.getBounds("0").height) / 2f));
	        AssetLoader.scoreFont.draw(batcher, "Best:", ((gameWidth / 2f) - (AssetLoader.scoreFont.getBounds("Best:").width / 2f)), FTPY + (((height / 5f) * 3) + (AssetLoader.scoreFont.getBounds("0").height) / 2f));
	        AssetLoader.scoreFont.draw(batcher, best+"", ((gameWidth / 2f) - (AssetLoader.scoreFont.getBounds(best+"").width / 2f)), FTPY + (((height / 5f) * 4) + (AssetLoader.scoreFont.getBounds("0").height) / 2f));
        }else {
        		if(comparator <= passed){
        			comparator += period;
        			bestScoreVissible = !bestScoreVissible;
        		}
        		passed += delta;
        		
        		if(bestScoreVissible){
        			AssetLoader.scoreFont.draw(batcher, "NEW BEST:", ((gameWidth / 2f) - (AssetLoader.scoreFont.getBounds("NEW BEST:").width / 2f)), FTPY + (((height / 3f) * 1) + (AssetLoader.scoreFont.getBounds("0").height) / 2f));
        	        AssetLoader.scoreFont.draw(batcher, score+"", ((gameWidth / 2f) - (AssetLoader.scoreFont.getBounds(score+"").width / 2f)), FTPY + (((height / 3f) * 2) + (AssetLoader.scoreFont.getBounds("0").height) / 2f));
        		}
            //AssetLoader.font.draw(batcher, "Best:", ((gameWidth / 2f) - (AssetLoader.font.getBounds("Best:").width / 2f)), FTPY + (((height / 5f) * 3) + (AssetLoader.font.getBounds("0").height) / 2f));
	        //AssetLoader.font.draw(batcher, best+"", ((gameWidth / 2f) - (AssetLoader.font.getBounds(best+"").width / 2f)), FTPY + (((height / 5f) * 4) + (AssetLoader.font.getBounds("0").height) / 2f));	
        }
	}
	
	public void startFloating(int score, int best) {
		this.score = score;
		this.best = best;
		floating = true;
	}
	
	public Button getRateButton() {
		return rateButton;
	}
}
