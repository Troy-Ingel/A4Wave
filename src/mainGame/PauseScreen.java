// package
package mainGame;

// imports
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;

// class
public class PauseScreen {

	// instance methods
	private Game game;
	private Handler handler;
	private HUD hud;
	private String text;
	private BufferedImage background;
	private BufferedImage bg;

	// constructor
	public PauseScreen(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}

	// instance variables
	// tick
	public void tick() {

	}

	// render
	public void render(Graphics g) {
		g.drawImage(bg, 0, 0, Game.WIDTH, Game.HEIGHT, null);

		// g.drawRect(0 , 0, game.WIDTH, game.HEIGHT);
		
		background = null;
		try {
			background = ImageIO.read(new File("images/background.jpg"));
			bg = ImageIO.read(new File("images/blackBG.jpg"));

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Paused");
		Font font = new Font("Apple Chancery", 1, 50);
		text = "Paused";
		g.drawString(text, (int) (game.WIDTH / 2 - getTextWidth(font, text)), (int) (game.HEIGHT / 2));

	}

	// get text width
	public int getTextWidth(Font font, String text) {
		AffineTransform at = new AffineTransform();
		FontRenderContext frc = new FontRenderContext(at, true, true);
		int textWidth = (int) (font.getStringBounds(text, frc).getWidth());
		return textWidth;
	}
}
