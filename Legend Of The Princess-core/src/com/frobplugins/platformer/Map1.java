package com.frobplugins.platformer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
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

public class Map1 implements Screen{
	World world = new World(new Vector2(0, -9.81f), false);
	Box2DDebugRenderer b2dr;
	TiledMap map;
	OrthogonalTiledMapRenderer renderer;
	OrthographicCamera camera;
	Box2DMapObjectParser parser;
	Body player;
	Texture playerTex = new Texture(Gdx.files.internal("player.png"));
	Sprite sprite_player = new Sprite(playerTex);
	@Override
	public void show() {
		world.setContactListener(new CollisionListener());
		b2dr = new Box2DDebugRenderer();
		map = new TmxMapLoader().load("maps/Map1.tmx");
		parser = new Box2DMapObjectParser(.03125f);
		parser.load(world, map);
		parser.getBodies();
		parser.getFixtures();
		parser.getJoints();
		player = parser.getBodies().get("Player");
		parser.getFixtures().get("Player").setUserData("Player");
		parser.getFixtures().get("DoorCollider1").setUserData("Door1");
		parser.getFixtures().get("Briefje_sensor").setUserData("Briefje");
		sprite_player.setSize(32 * parser.getUnitScale(), 64 * parser.getUnitScale());
		renderer = new OrthogonalTiledMapRenderer(map, parser.getUnitScale());
		camera = new OrthographicCamera();
		camera.position.set(new Vector2(11, 82.5f), 0);
	}
	
	@Override
	public void render(float arg0) {
		world.step(arg0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sprite_player.setPosition(player.getPosition().x, player.getPosition().y);
		camera.position.x = sprite_player.getX();
		camera.position.y = sprite_player.getY();
		renderer.setView(camera);
		renderer.render();
		
		renderer.getBatch().begin();
			sprite_player.draw(renderer.getBatch());
		renderer.getBatch().end();
		
		b2dr.render(world, camera.combined);
		
		if(Gdx.input.isKeyPressed(Keys.A)){
			player.setLinearVelocity(-2f, player.getLinearVelocity().y);
		}
		if(Gdx.input.isKeyPressed(Keys.D)){
			player.setLinearVelocity(2f, player.getLinearVelocity().y);
		}
		if(!Gdx.input.isKeyPressed(Keys.D) && !Gdx.input.isKeyPressed(Keys.A)){
			player.setLinearVelocity(0f, player.getLinearVelocity().y);
		}
		if(Gdx.input.isKeyJustPressed(Keys.W)){
			player.applyForceToCenter(new Vector2(player.getLinearVelocity().x, 300f), false);
		}
		camera.update();
	}
	
	@Override
	public void dispose() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resize(int arg0, int arg1) {
		camera.viewportWidth = arg0 / 75;
		camera.viewportHeight = arg1 / 75;
		camera.update();
	}

	@Override
	public void resume() {
		
	}
}
