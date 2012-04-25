package states;

import java.awt.Dimension;
import java.util.List;

import map.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import ai.Pathfinder;

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

		camera = new Camera(new Vector2f(0,0));
		
		player = new Player(currentMap, camera);
		container.getInput().addMouseListener(player);
		//Pathfinder path = new Pathfinder(currentMap);
		//List<Vector2f> pathList = path.FindPath(new Vector2f(0,0), new Vector2f(10,10));
		//for(Vector2f point : pathList)
		//	System.out.println(point.toString());
		try {
			Enemy enemy = new Enemy(new Vector2f(200,200), new Image("data/sprites/enemy-1.png"), currentMap);
			currentMap.addEntity(enemy);
		} catch(SlickException e) {
			e.printStackTrace();
		}
		
		guiContainer = new GUIContainer();
		try {
			
			BuildingWindow buildingWindow = new BuildingWindow("building_window", new Vector2f(0, container.getHeight() - 100), new Image("data/sprites/main-window-bg.png"));
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
			test_icon.setSize(new Dimension(test_icon.getImage().getWidth(), test_icon.getImage().getHeight()));
			buildingWindow.addComponent(test_icon);
			
			BuildingIcon turretIcon = new BuildingIcon(
					"basic_turret_icon",
					BuildingType.BASIC_TURRET_ALLY,
					new Image("data/sprites/BASIC_TURRET_ALLY_ICON.png"),
					new Vector2f(350, 10));
			turretIcon.setEnabled(true);
			turretIcon.addListener(new GUIComponentClickEventListener() {
				public void componentClicked(GUIComponentClickEvent e) {
					if (e.getSource() instanceof BuildingIcon) {
						BuildingIcon source = (BuildingIcon) e.getSource();
						player.setBuilding(true);
						player.setRequestedBuilding(source.getType());
					}
				}
			});
			turretIcon.setSize(new Dimension(turretIcon.getImage().getWidth(), turretIcon.getImage().getHeight()));
			buildingWindow.addComponent(turretIcon);
			container.getInput().addMouseListener(buildingWindow);
			guiContainer.addWindow(buildingWindow);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void update(final GameContainer container, final StateBasedGame game_, final int delay) {
		
		camera.update(container.getInput().getAbsoluteMouseX(), container.getInput().getAbsoluteMouseY(), currentMap.getTileMap().getWidth(), currentMap.getTileMap().getHeight());
		currentMap.update(container, game_, delay);
		player.update(container, game_, delay);
		
		// Update the GUI on a separate thread to prevent any "lag" with the GUI
		Runnable guiRunnable = new Runnable() {
			public void run() {
				guiContainer.update(container, game_, delay, camera);
			}
		};
		Thread guiThread = new Thread(guiRunnable);
		
		guiThread.start();
		
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
