package com.doublew.outofhere.ScreenContent;

import java.util.ArrayList;

import com.badlogic.gdx.math.Intersector;
import com.doublew.outofhere.Buttons.BallButton;
import com.doublew.outofhere.Buttons.Button;
import com.doublew.outofhere.Buttons.RoundButton;
import com.doublew.outofhere.GameObjects.EnvironmentManager;
import com.doublew.outofhere.GameObjects.GOFloatingBottomPanel;
import com.doublew.outofhere.GameObjects.GOFloatingLeftPanel;
import com.doublew.outofhere.GameObjects.GOFloatingRightPanel;
import com.doublew.outofhere.GameObjects.GOFloatingTopPanel;
import com.doublew.outofhere.GameObjects.TopPanel;
import com.doublew.outofhere.Support.AssetLoader;
import com.doublew.outofhere.Support.RandomGenerator;
import com.doublew.outofhere.Support.SRS;

class OP {
	// OP - offset point for shaking
	float x, y;
	public OP(float x, float y) {
		this.x = x;
		this.y = y;
	}
}

public class GameMechanism {

	private float shadowIndent;
	private BallButton ball;
	private TopPanel topPanel;
	//order for arrows 
	private int countingArrowsOrder;
	private RandomGenerator RG;
	// timer stuff
	private float period, passed, comparator;
	private boolean ticking, isGameOver, isShaking;
	private EnvironmentManager EM;
	private GOFloatingTopPanel GOFTP;
	private GOFloatingLeftPanel GOFLP;
	private GOFloatingBottomPanel GOFBP;
	private GOFloatingRightPanel GOFRP;
	// List for shaking offsets
	private ArrayList<OP> shakingList;
	private int shakingListPointer;
	private boolean steady, onPause;
	private RoundButton resumeButton;
	private boolean soundCrutch;

	public GameMechanism() {
		shadowIndent = 3f;
		float cornerStepX = 250;
		float cornerStepY = cornerStepX;

		float wallStepX = cornerStepX;
		float wallStepY = cornerStepY;

		float closingStepX = 100;
		float closingStepY = closingStepX;
		
		float ballRadius = 20f;
		
		float lineSize = 2;
		
		countingArrowsOrder = 0;
		
		//when passed'(which is incrementing with the time delta') 
		//becomes more than comparator', the last is incrementing
		//by period', and updating, in the end it ticking every
		//period' value(in seconds)
		period = 0.5f;// seconds between arrows
		passed = 0;
		comparator = 0;
		
		isGameOver = false;
		isShaking = false;
		steady = true;
		
		ball = new BallButton(SRS.gameWidth / 2f, 24 + (SRS.gameHeight - 24) / 2, ballRadius, shadowIndent);

		topPanel = new TopPanel(new Button(136 - 24, 0, 24, 24,
				AssetLoader.pauseButtonUp, AssetLoader.pauseButtonDown),
				shadowIndent, 0);
		RG = new RandomGenerator(4);

		EM = new EnvironmentManager(SRS.gameWidth, SRS.gameHeight,
				shadowIndent, 44, 44, topPanel.getHeight(), cornerStepX,
				cornerStepY, wallStepX, wallStepY, closingStepX, closingStepY);

		GOFTP = new GOFloatingTopPanel(200, SRS.gameWidth, SRS.gameHeight,
				lineSize);
		GOFLP = new GOFloatingLeftPanel(200, SRS.gameWidth, SRS.gameHeight,
				lineSize);
		GOFBP = new GOFloatingBottomPanel(200, SRS.gameWidth, SRS.gameHeight,
				lineSize);
		GOFRP = new GOFloatingRightPanel(200, SRS.gameWidth, SRS.gameHeight,
				lineSize);

		resumeButton = new RoundButton((float) (SRS.gameWidth / 2),
				(float) (24 + ((SRS.gameHeight - 24) / 2)), ballRadius,
				AssetLoader.playButtonUp, AssetLoader.playButtonDown);

		
		RG.generateWays();
		play();
		soundCrutch = true;
	}

