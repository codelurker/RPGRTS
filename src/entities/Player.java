package entities;

import gui.building.BuildingType;
import map.CollisionTile;
import map.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import buildings.Building;

import util.CollisionHelper;
import util.ResourceManager;

public class Player {
	
	//private float FIRE_DELAY = 0.1F;
	//private float fireTime = 0.0F;
	private boolean building = false;
	private Map currentMap;
	private BuildingType requestedBuilding;
	
	public Player(Map map) {
		currentMap = map;
	}

	public void update(GameContainer container, StateBasedGame game_, int delay, Camera camera) {
		Vector2f worldMousePosition = new Vector2f(container.getInput().getMouseX() - camera.getPosition().x, container.getInput().getMouseY() - camera.getPosition().y);

		if (building) {
			if (container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				boolean validPlacement = true;
				
				for (CollisionTile tile : currentMap.getCollisionLayer()) {
					if (CollisionHelper.intersectingShapes(
							(int)worldMousePosition.x, (int)worldMousePosition.y, ResourceManager.getBuildingSprite(requestedBuilding).getWidth(), ResourceManager.getBuildingSprite(requestedBuilding).getHeight(),
							(int)tile.getPosition().x, (int)tile.getPosition().y, tile.getWidth(), tile.getHeight())) {
						validPlacement = false;
						break;						
					}
				}
				
				for (Entity building : currentMap.getEntities(Building.class)) {
					if (CollisionHelper.intersectingShapes(
							(int)worldMousePosition.x, (int)worldMousePosition.y, ResourceManager.getBuildingSprite(requestedBuilding).getWidth(), ResourceManager.getBuildingSprite(requestedBuilding).getHeight(),
							(int)building.getPosition().x, (int)building.getPosition().y, building.getSprite().getWidth(), building.getSprite().getHeight())) {
						validPlacement = false;
						break;					
					}				
				}
				
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
		} else {
			
		}
		
		/*fireTime += delay / 1000.0F;
		
		if (container.getInput().isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON) && fireTime > FIRE_DELAY) {
			try {
				Bullet newBullet = new Bullet(new Image("data/sprites/bullet-1.png"), this, new Vector2f(container.getInput().getAbsoluteMouseX(), container.getInput().getAbsoluteMouseY()),  getCurrentMap());
				getCurrentMap().addEntity(newBullet);
			} catch (SlickException e) {
				e.printStackTrace();
			}
			fireTime = 0;
		}*/
	}
	
	public void render(GameContainer container, StateBasedGame game_, Graphics g) {

	}
	
	public BuildingType getRequestedBuilding() {
		return requestedBuilding;
	}

	public void setRequestedBuilding(BuildingType requestedBuilding) {
		this.requestedBuilding = requestedBuilding;
	}

	public boolean isBuilding() {
		return building;
	}

	public void setBuilding(boolean building) {
		this.building = building;
	}

	public Map getCurrentMap() {
		return currentMap;
	}

	public void setCurrentMap(Map currentMap) {
		this.currentMap = currentMap;
	}
}
