package states;

import java.awt.Font;
import java.awt.Point;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MenuState extends BasicGameState {
	
	private StateBasedGame game;
	
	private MenuItem[] menuItems = {
			new MenuItem("Start", new Point(350, 400), new UnicodeFont(new Font("Arial", Font.PLAIN, 24)), 1),
			new MenuItem("End", new Point(350, 445), new UnicodeFont(new Font("Arial", Font.PLAIN, 24)), 0)
	};
	
	public MenuState() {
	
	}
	
	@Override
	public int getID() {
		return 0;
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game_) {
		this.game = game_;
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game_, int delay) {
	
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game_, Graphics g) {
		g.drawString("RPGRTS Game.", 350, 100);
		for (MenuItem menuitem : menuItems) {
			g.setFont(menuitem.getFont());
			menuitem.getFont().drawString( menuitem.getPosition().x, menuitem.getPosition().y, menuitem.getText(), Color.white);
		}
	}
	
	@Override
	public void mousePressed(int button, int x, int y) {
		for(MenuItem menuItem : menuItems) {
			if ((x >= menuItem.getPosition().x && x <= menuItem.getPosition().x + menuItem.getFont().getWidth(menuItem.getText()) 
					&& (y >= menuItem.getPosition().y && y <= menuItem.getPosition().y + menuItem.getFont().getHeight(menuItem.getText())))) {
				game.enterState(menuItem.getDestinationStateId());
			}
		}
	}
}
