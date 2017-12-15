// package
package mainGame;

// imports
import java.awt.Color; 
import java.awt.Font;
import java.awt.Graphics;

/**
 * The main Heads Up Display of the game
 * 
 * @author Team A4 | Last Edit Date: Dec. 11, 2017
 *
 */

// class
public class HUD {
	// HUD fixed
	// instance variables
	public double health = 100;
	private double healthMax = 100;

	private double greenValue = 255;

	private int score = 00000000000;
	private int level = 0;

	private boolean regen = false;
	private int timer = 60;
	private int healthBarWidth = 600;
	private int healthBarModifier = 2;
	private boolean doubleHealth = false;
	private String ability = "";
	private int abilityUses;

	private Color scoreColor = Color.white;

	private int extraLives = 0;

	// methods
	
	// tick
	public void tick() {
		health = Game.clamp(health, 0, health);

		greenValue = Game.clamp(greenValue, 0, 255);

		greenValue = health * healthBarModifier;

		score++;

		if (regen == true) {// regenerates health if that ability has been
							// unlocked
			timer--;
			if (timer == 0 && health < 100) {
				health += 1;
				timer = 60;
			}
		}

	}

	// render
	public void render(Graphics g) {
		Font font = new Font("Amoebic", 1, 30);

		g.setColor(Color.GRAY);
		g.fillRect(15, 15, healthBarWidth, 96);
		g.setColor(new Color(75, (int) greenValue, 0));
		g.fillRect((int) 15, (int) 15, (int) health * 6, 96);
		g.setColor(scoreColor);
		g.drawRect(15, 15, healthBarWidth, 96);

		g.setFont(font);

		g.drawString("Score: " + score, 15, 150);
		g.drawString("Level: " + level, 15, 185);
		g.drawString("Extra Lives: " + extraLives, 15, 220);

		if (ability.equals("freezeTime")) {
			g.drawString("Time Freezes: " + abilityUses, Game.WIDTH - 300, 64);
		} else if (ability.equals("clearScreen")) {
			g.drawString("Screen Clears: " + abilityUses, Game.WIDTH - 300, 64);
		} else if (ability.equals("levelSkip")) {
			g.drawString("Level Skips: " + abilityUses, Game.WIDTH - 300, 64);
		}
	}

	// getters and setters
	
	public void setAbility(String ability) {
		this.ability = ability;
	}

	public int getAbilityUses() {
		return this.abilityUses;
	}

	public void setAbilityUses(int abilityUses) {
		this.abilityUses = abilityUses;
	}

	public void updateScoreColor(Color color) {
		this.scoreColor = color;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getScore() {
		return score;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void setRegen() {
		regen = true;
	}

	public void resetRegen() {
		regen = false;
	}

	public void setExtraLives(int lives) {
		this.extraLives = lives;
	}

	public int getExtraLives() {
		return this.extraLives;
	}

	// for health increase
	public void healthIncrease() {
		doubleHealth = true;
		healthMax = 200;
		this.health = healthMax;
		healthBarModifier = 1;
		healthBarWidth = 1200;
	}

	// to reset health
	public void resetHealth() {
		doubleHealth = false;
		healthMax = 100;
		this.health = healthMax;
		healthBarModifier = 2;
		healthBarWidth = 600;
	}

	// to restore health
	public void restoreHealth() {
		this.health = healthMax;
	}

	// to add health
	public void addHealth() {
		if (healthMax == 100) {
			if (health <= 75) {
				this.health += 25;
			} else {
				this.health = healthMax;
			}
		} else if (healthMax == 200) {
			if (health <= 150) {
				this.health += 50;
			} else {
				this.health = healthMax;
			}
		}
	}

	// to add boost
	public void addBoost() {
		Player.playerSpeed = 20;
	}
}