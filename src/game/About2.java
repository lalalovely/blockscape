package game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class About2 extends BasicGameState {
	private Image next, bak;
	private Background back;
	private boolean isBak = false;
	private boolean isNext = false;
	private String name = "res/AboutGame2.png";
	
	public About2(int id) {
		
	}

	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		try {
			back = new Background(name, 1);
			next = new Image("res/aboutNext.png");
			bak = new Image("res/aboutBack.png");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}


	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		back.draw(arg2);
		
		if (!isBak) {
			arg2.drawImage(bak, 270, 360);
		} else {
			bak.setFilter(Image.FILTER_LINEAR);
            bak.draw(267, 356, 1.1f);
		}
		
		if (!isNext) {
			arg2.drawImage(next, 1030, 360);
		} else {
			next.setFilter(Image.FILTER_LINEAR);
            next.draw(1027, 356, 1.1f);
		}
	}


	public void update(GameContainer gc, StateBasedGame arg1, int arg2) throws SlickException {
		Input input = gc.getInput();
		

        if (input.isKeyPressed(Input.KEY_RIGHT)) {
        	isNext = true;
        	arg1.enterState(-9);
        } else if (input.isKeyPressed(Input.KEY_LEFT)) {
        	//System.exit(0);
        	isBak = true;
        	arg1.enterState(-7);
        } else {
        	isBak = isNext = false;
        }
	}

	
	public int getID() {
		return -8;
	}
	
	

}