	public void update(float delta) {
		if (!onPause) {
			if (!isGameOver) {
				//choosing direction  
				if (ticking) {
					if (comparator <= passed) {
						comparator += period;
						if (steady) {
							ball.update(5);
							steady = false;
						} else {
							if (countingArrowsOrder == 4) {
								// Game over case 1
								gameOver(false);
							} else {
								ball.update(RG.getWay(countingArrowsOrder));
								nextOrder();
							}
						}
					}
					passed += delta;
				}
				//transition between rooms
				if (!ticking) {
					ball.update(4); // set ball's texture to empty
					if (!EM.isCornersMoving() && !EM.isWallsMoving()) {
						if (!EM.isWallsClosing()) {
							soundCrutch = true;
							EM.reloadOffscreenWalls();
							startTicking();
						} else {
							EM.updateClosingWalls(delta);
							if(soundCrutch){
								AssetLoader.playSound(4);
								soundCrutch = false;
							}
						}
					} else {
						EM.updateCorners(delta);
						EM.updateMovingWalls(delta);

						// Game over case 2
						if (checkCollision()) {
							gameOver(true);
						}
					}
				}
			} else {
				if (!isShaking) {
					GOFTP.update(delta);
					GOFLP.update(delta);
					GOFBP.update(delta);
					GOFRP.update(delta);
				} else {
					shakingUpdate(delta);
				}
			}
		}
	}

	public boolean checkCollision() {
		if (Intersector.overlaps(ball.getBounds(), EM.getFirst1w().getBounds())
				|| Intersector.overlaps(ball.getBounds(), EM.getSecond1w()
						.getBounds())
				|| Intersector.overlaps(ball.getBounds(), EM.getThird1w()
						.getBounds())
				|| Intersector.overlaps(ball.getBounds(), EM.getFourth1w()
						.getBounds())
				|| Intersector.overlaps(ball.getBounds(), EM.getFirst2w()
						.getBounds())
				|| Intersector.overlaps(ball.getBounds(), EM.getSecond2w()
						.getBounds())
				|| Intersector.overlaps(ball.getBounds(), EM.getThird2w()
						.getBounds())
				|| Intersector.overlaps(ball.getBounds(), EM.getFourth2w()
						.getBounds())) {
			return true;
		} else {
			return false;
		}
	}

	public float getShadowIndent() {
		return shadowIndent;
	}

	public BallButton getBall() {
		return ball;
	}

	public TopPanel getTopPanel() {
		return topPanel;
	}

	public void nextOrder() {
		countingArrowsOrder++;
	}

	public void startTicking() {
		ticking = true;
	}

	public void reloadTicking() {
		ticking = false;
		countingArrowsOrder = 0;
		comparator = 0;
		passed = 0;
		steady = true;
		RG.generateWays();
		RG.generateWalls(ball.getWay());
	}

	public boolean isTicking() {
		return ticking;
	}

	public void swapCornersAndWalls() {
		EM.switcher(ball.getWay(), RG.getWall(0), RG.getWall(1), RG.getWall(2),
				RG.getWall(3));
		// Corners ready to move
		EM.getFirst1().move();
		EM.getSecond1().move();
		EM.getThird1().move();
		EM.getFourth1().move();

		EM.getFirst2().move();
		EM.getSecond2().move();
		EM.getThird2().move();
		EM.getFourth2().move();
		
		// Walls ready to move
		EM.getFirst1w().startMoving();
		EM.getSecond1w().startMoving();
		EM.getThird1w().startMoving();
		EM.getFourth1w().startMoving();

		EM.getFirst2w().startMoving();
		EM.getSecond2w().startMoving();
		EM.getThird2w().startMoving();
		EM.getFourth2w().startMoving();
	}

	public EnvironmentManager getEM() {
		return EM;
	}
	
	public void gameOver(boolean needShaking) {
		//if game over case #1 then shaking is not required!  
		if (needShaking){
			AssetLoader.playSound(1);
			shackeScreen();
		}else {
			AssetLoader.playSound(0);
		}
		isGameOver = true;
		GOFTP.startFloating(topPanel.getScore(), AssetLoader.getHighScore());
		GOFLP.startFloating();
		GOFBP.startFloating();
		GOFRP.startFloating();
	}

	public GOFloatingTopPanel getGOFTP() {
		return GOFTP;
	}

	public GOFloatingLeftPanel getGOFLP() {
		return GOFLP;
	}

	public GOFloatingBottomPanel getGOFBP() {
		return GOFBP;
	}

	public GOFloatingRightPanel getGOFRP() {
		return GOFRP;
	}

	// for renderer
	public boolean isGameOver() {
		return isGameOver;
	}

