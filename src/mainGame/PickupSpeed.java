package mainGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class PickupSpeed extends GameObject {
	// moves similar to Enemy Sweep

	private Handler handler;
	private Image img;

	public PickupSpeed(double x, double y, int velX, int velY, ID id, Handler handler) {
		super(x, y, id);
		this.img = getImage("images/speed.png");
		this.handler = handler;
		this.velX = velX;
		this.velY = velY;
	}

	public void tick() {
		this.x += velX;
		this.y += velY;

		if (this.y <= 0 || this.y >= Game.HEIGHT - 40)
			velY *= -1;
		if (this.x <= 0 || this.x >= Game.WIDTH - 16)
			velX *= -1;
	}

	public void render(Graphics g) {
		g.drawImage(img, (int) this.x, (int) this.y, null);

	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) this.x, (int) this.y, (int) this.img.getWidth(null), (int) this.img.getHeight(null));
	}

	public Image getImage(String path) {
		Image image = null;
		try {
			image = Toolkit.getDefaultToolkit().getImage(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;

	}

}
