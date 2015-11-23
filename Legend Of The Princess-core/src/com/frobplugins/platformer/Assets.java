package com.frobplugins.platformer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Gebruiker on 7-10-2015.
 */
public class Assets {

    public static Texture splash;
    public static Texture mainmenu;
    public static Texture bg;

    public static void loadAssets(){
        splash = new Texture(Gdx.files.internal("FrobPlugins_splash.png"));
        mainmenu = new Texture(Gdx.files.internal("menu.png"));
        bg = new Texture(Gdx.files.internal("maps/bg3.png"));
    }
}
