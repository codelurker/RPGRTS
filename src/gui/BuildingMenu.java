package gui;

import java.util.ArrayList;
import java.util.List;

import map.CollisionTile;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import util.CollisionHelper;
import util.ResourceManager;
import buildings.Building;
import entities.Camera;
import entities.Entity;
import entities.Player;

public class BuildingMenu extends GUIMenu {
	private List<BuildingIcon> buildingIcons = new ArrayList<BuildingIcon>();
	private BuildingType requestedBuilding;
	
	public BuildingMenu(int id, String name, Vector2f position) {
		super(id, name, position);		
		buildingIcons.add(new BuildingIcon(BuildingType.COMMAND_CENTER_ALLY, ResourceManager.getBuildingSprite(BuildingType.COMMAND_CENTER_ALLY), new Vector2f(300, 700)));
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delay, Player player, Camera camera) {
		
		Vector2f worldMousePosition = new Vector2f(container.getInput().getMouseX() - camera.getPosition().x, container.getInput().getMouseY() - camera.getPosition().y);
		
		if (!player.isBuilding()) {
			if (container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				if (container.getInput().getAbsoluteMouseY() >= 600) {
					for (BuildingIcon icon : buildingIcons) {
						if (CollisionHelper.intersectingShapes(
								(int)container.getInput().getMouseX(), (int)container.getInput().getMouseY(), 0, 0,
								(int)icon.getPosition().x, (int)icon.getPosition().y, icon.getSprite().getWidth(), icon.getSprite().getHeight())) {
							requestedBuilding = icon.getType();
							player.setBuilding(true);							
						}
					}
				}		
			}
		} else {
			if (container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				boolean validPlacement = true;
				
				for (CollisionTile tile : player.getCurrentMap().getCollisionLayer()) {
					if (CollisionHelper.intersectingShapes(
							(int)worldMousePosition.x, (int)worldMousePosition.y, ResourceManager.getBuildingSprite(requestedBuilding).getWidth(), ResourceManager.getBuildingSprite(requestedBuilding).getHeight(),
							(int)tile.getPosition().x, (int)tile.getPosition().y, tile.getWidth(), tile.getHeight())) {
						validPlacement = false;
						break;						
					}
				}
				
				for (Entity building : player.getCurrentMap().getEntities(Building.class)) {
					if (CollisionHelper.intersectingShapes(
							(int)worldMousePosition.x, (int)worldMousePosition.y, ResourceManager.getBuildingSprite(requestedBuilding).getWidth(), ResourceManager.getBuildingSprite(requestedBuilding).getHeight(),
							(int)building.getPosition().x, (int)building.getPosition().y, building.getSprite().getWidth(), building.getSprite().getHeight())) {
						validPlacement = false;
						break;					
					}				
				}
				
				if (validPlacement) {
					player.getCurrentMap().addEntity(
							new Building(
									requestedBuilding.toString(),
									ResourceManager.getBuildingSprite(requestedBuilding),
									worldMousePosition,
									player.getCurrentMap(), 0)
							);
					player.setBuilding(false);
				}
			}	
		}
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game_, Graphics g, Player player) {
		
		for (BuildingIcon icon : buildingIcons) {
			icon.render(g);
		}
		
		if (player.isBuilding()) {
			if (container.getInput().getAbsoluteMouseY() <= 670)
				ResourceManager.getBuildingSprite(requestedBuilding).draw(container.getInput().getAbsoluteMouseX(), container.getInput().getAbsoluteMouseY());
		}
	}
} 
