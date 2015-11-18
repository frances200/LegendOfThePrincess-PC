package com.frobplugins.platformer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

public class Box2D implements Screen {
	World world = new World(new Vector2(0, -10), true);
	private Box2DDebugRenderer b2dr;
	private OrthographicCamera camera;
	private OrthographicCamera hudCam;
	public float PPM = 100;
	private OrthographicCamera b2dCam;
	private TiledMap map;
	private OrthogonalTiledMapRenderer tmr;
	private float tileSize = 64;

	@Override
	public void show() {
		b2dr = new Box2DDebugRenderer();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 640, 640);
		hudCam = new OrthographicCamera();
		hudCam.setToOrtho(false, 640, 640);
		camera.position.set(new Vector2(1 * tileSize, 10 * tileSize), 0);
		camera.update();
		
		//create platform
		BodyDef bdef = new BodyDef();
		bdef.position.set(160 / PPM, 120 / PPM);
		bdef.type = BodyType.StaticBody;
		Body body = world.createBody(bdef);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(50 / PPM, 5 / PPM);
		
		FixtureDef fdef = new FixtureDef();
		fdef.shape = shape;
		body.createFixture(fdef);
		
		//create falling box
		bdef.position.set(160 / PPM, 200 / PPM);
		bdef.type = BodyType.DynamicBody;
		body = world.createBody(bdef);
		
		shape.setAsBox(5 / PPM, 5 / PPM);
		fdef.shape = shape;
		body.createFixture(fdef);
	
		//Set up B2D camera
		b2dCam = new OrthographicCamera();
		b2dCam.setToOrtho(false, 640 / PPM, 640 / PPM);
		
		//////////////////////////////////////////
		map = new TmxMapLoader().load("maps/Level1.tmx");
		tmr = new OrthogonalTiledMapRenderer(map);
		TiledMapTileLayer layer = 
				(TiledMapTileLayer) map.getLayers().get("Grass");
		for(int row=0;row<layer.getHeight();row++){
			for(int col=0;col<layer.getWidth();col++){
				
				Cell cell = layer.getCell(col, row);
				if(!(cell == null) && !(cell.getTile() == null)){
					
					bdef.type = BodyType.StaticBody;
					bdef.position.set(
							(col + 0.5f) * tileSize / PPM,
							(row + 0.5f) * tileSize / PPM
					);
					
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
				}else{
					System.out.println("air blocks found");
					break;
				}
			}
		}
	}

	@Override
	public void render(float delta) {
		update(delta);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		tmr.setView(camera);
		tmr.render();
		
		b2dr.render(world, b2dCam.combined);
	}
	
	public void update(float delta){
		world.step(delta, 6, 2);
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
	
	
}
