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
	@Override
	public void beginContact(Contact arg0) {
		Fixture fa = arg0.getFixtureA();
        Fixture fb = arg0.getFixtureB();
        
        if(fa.getUserData() != null && fa.getUserData().equals("Player")){
        	if(fb.getUserData() != null && fb.getUserData().equals("Door1")){
        		door1 = true;
        	}
        	if(fb.getUserData() != null && fb.getUserData().equals("Briefje")){
        		System.out.println("oke");
        	}
        }
        if(fb.getUserData() != null && fb.getUserData().equals("Player")){
        	if(fa.getUserData() != null && fa.getUserData().equals("Door1")){
        		door1 = true;
        	}
        	if(fa.getUserData() != null && fa.getUserData().equals("Briefje")){
        		System.out.println("oke");
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
        		System.out.println("oke");
        	}
        }
        if(fb.getUserData() != null && fb.getUserData().equals("Player")){
        	if(fa.getUserData() != null && fa.getUserData().equals("Door1")){
        		door1 = false;
        	}
        	if(fa.getUserData() != null && fa.getUserData().equals("Briefje")){
        		System.out.println("oke");
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
