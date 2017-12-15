// package
package mainGame;

// imports
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * The trail that follows the player and some of the enemies
 * 
 * @author Team A4 | Last Edit Date: Dec. 11, 2017
 *
 */

// class
public class Trail extends GameObject {

	// instance variables
	private float alpha = 1;
	private Handler handler;
	private Color color;
	private int width, height;
	private double life; // life = 0.01 -> 0.1

	// constructor
	public Trail(double x, double y, ID id, Color color, int width, int height, double life, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.color = color;
		this.width = width;
		this.height = height;
		this.life = life;

	}

	// instance methods

	// tick
	public void tick() {// slowly fades each square
		if (alpha > life) {
			alpha -= life - 0.001;
		} else
			handler.removeObject(this);

	}

	// render - graphics
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(alpha));
		g.setColor(color);
		g.fillRect((int) this.x, (int) this.y, this.width, this.height);
		g2d.setComposite(makeTransparent(1));// allows for the rectangle to appear like it's fading
	}

	/**
	 * Helps make the rectangle fade away
	 * 
	 * @param alpha
	 *            is the amount of fade
	 * @return the AlphaComposite instance of this alpha level (simply how Swing
	 *         allows you to use opacity in objects)
	 */
	private AlphaComposite makeTransparent(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return (AlphaComposite.getInstance(type, alpha));

	}

	// get bounds of Rectangle
	public Rectangle getBounds() {
		return null;
	}

}
