package util;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import gui.building.BuildingType;

public class ResourceManager {
	public static Map<BuildingType, Image> buildingSprites = new HashMap<BuildingType, Image>();
	
	public static Map<BuildingType, Image> getBuildingSprites() {
		if (buildingSprites == null)
			 buildingSprites = new HashMap<BuildingType, Image>();
		
		return buildingSprites;
	}
	
	public static Image getBuildingSprite(BuildingType type) {
		if (buildingSprites.containsKey(type)) {
			return buildingSprites.get(type);
		} else {
			try {
				Image building = new Image("data/sprites/" + type.toString() + ".png");
				buildingSprites.put(type, building);
				return buildingSprites.get(type);
			} catch (SlickException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
	
	public static Image getBuildingIconSprite(BuildingType type) {
		if (buildingSprites.containsKey(type)) {
			return buildingSprites.get(type);
		} else {
			try {
				Image building = new Image("data/sprites/" + type.toString() + ".png");
				buildingSprites.put(type, building);
				return buildingSprites.get(type);
			} catch (SlickException e) {
				e.printStackTrace();
				return null;
			}
		}		
	}
}
