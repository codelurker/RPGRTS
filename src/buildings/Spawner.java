package buildings;

import map.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;



public class Spawner extends Building {
	private SpawnerType type;
	
	private int spawnTime = 0;
	private int spawnRate = 1000; // MS
	
	public Spawner(String name, Image sprite, Vector2f position, SpawnerType type, Map currentMap, int team) {
		super(name, sprite, position, currentMap, team);
		this.type = type;
	}

	@Override
	public void update(GameContainer container, StateBasedGame game_, int delay) {
		spawnTime += delay;
		if (spawnTime > spawnRate) {
			System.out.println("Spawn enemy: " + type.toString());
			spawnTime = 0;
		}
	}
	
	public SpawnerType getType() {
		return type;
	}

	public void setType(SpawnerType type) {
		this.type = type;
	}

	public int getSpawnTime() {
		return spawnTime;
	}

	public void setSpawnTime(int spawnTime) {
		this.spawnTime = spawnTime;
	}
}
