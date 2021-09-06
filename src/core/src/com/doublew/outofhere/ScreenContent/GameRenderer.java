package com.doublew.outofhere.ScreenContent;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.doublew.outofhere.Support.SRS;

public class GameRenderer {

	private OrthographicCamera cam;
	private SpriteBatch batcher, shadowBatcher;
	private GameMechanism mech;

	public GameRenderer(GameMechanism mech) {
		this.mech = mech;

		cam = new OrthographicCamera();
		cam.setToOrtho(true, SRS.gameWidth, SRS.gameHeight);

		batcher = new SpriteBatch();
		shadowBatcher = new SpriteBatch();
		shadowBatcher.setColor(0, 0, 0, 0.3f);

		batcher.setProjectionMatrix(cam.combined);
		shadowBatcher.setProjectionMatrix(cam.combined);
	}

	public void draw(float delta) {
		if (!mech.isPaused()) {
			Gdx.gl.glClearColor(142 / 255f, 178 / 255f, 172 / 255f, 0f);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			
			shadowBatcher.begin();
			mech.getEM().drawShadow(shadowBatcher);
			mech.getBall().drawShadow(shadowBatcher);
			mech.getTopPanel().drawShadow(shadowBatcher);
			shadowBatcher.end();

			batcher.begin();
			batcher.setColor(Color.WHITE);
			mech.getEM().draw(batcher);
			mech.getBall().draw(batcher);
			mech.getTopPanel().draw(batcher);
			if (mech.isGameOver()) {
				mech.getGOFTP().draw(batcher, delta);
				mech.getGOFLP().draw(batcher);
				mech.getGOFBP().draw(batcher);
				mech.getGOFRP().draw(batcher);
			}
			batcher.end();
		} else {
			Gdx.gl.glClearColor(59 / 225f, 74 / 225f, 71 / 225f, 0f);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

			batcher.begin();
			batcher.setColor(Color.GRAY);
			mech.getEM().draw(batcher);
			mech.getBall().draw(batcher);
			mech.getTopPanel().draw(batcher);
			batcher.end();

			batcher.begin();
			batcher.setColor(Color.WHITE);
			mech.getResumeButton().draw(batcher);
			batcher.end();
		}

	}
}
