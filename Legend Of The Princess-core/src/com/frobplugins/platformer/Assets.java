package com.frobplugins.platformer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;

/**
 * Created by Gebruiker on 7-10-2015.
 */
public class Assets {

    public static Texture splash;
    public static Texture bg;

    public static Texture mainmenu;
    public static Texture opties;
    public static Texture credits;

    public static void loadAssets(){
        splash = new Texture(Gdx.files.internal("FrobPlugins_splash.png"));
        bg = new Texture(Gdx.files.internal("maps/bg3.png"));
        
        mainmenu = new Texture(Gdx.files.internal("menu.png"));
        opties = new Texture(Gdx.files.internal("opties.png"));
        credits = new Texture(Gdx.files.internal("credits.png"));
        		
    }
}
