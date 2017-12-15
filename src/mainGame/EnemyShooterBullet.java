// package
package mainGame;

// import
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * A type of enemy in the game
 * 
 * @author Team A4 | Last Edit Date: Dec. 11, 2017
 *
 */

// class
public class EnemyShooterBullet extends GameObject {
	// instance variable
	private Handler handler;

	// constructor
	public EnemyShooterBullet(double x, double y, double velX, double velY, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.velX = velX;
		this.velY = velY;
	}

	// insance methods

	// tick
	public void tick() {
		this.x += velX;
		this.y += velY;

		// if (this.y <= 0 || this.y >= Game.HEIGHT - 40) velY *= -1;
		// if (this.x <= 0 || this.x >= Game.WIDTH - 16) velX *= -1;

		handler.addObject(new Trail(x, y, ID.Trail, Color.yellow, 4, 4, 0.025, this.handler));

		removeBullets();
	}

	// remove bullets
	public void removeBullets() {

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.EnemyShooterBullet) {
				if (tempObject.getX() >= Game.WIDTH || tempObject.getY() >= Game.HEIGHT) {
					handler.removeObject(tempObject);
				}
			}

		}

	}

	// render
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int) x, (int) y, 4, 4);

	}

	// get bounds - rectangle
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) this.x, (int) this.y, 16, 16);
	}

}
