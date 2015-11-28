package com.frobplugins.platformer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import net.dermetfan.gdx.physics.box2d.Box2DMapObjectParser;

public class Testclass implements Screen{
	World world = new World(new Vector2(0, -9.81f), true);
	private Box2DDebugRenderer b2dr;
	private OrthographicCamera camera;
	private TiledMap map;
	private OrthogonalTiledMapRenderer tmr;
	private Body player;
	private Texture playerTex = new Texture(Gdx.files.internal("player.png"));
	private Sprite sprite_player = new Sprite(playerTex);
	
	@Override
	public void show() {
		camera = new OrthographicCamera();
		b2dr = new Box2DDebugRenderer();
		map = new TmxMapLoader().load("maps/Level4.tmx");
		Box2DMapObjectParser parser = new Box2DMapObjectParser(.03125f);
		parser.load(world, map);
		parser.getBodies();
		parser.getFixtures();
		parser.getJoints();
		parser.setTileWidth(64);
		parser.setTileHeight(64);
		player = parser.getBodies().get("Player");
		sprite_player.setSize(64 * parser.getUnitScale(), 128 * parser.getUnitScale());
		tmr = new OrthogonalTiledMapRenderer(map, parser.getUnitScale());
		camera.position.y = 1000 / 100;
		camera.position.x = (25 * 64) / 100;
	}
	@Override
	public void render(float delta) {
		update(delta);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		sprite_player.setPosition(player.getPosition().x, player.getPosition().y);
		if(player.getPosition().x >= 20){
			camera.position.x = sprite_player.getX();
			camera.position.y = sprite_player.getY();
		}else{
			camera.position.y = player.getPosition().y;
			camera.position.x = 20;
		}
		System.out.println(player.getPosition().x);
		camera.update();
		float bgX = camera.position.x;
		float bgY = camera.position.y;
		
		tmr.getBatch().begin();
			tmr.getBatch().draw(Assets.bg, bgX - 320, bgY - 320);
		tmr.getBatch().end();
		
		tmr.setView(camera);
		tmr.render();
		
		tmr.getBatch().begin();
			sprite_player.draw(tmr.getBatch());
		tmr.getBatch().end();
		
		//b2dr.render(world, camera.combined);
	}
	
	public void update(float delta){
		world.step(delta, 1, 1);
		
		if(Gdx.input.isKeyPressed(Keys.A)){
			player.setLinearVelocity(-10f, player.getLinearVelocity().y);
		}
		if(Gdx.input.isKeyPressed(Keys.D)){
			player.setLinearVelocity(10f, player.getLinearVelocity().y);
		}
		if(!Gdx.input.isKeyPressed(Keys.D) && !Gdx.input.isKeyPressed(Keys.A)){
			player.setLinearVelocity(0f, player.getLinearVelocity().y);
		}
	}


	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = width / 25;
		camera.viewportHeight = height / 25;
		camera.update();
		System.out.println(camera.position.x);
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
