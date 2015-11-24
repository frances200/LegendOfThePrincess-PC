package com.frobplugins.platformer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class SoundManager {
	
	public static Music theme;
	public static Music LevelScreen;
	public static Music Level1;
	

	public static void create() {
	
		theme = Gdx.audio.newMusic(Gdx.files.internal("sounds/Theme LOP.mp3"));
	
	}

	public static void dispose() {
		theme.dispose();
	}
}
