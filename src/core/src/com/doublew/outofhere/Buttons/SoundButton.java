package com.doublew.outofhere.Buttons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.doublew.outofhere.Support.AssetLoader;
	
public class SoundButton {
	private Rectangle bounds;
	private boolean isPressed;
	private enum state{first, second, third, fourth};
	private state current;
	
	private TextureRegion currentUp, currentDown, up1, down1, up2, down2, up3, down3, up4, down4;
	
	public SoundButton(float x, float y, float width, float height) {
		bounds = new Rectangle(x, y, width, height);
		
		isPressed = false;
		
		up1 = AssetLoader.soundButtonUp;
		down1 = AssetLoader.soundButtonDown;
		
		up2 = AssetLoader.soundButtonSoundUp;
		down2 = AssetLoader.soundButtonSoundDown;
		
		up3 = AssetLoader.soundButtonMusicUp;
		down3 = AssetLoader.soundButtonMusicDown;
		
		up4 = AssetLoader.soundButtonOffUp;
		down4 = AssetLoader.soundButtonOffDown;
		
		switch(AssetLoader.getSoundSettings()) {
			case 1: current = state.first; currentUp = up1; currentDown = down1; break;
			case 2: current = state.second; currentUp = up2; currentDown = down2;  break;
			case 3: current = state.third; currentUp = up3; currentDown = down3;  break;
			case 4: current = state.fourth; currentUp = up4; currentDown = down4;  break;
			default: current = state.first; currentUp = up1; currentDown = down1;  break;
		}
		
		AssetLoader.setMusic();
		
		
	}
	
	public boolean isClicked(int screenX, int screenY) {
		return bounds.contains(screenX, screenY);
	}
	
	public void draw(SpriteBatch batcher) {
		if (isPressed) {
			batcher.draw(currentDown, bounds.x, bounds.y, bounds.width, bounds.height);
		} else {
			batcher.draw(currentUp, bounds.x, bounds.y, bounds.width, bounds.height);
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
			
			switch(current){
				case first: currentUp = up2; currentDown = down2; current = state.second; AssetLoader.setSoundSettings(2); break;
				case second: currentUp = up3; currentDown = down3; current = state.third; AssetLoader.setSoundSettings(3); break;
				case third: currentUp = up4; currentDown = down4; current = state.fourth; AssetLoader.setSoundSettings(4); break;
				case fourth: currentUp = up1; currentDown = down1; current = state.first; AssetLoader.setSoundSettings(1); break;
			}
			
			AssetLoader.setMusic();
			
			return true;
		
		}
		
		// Whenever a finger is released, we will cancel any presses.
		isPressed = false;
		//Gdx.app.log("Button", "Up!");
		return false;
	}
	
	public int getCurrentState() {
		switch(current) {
			case first: return 0;
			case second: return 1;
			case third: return 2;
			case fourth: return 3;
			default: return 0;
		}
	}
}
