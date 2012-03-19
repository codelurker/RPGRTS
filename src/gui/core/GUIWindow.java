package gui.core;

import entities.Camera;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

/*
 * Collection of GUIComponents
 * 		GUIComponents positions are based on the windows position.
 */

public class GUIWindow extends GUIComponent {
	
	private Image background;
	private Map<String, GUIComponent> components = new HashMap<String, GUIComponent>();
	
	public GUIWindow(String name, Vector2f position, Image background) {
		super(name, position);
		this.background = background;
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game_, int delay, Camera camera) {
		Iterator<Entry<String, GUIComponent>> it = components.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, GUIComponent> entry = (Entry<String, GUIComponent>) it.next();
			GUIComponent component = (GUIComponent)entry.getValue();
			if (component.isEnabled())
				component.update(container, game_, delay, camera);
		}
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game_, Graphics g) {
		background.draw(getPosition().x, getPosition().y);
		
		Iterator<Entry<String, GUIComponent>> it = components.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, GUIComponent> entry = (Entry<String, GUIComponent>) it.next();
			GUIComponent component = (GUIComponent)entry.getValue();
			if (component.isEnabled())
				component.render(container, game_, g);
		}		
	}
	
	public void addComponent(GUIComponent component) {
		if (!components.containsKey(component.getName())) {
			component.setPosition(component.getPosition().add(this.getPosition()));
			components.put(component.getName(), component);
		}
	}
}
