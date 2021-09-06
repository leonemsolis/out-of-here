package com.doublew.outofhere.GameObjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.doublew.outofhere.Support.AssetLoader;

public class Corner {
	
	private float x1, x2, y1, y2, width, height, shadowIndent, stepX, stepY;
	//Moving X and Y to understand, when corner stops with x and y
	private boolean moving, movingX, movingY;
	private TextureRegion cornerTexture, shadowTexture;
	
	public Corner(int number, float x1, float x2, float y1, float y2, float shadowIndent, float stepX, float stepY) {
		moving = false;
		movingX = false;
		movingY = false;
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.width = 44;
		this.height = 44;
		this.shadowIndent = shadowIndent;
		//step - 'game pixels' per second
		this.stepX = stepX;
		this.stepY = stepY;
		
		switch (number) {
			case 1: cornerTexture = AssetLoader.cornerPanel1; shadowTexture = AssetLoader.shadowCornerPanel1; break;
			case 2: cornerTexture = AssetLoader.cornerPanel2; shadowTexture = AssetLoader.shadowCornerPanel2; break;
			case 3: cornerTexture = AssetLoader.cornerPanel3; shadowTexture = AssetLoader.shadowCornerPanel3; break;
			case 4: cornerTexture = AssetLoader.cornerPanel4; shadowTexture = AssetLoader.shadowCornerPanel4; break;
		}
	}
	
	public void move() {
		moving = true;
		movingX = true;
		movingY = true;
	}
	
	public void update(float delta) {
		if(moving) {
			if(x1 < x2) {
				if(x1 + (stepX * delta) >= x2) { // if object is a bit over finish
					x1 = x2;
					movingX = false;
				}else{
					if(x1<=x2) {
						x1+= (stepX * delta);
					}
				}
			}else{
				if(x1 - (stepX * delta) <= x2) {
					x1 = x2;
					movingX = false;
				}else{
					if(x1>=x2) {
						x1-= (stepX * delta);
					}
				}
			}
			
			if(y1 < y2) {
				if(y1 + (stepY * delta) >= y2) {
					y1 = y2;
					movingY = false;
				}else{
					if(y1<=y2) {
						y1+= (stepY * delta);
					}
				}
			}else{
				if(y1 - (stepY * delta) <= y2){
					y1 = y2;
					movingY = false;
				}else{
					if(y1>=y2) {
						y1-= (stepY * delta);
					}
				}
			}
		}
		
		if(!movingX && !movingY){
			moving = false;
		}
	}
	
	public void draw(SpriteBatch batcher) {
		batcher.draw(cornerTexture, x1, y1, width, height);
	}
	
	public void drawShadow(SpriteBatch shadowBatcher) {
		shadowBatcher.draw(shadowTexture, x1 + shadowIndent, y1 + shadowIndent, width, height);
	}
	
	public boolean isMoving() {
		return moving;
	}
	
	public void setFinishX(float value) {
		x2 = value;
	}
	public void setFinishY(float value) {
		y2 = value;
	}
	public void setStartX(float value) {
		x1 = value;
	}
	public void setStartY(float value) {
		y1 = value;
	}
	
	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}
	
	public float getX() {
		return x1;
	}
	
	public float getY() {
		return y1;
	}
	
	//for shaking
	public void addX(float value) {
		x1 += value;
	}
	
	public void addY(float value) {
		y1 += value;
	}
}
