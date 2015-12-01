package com.frobplugins.platformer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class SoundManager {
	
	public static Music theme;
	public static Music LevelScreen;
	public static Music Song_Level1;
	
	
	public static void create() {
	
		theme = Gdx.audio.newMusic(Gdx.files.internal("sounds/Theme LOP.mp3"));
		Song_Level1 = Gdx.audio.newMusic(Gdx.files.internal("sounds/Song_Level1.mp3"));
	
	}
	
	public static void StopTheme(){
		theme.stop();
	}
	public static void StopLevel1(){
		Song_Level1.stop();
	}

	public void dispose() {
		
	}
}
