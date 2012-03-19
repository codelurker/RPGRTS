package gui.core;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import entities.Camera;

public class GUIImage extends GUIComponent {
	
	private Image image;
	
	public GUIImage(String name, Vector2f position, Image image) {
		super(name, position);
		this.image = image;
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game_, int delay, Camera camera) { 
		
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game_, Graphics g) {
		image.draw(getPosition().x, getPosition().y);
	}
	
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}
