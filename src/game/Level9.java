package game;



import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.state.*;

public class Level9 implements Level{
    private Shape levelBase, key, key2, door, onAndOff, door2, bimg, spike;
    private FallingObj obj1;
    private Image k1, k2, d1, d2, d3, img, s, on, off;
    private ShapeRenderer sr;
    private Platform water, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12;
    private boolean keyMain, pWin, pDead, openTel, keyTel;
    private boolean switchedOn;
    private Sound sound;
    private List<Platform> platforms = new ArrayList<>();
    private int HORIZONTAL = 1;
    private int VERTICAL = -1;
    private boolean stop;
    private int playerX, playerY, telX, telY;
    
    private String pImg = "res/JumPlatform.png",
    		dImg = "res/DoorPlatform.png",
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
            
            p1 = new Platform(250, 370, 140, 50, pImg); // teleport
            p2 = new Platform(50, 620, 150, 50, dImg); // main door
            p3 = new Platform(270, 620, 140, 50, pImg, HORIZONTAL, 270, 450); // to main door
            p4 = new Platform(970, 180, 140, 50, pImg); 
            p5 = new Platform(700, 400, 140, 50, pImg, VERTICAL, 200, 550); // to switch
            p6 = new Platform(1170, 180, 140, 50, pImg);//switch
            p7 = new Platform(30, 150, 140, 50, pImg);//spike
            p8 = new Platform(50, 370, 140, 50, pImg);// main key
            p9 = new Platform(920, 620, 140, 50, pImg, HORIZONTAL, 900, 1000); // 1st from start
            p10 = new Platform(1170, 500, 140, 50, pImg);
            p11 = new Platform(1170, 330, 140, 50, pImg); // start
            p12 = new Platform(230, 180, 140, 50, pImg, HORIZONTAL, 230, 420);
                        
            water = new Platform(10, 683, 1344, 65, waterImg);
            key = new Rectangle(90,  300, 110, 90);
            key2 = new Rectangle(1210, 430, 110, 90);
            door2 = new Rectangle(250, 260, 120,120);
            door = new Rectangle(70, 510, 120,120);
            obj1 = new FallingObj();
            spike = new Rectangle(60, 120, 90, 30);
            bimg = new Rectangle(10,20,1366,768);
            onAndOff = new Rectangle(1200, 160, 70, 30);
            
            
            k1 = new Image(keyImg);
            k2 = new Image(silverKeyImg); 
            d1 = new Image(doorImg);
            d2 = new Image(openDoorImg);
            d3 = new Image(teleportImg); // teleport door
            on = new Image("res/switchOn.png");
            off = new Image("res/switchOff.png");            
            img = new Image("res/BG4.png");
            s = new Image("res/spike.png");
            sr = new ShapeRenderer();
            
            sound = new Sound(soundLevel);    
           
            playerX = telX = 1220;
            playerY = telY =  230;
            
        	
        	platforms.add(p1);
        	platforms.add(p2);
        	platforms.add(p3);
        	platforms.add(p4);
        	platforms.add(p5);
        	platforms.add(p6);
        	platforms.add(p7);
        	platforms.add(p8);
        	platforms.add(p9);
        	platforms.add(p10);
        	platforms.add(p11);
        	platforms.add(p12);
        	
        }
	
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        sr.textureFit(bimg,img);
        sr.textureFit(door, d1);
        sr.textureFit(spike, s);
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
        p12.render(gc, g);
        water.render(gc, g);
        if (!openTel){
        	sr.textureFit(door2, d3);
        }
        if (openTel){
        	sr.textureFit(door2, d2);
        }
        if (!keyMain){
        	sr.textureFit(key, k1);
        }
        if (!openTel && !keyTel){
        	sr.textureFit(key2, k2);
        }
        if (switchedOn){
            sr.textureFit(onAndOff, on);
        } else {
            sr.textureFit(onAndOff, off);           
        }
         
        obj1.render(gc, g);

    }

    public void update(GameContainer gc, StateBasedGame sbg) throws SlickException {
    	obj1.update(water.intersects(obj1.getS()), collidePlatform(obj1.getS()) || collideMovingPlatform(obj1.getS()), collideBox(obj1.getS()));
        p9.update();
        p5.update();
        if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)){
        	sound.stop();
        	stop = true;
            sbg.enterState(2);
        }
        
        if (!sound.playing() && !stop) {
            sound.play();
        }

        if (pWin){
            pWin = false;
            ((Select)sbg.getState(2)).setCleared(true, 9);
            sound.stop();
            stop = true;
            ((ClearedLevel) sbg.getState(-2)).setCurrentLvl(9);
            ((ClearedLevel) sbg.getState(-2)).init(gc, sbg);
            sbg.enterState(-2);
        }
        if (pDead){
        	sound.stop();
        	stop = true;
            ((FailedLevel) sbg.getState(-1)).setCurrentLvl(9);
            ((FailedLevel) sbg.getState(-1)).init(gc, sbg);
            try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            sbg.enterState(-1);
        }
        if (switchedOn){
        	p3.update();
        } else {
        	p12.update();
        }
       
   }
    
    // for last level
    public void updateLastLevel(GameContainer gc, StateBasedGame sbg) throws SlickException {
    	obj1.update(water.intersects(obj1.getS()), collidePlatform(obj1.getS()) || collideMovingPlatform(obj1.getS()), collideBox(obj1.getS()));
    	 p9.update();
         p5.update();
         if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)){
        	sound.stop();
        	stop = true;
            sbg.enterState(2);
        }
        
        if (!sound.playing() && !stop) {
            sound.play();
        }

        if (pWin){
        	pWin = false;
    		sound.stop();
            stop = true;
            ((ClearedLevel) sbg.getState(-2)).setCurrentLvl(9);
            ((ClearedLevel) sbg.getState(-2)).init(gc, sbg);
            sbg.enterState(-2);            
        }
        if (pDead){
        	sound.stop();
        	stop = true;
            ((FailedLevel) sbg.getState(-1)).setCurrentLvl(10);
            ((FailedLevel) sbg.getState(-1)).init(gc, sbg);
            try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            sbg.enterState(-1);
        }
        if (switchedOn){
        	p3.update();
        	p12.update();
        }
   }
		
    public boolean collideBox(Shape s) {
        return levelBase.intersects(s);
                
    }
    
    public boolean collidePlatform(Shape s){
    	for (int i = 0; i < platforms.size(); i++){
        	if(platforms.get(i).intersects(s)){
        		return true;
        	}
        }
        return false;    
    }
    
    public boolean collideMovingPlatform(Shape s){
    	return p9.intersects(s) || p5.intersects(s) || p3.intersects(s) || p12.intersects(s);
    }
	
    // dead
    public void setDead(boolean c){
        pDead = c;
    }
    
    public boolean gameOver(Shape s){
        return obj1.collidesWith(s) || water.intersects(s) || spike.intersects(s);
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
	public int getStartX() {
		return playerX;
	}

	@Override
	public int getStartY() {
		return playerY;
	}

	@Override
	public int getTelX() {
		return telX;
	}

	@Override
	public int getTelY() {
		return telY;
	}

	@Override
	public boolean collideSwitch(Shape s) {
		return onAndOff.intersects(s);
	}

	@Override
	public boolean getSwitch() {		 
		return switchedOn;
	}

	@Override
	public void setSwitch(boolean s) {
		this.switchedOn = s;
		
	}

	
}



