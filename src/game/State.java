package game;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;


public class State extends StateBasedGame {
	public static final String gamename = "BlockScape";
	public static final int introstate = 0;
	public static final int menustate = 1;
	public static final int select = 2;
	public static final int threestate = 5;
	public static final int instructstate = -3;
	public static final int help2 = -4;
	public static final int help3 = -5;
	public static final int help4 = -6;
	
	public static final int about1 = -7;
	public static final int about2 = -8;
	public static final int about3 = -9;
	
	public State(String name) {
		super(gamename);
		
		this.addState(new Intro(introstate));
		this.addState(new Menu(menustate));
		this.addState(new Game(3));
		this.addState(new Select(select));
		this.addState(new Help1(instructstate));
		this.addState(new Help2(help2));
		this.addState(new Help3(help3));
		this.addState(new Help4(help4));
		this.addState(new About1(about1));
		this.addState(new About2(about2));
		this.addState(new About3(about3));
		this.addState(new ClearedLevel(-2));
		this.addState(new FailedLevel(-1));
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(introstate).init(gc, this);
		this.getState(menustate).init(gc, this);
		this.enterState(introstate);
	}

}
