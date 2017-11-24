package mainGame;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import mainGame.Game.STATE;

/**
 * This class closely resembles Spawn1to10. Please refer to that class for
 * documentation
 * 
 * @author Brandon Loehle 5/30/16
 *
 */

public class Spawn10to20 {

	public static int LEVEL_SET = 2;
	private Handler handler;
	private HUD hud;
	private Game game;
	private Random r = new Random();
	private int spawnTimer;
	private int levelTimer;
	private String[] side = { "left", "right", "top", "bottom" };

	UnorderedList list = new UnorderedList(11);
	ArrayList<Integer> levels = new ArrayList<Integer>();
	private int levelsRemaining;
	private int levelNumber = 0;
	private int tempCounter = 0;

	public Spawn10to20(Handler handler, HUD hud, Game game) {
		this.handler = handler;
		this.hud = hud;
		this.game = game;
		handler.object.clear();
		hud.restoreHealth();
		spawnTimer = 10;
		levelTimer = 150;
		levelsRemaining = 10;
		tempCounter = 0;
		addLevels();
		list.getItem(0);
		levelNumber = 0;
		list.display();

	}// end of Spawn10to20

	/**
	 * Pre-load every level
	 */
	public void addLevels() {
		for (int i = 1; i <= 11; i++) {
			list.addItem(i);
		} // end for
	}// end addLevels()

