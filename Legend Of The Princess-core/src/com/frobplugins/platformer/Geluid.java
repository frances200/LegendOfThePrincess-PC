package com.frobplugins.platformer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Geluid implements Screen{
	
	Main main;
	
	public static int Volume = 100;
	private OrthographicCamera camera;
    private BitmapFont font;
    private FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("arial.ttf"));
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
	
	
    public Geluid(Main main){
        this.main = main;
    }
    
	@Override
	public void show() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 320, 240);
        camera.update();
        parameter.borderColor = Color.WHITE;
        parameter.color = Color.BLACK;
        parameter.borderWidth = 1;
        parameter.size = 18;
        font = generator.generateFont(parameter);
        Volume();
	        
	}

	@Override
	public void render(float arg0) {
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        Volume();
        main.batch.setProjectionMatrix(camera.combined);
        main.batch.begin();
        
        	main.batch.draw(Assets.geluid, 0, 0);
        
        	if(Volume == 10){
        		main.batch.draw(Assets.volume_10, 70, 130);
        		SoundManager.theme.setVolume(0.1f);
        	} else if(Volume == 20){
        		main.batch.draw(Assets.volume_20, 70, 130);
        		SoundManager.theme.setVolume(0.2f);
        	} else if(Volume == 30){
        		main.batch.draw(Assets.volume_30, 70, 130);
        		SoundManager.theme.setVolume(0.3f);
        	} else if(Volume == 40){
        		main.batch.draw(Assets.volume_40, 70, 130);
        		SoundManager.theme.setVolume(0.4f);
        	} else if(Volume == 50){
        		main.batch.draw(Assets.volume_50, 70, 130);
        		SoundManager.theme.setVolume(0.5f);
        	} else if(Volume == 60){
        		main.batch.draw(Assets.volume_60, 70, 130);
        		SoundManager.theme.setVolume(0.6f);
        	} else if(Volume == 70){
        		main.batch.draw(Assets.volume_70, 70, 130);
        		SoundManager.theme.setVolume(0.7f);
        	} else if(Volume == 80){
        		main.batch.draw(Assets.volume_80, 70, 130);
        		SoundManager.theme.setVolume(0.8f);
        	} else if(Volume == 90){
        		main.batch.draw(Assets.volume_90, 70, 130);
        		SoundManager.theme.setVolume(0.9f);
        	} else if(Volume == 100){
        		main.batch.draw(Assets.volume_100, 70, 130);
        		SoundManager.theme.setVolume(1f);
        	} 
        	
        	font.draw(main.batch, "Volume:", 0, 161.25F);
        	//font.draw(main.batch, "Geluid", 220, 300);
        	//font.draw(main.batch, "Overig", 240, 160);
        main.batch.end();
		
        clickListener();
	}

    public void clickListener(){
        if(Gdx.input.justTouched()){
            if(Gdx.input.getX() >= 140 && Gdx.input.getX() <= 190
                    && Gdx.input.getY() >= 160 && Gdx.input.getY() <= 290){
            	System.out.println("test");
            	Volume = 10;
            }
        }
    }
    
    public void Volume() {
    	
    }

	@Override
	public void resize(int width, int height) {
		
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
