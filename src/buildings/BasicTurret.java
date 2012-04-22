package buildings;

import map.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import util.CollisionHelper;

import entities.Bullet;
import entities.Enemy;
import entities.Entity;

public class BasicTurret extends Building {
	
	private int range = 100; // circle radius
	private Entity target;
	private int attackPower;
	private float FIRE_DELAY = 0.5F;
	private float fireTime = 0.0F;
	
	public BasicTurret(String name, Image sprite, Vector2f position, Map currentMap, int team) {
		super(name, sprite, position, currentMap, team);
		setHealth(300);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game_, int delay) {
		if (this.isAlive()) {
			fireTime += delay / 1000.0F;
			for (Entity entity : this.getCurrentMap().getEntities(Enemy.class)) {
				Enemy enemy = (Enemy) entity;
				if (this.getCenterPosition().distance(enemy.getCenterPosition()) <= range) {
					if (fireTime > FIRE_DELAY) {
						try {
							Bullet newBullet = new Bullet(new Image("data/sprites/bullet-1.png"), this, enemy.getCenterPosition(),  getCurrentMap());
							getCurrentMap().addEntity(newBullet);
						} catch (SlickException e) {
							e.printStackTrace();
						}
						fireTime = 0;						
					}
				}
			}
		}
	}
	
	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public Entity getTarget() {
		return target;
	}

	public void setTarget(Entity target) {
		this.target = target;
	}
	
	public int getAttackPower() {
		return attackPower;
	}

	public void setAttackPower(int attackPower) {
		this.attackPower = attackPower;
	}
	
}
