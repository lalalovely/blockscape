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

public class Level1 implements Level{
    private static final int ID = 4;
    private Shape levelBase, key, door,bimg;
    private FallingObj obj1;
    private Image k, d, img;
    private ShapeRenderer sr;
    private Platform land, water, p1, p2, p3, p4, p5;
    private List<Platform> platforms = new ArrayList<>();
    private Sound sound;
    private boolean pDead, pWin, keyMain;
    private boolean stop;
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
            p3 = new Platform(902, 450, 140, 50, pImg);
            p4 = new Platform(1052, 300, 140, 50, pImg);
            p5 = new Platform(1202, 150, 150, 40, dImg); // door
            land = new Platform(10, 620, 300, 70, landImg);
            water = new Platform(10, 683, 1344, 65, waterImg);
            
            key = new Rectangle(770, 520, 110, 90);
            door = new Rectangle(1220,35,120,120);
            bimg = new Rectangle(0,0,1366,768);
            bimg = new Rectangle(10,31,1345,700);
            
            obj1 = new FallingObj();
            
            k = new Image(keyImg);
            d = new Image(doorImg);
            img = new Image("res/lvl1BG.png");
            
            sr = new ShapeRenderer();
            
            sound = new Sound(soundLevel);       
        
            playerX = 25;
            playerY = 550;
            platforms.add(p1);
            platforms.add(p2);
            platforms.add(p3);
            platforms.add(p4);
            platforms.add(p5);
            platforms.add(land);                
    }
	
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		sr.textureFit(bimg,img); 
        g.draw(bimg);
        sr.textureFit(door,d);
		g.draw(levelBase);
        p1.render(gc, g);
        p2.render(gc, g);
        p3.render(gc, g);
        p4.render(gc, g);
        p5.render(gc, g);
        land.render(gc, g);
        water.render(gc, g);
        if (!keyMain){
            sr.textureFit(key, k);
        }
        obj1.render(gc, g);
                
	}


    public void update(GameContainer gc, StateBasedGame sbg) throws SlickException {
        obj1.update(water.intersects(obj1.getS()), collidePlatform(obj1.getS()) || collideMovingPlatform(obj1.getS()), collideBox(obj1.getS()));
        if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)){
        	sound.stop();
        	stop = true;
            sbg.enterState(2);
        }
        if (!sound.playing() && !stop){
            sound.play();        
        }
        if (pWin){
            pWin = false;
            stop = true;
            ((Select)sbg.getState(2)).setCleared(true, 1);
            sound.stop();
            ((ClearedLevel) sbg.getState(-2)).setCurrentLvl(1);
            ((ClearedLevel) sbg.getState(-2)).init(gc, sbg);
            sbg.enterState(-2);
        }
        if (pDead){
        	((FailedLevel) sbg.getState(-1)).setCurrentLvl(1);
            ((FailedLevel) sbg.getState(-1)).init(gc, sbg);
            try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            stop = true;
            sound.stop();
            sbg.enterState(-1);
        }
    }
    
    // for last Level
    public void updateLastLevel(GameContainer gc, StateBasedGame sbg) throws SlickException {
    	obj1.update(water.intersects(obj1.getS()), collidePlatform(obj1.getS()) || collideMovingPlatform(obj1.getS()), collideBox(obj1.getS()));
        if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)){
        	sound.stop();
        	stop = true;
            sbg.enterState(2);
        }
        if (!sound.playing() && !stop){
            sound.play();        
        }
        if (pWin){
            pWin = false;
            sound.stop();  
            stop = true;
            ((Game) sbg.getState(3)).setLastLevel(true);
            ((Game) sbg.getState(3)).setLevel(2);
            ((Game) sbg.getState(3)).init(gc, sbg);
            sbg.enterState(3);
            
        }
        if (pDead){
        	((FailedLevel) sbg.getState(-1)).setCurrentLvl(10);
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
    	return obj1.collidesWith(s) || water.intersects(s);
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
