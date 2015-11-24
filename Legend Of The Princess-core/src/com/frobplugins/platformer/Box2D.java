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
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Box2D implements Screen,InputProcessor{
	World world = new World(new Vector2(0, -9.82f), true);
	private Box2DDebugRenderer b2dr;
	private OrthographicCamera camera;
	private OrthographicCamera cam;
	private TiledMap map;
	private OrthogonalTiledMapRenderer tmr;
	float tileSize = 64;
	private Body player;
	Texture playerTexture;
	Sprite sprite;
	private float torque = 0;
	private float PPM = 100;
	SpriteBatch batch;
	
	@Override
	public void show() {
		SoundManager.theme.stop();
		Gdx.input.setInputProcessor(this);
		b2dr = new Box2DDebugRenderer();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 640, 640);
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		batch = new SpriteBatch();
		
		bdef.position.set(2.4f * tileSize / PPM , 22 * tileSize / PPM );
		bdef.type = BodyType.DynamicBody;
		player = world.createBody(bdef);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(32 / PPM , 6 / PPM  );
		fdef.shape = shape;
		player.createFixture(fdef);
		playerTexture = new Texture(Gdx.files.internal("player.png"));
		sprite = new Sprite(playerTexture);
		
		//////////////////////////////////////////
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 640 / PPM, 640 / PPM);
		map = new TmxMapLoader().load("maps/Level1.tmx");
		tmr = new OrthogonalTiledMapRenderer(map);
		
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("Grass");
		tileSize = layer.getTileWidth();
		for(int row=0;row<layer.getHeight();row++){
			for(int col=0;col<layer.getWidth();col++){
				Cell cell = layer.getCell(col, row);
				
				if(cell == null) {
					System.out.println("found air blocks");
					continue;
				}
				if(cell.getTile() == null){
					System.out.println("found air blocks");
					continue;
				}
				bdef.type = BodyType.StaticBody;
				bdef.position.set((col + 0.5f) * tileSize / PPM,
								  (row + 0.5f) * tileSize / PPM); 
				ChainShape cs = new ChainShape();
				Vector2[] v = new Vector2[3];
				v[0] = new Vector2(-tileSize / 2 / PPM, -tileSize / 2 / PPM);
				v[1] = new Vector2(-tileSize / 2 / PPM, tileSize / 2 / PPM);
				v[2] = new Vector2(tileSize / 2 / PPM, tileSize / 2 / PPM);
				cs.createChain(v);
				fdef.friction = 0;
				fdef.shape = cs;
				fdef.isSensor = false;
				world.createBody(bdef).createFixture(fdef);
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
		System.out.println(player.getPosition().y);
		
		tmr.getBatch().begin();
			tmr.getBatch().draw(Assets.bg, bgX, bgY);
		tmr.getBatch().end();
		cam.update();
		tmr.setView(cam);
		tmr.render();
		
		batch.begin();
			batch.draw(sprite, player.getPosition().x - 32, player.getPosition().y - 64);
		batch.end();
		
		b2dr.render(world, camera.combined);
	}
	
	public void update(float delta){
		world.step(delta, 1, 1);
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
		if(arg0 == Input.Keys.RIGHT) 
            player.setLinearVelocity(10f, 0f);
        if(arg0 == Input.Keys.LEFT)
        	player.setLinearVelocity(-10f,0f);

        if(arg0 == Input.Keys.UP)
        	player.applyForceToCenter(0f,100f,true);
        if(arg0 == Input.Keys.DOWN)
        	player.applyForceToCenter(0f, -100f, true);
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