	public void shackeScreen() {
		isShaking = true;
		shakingList = new ArrayList<OP>();
		shakingList.clear();
		comparator = 0;
		passed = 0;
		period = 0.03f;
		shakingListPointer = 0;

		// in the list will be distance
		shakingList.add(new OP(-5, -5)); // -5, -5
		shakingList.add(new OP(0, 10)); // -5, 5
		shakingList.add(new OP(10, -15)); // 5, -10
		shakingList.add(new OP(-5, 15)); // 0, 5
		shakingList.add(new OP(0, -15)); // 0, -10
		shakingList.add(new OP(10, 10)); // 10, 0

		shakingList.add(new OP(-15, -5)); // -5, -5
		shakingList.add(new OP(0, 10)); // -5, 5
		shakingList.add(new OP(10, -15)); // 5, -10
		shakingList.add(new OP(-5, 15)); // 0, 5
		shakingList.add(new OP(0, -15)); // 0, -10
		shakingList.add(new OP(10, 10)); // 10, 0

		// return to beginning at the end
		shakingList.add(new OP(-10, 0)); // 0, 0
	}

	public void shakingUpdate(float delta) {
		if (comparator <= passed) {
			comparator += period;
			if (shakingListPointer > (shakingList.size() - 1)) {
				isShaking = false;
			} else {
				// Shake ball
				ball.getBounds().x += shakingList.get(shakingListPointer).x;
				ball.getBounds().y += shakingList.get(shakingListPointer).y;
				// Shake first corners
				EM.getFirst1().addX(shakingList.get(shakingListPointer).x);
				EM.getFirst1().addY(shakingList.get(shakingListPointer).y);
				EM.getSecond1().addX(shakingList.get(shakingListPointer).x);
				EM.getSecond1().addY(shakingList.get(shakingListPointer).y);
				EM.getThird1().addX(shakingList.get(shakingListPointer).x);
				EM.getThird1().addY(shakingList.get(shakingListPointer).y);
				EM.getFourth1().addX(shakingList.get(shakingListPointer).x);
				EM.getFourth1().addY(shakingList.get(shakingListPointer).y);
				// Shake second corners
				EM.getFirst2().addX(shakingList.get(shakingListPointer).x);
				EM.getFirst2().addY(shakingList.get(shakingListPointer).y);
				EM.getSecond2().addX(shakingList.get(shakingListPointer).x);
				EM.getSecond2().addY(shakingList.get(shakingListPointer).y);
				EM.getThird2().addX(shakingList.get(shakingListPointer).x);
				EM.getThird2().addY(shakingList.get(shakingListPointer).y);
				EM.getFourth2().addX(shakingList.get(shakingListPointer).x);
				EM.getFourth2().addY(shakingList.get(shakingListPointer).y);
				// Shake first walls
				EM.getFirst1w().addX(shakingList.get(shakingListPointer).x);
				EM.getFirst1w().addY(shakingList.get(shakingListPointer).y);
				EM.getSecond1w().addX(shakingList.get(shakingListPointer).x);
				EM.getSecond1w().addY(shakingList.get(shakingListPointer).y);
				EM.getThird1w().addX(shakingList.get(shakingListPointer).x);
				EM.getThird1w().addY(shakingList.get(shakingListPointer).y);
				EM.getFourth1w().addX(shakingList.get(shakingListPointer).x);
				EM.getFourth1w().addY(shakingList.get(shakingListPointer).y);
				// Shake second walls
				EM.getFirst2w().addX(shakingList.get(shakingListPointer).x);
				EM.getFirst2w().addY(shakingList.get(shakingListPointer).y);
				EM.getSecond2w().addX(shakingList.get(shakingListPointer).x);
				EM.getSecond2w().addY(shakingList.get(shakingListPointer).y);
				EM.getThird2w().addX(shakingList.get(shakingListPointer).x);
				EM.getThird2w().addY(shakingList.get(shakingListPointer).y);
				EM.getFourth2w().addX(shakingList.get(shakingListPointer).x);
				EM.getFourth2w().addY(shakingList.get(shakingListPointer).y);
				
				//Next shaking point
				shakingListPointer++;
			}
		}
		passed += delta;
	}

	public void saveScore() {
		if (topPanel.getScore() > AssetLoader.getHighScore()) {
			AssetLoader.setHighScore(topPanel.getScore());
		}
	}

	public void pause() {
		onPause = true;
		if(AssetLoader.getSoundSettings() == 1 || AssetLoader.getSoundSettings() == 3){
			AssetLoader.music.pause();
		}
	}

	public void play() {
		onPause = false;
		if(AssetLoader.getSoundSettings() == 1 || AssetLoader.getSoundSettings() == 3){
			AssetLoader.music.play();
		}
	}

	public boolean isPaused() {
		return onPause;
	}

	public RoundButton getResumeButton() {
		return resumeButton;
	}
}
