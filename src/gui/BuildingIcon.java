package gui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

public class BuildingIcon {
	
	private Vector2f position;
	private Image sprite;
	private BuildingType type;
	
	public BuildingIcon(BuildingType type, Image sprite, Vector2f position) {
		this.type = type;
		this.sprite = sprite;
		this.position = position;
	}
	
	public void render(Graphics g) {
		sprite.draw((int)position.x, (int)position.y);
	}
	
	public Vector2f getPosition() {
		return position;
	}
	public void setPosition(Vector2f position) {
		this.position = position;
	}
	public Image getSprite() {
		return sprite;
	}
	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}
	public BuildingType getType() {
		return type;
	}
	public void setType(BuildingType type) {
		this.type = type;
	}
}
