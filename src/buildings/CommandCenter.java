package buildings;

import map.Map;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

public class CommandCenter extends Building {
	public CommandCenter(String name, Image sprite, Vector2f position, Map currentMap, int team) {
		super(name, sprite, position, currentMap, team);
		setHealth(1000);
	}
}
