package com.frobplugins.platformer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Gebruiker on 29-10-2015.
 */
public class Level1 implements Screen {

	static float WIDTH;
	static float HEIGHT;
	static float MAX_VELOCITY = 10f;
	static float JUMP_VELOCITY = 40f;
	static float DAMPING = 0.87f;
	
	private boolean collisionX = false;
	private boolean collisionY = false;

	enum State {
		Standing, Walking, Jumping
	}

	final Vector2 position = new Vector2();
	final Vector2 velocity = new Vector2();
	State state = State.Walking;
	float stateTime = 0;
	boolean facesRight = true;
	boolean grounded = false;

private TiledMap map;
private OrthogonalTiledMapRenderer renderer;
private OrthographicCamera camera;
private Texture koalaTexture;
private Animation stand;
private Animation walk;
private Animation jump;
private Level1 koala;
private Rectangle koalaRect;

private static final float GRAVITY = -2.5f;

@Override
public void show () {
	koalaTexture = new Texture("player.png");
	map = new TmxMapLoader().load("maps/Level1.tmx");
	renderer = new OrthogonalTiledMapRenderer(map);
	camera = new OrthographicCamera();
	camera.setToOrtho(false, 320, 240);
	camera.update();
	koala = new Level1();
	koala.position.set(11 * 64, 50 * 64);
	koalaRect = new Rectangle(koala.position.x, koala.position.y, 64, 128);
}

@Override
public void render (float delta) {
	// clear the screen
	Gdx.gl.glClearColor(0.7f, 0.7f, 1.0f, 1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	// get the delta time
	float deltaTime = Gdx.graphics.getDeltaTime();

	// update the koala (process input, collision detection, position update)
	updateKoala(deltaTime);

	// let the camera follow the koala, x-axis only
	koalaRect.x = koala.position.x;
	koalaRect.y = koala.position.y;
	camera.position.x = koalaRect.x + koalaRect.width/2;
	camera.position.y = koalaRect.y + koalaRect.height/2;
	camera.update();

	// set the tile map rendere view based on what the
	// camera sees and render the map
	renderer.setView(camera);
	renderer.render();

	// render the koala
	renderKoala(deltaTime);
	//System.out.println(koala.position.x + " " + koala.position.y);
}

private Vector2 tmp = new Vector2();

private void updateKoala (float deltaTime) {
	koala.stateTime += deltaTime;

	// check input and apply to velocity & state
	if ((Gdx.input.isKeyPressed(Keys.SPACE)) && koala.grounded) {
		koala.velocity.y += koala.JUMP_VELOCITY;
		koala.state = State.Jumping;
		koala.grounded = false;
	}

	if (Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A)) {
		koala.velocity.x = -koala.MAX_VELOCITY;
		if (koala.grounded) koala.state = State.Walking;
		koala.facesRight = false;
	}

	if (Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D)) {
		koala.velocity.x = koala.MAX_VELOCITY;
		if (koala.grounded) koala.state = State.Walking;
		koala.facesRight = true;
	}

	// apply gravity if we are falling
	koala.velocity.add(0, GRAVITY);

	// clamp the velocity to the maximum, x-axis only
	if (Math.abs(koala.velocity.x) > koala.MAX_VELOCITY) {
		koala.velocity.x = Math.signum(koala.velocity.x) * koala.MAX_VELOCITY;
	}

	// clamp the velocity to 0 if it's < 1, and set the state to standing
	if (Math.abs(koala.velocity.x) < 1) {
		koala.velocity.x = 0;
		if (koala.grounded) koala.state = State.Standing;
	}

	// multiply by delta time so we know how far we go
	// in this frame
	koala.velocity.scl(deltaTime);
	
	// unscale the velocity by the inverse delta time and set
	// the latest position

