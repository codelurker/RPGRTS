package entities;

import map.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class Enemy extends Entity {

	public Enemy(Vector2f position, Image sprite, Map map) {
		super(sprite, position, map);
	}
	
	public void render(GameContainer container, StateBasedGame game_, Graphics g) {
		getSprite().draw(getPosition().x, getPosition().y);
	}
	
	public void handleCollisionWithPlayerBullets() {
		System.out.println("Player's bullet collided with enemy.");
	}
}
