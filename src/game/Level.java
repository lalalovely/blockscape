package game;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;


public interface Level  {
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException;

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException;

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException;

    public boolean collideBox(Shape s);
    
    public boolean collidePlatform(Shape s);
    
    public boolean collideMovingPlatform(Shape s);
        
    public boolean gameOver(Shape s);
    
    public boolean collideKeyMain(Shape s);
    
    public boolean collideKeyTel(Shape s);
    
    public boolean collideTel(Shape s);

    public boolean collideDoor(Shape s);

    public boolean winLevel(Shape s);
    
    public boolean getOpenTel();
    
    public boolean getKeyTel();
    
    public boolean getKeyMain();
    
    public boolean isWin();
    
    public void setKeyMain(boolean m);
    
    public void setKeyTel(boolean t);
    
    public void setOpenTel(boolean o);
    
    public void setWin(boolean w);
    
    public void setDead(boolean d);
    
        
}
