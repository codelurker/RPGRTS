package entities;

import map.Map;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

public class Enemy extends Entity {

	public Enemy(Vector2f position, Image sprite, Map map) {
		super(sprite, position, map);
	}
	
	public void handleCollisionWithPlayerBullets() {
		System.out.println("Player's bullet collided with enemy.");
	}
}
