package game;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class Player {
	
	private static float gravity = 0.5f;
	private static float jumpStrength = -13;
	private static float speed = 4;
	private static Image p;
    private static ShapeRenderer sr;
	private boolean prevCollideSwitch = false;	
	private Shape player;
	private Level level;
	private Sound jump, key, teleport, doorOpen, switchso;
		
	private float vX = 0;
	private float vY = 0;
        
	private String playerImg = "res/sprite1.png";
	
	public Player(Level level ) {
		this.level = level;
	}
	
	
	
	public void init(GameContainer gc) throws SlickException {
	    p = new Image(playerImg);	
        player = new RoundedRectangle(level.getStartX(), level.getStartY(), 65, 55, 3);
        level.setWin(false);
    	jump = new Sound("res/jump.wav");
    	key = new Sound("res/key.wav");
    	teleport = new Sound("res/teleport.wav");
    	doorOpen = new Sound("res/doorOpen.wav");
    	switchso = new Sound("res/switch.wav");
    }
	

	public void render(GameContainer gc, Graphics g) throws SlickException {
            sr.textureFit(player, p);                     
        }

	public void update(GameContainer gc, int delta) throws SlickException {
			if (level.gameOver(player)){
                level.setDead(true);
                return;
            }
            if (level.winLevel(player)){
                level.setWin(true);
                if (!doorOpen.playing()){
                	doorOpen.play();
                }
                try {
    				Thread.sleep(500);
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
                
                
            }
            
           
            // Y acceleration  
            vY += gravity;
            if( gc.getInput().isKeyDown(Input.KEY_SPACE)){
            	player.setY(player.getY() + 2f );                
                if(level.collideBox(player) || level.collidePlatform(player) || level.collideMovingPlatform(player)) {                	
                    vY = jumpStrength;
                    if (!jump.playing()){
                		jump.play();
                	}
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
                	player.setX(orig - 2f);
                }
                vX = 0;

            }
            
            // collide main key
            if (level.collideKeyMain(player)){
            	if (!level.getKeyMain()){
            		switchso.play();
            		if (!key.playing()){
            			key.play();
            		}
            	}
            	if (level.getKeyTel()){
            		level.setKeyTel(false);
            	}
            	level.setKeyMain(true);
            }
            
            // collide teleport key
            if (level.collideKeyTel(player)){
            	if(!level.getKeyTel()){
            		if (!key.playing()){
	            		key.play();
	            	}
            	}
            	if (level.getKeyMain() && !level.getOpenTel()){
            		level.setKeyMain(false);
            	}  
            	level.setKeyTel(true);
            }

            if (level.collideTel(player)){
            	// open the teleport door
            	if (level.getKeyTel()){
            		level.setOpenTel(true);           		
            	}
            	
            	// wrong key. main key to teleport door
            	if (level.getKeyMain() && !level.getOpenTel()){
            		level.setKeyMain(false);
            	}          	

            	// teleport            	
            	if (level.getOpenTel()){
            		if(!teleport.playing()){
            			teleport.play();
            		}
            	    player.setX(level.getTelX());
            	    player.setY(level.getTelY());
            	}
            }
            
            // wrong key. teleport key to main door
            if (level.collideDoor(player)){
            	if (level.getKeyTel()){
            		level.setKeyTel(false);
            	}
            }
            
            // switch
           if (level.collideSwitch(player)){
        	   if (!prevCollideSwitch){
        		   
            		if (level.getSwitch()){
            			level.setSwitch(false);
            		} else {
                		level.setSwitch(true);
                	}
            		prevCollideSwitch = true;
            	}             	
            } else {
            	prevCollideSwitch = false;
            }
            
            
            
            level.setDead(false);
    }
}
