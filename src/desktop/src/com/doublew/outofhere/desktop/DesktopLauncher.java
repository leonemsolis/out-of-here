package com.doublew.outofhere.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.doublew.outofhere.Main.OOHGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Out of here";
		int sr = 1;
		
		switch (sr) {
		case 0:
			config.width = 240;
			config.height =	360;
			break;

		case 1:
			config.width = 360;
			config.height =	480;
			break;
			
		case 2:
			config.width = 540;
			config.height =	960;
			break;
		}
		 
		new LwjglApplication(new OOHGame(), config);
	}
}
