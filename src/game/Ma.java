package game;



import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;


public class Ma {
	
	
	public static void main(String[] args) {
            try {
                    AppGameContainer app = new AppGameContainer(new State("Block Scape"));
                    app.setDisplayMode(1366, 768, false);
                    app.setVSync(true);
                    app.start();
            } catch (SlickException e) {
                    e.printStackTrace();
            }
	}
}
