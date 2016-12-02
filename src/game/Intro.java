package game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Intro extends BasicGameState {
	
	private Image logo1, logo2;
	private Background back;
    private boolean stop, played;
    private org.newdawn.slick.Sound sound;
    private String introBg = "res/introNEW.png",
	title = "res/bigTitle.png",
	soundMenu = "res/menu.wav"; 
    		
	
	public Intro(int id) {
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		try {
            back = new Background(introBg, 1);
			logo1 = new Image(title);
			//logo2 = new Image("res/SCAPE.png");
                        
            sound = new org.newdawn.slick.Sound(soundMenu);
            stop = false;
            played = false;
		
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		back.draw(g);
		logo1.draw(198, 20);
		//logo2.draw(260, 300);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException {

		Input input = gc.getInput();
		
        if (!stop && !played){
            sound.play();
            played = true;
        }
        
        if (input.isKeyPressed(Input.KEY_ENTER)) {
        	sound.stop();
        	stop = true;
        	sbg.enterState(1);
        } else if (input.isKeyPressed(Input.KEY_ESCAPE)) {
        	System.exit(0);
        }
       
	}

	@Override
	public int getID() {
		return 0;
	}

	
}
