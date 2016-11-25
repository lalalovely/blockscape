package game;



import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.state.*;

public class Level4 extends BasicGameState implements Level{
    private static final int ID = 7;
    private Shape levelBase, key, key2, door, door2, door3, bimg;
    private FallingObj obj1;
    private Image k1, k2, d1, d2, d3, img;
    private ShapeRenderer sr;
    private Platform land, water, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11;
    private boolean keyMain, pWin, pDead, openTel, keyTel;
    private Sound sound;
    private boolean stop, played;
    private int HORIZONTAL = 1;
    private int VERTICAL = -1; 
    
    private String pImg = "res/JumPlatform.png",
    		dImg = "res/DoorPlatform.png",
    		landImg = "res/land.png",
    		waterImg = "res/water.png",
    	    keyImg = "res/BrownKey.png",
    	    doorImg = "res/brownDoor.png",
    	    teleportImg = "res/SilverDoor.png",
    	    soundLevel = "res/level1.wav",
    	    silverKeyImg = "res/SilverKey.png",
	        openDoorImg = "res/openDoor.png";
   
    
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
            p3 = new Platform(650, 620, 140, 50, pImg, false);
            p4 = new Platform(1000, 150, 140, 50, pImg, false, HORIZONTAL, 1000, 1050);
            p5 = new Platform(950, 370, 140, 50, pImg, false); // 1st key
            p6 = new Platform(1200, 150, 150, 50, dImg, false);//main door
            p7 = new Platform(30, 150, 140, 50, pImg, false);//1st door
            p8 = new Platform(650, 370, 140, 50, pImg, false);// main key
            p9 = new Platform(950, 620, 140, 50, pImg, false);
            p10 = new Platform(850, 150, 140, 50, pImg, false);
            p11 = new Platform(1200, 500, 140, 50, pImg, false);
            
            land = new Platform(10, 620, 300, 70, landImg, false);
            water = new Platform(10, 683, 1344, 65, waterImg, false);
            key = new Rectangle(670, 300, 110, 90);
            key2 = new Rectangle(970, 300, 110, 90);
            door2 = new Rectangle(25, 30, 120,120);
            door3 = new Rectangle(25, 30, 120,120);
            door = new Rectangle(1210, 30, 120,120);
            bimg = new Rectangle(0,0,1366,768);
            obj1 = new FallingObj();
            
            k1 = new Image(keyImg);
            k2 = new Image(silverKeyImg); 
            d1 = new Image(doorImg);
            d2 = new Image(openDoorImg);
            d3 = new Image(teleportImg); // teleport door
            bimg = new Rectangle(10,20,1366,768);
            img = new Image("res/BG4.png");
            sr = new ShapeRenderer();
            sound = new org.newdawn.slick.Sound(soundLevel);    
            played = false;
            stop = false;
        }
	
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        sr.textureFit(bimg,img);
        sr.textureFit(door, d1);
        g.draw(bimg);
        g.draw(levelBase);
        p1.render(gc, g);
        p2.render(gc, g);
        p3.render(gc, g);
        p4.render(gc, g);
        p5.render(gc, g);
        p6.render(gc, g);
        p7.render(gc, g);
        p8.render(gc, g);
        p9.render(gc, g);
        p10.render(gc, g);
        p11.render(gc, g);
        land.render(gc, g);
        water.render(gc, g);
        if (!openTel){
        	sr.textureFit(door2, d3);
        }
        if (openTel){
        	sr.textureFit(door3, d2);
        }
        if (!keyMain){
        	sr.textureFit(key, k1);
        }
        if (!openTel && !keyTel){
        	sr.textureFit(key2, k2);
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
        	((Select)sbg.getState(2)).setCleared(true, 4);
            pWin = false;
            stop = true;
            sound.stop();
            sbg.enterState(2);
        }
        if (!stop && !played){
            sound.loop();   
            played = true;
        }       
   }
		
    public boolean collideBox(Shape s) {
        return levelBase.intersects(s);
                
    }
    
    public boolean collidePlatform(Shape s){
        return  p3.intersects(s) || p5.intersects(s) ||
        		p6.intersects(s) || p7.intersects(s) || 
        		p8.intersects(s) || p9.intersects(s) ||
        		p10.intersects(s) || p11.intersects(s) ||
        		land.intersects(s);         
    }
      
    public boolean collideMovingPlatform(Shape s){
    	return p2.intersects(s) || p1.intersects(s) || p4.intersects(s);
    }
	
    // dead
    public void setDead(boolean c){
        pDead = c;
    }
    
    public boolean gameOver(Shape s){
        return obj1.collidesWith(s) || water.intersects(s);
    }    
    
    // main key
    public void setKeyMain(boolean c){
        keyMain = c;
    }
    
    public boolean getKeyMain(){
    	return keyMain;
    }
    
    public boolean collideKeyMain(Shape s){
        return s.intersects(key);
    }
    
    // key Teleport
    public void setKeyTel(boolean c){
        keyTel = c;
    }
    
    public boolean getKeyTel(){
    	return keyTel;
    }
    
    public boolean collideKeyTel(Shape s){
        return s.intersects(key2);
    }
         
    // win
    public void setWin(boolean c){
        pWin = c;
    }
    
    public boolean winLevel(Shape s){
        return door.intersects(s) && keyMain;
    }
       
    // door collision
    // main door
    public boolean collideDoor(Shape s){
        return door.intersects(s);
    }
    
    // teleportation
    public boolean collideTel(Shape s){
    	return door2.intersects(s);
    }
    
    // teleportaion open
    public void setOpenTel(boolean o){
    	openTel = o;
    }
    
    public boolean getOpenTel(){
    	return openTel;
    }
    
    public boolean isWin(){
		return pWin;
	}

    @Override
    public int getID() {
        return ID;
    }

	
}



