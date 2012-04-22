package buildings;

import gui.building.BuildingType;
import map.Map;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import util.ResourceManager;

public class BuildingFactory {
	public static Spawner CreateSpawner(Vector2f position, SpawnerType type, Map currentMap, int team) {
		Spawner spawner = null;
		
		try {
			if (type == SpawnerType.ENEMY1) {
				spawner = new Spawner("ENEMY1 Spawner", new Image("data/sprites/spawner-ENEMY1.png"), position, type, currentMap, team);
				System.out.println("Creating new spawner");
			}
		} catch(SlickException e) {
			e.printStackTrace();
		}
		
		return spawner;
	}
	
	public static CommandCenter CreateCommandCenter(Vector2f position, Map currentMap, int team) {
		CommandCenter cc = null;
		
		if (team == 0) { // Player team
			try {
				cc = new CommandCenter("Command Center", new Image("data/sprites/COMMAND_CENTER_ALLY.png"), position, currentMap, team);
			} catch (SlickException e) {
				e.printStackTrace();
			}
		} else {
			try {
			cc = new CommandCenter("Command Center", new Image("data/sprites/commandcenter-enemy.png"), position, currentMap, team);
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		
		return cc;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBuilding(BuildingType type, Vector2f position, Map map) {
		if (type == BuildingType.BASIC_TURRET_ALLY)
			return (T) new BasicTurret("test_turret", ResourceManager.getBuildingSprite(type), position, map, 0);
		else if (type == BuildingType.COMMAND_CENTER_ALLY)
			return (T) new CommandCenter("Command Center", ResourceManager.getBuildingSprite(type), position, map, 0);
		else
			return null;
	}
}
