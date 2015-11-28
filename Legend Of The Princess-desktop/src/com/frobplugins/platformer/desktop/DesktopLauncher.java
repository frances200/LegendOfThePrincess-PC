package com.frobplugins.platformer.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.frobplugins.platformer.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Main(), config);
		config.title = "Legend of The Princess ALPHA v0.1";
		config.fullscreen = false;
		config.resizable =  false;
		config.width = 1024;
		config.height = 720;
	}
}