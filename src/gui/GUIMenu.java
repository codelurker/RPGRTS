package gui;

import org.newdawn.slick.geom.Vector2f;

public class GUIMenu {
	private int id;
	private String name;
	private Vector2f position;
	
	public GUIMenu(int id, String name, Vector2f position) {
		this.id = id;
		this.name = name;
		this.position = position;
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
