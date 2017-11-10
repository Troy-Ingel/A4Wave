package mainGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import mainGame.Game.STATE;

/**
 * The main menu
 * 
 * @author Brandon Loehle 5/30/16
 *
 */

public class Menu {
	// why isn't this coming up as a change?

	private Game game;
	private Handler handler;
	private HUD hud;
	private BufferedImage background;
	private int timer;
	private Random r;
	private ArrayList<Color> colorPick = new ArrayList<Color>();
	private int colorIndex;
	private Spawn1to10 spawner;
	private String text1;
	private String text2;
	private String text3;
	private String text4;
	private String text5;
	private String text6;
	private String text7;
	private String text8;
	private String text9;
	private String text10;
	private String text11;
	private String text12;
	private String[] scores;
	// public Leaderboard lb;

	public Menu(Game game, Handler handler, HUD hud, Spawn1to10 spawner) {
		HighscoreManager hm = new HighscoreManager();
		scores = new String[hm.getScores().size()];
		for (int i = 0; i < hm.getScores().size(); i++) {

			scores[i] = hm.getHighscoreString(i);
		}
		this.game = game;
		this.handler = handler;
		this.hud = hud;
		this.spawner = spawner;
		timer = 10;
		r = new Random();
		addColors();

		background = null;
		try {
			background = ImageIO.read(new File("images/background.jpg"));

		} catch (Exception e) {
			e.printStackTrace();
		}

		handler.addObject(new MenuFireworks((r.nextInt(Game.WIDTH) - 25), 500, 50, 50, 0, -2,
				colorPick.get(r.nextInt(6)), ID.Firework, this.handler));

	}

	public void addColors() {
		colorPick.add(Color.blue);
		colorPick.add(Color.white);
		colorPick.add(Color.green);
		colorPick.add(Color.red);
		colorPick.add(Color.cyan);
		colorPick.add(Color.magenta);
		colorPick.add(Color.yellow);
	}

	public void tick() {
		timer--;
		if (timer <= 0) {
			handler.object.clear();
			colorIndex = r.nextInt(6);
			handler.addObject(new MenuFireworks((r.nextInt(Game.WIDTH) - 25), 1080, 100, 100, 0, -4,
					colorPick.get(colorIndex), ID.Firework, this.handler));
			timer = 300;
		}
		handler.tick();
	}

