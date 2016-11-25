package game;


import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends BasicGameState {
    public int ID;    
    private Level level;
    private int l;

    private Player player;

    public Game(int ID) {
        this.ID = ID;
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    	if (l == 4){
    		level = new Level4();
    	} else if (l == 3){
    		level = new Level3();
    	} else if (l == 2){
    		level = new Level2();
    	} else {
    		level = new Level1();
    	}
        level.init(gc ,sbg);

        player = new Player(level);
        player.init(gc);
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        level.render(gc, sbg, g);
        player.render(gc, g);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        level.update(gc, sbg, i);
        player.update(gc, i);
    }

    @Override
    public int getID() {
        return ID;
    }
    
    public void setLevel(int l){
    	this.l = l;
    }
    
    public boolean cleared(){
    	return level.isWin();
    }
}
