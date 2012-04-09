package entities;

import gui.building.BuildingType;
import map.CollisionTile;
import map.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import buildings.Building;

import util.CollisionHelper;
import util.ResourceManager;

public class Player implements MouseListener {
	
	//private float FIRE_DELAY = 0.1F;
	//private float fireTime = 0.0F;
	private boolean building = false;
	private Map currentMap;
	private Camera camera;
	private BuildingType requestedBuilding;
	
	public Player(Map map, Camera camera) {
		currentMap = map;
		this.camera = camera;
	}

	public void update(GameContainer container, StateBasedGame game_, int delay) {
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

	@Override
	public void inputEnded() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputStarted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isAcceptingInput() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void setInput(Input arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(int button, int x, int y) {		
		Vector2f worldMousePosition = new Vector2f(x - camera.getPosition().x, y - camera.getPosition().y);

		if (building) {
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
		} else {
			
		}		
	}

	@Override
	public void mouseReleased(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseWheelMoved(int arg0) {
		// TODO Auto-generated method stub
		
	}
}
