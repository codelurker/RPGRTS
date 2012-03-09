package entities;

import map.CollisionTile;
import map.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class Bullet extends Entity {
	
	private int speed;
	private int angle;
	private Vector2f velocity;
	private Entity owner;
	private Vector2f direction;
	
	public Bullet(Image sprite, Entity owner, Vector2f mouse, Map map) {
		super(sprite, owner.getPosition().copy(), map);
		this.owner = owner;
		speed = 250;
		direction = new Vector2f(512, 384).sub(mouse);
		direction.normalise();
		velocity = new Vector2f(direction.x * speed * -1, direction.y * speed * -1);
	}
	
	public void update(GameContainer container, StateBasedGame game_, int delay) {
		for (CollisionTile collision : getCurrentMap().getCollisionLayer()) {
			if ((getPosition().x + getSprite().getWidth() > collision.getPosition().x && getPosition().x < collision.getPosition().x + collision.getWidth())
					&& (getPosition().y + getSprite().getHeight() > collision.getPosition().y && getPosition().y < collision.getPosition().y + collision.getHeight())) {
				setCollided(true);
			}
		}
		
		if (!isCollided())
			getPosition().add(new Vector2f(velocity.x * (delay/1000.0f), velocity.y * (delay/1000.0f)));
		else
			getCurrentMap().removeEntity(this);
	}
	
	@Override
	public void handleMapBoundCollision() {
		setCollided(true);
	}
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}

	public Entity getOwner() {
		return owner;
	}

	public void setOwner(Entity owner) {
		this.owner = owner;
	}
}