	// Apply damping to the velocity on the x-axis so we don't
	// walk infinitely once a key was pressed
	koala.velocity.x *= koala.DAMPING;
	koala.WIDTH = 64;
	koala.HEIGHT = 128;
	float oldX = koala.position.x;
	float oldY = koala.position.y;
	 TiledMapTileLayer collisionLayer = (TiledMapTileLayer) map.getLayers().get("Grass");
	 for(int x=0;x<collisionLayer.getWidth();x++){
		 for(int y=0;y<collisionLayer.getHeight();y++){
			 if(koala.velocity.x < 0){
				 //top left
				 if(collisionLayer.getCell((int) (koala.position.x / 64), (int) ((koala.position.y + HEIGHT) / 64)) == null){
					 break;
				 }else{
					 collisionX = collisionLayer.getCell((int) (koala.position.x / 64), (int) ((koala.position.y + HEIGHT) / 64)).getTile().getProperties().containsKey("blocked");
				 }
				 //middle left
				 if(collisionLayer.getCell((int) (koala.position.x / 64), (int) ((koala.position.y + (HEIGHT/2)) / 64)) == null){
					 break;
				 }else{
					 if(collisionX == false)
					 collisionX = collisionLayer.getCell((int) (koala.position.x / 64), (int) ((koala.position.y + (HEIGHT/2)) / 64)).getTile().getProperties().containsKey("blocked");
				 }
				 if(collisionLayer.getCell((int) (koala.position.x / 64), (int) ((koala.position.y + (HEIGHT/4)) / 64)) == null){
					 break;
				 }else{
					 if(collisionX == false)
					 collisionX = collisionLayer.getCell((int) (koala.position.x / 64), (int) ((koala.position.y + (HEIGHT/4)) / 64)).getTile().getProperties().containsKey("blocked");
				 }
				 //bottom left
				 if(collisionLayer.getCell((int) (koala.position.x / 64), (int) ((koala.position.y) / 64)) == null){
					 break;
				 }else{
					 if(collisionX == false)
					 collisionX = collisionLayer.getCell((int) (koala.position.x / 64), (int) ((koala.position.y) / 64)).getTile().getProperties().containsKey("blocked");
				 }
			 }
			 
			 if(koala.velocity.x > 0){
				//top left
				 if(collisionLayer.getCell((int) ((koala.position.x + 64) / 64), (int) ((koala.position.y + HEIGHT) / 64)) == null){
					 break;
				 }else{
					 collisionX = collisionLayer.getCell((int) ((koala.position.x + 64) / 64), (int) ((koala.position.y + HEIGHT) / 64)).getTile().getProperties().containsKey("blocked");
				 }
				 //middle left
				 if(collisionLayer.getCell((int) ((koala.position.x + 64) / 64), (int) ((koala.position.y + (HEIGHT/2)) / 64)) == null){
					 break;
				 }else{
					 if(collisionX == false)
					 collisionX = collisionLayer.getCell((int) (koala.position.x / 64), (int) ((koala.position.y + (HEIGHT/2)) / 64)).getTile().getProperties().containsKey("blocked");
				 }
				 if(collisionLayer.getCell((int) ((koala.position.x + 64) / 64), (int) ((koala.position.y + (HEIGHT/4)) / 64)) == null){
					 break;
				 }else{
					 if(collisionX == false)
					 collisionX = collisionLayer.getCell((int) (koala.position.x / 64), (int) ((koala.position.y + (HEIGHT/4)) / 64)).getTile().getProperties().containsKey("blocked");
				 }
				 //bottom left
				 if(collisionLayer.getCell((int) ((koala.position.x + 64) / 64), (int) ((koala.position.y) / 64)) == null){
					 break;
				 }else{
					 if(collisionX == false)
					 collisionX = collisionLayer.getCell((int) (koala.position.x / 64), (int) ((koala.position.y) / 64)).getTile().getProperties().containsKey("blocked");
				 }
			 }
			 
			 if(koala.velocity.y < 0){
				 //bottom left
				 if(collisionLayer.getCell((int) ((koala.position.x) / 64), (int) ((koala.position.y) / 64)) == null){
					 break;
				 }else{
					 collisionY = collisionLayer.getCell((int) ((koala.position.x) / 64), (int) ((koala.position.y) / 64)).getTile().getProperties().containsKey("blocked"); 
				 }
				 //middle bottom
				 if(collisionLayer.getCell((int) ((koala.position.x) / 64), (int) ((koala.position.y) / 64)) == null){
					 break;
				 }else{
					 if(collisionY == false)
					 collisionY = collisionLayer.getCell((int) ((koala.position.x) / 64), (int) ((koala.position.y) / 64)).getTile().getProperties().containsKey("blocked"); 
				 }
				 //bottom right
				 if(collisionLayer.getCell((int) ((koala.position.x + 64) / 64), (int) ((koala.position.y) / 64)) == null){
					 break;
				 }else{
					 if(collisionY == false)
					 collisionY = collisionLayer.getCell((int) ((koala.position.x + 64) / 64), (int) ((koala.position.y) / 64)).getTile().getProperties().containsKey("blocked"); 
				 }
			 }
		 }
	 }
	 
	 if(collisionX){
		 koala.position.x = oldX;
		 koala.velocity.x = 0;
	 }if(collisionY){
		 koala.position.y = oldY;
		 koala.velocity.y = 0;
	 }
	 koala.position.add(koala.velocity);
	 koala.velocity.scl(1 / deltaTime);
	 System.out.println(collisionX);
}

private void renderKoala (float deltaTime) {

	// draw the koala, depending on the current velocity
	// on the x-axis, draw the koala facing either right
	// or left
	renderer.getBatch().begin();
		renderer.getBatch().draw(koalaTexture, koala.position.x, koala.position.y, WIDTH, HEIGHT);
	renderer.getBatch().end();
}

@Override
public void dispose () {
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
}
