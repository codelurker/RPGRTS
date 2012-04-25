package buildings;

import java.util.ArrayList;
import java.util.List;

import map.CollisionTile;
import map.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import entities.Enemy;
import entities.EnemyListener;

import util.CollisionHelper;
import util.ResourceManager;



public class Spawner extends Building {
	private SpawnerType type;
	
	private int spawnTime = 0;
	private int spawnRate = 1000; // MS
	private int spawns = 0;
	
	public Spawner(String name, Image sprite, Vector2f position, SpawnerType type, Map currentMap, int team) {
		super(name, sprite, position, currentMap, team);
		this.type = type;
	}

	@Override
	public void update(GameContainer container, StateBasedGame game_, int delay) {
		spawnTime += delay;
		if (spawnTime > spawnRate) {
			if (spawns < 3) {
				Enemy enemy = new Enemy(findSpawnPosition(), ResourceManager.getEnemySprite(SpawnerType.ENEMY1), getCurrentMap());
				enemy.setListener(new EnemyListener() {
					public void enemyKilled(Enemy enemy) {
						spawns--;
					}
				});
				getCurrentMap().addEntity(enemy);
				spawnTime = 0;
				spawns++;
			}
		}
	}
	
	private Vector2f findSpawnPosition() {
		Vector2f spawnPos = new Vector2f();
		for (Vector2f position : getNeighborPositions()) {
			for (CollisionTile collisionTile : this.getCurrentMap().getCollisionLayer()) {
				if (!CollisionHelper.intersectingShapes(
						(int)position.x, (int)position.y, 32, 32,
						(int)collisionTile.getPosition().x, (int)collisionTile.getPosition().y, 32, 32)) {
					spawnPos = position;
				}
			}
		}
		return spawnPos;
	}
	
	private List<Vector2f> getNeighborPositions() {
		List<Vector2f> positions = new ArrayList<Vector2f>();
		
		positions.add(new Vector2f(this.getPosition().x - 32, this.getPosition().y)); // Left
		positions.add(new Vector2f(this.getPosition().x + this.getSprite().getWidth() + 32, this.getPosition().y)); // Right
		positions.add(new Vector2f(this.getPosition().x, this.getPosition().y - 32)); // Top
		positions.add(new Vector2f(this.getPosition().x, this.getPosition().y + this.getSprite().getHeight() + 32)); // Bottom
		
		return positions;
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
