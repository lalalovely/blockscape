package game;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Menu extends BasicGameState {
	
	private Background back;
	private Image title, bak, how, about, exit, play;
	private boolean isPlay = false;
	private boolean isExit = false;
	private boolean isHow = false;
	private boolean isAbout = false;
	private boolean isBack = false;
    private Sound sound;
	private boolean stop, played;
	
	
	private String menuBg = "res/MainMenuBG.png",
	titleImg = "res/SmallTitle.png",
	playImg = "res/NEW.png",
	backImg =  "res/back1.png",
	helpImg = "res/HELP.png",
	aboutImg = "res/about.png",
	quitImg = "res/quit.jpg",
	menuSound = "res/menu.wav";
	
	public Menu(int id) {
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		try {
            back = new Background(menuBg, 1);
            title = new Image(titleImg);
            play = new Image(playImg);
            bak = new Image(backImg);
    		how = new Image(helpImg);
    		about = new Image(aboutImg);
    		exit = new Image(quitImg);
			sound = new Sound(menuSound);
            stop = false;
            played = false;
                        
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics graphics) throws SlickException {
		
		
		back.draw(graphics);
		title.draw(450, 20);
		if (!isPlay) {
			graphics.drawImage(play, 370, 250);
		} else {
			play.setFilter(Image.FILTER_LINEAR);
            play.draw(367, 246, 1.0f);
		}
		
		if (!isBack) {
            graphics.drawImage(bak, 370, 600);
        } else {
            bak.setFilter(Image.FILTER_LINEAR);
            bak.draw(367, 596, 1.1f);
        }
		
		if (!isHow) {
			graphics.drawImage(how, 500, 600);
		} else {
			how.setFilter(Image.FILTER_LINEAR);
            how.draw(497, 596, 1.1f);
		}
		
		if (!isAbout) {
			graphics.drawImage(about, 620, 600);
		} else {
			about.setFilter(Image.FILTER_LINEAR);
            about.draw(617, 596, 1.1f);
		}
		
		if (!isExit) {
			graphics.drawImage(exit, 740, 600);
		} else {
			exit.setFilter(Image.FILTER_LINEAR);
            exit.draw(737, 596, 1.1f);
		}

	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		int x = Mouse.getX();
		int y = Mouse.getY();
		
				
		if (!stop && !played){
                    sound.play();
                    played = true;
                }
		
		if ((x >= 589 && x <= 739) && (y <= 385 && y >= 235)) {
            isPlay = true;
            if (Mouse.isButtonDown(0)) {
                sound.stop();	
                stop = true;
                arg1.enterState(2);

            }
		} else if ((x >= 428 && x <= 532) && (y <= 123 && y >= 32)) {
			isBack = true;
			if (Mouse.isButtonDown(0)) {
				arg1.enterState(0);
			}
		} else if ((x >= 558 && x <= 655) && (y <= 123 && y >= 32)) {
            isHow = true;
            if (Mouse.isButtonDown(0)) {
            	arg1.enterState(7);
            }
		} else if ((x >= 679 && x <= 774) && (y <= 123 && y >= 32)) {
			isAbout = true;
		} else if ((x >= 799 && x <= 893) && (y <= 123 && y >= 32)) {
			isExit = true;
			if (Mouse.isButtonDown(0)) {
				System.exit(0);
			}
		} else {
			isPlay = isBack = isHow = isAbout = isExit = false;
		}
	}

	@Override
	public int getID() {
		return 1;
	}

	
}
