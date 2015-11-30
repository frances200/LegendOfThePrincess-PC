package com.frobplugins.platformer;

import com.badlogic.gdx.Game;
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
		parser.getFixtures().get("DoorCollider2").setUserData("Door2");
		parser.getFixtures().get("DoorCollider3").setUserData("Door3");
		
		parser.getFixtures().get("Briefje_sensor").setUserData("Briefje");
		
		parser.getFixtures().get("SlaapEnWerkkamer").setUserData("SlaapEnWerkkamer");
		
		parser.getFixtures().get("Spikes_werkplek1").setUserData("Spikes_werkplek1");
		parser.getFixtures().get("Spikes_werkplek2").setUserData("Spikes_werkplek2");
		parser.getFixtures().get("Spikes_keuken1").setUserData("Spikes_keuken1");
		parser.getFixtures().get("Spikes_keuken2").setUserData("Spikes_keuken2");
		parser.getFixtures().get("Spikes_keuken3").setUserData("Spikes_keuken3");
		parser.getFixtures().get("Spikes_woonkamer1").setUserData("Spikes_woonkamer1");
		parser.getFixtures().get("Spikes_woonkamer2").setUserData("Spikes_woonkamer2");
		parser.getFixtures().get("Spikes_woonkamer3").setUserData("Spikes_woonkamer3");
		
		parser.getFixtures().get("foot").setUserData("foot");
		sprite_player.setSize(32 * parser.getUnitScale(), 64 * parser.getUnitScale());
		Assets.sprite_briefje.setSize(240 * parser.getUnitScale(), 240 * parser.getUnitScale());
		renderer = new OrthogonalTiledMapRenderer(map, parser.getUnitScale());
		camera = new OrthographicCamera();
		camera.position.set(new Vector2(11, 82.5f), 0);
		//SoundManager.theme.stop();
		//SoundManager.Song_Level1.play();
		//SoundManager.Song_Level1.setVolume(Geluid.Volume);
	}
	
	@Override
	public void render(float arg0) {
		world.step(arg0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sprite_player.setPosition(player.getPosition().x, player.getPosition().y);
		camera.position.x = sprite_player.getX();
		camera.position.y = sprite_player.getY();
		Assets.sprite_briefje.setPosition(camera.position.x - (320 * parser.getUnitScale())+6.25f, camera.position.y - (320 * parser.getUnitScale())+6.25f);
		renderer.setView(camera);
		renderer.render();
		
		renderer.getBatch().begin();
			sprite_player.draw(renderer.getBatch());
			
			if(CollisionListener.isShowingBrief) {
				Assets.sprite_briefje.draw(renderer.getBatch());
			}
		renderer.getBatch().end();
		
		System.out.println(player.getPosition().x + " + " + player.getPosition().y);
		
		if (CollisionListener.door1){
			System.out.println("oke");
		}
		//b2dr.render(world, camera.combined);
		if(!CollisionListener.isShowingBrief){
			if(Gdx.input.isKeyPressed(Keys.A)){
				player.setLinearVelocity(-2f, player.getLinearVelocity().y);
			}
			if(Gdx.input.isKeyPressed(Keys.D)){
				player.setLinearVelocity(2f, player.getLinearVelocity().y);
			}
			if(!Gdx.input.isKeyPressed(Keys.D) && !Gdx.input.isKeyPressed(Keys.A)){
				player.setLinearVelocity(0f, player.getLinearVelocity().y);
			}
			if(CollisionListener.canJump){
				if(Gdx.input.isKeyJustPressed(Keys.W)){
					player.applyForceToCenter(new Vector2(player.getLinearVelocity().x, 300f), false);
				}
			}
		}
		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			CollisionListener.isShowingBrief=false;
		}
		if(CollisionListener.spikes_werkplek1 || CollisionListener.spikes_werkplek2 || CollisionListener.spikes_keuken1 || 
				CollisionListener.spikes_keuken2 || CollisionListener.spikes_keuken3 || CollisionListener.spikes_woonkamer1 || 
					CollisionListener.spikes_woonkamer2 || CollisionListener.spikes_woonkamer3) {
			((Game) Gdx.app.getApplicationListener()).setScreen(new DeathLevel1(null));
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
