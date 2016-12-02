package game;


import org.newdawn.slick.GameContainer;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.state.*;

public class Level4 implements Level{
    private Shape levelBase, key, door, bimg;
    private FallingObj obj1;
    private Image k, d, img;
    private ShapeRenderer sr;
    private Platform land, water, p1, p2, p3, p4, p5,p6,p7,p8;
    private boolean keyMain, pWin, pDead;
    private Sound sound;
    private int HORIZONTAL = 1;
    private List<Platform> platforms = new ArrayList<>();
    private int VERTICAL = -1; 
    private int playerX, playerY;
    private boolean stop;
    private String pImg = "res/JumPlatform.png",
    		dImg = "res/DoorPlatform.png",
    		landImg = "res/land.png",
    		waterImg = "res/water.png",
    	    keyImg = "res/BrownKey.png",
    	    doorImg = "res/brownDoor.png",
    	    soundLevel = "res/level1.wav";
    	    
    
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
            p1 = new Platform(170, 320, 140, 50, pImg, HORIZONTAL, 50, 300);
            p2 = new Platform(380, 550, 140, 50, pImg, VERTICAL, 450, 620);
            p3 = new Platform(650, 600, 140, 50, pImg);
            p4 = new Platform(1000, 600, 140, 50, pImg, HORIZONTAL, 900, 1100);
            p5 = new Platform(1200, 430, 140, 50, pImg);
            p6 = new Platform(1200, 200, 140, 50, pImg);//key
            p7 = new Platform(30, 150, 150, 40, dImg);//door
            p8 = new Platform(900, 330, 140, 50, pImg);            
            land = new Platform(10, 620, 300, 70, landImg);

            water = new Platform(10, 683, 1344, 65, waterImg);
            key = new Rectangle(1220, 130, 110, 90);
            door = new Rectangle(25, 30, 120,120);
            bimg = new Rectangle(0,0,1366,768);
            obj1 = new FallingObj();
            k = new Image(keyImg);
            d = new Image(doorImg);
            bimg = new Rectangle(10,20,1366,768);
            img = new Image("res/BG3.png");
            sr = new ShapeRenderer();
            sound = new Sound(soundLevel);    
      
            playerX = 25;
            playerY = 550;
            platforms.add(p1);
            platforms.add(p2);
            platforms.add(p3);
            platforms.add(p4);
            platforms.add(p5);
            platforms.add(p6);
            platforms.add(p7);
            platforms.add(p8);
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
        p6.render(gc, g);
        p7.render(gc, g);
        p8.render(gc, g);
        land.render(gc, g);
        water.render(gc, g);
        if (!keyMain){
            sr.textureFit(key, k);
        }
        obj1.render(gc, g);

    }

    public void update(GameContainer gc, StateBasedGame sbg) throws SlickException {
    	obj1.update(water.intersects(obj1.getS()), collidePlatform(obj1.getS()) || collideMovingPlatform(obj1.getS()), collideBox(obj1.getS()));
        p2.update();
        p1.update();
        p4.update();
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
            sound.stop();
            ((Select)sbg.getState(2)).setCleared(true, 4);
            
        
            ((ClearedLevel) sbg.getState(-2)).setCurrentLvl(4);
            ((ClearedLevel) sbg.getState(-2)).init(gc, sbg);
            sbg.enterState(-2);
        }
        if (pDead){
        	sound.stop();
        	stop = true;
            ((FailedLevel) sbg.getState(-1)).setCurrentLvl(4);
            ((FailedLevel) sbg.getState(-1)).init(gc, sbg);
            try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            sbg.enterState(-1);
        }
    }
    // for last level
    public void updateLastLevel(GameContainer gc, StateBasedGame sbg) throws SlickException {
    	obj1.update(water.intersects(obj1.getS()), collidePlatform(obj1.getS()) || collideMovingPlatform(obj1.getS()), collideBox(obj1.getS()));
        p2.update();
        p1.update();
        p4.update();
        if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)){
        	sound.stop();
        	stop = true;
            sbg.enterState(2);
        }
        if (!sound.playing() && !stop){
            sound.loop();
        }
        if (pWin){
            pWin = false;
            sound.stop();            
            stop = true;
            ((Game) sbg.getState(3)).setLastLevel(true);
            ((Game) sbg.getState(3)).setLevel(5);
            ((Game) sbg.getState(3)).init(gc, sbg);
            sbg.enterState(3);
            
        }
        if (pDead){
        	((FailedLevel) sbg.getState(-1)).setCurrentLvl(10);
            ((FailedLevel) sbg.getState(-1)).init(gc, sbg);
            try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            sound.stop();
        	stop = true;
            sbg.enterState(-1);
        }
    }

	// collision platforms and box
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
    
    
    public boolean collideMovingPlatform(Shape s){
    	return p2.intersects(s) || p1.intersects(s) || p4.intersects(s);
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
		return 0;
	}

	@Override
	public int getTelY() {
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

