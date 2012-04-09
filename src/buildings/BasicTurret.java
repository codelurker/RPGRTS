package buildings;

import map.Map;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

import entities.Entity;

public class BasicTurret extends Building {
	
	private int range; // circle radius
	private Entity target;
	private int attackPower;

	public BasicTurret(String name, Image sprite, Vector2f position, Map currentMap, int team) {
		super(name, sprite, position, currentMap, team);
		setHealth(300);
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
