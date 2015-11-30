package com.frobplugins.platformer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Gebruiker on 7-10-2015.
 */
public class Assets {

    public static Texture splash;
    public static Texture bg;

    public static Texture credits;
    public static Texture mainmenu;
    public static Texture opties;
    public static Texture geluid;
    public static Texture muted;
    public static Texture non_muted;
    public static Texture DeathLevel1;
    
    public static Texture volume_10;
    public static Texture volume_20;
    public static Texture volume_30;
    public static Texture volume_40;
    public static Texture volume_50;
    public static Texture volume_60;
    public static Texture volume_70;
    public static Texture volume_80;
    public static Texture volume_90;
    public static Texture volume_100;
    
    public static Texture briefje;
    public static Sprite sprite_briefje;

    public static void loadAssets(){
        splash = new Texture(Gdx.files.internal("FrobPlugins_splash.png"));
        bg = new Texture(Gdx.files.internal("maps/bg3.png"));
        
        credits = new Texture(Gdx.files.internal("credits.png"));
        mainmenu = new Texture(Gdx.files.internal("menu.png"));
        opties = new Texture(Gdx.files.internal("opties.png"));
        geluid = new Texture(Gdx.files.internal("geluid.png"));
        DeathLevel1 = new Texture(Gdx.files.internal("DeathLevel1.png"));

        muted = new Texture(Gdx.files.internal("muted.png"));
        non_muted = new Texture(Gdx.files.internal("non muted.png"));

        volume_10 = new Texture(Gdx.files.internal("volume/volume_10.png"));
        volume_20 = new Texture(Gdx.files.internal("volume/volume_20.png"));
        volume_30 = new Texture(Gdx.files.internal("volume/volume_30.png"));
        volume_40 = new Texture(Gdx.files.internal("volume/volume_40.png"));
        volume_50 = new Texture(Gdx.files.internal("volume/volume_50.png"));
        volume_60 = new Texture(Gdx.files.internal("volume/volume_60.png"));
        volume_70 = new Texture(Gdx.files.internal("volume/volume_70.png"));
        volume_80 = new Texture(Gdx.files.internal("volume/volume_80.png"));
        volume_90 = new Texture(Gdx.files.internal("volume/volume_90.png"));
        volume_100 = new Texture(Gdx.files.internal("volume/volume_100.png"));
        
        briefje = new Texture(Gdx.files.internal("Brief.png"));
        sprite_briefje = new Sprite(briefje);
        		
    }
}
