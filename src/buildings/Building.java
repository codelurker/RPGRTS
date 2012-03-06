package buildings;

import map.Map;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

import entities.Entity;

public class Building extends Entity {
	
	private int team;

	public Building(Image sprite, Vector2f position, Map currentMap, int team) {
		super(sprite, position, currentMap);
		this.team = team;
	}
	
	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}
}
