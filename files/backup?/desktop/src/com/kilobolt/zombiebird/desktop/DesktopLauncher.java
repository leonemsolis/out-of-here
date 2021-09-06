package com.kilobolt.zombiebird.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "ZombieBird";
		config.useGL30 = false;
		config.width = 272;
		config.height = 408;
		new LwjglApplication(new com.kilobolt.ZombieBird.ZBGame(), config);
	}
}
