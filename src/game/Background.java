package game;


import org.newdawn.slick.Image;



public class Background {
	
	private Image image;
	private double x, y, dx, dy;
	private double moveScale;
	
	public Background(String s, double ms) {
		
		try {
			image = new Image(s);
		} catch (Exception ex) {
			ex.printStackTrace();
		}		
	}
	
	public void setPosition(double x, double y) {
		this.x = (x * moveScale) % 802;
		this.y = (y * moveScale) % 802;
	}
	
	public void setVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	
	public void draw(org.newdawn.slick.Graphics g) {
		g.drawImage(image, (int) x, 
				(int) y, null);
		if (x < 0) {
			g.drawImage(
					image,
					(int) x + 802,
					(int) y, null);
		}
		if (x > 0) {
			g.drawImage(
					image,
					(int) x - 802,
					(int) y, null);
		}
	}
	
}

