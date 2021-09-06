package com.doublew.outofhere.ScreenContent;
import com.doublew.outofhere.Buttons.Button;
import com.doublew.outofhere.Buttons.RoundButton;
import com.doublew.outofhere.GameObjects.FloatingTopPanel;
import com.doublew.outofhere.GameObjects.Corner;
import com.doublew.outofhere.GameObjects.TopPanel;
import com.doublew.outofhere.Screens.MenuScreen;
import com.doublew.outofhere.Support.AssetLoader;
import com.doublew.outofhere.Support.SRS;

public class MenuMechanism{
	
	private MenuScreen menuScreen;
	private RoundButton playButton;
	private Corner firstCorner, secondCorner, thirdCorner, fourthCorner;
	private float shadowIndent;
	private TopPanel topPanel;
	
	private FloatingTopPanel FTP;
	//trigger to switch screen, when corners are not moving and readyToGame is true, screen is switching to game
	private boolean readyToGame;
	
	public MenuMechanism(MenuScreen menuScreen) {
		shadowIndent = 3f;
		topPanel = new TopPanel(new Button(136-24, 0, 24, 24, AssetLoader.helpButtonUp, AssetLoader.helpButtonDown), shadowIndent, AssetLoader.getHighScore());
		this.menuScreen = menuScreen;
		playButton = new RoundButton((float)(SRS.gameWidth / 2), (float)(24 + ((SRS.gameHeight - 24) / 2)), 20, AssetLoader.playButtonUp, AssetLoader.playButtonDown);
		//Step x and y for sending to corners
		float cornerStepX = 75;
		float cornerStepY = cornerStepX; // / (menuScreen.getGameWidth() / (menuScreen.getGameHeight() - 24f));
		float cornerWidth = 44; 
		float cornerHeight = 44;
		firstCorner = new Corner(1, -(cornerWidth / 2) - shadowIndent, 0, topPanel.getHeight() - (cornerHeight / 2) - shadowIndent, topPanel.getHeight(), shadowIndent, cornerStepX, cornerStepY);
		secondCorner = new Corner(2, (SRS.gameWidth - (cornerWidth / 2)) + shadowIndent, SRS.gameWidth - cornerWidth, topPanel.getHeight() - (cornerHeight / 2) - shadowIndent, topPanel.getHeight(), shadowIndent, cornerStepX, cornerStepY);
		thirdCorner = new Corner(3, (SRS.gameWidth - (cornerWidth / 2)) + shadowIndent, SRS.gameWidth - cornerWidth, (SRS.gameHeight - (cornerHeight / 2)) + shadowIndent, SRS.gameHeight - 44, shadowIndent, cornerStepX, cornerStepY);
		fourthCorner = new Corner(4, -(cornerWidth / 2) - shadowIndent, 0, (SRS.gameHeight - (cornerWidth / 2)) + shadowIndent, SRS.gameHeight - cornerHeight, shadowIndent, cornerStepX, cornerStepY);
		//Let FTP move with the same speed as corners
		FTP = new FloatingTopPanel(cornerStepY, SRS.gameWidth);
		readyToGame = false;
	}

	public void update(float delta) {
		firstCorner.update(delta);
		secondCorner.update(delta);
		thirdCorner.update(delta);
		fourthCorner.update(delta);
		FTP.update(delta);
		
		if(readyToGame && !firstCorner.isMoving() && !secondCorner.isMoving() && !thirdCorner.isMoving() && !fourthCorner.isMoving()) {
			menuScreen.startGame();
		}
	}
	
	public RoundButton getPlayButton() {
		return playButton;
	}
	
	public Corner getFirstCorner() {
		return firstCorner;
	}

	public Corner getSecondCorner() {
		return secondCorner;
	}

	public Corner getThirdCorner() {
		return thirdCorner;
	}

	public Corner getFourthCorner() {
		return fourthCorner;
	}
	
	public float getShadowIndent() {
		return shadowIndent;
	}
	
	public FloatingTopPanel getFTP() {
		return FTP;
	}
	
	public TopPanel getTopPanel() {
		return topPanel;
	}
	
	public void readyToGame() {
		readyToGame = true;
	}
}

