package game;


import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends BasicGameState {
    public int ID;    
    private Level level;
    private int l;

    private Player player;
    private boolean isLastLevel;

    public Game(int ID) {
        this.ID = ID;
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    	if (l == 10){
    		level = new Level10();
    	} else if (l == 9){
    		level = new Level9();
    	}
    	else if (l == 8){
    		level = new Level8();
    	} else if (l == 7){
    		level = new Level7();
    	} else if (l == 6){
    		level = new Level6();
    	} else if (l == 5){
    		level = new Level5();
    	} else if (l == 4){
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
    	if (isLastLevel){
    		level.updateLastLevel(gc, sbg);
    	} else {
    		level.update(gc, sbg);
    	}
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
    
    public void setLastLevel(boolean l){
    	isLastLevel = l;
    }
}
