package game;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;


public class State extends StateBasedGame {
	public static final String gamename = "BlockScape";
	public static final int introstate = 0;
	public static final int menustate = 1;
	public static final int select = 2;
	public static final int threestate = 5;
	public static final int instructstate = 7;

	public State(String name) {
		super(gamename);
		
		this.addState(new Intro(introstate));
		this.addState(new Menu(menustate));
		this.addState(new Game(3));
		this.addState(new Select(select));
		this.addState(new Instruction(instructstate));
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(introstate).init(gc, this);
		this.getState(menustate).init(gc, this);
		this.enterState(introstate);
	}

}
