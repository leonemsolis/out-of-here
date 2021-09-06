package com.doublew.outofhere.GameObjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.doublew.outofhere.Buttons.Button;
import com.doublew.outofhere.Buttons.SoundButton;
import com.doublew.outofhere.Support.AssetLoader;

public class TopPanel {
	
	private int score;
	private float shadowIndent, width, height, x, y;
	private SoundButton soundButton;
	private Button rightButton;
	private TextureRegion panelTexture, shadowPanelTexture;
	
	public TopPanel(Button button, float shadowIndent, int score) {
		this.rightButton = button;
		this.shadowIndent = shadowIndent;
		this.score = score;
		x = 0; y = 0; width = 136; height = 24;
		
		soundButton = new SoundButton(0, 0, 24, 24);
		panelTexture = AssetLoader.topPanel;
		shadowPanelTexture = AssetLoader.shadowTopPanel;
	}
	
	public void draw(SpriteBatch batcher) {
		batcher.draw(panelTexture, x, y, width, height);
		soundButton.draw(batcher);
        AssetLoader.scoreFont.draw(batcher, score+"", (136 / 2f) - (AssetLoader.scoreFont.getBounds(score+"").width / 2f), (24 / 2f) + (AssetLoader.scoreFont.getBounds(score+"").height) / 2f);
        rightButton.draw(batcher);
	}
	
	public void drawShadow(SpriteBatch shadowBatcher) {
		shadowBatcher.draw(shadowPanelTexture, x + shadowIndent, y + shadowIndent, width, height);
	}
	
	public SoundButton getSoundButton() {
		return soundButton;
	}

	public Button getRightButton() {
		return rightButton;
	}
	
	public void addScore(int value) {
		if(score != 99999999) {
			score += value;
		}
	}
	
	public float getHeight() {
		return height;
	}
	
	public int getScore() {
		return score;
	}

}
