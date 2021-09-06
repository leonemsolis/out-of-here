package com.doublew.outofhere.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.doublew.outofhere.Main.OOHGame;
import com.doublew.outofhere.ScreenContent.MenuMechanism;
import com.doublew.outofhere.ScreenContent.MenuRenderer;
import com.doublew.outofhere.Support.InputHandler;
import com.doublew.outofhere.Support.SRS;

public class MenuScreen implements Screen {
	
	private OOHGame game;
	private MenuMechanism mech;
	private MenuRenderer renderer;
	
	public MenuScreen(OOHGame game) {
		this.game = game;		
		mech = new MenuMechanism(this);
		renderer = new MenuRenderer(mech);
		Gdx.input.setInputProcessor(new InputHandler(game, SRS.screenScaleX, SRS.screenScaleY));
	}

	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {	
		mech.update(delta);
		renderer.draw();	
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
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
	
	public void startGame() {
		game.setScreen(2);
	}
	
	public MenuMechanism getMech() {
		return mech;
	}
	

}
