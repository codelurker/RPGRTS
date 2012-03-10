package entities;

import org.newdawn.slick.geom.Vector2f;

public class Camera {
	private Vector2f position;
	
	public Camera(Vector2f position) {
		this.position = position;
	}
	
	public void update(int mousex, int mousey, int mapWidth, int mapHeight) {
		if (mousex >= 1024 - 50)
			position.x -= 10;
		if (mousex <= 50)
			position.x += 10;
		if (mousey >= mapHeight * 32 - 40)
			position.y -= 10;
		if (mousey <= 50)
			position.y += 10;
		
		if (position.x > (mapWidth * 32) / 2)
			position.x = (mapWidth * 32) / 2;
		if (position.x < -((mapWidth * 32) / 2))
			position.x = -((mapWidth * 32) / 2);
		if (position.y > (mapHeight * 32) / 2)
			position.y = (mapHeight * 32) / 2;
		if (position.y < -((mapHeight * 32) / 2))
			position.y = -((mapHeight * 32) / 2);
	}
	
	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}

}
