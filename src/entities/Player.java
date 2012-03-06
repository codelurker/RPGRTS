package entities;

import map.CollisionTile;
import map.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class Player extends Entity {
	
	private Vector2f velocity;
	private boolean moving;
	private float FIRE_DELAY = 0.1F;
	private float fireTime = 0.0F;
	private int movementSpeed = 2;
	
	public Player(Vector2f position, Image sprite, Map map) {
		super(sprite, position, map);
		velocity = new Vector2f(0,0);
	}

	public void update(GameContainer container, StateBasedGame game_, int delay) {
		setCollided(false);
		moving = false;
		
		float newx = getPosition().x;
		float newy = getPosition().y;
		
		if (container.getInput().isKeyDown(Input.KEY_LEFT)) {
			newx -= movementSpeed;
			moving = true;
		}
		if (container.getInput().isKeyDown(Input.KEY_RIGHT)) {
			newx += movementSpeed;
			moving = true;
		}
		if (container.getInput().isKeyDown(Input.KEY_UP)) {
			newy -= movementSpeed;
			moving = true;
		}
		if (container.getInput().isKeyDown(Input.KEY_DOWN)) {
			newy += movementSpeed;
			moving = true;
		}
		
		Vector2f newPos = new Vector2f(newx, newy);
		
		// TODO: Optimize - only check collision based on current player's position
		for (CollisionTile collision : getCurrentMap().getCollisionLayer()) {
			if ((newPos.x + getSprite().getWidth() > collision.getPosition().x && newPos.x < collision.getPosition().x + collision.getWidth())
					&& (newPos.y + getSprite().getHeight() > collision.getPosition().y && newPos.y < collision.getPosition().y + collision.getHeight())) {
				setCollided(true);
			}
		}
		
		if (!isCollided()) {
			if (moving) {
				setPosition(newPos);
			}
		} else {
			setPosition(getPosition());
		}
		
		fireTime += delay / 1000.0F;
		
		if (container.getInput().isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON) && fireTime > FIRE_DELAY) {
			try {
				Bullet newBullet = new Bullet(new Image("data/sprites/bullet-1.png"), this, new Vector2f(container.getInput().getAbsoluteMouseX(), container.getInput().getAbsoluteMouseY()),  getCurrentMap());
				getCurrentMap().addEntity(newBullet);
			} catch (SlickException e) {
				e.printStackTrace();
			}
			fireTime = 0;
		}
		
	}
	
	public void render(GameContainer container, StateBasedGame game_, Graphics g) {
		getSprite().draw(getPosition().x, getPosition().y);
	}
	
	public void handleCollisionWithEnemy(Enemy enemy) {
		System.out.println("Player collided with enemy.");
	}
	public Vector2f getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2f velocity) {
		this.velocity = velocity;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}
}
