package states;

import java.util.ArrayList;
import java.util.List;

import map.CollisionTile;
import map.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import buildings.Building;

import util.ResourceManager;

import entities.Camera;
import entities.Enemy;
import entities.Player;
import gui.BuildingIcon;
import gui.BuildingType;

public class GameState extends BasicGameState {

	private Map currentMap;
	private Player player;
	private Camera camera;
	
	private List<BuildingIcon> buildingIcons = new ArrayList<BuildingIcon>();
	private boolean building = false;
	private BuildingType requestedBuilding;
	
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
		
		buildingIcons.add(new BuildingIcon(BuildingType.COMMAND_CENTER_ALLY, ResourceManager.getBuildingSprite(BuildingType.COMMAND_CENTER_ALLY), new Vector2f(300, 700)));
		
		camera = new Camera(new Vector2f(0,0));
		
		ResourceManager.getBuildingSprite(BuildingType.COMMAND_CENTER_ALLY);
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
		
		Vector2f worldMousePosition = new Vector2f(container.getInput().getMouseX() - camera.getPosition().x, container.getInput().getMouseY() - camera.getPosition().y);
		
		if (!building) {
			if (container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				if (container.getInput().getAbsoluteMouseY() >= 600) {
					for (BuildingIcon icon : buildingIcons) {
						if ((container.getInput().getAbsoluteMouseX() >= icon.getPosition().x && container.getInput().getAbsoluteMouseX() <= icon.getPosition().x + icon.getSprite().getWidth())
								&& (container.getInput().getAbsoluteMouseY() >= icon.getPosition().y && container.getInput().getAbsoluteMouseY() <= icon.getPosition().y + icon.getSprite().getHeight())) {
									requestedBuilding = icon.getType();
									building = true;
						}
					}
				}		
			}
		} else {
			if (container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				boolean validPlacement = true;
				
				for (CollisionTile tile : currentMap.getCollisionLayer()) {
					if ((worldMousePosition.x + ResourceManager.getBuildingSprite(requestedBuilding).getWidth() >= tile.getPosition().x && worldMousePosition.x <= tile.getPosition().x + tile.getWidth()) 
							&& (worldMousePosition.y + ResourceManager.getBuildingSprite(requestedBuilding).getWidth() >= tile.getPosition().y && worldMousePosition.y <= tile.getPosition().y + tile.getHeight())) {
						validPlacement = false;
						break;
					}
				}
				
				System.out.println(ResourceManager.getBuildingSprite(requestedBuilding).getWidth());
				if (validPlacement) {
					currentMap.addEntity(
							new Building(
									requestedBuilding.toString(),
									ResourceManager.getBuildingSprite(requestedBuilding),
									worldMousePosition,
									currentMap, 0)
							);
					building = false;
				}
			}	
		}
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game_, Graphics g) {
		g.translate(camera.getPosition().x, camera.getPosition().y);
		currentMap.render(container, game_, g);
		
		g.resetTransform();
		
		g.fillRect(0, container.getHeight() - 100, container.getWidth(), 100);
		
		for (BuildingIcon icon : buildingIcons) {
			icon.render(g);
		}
		
		if (building) {
			if (container.getInput().getAbsoluteMouseY() <= 670)
				ResourceManager.getBuildingSprite(requestedBuilding).draw(container.getInput().getAbsoluteMouseX(), container.getInput().getAbsoluteMouseY());
		}
		
	}
}
