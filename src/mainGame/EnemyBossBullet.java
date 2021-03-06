// package
package mainGame;

// imports
import java.awt.Color; 
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * The bullets that the first boss shoots
 * 
 * @author Team A4 | Last Edit Date: Dec. 11, 2017
 *
 */

// class
public class EnemyBossBullet extends GameObject {

	// instance variables
	private Handler handler;
	Random r = new Random();
	private int max = 15;
	private int min = -15;

	// constructor
	public EnemyBossBullet(double x, double y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		velX = (r.nextInt((max - min) + 1) + min);// OFFICIAL WAY TO GET A RANGE FOR randInt()
		velY = 30;
	}

	// instance methods
	
	// tick
	public void tick() {
		this.x += velX;
		this.y += velY;

		// if (this.y <= 0 || this.y >= Game.HEIGHT - 40) velY *= -1;
		// if (this.x <= 0 || this.x >= Game.WIDTH - 16) velX *= -1;

		if (this.y >= Game.HEIGHT)
			handler.removeObject(this);

		handler.addObject(new Trail(x, y, ID.Trail, Color.blue, 16, 16, 0.025, this.handler));

	}

	// render 
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect((int) x, (int) y, 16, 16);
	}

	// get bounds
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) this.x, (int) this.y, 16, 16);
	}

}