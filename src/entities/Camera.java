package entities;

import org.newdawn.slick.geom.Vector2f;

public class Camera {
	private Vector2f position;
	
	public Camera(Vector2f position) {
		this.position = position;
	}
	
	public void update(Player player) {
		setPosition(new Vector2f(player.getPosition().x, player.getPosition().y));
	}
	
	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}

}
