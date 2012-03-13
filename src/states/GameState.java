package states;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import map.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import entities.Camera;
import entities.Enemy;
import entities.Player;
import gui.BuildingMenu;
import gui.GUIMenu;

public class GameState extends BasicGameState {

	private Map currentMap;
	private Player player;
	private Camera camera;
	
	private java.util.Map<String, GUIMenu> menus = new HashMap<String, GUIMenu>();
	
	@Override
	public int getID() {
		return 1;
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game_) {
		currentMap = new Map(1, "DebugMap");
		
		player = new Player(currentMap);
		
		try {
			Enemy enemy = new Enemy(new Vector2f(200,200), new Image("data/sprites/enemy-1.png"), currentMap);
			currentMap.addEntity(enemy);
		} catch(SlickException e) {
			e.printStackTrace();
		}
		
		camera = new Camera(new Vector2f(0,0));
		
		BuildingMenu buildingMenu = new BuildingMenu(1, "Building", new Vector2f(0,0));
		menus.put("Building", buildingMenu);
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game_, int delay) {
		if (container.getInput().isKeyDown(Input.KEY_LEFT)) {
			
		}
		if (container.getInput().isKeyDown(Input.KEY_RIGHT)) {
			
		}
		if (container.getInput().isKeyDown(Input.KEY_UP)) {
			
		}
		if (container.getInput().isKeyDown(Input.KEY_DOWN)) {
			
		}
		
		if (container.getInput().isKeyPressed(Input.KEY_B)) {
			System.out.println("Pressed B");
		}
		
		camera.update(container.getInput().getAbsoluteMouseX(), container.getInput().getAbsoluteMouseY(), currentMap.getTileMap().getWidth(), currentMap.getTileMap().getHeight());
		currentMap.update(container, game_, delay);
		
		Iterator<Entry<String, GUIMenu>> it = menus.entrySet().iterator();
		while (it.hasNext()) {
			java.util.Map.Entry entry = (java.util.Map.Entry) it.next();
			GUIMenu menu = (GUIMenu)entry.getValue();
			if (menu.isActive())
				menu.update(container, game_, delay, player, camera);
		}
		
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game_, Graphics g) {
		g.translate(camera.getPosition().x, camera.getPosition().y);
		currentMap.render(container, game_, g);
		
		g.resetTransform();
		
		g.fillRect(0, container.getHeight() - 100, container.getWidth(), 100);

		Iterator<Entry<String, GUIMenu>> it = menus.entrySet().iterator();
		while (it.hasNext()) {
			java.util.Map.Entry entry = (java.util.Map.Entry) it.next();
			GUIMenu menu = (GUIMenu)entry.getValue();
			if (menu.isActive())
				menu.render(container, game_, g, player);
		}
	}
}
