package com.doublew.outofhere.GameObjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.doublew.outofhere.Support.AssetLoader;

public class Wall {
	private float x1, y1, x2, y2, width, height, stepX, stepY, shadowIndent, closingStepX, closingStepY;
	//for game over checking
	private Rectangle bounds;
	private float finalWidth;
	private float finalHeight;
	// Destination
	private float x12, y12, x22, y22;
	private boolean moving, movingX1, movingY1, movingX2, movingY2;
	private boolean closing, closingX, closingY;
	private final boolean horizontal;
	
	private TextureRegion wallTexture, wallShadow;

	public Wall(float x1, float y1, float x2, float y2, float stepX,
			float stepY, float shadowIndent, float width, float height,
			float finalWidth, float finalHeight, float closingStepX, float closingStepY) {
		wallTexture = AssetLoader.wall;
		wallShadow = AssetLoader.shadowWall;
		
		this.width = width;
		this.height = height;

		//this.width = finalWidth; //fix
		//this.height = finalHeight; //fix

		this.finalWidth = finalWidth;
		this.finalHeight = finalHeight;
		this.shadowIndent = shadowIndent;
		this.stepX = stepX;
		this.stepY = stepY;
		this.closingStepX = closingStepX;
		this.closingStepY = closingStepY;

		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		
		bounds = new Rectangle(x1, y1, width, height);

		moving = false;
		movingX1 = false;
		movingY1 = false;
		movingX2 = false;
		movingY2 = false;

		this.horizontal = width != finalWidth;
		
		closing = false;
		if(horizontal) {
			closingX = false;
			closingY = false;
		}else {
			closingX = false;
			closingY = false;
		}
	}
	
	public void updateClosing(float delta) {
		if(closing){
			if(horizontal) {
				if(width < finalWidth) {
					if(width + (closingStepX * delta) >= finalWidth) {
						//x2 -= (finalWidth - width);
						width = finalWidth;
						closingX = false;
					}else {
						width += (closingStepX * delta);
						//x2 -= (closingStepX * delta);
					}
				}
			}else {
				if(height < finalHeight) {
					if(height + (closingStepY * delta) >= finalHeight) {
						//y2 -= (finalHeight - height);
						height = finalHeight;
						closingY = false;
					}else {
						height += (closingStepY * delta);
						//y2 -= (closingStepY * delta);
					}
				}
			}
		}
		if(!closingX && !closingY) {
			if(closing == true) AssetLoader.playSound(5);
			closing = false;
		}
		updateBounds();
	}

	public void updateMoving(float delta) {
		if (moving) {
				// FIRST HORIZONTAL HALF
				//moving to the right
				if (x1 < x12) {
					if (x1 + (stepX * delta) >= x12) {
						x1 = x12;
						movingX1 = false;
					} else {
						x1 += stepX * delta;
					}
				} else { // moving to the left
					if (x1 - (stepX * delta) <= x12) {
						x1 = x12;
						movingX1 = false;
					} else {
						x1 -= stepX * delta;
					}
				}
				//SECOND HORIZONTAL HALF
				//moving to the right
				if (x2 < x22) {
					if (x2 + (stepX * delta) >= x22) {
						x2 = x22;
						movingX2 = false;
					} else {
						x2 += stepX * delta;
					}
				} else { // moving to the left
					if (x2 - (stepX * delta) <= x22) {
						x2 = x22;
						movingX2 = false;
					} else {
						x2 -= stepX * delta;
					}
				}
				// FIRST VERTICAL HALF
				//moving down
				if (y1 < y12) {
					if (y1 + (stepY * delta) >= y12) {
						y1 = y12;
						movingY1 = false;
					} else {
						y1 += stepY * delta;
					}
				} else { // moving up
					if (y1 - (stepY * delta) <= y12) {
						y1 = y12;
						movingY1 = false;
					} else {
						y1 -= stepY * delta;
					}
				}
				//SECOND VERTICAL HALF
				//moving down
				if (y2 < y22) {
					if (y2 + (stepY * delta) >= y22) {
						y2 = y22;
						movingY2 = false;
					} else {
						y2 += stepY * delta;
					}
				} else { // moving up
					if (y2 - (stepY * delta) <= y22) {
						y2 = y22;
						movingY2 = false;
					} else {
						y2 -= stepY * delta;
					}
				}
		}
		if(!movingX1 && !movingX2 && !movingY1 && !movingY2) {
			moving = false;
		}
		updateBounds();
	}

	public void startMoving() {
		moving = true;
	
		movingX1 = true;
		movingX2 = true;

		movingY1 = true;
		movingY2 = true;
	}

	public boolean isMoving() {
		return moving;
	}
	
	public void close() {
		closing = true;
		
		if(horizontal) {
			closingX = true;
		}else {
			closingY = true;
		}
	}
	
	public void open() {
		if(horizontal) {
			width = 0;
		}else {
			height = 0;
		}
	}
	
	public boolean isClosing() {
		return closing;
	}
	
	

	public void draw(SpriteBatch batcher) {
		batcher.draw(wallTexture, x1, y1, width, height);

		if (horizontal) {
			if(width != 0) { //bug fix with horizontal wall texture
				batcher.draw(wallTexture, x2 - width, y2, width + 1, height);
			}else {
				batcher.draw(wallTexture, x2 - width, y2, width, height);
			}
		} else {
			batcher.draw(wallTexture, x2, y2 - height, width, height);
		}
	}

	public void drawShadow(SpriteBatch shadowBatcher) {
		shadowBatcher.draw(wallShadow, x1 + shadowIndent, y1 + shadowIndent, width, height);
		if(horizontal) {
			shadowBatcher.draw(wallShadow, x2 - width + shadowIndent, y2 + shadowIndent, width , height);
		}else {
			shadowBatcher.draw(wallShadow, x2 + shadowIndent, y2 - height + shadowIndent, width , height);
		}
	}
	
	public void setStartX(float x1, float x2) {
		this.x1 = x1;
		this.x2 = x2;
		//if(horizontal) this.x2 -= width; //fix
	}

	public void setStartY(float y1, float y2) {
		this.y1 = y1;
		this.y2 = y2;
		//if(!horizontal) this.y2 -= height; //fix
	}

	public void setFinishX(float x12, float x22) {
		this.x12 = x12;
		this.x22 = x22;
		//if(horizontal) this.x22 -= width; //fix
	}

	public void setFinishY(float y12, float y22) {
		this.y12 = y12;
		this.y22 = y22;
		//if(!horizontal) this.y22 -= height; //fix
	}
	
	public void updateBounds() {
		if(horizontal) {
			bounds.width = width;
		}else {
			bounds.height = height;
		}
		bounds.x = x1;
		bounds.y = y1;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public void addX(float value) {
		x1 += value;
		x2 += value;
	}
	
	public void addY(float value) {
		y1 += value;
		y2 += value;
	}
}
