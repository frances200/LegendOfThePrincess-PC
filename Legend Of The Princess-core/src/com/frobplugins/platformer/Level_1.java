package com.frobplugins.platformer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Level_1 implements Screen {
	
	OrthographicCamera cam;
	OrthographicCamera b2dCam;
	World world;
	Box2DDebugRenderer b2dr;
	Body player;
	float PPM = 100;
	TiledMap map;
	OrthogonalTiledMapRenderer renderer;
	
	@Override
	public void show() {
		world = new World(new Vector2(0f, -9.82f), true);
		b2dr = new Box2DDebugRenderer();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 640, 640);
		BodyDef bdef = new BodyDef();
		bdef.position.set(160 / PPM, 120 / PPM);
		bdef.type = BodyType.StaticBody;
		player = world.createBody(bdef);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(50 / PPM, 5 / PPM);
		
		FixtureDef fdef = new FixtureDef();
		fdef.shape = shape;
		player.createFixture(fdef);
		
		bdef.position.set(160 / PPM, 200 / PPM);
		bdef.type = BodyType.DynamicBody;
		player = world.createBody(bdef);
		
		shape.setAsBox(5 / PPM, 5 / PPM);
		fdef.shape = shape;
		player.createFixture(fdef);
		
		b2dCam = new OrthographicCamera();
		b2dCam.setToOrtho(false, 640 / PPM, 640 / PPM);
		
		map = new TmxMapLoader().load("maps/Level1.tmx");
		renderer = new OrthogonalTiledMapRenderer(map);
		cam.position.set(new Vector2(12 * 64, 20 * 64), 0);
		cam.update();
		
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("CollisionLayer");
		for(int row=0;row<layer.getHeight();row++){
			for(int col=0;col<layer.getWidth();col++){
				if(layer.getCell(col, row) != null){
					Cell cell = layer.getCell(col, row);
					
					if(cell == null) {
						System.out.println("found air blocks");
						break;
					}
					if(cell.getTile() == null){
						System.out.println("found air blocks");
						break;
					}
					
					bdef.type = BodyType.StaticBody;
					bdef.position.set((col + 0.5f) * 64 / PPM, 
									  (row + 0.5f) * 64 / PPM
					);
					shape.setAsBox(32 / PPM, 32 / PPM);
					fdef.shape = shape;
					fdef.isSensor = false;
					fdef.friction = 0;
					world.createBody(bdef).createFixture(fdef);
				}
			}
		}
	}
	
	@Override
	public void render(float arg0) {
		world.step(arg0, 1, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		b2dCam.position.x = cam.position.x;
		b2dCam.position.y = cam.position.y;
		renderer.setView(cam);
		renderer.render();
		b2dr.render(world, b2dCam.combined);
	}
	
	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resize(int arg0, int arg1) {

	}
	@Override
	public void dispose() {

	}

	@Override
	public void resume() {

	}
}