	public void render(Graphics g) {
		if (game.gameState == STATE.Menu) {
			g.drawImage(background, 0, 0, Game.WIDTH, Game.HEIGHT, null);
			handler.render(g);
			Font font = new Font("Apple Chancery", 1, 50);
			// J fixed the HUD on the menu

			g.setFont(font);
			g.setColor(Color.white);
			g.drawString("Harry Potter Waves", 100, 50);

			// g.setColor(Color.white);
			g.drawRect(100, Game.HEIGHT / 6 - 65, Game.WIDTH - 200, 100);
			g.setFont(font);
			// g.setColor(Color.white);
			text1 = "Play";
			g.drawString(text1, Game.WIDTH / 2 - getTextWidth(font, text1) / 2, Game.HEIGHT / 6);

			// g.setColor(Color.white);
			g.drawRect(100, Game.HEIGHT / 3 - 65, Game.WIDTH - 200, 100);
			g.setFont(font);
			// g.setColor(Color.white);
			text2 = "Help";
			g.drawString(text2, Game.WIDTH / 2 - getTextWidth(font, text2) / 2, Game.HEIGHT / 3);

			// g.setColor(Color.white);
			g.drawRect(100, Game.HEIGHT / 2 - 65, Game.WIDTH - 200, 100);
			g.setFont(font);
			// g.setColor(Color.white);
			text10 = "Leaderboard";
			g.drawString(text10, Game.WIDTH / 2 - getTextWidth(font, text10) / 2, Game.HEIGHT / 2);

			// g.setColor(Color.white);
			g.drawRect(100, 2 * Game.HEIGHT / 3 - 65, Game.WIDTH - 200, 100);
			g.setFont(font);
			// g.setColor(Color.white);
			text3 = "Credits";
			g.drawString(text3, Game.WIDTH / 2 - getTextWidth(font, text3) / 2, 2 * Game.HEIGHT / 3);

			// g.setColor(Color.white);
			g.drawRect(100, 5 * Game.HEIGHT / 6 - 65, Game.WIDTH - 200, 100);
			g.setFont(font);
			// g.setColor(Color.white);
			text4 = "Quit";
			g.drawString(text4, Game.WIDTH / 2 - getTextWidth(font, text4) / 2, 5 * Game.HEIGHT / 6);

		} else if (game.gameState == STATE.Help) {// if the user clicks on
													// "help"
			Font font = new Font("Apple Chancery", 1, 50);
			Font font2 = new Font("Apple Chancery", 1, 25);

			g.setFont(font);
			g.setColor(Color.white);
			text5 = "Help";
			g.drawString(text5, Game.WIDTH / 2 - getTextWidth(font, text5) / 2, 70);

			g.setFont(font2);
			text6 = "Use arrow keys to avoid enemies. Avoid enemies long enough to rack up points and advance to the"
					+ " next level!";
			g.drawString(text6, Game.WIDTH / 2 - getTextWidth(font2, text6) / 2, 150);
			text11 = "Capture the Golden Snitch to gain a speed boost!"
					+ " Or capture the Chocolate Frog to regain some health!";
			g.drawString(text11, Game.WIDTH / 2 - getTextWidth(font2, text11) / 2, 200);
			text7 = "Defeat the boss on Level 11 to win an upgrade!";
			g.drawString(text7, Game.WIDTH / 2 - getTextWidth(font2, text7) / 2, 250);
			text8 = "Ability upgrades are used by pressing Enter.";
			g.drawString(text8, Game.WIDTH / 2 - getTextWidth(font2, text8) / 2, 300);

			// g.drawString("Waves: Simply use WASD to avoid enemies. Once you
			// avoid" + " \n"
			// + "them long enough, a new batch will spawn in!"
			// + "Defeat each boss to win!", 40, 200);

			g.setFont(font2);
			g.setColor(Color.white);
			text9 = "Back";
			g.drawRect(Game.WIDTH / 2 - getTextWidth(font2, text9), 415, 100, 50);
			g.drawString(text9, Game.WIDTH / 2 - getTextWidth(font2, text9) / 2, 450);
		} else if (game.gameState == STATE.Leaderboard) { // This is the
															// leaderboard
															// rendering
			// once more for all the marbles

			Font font = new Font("Apple Chancery", 1, 50);
			Font font2 = new Font("Apple Chancery", 1, 25);
			Font font3 = new Font("Apple Chancery", 1, 35);
			// lb.Display();
			g.setFont(font);
			g.setColor(Color.white);
			text5 = "Leaderboard";

			int y = Game.HEIGHT / 5;
			g.drawString(text5, Game.WIDTH / 2 - getTextWidth(font, text5) / 2, 70);

			g.setFont(font3);
			g.setColor(Color.white);
			
			int numScoresToShow = Math.min(5, scores.length);
			
			for (int i = 0; i < numScoresToShow; i++) {
				g.drawString(scores[i], Game.WIDTH / 2 - getTextWidth(font3, scores[i]) / 2, y); // 1
				y += 100;
			}

			g.setFont(font2);
			g.setColor(Color.white);
			text9 = "Back";
			g.drawRect(Game.WIDTH / 2 - getTextWidth(font2, text9), 3 * Game.HEIGHT / 4 + 25, 100, 50);
			g.drawString(text9, Game.WIDTH / 2 - getTextWidth(font2, text9) / 2, 3 * Game.HEIGHT / 4 + 60);
		}
	}

	public int getTextWidth(Font font, String text) {
		AffineTransform at = new AffineTransform();
		FontRenderContext frc = new FontRenderContext(at, true, true);
		int textWidth = (int) (font.getStringBounds(text, frc).getWidth());
		return textWidth;
	}

}