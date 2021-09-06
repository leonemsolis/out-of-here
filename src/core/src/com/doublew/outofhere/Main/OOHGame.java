package com.doublew.outofhere.Main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.doublew.outofhere.Screens.GameScreen;
import com.doublew.outofhere.Screens.HelpScreen;
import com.doublew.outofhere.Screens.MenuScreen;
import com.doublew.outofhere.Screens.SplashScreen;
import com.doublew.outofhere.Support.AssetLoader;
import com.doublew.outofhere.Support.SRS;

public class OOHGame extends Game {
	private int currentScreen;
	private SplashScreen splashScreen;
	private MenuScreen menuScreen;
	private GameScreen gameScreen;
	private HelpScreen helpScreen;
	
	@Override
	public void create() {
		Gdx.input.setCatchBackKey(true);
		AssetLoader.load();
		SRS.load();
		setScreen(0);
	} 

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
	
	
	public void setScreen(int screen){
		switch (screen) {
		case 0:
			reloadScreen(0);
			setScreen(splashScreen);
			currentScreen = 0;
			break;
		case 1:
			reloadScreen(1);
			setScreen(menuScreen);
			currentScreen = 1;
			break;
		case 2:
			reloadScreen(2);
			setScreen(gameScreen);
			currentScreen = 2;
			break;
		case 3:
			reloadScreen(3);
			setScreen(helpScreen);
			currentScreen = 3;
			break;
		}
	}
	
	public void reloadScreen(int screen){
		switch (screen) {
		case 0:
			splashScreen = new SplashScreen(this);
			break;
		case 1:
			menuScreen = new MenuScreen(this);
			break;
		case 2:
			gameScreen = new GameScreen(this);
			break;
		case 3: 
			helpScreen = new HelpScreen(this);
			break;
		}
	}
	
	public MenuScreen getMenuScreen() {
		return menuScreen;
	}
	
	public GameScreen getGameScreen() {
		return gameScreen;
	}
	
	public HelpScreen getHelpScreen() {
		return helpScreen;
	}
	
	public int getCurrentScreen(){
		return currentScreen;
	}
}
