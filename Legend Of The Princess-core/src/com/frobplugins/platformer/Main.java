package com.frobplugins.platformer;

import static com.frobplugins.platformer.Variables.PPM;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main extends Game implements ApplicationListener {
	SpriteBatch batch;
	Texture img;
    public static OrthographicCamera camera;
    public static OrthographicCamera b2dcam;

	@Override
	public void create () {
        Assets.loadAssets();
        batch = new SpriteBatch();
        setScreen(new Box2D());
	}

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }
}
