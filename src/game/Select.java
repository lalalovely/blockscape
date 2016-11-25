package game;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;


public class Select extends BasicGameState {
	private Background back;
	private Image logo, bak, one, two, three, four, five;
	private boolean isBak, isOne, isTwo, isThree, isFour, isFive;
    private boolean stop, played;
    private org.newdawn.slick.Sound sound;
    public boolean c1, c2, c3, c4;

	
	public Select(int id) {
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		try {
            back = new Background("res/SelectMenu.png", 1);
            bak = new Image("res/back1.png");
			one = new Image("res/s1.jpg");
			two = new Image("res/s2.jpg");
			three = new Image("res/s3.jpg");
			four = new Image("res/s4.jpg");
			five = new Image("res/s5.jpg");
			logo = new Image("res/SmallTitle.png");
            sound = new org.newdawn.slick.Sound("res/menu.wav");
            stop = false;
            played = false;
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		back.draw(g);
		logo.draw(450, 5);
		
		if (!isBak) {
			g.drawImage(bak, 570, 600);
		} else {
			bak.setFilter(Image.FILTER_LINEAR);
            bak.draw(567, 596, 1.1f);
		}
		if (!isOne) {
			g.drawImage(one, 150, 300);
		} else {
			one.setFilter(Image.FILTER_LINEAR);
            one.draw(147, 296, 1.1f);
		}
		
		if (!isTwo) {
            g.drawImage(two, 350, 300);
        } else {
            two.setFilter(Image.FILTER_LINEAR);
            two.draw(347, 296, 1.1f);
        }
		
		if (!isThree) {
			g.drawImage(three, 550, 300);
		} else {
			three.setFilter(Image.FILTER_LINEAR);
            three.draw(547, 296, 1.1f);
		}
		
		if (!isFour) {
			g.drawImage(four, 750, 300);
		} else {
			four.setFilter(Image.FILTER_LINEAR);
            four.draw(747, 296, 1.1f);
		}
		
		if (!isFive) {
			g.drawImage(five, 950, 300);
		} else {
			five.setFilter(Image.FILTER_LINEAR);
            five.draw(947, 296, 1.1f);
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

		if ((x >= 219 && x <= 376) && (y >= 240 && y <= 413)) {
			isOne = true;
			if (Mouse.isButtonDown(0)) {
				((Game)arg1.getState(3)).setLevel(1);
				((Game)arg1.getState(3)).init(arg0, arg1);
                sound.stop();	
                stop = true;
                arg1.enterState(3);      
			}
		} else if ((x > 418 && x <= 576) && (y >= 240 && y <= 413)) {
			isTwo = true;
			if (Mouse.isButtonDown(0)) {
				
				
				if (c1){
					((Game)arg1.getState(3)).setLevel(2);
					((Game)arg1.getState(3)).init(arg0, arg1);
	                sound.stop();	
	                stop = true;
	                arg1.enterState(3);
				}
			}
		} else if ((x >= 619 && x <= 776) && (y >= 240 && y <= 413)) {
			isThree = true;
			if (Mouse.isButtonDown(0)) {
				
				if (c2){
					((Game)arg1.getState(3)).setLevel(3);
					((Game)arg1.getState(3)).init(arg0, arg1);
					sound.stop();	
	                stop = true;
					arg1.enterState(3);
				}
			}
		} else if ((x >= 818 && x <= 976) && (y >= 240 && y <= 413)) {
			isFour = true;
			if (Mouse.isButtonDown(0)) {
				
				if (c3){
					((Game)arg1.getState(3)).setLevel(4);
					((Game)arg1.getState(3)).init(arg0, arg1);
	                sound.stop();	
	                stop = true;
	                arg1.enterState(3);
				}
			}
		} else if ((x >= 1018 && x <= 1176) && (y >= 240 && y <= 413)) {
			isFive = true;
			if (Mouse.isButtonDown(0)) {
                sound.stop();	
                stop = true;
                arg1.enterState(4);
			}
		} else if ((x >= 629 && x <= 724) && (y >= 31 && y <= 123)) {
			isBak = true;
			if (Mouse.isButtonDown(0)) {
				sound.stop();	
                stop = true;
				arg1.enterState(1);
			}
		} else {
			isBak = isOne = isTwo = isThree = isFour = isFive = false;
		}
		
	}
	
	public void setCleared(boolean c, int l){
		if (l == 1){
			c1 = c;
		}
		if (l == 2){
			c2 = c;
		}
		if (l == 3){
			c3 = c;
		}
		if (l == 4){
			c4 = c;
		}
	}

	@Override
	public int getID() {
		return 2;
	}

}
