package map;

import org.newdawn.slick.geom.Vector2f;

public class CollisionTile {

	private Vector2f position;
	private int width;
	private int height;
	
	public CollisionTile(Vector2f position, int width, int height) {
		this.position = position;
		this.width = width;
		this.height = height;
	}
	
	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