	/**
	 * Called once every 60 seconds by the Game loop
	 */
	public void tick() {
		if (levelNumber <= 0) {
			levelTimer--;
			if (tempCounter < 1) { // display intro game message ONE time
				handler.addObject(new LevelText(Game.WIDTH / 2 - 675, Game.HEIGHT / 2 - 100, "Same levels...",
						ID.Levels1to10Text));
				handler.addObject(new LevelText(Game.WIDTH / 2 - 675, Game.HEIGHT / 2, "...but a little harder now",
						ID.Levels1to10Text));
				tempCounter++;
			} // end if
			if (levelTimer <= 0) { // time to play!
				handler.clearEnemies();
				tempCounter = 0;

				levelNumber = 1;
				list.getItem(0);
			} // end if

		} // end if
		/*
		 * EVERY LEVEL WORKS THE SAME WAY
		 * 
		 * Only the first level is commented
		 * 
		 * Please refer to this bit of code to understand how each level works
		 * 
		 */
		else if (levelNumber == 1 && list.getItem(0) == 1) {// this is level 1

			spawnTimer--;// keep decrementing the spawning spawnTimer 60 times a
							// second
			levelTimer--;// keep decrementing the level spawnTimer 60 times a
							// second
			if (tempCounter < 1) {// called only once, but sets the levelTimer
									// to how long we want this level to
									// run for
				handler.addObject(new LevelText(15, 600, "LEVEL 11", ID.Levels1to10Text));
				// J's level transition text
				levelTimer = 1000; // 2000 / 60 method calls a second = 33.33
									// seconds long
				tempCounter++; // ensures the method is only called once
			} // end if

			if (spawnTimer == 0) { // time to spawn another enemy
				handler.addObject(
						new EnemyBasic(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 13, 13, ID.EnemyBasic, handler));
				// add them to the handler, which handles all game objects
				spawnTimer = 80; // reset the spawn timer
			} // end if

			if (levelTimer == 750) {
				// J makes level transition text disappear
				handler.clearText();
			}

			if (levelTimer == 0) {// level is over
				handler.clearEnemies();// clear the enemies
				hud.setLevel(hud.getLevel() + 1);// Increment level number on
													// HUD
				spawnTimer = 40;
				tempCounter = 0;// reset tempCounter
				if (levelsRemaining == 1) {// time for the boss!
					levelNumber = 11;// arbitrary number for the boss level
				} // end if
				else {// not time for the boss, just go to the next level
						// list.removeItem(0);
					list.getItem(1);
					levelNumber++;
				} // end else
			} // end if
		} // end else if

		else if (levelNumber == 2) {
			spawnTimer--;
			levelTimer--;
			if (tempCounter < 1) {
				handler.addObject(new LevelText(15, 600, "LEVEL 12", ID.Levels1to10Text));
				// J's level transition text
				levelTimer = 1000;
				tempCounter++;
			} // end if
			if (spawnTimer == 30) {
				handler.addObject(
						new EnemySweep(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 20, 2, ID.EnemySweep, handler));
			} // end if
			else if (spawnTimer == 20) {
				handler.addObject(
						new EnemySweep(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 20, -2, ID.EnemySweep, handler));
			} /// end else if
			else if (spawnTimer == 10) {
				handler.addObject(
						new EnemySweep(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 20, 4, ID.EnemySweep, handler));
			} // end else if
			else if (spawnTimer == 0) {
				handler.addObject(
						new EnemySweep(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 20, -4, ID.EnemySweep, handler));
				spawnTimer = 45;
			} // end else if

			if (levelTimer == 750) {
				// J makes level transition text disappear
				// J adds health pickup
				handler.clearText();
				handler.addObject(
						new PickupHealth(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 9, 9, ID.PickupHealth, handler));
			}

			if (levelTimer == 0) {
				handler.clearEnemies();
				hud.setLevel(hud.getLevel() + 1);
				tempCounter = 0;
				if (levelsRemaining == 1) {
					levelNumber = 11;
				} // end if
				else {
					list.getItem(2);
					levelNumber++;
				} // end else
			} // end if
		} // end else if
		else if (levelNumber == 3 && list.getItem(2) == 3) {
			spawnTimer--;
			levelTimer--;
			if (tempCounter < 1) {
				handler.addObject(new LevelText(15, 600, "LEVEL 13", ID.Levels1to10Text));
				// J's level transition text
				levelTimer = 1000;
				tempCounter++;
			} // end if
			if (spawnTimer <= 0) {
				handler.addObject(new EnemyBurst(-250, 250, 75, 75, 250, side[r.nextInt(4)], ID.EnemyBurst, handler));
				spawnTimer = 120;
			} // end if

			if (levelTimer == 750) {
				// J makes level transition text disappear
				handler.clearText();
			}

			if (levelTimer == 0) {
				handler.clearEnemies();
				hud.setLevel(hud.getLevel() + 1);
				spawnTimer = 10;
				tempCounter = 0;
				if (levelsRemaining == 1) {
					levelNumber = 11;
				} // end if
				else {
					list.getItem(3);
					levelNumber++;
				} // end else
			} // end if
		} // end else if

		else if (levelNumber == 4 && list.getItem(3) == 4) {
			levelTimer--;
			if (tempCounter < 1) {
				handler.addObject(new LevelText(15, 600, "LEVEL 14", ID.Levels1to10Text));
				// J's level transition text
				handler.addObject(new EnemyShooter(r.nextInt(Game.WIDTH) - 35, r.nextInt(Game.HEIGHT) - 75, 100, 100,
						-30, ID.EnemyShooter, this.handler));
				levelTimer = 1000;
				tempCounter++;
			} // end if

			if (levelTimer == 750) {
				// J makes level transition text disappear
				// J adds health pickup
				handler.clearText();
				handler.addObject(new PickupHealth(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 9, 9, ID.PickupHealth, handler));
			}

			if (levelTimer == 0) {
				handler.clearEnemies();
				hud.setLevel(hud.getLevel() + 1);
				spawnTimer = 10;
				tempCounter = 0;
				
				if (levelsRemaining == 1) {
					levelNumber = 11;
				} // end if
				else {
					list.getItem(4);
					levelNumber++;
				} // end else
			} // end if
		} // end else if

		else if (levelNumber == 5 && list.getItem(4) == 5) {
			spawnTimer--;
			levelTimer--;
			if (tempCounter < 1) {
				handler.addObject(new LevelText(15, 600, "LEVEL 15", ID.Levels1to10Text));
				// J's level transition text
				levelTimer = 1000;
				tempCounter++;
			} // end if
			if (spawnTimer == 0) {
				handler.addObject(
						new EnemySmart(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), -7, ID.EnemySmart, handler));
				spawnTimer = 80;
			} // end if

			if (levelTimer == 750) {
				// J makes level transition text disappear
				handler.clearText();
			}

			if (levelTimer == 0) {
				handler.clearEnemies();
				hud.setLevel(hud.getLevel() + 1);
				spawnTimer = 10;
				tempCounter = 0;
				if (levelsRemaining == 1) {
					levelNumber = 11;
				} // end if
				else {
					list.getItem(5);
					levelNumber++;
				} // end else
			} // end if
		} // end else if
		
