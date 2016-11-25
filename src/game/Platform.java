package game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.ShapeRenderer;

public class Platform {
    private int HORIZONTAL = 1;
    private int VERTICAL = -1;  
    private Image img;
    private Shape s;
    private ShapeRenderer sr;
    private boolean drawRect, drawImg;
    private int speed = 1;
    private int dir = 0;
    private float start, stop;
       
    
    public Platform(int x, int y, int width, int height, String l, boolean drawRect){
        s = new Rectangle(x, y, width, height);
        sr = new ShapeRenderer();
        try{
            img = new Image(l);
        } catch (SlickException e){
            e.printStackTrace();
        }        
        this.drawRect = drawRect;
        drawImg = true;
    }
    
    public Platform(int x, int y, int width, int height, String l, boolean drawRect, int dir, int start, int stop){
        s = new Rectangle(x, y, width, height);
        sr = new ShapeRenderer();
        this.start = start;
        this.stop = stop;
        try{
            img = new Image(l);
        } catch (SlickException e){
            e.printStackTrace();
        }        
        this.dir = dir;
        this.drawRect = drawRect;
        drawImg = true;
    }
    
    
    
    public void render(GameContainer gc, Graphics g) throws SlickException {
        if (drawImg){
        	sr.textureFit(s, img);
        }
        
        if (drawRect){
            g.draw(s);  
        }
    }
    
    public void update(){
    	
    	if (dir == HORIZONTAL){    		
    		if (s.getX() + speed > stop || s.getX() + speed < start){
    			speed = -speed;
    		} 
    		s.setX(s.getX() + speed);
    		
    	} else if (dir == VERTICAL){
    		if (s.getY() + speed > stop || s.getY() + speed < start){
    			speed = -speed;
    		} 
    		s.setY(s.getY() + speed);
    	} else {
    		// do nothing
    	}
    }
    
    public boolean intersects(Shape a){
        return s.intersects(a);
    }       
    
    public void setDrawImg(boolean d){
    	drawImg = d;
    }
}
