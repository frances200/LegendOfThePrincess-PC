package com.frobplugins.platformer;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class CollisionListener implements ContactListener{
	
	Map1 map1 = new Map1();
	public static boolean door1;
	public static boolean door2;
	public static boolean door3;
	public static boolean canJump;
	public static boolean isShowingBrief = false;
	public static boolean spikes_werkplek1;
	public static boolean spikes_werkplek2;
	public static boolean spikes_keuken1;
	public static boolean spikes_keuken2;
	public static boolean spikes_keuken3;
	public static boolean spikes_keuken4;
	public static boolean spikes_woonkamer1;
	public static boolean spikes_woonkamer2;
	public static boolean spikes_woonkamer3;
	
	@Override
	public void beginContact(Contact arg0) {
		Fixture fa = arg0.getFixtureA();
        Fixture fb = arg0.getFixtureB();
        
        if(fa.getUserData() != null && fa.getUserData().equals("Player")){
        	if(fb.getUserData() != null && fb.getUserData().equals("Door1")){
        		door1 = true;
        	}
        	if(fb.getUserData() != null && fb.getUserData().equals("Door2")){
        		door2 = true;
        	}
        	if(fb.getUserData() != null && fb.getUserData().equals("Door3")){
        		door3 = true;
        	}
        	if(fb.getUserData() != null && fb.getUserData().equals("Coin")){
        		Assets.sprite_coin.setPosition(0, 0);
        	}
        	if(fb.getUserData() != null && fb.getUserData().equals("Coin1")){
        		Assets.sprite_coin1.setPosition(0, 0);
        	}
        	if(fb.getUserData() != null && fb.getUserData().equals("Coin2")){
        		Assets.sprite_coin2.setPosition(0, 0);
        	}
        	if(fb.getUserData() != null && fb.getUserData().equals("Coin3")){
        		Assets.sprite_coin3.setPosition(0, 0);
        	}
        	if(fb.getUserData() != null && fb.getUserData().equals("Coin4")){
        		Assets.sprite_coin4.setPosition(0, 0);
        	}
        	if(fb.getUserData() != null && fb.getUserData().equals("Coin5")){
        		Assets.sprite_coin5.setPosition(0, 0);
        	}
        	if(fb.getUserData() != null && fb.getUserData().equals("Coin6")){
        		Assets.sprite_coin6.setPosition(0, 0);
        	}
        	if(fb.getUserData() != null && fb.getUserData().equals("Coin7")){
        		Assets.sprite_coin7.setPosition(0, 0);
        	}
        	if(fb.getUserData() != null && fb.getUserData().equals("Coin8")){
        		Assets.sprite_coin8.setPosition(0, 0);
        	}
        	if(fb.getUserData() != null && fb.getUserData().equals("Coin9")){
        		Assets.sprite_coin9.setPosition(0, 0);
        	}
        	if(fb.getUserData() != null && fb.getUserData().equals("Coin10")){
        		Assets.sprite_coin10.setPosition(0, 0);
        	}
        	if(fb.getUserData() != null && fb.getUserData().equals("Coin11")){
        		Assets.sprite_coin11.setPosition(0, 0);
        	}
        	if(fb.getUserData() != null && fb.getUserData().equals("Coin12")){
        		Assets.sprite_coin12.setPosition(0, 0);
        	}
        	if(fb.getUserData() != null && fb.getUserData().equals("Coin13")){
        		Assets.sprite_coin13.setPosition(0, 0);
        	}
        	if(fb.getUserData() != null && fb.getUserData().equals("Coin14")){
        		Assets.sprite_coin14.setPosition(0, 0);
        	}
        	if(fb.getUserData() != null && fb.getUserData().equals("Coin15")){
        		Assets.sprite_coin15.setPosition(0, 0);
        	}
        	if(fb.getUserData() != null && fb.getUserData().equals("Coin16")){
        		Assets.sprite_coin16.setPosition(0, 0);
        	}
        	if(fb.getUserData() != null && fb.getUserData().equals("Coin17")){
        		Assets.sprite_coin17.setPosition(0, 0);
        	}
        	if(fb.getUserData() != null && fb.getUserData().equals("Coin18")){
        		Assets.sprite_coin18.setPosition(0, 0);
        	}
        	if(fb.getUserData() != null && fb.getUserData().equals("Coin19")){
        		Assets.sprite_coin19.setPosition(0, 0);
        	}
        	if(fb.getUserData() != null && fb.getUserData().equals("Coin20")){
        		Assets.sprite_coin20.setPosition(0, 0);
        	}
        	if(fb.getUserData() != null && fb.getUserData().equals("Briefje")){
        		isShowingBrief=true;
        	}
        	
        	
        	if(fb.getUserData() != null && fb.getUserData().equals("Spikes_werkplek1")){
        		spikes_werkplek1=true;
        	}
        	if(fb.getUserData() != null && fb.getUserData().equals("Spikes_werkplek2")){
        		spikes_werkplek2=true;
        	}
        	if(fb.getUserData() != null && fb.getUserData().equals("Spikes_keuken1")){
        		spikes_keuken1=true;
        	}
        	if(fb.getUserData() != null && fb.getUserData().equals("Spikes_keuken2")){
        		spikes_keuken2=true;
        	}
        	if(fb.getUserData() != null && fb.getUserData().equals("Spikes_keuken3")){
        		spikes_keuken3=true;
        	}
        	if(fb.getUserData() != null && fb.getUserData().equals("Spikes_keuken4")){
        		spikes_keuken4=true;
        	}
        	if(fb.getUserData() != null && fb.getUserData().equals("Spikes_woonkamer1")){
        		spikes_woonkamer1=true;
        	}
        	if(fb.getUserData() != null && fb.getUserData().equals("Spikes_woonkamer2")){
        		spikes_woonkamer2=true;
        	}
        	if(fb.getUserData() != null && fb.getUserData().equals("Spikes_woonkamer3")){
        		spikes_woonkamer3=true;
        	}
        	
        	
        	
        }
        if(fb.getUserData() != null && fb.getUserData().equals("Player")){
        	if(fa.getUserData() != null && fa.getUserData().equals("Door1")){
        		door1 = true;
        	}
        	if(fa.getUserData() != null && fa.getUserData().equals("Door2")){
        		door2 = true;
        	}
        	if(fa.getUserData() != null && fa.getUserData().equals("Door3")){
        		door3 = true;
        	}
        	if(fa.getUserData() != null && fa.getUserData().equals("Briefje")){
        		isShowingBrief=true;
        	}
        	
        	
        	if(fa.getUserData() != null && fa.getUserData().equals("Spikes_werkplek1")){
        		spikes_werkplek1=true;
        	}
        	if(fa.getUserData() != null && fa.getUserData().equals("Spikes_werkplek2")){
        		spikes_werkplek2=true;
        	}
        	if(fa.getUserData() != null && fa.getUserData().equals("Spikes_keuken1")){
        		spikes_keuken1=true;
        	}
        	if(fa.getUserData() != null && fa.getUserData().equals("Spikes_keuken2")){
        		spikes_keuken2=true;
        	}
        	if(fa.getUserData() != null && fa.getUserData().equals("Spikes_keuken3")){
        		spikes_keuken3=true;
        	}
        	if(fa.getUserData() != null && fa.getUserData().equals("Spikes_keuken4")){
        		spikes_keuken4=true;
        	}
        	if(fa.getUserData() != null && fa.getUserData().equals("Spikes_woonkamer1")){
        		spikes_woonkamer1=true;
        	}
        	if(fa.getUserData() != null && fa.getUserData().equals("Spikes_woonkamer2")){
        		spikes_woonkamer2=true;
        	}
        	if(fa.getUserData() != null && fa.getUserData().equals("Spikes_woonkamer3")){
        		spikes_woonkamer3=true;
        	}
        	
        	
        	
        }
        if(fa.getUserData() != null && fa.getUserData().equals("foot")){
        	if(!fb.isSensor()){
        		canJump = true;
        	}
        }
        if(fb.getUserData() != null && fb.getUserData().equals("foot")){
        	if(!fa.isSensor()){
        		canJump = true;
        	}
        }
	}

	@Override
	public void endContact(Contact arg0) {
		Fixture fa = arg0.getFixtureA();
        Fixture fb = arg0.getFixtureB();
        
        if(fa.getUserData() != null && fa.getUserData().equals("Player")){
        	if(fb.getUserData() != null && fb.getUserData().equals("Door1")){
        		door1 = false;
        	}
        	if(fb.getUserData() != null && fb.getUserData().equals("Door2")){
        		door2 = false;
        	}
        	if(fb.getUserData() != null && fb.getUserData().equals("Door3")){
        		door3 = false;
        	}
        	if(fb.getUserData() != null && fb.getUserData().equals("Briefje")){
        		isShowingBrief=false;
        	}
        	
        	
        	
        	if(fb.getUserData() != null && fb.getUserData().equals("Spikes_werkplek1")){
        		spikes_werkplek1=false;
        	}
        	if(fb.getUserData() != null && fb.getUserData().equals("Spikes_werkplek2")){
        		spikes_werkplek2=false;
        	}
        	if(fb.getUserData() != null && fb.getUserData().equals("Spikes_keuken1")){
        		spikes_keuken1=false;
        	}
        	if(fb.getUserData() != null && fb.getUserData().equals("Spikes_keuken2")){
        		spikes_keuken2=false;
        	}
        	if(fb.getUserData() != null && fb.getUserData().equals("Spikes_keuken3")){
        		spikes_keuken3=false;
        	}
        	if(fb.getUserData() != null && fb.getUserData().equals("Spikes_keuken4")){
        		spikes_keuken4=false;
        	}
        	if(fb.getUserData() != null && fb.getUserData().equals("Spikes_woonkamer1")){
        		spikes_woonkamer1=false;
        	}
        	if(fb.getUserData() != null && fb.getUserData().equals("Spikes_woonkamer2")){
        		spikes_woonkamer2=false;
        	}
        	if(fb.getUserData() != null && fb.getUserData().equals("Spikes_woonkamer3")){
        		spikes_woonkamer3=false;
        	}
        	
        	
        	
        }
        if(fb.getUserData() != null && fb.getUserData().equals("Player")){
        	if(fa.getUserData() != null && fa.getUserData().equals("Door1")){
        		door1 = false;
        	}
        	if(fa.getUserData() != null && fa.getUserData().equals("Door2")){
        		door2 = false;
        	}
        	if(fa.getUserData() != null && fa.getUserData().equals("Door3")){
        		door3 = false;
        	}
        	if(fa.getUserData() != null && fa.getUserData().equals("Briefje")){
        		isShowingBrief=false;
        	}
        	
        	
        	
        	
        	if(fa.getUserData() != null && fa.getUserData().equals("Spikes_werkplek1")){
        		spikes_werkplek1=false;
        	}
        	if(fa.getUserData() != null && fa.getUserData().equals("Spikes_werkplek2")){
        		spikes_werkplek2=false;
        	}
        	if(fa.getUserData() != null && fa.getUserData().equals("Spikes_keuken1")){
        		spikes_keuken1=false;
        	}
        	if(fa.getUserData() != null && fa.getUserData().equals("Spikes_keuken2")){
        		spikes_keuken2=false;
        	}
        	if(fa.getUserData() != null && fa.getUserData().equals("Spikes_keuken3")){
        		spikes_keuken3=false;
        	}
        	if(fa.getUserData() != null && fa.getUserData().equals("Spikes_keuken4")){
        		spikes_keuken4=false;
        	}
        	if(fa.getUserData() != null && fa.getUserData().equals("Spikes_woonkamer1")){
        		spikes_woonkamer1=false;
        	}
        	if(fa.getUserData() != null && fa.getUserData().equals("Spikes_woonkamer2")){
        		spikes_woonkamer2=false;
        	}
        	if(fa.getUserData() != null && fa.getUserData().equals("Spikes_woonkamer3")){
        		spikes_woonkamer3=false;
        	}
        	
        	
        	
        }
        if(fa.getUserData() != null && fa.getUserData().equals("foot")){
        	if(!fb.isSensor()){
        		canJump = false;
        	}
        }
        if(fb.getUserData() != null && fb.getUserData().equals("foot")){
        	if(!fa.isSensor()){
        		canJump = false;
        	}
        }
	}

	@Override
	public void postSolve(Contact arg0, ContactImpulse arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preSolve(Contact arg0, Manifold arg1) {
		// TODO Auto-generated method stub
		
	}

}
