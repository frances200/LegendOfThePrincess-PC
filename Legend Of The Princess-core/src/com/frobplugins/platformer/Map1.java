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
	Main main;
	Body player;
	Texture playerTex = new Texture(Gdx.files.internal("player.png"));
	Sprite sprite_player = new Sprite(playerTex);
	boolean setPosition;
	@Override
	public void show() {
		try{
			SoundManager.StopTheme();
		}catch(Exception e){
			e.printStackTrace();
		}
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
		parser.getFixtures().get("Spikes_keuken4").setUserData("Spikes_keuken4");
		parser.getFixtures().get("Spikes_woonkamer1").setUserData("Spikes_woonkamer1");
		parser.getFixtures().get("Spikes_woonkamer2").setUserData("Spikes_woonkamer2");
		parser.getFixtures().get("Spikes_woonkamer3").setUserData("Spikes_woonkamer3");
		parser.getFixtures().get("Coin").setUserData("Coin");
		parser.getFixtures().get("Coin1").setUserData("Coin1");
		parser.getFixtures().get("Coin2").setUserData("Coin2");
		parser.getFixtures().get("Coin3").setUserData("Coin3");
		parser.getFixtures().get("Coin4").setUserData("Coin4");
		parser.getFixtures().get("Coin5").setUserData("Coin5");
		parser.getFixtures().get("Coin6").setUserData("Coin6");
		parser.getFixtures().get("Coin7").setUserData("Coin7");
		parser.getFixtures().get("Coin8").setUserData("Coin8");
		parser.getFixtures().get("Coin9").setUserData("Coin9");
		parser.getFixtures().get("Coin10").setUserData("Coin10");
		parser.getFixtures().get("Coin11").setUserData("Coin11");
		parser.getFixtures().get("Coin12").setUserData("Coin12");
		parser.getFixtures().get("Coin13").setUserData("Coin13");
		parser.getFixtures().get("Coin14").setUserData("Coin14");
		parser.getFixtures().get("Coin15").setUserData("Coin15");
		parser.getFixtures().get("Coin16").setUserData("Coin16");
		parser.getFixtures().get("Coin17").setUserData("Coin17");
		parser.getFixtures().get("Coin18").setUserData("Coin18");
		parser.getFixtures().get("Coin19").setUserData("Coin19");
		parser.getFixtures().get("Coin20").setUserData("Coin20");
		
		Assets.sprite_coin.setSize(25 * parser.getUnitScale(), 25 * parser.getUnitScale());
		Assets.sprite_coin1.setSize(25 * parser.getUnitScale(), 25 * parser.getUnitScale());
		Assets.sprite_coin2.setSize(25 * parser.getUnitScale(), 25 * parser.getUnitScale());
		Assets.sprite_coin3.setSize(25 * parser.getUnitScale(), 25 * parser.getUnitScale());
		Assets.sprite_coin4.setSize(25 * parser.getUnitScale(), 25 * parser.getUnitScale());
		Assets.sprite_coin5.setSize(25 * parser.getUnitScale(), 25 * parser.getUnitScale());
		Assets.sprite_coin6.setSize(25 * parser.getUnitScale(), 25 * parser.getUnitScale());
		Assets.sprite_coin7.setSize(25 * parser.getUnitScale(), 25 * parser.getUnitScale());
		Assets.sprite_coin8.setSize(25 * parser.getUnitScale(), 25 * parser.getUnitScale());
		Assets.sprite_coin9.setSize(25 * parser.getUnitScale(), 25 * parser.getUnitScale());
		Assets.sprite_coin10.setSize(25 * parser.getUnitScale(), 25 * parser.getUnitScale());
		Assets.sprite_coin11.setSize(25 * parser.getUnitScale(), 25 * parser.getUnitScale());
		Assets.sprite_coin12.setSize(25 * parser.getUnitScale(), 25 * parser.getUnitScale());
		Assets.sprite_coin13.setSize(25 * parser.getUnitScale(), 25 * parser.getUnitScale());
		Assets.sprite_coin14.setSize(25 * parser.getUnitScale(), 25 * parser.getUnitScale());
		Assets.sprite_coin15.setSize(25 * parser.getUnitScale(), 25 * parser.getUnitScale());
		Assets.sprite_coin16.setSize(25 * parser.getUnitScale(), 25 * parser.getUnitScale());
		Assets.sprite_coin17.setSize(25 * parser.getUnitScale(), 25 * parser.getUnitScale());
		Assets.sprite_coin18.setSize(25 * parser.getUnitScale(), 25 * parser.getUnitScale());
		Assets.sprite_coin19.setSize(25 * parser.getUnitScale(), 25 * parser.getUnitScale());
		Assets.sprite_coin20.setSize(25 * parser.getUnitScale(), 25 * parser.getUnitScale());
		
		if(!setPosition){
			Assets.sprite_coin.setPosition(parser.getBodies().get("Coin").getPosition().x, parser.getBodies().get("Coin").getPosition().y);
			Assets.sprite_coin1.setPosition(parser.getBodies().get("Coin1").getPosition().x, parser.getBodies().get("Coin1").getPosition().y);
			Assets.sprite_coin2.setPosition(parser.getBodies().get("Coin2").getPosition().x, parser.getBodies().get("Coin2").getPosition().y);
			Assets.sprite_coin3.setPosition(parser.getBodies().get("Coin3").getPosition().x, parser.getBodies().get("Coin3").getPosition().y);
			Assets.sprite_coin4.setPosition(parser.getBodies().get("Coin4").getPosition().x, parser.getBodies().get("Coin4").getPosition().y);
			Assets.sprite_coin5.setPosition(parser.getBodies().get("Coin5").getPosition().x, parser.getBodies().get("Coin5").getPosition().y);
			Assets.sprite_coin6.setPosition(parser.getBodies().get("Coin6").getPosition().x, parser.getBodies().get("Coin6").getPosition().y);
			Assets.sprite_coin7.setPosition(parser.getBodies().get("Coin7").getPosition().x, parser.getBodies().get("Coin7").getPosition().y);
			Assets.sprite_coin8.setPosition(parser.getBodies().get("Coin8").getPosition().x, parser.getBodies().get("Coin8").getPosition().y);
			Assets.sprite_coin9.setPosition(parser.getBodies().get("Coin9").getPosition().x, parser.getBodies().get("Coin9").getPosition().y);
			Assets.sprite_coin10.setPosition(parser.getBodies().get("Coin10").getPosition().x, parser.getBodies().get("Coin10").getPosition().y);
			Assets.sprite_coin11.setPosition(parser.getBodies().get("Coin11").getPosition().x, parser.getBodies().get("Coin11").getPosition().y);
			Assets.sprite_coin12.setPosition(parser.getBodies().get("Coin12").getPosition().x, parser.getBodies().get("Coin12").getPosition().y);
			Assets.sprite_coin13.setPosition(parser.getBodies().get("Coin13").getPosition().x, parser.getBodies().get("Coin13").getPosition().y);
			Assets.sprite_coin14.setPosition(parser.getBodies().get("Coin14").getPosition().x, parser.getBodies().get("Coin14").getPosition().y);
			Assets.sprite_coin15.setPosition(parser.getBodies().get("Coin15").getPosition().x, parser.getBodies().get("Coin15").getPosition().y);
			Assets.sprite_coin16.setPosition(parser.getBodies().get("Coin16").getPosition().x, parser.getBodies().get("Coin16").getPosition().y);
			Assets.sprite_coin17.setPosition(parser.getBodies().get("Coin17").getPosition().x, parser.getBodies().get("Coin17").getPosition().y);
			Assets.sprite_coin18.setPosition(parser.getBodies().get("Coin18").getPosition().x, parser.getBodies().get("Coin18").getPosition().y);
			Assets.sprite_coin19.setPosition(parser.getBodies().get("Coin19").getPosition().x, parser.getBodies().get("Coin19").getPosition().y);
			Assets.sprite_coin20.setPosition(parser.getBodies().get("Coin20").getPosition().x, parser.getBodies().get("Coin20").getPosition().y);
			setPosition = true;
		}
		parser.getFixtures().get("foot").setUserData("foot");
		sprite_player.setSize(32 * parser.getUnitScale(), 64 * parser.getUnitScale());
		Assets.sprite_briefje.setSize(240 * parser.getUnitScale(), 240 * parser.getUnitScale());
		renderer = new OrthogonalTiledMapRenderer(map, parser.getUnitScale());
		camera = new OrthographicCamera();
		camera.position.set(new Vector2(11, 82.5f), 0);
		try{
				if(!Geluid.MutedBgMusic){
					SoundManager.Song_Level1.play();
					SoundManager.Song_Level1.setVolume(Geluid.Volume);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		if(Geluid.MutedBgMusic){
			SoundManager.Song_Level1.pause();
		}
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
			Assets.sprite_coin.draw(renderer.getBatch());
			Assets.sprite_coin1.draw(renderer.getBatch());
			Assets.sprite_coin2.draw(renderer.getBatch());
			Assets.sprite_coin3.draw(renderer.getBatch());
			Assets.sprite_coin4.draw(renderer.getBatch());
			Assets.sprite_coin5.draw(renderer.getBatch());
			Assets.sprite_coin6.draw(renderer.getBatch());
			Assets.sprite_coin7.draw(renderer.getBatch());
			Assets.sprite_coin8.draw(renderer.getBatch());
			Assets.sprite_coin9.draw(renderer.getBatch());
			Assets.sprite_coin10.draw(renderer.getBatch());
			Assets.sprite_coin11.draw(renderer.getBatch());
			Assets.sprite_coin12.draw(renderer.getBatch());
			Assets.sprite_coin13.draw(renderer.getBatch());
			Assets.sprite_coin14.draw(renderer.getBatch());
			Assets.sprite_coin15.draw(renderer.getBatch());
			Assets.sprite_coin16.draw(renderer.getBatch());
			Assets.sprite_coin17.draw(renderer.getBatch());
			Assets.sprite_coin18.draw(renderer.getBatch());
			Assets.sprite_coin19.draw(renderer.getBatch());
			Assets.sprite_coin20.draw(renderer.getBatch());
			sprite_player.draw(renderer.getBatch());
			
			if(CollisionListener.isShowingBrief) {
				Assets.sprite_briefje.draw(renderer.getBatch());
			}
		renderer.getBatch().end();
		
		System.out.println(player.getPosition().x + " + " + player.getPosition().y);
		
		if (CollisionListener.door1){
			if(Gdx.input.isKeyJustPressed(Keys.ENTER)){
				player.setTransform(new Vector2(parser.getBodies().get("Teleport1").getPosition().x, parser.getBodies().get("Teleport1").getPosition().y), player.getAngle());
				CollisionListener.door1 = false;
			}
		}
		if (CollisionListener.door2){
			if(Gdx.input.isKeyJustPressed(Keys.ENTER)){
				player.setTransform(new Vector2(parser.getBodies().get("Teleport2").getPosition().x, parser.getBodies().get("Teleport2").getPosition().y), player.getAngle());
				CollisionListener.door2 = false;
			}
		}
		if (CollisionListener.door3){
			if(Gdx.input.isKeyJustPressed(Keys.ENTER)){
				((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenu(main));
			}
		}
		if(!CollisionListener.isShowingBrief){
			if(Gdx.input.isKeyPressed(Keys.LEFT)){
				player.setLinearVelocity(-2f, player.getLinearVelocity().y);
			}
			if(Gdx.input.isKeyPressed(Keys.RIGHT)){
				player.setLinearVelocity(2f, player.getLinearVelocity().y);
			}
			if(!Gdx.input.isKeyPressed(Keys.RIGHT) && !Gdx.input.isKeyPressed(Keys.LEFT)){
				player.setLinearVelocity(0f, player.getLinearVelocity().y);
			}
			if(CollisionListener.canJump){
				if(Gdx.input.isKeyJustPressed(Keys.UP)){
					player.applyForceToCenter(new Vector2(player.getLinearVelocity().x, 300f), false);
				}
			}
		}
		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			CollisionListener.isShowingBrief=false;
		}
		if(CollisionListener.spikes_werkplek1 || CollisionListener.spikes_werkplek2 || CollisionListener.spikes_keuken1 || 
				CollisionListener.spikes_keuken2 || CollisionListener.spikes_keuken3 || CollisionListener.spikes_woonkamer1 || 
					CollisionListener.spikes_woonkamer2 || CollisionListener.spikes_woonkamer3 || CollisionListener.spikes_keuken4) {
			((Game) Gdx.app.getApplicationListener()).setScreen(new DeathLevel1(main));
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