		else if (levelNumber == 6 && list.getItem(5) == 6) {
			spawnTimer--;
			levelTimer--;
			if (tempCounter < 1) {
				handler.addObject(new LevelText(15, 600, "LEVEL 16", ID.Levels1to10Text));
				// J's level transition text
				levelTimer = 1000;
				tempCounter++;
			} // end if
			if (spawnTimer == 0) {
				handler.addObject(
						new EnemyBasic(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 15, 15, ID.EnemyBasic, handler));
				spawnTimer = 50;
			} // end if

			if (levelTimer == 750) {
				// J makes level transition text disappear
				// J adds health pickup
				handler.addObject(new PickupHealth(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 9, 9, ID.PickupHealth, handler));
				handler.clearText();
			}

			if (levelTimer == 0) {
				handler.clearEnemies();
				hud.setLevel(hud.getLevel() + 1);
				spawnTimer = 40;
				tempCounter = 0;
				if (levelsRemaining == 1) {
					levelNumber = 11;
				} // end if
				else {
					list.getItem(6);
					levelNumber++;
				} // end else
			} // end if
		} // end else if

		else if (levelNumber == 7 && list.getItem(6) == 7) {
			spawnTimer--;
			levelTimer--;
			if (tempCounter < 1) {
				handler.addObject(new LevelText(15, 600, "LEVEL 17", ID.Levels1to10Text));
				// J's level transition text
				levelTimer = 1000;
				tempCounter++;
			} // end if
			if (spawnTimer == 35) {
				handler.addObject(
						new EnemySweep(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 10, 2, ID.EnemySweep, handler));
			} // end if
			else if (spawnTimer == 25) {
				handler.addObject(
						new EnemySweep(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 30, -2, ID.EnemySweep, handler));
			} // end else if
			else if (spawnTimer == 15) {
				handler.addObject(
						new EnemySweep(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 30, 4, ID.EnemySweep, handler));
			} // end if
			else if (spawnTimer == 0) {
				handler.addObject(
						new EnemySweep(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 30, -4, ID.EnemySweep, handler));
				spawnTimer = 40;
			} // end else if

			if (levelTimer == 750) {
				// J makes level transition text disappear
				handler.clearText();
			}

			if (levelTimer == 0) {
				handler.clearEnemies();
				hud.setLevel(hud.getLevel() + 1);
				tempCounter = 0;
				if (levelsRemaining == 1) {
					levelNumber = 11;
				} // end if
				else {
					list.getItem(7);
					levelNumber++;
				} // end else
			} // end if
		} // end else if

		else if (levelNumber == 8 && list.getItem(7) == 8) {
			spawnTimer--;
			levelTimer--;
			if (tempCounter < 1) {
				handler.addObject(new LevelText(15, 600, "LEVEL 18", ID.Levels1to10Text));
				// J's level transition text
				levelTimer = 1000;
				tempCounter++;
			} // end if
			if (spawnTimer <= 0) {
				handler.addObject(new EnemyBurst(-300, 300, 60, 60, 300, side[r.nextInt(4)], ID.EnemyBurst, handler));
				spawnTimer = 60;
			} // end if

			if (levelTimer == 750) {
				// J makes level transition text disappear
				// J adds health pickup
				handler.addObject(new PickupHealth(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 9, 9, ID.PickupHealth, handler));
				handler.clearText();
			}

			if (levelTimer == 0) {
				handler.clearEnemies();
				hud.setLevel(hud.getLevel() + 1);
				spawnTimer = 10;
				tempCounter = 0;
				if (levelsRemaining == 1) {
					levelNumber = 11;
				} // end if
				else {
					list.getItem(8);
					levelNumber++;
				} // end else
			} // end if
		} // end else if

		else if (levelNumber == 9 && list.getItem(8) == 9) {
			levelTimer--;
			if (tempCounter < 1) {
				handler.addObject(new LevelText(15, 600, "LEVEL 19", ID.Levels1to10Text));
				// J's level transition text
				handler.addObject(new EnemyShooter(r.nextInt(Game.WIDTH) - 35, r.nextInt(Game.HEIGHT) - 75, 200, 200,
						-40, ID.EnemyShooter, this.handler));
				levelTimer = 1000;
				tempCounter++;
			} // end if

			if (levelTimer == 750) {
				// J makes level transition text disappear
				handler.clearText();
			}

			if (levelTimer == 0) {
				handler.clearEnemies();
				hud.setLevel(hud.getLevel() + 1);
				spawnTimer = 10;
				tempCounter = 0;
				if (levelsRemaining == 1) {
					levelNumber = 11;
				} // end if
				else {
					list.getItem(9);
					levelNumber++;
				} // end else
			} // end if
		} // end else if
		/*
		 */

