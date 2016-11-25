package game;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class Player {
	
	private static float gravity = 0.5f;
	private static float jumpStrength = -13;
	private static float speed = 4;
	private static Image p;
    private static ShapeRenderer sr;
		
	private Shape player;
	private Level level;
	
	private float vX = 0;
	private float vY = 0;
        
	private String playerImg = "res/sprite1.png";
	public Player( Level level ) {
		this.level = level;
	}
	
	
	
	public void init(GameContainer gc) throws SlickException {
	    p = new Image(playerImg);	
        player = new RoundedRectangle(25,540,65,55,3);
        level.setWin(false);
    }
	

	public void render(GameContainer gc, Graphics g) throws SlickException {
            sr.textureFit(player, p);                     
        }

	public void update(GameContainer gc, int delta) throws SlickException {
			if (level.gameOver(player)){
                level.setDead(true);
                player.setX(25);
                player.setY(400);
                level.setKeyMain(false);
                level.setKeyTel(false);
                level.setOpenTel(false);
                return;
            }
            if (level.winLevel(player)){
                level.setWin(true);
            }
            
           
            // Y acceleration  
            vY += gravity;
            if( gc.getInput().isKeyDown(Input.KEY_SPACE)){
                player.setY(player.getY() + 2f );                
                if(level.collideBox(player) || level.collidePlatform(player) || level.collideMovingPlatform(player)) {                	
                    vY = jumpStrength;
                }
                
                player.setY( player.getY() - 2f );
            }

            // Y Movement-Collisions
            player.setY( player.getY() + vY);
            if (level.collideBox(player) || level.collidePlatform(player)){
            	player.setY(player.getY() - vY);
            	vY = 0;
            }
            
            // Y Movement-Collisions on moving platform
            if (level.collideMovingPlatform(player)){
            	float orig = player.getY() - vY; 
            	player.setY(orig + 1.5f);
            	if (level.collideMovingPlatform(player)){
            		player.setY(orig - 1.1f);            		
            	} else {
            		vY = 0;
            	}
            }           
           
            // X acceleration
            if( gc.getInput().isKeyDown(Input.KEY_LEFT) ){
                vX = -speed;
            } else if( gc.getInput().isKeyDown(Input.KEY_RIGHT) ){
                vX = speed;
            } else {
                vX = 0;
            }

            //  X Movement-Collisions
            player.setX( player.getX() + vX);
            if(level.collideBox(player) || level.collidePlatform(player)) {
                player.setX(player.getX() - vX);                
                vX = 0;
            }
            
            // X Movement-Collisions on moving platform
            if(level.collideMovingPlatform(player)) {
            	float orig = player.getX() - vX; 
                player.setX(orig + 1.5f);
                if (level.collideMovingPlatform(player)){
                	player.setX(orig - 1.5f);
                }
                vX = 0;

            }
            if (level.collideKeyMain(player)){
            	if (level.getKeyTel()){
            		level.setKeyTel(false);
            	}
            	level.setKeyMain(true);
            }
            
            if (level.collideKeyTel(player)){
            	if (level.getKeyMain()){
            		level.setKeyMain(false);
            	}
            	level.setKeyTel(true);
            }
            
            if (level.collideTel(player)){
            	if (level.getKeyTel()){
            		level.setOpenTel(true);           		
            	}
            	if (level.getKeyMain() && !level.getOpenTel()){
            		level.setKeyMain(false);
            	}          	
            	if (level.getOpenTel()){
            	    player.setX(900);
            	    player.setY(80);
            	}
            }
            
            if (level.collideDoor(player)){
            	if (level.getKeyTel()){
            		level.setKeyTel(false);
            	}
            }
            level.setDead(false);
    }
}
