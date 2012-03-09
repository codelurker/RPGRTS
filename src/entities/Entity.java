package entities;

import map.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class Entity {
	
	private Image sprite;
	private Vector2f position;
	private boolean alive;
	private boolean collided;
	private Map currentMap;

	public Entity(Image sprite, Vector2f position, Map map) {
		this.sprite = sprite;
		this.position = position;
		this.currentMap = map;
		alive = true;
		collided = false;
	}
	
	public void update(GameContainer container, StateBasedGame game_, int delay) { }
	public void render(GameContainer container, StateBasedGame game_, Graphics g) { 
		sprite.draw(getPosition().x, getPosition().y);
	}
	
	public boolean collidesWith(Entity entity) {
		if ((position.x + sprite.getWidth() > entity.getPosition().x && position.x < entity.getPosition().x  + entity.getSprite().getWidth()) 
				&& (position.y + sprite.getHeight() > entity.getPosition().y && position.y < entity.getPosition().y + entity.getSprite().getHeight())) {
			return true;
		} else
			return false;
	}
	
	public void handleMapBoundCollision() { }
	
	public Vector2f getPosition() {
		return position;
	}
	
	public void setPosition(Vector2f position) {
		this.position = position;
	}
	
	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	public boolean isCollided() {
		return collided;
	}

	public void setCollided(boolean collided) {
		this.collided = collided;
	}

	public Map getCurrentMap() {
		return currentMap;
	}

	public void setCurrentMap(Map currentMap) {
		this.currentMap = currentMap;
	}
	
	public Image getSprite() {
		return sprite;
	}

	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}
}
