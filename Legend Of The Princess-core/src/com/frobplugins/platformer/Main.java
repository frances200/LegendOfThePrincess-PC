package com.frobplugins.platformer;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main extends Game{
	SpriteBatch batch;
	Texture img;
    public static OrthographicCamera camera;
    public static OrthographicCamera b2dcam;
    private Image background;

	@Override
	public void create () {
        Assets.loadAssets();
        batch = new SpriteBatch();
        setScreen(new SplashScreen(this));
	}
}
