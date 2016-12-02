package game;


import java.util.*;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.Sound;

public class Level10 implements Level{
    private Shape levelBase, key, door,bimg;
    private Image k, d, img;
    private ShapeRenderer sr;
    private Platform land, water, p1, p2, p3;
    private List<Platform> platforms = new ArrayList<>();
    private Sound sound;
    private boolean stop;
    private boolean pDead, pWin, keyMain;
    private String pImg = "res/JumPlatform.png",
    		dImg = "res/DoorPlatform.png",
    		landImg = "res/land.png",
    		waterImg = "res/water.png",
    	    keyImg = "res/BrownKey.png",
    	    doorImg = "res/brownDoor.png",
    	    soundLevel = "res/level1.wav";
    private int playerX, playerY;

    
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        float[] polygonPoints = new float[]
                            {10, 30,
                            50, 30,
                            50, 30,
                            750, 30,
                            750, 30,
                            600, 30,
                            750, 30,
                            750, 30,
                            800, 30,
                            850, 30, 
                            850, 30,
                            900, 30,
                            900, 30,
                            1000, 30,
                            1355, 30, 
                            1355, 750,
                            10, 750};
            levelBase = new Polygon(polygonPoints);
            p1 = new Platform(480, 600, 140, 50, pImg);
            p2 = new Platform(742, 600, 140, 50, pImg);
            land = new Platform(10, 620, 300, 70, landImg);
            water = new Platform(10, 683, 1344, 65, waterImg);
            
            key = new Rectangle(500, 520, 110, 90);
            door = new Rectangle(770, 490, 120, 120);
            bimg = new Rectangle(0,0,1366,768);
            bimg = new Rectangle(10,31,1345,700);
            
            k = new Image(keyImg);
            d = new Image(doorImg);
            img = new Image("res/lvl1BG.png");
            
            sr = new ShapeRenderer();
            
            sound = new Sound(soundLevel);       
        
            playerX = 25;
            playerY = 550;
            platforms.add(p1);
            platforms.add(p2);
            platforms.add(land);    
            ((Game) sbg.getState(3)).setLastLevel(false);
    }
	
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		sr.textureFit(bimg,img); 
        g.draw(bimg);
        sr.textureFit(door,d);
		g.draw(levelBase);
        p1.render(gc, g);
        p2.render(gc, g);
        land.render(gc, g);
        water.render(gc, g);
        if (!keyMain){
            sr.textureFit(key, k);
        }        
	}


    public void update(GameContainer gc, StateBasedGame sbg) throws SlickException {
        if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)){
        	sound.stop();
        	stop = true;
            sbg.enterState(2);
        }
        if (!sound.playing() && !stop){
            sound.play();        
        }
        if (pWin){
        	sound.stop();
        	stop = true;
        	((Game) sbg.getState(3)).setLastLevel(true);
            ((Game) sbg.getState(3)).setLevel(1);
            ((Game) sbg.getState(3)).init(gc, sbg);
            sbg.enterState(3);
        }
        if (pDead){
        	((FailedLevel) sbg.getState(-1)).setCurrentLvl(1);
            ((FailedLevel) sbg.getState(-1)).init(gc, sbg);
            try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            sound.stop();
            stop = true;
            sbg.enterState(-1);
        }
    }
    
    // for last Level
    public void updateLastLevel(GameContainer gc, StateBasedGame sbg) throws SlickException {
    	// this is the last level
    }
	
	
    @Override
    public boolean collideBox(Shape s) {
        return levelBase.intersects(s);                
    }
    
    @Override
    public boolean collidePlatform(Shape s){
        for (int i = 0; i < platforms.size(); i++){
        	if(platforms.get(i).intersects(s)){
        		return true;
        	}
        }
        return false;
    }
    
    
    @Override
    public boolean collideMovingPlatform(Shape s){
    	return false;
    }
	
    // dead
    public boolean gameOver(Shape s){
    	return water.intersects(s);
    }

    public void setDead(boolean c){
        pDead = c;
    }
    
    // win
    public void setWin(boolean c){
        pWin = c;
    }
    
    public boolean winLevel(Shape s){
        return door.intersects(s) && keyMain;
    }
    
    // main key
 	public boolean collideKeyMain(Shape s) {
		return s.intersects(key);
	}

 	public boolean getKeyMain() {
		return keyMain;
	}

	
	public void setKeyMain(boolean m) {
		keyMain = m;
	}

	// main door
	public boolean collideDoor(Shape s) {
		return false;
	}

	// teleportation
	public boolean collideKeyTel(Shape s) {
		return false;
	}

	public boolean getKeyTel() {
		return false;
	}

	public void setKeyTel(boolean t) {}
	
	// open teleportation
	public void setOpenTel(boolean o) {}

	public boolean collideTel(Shape s) {
		return false;
	}
	public boolean getOpenTel() {
		return false;
	}
	
	public boolean isWin(){
		return pWin;
	}

	@Override
	public int getStartX() {
		return playerX;
	}

	@Override
	public int getStartY() {
		return playerY;
	}

	@Override
	public int getTelX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTelY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean collideSwitch(Shape s) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getSwitch() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setSwitch(boolean s) {
		// TODO Auto-generated method stub
		
	}

}
