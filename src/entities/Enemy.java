package entities;

import map.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class Enemy extends Entity {

	public Enemy(Vector2f position, Image sprite, Map map) {
		super(sprite, position, map);
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game_, int delay) {
		if (!this.isCollided()) {
			setPosition(new Vector2f(getPosition().x, getPosition().y+1));
		}
	}
	
	public void handleCollisionWithPlayerBullets() {
		System.out.println("Player's bullet collided with enemy.");
	}
}
