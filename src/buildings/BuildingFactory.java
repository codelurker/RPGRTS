package buildings;

import map.Map;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class BuildingFactory {
	public static Spawner CreateSpawner(Vector2f position, SpawnerType type, Map currentMap, int team) {
		Spawner spawner = null;
		
		try {
			if (type == SpawnerType.ENEMY1) {
				spawner = new Spawner(new Image("data/sprites/spawner-ENEMY1.png"), position, type, currentMap, team);
				System.out.println("Creating new spawner");
			}
		} catch(SlickException e) {
			e.printStackTrace();
		}
		
		return spawner;
	}
}
