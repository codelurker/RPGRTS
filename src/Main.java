import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import states.GameState;
import states.MenuState;


public class Main extends StateBasedGame {
    public Main() {
        super("SimpleTest");
    }
    
    @Override
    public void initStatesList(GameContainer container) throws SlickException {
    	addState(new MenuState());
    	addState(new GameState());
    }

    public static void main(String[] args) {
        try {
            AppGameContainer app = new AppGameContainer(new Main());
            app.setTargetFrameRate(60);
            app.setDisplayMode(1280, 800, false);
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
