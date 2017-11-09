package mainGame;

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

public class PickAPlayerScreen {
	
	private Game game;
	private Handler handler;
	private HUD hud;
	private String text;
	private String text2;
	private BufferedImage player1;
	private BufferedImage player2;
	private BufferedImage player3;
	private BufferedImage player4;

	public PickAPlayerScreen(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}

	public void tick() {

	}
	
	public void render(Graphics g) {
	Font font = new Font("Apple Chancery", 1, 50);
	Font font2 = new Font("Apple Chancery", 1, 25);
	g.setFont(font);
	g.setColor(Color.white);
	text = "Pick a Player!";
	g.drawString(text, Game.WIDTH / 2 - getTextWidth(font, text) / 2, 70);
	
	player1 = null;
	try {
		player1 = ImageIO.read(new File("images/player1option.png"));
	} catch (Exception e) {
		e.printStackTrace();
	}
	player2 = null;
	try {
		player2 = ImageIO.read(new File("images/player2option.png"));
	} catch (Exception e) {
		e.printStackTrace();
	}
	player3 = null;
	try {
		player3 = ImageIO.read(new File("images/player3option.png"));
	} catch (Exception e) {
		e.printStackTrace();
	}
	player4 = null;
	try {
		player4 = ImageIO.read(new File("images/player4option.png"));
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	g.drawImage(player1, Game.WIDTH/5 - 100, Game.HEIGHT / 2 - 140, 200, 280, null);
	g.drawImage(player2, 2*Game.WIDTH/5 - 100, Game.HEIGHT / 2 - 140, 200, 280, null);
	g.drawImage(player3, 3*Game.WIDTH/5 - 100, Game.HEIGHT / 2 - 140, 200, 280, null);
	g.drawImage(player4, 4*Game.WIDTH/5 - 100, Game.HEIGHT / 2 - 140, 200, 280, null);
	
	}
	
	public int getTextWidth(Font font, String text) {
		AffineTransform at = new AffineTransform();
		FontRenderContext frc = new FontRenderContext(at, true, true);
		int textWidth = (int) (font.getStringBounds(text, frc).getWidth());
		return textWidth;
	}
}
