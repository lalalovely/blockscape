package game;

import java.util.Random;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class FallingObj {
    private Shape s;
    private static float gravity = 0.1f;
    private float ySpeed = 0, xSpeed;
    private Random r = new Random();
    private Image fo;
    private ShapeRenderer sr;
    
    private int x = randomNum(50, 1200);
    private int y = 30;
    private int[] speeds = {-2, 2};
            
    public FallingObj() throws SlickException{
     
        s = new RoundedRectangle(x, y, 65, 60, 8);
        xSpeed = speeds[randomNum(0, 1)];
        fo = new Image("res/Monster.png");
        sr = new ShapeRenderer();

    }
    
    public void render(GameContainer gc, Graphics g) throws SlickException {
        sr.textureFit(s, fo);        
    }
    
    public int randomNum(int x, int y){
        return r.nextInt(Math.max(x, y) - Math.min(x, y) + 1) + Math.min(x, y);
    }
    public void update(boolean dead, boolean collidePlatform, boolean collideBox) {
        
        if (dead){
            // set new starting position of object
            ySpeed = 0;
            s.setX(randomNum(50, 1200));
            s.setY(30);
            xSpeed = speeds[randomNum(0, 1)];            
        } else {
            if (!collidePlatform){
                s.setY(s.getY() + ySpeed);
                ySpeed += gravity;
            }
            if (collideBox){
                xSpeed = -xSpeed;
            }
            s.setX(s.getX() + xSpeed);            
        }
    }

    public Shape getS() {
        return s;
    }
    public boolean collidesWith(Shape a){
        return a.intersects(s);
    }
}
