package game;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;


public class Select extends BasicGameState {
	private Background back;
	private Image logo, bak, one, two, three, four, five, six, seven, eight, nine, ten;
	private boolean isBak, isOne, isTwo, isThree, isFour, isFive, isSix, isSeven, isEight, isNine, isTen;
    private boolean stop, played;
    private org.newdawn.slick.Sound sound;
    private boolean c1, c2, c3, c4 = true, c5 = true, c6 = true, c7 = true, c8 = true, c9 = true, c10;

	
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
			six = new Image("res/6.png");
			seven = new Image("res/7.png");
			eight = new Image("res/8.png");
			nine = new Image("res/9.png");
			ten = new Image("res/10.png");
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
		logo.draw(470, -10);
		
		
		if (!isBak) {
			g.drawImage(bak, 590, 600);
		} else {
			bak.setFilter(Image.FILTER_LINEAR);
            bak.draw(587, 596, 1.1f);
		}
		if (!isOne) {
			g.drawImage(one, 150, 205);
		} else {
			one.setFilter(Image.FILTER_LINEAR);
            one.draw(147, 200, 1.0f);
		}
		
		if (!isTwo) {
            g.drawImage(two, 350, 205);
        } else {
            two.setFilter(Image.FILTER_LINEAR);
            two.draw(347, 200, 1.0f);
        }
		
		if (!isThree) {
			g.drawImage(three, 550, 205);
		} else {
			three.setFilter(Image.FILTER_LINEAR);
            three.draw(547, 200, 1.0f);
		}
		
		if (!isFour) {
			g.drawImage(four, 750, 205);
		} else {
			four.setFilter(Image.FILTER_LINEAR);
            four.draw(747, 200, 1.0f);
		}
		
		if (!isFive) {
			g.drawImage(five, 950, 205);
		} else {
			five.setFilter(Image.FILTER_LINEAR);
            five.draw(947, 200, 1.0f);
		}
		
		if (!isSix) {
			g.drawImage(six, 150, 400);
		} else {
			six.setFilter(Image.FILTER_LINEAR);
            six.draw(147, 395, 1.0f);
		}
		
		if (!isSeven) {
			g.drawImage(seven, 350, 400);
		} else {
			seven.setFilter(Image.FILTER_LINEAR);
            seven.draw(347, 395, 1.0f);
		}
		
		if (!isEight) {
			g.drawImage(eight, 550, 400);
		} else {
			eight.setFilter(Image.FILTER_LINEAR);
            eight.draw(547, 395, 1.0f);
		}
		
		if (!isNine) {
			g.drawImage(nine, 750, 400);
		} else {
			nine.setFilter(Image.FILTER_LINEAR);
            nine.draw(747, 395, 1.0f);
		}
		
		if (!isTen) {
			g.drawImage(ten, 950, 405);
		} else {
			ten.setFilter(Image.FILTER_LINEAR);
            ten.draw(947, 400, 1.0f);
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

		if ((x >= 220 && x <= 376) && (y >= 337 && y <= 507)) {
			isOne = true;
			if (Mouse.isButtonDown(0)) {
				((Game)arg1.getState(3)).setLevel(1);
				((Game)arg1.getState(3)).init(arg0, arg1);
                sound.stop();	
                stop = true;
                arg1.enterState(3);      
			}
		} else if ((x >= 420 && x <= 575) && (y >= 337 && y <= 507)) {
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
		} else if ((x >= 620 && x <= 776) && (y >= 337 && y <= 507)) {
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
		} else if ((x >= 819 && x <= 973) && (y >= 337 && y <= 507)) {
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
		} else if ((x >= 1020 && x <= 1176) && (y >= 337 && y <= 507)) {
			isFive = true;
			if (Mouse.isButtonDown(0)) {
				if (c4){
					((Game)arg1.getState(3)).setLevel(5);
					((Game)arg1.getState(3)).init(arg0, arg1);
		            sound.stop();	
		            stop = true;
		            arg1.enterState(3);
				}
			}
		} else if ((x >= 650 && x <= 743) && (y >= 32 && y <= 121)) {
			isBak = true;
			if (Mouse.isButtonDown(0)) {
				sound.stop();	
                stop = true;
				arg1.enterState(1);
			}
		} else if ((x >= 220 && x <= 376) && (y >= 142 && y <= 311 )) {
			isSix = true;
			if (Mouse.isButtonDown(0)) {
				if (c5){
					((Game)arg1.getState(3)).setLevel(6);
					((Game)arg1.getState(3)).init(arg0, arg1);
	                sound.stop();	
	                stop = true;
	                arg1.enterState(3);
				}
			}
		} else if ((x >= 420 && x <= 575) && (y >= 142 && y <= 311 )) {
			isSeven = true;
			if (Mouse.isButtonDown(0)) {
				if (c6){
					((Game)arg1.getState(3)).setLevel(7);
					((Game)arg1.getState(3)).init(arg0, arg1);
	                sound.stop();	
	                stop = true;
	                arg1.enterState(3);
				}
			}
		} else if ((x >= 620 && x <= 776) && (y >= 142 && y <= 311 )) {
			isEight = true;
			if (Mouse.isButtonDown(0)) {
				if (c7){
					((Game)arg1.getState(3)).setLevel(8);
					((Game)arg1.getState(3)).init(arg0, arg1);
	                sound.stop();	
	                stop = true;
	                arg1.enterState(3);
				}
			}
		} else if ((x >= 819 && x <= 973) && (y >= 142 && y <= 311 )) {
			isNine = true;
			if (Mouse.isButtonDown(0)) {
				if (c8){
					((Game)arg1.getState(3)).setLevel(9);
					((Game)arg1.getState(3)).init(arg0, arg1);
	                sound.stop();	
	                stop = true;
	                arg1.enterState(3);
				}
			}
		} else if ((x >= 1020 && x <= 1176) && (y >= 142 && y <= 311 )) {
			isTen = true;
			if (Mouse.isButtonDown(0)) {
				if (c9){
					((Game)arg1.getState(3)).setLevel(10);
					((Game)arg1.getState(3)).init(arg0, arg1);
	                sound.stop();	
	                stop = true;
	                arg1.enterState(3);
				}
			}
		} else {
			isBak = isOne = isTwo = isThree = isFour = isFive = isSix = isSeven = isEight = isNine = isTen = false;
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
		if (l == 5){
			c5 = c;
		}
		if (l == 6){
			c6 = c;
		}
		if (l == 7){
			c7 = c;
		}
		if (l == 8){
			c8 = c;
		}
		if (l == 9){
			c9 = c;
		}
		if (l == 10){
			c10 = c;
		}
		
		
	}

	@Override
	public int getID() {
		return 2;
	}

}