package gui.core;

import entities.Camera;
import gui.events.GUIComponentClickEvent;
import gui.events.GUIComponentClickEventListener;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class GUIComponent {
	
	private String name;
	private Vector2f position;
	private boolean enabled;
	
	private List<EventListener> eventListeners = new ArrayList<EventListener>();
	
	public GUIComponent(String name, Vector2f position) {
		this.name = name;
		this.position = position;
		this.enabled = false;
	}
	
	public void update(GameContainer container, StateBasedGame game_, int delay, Camera camera) { }
	public void render(GameContainer container, StateBasedGame game_, Graphics g) { }
	
	public synchronized void addListener(EventListener e) {
		eventListeners.add(e);
	}
	
	public synchronized void clickEvent(GUIComponentClickEvent e) {
		for (EventListener listener : eventListeners) {
			if (listener instanceof GUIComponentClickEventListener) {
				((GUIComponentClickEventListener) listener).componentClicked(e);
			}
		}
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
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	
}