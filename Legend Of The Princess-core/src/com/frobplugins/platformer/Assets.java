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
    public static Texture coin;
    public static Texture coin1;
    public static Texture coin2;
    public static Texture coin3;
    public static Texture coin4;
    public static Texture coin5;
    public static Texture coin6;
    public static Texture coin7;
    public static Texture coin8;
    public static Texture coin9;
    public static Texture coin10;
    public static Texture coin11;
    public static Texture coin12;
    public static Texture coin13;
    public static Texture coin14;
    public static Texture coin15;
    public static Texture coin16;
    public static Texture coin17;
    public static Texture coin18;
    public static Texture coin19;
    public static Texture coin20;
    public static Sprite sprite_coin;
    public static Sprite sprite_coin1;
    public static Sprite sprite_coin2;
    public static Sprite sprite_coin3;
    public static Sprite sprite_coin4;
    public static Sprite sprite_coin5;
    public static Sprite sprite_coin6;
    public static Sprite sprite_coin7;
    public static Sprite sprite_coin8;
    public static Sprite sprite_coin9;
    public static Sprite sprite_coin10;
    public static Sprite sprite_coin11;
    public static Sprite sprite_coin12;
    public static Sprite sprite_coin13;
    public static Sprite sprite_coin14;
    public static Sprite sprite_coin15;
    public static Sprite sprite_coin16;
    public static Sprite sprite_coin17;
    public static Sprite sprite_coin18;
    public static Sprite sprite_coin19;
    public static Sprite sprite_coin20;
    
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
        coin = new Texture(Gdx.files.internal("Coin.png"));
        coin1 = new Texture(Gdx.files.internal("Coin.png"));
        coin2 = new Texture(Gdx.files.internal("Coin.png"));
        coin3 = new Texture(Gdx.files.internal("Coin.png"));
        coin4 = new Texture(Gdx.files.internal("Coin.png"));
        coin5 = new Texture(Gdx.files.internal("Coin.png"));
        coin6 = new Texture(Gdx.files.internal("Coin.png"));
        coin7 = new Texture(Gdx.files.internal("Coin.png"));
        coin8 = new Texture(Gdx.files.internal("Coin.png"));
        coin9 = new Texture(Gdx.files.internal("Coin.png"));
        coin10 = new Texture(Gdx.files.internal("Coin.png"));
        coin11 = new Texture(Gdx.files.internal("Coin.png"));
        coin12 = new Texture(Gdx.files.internal("Coin.png"));
        coin13 = new Texture(Gdx.files.internal("Coin.png"));
        coin14 = new Texture(Gdx.files.internal("Coin.png"));
        coin15 = new Texture(Gdx.files.internal("Coin.png"));
        coin16 = new Texture(Gdx.files.internal("Coin.png"));
        coin17 = new Texture(Gdx.files.internal("Coin.png"));
        coin18 = new Texture(Gdx.files.internal("Coin.png"));
        coin19 = new Texture(Gdx.files.internal("Coin.png"));
        coin20 = new Texture(Gdx.files.internal("Coin.png"));
        sprite_coin = new Sprite(coin);
        sprite_coin1 = new Sprite(coin1);
        sprite_coin2 = new Sprite(coin2);
        sprite_coin3 = new Sprite(coin3);
        sprite_coin4 = new Sprite(coin4);
        sprite_coin5 = new Sprite(coin5);
        sprite_coin6 = new Sprite(coin6);
        sprite_coin7 = new Sprite(coin7);
        sprite_coin8 = new Sprite(coin8);
        sprite_coin9 = new Sprite(coin9);
        sprite_coin10 = new Sprite(coin10);
        sprite_coin11 = new Sprite(coin11);
        sprite_coin12 = new Sprite(coin12);
        sprite_coin13 = new Sprite(coin13);
        sprite_coin14 = new Sprite(coin14);
        sprite_coin15 = new Sprite(coin15);
        sprite_coin16 = new Sprite(coin16);
        sprite_coin17 = new Sprite(coin17);
        sprite_coin18 = new Sprite(coin18);
        sprite_coin19 = new Sprite(coin19);
        sprite_coin20 = new Sprite(coin20);
        
        briefje = new Texture(Gdx.files.internal("Brief.png"));
        sprite_briefje = new Sprite(briefje);
        		
    }
}
