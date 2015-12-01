package com.frobplugins.platformer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class DeathLevel1 implements Screen{
	
    Stage stage = new Stage(new StretchViewport(320, 240));
	Main main;

    public static Image splashImage;
    private Viewport viewport;
	private OrthographicCamera camera;
    private BitmapFont font;
    private FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("arial.ttf"));
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    private SpriteBatch batch = new SpriteBatch();
	
	
    public DeathLevel1(Main main){
        this.main = main;
    }
    
	@Override
	public void show() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 320, 240);
        camera.update();
        splashImage = new Image(Assets.DeathLevel1);
        stage.addActor(splashImage);
        splashImage.setPosition(0, 0);
        splashImage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(0f)));
        viewport = new FitViewport(320, 240, camera);
        parameter.borderColor = Color.WHITE;
        parameter.color = Color.BLACK;
        parameter.borderWidth = 2;
        parameter.size = 48;
        font = generator.generateFont(parameter);
		CollisionListener.spikes_keuken1 = false;
		CollisionListener.spikes_keuken2 = false;
		CollisionListener.spikes_keuken3 = false;
		CollisionListener.spikes_keuken4 = false;
		CollisionListener.spikes_werkplek1 = false;
		CollisionListener.spikes_werkplek2 = false;
		CollisionListener.spikes_woonkamer1 = false;
		CollisionListener.spikes_woonkamer2 = false;
		CollisionListener.spikes_woonkamer3 = false;
	}

	@Override
	public void render(float delta) {
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        stage.getViewport().apply();
        stage.act();
        stage.draw();
        batch.begin();
        	font.draw(batch, "Restart", 220, 300);
        batch.end();
        
        clickListener();
		
	}

    public void clickListener(){
        if(Gdx.input.justTouched()){
        	if(Gdx.input.getX() >= 220 && Gdx.input.getX() <= 420
                    && Gdx.input.getY() >= 325 && Gdx.input.getY() <= 405){
                ((Game) Gdx.app.getApplicationListener()).setScreen(new Map1());
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
