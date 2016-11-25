package game;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.state.*;

public class Level3 extends BasicGameState implements Level{
    private static final int ID = 6;
    private Shape levelBase, key, door, bimg;
    private FallingObj obj1;
    private Image k, d, img;
    private ShapeRenderer sr;
    private Platform land, water, p1, p2, p3, p4, p5,p6,p7,p8;
    private boolean keyMain, pWin, pDead;
    private org.newdawn.slick.Sound sound;
    private boolean stop, played;
    private int HORIZONTAL = 1;
    private int VERTICAL = -1; 
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
            p1 = new Platform(170, 320, 140, 50, pImg, false, HORIZONTAL, 50, 300);
            p2 = new Platform(380, 550, 140, 50, pImg, false, VERTICAL, 450, 620);
            p3 = new Platform(650, 600, 140, 50, pImg, false);
            p4 = new Platform(1000, 600, 140, 50, pImg, false, HORIZONTAL, 900, 1100);
            p5 = new Platform(1200, 430, 140, 50, pImg, false);
            p6 = new Platform(1200, 200, 140, 50, pImg, false);//key
            p7 = new Platform(30, 150, 150, 40, dImg, false);//door
            p8 = new Platform(900, 330, 140, 50, pImg, false);
            
            land = new Platform(10, 620, 300, 70, landImg, false);
            water = new Platform(10, 683, 1344, 65, waterImg, false);
            key = new Rectangle(1220, 130, 110, 90);
            door = new Rectangle(25, 30, 120,120);
            bimg = new Rectangle(0,0,1366,768);
            obj1 = new FallingObj();
            k = new Image(keyImg);
            d = new Image(doorImg);
            bimg = new Rectangle(10,20,1366,768);
            img = new Image("res/BG3.png");
            sr = new ShapeRenderer();
            sound = new org.newdawn.slick.Sound(soundLevel);    
            played = false;
            stop = false;
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

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        obj1.update(650, collidePlatform(obj1.getS()), collideBox(obj1.getS()), collideMovingPlatform(obj1.getS()), pDead);
        p2.update();
        p1.update();
        p4.update();
        if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)){
        	stop = true;
            sound.stop();
            sbg.enterState(2);
        }
        if (pWin){
        	((Select)sbg.getState(2)).setCleared(true, 3);
            pWin = false;
            stop = true;
            sound.stop();
            sbg.enterState(2);
        }
        if ( !stop && !played){
            sound.loop();   
            played = true;
        }
    }
	
	// collision platforms and box
    public boolean collideBox(Shape s) {
        return levelBase.intersects(s);
    }
    
    public boolean collidePlatform(Shape s){
        return  p3.intersects(s) || p5.intersects(s) ||
        		p6.intersects(s) || p7.intersects(s) || 
        		p8.intersects(s) || land.intersects(s);         
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
	
	public int getID() {
        return ID;
    }

	

}

