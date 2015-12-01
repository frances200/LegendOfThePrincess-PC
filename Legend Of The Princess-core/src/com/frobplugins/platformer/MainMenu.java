package com.frobplugins.platformer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Gebruiker on 7-10-2015.
 */
public class MainMenu implements Screen {

    Stage stage = new Stage(new StretchViewport(320, 240));
    Main main;
    public static Image splashImage;
    private Viewport viewport;
    private OrthographicCamera camera;
    private BitmapFont font;
    private FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("arial.ttf"));
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    
    public static boolean hasStartedTheme = false;

    public MainMenu(Main main){
        this.main = main;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 320, 240);
        camera.update();
        splashImage = new Image(Assets.mainmenu);
        stage.addActor(splashImage);
        splashImage.setPosition(0, 0);
        splashImage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(0.5f)));
        viewport = new FitViewport(320, 240, camera);
        parameter.borderColor = Color.WHITE;
        parameter.color = Color.BLACK;
        parameter.borderWidth = 2;
        parameter.size = 48;
        font = generator.generateFont(parameter);
        try{
        	if(SoundManager.Song_Level1.isPlaying()){
        		SoundManager.Song_Level1.setLooping(false);
        		SoundManager.Song_Level1.stop();
        		SoundManager.Song_Level1.dispose();
        		SoundManager.create();
        		SoundManager.theme.play();
        	}
        }catch(Exception e){
        	e.printStackTrace();
        }
        if(hasStartedTheme == false) {
        	SoundManager.create();
            SoundManager.theme.setLooping(true);
        	SoundManager.theme.play();
        	hasStartedTheme=true;
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        stage.getViewport().apply();
        stage.act();
        stage.draw();
        main.batch.begin();
            font.draw(main.batch, "Spelen", 230, 303);
            font.draw(main.batch, "Opties", 230, 208);
            font.draw(main.batch, "Credits", 230, 110);
        main.batch.end();

        clickListener();
    }

    public void clickListener(){
        if(Gdx.input.justTouched()){
            if(Gdx.input.getX() >= 220 && Gdx.input.getX() <= 420
                    && Gdx.input.getY() >= 310 && Gdx.input.getY() <= 400){
                ((Game) Gdx.app.getApplicationListener()).setScreen(new LevelScreen());
            }
        }
        if(Gdx.input.justTouched()){
            if(Gdx.input.getX() >= 220 && Gdx.input.getX() <= 420
                    && Gdx.input.getY() >= 410 && Gdx.input.getY() <= 500){
                ((Game) Gdx.app.getApplicationListener()).setScreen(new Options(main));
            }
        }
        if(Gdx.input.justTouched()){
            if(Gdx.input.getX() >= 220 && Gdx.input.getX() <= 420
                    && Gdx.input.getY() >= 510 && Gdx.input.getY() <= 600){
            	System.out.println("hai");
                ((Game) Gdx.app.getApplicationListener()).setScreen(new Credits(main));
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        stage.getViewport().update(width, height, false);
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}
