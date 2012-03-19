package states;

import map.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import util.ResourceManager;

import entities.Camera;
import entities.Enemy;
import entities.Player;
import gui.building.BuildingIcon;
import gui.building.BuildingType;
import gui.building.BuildingWindow;
import gui.core.GUIContainer;
import gui.events.GUIComponentClickEvent;
import gui.events.GUIComponentClickEventListener;

public class GameState extends BasicGameState {

	private Map currentMap;
	private Player player;
	private Camera camera;
	
	private GUIContainer guiContainer;
	
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
		
		guiContainer = new GUIContainer();
		try {
			
			final BuildingWindow buildingWindow = new BuildingWindow("building_window", new Vector2f(0, container.getHeight() - 100), new Image("data/sprites/main-window-bg.png"));
			buildingWindow.setEnabled(true);
			
			BuildingIcon test_icon = new BuildingIcon(
							"command_center_icon",
							BuildingType.COMMAND_CENTER_ALLY,
							new Image("data/sprites/COMMAND_CENTER_ALLY_ICON.png"),
							new Vector2f(300, 10)
							);
			
			test_icon.setEnabled(true);
			test_icon.addListener(new GUIComponentClickEventListener() {
				public void componentClicked(GUIComponentClickEvent e) {
					if (e.getSource() instanceof BuildingIcon) {
						BuildingIcon source = (BuildingIcon) e.getSource();
						player.setBuilding(true);
						player.setRequestedBuilding(source.getType());
					}
				}
			});
			buildingWindow.addComponent(test_icon);
			
			guiContainer.addWindow(buildingWindow);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
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
		player.update(container, game_, delay, camera);
		guiContainer.update(container, game_, delay, camera);
		
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game_, Graphics g) {
		g.translate(camera.getPosition().x, camera.getPosition().y);
		currentMap.render(container, game_, g);
		
		g.resetTransform();
		
		if (player.isBuilding()) {
			if (container.getInput().getAbsoluteMouseY() <= 670)
				ResourceManager.getBuildingSprite(player.getRequestedBuilding()).draw(container.getInput().getAbsoluteMouseX(), container.getInput().getAbsoluteMouseY());
		}
		guiContainer.render(container, game_, g);
	}
}
