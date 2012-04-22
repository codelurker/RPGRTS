package map;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import util.CollisionHelper;

import buildings.Building;
import buildings.BuildingFactory;
import buildings.SpawnerType;

import entities.Bullet;
import entities.Enemy;
import entities.Entity;
import gui.building.BuildingType;

public class Map {
	private int id;
	private String name;
	private TiledMap tileMap;
	private List<CollisionTile> collisionLayer = new ArrayList<CollisionTile>();
	private List<Entity> entities = new ArrayList<Entity>();

	public Map(int id, String name) {
		this.id = id;
		this.name = name;
		
		try {
			tileMap = new TiledMap("data/maps/" + id + ".tmx");
		} catch(SlickException e) {
			e.printStackTrace();
		}
		
		for (int x = 0; x < tileMap.getWidth(); x++) {
			for (int y = 0; y < tileMap.getHeight(); y++) {
				Image tImg = tileMap.getTileImage(x, y, 1);
				if (tImg != null) {
					CollisionTile collisionTile = new CollisionTile(new Vector2f(x * tImg.getWidth(), y * tImg.getHeight()), tImg.getWidth(), tImg.getHeight());
					collisionLayer.add(collisionTile);
				}
				Image spImg = tileMap.getTileImage(x, y, 2);
				if (spImg != null) {
					entities.add(BuildingFactory.CreateSpawner(new Vector2f(x * spImg.getWidth(), y * spImg.getHeight()), SpawnerType.ENEMY1, this, 0));
				}
			}
		}
		
		entities.add(BuildingFactory.CreateCommandCenter(new Vector2f(400, 300), this, 0));
	}
	
	public void update(GameContainer container, StateBasedGame game_, int delay) {
		
		List<Entity> entitiesToRemove = new ArrayList<Entity>();
		
		for (int i = 0; i < entities.size(); i++) {
			Entity currentEntity = entities.get(i);
			
			// Check for collision for enemies
			if (currentEntity instanceof Enemy) {
				// Check for collision between enemy and buildings
				for (Entity entity : getEntities(Building.class)) {
					Building building = (Building) entity;
					if (CollisionHelper.intersectingShapes(
							(int)currentEntity.getPosition().x, 
							(int)currentEntity.getPosition().y, 
							currentEntity.getSprite().getWidth(), 
							currentEntity.getSprite().getHeight(), 
							(int)building.getPosition().x, (int)building.getPosition().y, building.getSprite().getWidth(), building.getSprite().getHeight())) {

						entities.get(i).setCollided(true);
					}
				}
				
				// Collision between enemy and bullets
				for (Entity entity : getEntities(Bullet.class)) {
					Bullet bullet = (Bullet) entity;
					if (CollisionHelper.intersectingShapes(
							(int)currentEntity.getPosition().x, (int)currentEntity.getPosition().y, 
							currentEntity.getSprite().getWidth(), currentEntity.getSprite().getHeight(),
							(int)bullet.getPosition().x, (int)bullet.getPosition().y, 
							bullet.getSprite().getWidth(), bullet.getSprite().getHeight())) {
						
						bullet.setAlive(false);
						currentEntity.setAlive(false);
						entitiesToRemove.add(bullet);
						entitiesToRemove.add(currentEntity);
					}
				}
			}
			
			currentEntity.update(container, game_, delay);
			clampEntityToMap(currentEntity);
		}
		
		if (entitiesToRemove.size() > 0)
			entities.removeAll(entitiesToRemove);
	}
	
	public void render(GameContainer container, StateBasedGame game_, Graphics g) {
		tileMap.render(0, 0);
		
		for (CollisionTile collision : collisionLayer) {
			g.drawRect(collision.getPosition().x, collision.getPosition().y, 32, 32);
		}
		
		for (Entity entity : entities) {
			entity.render(container, game_, g);
		}
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TiledMap getTileMap() {
		return tileMap;
	}

	public void setTileMap(TiledMap tileMap) {
		this.tileMap = tileMap;
	}
	
	public List<CollisionTile> getCollisionLayer() {
		return collisionLayer;
	}

	public void setCollisionLayer(ArrayList<CollisionTile> collisionLayer) {
		this.collisionLayer = collisionLayer;
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public void setCollisionLayer(List<CollisionTile> collisionLayer) {
		this.collisionLayer = collisionLayer;
	}
	
	public void addEntity(Entity entity) {
		entities.add(entity);
	}
	
	public void removeEntity(Entity entity) {
		entities.remove(entity);
	}
	
	public ArrayList<Entity> getEntities(Class<? extends Entity> c) {
		ArrayList<Entity> results = new ArrayList<Entity>();
		for (Entity entity : entities) {
			if (c.isInstance(entity)) {
				results.add(entity);
			}
		}
		return results;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	
	public void clampEntityToMap(Entity entity) {
		boolean collidedWithBounds = false;
		
		if (entity.getPosition().x < 0) {
			entity.getPosition().x = 0;
			collidedWithBounds = true;
		} 
		if (entity.getPosition().x + entity.getSprite().getWidth() >  tileMap.getWidth() *  tileMap.getTileWidth()) {
			entity.getPosition().x =  tileMap.getWidth() *  tileMap.getTileWidth() - entity.getSprite().getWidth();
			collidedWithBounds = true;
		} 
		if (entity.getPosition().y < 0) {
			entity.getPosition().y = 0;
			collidedWithBounds = true;
		}
		if (entity.getPosition().y + entity.getSprite().getHeight() >  tileMap.getHeight() *  tileMap.getTileHeight()) {
			entity.getPosition().y =  tileMap.getHeight() *  tileMap.getTileHeight() - entity.getSprite().getHeight();
			collidedWithBounds = true;
		}
		
		if (collidedWithBounds)
			entity.handleMapBoundCollision();
	}
	
	public void addBuilding(BuildingType type, Vector2f position, int team) {
		switch(type) {
			case BASIC_TURRET_ALLY:
				
				break;
		}
		
	}
}