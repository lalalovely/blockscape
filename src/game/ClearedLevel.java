package game;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class ClearedLevel extends BasicGameState {
	private int ID;
	private Background back;
	private Image clear, bak, next, again;
	private boolean isAgain = false;
	private boolean isBak = false;
	private boolean isNext = false;
	private int curLevel;
	private Sound so;
	public ClearedLevel(int id) {
		ID = id;
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		try {
			back = new Background("res/SelectMenu.png", 1);
			bak = new Image("res/backbutton.png");
			again = new Image("res/NextButton.png");
			next = new Image("res/next.png");
			clear = new Image("res/cleared.png");
			so = new Sound("res/cleared.wav");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		back.draw(g);
		clear.draw(330, 10);
		
		if (!isBak) {
            g.drawImage(bak, 170, 400);
        } else {
            bak.setFilter(Image.FILTER_LINEAR);
            bak.draw(167, 396, 1.1f);
        }
		
		if (!isAgain) {
			g.drawImage(again, 430, 530);
		} else {
			again.setFilter(Image.FILTER_LINEAR);
            again.draw(427, 526, 1.1f);
		}
		
		if (!isNext) {
			g.drawImage(next, 700, 400);
		} else {
			next.setFilter(Image.FILTER_LINEAR);
            next.draw(697, 396, 1.1f);
		}
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		int x = Mouse.getX();
		int y = Mouse.getY();
		if (!so.playing()){
			so.play();
		}
		if ((x >= 531 && x <= 838) && (y >= 71 && y <= 154)) {
			isAgain = true;
			if (Mouse.isButtonDown(0)) {
				// play again
				((Game)arg1.getState(3)).setLevel(curLevel);
				((Game)arg1.getState(3)).init(arg0, arg1);
				so.stop();
				arg1.enterState(3);
            }
		} else if ((x >= 271 && x <= 579) && (y >= 202 && y <= 285)) {
			isBak = true;
			if (Mouse.isButtonDown(0)) {
				// select
				so.stop();
				arg1.enterState(2);	
			}
		} else if ((x >= 800 && x <= 1108) && (y >= 201 && y <= 284)) {
			isNext = true;
			if (Mouse.isButtonDown(0)) {
				// next
				((Game)arg1.getState(3)).setLevel(curLevel + 1);
				((Game)arg1.getState(3)).init(arg0, arg1);
				so.stop();
				arg1.enterState(3);
			}
		} else {
			isAgain = isBak = isNext = false;
		}
	}
	
	public void setCurrentLvl(int lvl){
		curLevel = lvl;
	}
	
	@Override
	public int getID() {
		return ID;
	}
}
