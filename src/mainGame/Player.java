// package
package mainGame;

// imports
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Random;
import mainGame.Game.STATE;

/**
 * The main player in the game
 * 
 * @author Team A4 | Last Edit Date: Dec. 11, 2017
 *
 */

// class
public class Player extends GameObject {

	// instance variables
	Random r = new Random();
	Handler handler;
	private HUD hud;
	private Game game;
	private int damage;
	private int playerWidth, playerHeight;
	public static int playerSpeed = 10;
	public int characterId;

	// constructor
	public Player(double x, double y, ID id, Handler handler, HUD hud, Game game) {
		super(x, y, id);
		this.handler = handler;
		this.hud = hud;
		this.game = game;
		this.damage = 2;
		playerWidth = 32;
		playerHeight = 32;
	}

	// instance methods

	// tick
	@Override
	public void tick() {
		this.x += velX;
		this.y += velY;
		x = Game.clamp(x, 0, Game.WIDTH - 38);
		y = Game.clamp(y, 0, Game.HEIGHT - 60);

		// add the trail that follows it
		// EVAN CHANGES HERE: removed code for player trail, kept still for
		// enemy

		collision();
		checkIfDead();

	}

	// check if dead
	public void checkIfDead() {
		if (hud.health <= 0) {// player is dead, game over!

			if (hud.getExtraLives() == 0) {
				game.gameState = STATE.GameOver;
			}

			else if (hud.getExtraLives() > 0) {// has an extra life, game
												// continues
				hud.setExtraLives(hud.getExtraLives() - 1);
				hud.setHealth(100);
			}
		}
	}

	/**
	 * Checks for collisions with all of the enemies, and handles it accordingly
	 */
	public void collision() {

		hud.updateScoreColor(Color.white);
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.EnemyBasic || tempObject.getId() == ID.EnemyFast
					|| tempObject.getId() == ID.EnemySmart || tempObject.getId() == ID.EnemyBossBullet
					|| tempObject.getId() == ID.EnemySweep || tempObject.getId() == ID.EnemyShooterBullet
					|| tempObject.getId() == ID.EnemyBurst || tempObject.getId() == ID.EnemyShooter
					|| tempObject.getId() == ID.BossEye) {// tempObject is an
															// enemy

				// collision code
				if (getBounds().intersects(tempObject.getBounds())) {// player
																		// hit
																		// an
																		// enemy
					hud.health -= damage;
					hud.updateScoreColor(Color.red);
				}

			}
			if (tempObject.getId() == ID.EnemyBoss) {
				// Allows player time to get out of upper area where they will
				// get hurt once the
				// boss starts moving
				if (this.y <= 138 && tempObject.isMoving) {
					hud.health -= 2;
					hud.updateScoreColor(Color.red);
				}
			}
			if (tempObject.getId() == ID.PickupHealth) {
				if (getBounds().intersects(tempObject.getBounds())) {
					// player gains health when they touch health pickup
					hud.addHealth();
					handler.clearPickupHealth();
				}
			}
			if (tempObject.getId() == ID.PickupSpeed) {
				if (getBounds().intersects(tempObject.getBounds())) {
					// player gain speed when they touch pickup
					hud.addBoost();
					handler.clearPickupSpeed();
				}
			}

		}
	}

	// render
	@Override
	public void render(Graphics g) {

		// g.setColor(Color.white);
		// g.fillRect((int) x, (int) y, playerWidth, playerHeight);

		g.drawImage(getCharacter(characterId), (int) x, (int) y, playerWidth, playerHeight, null);
	}

	// get bounds
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) this.x, (int) this.y, 32, 32);
	}

	// set damage
	public void setDamage(int damage) {
		this.damage = damage;
	}

	// set player size
	public void setPlayerSize(int size) {
		this.playerWidth = size;
		this.playerHeight = size;
	}

	/*
	 * get character allows different character to be played before game begins
	 */
	public Image getCharacter(int characterId) {
		if (characterId == 1) {
			return getImage("images/player1.png");
		} else if (characterId == 2) {
			return getImage("images/player2.png");
		} else if (characterId == 3) {
			return getImage("images/player3.png");
		} else if (characterId == 4) {
			return getImage("images/player4.png");
		} else if (characterId == 5) {
			return getImage("images/player5.png");
		} else if (characterId == 6) {
			return getImage("images/player6.png");
		} else if (characterId == 7) {
			return getImage("images/player7.png");
		} else if (characterId == 8) {
			return getImage("images/player8.png");
		} else {
			return getImage("images/player1.png");
		}
	}

	// set character
	public void setCharacter(int id) {
		characterId = id;
	}

	// image
	public Image getImage(String path) {
		Image image = null;
		try {
			image = Toolkit.getDefaultToolkit().getImage(path);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return image;
	}

}
