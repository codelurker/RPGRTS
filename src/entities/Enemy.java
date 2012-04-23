package entities;

import map.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class Enemy extends Entity {

	private EnemyListener listener;

	public Enemy(Vector2f position, Image sprite, Map map) {
		super(sprite, position, map);
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game_, int delay) {
		if (this.isAlive() && !this.isCollided()) {
			setPosition(new Vector2f(getPosition().x, getPosition().y+1));
		}
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game_, Graphics g) {
		if (this.isAlive()) {
			super.render(container, game_, g);
			g.drawString(getPosition().x+","+getPosition().y, getPosition().x - (getSprite().getWidth() / 2), getPosition().y + (getSprite().getHeight()));
		}
	}
	
	public void kill() {
		setAlive(false);
		if (listener != null)
			listener.enemyKilled(this);
	}
	
	public EnemyListener getListener() {
		return listener;
	}

	public void setListener(EnemyListener listener) {
		this.listener = listener;
	}
}
