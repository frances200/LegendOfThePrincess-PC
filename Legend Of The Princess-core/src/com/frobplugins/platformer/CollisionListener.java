package com.frobplugins.platformer;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class CollisionListener implements ContactListener{
	
	Map1 map1 = new Map1();
	public static boolean door1;
	public static boolean canJump;
	public static boolean isShowingBrief = false;
	public static boolean spikes_werkplek1;
	public static boolean spikes_werkplek2;
	public static boolean spikes_keuken1;
	public static boolean spikes_keuken2;
	public static boolean spikes_keuken3;
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
