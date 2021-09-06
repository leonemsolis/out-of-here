package com.doublew.outofhere.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.doublew.outofhere.Main.OOHGame;
import com.doublew.outofhere.ScreenContent.GameMechanism;
import com.doublew.outofhere.ScreenContent.GameRenderer;
import com.doublew.outofhere.Support.InputHandler;
import com.doublew.outofhere.Support.SRS;

public class GameScreen implements Screen {

	private GameMechanism mech;
	private GameRenderer renderer;

	public GameScreen(OOHGame game) {
        mech = new GameMechanism();
        renderer = new GameRenderer(mech);
		Gdx.input.setInputProcessor(new InputHandler(game, SRS.screenScaleX, SRS.screenScaleY));
	}
	
	@Override
	public void show() {
	}
	
	
	@Override
	public void render(float delta) {
		mech.update(delta);
		renderer.draw(delta);
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
		if(!mech.isPaused() && !mech.isGameOver()){
			mech.pause();
		}
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
	}
	
	public GameMechanism getMech() {
		return mech;
	}
}
