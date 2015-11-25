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

public class Credits implements Screen{
	
    Stage stage = new Stage(new StretchViewport(320, 240));
	Main main;

    public static Image splashImage;
    private Viewport viewport;
	private OrthographicCamera camera;
    private BitmapFont font;
    private FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("arial.ttf"));
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
	
	
    public Credits(Main main){
        this.main = main;
    }
    
	@Override
	public void show() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 640, 640);
        camera.update();
        splashImage = new Image(Assets.credits);
        stage.addActor(splashImage);
        splashImage.setPosition(0, 0);
        splashImage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(0.5f)));
        viewport = new FitViewport(320, 240, camera);
        parameter.borderColor = Color.WHITE;
        parameter.color = Color.BLACK;
        parameter.borderWidth = 2;
        parameter.size = 48;
        font = generator.generateFont(parameter);
		
	}

	@Override
	public void render(float arg0) {
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        stage.getViewport().apply();
        stage.act();
        stage.draw();
        main.batch.begin();
        	font.draw(main.batch, "Dit spel is gemaakt door", 0, 300);
        	font.draw(main.batch, " Francesco Gabrielle", 0, 200);
        	font.draw(main.batch, "[TERUG]", 0, 67);
        main.batch.end();
		
	}

    public void clickListener(){
        if(Gdx.input.justTouched()){
            if(Gdx.input.getX() >= 220 && Gdx.input.getX() <= 420
                    && Gdx.input.getY() >= 310 && Gdx.input.getY() <= 400){
                ((Game) Gdx.app.getApplicationListener()).setScreen(new LevelScreen());
            }
        }
        if(Gdx.input.justTouched()){
        	if(Gdx.input.getX() >= 0 && Gdx.input.getX() <= 200
                    && Gdx.input.getY() >= 565 && Gdx.input.getY() <= 640){
        		MainMenu.hasStartedTheme = true;
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenu(main));
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

}
