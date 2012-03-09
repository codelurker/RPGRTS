package states;

import map.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import entities.Camera;
import entities.Enemy;
import entities.Player;

public class GameState extends BasicGameState {

	private Map currentMap;
	private Player player;
	private Camera camera;
	
	@Override
	public int getID() {
		return 1;
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game_) {
		currentMap = new Map(1, "DebugMap");
		
		try {
			player = new Player(new Vector2f(container.getWidth() / 2 + 40, container.getHeight() / 2), new Image("data/sprites/player.png"), currentMap);
		} catch(SlickException e) {
			e.printStackTrace();
		}
		currentMap.addEntity(player);
		
		try {
			Enemy enemy = new Enemy(new Vector2f(200,200), new Image("data/sprites/enemy-1.png"), currentMap);
			currentMap.addEntity(enemy);
		} catch(SlickException e) {
			e.printStackTrace();
		}
		
		camera = new Camera(new Vector2f(container.getWidth() / 2, container.getHeight() / 2));
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game_, int delay) {
		if (container.getInput().isKeyPressed(Input.KEY_B)) {
			System.out.println("Pressed B");
		}
		
		camera.update((Player)currentMap.getEntities(Player.class).get(0));
		currentMap.update(container, game_, delay);
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game_, Graphics g) {
		g.translate((container.getWidth() / 2) - camera.getPosition().x, (container.getHeight() / 2) - camera.getPosition().y);
		currentMap.render(container, game_, g);
		g.resetTransform();
		g.fillRect(0, container.getHeight() - 100, container.getWidth(), 100);
	}
}
