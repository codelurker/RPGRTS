package entities;

import java.util.List;
import java.util.Queue;

import map.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import buildings.Building;

import ai.Pathfinder;

public class Enemy extends Entity {

	private EnemyListener listener;
	
	private Entity target;
	private Queue<Vector2f> pathToTarget;
	
	public Enemy(Vector2f position, Image sprite, Map map) {
		super(sprite, position, map);
		//Pathfinder pathfinder = new Pathfinder(map);
		//Building closestBuilding = (Building)findClosestBuilding();
		//pathToTarget = (Queue<Vector2f>) pathfinder.FindPath(new Vector2f(this.getPosition().x / 32, this.getPosition().y / 32), new Vector2f(closestBuilding.getPosition().x / 32, closestBuilding.getPosition().y / 32));
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game_, int delay) {
		if (this.isAlive() && !this.isCollided()) {
			setPosition(new Vector2f(getPosition().x, getPosition().y+1));
			/*if (pathToTarget.size() > 0) {
				for (Vector2f pos : pathToTarget) {
					setPosition(new Vector2f(pos.x * 32, pos.y * 32));
					pathToTarget.remove(pos);
				}
			}*/
		}
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game_, Graphics g) {
		if (this.isAlive()) {
			super.render(container, game_, g);
		}
	}
	
	public void kill() {
		setAlive(false);
		if (listener != null)
			listener.enemyKilled(this);
	}
	
	// Enemies will attack the closest building.
	// Create a path using Pathfinder to the closest building
	public Entity findClosestBuilding() {
		Entity returnEntity = null;
		float distance = Float.MAX_VALUE;
		
		for (Entity entity : this.getCurrentMap().getEntities(Building.class)) {
			Building building = (Building) entity; 
			// only search for player buildings
			if (building.getTeam() == 0) {
				float distanceBetweenBuildingAndEnemy = building.getPosition().distance(this.getPosition());
				if (distanceBetweenBuildingAndEnemy < distance) {
					distance = building.getPosition().distance(this.getPosition());
					returnEntity = building;
				}
			}
		}
		
		if (returnEntity != null)
			System.out.println("Closest: " + returnEntity.getPosition());
		return returnEntity;
	}
	
	public EnemyListener getListener() {
		return listener;
	}

	public void setListener(EnemyListener listener) {
		this.listener = listener;
	}

	public Entity getTarget() {
		return target;
	}

	public void setTarget(Entity target) {
		this.target = target;
	}

	public Queue<Vector2f> getPathToTarget() {
		return pathToTarget;
	}

	public void setPathToTarget(Queue<Vector2f> pathToTarget) {
		this.pathToTarget = pathToTarget;
	}
}
