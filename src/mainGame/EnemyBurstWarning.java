// package
package mainGame;

// imports
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * A warning that is displayed before EnemyBurst comes across the screen
 * 
 * @author Team A4 | Last Edit Date: Dec. 11, 2017
 *
 */

// class
public class EnemyBurstWarning extends GameObject {
	// instance variables
	private Handler handler;
	private int width;
	private int height;
	private int timer;
	private Color color;
	private int hasFlashed;

	// constructor
	public EnemyBurstWarning(double x, double y, int width, int height, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.width = width;
		this.height = height;
		timer = 10;
		this.color = Color.orange;
		this.hasFlashed = 0;
	}

	// instance methods

	// tick
	public void tick() {
		flash();
		checkFlash();
	}

	// flash
	public void flash() {
		timer--;
		if (timer == 5) {
			this.color = Color.black;
		} else if (timer == 0) {
			this.color = Color.orange;
			this.hasFlashed++;
			timer = 10;
		}

	}

	// check flash
	public void checkFlash() {
		if (this.hasFlashed == 5) {
			for (int i = 0; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);
				if (tempObject.getId() == ID.EnemyBurstWarning) {
					handler.removeObject(tempObject);
					i--;
				}
			}
		}
	}

	// render
	public void render(Graphics g) {
		g.setColor(this.color);
		g.fillRect((int) x, (int) y, this.width, this.height);

	}

	// get bounds - rectangle
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) this.x, (int) this.y, 16, 16);
	}

}
