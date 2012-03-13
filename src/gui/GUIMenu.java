package gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import entities.Camera;
import entities.Player;

public class GUIMenu {
	private int id;
	private String name;
	private Vector2f position;
	private boolean active;
	
	public GUIMenu(int id, String name, Vector2f position) {
		this.id = id;
		this.name = name;
		this.position = position;
		active = true;
	}
	
	public void update(GameContainer container, StateBasedGame game, int delay, Player player, Camera camera) { }
	public void render(GameContainer container, StateBasedGame game_, Graphics g, Player player) { }
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}
}
