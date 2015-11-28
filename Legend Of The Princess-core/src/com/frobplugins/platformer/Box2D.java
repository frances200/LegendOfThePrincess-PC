package com.frobplugins.platformer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import net.dermetfan.gdx.physics.box2d.Box2DMapObjectParser;

public class Box2D implements Screen{
	World world = new World(new Vector2(0, -9.82f), true);
	private Box2DDebugRenderer b2dr;
	private OrthographicCamera camera;
	private OrthographicCamera cam;
	private TiledMap map;
	private OrthogonalTiledMapRenderer tmr;
	float tileSize = 64;
	private Body player;
	private Body ground;
	Texture playerTexture;
	Sprite sprite;
	private float torque = 0;
	private float PPM = 100;
	private boolean canJump = false;
	
	@Override
	public void show() {
		SoundManager.theme.stop();
		b2dr = new Box2DDebugRenderer();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 640, 640);
		
		BodyDef bdef = new BodyDef();
		createCollisionListener();
		FixtureDef fdef = new FixtureDef();
		
		bdef.position.set(2.4f * tileSize  , 22 * tileSize  );
		bdef.type = BodyType.DynamicBody;
		player = world.createBody(bdef);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(32  , 64);
		fdef.shape = shape;
		player.createFixture(fdef);
		
		shape.setAsBox(16, 16, new Vector2(0, -64), 0);
		fdef.shape = shape;
		fdef.isSensor = true;
		player.createFixture(fdef).setUserData("foot");;
		
		playerTexture = new Texture(Gdx.files.internal("player.png"));
		sprite = new Sprite(playerTexture);
		
		//////////////////////////////////////////
		camera = new OrthographicCamera();
		map = new TmxMapLoader().load("maps/Level4.tmx");
		Box2DMapObjectParser parser = new Box2DMapObjectParser();
		parser.load(world, map);
		parser.getBodies();
		parser.getFixtures();
		parser.getJoints();
		tmr = new OrthogonalTiledMapRenderer(map, parser.getUnitScale());
		//TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("Grass");
		//tileSize = layer.getTileWidth();
		/*for(int row=0;row<layer.getHeight();row++){
			for(int col=0;col<layer.getWidth();col++){
				Cell cell = layer.getCell(col, row);
				
				if(cell == null) {
					continue;
				}
				if(cell.getTile() == null){
					continue;
				}
				bdef.type = BodyType.StaticBody;
				bdef.position.set((col + 0.5f) * tileSize ,
								  (row + 0.5f) * tileSize );
				ground = world.createBody(bdef);
				shape.setAsBox(32, 32);
				fdef.friction = 0;
				fdef.shape = shape;
				fdef.isSensor = false;
				ground.createFixture(fdef);
			}
		}*/
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#render(float)
	 */
	@Override
	public void render(float delta) {
		update(delta);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		player.setLinearVelocity(player.getLinearVelocity().x, player.getLinearVelocity().y);
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
		tmr.setView(camera);
		tmr.render();
		
		tmr.getBatch().begin();
			tmr.getBatch().draw(sprite, player.getPosition().x - 32, player.getPosition().y - 64);
		tmr.getBatch().end();
		b2dr.render(world, camera.combined);
		if(!Gdx.input.isKeyPressed(Input.Keys.LEFT) && !Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !Gdx.input.isKeyPressed(Input.Keys.UP)){
			player.setLinearVelocity(0, -1000);
		}
		System.out.println(player.getLinearVelocity().y);
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) 
			player.setLinearVelocity(1000, player.getLinearVelocity().y);
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
        	player.setLinearVelocity(-1000, -player.getLinearVelocity().y);
        if(canJump){
	        if(Gdx.input.isKeyJustPressed(19)){
		        player.applyForceToCenter(0, 20000, true);
	       }
        }
        if(player.getLinearVelocity().y <= 105 && player.getLinearVelocity().y > 0)
		      player.setLinearVelocity(player.getLinearVelocity().x, -1000);
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
                	if(fixtureA.getUserData() != null && fixtureA.getUserData().equals("foot")){
                		canJump = true;
                	}
                	if(fixtureB.getUserData() != null && fixtureA.getUserData().equals("foot")){
                		canJump = true;
                	}
                }
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
                	
                }
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
		camera.viewportWidth = width / 25;
		camera.viewportHeight = height / 25;
		camera.update();
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
}
