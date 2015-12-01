package com.frobplugins.platformer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class LevelScreen implements Screen,InputProcessor{
	World world = new World(new Vector2(0, 0), true);
	private Box2DDebugRenderer b2dr;
	private OrthographicCamera camera;
	private OrthographicCamera cam;
	private TiledMap map;
	private OrthogonalTiledMapRenderer tmr;
	float tileSize = 32;
	private Body player;
	private Body collisionLayer;
	private Body level1Collider;
	Texture playerTexture;
	Sprite sprite;
	private float torque = 0;
	private float PPM = 100;
	boolean enableLevel1 = false;
	Texture loadLevel1;
	Sprite sprite1;
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);
		b2dr = new Box2DDebugRenderer();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 640, 640);
		createCollisionListener();
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		
		bdef.position.set(37.5f * tileSize , 61.5f * tileSize );
		bdef.type = BodyType.DynamicBody;
		player = world.createBody(bdef);
		
		PolygonShape shape = new PolygonShape();
		PolygonShape sjeep = new PolygonShape();
		sjeep.setAsBox(16, 16);
		shape.setAsBox(15, 15);
		fdef.shape = shape;
		player.createFixture(fdef);
		playerTexture = new Texture(Gdx.files.internal("PlayerHead.png"));
		sprite = new Sprite(playerTexture);
		loadLevel1 = new Texture(Gdx.files.internal("LevelScreen/loadLevel1.png"));
		sprite1 = new Sprite(loadLevel1);
		
		//////////////////////////////////////////
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 640, 640);
		map = new TmxMapLoader().load("LevelScreen/Level1.tmx");
		tmr = new OrthogonalTiledMapRenderer(map);
		
		TiledMapTileLayer PadTeLopen = (TiledMapTileLayer) map.getLayers().get("Collisions pad te lopen");
		tileSize = PadTeLopen.getTileWidth();
		for(int row=0;row<PadTeLopen.getHeight();row++){
			for(int col=0;col<PadTeLopen.getWidth();col++){
				Cell cell = PadTeLopen.getCell(col, row);
				
				if(cell == null) {
					continue;
				}
				if(cell.getTile() == null){
					continue;
				}
				bdef.type = BodyType.StaticBody;
				bdef.position.set((col + 0.5f) * tileSize ,
								  (row + 0.5f) * tileSize );
				collisionLayer = world.createBody(bdef);
				fdef.friction = 0;
				fdef.shape = sjeep;
				fdef.isSensor = false;
				collisionLayer.createFixture(fdef);
			}
		}
		TiledMapTileLayer Level1 = (TiledMapTileLayer) map.getLayers().get("Enter Level 1 Collider");
		tileSize = Level1.getTileWidth();
		for(int row=0;row<Level1.getHeight();row++){
			for(int col=0;col<Level1.getWidth();col++){
				Cell cell = Level1.getCell(col, row);
				
				if(cell == null) {
					continue;
				}
				if(cell.getTile() == null){
					continue;
				}
				bdef.type = BodyType.StaticBody;
				bdef.position.set((col + 0.5f) * tileSize ,
						(row + 0.5f) * tileSize );
				level1Collider = world.createBody(bdef);
				fdef.friction = 0;
				fdef.shape = sjeep;
				fdef.isSensor = true;
				level1Collider.createFixture(fdef);
			}
		}
	}

	@Override
	public void render(float delta) {
		update(delta);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		camera.position.set(player.getPosition().x, player.getPosition().y, 0);
		camera.update();
		float bgX = camera.position.x - 320;
		float bgY = camera.position.y - 320;
		cam.position.x = camera.position.x;
		cam.position.y = camera.position.y;
		
		tmr.getBatch().begin();
			tmr.getBatch().draw(Assets.bg, bgX, bgY);
		tmr.getBatch().end();
		cam.update();
		tmr.setView(cam);
		tmr.render();
		
		tmr.getBatch().begin();
			tmr.getBatch().draw(sprite, player.getPosition().x - 16, player.getPosition().y - 16);
			if(enableLevel1){
				tmr.getBatch().draw(sprite1, player.getPosition().x - 225, player.getPosition().y - 125);
			}
		tmr.getBatch().end();
		if(!enableLevel1){
			if(Gdx.input.isKeyPressed(Keys.LEFT)){
				player.setLinearVelocity(-100f,0f);
			}
			if(Gdx.input.isKeyPressed(Keys.RIGHT)){
				player.setLinearVelocity(100f,0f);
			}	
	        if(Gdx.input.isKeyPressed(Keys.UP)){
	        	player.applyForceToCenter(0f,10000f,true);
			}
	        if(Gdx.input.isKeyPressed(Keys.DOWN)){
	        	player.applyForceToCenter(0f, -10000f, true);
			}
	        if(!Gdx.input.isKeyPressed(Keys.DOWN) && !Gdx.input.isKeyPressed(Keys.LEFT) && !Gdx.input.isKeyPressed(Keys.RIGHT) && !Gdx.input.isKeyPressed(Keys.UP)){
	        	player.setLinearVelocity(0, 0);
	        }
		}else{
			if(Gdx.input.isKeyJustPressed(Keys.ENTER)){
				try{
	        	SoundManager.theme.setLooping(false);
	        	SoundManager.theme.stop();
	        	SoundManager.theme.dispose();
	        	System.out.println(SoundManager.theme.isPlaying());
				}catch(Exception e){
					e.printStackTrace();
				}
	        	((Game) Gdx.app.getApplicationListener()).setScreen(new Map1());
	        }
		}
	}
	
	public void update(float delta){
		world.step(delta, 1, 1);
	}
	
	private void createCollisionListener() {
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();
                boolean sensorA = fixtureA.isSensor();
                boolean sensorB = fixtureB.isSensor();
                if(sensorA && sensorB){
                	return;
                }else{
                	if(fixtureA == player.getFixtureList().get(0) && fixtureB == level1Collider.getFixtureList().get(0)){
                		enableLevel1 = true;
                	}
                	if(fixtureB == player.getFixtureList().get(0) && fixtureA == level1Collider.getFixtureList().get(0)){
                		enableLevel1 = true;
                	}
                }
                System.out.println("Level1: " + enableLevel1);
            }

            @Override
            public void endContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();
                boolean sensorA = fixtureA.isSensor();
                boolean sensorB = fixtureB.isSensor();
                if(sensorA && sensorB){
                	return;
                }else{
                	if(fixtureA == player.getFixtureList().get(0) && fixtureB == level1Collider.getFixtureList().get(0)){
                		enableLevel1 = false;
                	}
                	if(fixtureB == player.getFixtureList().get(0) && fixtureA == level1Collider.getFixtureList().get(0)){
                		enableLevel1 = false;
                	}
                }
                System.out.println("Level1: " + enableLevel1);
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {
            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {
            }

        });
    }

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
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
	public boolean keyDown(int arg0) {
		
		return false;
	}

	@Override
	public boolean keyTyped(char arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	} 
	
	
}
