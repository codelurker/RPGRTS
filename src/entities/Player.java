package entities;

import map.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

public class Player {
	
	//private float FIRE_DELAY = 0.1F;
	//private float fireTime = 0.0F;
	
	public Player(Map map) {
		
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
}
