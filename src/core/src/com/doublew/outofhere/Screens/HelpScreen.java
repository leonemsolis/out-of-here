package com.doublew.outofhere.Screens;

import com.badlogic.gdx.Screen;
import com.doublew.outofhere.Main.OOHGame;
import com.doublew.outofhere.ScreenContent.HelpMechanism;
import com.doublew.outofhere.ScreenContent.HelpRenderer;

public class HelpScreen implements Screen {
	
	HelpMechanism mech;
	HelpRenderer renderer;
	
	public HelpScreen(OOHGame game) {
		mech = new HelpMechanism();
		renderer = new HelpRenderer(mech);
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
	
	public HelpMechanism getMech() {
		return mech;
	}
}
