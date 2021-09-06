package com.doublew.outofhere.GameObjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EnvironmentManager {
	// coordinates for corners
	// first word - location of corner, x1 - left top, x2 - right top, x3 -
	// right bottom, x4 - left bottom
	private final float centerX1, centerY1, centerX2, centerY2, centerX3,
			centerY3, centerX4, centerY4;
	private final float topX1, topY1, topX2, topY2, topX3, topY3, topX4, topY4;
	private final float leftX1, leftY1, leftX2, leftY2, leftX3, leftY3, leftX4,
			leftY4;
	private final float bottomX1, bottomY1, bottomX2, bottomY2, bottomX3,
			bottomY3, bottomX4, bottomY4;
	private final float rightX1, rightY1, rightX2, rightY2, rightX3, rightY3,
			rightX4, rightY4;

	// coordinates for walls
	// first word - location, second - number of wall, x1 or x2 - first half of
	// wall or second
	private final float centralFirstX1, centralFirstY1, centralFirstX2,
			centralFirstY2, centralSecondX1, centralSecondY1, centralSecondX2,
			centralSecondY2, centralThirdX1, centralThirdY1, centralThirdX2,
			centralThirdY2, centralFourthX1, centralFourthY1, centralFourthX2,
			centralFourthY2;
	private final float topFirstX1, topFirstY1, topFirstX2, topFirstY2,
			topSecondX1, topSecondY1, topSecondX2, topSecondY2, topThirdX1,
			topThirdY1, topThirdX2, topThirdY2, topFourthX1, topFourthY1,
			topFourthX2, topFourthY2;
	private final float leftFirstX1, leftFirstY1, leftFirstX2, leftFirstY2,
			leftSecondX1, leftSecondY1, leftSecondX2, leftSecondY2,
			leftThirdX1, leftThirdY1, leftThirdX2, leftThirdY2, leftFourthX1,
			leftFourthY1, leftFourthX2, leftFourthY2;
	private final float bottomFirstX1, bottomFirstY1, bottomFirstX2,
			bottomFirstY2, bottomSecondX1, bottomSecondY1, bottomSecondX2,
			bottomSecondY2, bottomThirdX1, bottomThirdY1, bottomThirdX2,
			bottomThirdY2, bottomFourthX1, bottomFourthY1, bottomFourthX2,
			bottomFourthY2;
	private final float rightFirstX1, rightFirstY1, rightFirstX2, rightFirstY2,
			rightSecondX1, rightSecondY1, rightSecondX2, rightSecondY2,
			rightThirdX1, rightThirdY1, rightThirdX2, rightThirdY2,
			rightFourthX1, rightFourthY1, rightFourthX2, rightFourthY2;

	private boolean firstOne;
	private boolean horizontal;
	
	private Corner first1c, second1c, third1c, fourth1c, first2c, second2c,
	third2c, fourth2c;
	private Wall first1w, first2w, second1w, second2w, third1w, third2w,
	fourth1w, fourth2w;

	public EnvironmentManager(float gameWidth, float gameHeight,
			float shadowIndent, float cornerWidth, float cornerHeight,
			float topPanelHeight, float cornerStepX, float cornerStepY,
			float wallStepX, float wallStepY, float closingStepX,
			float closingStepY) {

		firstOne = true;
		horizontal = true;

		centerX1 = 0;
		centerY1 = topPanelHeight;

		centerX2 = gameWidth - cornerWidth;
		centerY2 = centerY1;

		centerX3 = centerX2;
		centerY3 = gameHeight - cornerHeight;

		centerX4 = centerX1;
		centerY4 = centerY3;

		topX1 = 0;
		topY1 = (topPanelHeight + (cornerHeight / 2f))
				- ((gameHeight - topPanelHeight));

		topX2 = gameWidth - cornerWidth;
		topY2 = topY1;

		topX3 = topX2;
		topY3 = topPanelHeight - (cornerHeight / 2f);

		topX4 = topX1;
		topY4 = topY3;

		leftX1 = (cornerWidth / 2f) - gameWidth;
		leftY1 = topPanelHeight;

		leftX2 = -(cornerWidth / 2f);
		leftY2 = leftY1;

		leftX3 = leftX2;
		leftY3 = gameHeight - cornerHeight;

		leftX4 = leftX1;
		leftY4 = leftY3;

		bottomX1 = 0;
		bottomY1 = gameHeight - (cornerHeight / 2f);

		bottomX2 = gameWidth - cornerWidth;
		bottomY2 = bottomY1;

		bottomX3 = bottomX2;
		bottomY3 = (gameHeight - (cornerHeight / 2f))
				+ (gameHeight - topPanelHeight - cornerHeight); // gameHeight -
																// topPanelHeight
																// + (gameHeight
																// -
																// topPanelHeight
																// -
																// cornerHeight);
																// //(gameHeight
																// -
																// (cornerHeight
																// / 2f)) +
																// (gameHeight -
																// topPanelHeight);

		bottomX4 = bottomX1;
		bottomY4 = bottomY3;

		rightX1 = gameWidth - (cornerWidth / 2f);
		rightY1 = topPanelHeight;

		rightX2 = rightX1 + (gameWidth - cornerWidth);
		rightY2 = rightY1;

		rightX3 = rightX2;
		rightY3 = gameHeight - cornerHeight;

		rightX4 = rightX1;
		rightY4 = rightY3;

		first1c = new Corner(1, centerX1, centerX1, centerY1, centerY1,
				shadowIndent, cornerStepX, cornerStepY);
		second1c = new Corner(2, centerX2, centerX2, centerY2, centerY2,
				shadowIndent, cornerStepX, cornerStepY);
		third1c = new Corner(3, centerX3, centerX3, centerY3, centerY3,
				shadowIndent, cornerStepX, cornerStepY);
		fourth1c = new Corner(4, centerX4, centerX4, centerY4, centerY4,
				shadowIndent, cornerStepX, cornerStepY);

		first2c = new Corner(1, topX1, topX1, topY1, topY1, shadowIndent,
				cornerStepX, cornerStepY);
		second2c = new Corner(2, topX2, topX2, topY2, topY2, shadowIndent,
				cornerStepX, cornerStepY);
		third2c = new Corner(3, topX3, topX3, topY3, topY3, shadowIndent,
				cornerStepX, cornerStepY);
		fourth2c = new Corner(4, topX4, topX4, topY4, topY4, shadowIndent,
				cornerStepX, cornerStepY);

		centralFirstX1 = centerX1 + cornerWidth;//
		centralFirstY1 = centerY1;//
		centralFirstX2 = centerX2;//
		centralFirstY2 = centerY2;//

		centralSecondX1 = centerX2 + (cornerWidth / 2f);//
		centralSecondY1 = centerY2 + cornerHeight;//
		centralSecondX2 = centralSecondX1;//
		centralSecondY2 = centerY3;//

		centralThirdX1 = centerX4 + cornerWidth;//
		centralThirdY1 = centerY4 + (cornerHeight / 2f);//
		centralThirdX2 = centerX3;//
		centralThirdY2 = centerY3 + (cornerWidth / 2f);//

		centralFourthX1 = centerX1;//
		centralFourthY1 = centerY1 + cornerHeight;//
		centralFourthX2 = centerX4;//
		centralFourthY2 = centerY4;//

		//

		leftFirstX1 = leftX1 + cornerWidth;//
		leftFirstY1 = leftY1;//
		leftFirstX2 = leftX2;//
		leftFirstY2 = leftY2;//

		leftSecondX1 = leftX2 + (cornerWidth / 2f);//
		leftSecondY1 = leftY2 + cornerHeight;//
		leftSecondX2 = leftSecondX1;//
		leftSecondY2 = leftY3;//

		leftThirdX1 = leftX4 + cornerWidth;
		leftThirdY1 = leftY4 + (cornerHeight / 2f);
		leftThirdX2 = leftX3;
		leftThirdY2 = leftY3 + (cornerWidth / 2f);

		leftFourthX1 = leftX1;
		leftFourthY1 = leftY1 + cornerHeight;
		leftFourthX2 = leftX4;
		leftFourthY2 = leftY4;

		//

		bottomFirstX1 = bottomX1 + cornerWidth;
		bottomFirstY1 = bottomY1;
		bottomFirstX2 = bottomX2;
		bottomFirstY2 = bottomY2;

		bottomSecondX1 = bottomX2 + (cornerWidth / 2f);
		bottomSecondY1 = bottomY2 + cornerHeight;
		bottomSecondX2 = bottomSecondX1;
		bottomSecondY2 = bottomY3;

		bottomThirdX1 = bottomX4 + cornerWidth;
		bottomThirdY1 = bottomY4 + (cornerHeight / 2f);
		bottomThirdX2 = bottomX3;
		bottomThirdY2 = bottomY3 + (cornerWidth / 2f);

		bottomFourthX1 = bottomX1;
		bottomFourthY1 = bottomY1 + cornerHeight;
		bottomFourthX2 = bottomX4;
		bottomFourthY2 = bottomY4;

		//

		rightFirstX1 = rightX1 + cornerWidth;
		rightFirstY1 = rightY1;
		rightFirstX2 = rightX2;
		rightFirstY2 = rightY2;

		rightSecondX1 = rightX2 + (cornerWidth / 2f);
		rightSecondY1 = rightY2 + cornerHeight;
		rightSecondX2 = rightSecondX1;
		rightSecondY2 = rightY3;

		rightThirdX1 = rightX4 + cornerWidth;
		rightThirdY1 = rightY4 + (cornerHeight / 2f);
		rightThirdX2 = rightX3;
		rightThirdY2 = rightY3 + (cornerWidth / 2f);

		rightFourthX1 = rightX1;
		rightFourthY1 = rightY1 + cornerHeight;
		rightFourthX2 = rightX4;
		rightFourthY2 = rightY4;

		//

		topFirstX1 = topX1 + cornerWidth;
		topFirstY1 = topY1;
		topFirstX2 = topX2;
		topFirstY2 = topY2;

		topSecondX1 = topX2 + (cornerWidth / 2f);
		topSecondY1 = topY2 + cornerHeight;
		topSecondX2 = topSecondX1;
		topSecondY2 = topY3;

		topThirdX1 = topX4 + cornerWidth;
		topThirdY1 = topY4 + (cornerHeight / 2f);
		topThirdX2 = topX3;
		topThirdY2 = topY3 + (cornerWidth / 2f);

		topFourthX1 = topX1;
		topFourthY1 = topY1 + cornerHeight;
		topFourthX2 = topX4;
		topFourthY2 = topY4;

		//

		// Wall parameters
		float horizontalWidth = 0;
		float horizontalHeight = cornerHeight / 2f;
		float horizontalFinalWidth = (gameWidth / 2f) - cornerWidth;
		;
		float horizontalFinalHeight = horizontalHeight;

		float verticalWidth = cornerWidth / 2;
		float verticalHeight = 0;
		float verticalFinalWidth = verticalWidth;
		float verticalFinalHeight = ((gameHeight - topPanelHeight) / 2f)
				- cornerHeight;

		first1w = new Wall(centralFirstX1, centralFirstY1, centralFirstX2,
				centralFirstY2, wallStepX, wallStepY, shadowIndent,
				horizontalWidth, horizontalHeight, horizontalFinalWidth,
				horizontalFinalHeight, closingStepX, closingStepY);
		first2w = new Wall(topFirstX1, topFirstY1, topFirstX2, topFirstY2,
				wallStepX, wallStepY, shadowIndent, horizontalWidth,
				horizontalHeight, horizontalFinalWidth, horizontalFinalHeight,
				closingStepX, closingStepY);

		second1w = new Wall(centralSecondX1, centralSecondY1, centralSecondX2,
				centralSecondY2, wallStepX, wallStepY, shadowIndent,
				verticalWidth, verticalHeight, verticalFinalWidth,
				verticalFinalHeight, closingStepX, closingStepY);
		second2w = new Wall(topSecondX1, topSecondY1, topSecondX2, topSecondY2,
				wallStepX, wallStepY, shadowIndent, verticalWidth,
				verticalHeight, verticalFinalWidth, verticalFinalHeight,
				closingStepX, closingStepY);

		third1w = new Wall(centralThirdX1, centralThirdY1, centralThirdX2,
				centralThirdY2, wallStepX, wallStepY, shadowIndent,
				horizontalWidth, horizontalHeight, horizontalFinalWidth,
				horizontalFinalHeight, closingStepX, closingStepY);
		third2w = new Wall(topThirdX1, topThirdY1, topThirdX2, topThirdY2,
				wallStepX, wallStepY, shadowIndent, horizontalWidth,
				horizontalHeight, horizontalFinalWidth, horizontalFinalHeight,
				closingStepX, closingStepY);

		fourth1w = new Wall(centralFourthX1, centralFourthY1, centralFourthX2,
				centralFourthY2, wallStepX, wallStepY, shadowIndent,
				verticalWidth, verticalHeight, verticalFinalWidth,
				verticalFinalHeight, closingStepX, closingStepY);
		fourth2w = new Wall(topFourthX1, topFourthY1, topFourthX2, topFourthY2,
				wallStepX, wallStepY, shadowIndent, verticalWidth,
				verticalHeight, verticalFinalWidth, verticalFinalHeight,
				closingStepX, closingStepY);

	}

	public void draw(SpriteBatch batcher) {
		first1c.draw(batcher);
		second1c.draw(batcher);
		third1c.draw(batcher);
		fourth1c.draw(batcher);

		first2c.draw(batcher);
		second2c.draw(batcher);
		third2c.draw(batcher);
		fourth2c.draw(batcher);

		first1w.draw(batcher);
		second1w.draw(batcher);
		third1w.draw(batcher);
		fourth1w.draw(batcher);

		first2w.draw(batcher);
		second2w.draw(batcher);
		third2w.draw(batcher);
		fourth2w.draw(batcher);

	}

	public void drawShadow(SpriteBatch shadowBatcher) {
		first1c.drawShadow(shadowBatcher);
		second1c.drawShadow(shadowBatcher);
		third1c.drawShadow(shadowBatcher);
		fourth1c.drawShadow(shadowBatcher);

		first2c.drawShadow(shadowBatcher);
		second2c.drawShadow(shadowBatcher);
		third2c.drawShadow(shadowBatcher);
		fourth2c.drawShadow(shadowBatcher);

		first1w.drawShadow(shadowBatcher);
		second1w.drawShadow(shadowBatcher);
		third1w.drawShadow(shadowBatcher);
		fourth1w.drawShadow(shadowBatcher);

		first2w.drawShadow(shadowBatcher);
		second2w.drawShadow(shadowBatcher);
		third2w.drawShadow(shadowBatcher);
		fourth2w.drawShadow(shadowBatcher);

	}

	public void updateCorners(float delta) {
		first1c.update(delta);
		second1c.update(delta);
		third1c.update(delta);
		fourth1c.update(delta);

		first2c.update(delta);
		second2c.update(delta);
		third2c.update(delta);
		fourth2c.update(delta);
	}

	public void reloadOffscreenWalls() {
		if (firstOne) {
			first2w.open();
			second2w.open();
			third2w.open();
			fourth2w.open();
		} else {
			first1w.open();
			second1w.open();
			third1w.open();
			fourth1w.open();
		}
	}

	public void updateClosingWalls(float delta) {
		first1w.updateClosing(delta);
		second1w.updateClosing(delta);
		third1w.updateClosing(delta);
		fourth1w.updateClosing(delta);

		first2w.updateClosing(delta);
		second2w.updateClosing(delta);
		third2w.updateClosing(delta);
		fourth2w.updateClosing(delta);
	}

	public void updateMovingWalls(float delta) {
		first1w.updateMoving(delta);
		second1w.updateMoving(delta);
		third1w.updateMoving(delta);
		fourth1w.updateMoving(delta);

		first2w.updateMoving(delta);
		second2w.updateMoving(delta);
		third2w.updateMoving(delta);
		fourth2w.updateMoving(delta);
	}

	public void switcher(int way, boolean firstWallClosed,
			boolean secondWallClosed, boolean thirdWallClosed,
			boolean fourthWallClosed) {
		if (way == 0 || way == 2) {
			horizontal = false;
		} else {
			horizontal = true;
		}
		setCorner(way);
		setWall(way, firstWallClosed, secondWallClosed, thirdWallClosed,
				fourthWallClosed);
		firstOne = !firstOne;
	}

	public void setWall(int way, boolean firstWallClosed,
			boolean secondWallClosed, boolean thirdWallClosed,
			boolean fourthWallClosed) {
		if (firstOne) {
			switch (way) {
			case 0:
				// first walls
				first1w.setFinishX(bottomFirstX1, bottomFirstX2);
				first1w.setFinishY(bottomFirstY1, bottomFirstY2);
				second1w.setFinishX(bottomSecondX1, bottomSecondX2);
				second1w.setFinishY(bottomSecondY1, bottomSecondY2);
				third1w.setFinishX(bottomThirdX1, bottomThirdX2);
				third1w.setFinishY(bottomThirdY1, bottomThirdY2);
				fourth1w.setFinishX(bottomFourthX1, bottomFourthX2);
				fourth1w.setFinishY(bottomFourthY1, bottomFourthY2);
				// second walls
				first2w.setStartX(topFirstX1, topFirstX2);
				first2w.setStartY(topFirstY1, topFirstY2);
				second2w.setStartX(topSecondX1, topSecondX2);
				second2w.setStartY(topSecondY1, topSecondY2);
				third2w.setStartX(topThirdX1, topThirdX2);
				third2w.setStartY(topThirdY1, topThirdY2);
				fourth2w.setStartX(topFourthX1, topFourthX2);
				fourth2w.setStartY(topFourthY1, topFourthY2);
				break;

			case 1:
				// first walls
				first1w.setFinishX(rightFirstX1, rightFirstX2);
				first1w.setFinishY(rightFirstY1, rightFirstY2);
				second1w.setFinishX(rightSecondX1, rightSecondX2);
				second1w.setFinishY(rightSecondY1, rightSecondY2);
				third1w.setFinishX(rightThirdX1, rightThirdX2);
				third1w.setFinishY(rightThirdY1, rightThirdY2);
				fourth1w.setFinishX(rightFourthX1, rightFourthX2);
				fourth1w.setFinishY(rightFourthY1, rightFourthY2);
				// second walls
				first2w.setStartX(leftFirstX1, leftFirstX2);
				first2w.setStartY(leftFirstY1, leftFirstY2);
				second2w.setStartX(leftSecondX1, leftSecondX2);
				second2w.setStartY(leftSecondY1, leftSecondY2);
				third2w.setStartX(leftThirdX1, leftThirdX2);
				third2w.setStartY(leftThirdY1, leftThirdY2);
				fourth2w.setStartX(leftFourthX1, leftFourthX2);
				fourth2w.setStartY(leftFourthY1, leftFourthY2);
				break;

			case 2:
				// first walls
				first1w.setFinishX(topFirstX1, topFirstX2);
				first1w.setFinishY(topFirstY1, topFirstY2);
				second1w.setFinishX(topSecondX1, topSecondX2);
				second1w.setFinishY(topSecondY1, topSecondY2);
				third1w.setFinishX(topThirdX1, topThirdX2);
				third1w.setFinishY(topThirdY1, topThirdY2);
				fourth1w.setFinishX(topFourthX1, topFourthX2);
				fourth1w.setFinishY(topFourthY1, topFourthY2);
				// second walls
				first2w.setStartX(bottomFirstX1, bottomFirstX2);
				first2w.setStartY(bottomFirstY1, bottomFirstY2);
				second2w.setStartX(bottomSecondX1, bottomSecondX2);
				second2w.setStartY(bottomSecondY1, bottomSecondY2);
				third2w.setStartX(bottomThirdX1, bottomThirdX2);
				third2w.setStartY(bottomThirdY1, bottomThirdY2);
				fourth2w.setStartX(bottomFourthX1, bottomFourthX2);
				fourth2w.setStartY(bottomFourthY1, bottomFourthY2);
				break;

			case 3:
				// first walls
				first1w.setFinishX(leftFirstX1, leftFirstX2);
				first1w.setFinishY(leftFirstY1, leftFirstY2);
				second1w.setFinishX(leftSecondX1, leftSecondX2);
				second1w.setFinishY(leftSecondY1, leftSecondY2);
				third1w.setFinishX(leftThirdX1, leftThirdX2);
				third1w.setFinishY(leftThirdY1, leftThirdY2);
				fourth1w.setFinishX(leftFourthX1, leftFourthX2);
				fourth1w.setFinishY(leftFourthY1, leftFourthY2);
				// second walls
				first2w.setStartX(rightFirstX1, rightFirstX2);
				first2w.setStartY(rightFirstY1, rightFirstY2);
				second2w.setStartX(rightSecondX1, rightSecondX2);
				second2w.setStartY(rightSecondY1, rightSecondY2);
				third2w.setStartX(rightThirdX1, rightThirdX2);
				third2w.setStartY(rightThirdY1, rightThirdY2);
				fourth2w.setStartX(rightFourthX1, rightFourthX2);
				fourth2w.setStartY(rightFourthY1, rightFourthY2);
				break;
			}
			// walls which are not in the game screen,
			// will move into the center
			// also set closed and opened statuses
			first2w.setFinishX(centralFirstX1, centralFirstX2);
			first2w.setFinishY(centralFirstY1, centralFirstY2);
			second2w.setFinishX(centralSecondX1, centralSecondX2);
			second2w.setFinishY(centralSecondY1, centralSecondY2);
			third2w.setFinishX(centralThirdX1, centralThirdX2);
			third2w.setFinishY(centralThirdY1, centralThirdY2);
			fourth2w.setFinishX(centralFourthX1, centralFourthX2);
			fourth2w.setFinishY(centralFourthY1, centralFourthY2);

			if (firstWallClosed)
				first2w.close();
			if (secondWallClosed)
				second2w.close();
			if (thirdWallClosed)
				third2w.close();
			if (fourthWallClosed)
				fourth2w.close();
		} else {
			switch (way) {
			case 0:
				// first walls
				first2w.setFinishX(bottomFirstX1, bottomFirstX2);
				first2w.setFinishY(bottomFirstY1, bottomFirstY2);
				second2w.setFinishX(bottomSecondX1, bottomSecondX2);
				second2w.setFinishY(bottomSecondY1, bottomSecondY2);
				third2w.setFinishX(bottomThirdX1, bottomThirdX2);
				third2w.setFinishY(bottomThirdY1, bottomThirdY2);
				fourth2w.setFinishX(bottomFourthX1, bottomFourthX2);
				fourth2w.setFinishY(bottomFourthY1, bottomFourthY2);
				// second walls
				first1w.setStartX(topFirstX1, topFirstX2);
				first1w.setStartY(topFirstY1, topFirstY2);
				second1w.setStartX(topSecondX1, topSecondX2);
				second1w.setStartY(topSecondY1, topSecondY2);
				third1w.setStartX(topThirdX1, topThirdX2);
				third1w.setStartY(topThirdY1, topThirdY2);
				fourth1w.setStartX(topFourthX1, topFourthX2);
				fourth1w.setStartY(topFourthY1, topFourthY2);
				break;

			case 1:
				// first walls
				first2w.setFinishX(rightFirstX1, rightFirstX2);
				first2w.setFinishY(rightFirstY1, rightFirstY2);
				second2w.setFinishX(rightSecondX1, rightSecondX2);
				second2w.setFinishY(rightSecondY1, rightSecondY2);
				third2w.setFinishX(rightThirdX1, rightThirdX2);
				third2w.setFinishY(rightThirdY1, rightThirdY2);
				fourth2w.setFinishX(rightFourthX1, rightFourthX2);
				fourth2w.setFinishY(rightFourthY1, rightFourthY2);
				// second walls
				first1w.setStartX(leftFirstX1, leftFirstX2);
				first1w.setStartY(leftFirstY1, leftFirstY2);
				second1w.setStartX(leftSecondX1, leftSecondX2);
				second1w.setStartY(leftSecondY1, leftSecondY2);
				third1w.setStartX(leftThirdX1, leftThirdX2);
				third1w.setStartY(leftThirdY1, leftThirdY2);
				fourth1w.setStartX(leftFourthX1, leftFourthX2);
				fourth1w.setStartY(leftFourthY1, leftFourthY2);
				break;

			case 2:
				// first walls
				first2w.setFinishX(topFirstX1, topFirstX2);
				first2w.setFinishY(topFirstY1, topFirstY2);
				second2w.setFinishX(topSecondX1, topSecondX2);
				second2w.setFinishY(topSecondY1, topSecondY2);
				third2w.setFinishX(topThirdX1, topThirdX2);
				third2w.setFinishY(topThirdY1, topThirdY2);
				fourth2w.setFinishX(topFourthX1, topFourthX2);
				fourth2w.setFinishY(topFourthY1, topFourthY2);
				// second walls
				first1w.setStartX(bottomFirstX1, bottomFirstX2);
				first1w.setStartY(bottomFirstY1, bottomFirstY2);
				second1w.setStartX(bottomSecondX1, bottomSecondX2);
				second1w.setStartY(bottomSecondY1, bottomSecondY2);
				third1w.setStartX(bottomThirdX1, bottomThirdX2);
				third1w.setStartY(bottomThirdY1, bottomThirdY2);
				fourth1w.setStartX(bottomFourthX1, bottomFourthX2);
				fourth1w.setStartY(bottomFourthY1, bottomFourthY2);
				break;

			case 3:
				// first walls
				first2w.setFinishX(leftFirstX1, leftFirstX2);
				first2w.setFinishY(leftFirstY1, leftFirstY2);
				second2w.setFinishX(leftSecondX1, leftSecondX2);
				second2w.setFinishY(leftSecondY1, leftSecondY2);
				third2w.setFinishX(leftThirdX1, leftThirdX2);
				third2w.setFinishY(leftThirdY1, leftThirdY2);
				fourth2w.setFinishX(leftFourthX1, leftFourthX2);
				fourth2w.setFinishY(leftFourthY1, leftFourthY2);
				// second walls
				first1w.setStartX(rightFirstX1, rightFirstX2);
				first1w.setStartY(rightFirstY1, rightFirstY2);
				second1w.setStartX(rightSecondX1, rightSecondX2);
				second1w.setStartY(rightSecondY1, rightSecondY2);
				third1w.setStartX(rightThirdX1, rightThirdX2);
				third1w.setStartY(rightThirdY1, rightThirdY2);
				fourth1w.setStartX(rightFourthX1, rightFourthX2);
				fourth1w.setStartY(rightFourthY1, rightFourthY2);
				break;
			}
			// walls which are not in the game screen,
			// will move into the center
			// set closing status
			first1w.setFinishX(centralFirstX1, centralFirstX2);
			first1w.setFinishY(centralFirstY1, centralFirstY2);
			second1w.setFinishX(centralSecondX1, centralSecondX2);
			second1w.setFinishY(centralSecondY1, centralSecondY2);
			third1w.setFinishX(centralThirdX1, centralThirdX2);
			third1w.setFinishY(centralThirdY1, centralThirdY2);
			fourth1w.setFinishX(centralFourthX1, centralFourthX2);
			fourth1w.setFinishY(centralFourthY1, centralFourthY2);

			if (firstWallClosed)
				first1w.close();
			if (secondWallClosed)
				second1w.close();
			if (thirdWallClosed)
				third1w.close();
			if (fourthWallClosed)
				fourth1w.close();
		}
	}

	public void setCorner(int way) {
		if (firstOne) {
			switch (way) {
			case 0:
				// first corners
				first1c.setFinishX(bottomX1);
				first1c.setFinishY(bottomY1);
				second1c.setFinishX(bottomX2);
				second1c.setFinishY(bottomY2);
				third1c.setFinishX(bottomX3);
				third1c.setFinishY(bottomY3);
				fourth1c.setFinishX(bottomX4);
				fourth1c.setFinishY(bottomY4);
				// second corners
				first2c.setStartX(topX1);
				first2c.setStartY(topY1);
				second2c.setStartX(topX2);
				second2c.setStartY(topY2);
				third2c.setStartX(topX3);
				third2c.setStartY(topY3);
				fourth2c.setStartX(topX4);
				fourth2c.setStartY(topY4);
				break;

			case 1:
				// first corners
				first1c.setFinishX(rightX1);
				first1c.setFinishY(rightY1);
				second1c.setFinishX(rightX2);
				second1c.setFinishY(rightY2);
				third1c.setFinishX(rightX3);
				third1c.setFinishY(rightY3);
				fourth1c.setFinishX(rightX4);
				fourth1c.setFinishY(rightY4);
				// second corners
				first2c.setStartX(leftX1);
				first2c.setStartY(leftY1);
				second2c.setStartX(leftX2);
				second2c.setStartY(leftY2);
				third2c.setStartX(leftX3);
				third2c.setStartY(leftY3);
				fourth2c.setStartX(leftX4);
				fourth2c.setStartY(leftY4);
				break;

			case 2:
				// first corners
				first1c.setFinishX(topX1);
				first1c.setFinishY(topY1);
				second1c.setFinishX(topX2);
				second1c.setFinishY(topY2);
				third1c.setFinishX(topX3);
				third1c.setFinishY(topY3);
				fourth1c.setFinishX(topX4);
				fourth1c.setFinishY(topY4);
				// second corners
				first2c.setStartX(bottomX1);
				first2c.setStartY(bottomY1);
				second2c.setStartX(bottomX2);
				second2c.setStartY(bottomY2);
				third2c.setStartX(bottomX3);
				third2c.setStartY(bottomY3);
				fourth2c.setStartX(bottomX4);
				fourth2c.setStartY(bottomY4);
				break;

			case 3:
				// first corners
				first1c.setFinishX(leftX1);
				first1c.setFinishY(leftY1);
				second1c.setFinishX(leftX2);
				second1c.setFinishY(leftY2);
				third1c.setFinishX(leftX3);
				third1c.setFinishY(leftY3);
				fourth1c.setFinishX(leftX4);
				fourth1c.setFinishY(leftY4);
				// second corners
				first2c.setStartX(rightX1);
				first2c.setStartY(rightY1);
				second2c.setStartX(rightX2);
				second2c.setStartY(rightY2);
				third2c.setStartX(rightX3);
				third2c.setStartY(rightY3);
				fourth2c.setStartX(rightX4);
				fourth2c.setStartY(rightY4);
				break;
			}
			// corners which are not in the game screen,
			// corners move into the center
			first2c.setFinishX(centerX1);
			first2c.setFinishY(centerY1);
			second2c.setFinishX(centerX2);
			second2c.setFinishY(centerY2);
			third2c.setFinishX(centerX3);
			third2c.setFinishY(centerY3);
			fourth2c.setFinishX(centerX4);
			fourth2c.setFinishY(centerY4);

		} else {

			switch (way) {
			case 0:
				// first corners
				first2c.setFinishX(bottomX1);
				first2c.setFinishY(bottomY1);
				second2c.setFinishX(bottomX2);
				second2c.setFinishY(bottomY2);
				third2c.setFinishX(bottomX3);
				third2c.setFinishY(bottomY3);
				fourth2c.setFinishX(bottomX4);
				fourth2c.setFinishY(bottomY4);
				// second corners
				first1c.setStartX(topX1);
				first1c.setStartY(topY1);
				second1c.setStartX(topX2);
				second1c.setStartY(topY2);
				third1c.setStartX(topX3);
				third1c.setStartY(topY3);
				fourth1c.setStartX(topX4);
				fourth1c.setStartY(topY4);
				break;

			case 1:
				// first corners
				first2c.setFinishX(rightX1);
				first2c.setFinishY(rightY1);
				second2c.setFinishX(rightX2);
				second2c.setFinishY(rightY2);
				third2c.setFinishX(rightX3);
				third2c.setFinishY(rightY3);
				fourth2c.setFinishX(rightX4);
				fourth2c.setFinishY(rightY4);
				// second corners
				first1c.setStartX(leftX1);
				first1c.setStartY(leftY1);
				second1c.setStartX(leftX2);
				second1c.setStartY(leftY2);
				third1c.setStartX(leftX3);
				third1c.setStartY(leftY3);
				fourth1c.setStartX(leftX4);
				fourth1c.setStartY(leftY4);
				break;

			case 2:
				// first corners
				first2c.setFinishX(topX1);
				first2c.setFinishY(topY1);
				second2c.setFinishX(topX2);
				second2c.setFinishY(topY2);
				third2c.setFinishX(topX3);
				third2c.setFinishY(topY3);
				fourth2c.setFinishX(topX4);
				fourth2c.setFinishY(topY4);
				// second corners
				first1c.setStartX(bottomX1);
				first1c.setStartY(bottomY1);
				second1c.setStartX(bottomX2);
				second1c.setStartY(bottomY2);
				third1c.setStartX(bottomX3);
				third1c.setStartY(bottomY3);
				fourth1c.setStartX(bottomX4);
				fourth1c.setStartY(bottomY4);
				break;

			case 3:
				// first corners
				first2c.setFinishX(leftX1);
				first2c.setFinishY(leftY1);
				second2c.setFinishX(leftX2);
				second2c.setFinishY(leftY2);
				third2c.setFinishX(leftX3);
				third2c.setFinishY(leftY3);
				fourth2c.setFinishX(leftX4);
				fourth2c.setFinishY(leftY4);
				// second corners
				first1c.setStartX(rightX1);
				first1c.setStartY(rightY1);
				second1c.setStartX(rightX2);
				second1c.setStartY(rightY2);
				third1c.setStartX(rightX3);
				third1c.setStartY(rightY3);
				fourth1c.setStartX(rightX4);
				fourth1c.setStartY(rightY4);
				break;
			}
			// corners which are not in the game screen,
			// corners move into the center
			first1c.setFinishX(centerX1);
			first1c.setFinishY(centerY1);
			second1c.setFinishX(centerX2);
			second1c.setFinishY(centerY2);
			third1c.setFinishX(centerX3);
			third1c.setFinishY(centerY3);
			fourth1c.setFinishX(centerX4);
			fourth1c.setFinishY(centerY4);
		}
	}

	public boolean isCornersMoving() {
		return (first1c.isMoving() && first2c.isMoving() && second1c.isMoving()
				&& second2c.isMoving() && third1c.isMoving()
				&& third2c.isMoving() && fourth1c.isMoving() && fourth2c
					.isMoving());
	}

	public boolean isWallsMoving() {
		return (first2w.isMoving() && second2w.isMoving() && third2w.isMoving()
				&& fourth2w.isMoving() && first1w.isMoving()
				&& second1w.isMoving() && third1w.isMoving() && fourth1w
					.isMoving());
	}

	public boolean isWallsClosing() {
		return (first2w.isClosing() || second2w.isClosing()
				|| third2w.isClosing() || fourth2w.isClosing()
				|| first1w.isClosing() || second1w.isClosing()
				|| third1w.isClosing() || fourth1w.isClosing());
	}

	public Corner getFirst1() {
		return first1c;
	}

	public Corner getSecond1() {
		return second1c;
	}

	public Corner getThird1() {
		return third1c;
	}

	public Corner getFourth1() {
		return fourth1c;
	}

	public Corner getFirst2() {
		return first2c;
	}

	public Corner getSecond2() {
		return second2c;
	}

	public Corner getThird2() {
		return third2c;
	}

	public Corner getFourth2() {
		return fourth2c;
	}

	public Wall getFirst1w() {
		return first1w;
	}

	public Wall getFirst2w() {
		return first2w;
	}

	public Wall getSecond1w() {
		return second1w;
	}

	public Wall getSecond2w() {
		return second2w;
	}

	public Wall getThird1w() {
		return third1w;
	}

	public Wall getThird2w() {
		return third2w;
	}

	public Wall getFourth1w() {
		return fourth1w;
	}

	public Wall getFourth2w() {
		return fourth2w;
	}

	public boolean isHorizontal() {
		return horizontal;
	}

	public boolean isFirstOne() {
		return firstOne;
	}
}
