package gui.building;

import entities.Camera;
import gui.core.GUIImage;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class BuildingIcon extends GUIImage {
	
	private BuildingType type;
	
	public BuildingIcon(String name, BuildingType type, Image image, Vector2f position) {
		super(name, position, image);
		this.type = type;
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game_, int delay, Camera camera) {
	}
	
	public void render(Graphics g) {
		getImage().draw((int)getPosition().x, (int)getPosition().y);
	}
	
	public BuildingType getType() {
		return type;
	}
	public void setType(BuildingType type) {
		this.type = type;
	}
}
