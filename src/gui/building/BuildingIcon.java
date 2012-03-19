package gui.building;

import entities.Camera;
import gui.core.GUIImage;
import gui.events.GUIComponentClickEvent;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import util.CollisionHelper;

public class BuildingIcon extends GUIImage {
	
	private BuildingType type;
	
	public BuildingIcon(String name, BuildingType type, Image image, Vector2f position) {
		super(name, position, image);
		this.type = type;
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game_, int delay, Camera camera) {
		if (container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			if (CollisionHelper.intersectingShapes(
					container.getInput().getAbsoluteMouseX(), container.getInput().getAbsoluteMouseY(), 0, 0, 
					(int)getPosition().x, (int)getPosition().y, getImage().getWidth(), getImage().getHeight())) {
				this.clickEvent(new GUIComponentClickEvent(this, container.getInput().getMouseX(), container.getInput().getMouseY()));
			}
		}
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
