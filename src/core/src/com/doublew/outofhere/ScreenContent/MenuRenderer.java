package com.doublew.outofhere.ScreenContent;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.doublew.outofhere.Support.AssetLoader;
import com.doublew.outofhere.Support.SRS;

public class MenuRenderer {
	
	private OrthographicCamera cam;
	private SpriteBatch batcher, shadowBatcher;
	private MenuMechanism mech;
	private float shadowIndent;
	
	public MenuRenderer(MenuMechanism mech) {
		this.mech = mech;
		
		cam = new OrthographicCamera();
        cam.setToOrtho(true, SRS.gameWidth, SRS.gameHeight);
        
        //indent for shadows (x and y)
        shadowIndent = mech.getShadowIndent();
        
        //batcher for rendering texture
        batcher = new SpriteBatch();
        shadowBatcher = new SpriteBatch();
        shadowBatcher.setColor(0, 0, 0, 0.3f);
        
        //batcher for rendering shadows
        batcher.setProjectionMatrix(cam.combined);
        shadowBatcher.setProjectionMatrix(cam.combined);
	}
	
	public void draw() {
		Gdx.gl.glClearColor(142/255f, 178/255f, 172/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        shadowBatcher.begin();
        shadowBatcher.draw(AssetLoader.playButtonUp, mech.getPlayButton().getBounds().x - mech.getPlayButton().getBounds().radius + shadowIndent, mech.getPlayButton().getBounds().y - mech.getPlayButton().getBounds().radius + shadowIndent, (float)(mech.getPlayButton().getBounds().radius * 2), (float)(mech.getPlayButton().getBounds().radius * 2));
        mech.getFirstCorner().drawShadow(shadowBatcher);
        mech.getSecondCorner().drawShadow(shadowBatcher);
        mech.getThirdCorner().drawShadow(shadowBatcher);
        mech.getFourthCorner().drawShadow(shadowBatcher);
        mech.getTopPanel().drawShadow(shadowBatcher);
        shadowBatcher.end();
                
        batcher.begin();
        mech.getFirstCorner().draw(batcher);
        mech.getSecondCorner().draw(batcher);
        mech.getThirdCorner().draw(batcher);
        mech.getFourthCorner().draw(batcher);
        mech.getPlayButton().draw(batcher);
        mech.getTopPanel().draw(batcher);
        mech.getFTP().draw(batcher);
        batcher.end();
	}
}
