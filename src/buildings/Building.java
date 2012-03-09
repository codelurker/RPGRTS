package buildings;

import map.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import entities.Entity;

public class Building extends Entity {
	
	private String name;
	private int team;
	private int health;

	public Building(String name, Image sprite, Vector2f position, Map currentMap, int team) {
		super(sprite, position, currentMap);
		this.team = team;
		this.name = name; 
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game_, Graphics g) { 
		super.render(container, game_, g);
		g.drawString(name, getPosition().x - (getSprite().getWidth() / 2), getPosition().y + (getSprite().getHeight()));
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
}
