package com.doublew.outofhere.Support;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.doublew.outofhere.Main.OOHGame;

public class InputHandler implements InputProcessor {

	private float scaleX, scaleY;
	private OOHGame game;
	
	public InputHandler(OOHGame game, float scaleX, float scaleY) {
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.game = game;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Keys.BACK || keycode == Keys.ESCAPE){
			switch(game.getCurrentScreen()){
				case 1:
					Gdx.app.exit();
					break;
				case 2:
					game.getGameScreen().getMech().saveScore();
					game.setScreen(1);
					break;
				case 3:
					game.setScreen(1);
					break;
			}
			
		}
		
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		switch (game.getCurrentScreen()) {
		case 1:
			game.getMenuScreen().getMech().getPlayButton().isTouchDown(formatX(screenX), formatY(screenY));
			game.getMenuScreen().getMech().getTopPanel().getRightButton().isTouchDown(formatX(screenX), formatY(screenY));
			game.getMenuScreen().getMech().getTopPanel().getSoundButton().isTouchDown(formatX(screenX), formatY(screenY));
			break;
		case 2:
			if(!game.getGameScreen().getMech().isPaused()){
				if(!game.getGameScreen().getMech().isGameOver()) {
					game.getGameScreen().getMech().getTopPanel().getRightButton().isTouchDown(formatX(screenX), formatY(screenY));
				}else {
					game.getGameScreen().getMech().getGOFTP().getRateButton().isTouchDown(formatX(screenX), formatY(screenY));
				}
				game.getGameScreen().getMech().getTopPanel().getSoundButton().isTouchDown(formatX(screenX), formatY(screenY));
				game.getGameScreen().getMech().getGOFLP().getHomeButton().isTouchDown(formatX(screenX), formatY(screenY));
				game.getGameScreen().getMech().getGOFRP().getRestartButton().isTouchDown(formatX(screenX), formatY(screenY));
					if(game.getGameScreen().getMech().isTicking() && game.getGameScreen().getMech().getBall().isTouchDown(formatX(screenX), formatY(screenY)) && !game.getGameScreen().getMech().isGameOver()) {
						game.getGameScreen().getMech().reloadTicking();
						game.getGameScreen().getMech().swapCornersAndWalls();
						game.getGameScreen().getMech().getTopPanel().addScore(1);
						AssetLoader.playSound(2);
						AssetLoader.playSound(3);
					}
			}else {
				game.getGameScreen().getMech().getResumeButton().isTouchDown(formatX(screenX), formatY(screenY));
			}
			break;
		case 3:
			if(game.getHelpScreen().getMech().getButton().isTouchDown(formatX(screenX), formatY(screenY))) {
				if(!game.getHelpScreen().getMech().getButton().isReady()) {
					game.getHelpScreen().getMech().getButton().update();
				}else {
					game.setScreen(1);
				}
			}
			break;
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		switch (game.getCurrentScreen()) {
		case 1:
			if(game.getMenuScreen().getMech().getTopPanel().getRightButton().isTouchUp(formatX(screenX), formatY(screenY))) {
				game.setScreen(3);
			}
			game.getMenuScreen().getMech().getTopPanel().getSoundButton().isTouchUp(formatX(screenX), formatY(screenY));
			if(game.getMenuScreen().getMech().getPlayButton().isTouchUp(formatX(screenX), formatY(screenY))) {
				game.getMenuScreen().getMech().getFirstCorner().move();
				game.getMenuScreen().getMech().getSecondCorner().move();
				game.getMenuScreen().getMech().getThirdCorner().move();
				game.getMenuScreen().getMech().getFourthCorner().move();
				game.getMenuScreen().getMech().getFTP().startFloating();
				game.getMenuScreen().getMech().readyToGame();
				AssetLoader.playSound(4);
			}
			break;
		case 2:
			if(!game.getGameScreen().getMech().isPaused()) {
				if(!game.getGameScreen().getMech().isGameOver()) {
					if(game.getGameScreen().getMech().getTopPanel().getRightButton().isTouchUp(formatX(screenX), formatY(screenY))) {
						game.getGameScreen().getMech().pause();
					}
				}else {
					if(game.getGameScreen().getMech().getGOFTP().getRateButton().isTouchUp(formatX(screenX), formatY(screenY))) {
						Gdx.net.openURI("https://play.google.com/store/apps/details?id=com.doublew.outofhere.android");
					}
				}
				game.getGameScreen().getMech().getTopPanel().getSoundButton().isTouchUp(formatX(screenX), formatY(screenY));
				if(game.getGameScreen().getMech().getGOFLP().getHomeButton().isTouchUp(formatX(screenX), formatY(screenY))) {
					//home
					game.getGameScreen().getMech().saveScore();
					game.setScreen(1);
				}
				if(game.getGameScreen().getMech().getGOFRP().getRestartButton().isTouchUp(formatX(screenX), formatY(screenY))) {
					//restart
					game.getGameScreen().getMech().saveScore();
					game.setScreen(2);
				}
			}else {
				if(game.getGameScreen().getMech().getResumeButton().isTouchUp(formatX(screenX), formatY(screenY))) {
					game.getGameScreen().getMech().play();
				}
			}
			
			break;
		case 3:
			//TOUCH UP FOR HELP SCREEN
			break;
		}
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
	
	public int formatX(int x){
		return (int)(x / scaleX);
	}
	
	public int formatY(int y){
		return (int)(y / scaleY);
	}
}

