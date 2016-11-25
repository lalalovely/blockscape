package game;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Instruction extends BasicGameState {
	private Image pop, next, bak;
	private Background back;
	private boolean isBak = false;
	private boolean isNext = false;
	
	public Instruction(int id) {
		
	}

	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		try {
			back = new Background("res/MainMenuBG.png", 1);
			pop = new Image("res/instruct.png");
			next = new Image("res/next.png");
			bak = new Image("res/anBack.png");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}


	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		back.draw(arg2);
		pop.draw(400, 100);
		if (!isBak) {
			arg2.drawImage(bak, 550, 620);
		} else {
			bak.setFilter(Image.FILTER_LINEAR);
            bak.draw(547, 616, 1.1f);
		}
		
		if (!isNext) {
			arg2.drawImage(next, 670, 630);
		} else {
			next.setFilter(Image.FILTER_LINEAR);
            next.draw(667, 626, 1.0f);
		}
	}


	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		int x = Mouse.getX();
		int y = Mouse.getY();
		
		//System.out.println(x + ":" + y);
		
		if ((x >= 567 && x <= 658) && (y >= 34 && y <= 128)) {
			isBak = true;
			if (Mouse.isButtonDown(0)) {
				arg1.enterState(1);
			}
		} else {
			isBak = false;
		}
		
		
	}

	
	public int getID() {
		return 7;
	}
	
	

}