		else if (levelNumber == 10) {
			spawnTimer--;
			levelTimer--;
			if (tempCounter < 1) {
				handler.addObject(new LevelText(15, 600, "LEVEL 20", ID.Levels1to10Text));
				// J's level transition text
				levelTimer = 1000;
				tempCounter++;
			} // end if
			if (spawnTimer == 0) {
				handler.addObject(
						new EnemySmart(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), -9, ID.EnemySmart, handler));
				spawnTimer = 50;
			} // end if

			if (levelTimer == 750) {
				// J makes level transition text disappear
				// J adds health and speed pickups
				handler.clearText();
				handler.addObject(new PickupHealth(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 9, 9, ID.PickupHealth, handler));
				handler.addObject(new PickupSpeed(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 9, 9, ID.PickupSpeed, handler));
			}

			if (levelTimer == 0) {
				handler.clearEnemies();
				hud.setLevel(hud.getLevel() + 1);
				spawnTimer = 10;
				tempCounter = 0;
				if (levelsRemaining == 1) {
					levelNumber = 11;
				} // end if
				else {
					list.getItem(10);
					levelNumber++;

				} // end else
			} // end if
		} // end else if
		else if (levelNumber == 11) {// arbitrary
										// number for
										// the boss
			if (tempCounter < 1) {
				handler.addObject(new LevelText(15, 600, "BOSS LEVEL", ID.Levels1to10Text));
				// J's level transition text
				tempCounter++;
			} // end if

			else if (tempCounter >= 1) {
				handler.addObject(new BossEye(Game.WIDTH / 2 - 150, Game.HEIGHT / 2 - 150, ID.BossEye, handler, 1));
				handler.addObject(new BossEye(Game.WIDTH / 2 - 50, Game.HEIGHT / 2 - 150, ID.BossEye, handler, 2));
				handler.addObject(new BossEye(Game.WIDTH / 2 + 50, Game.HEIGHT / 2 - 150, ID.BossEye, handler, 3));
				handler.addObject(new BossEye(Game.WIDTH / 2 - 150, Game.HEIGHT / 2 - 50, ID.BossEye, handler, 4));
				handler.addObject(new BossEye(Game.WIDTH / 2 - 50, Game.HEIGHT / 2 - 50, ID.BossEye, handler, 5));
				handler.addObject(new BossEye(Game.WIDTH / 2 + 50, Game.HEIGHT / 2 - 50, ID.BossEye, handler, 6));
				handler.addObject(new BossEye(Game.WIDTH / 2 - 150, Game.HEIGHT / 2 + 50, ID.BossEye, handler, 7));
				handler.addObject(new BossEye(Game.WIDTH / 2 - 50, Game.HEIGHT / 2 + 50, ID.BossEye, handler, 8));
				handler.addObject(new BossEye(Game.WIDTH / 2 + 50, Game.HEIGHT / 2 + 50, ID.BossEye, handler, 9));
				tempCounter++;
			} // end else if

		} // end else if

	}// end tick()

	public void skipLevel() {
		if (levelsRemaining == 1) {
			tempCounter = 0;
			levelNumber = 11;
		} // end if
		else if (levelsRemaining > 1) {
			list.getItem(levelNumber + 1);
			levelsRemaining--;
		} // end else if
	}// end skipLevel

	public void restart() {
		levelNumber = -10;
		tempCounter = 0;
		levelTimer = 150;
		levelsRemaining = 10;
		list.getItem(0);
		game.gameState = STATE.Menu;

	}// end restart()

	public int getTextWidth(Font font, String text) {
		AffineTransform at = new AffineTransform();
		FontRenderContext frc = new FontRenderContext(at, true, true);
		int textWidth = (int) (font.getStringBounds(text, frc).getWidth());
		return textWidth;
	}

}// end Spawn10to20