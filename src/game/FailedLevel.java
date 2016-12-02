package game;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
//import org.newdawn.*;

public class FailedLevel extends BasicGameState {
	private int ID = -1;
	private Background back;
	private Image bak, again, failed;
	private boolean isBak = false;
	private boolean isAgain = false;
	private int curLevel;
	private Sound so, laugh;
	
	public FailedLevel(int id) {
		ID = id;
	}	

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		try {
			back = new Background("res/SelectMenu.png", 1);
			bak = new Image("res/backbutton.png");
			again = new Image("res/NextButton.png");
			failed = new Image("res/popFail.png");
			so = new Sound("res/failed.wav");
			laugh = new Sound("res/cackle.wav");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		back.draw(g);
		failed.draw(330, 50);
		
		if (!isBak) {
            g.drawImage(bak, 170, 460);
        } else {
            bak.setFilter(Image.FILTER_LINEAR);
            bak.draw(167, 456, 1.1f);
        }
		
		if (!isAgain) {
			g.drawImage(again, 700, 460);
		} else {
			again.setFilter(Image.FILTER_LINEAR);
            again.draw(697, 456, 1.1f);
		}
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		int x = Mouse.getX();
		int y = Mouse.getY();
		
		if(!so.playing()){
			so.play();
			laugh.play();
		}
		
		if ((x >= 801 && x <= 1108) && (y >= 140 && y <= 224)) {
			isAgain = true;
			if (Mouse.isButtonDown(0)) {
				// play again
				((Game)arg1.getState(3)).setLevel(curLevel);
				((Game)arg1.getState(3)).init(arg0, arg1);
				so.stop();
				laugh.stop();
				arg1.enterState(3);
				
			}
		} else if ((x >= 271 && x <= 580) && (y >= 142 && y <= 224)) {
			isBak = true;
			if (Mouse.isButtonDown(0)) {
				// select
				so.stop();
				laugh.stop();
				arg1.enterState(2);
			}
		} else {
			isAgain = isBak = false;
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
