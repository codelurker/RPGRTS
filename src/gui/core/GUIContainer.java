package gui.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import entities.Camera;

/*
 * Collection of GUIWindows
 */

public class GUIContainer {
	
	private Map<String, GUIWindow> windows = new HashMap<String, GUIWindow>();
	
	public void update(GameContainer container, StateBasedGame game_, int delay, Camera camera) {
		Iterator<Entry<String, GUIWindow>> it = windows.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, GUIWindow> entry = (Entry<String, GUIWindow>) it.next();
			GUIWindow window = (GUIWindow)entry.getValue();
			if (window.isEnabled())
				window.update(container, game_, delay, camera);
		}
	}
	
	public void render(GameContainer container, StateBasedGame game_, Graphics g) {
		Iterator<Entry<String, GUIWindow>> it = windows.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, GUIWindow> entry = (Entry<String, GUIWindow>) it.next();
			GUIWindow window = (GUIWindow)entry.getValue();
			if (window.isEnabled())
				window.render(container, game_, g);
		}		
	}
	
	public void addWindow(GUIWindow window) {
		if (!windows.containsKey(window.getName())) {
			windows.put(window.getName(), window);
		}
	}
}
