package com.doublew.outofhere.ScreenContent;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.doublew.outofhere.Support.AssetLoader;
import com.doublew.outofhere.Support.SRS;

public class HelpRenderer {
	
	private OrthographicCamera cam;
	private SpriteBatch batcher, shadowBatcher;
	private HelpMechanism mech;
	private String s1, s2, s3, s4, s5;
	
	public HelpRenderer(HelpMechanism mech) {
		this.mech = mech;
		
		cam = new OrthographicCamera();
        cam.setToOrtho(true, SRS.gameWidth, SRS.gameHeight);
        
        //batcher for rendering texture
        batcher = new SpriteBatch();
        shadowBatcher = new SpriteBatch();
        shadowBatcher.setColor(0, 0, 0, 0.3f);
        
        //batcher for rendering shadows
        batcher.setProjectionMatrix(cam.combined);
        shadowBatcher.setProjectionMatrix(cam.combined);
        
        s1 = "CLICK ON THE CIRCLE";
		s2 = "TO SELECT THE DIRECTION";
		s3 = "EACH DIRECTION WILL BE";
		s4 = "REPEATED ONLY ONCE";
		s5 = "KEEP CALM!";
	}
	
	public void draw() {
		Gdx.gl.glClearColor(142/255f, 178/255f, 172/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shadowBatcher.begin();
        mech.getButton().drawShadow(shadowBatcher);
        shadowBatcher.end();
        
        batcher.begin();
        AssetLoader.helpFont.draw(batcher, s1, (136 / 2f) - (AssetLoader.helpFont.getBounds(s1+"").width / 2f), (24 / 2f) + ((AssetLoader.helpFont.getBounds(s1+"").height) / 2f) * 1f);
        AssetLoader.helpFont.draw(batcher, s2, (136 / 2f) - (AssetLoader.helpFont.getBounds(s2+"").width / 2f), ((24 / 2f) + ((AssetLoader.helpFont.getBounds(s2+"").height) / 2f)) * 2f);
        AssetLoader.helpFont.draw(batcher, s3, (136 / 2f) - (AssetLoader.helpFont.getBounds(s3+"").width / 2f), ((24 / 2f) + ((AssetLoader.helpFont.getBounds(s3+"").height) / 2f)) * 3f);
        AssetLoader.helpFont.draw(batcher, s4, (136 / 2f) - (AssetLoader.helpFont.getBounds(s4+"").width / 2f), ((24 / 2f) + ((AssetLoader.helpFont.getBounds(s4+"").height) / 2f)) * 4f);
        AssetLoader.helpFont.draw(batcher, s5, (136 / 2f) - (AssetLoader.helpFont.getBounds(s5+"").width / 2f), SRS.gameHeight - ((24 / 2f) + ((AssetLoader.helpFont.getBounds(s1+"").height) / 2f)) + (AssetLoader.helpFont.getBounds(s1+"").height));
        mech.getButton().draw(batcher);
        batcher.end();
	}
}
