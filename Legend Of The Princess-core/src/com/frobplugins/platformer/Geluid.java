package com.frobplugins.platformer;

import com.badlogic.gdx.Game;
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
	private OrthographicCamera reset_camera;
    private BitmapFont font;
    private FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("arial.ttf"));
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    
    public static boolean MutedBgMusic = false;
    public static boolean MutedSoundEffects = false;
	
	
    public Geluid(Main main){
        this.main = main;
    }
    
	@Override
	public void show() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 320, 240);
        camera.update();
        reset_camera = new OrthographicCamera();
        reset_camera.setToOrtho(false, 640, 640);
        reset_camera.update();
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
        reset_camera.update();
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
        	
        	if(MutedBgMusic == false) {
        		main.batch.draw(Assets.non_muted, 240, 67);
        	} else if(MutedBgMusic == true) {
        		main.batch.draw(Assets.muted, 240, 67);
        	}
        	
        	if(MutedSoundEffects == false) {
        		main.batch.draw(Assets.non_muted, 240, 17);
        	} else if(MutedSoundEffects == true) {
        		main.batch.draw(Assets.muted, 240, 17);
        	}
        	
        	
        	font.draw(main.batch, "Volume:", 0, 161.25F);
        	font.draw(main.batch, "Demp achtergrond muziek:", 0, 100);
        	font.draw(main.batch, "Demp geluidseffecten:", 0, 50);
        	font.draw(main.batch, "[TERUG]", 0, 25);

        main.batch.end();
		
        clickListener();
	}

    public void clickListener(){
        if(Gdx.input.justTouched()){
        	
        	//Changing Volume
            if(Gdx.input.getX() >= 140 && Gdx.input.getX() <= 190
                    && Gdx.input.getY() >= 160 && Gdx.input.getY() <= 290){
            	System.out.println("volume 10");
            	Volume = 10;
            } else if(Gdx.input.getX() >= 191 && Gdx.input.getX() <= 240
                    && Gdx.input.getY() >= 160 && Gdx.input.getY() <= 290){
            	System.out.println("volume 20");
            	Volume = 20;
            } else if(Gdx.input.getX() >= 241 && Gdx.input.getX() <= 290
                    && Gdx.input.getY() >= 160 && Gdx.input.getY() <= 290){
            	System.out.println("volume 30");
            	Volume = 30;
            } else if(Gdx.input.getX() >= 291 && Gdx.input.getX() <= 340
                    && Gdx.input.getY() >= 160 && Gdx.input.getY() <= 290){
            	System.out.println("volume 40");
            	Volume = 40;
            } else if(Gdx.input.getX() >= 341 && Gdx.input.getX() <= 390
                    && Gdx.input.getY() >= 160 && Gdx.input.getY() <= 290){
            	System.out.println("volume 50");
            	Volume = 50;
            } else if(Gdx.input.getX() >= 391 && Gdx.input.getX() <= 440
                    && Gdx.input.getY() >= 160 && Gdx.input.getY() <= 290){
            	System.out.println("volume 60");
            	Volume = 60;
            } else if(Gdx.input.getX() >= 441 && Gdx.input.getX() <= 490
                    && Gdx.input.getY() >= 160 && Gdx.input.getY() <= 290){
            	System.out.println("volume 70");
            	Volume = 70;
            } else if(Gdx.input.getX() >= 491 && Gdx.input.getX() <= 540
                    && Gdx.input.getY() >= 160 && Gdx.input.getY() <= 290){
            	System.out.println("volume 80");
            	Volume = 80;
            } else if(Gdx.input.getX() >= 541 && Gdx.input.getX() <= 590
                    && Gdx.input.getY() >= 160 && Gdx.input.getY() <= 290){
            	System.out.println("volume 90");
            	Volume = 90;
            } else if(Gdx.input.getX() >= 591 && Gdx.input.getX() <= 640
                    && Gdx.input.getY() >= 160 && Gdx.input.getY() <= 290){
            	System.out.println("volume 100");
            	Volume = 100;
            
            //Muting Background Music
            } else if(Gdx.input.getX() >= 480 && Gdx.input.getX() <= 580
                    && Gdx.input.getY() >= 307 && Gdx.input.getY() <= 460){
            	System.out.println("Muted/ unmuted background music.");
            	if(MutedBgMusic == false) {
            		MutedBgMusic = true;
            		SoundManager.theme.pause();
            	} else if(MutedBgMusic == true) {
            		MutedBgMusic = false;
            		SoundManager.theme.play();
            	}
            	
            //Muting Sound Effects coörds arent right yet.
            }else if(Gdx.input.getX() >= 480 && Gdx.input.getX() <= 580
                    && Gdx.input.getY() >= 461 && Gdx.input.getY() <= 594){
            	System.out.println("Muted sound effects.");
            	if(MutedSoundEffects == false) {
            		MutedSoundEffects = true;
            	} else if(MutedSoundEffects == true) {
            		MutedSoundEffects = false;
            	}
            	
            //The [TERUG] button
            } else if(Gdx.input.getX() >= 0 && Gdx.input.getX() <= 160
                    && Gdx.input.getY() >= 575 && Gdx.input.getY() <= 640){
            	main.batch.setProjectionMatrix(reset_camera.combined);
            	((Game) Gdx.app.getApplicationListener()).setScreen(new Options(main));
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
