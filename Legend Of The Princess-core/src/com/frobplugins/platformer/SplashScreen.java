package com.frobplugins.platformer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Gebruiker on 7-10-2015.
 */

public class SplashScreen implements Screen {

    Main main;
    public static Image splashImage;
    Stage stage;
    private Viewport viewport;
    private OrthographicCamera camera;

    public SplashScreen(Main main){
        this.main = main;
    }

    public void dispose() {
        stage.dispose();
    }

    public void hide() {
        dispose();
    }

    public void pause() {

    }

    @Override
    public void render(float deltaTime) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        stage.getViewport().apply();
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        stage.getViewport().update(width, height, false);
    }

    public void resume() {

    }

    public void show() {
        camera = new OrthographicCamera();
        camera.setToOrtho(true, 320, 240);
        camera.update();
        LoadImages();
        LoadStage();
        stage.addActor(splashImage);
        splashImage.setPosition(0, 0);
        splashImage.addAction(Actions.sequence(Actions.alpha(0)
                , Actions.fadeIn(2f), Actions.delay(2), Actions.fadeOut(2f), Actions.run(new Runnable() {
            public void run() {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenu(main));
            }
        })));

        viewport = new FitViewport(320, 240, camera);
    }
    public void LoadImages(){
        splashImage = new Image(Assets.splash);
    }

    public void LoadStage(){
        stage = new Stage(new StretchViewport(1920, 1080));
    }
}
