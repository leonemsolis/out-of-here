package com.doublew.outofhere.Support;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	
	public static Texture logoTexture, texture, textureHD, texture1HD;
	public static TextureRegion logo, soundButtonUp, soundButtonDown, 
	soundButtonOffUp, soundButtonOffDown, soundButtonSoundUp, soundButtonSoundDown,
	soundButtonMusicUp, soundButtonMusicDown, helpButtonUp, helpButtonDown, lightOnUp, lightOnDown, lightOffUp, lightOffDown,
	playButtonUp, playButtonDown, topPanel, cornerPanel1, cornerPanel2, cornerPanel3, cornerPanel4,
	pauseButtonUp, pauseButtonDown, floatingTopPanel, ballTexture, arrowUp, arrowDown, arrowLeft, arrowRight;
	//creating extra topPanel, for shadowing, because colors of circle and this panel is different, it causes different color of shadow 
	public static TextureRegion shadowTopPanel, shadowCornerPanel1, shadowCornerPanel2, shadowCornerPanel3, shadowCornerPanel4, wall, shadowWall;
	public static TextureRegion GOFTP, GOFBP, GOFLP, GOFRP, GOFPE;
	public static TextureRegion restartButtonUp, restartButtonDown, homeButtonUp, homeButtonDown, steady, rateButtonUp, rateButtonDown, homeBall;
	public static Preferences soundPrefs, scorePrefs;
	public static BitmapFont scoreFont, helpFont;
	public static Music music;
	public static Sound wallClosing, wallClosed, death1, death2, shifting, point;
	
	public static void load() {
		logoTexture = new Texture(Gdx.files.internal("data/textures/logow.png"));
		logoTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		texture = new Texture(Gdx.files.internal("data/textures/texture.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		textureHD = new Texture(Gdx.files.internal("data/textures/textureHD.png"));
		textureHD.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		texture1HD = new Texture(Gdx.files.internal("data/textures/texture1HD.png"));
		texture1HD.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		logo = new TextureRegion(logoTexture, 0, 0, 512, 128);
	
		soundButtonUp = new TextureRegion(texture1HD, 0, 0, 500, 500);
		soundButtonDown = new TextureRegion(texture1HD, 0, 500, 500, 500);
		soundButtonOffUp = new TextureRegion(texture1HD, 500, 0, 500, 500);
		soundButtonOffDown = new TextureRegion(texture1HD, 500, 500, 500, 500);
		soundButtonSoundUp = new TextureRegion(texture1HD, 1000, 0, 500, 500);
		soundButtonSoundDown = new TextureRegion(texture1HD, 1000, 500, 500, 500);
		soundButtonMusicUp = new TextureRegion(texture1HD, 1500, 0, 500, 500);
		soundButtonMusicDown = new TextureRegion(texture1HD, 1500, 500, 500, 500);
		
		soundButtonUp.flip(false, true);
		soundButtonDown.flip(false, true);
		soundButtonOffUp.flip(false, true);
		soundButtonOffDown.flip(false, true);
		soundButtonSoundUp.flip(false, true);
		soundButtonSoundDown.flip(false, true);
		soundButtonMusicUp.flip(false, true);
		soundButtonMusicDown.flip(false, true);
		
		helpButtonUp = new TextureRegion(texture1HD, 1500, 1000, 500, 500);
		helpButtonDown = new TextureRegion(texture1HD, 1500, 1500, 500, 500);
		
		helpButtonUp.flip(false, true);
		helpButtonDown.flip(false, true);
		
		pauseButtonUp = new TextureRegion(texture, 96, 0, 24, 24);
		pauseButtonDown = new TextureRegion(texture, 96, 24, 24, 24);
		
		pauseButtonUp.flip(false, true);
		pauseButtonDown.flip(false, true);
		
		topPanel = new TextureRegion(texture, 0, 96, 136, 24);
		shadowTopPanel = new TextureRegion(texture, 0, 120, 136, 24);
		
		floatingTopPanel = new TextureRegion(texture, 136, 96, 112, 24);
		floatingTopPanel.flip(false, true);
		
		playButtonUp = new TextureRegion(textureHD, 0, 0, 500, 500);
		playButtonDown = new TextureRegion(textureHD, 0, 500, 500, 500);
		
		cornerPanel1 = new TextureRegion(texture, 0, 144, 44, 44);
		cornerPanel1.flip(false, true);
		
		cornerPanel2 = new TextureRegion(texture, 88, 144, 44, 44);
		cornerPanel2.flip(false, false);
		
		cornerPanel3 = new TextureRegion(texture, 88, 144, 44, 44);
		cornerPanel3.flip(false, true);
		
		cornerPanel4 = new TextureRegion(texture, 0, 144, 44, 44);
		cornerPanel4.flip(false, false);
		
		shadowCornerPanel1 = new TextureRegion(texture, 44, 144, 44, 44);
		shadowCornerPanel1.flip(false, true);
		
		shadowCornerPanel2 = new TextureRegion(texture, 44, 144, 44, 44);
		shadowCornerPanel2.flip(true, true);
		
		shadowCornerPanel3 = new TextureRegion(texture, 44, 144, 44, 44);
		shadowCornerPanel3.flip(true, false);
		
		shadowCornerPanel4 = new TextureRegion(texture, 44, 144, 44, 44);
		shadowCornerPanel4.flip(false, false);
		
		ballTexture = new TextureRegion(texture, 0, 500, 500, 500);
		
		arrowUp = new TextureRegion(textureHD, 500, 0, 500, 500);
		arrowUp.flip(false, true);
		arrowDown = new TextureRegion(textureHD, 500, 0, 500, 500);
		arrowRight = new TextureRegion(textureHD, 500, 500, 500, 500);
		arrowLeft = new TextureRegion(textureHD, 500, 500, 500, 500);
		arrowLeft.flip(true, false);
		
		
		wall = new TextureRegion(texture, 0, 0, 1, 1);
		shadowWall = new TextureRegion(texture, 0, 120, 1, 1);
		
		GOFTP = new TextureRegion(texture, 0, 100, 1, 1);
		GOFLP = new TextureRegion(texture, 0, 100, 1, 1);
		GOFBP = new TextureRegion(texture, 0, 100, 1, 1);
		GOFRP = new TextureRegion(texture, 0, 100, 1, 1);
		GOFPE = new TextureRegion(texture, 0, 0, 1, 1); //for underline panels
		
		restartButtonUp = new TextureRegion(texture1HD, 500, 1000, 500, 500);
		restartButtonDown = new TextureRegion(texture1HD, 500, 1500, 500, 500);
		restartButtonUp.flip(false, true);
		restartButtonDown.flip(false, true);
		
		homeButtonUp = new TextureRegion(texture1HD, 0, 1000, 500, 500);
		homeButtonDown = new TextureRegion(texture1HD, 0, 1500, 500, 500);
		homeButtonUp.flip(false, true);
		homeButtonDown.flip(false, true);
		
		lightOnUp = new TextureRegion(texture, 192, 0, 24, 24);
		lightOnDown = new TextureRegion(texture, 192, 24, 24, 24);
		lightOffUp = new TextureRegion(texture, 216, 0, 24, 24);
		lightOffDown = new TextureRegion(texture, 216, 24, 24, 24);
		
		lightOnUp.flip(false, true);
		lightOnDown.flip(false, true);
		lightOffUp.flip(false, true);
		lightOffDown.flip(false, true);
		
		
		steady = new TextureRegion(texture, 24, 48, 24, 24);
		steady.flip(false, true);
		
		rateButtonUp = new TextureRegion(texture1HD, 1000, 1000, 500, 500);
		rateButtonUp.flip(false, true);
		rateButtonDown = new TextureRegion(texture1HD, 1000, 1500, 500, 500);
		rateButtonDown.flip(false, true);
		
		homeBall = new TextureRegion(texture, 500, 500, 500, 500);
		homeBall.flip(false, true);
		
		scoreFont = new BitmapFont(Gdx.files.internal("data/fonts/128.fnt"));
		scoreFont.setScale(.3f / 1.5f, -.15f / 1.5f);
		

		helpFont = new BitmapFont(Gdx.files.internal("data/fonts/128.fnt"));
		helpFont.setScale(.3f / 2.3f, -.15f / 2.3f);
		
		music = Gdx.audio.newMusic(Gdx.files.internal("data/music/GameTune.ogg"));
		music.setLooping(true);
		music.setVolume(1f);
		
		shifting = Gdx.audio.newSound(Gdx.files.internal("data/sounds/shifting.wav"));
		point = Gdx.audio.newSound(Gdx.files.internal("data/sounds/point.wav"));
		death1 = Gdx.audio.newSound(Gdx.files.internal("data/sounds/death1.wav"));
		death2 = Gdx.audio.newSound(Gdx.files.internal("data/sounds/death2.wav"));
		wallClosed = Gdx.audio.newSound(Gdx.files.internal("data/sounds/wallclosed.wav"));
		wallClosing = Gdx.audio.newSound(Gdx.files.internal("data/sounds/wallclosing.wav"));
		
		/////////////////////////////////////////???
		
		soundPrefs = Gdx.app.getPreferences("OOHSound");
		scorePrefs = Gdx.app.getPreferences("OOHScore");
		
		

		if (!scorePrefs.contains("highScore")) {
			scorePrefs.putInteger("highScore", 0);
			scorePrefs.flush();
		}
		if(!soundPrefs.contains("soundSettings")){
			soundPrefs.putInteger("soundSettings", 1);
			soundPrefs.flush();
		}
	}
	
	public static void setHighScore(int x) {
		scorePrefs.putInteger("highScore", x);
		scorePrefs.flush();
	}
	
	public static int getHighScore() {
		return scorePrefs.getInteger("highScore");
	}
	
	public static void setSoundSettings(int x) {
		soundPrefs.putInteger("soundSettings", x);
		soundPrefs.flush();
	}
	
	public static int getSoundSettings() {
		return soundPrefs.getInteger("soundSettings");
	}
	
	public static void setMusic() {
		if(soundPrefs.getInteger("soundSettings") == 1 || soundPrefs.getInteger("soundSettings") == 3) {
			music.play();
		}else {
			music.stop();
		}
	}
	
	//plays sounds or mute, based on setting
	public static void playSound(int id) {
		if(soundPrefs.getInteger("soundSettings") == 1 || soundPrefs.getInteger("soundSettings") == 2) {
			switch (id) {
				case 0: death1.play(); break;
				case 1: death2.play(); break;
				case 2: point.play(); break;
				case 3: shifting.play(0.05f); break;
				case 4: wallClosing.play(); break;
				case 5: wallClosed.play(); break;
			}
		}
	}

	
	public static void dispose() {
		logoTexture.dispose();
		texture.dispose();
		texture1HD.dispose();
		textureHD.dispose();
		scoreFont.dispose();
		helpFont.dispose();
		music.dispose();
		death1.dispose();
		death2.dispose();
		shifting.dispose();
		point.dispose();
		wallClosed.dispose();
		wallClosing.dispose();
	}
}
