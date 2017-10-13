package mainGame;

import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import mainGame.Game.STATE;

/**
 * Contains the programming of levels 1-10, as well as handles levell
 * progressionn
 * 
 * @author Brandon Loehle 5/30/16
 */

public class Spawn1to10 {

	public static int LEVEL_SET = 1;
	private Handler handler;
	private HUD hud;
	private Game game;
	private int scoreKeep = 0;
	private Random r = new Random();
	private int spawnTimer;
	private int levelTimer;
	private String[] side = { "left", "right", "top", "bottom" };
	// int[] arrlevels = new int [11] ;

	UnorderedList list = new UnorderedList(11);
	ArrayList<Integer> levels = new ArrayList<Integer>(); // MAKE THIS AN
	// ARRAY
	// LIST SO I CAN
	// REMOVE OBJECTS
	private int index;
	private int levelsRemaining;
	private int levelNumber = 0;
	private int tempCounter = 0;

	public Spawn1to10(Handler handler, HUD hud, Game game) {
		this.handler = handler;
		this.hud = hud;
		this.game = game;
		handler.object.clear();
		hud.health = 100;
		hud.setScore(0);
		hud.setLevel(1);
		spawnTimer = 10;
		levelTimer = 150;
		levelsRemaining = 10;
		hud.setLevel(1);
		tempCounter = 0;
		addLevels();
		list.getItem(0);
		// index = r.nextInt(levelsRemaining);
		levelNumber = 0;
		list.display();

	}// end of Spawn1to10

	/**
	 * Pre-load every level
	 */
	public void addLevels() {
		for (int i = 1; i <= 11; i++) {
			list.addItem(i);
			// levels.add(i);

		} // end for
	}// end addLevels()

	/**
	 * Called once every 60 seconds by the Game loop
	 */
	public void tick() {
		if (levelNumber <= 0) {
			levelTimer--;
			if (tempCounter < 1) {// display intro game message ONE time
				handler.addObject(new LevelText(15, 600, "Let's start off easy...", ID.Levels1to10Text));
				tempCounter++;
			} // end if
			if (levelTimer <= 0) {// time to play!
				handler.clearEnemies();
				tempCounter = 0;
				// levelNumber = levels.get(index);

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
			handler.addObject(new LevelText(15, 600, "This is Level 1!", ID.Levels1to10Text));
			if (tempCounter < 1) {// called only once, but sets the levelTimer
									// to how long we want this level to
									// run for
				levelTimer = 1000;// 2000 / 60 method calls a second = 33.33
									// seconds long
				tempCounter++;// ensures the method is only called once
			} // end if
			if (spawnTimer == 0) {// time to spawn another enemy
				handler.addObject(
						new EnemyBasic(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 9, 9, ID.EnemyBasic, handler));// add
																														// them
																														// to
																														// the
																														// handler,
																														// which
																														// handles
																														// all
																														// game
																														// objects
				spawnTimer = 100;// reset the spawn timer
			} // end if
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

					/*
					 * levels.remove(index);// remove the current level from
					 * being // selected levelsRemaining--; index =
					 * r.nextInt(levelsRemaining);// pick another level at //
					 * random levelNumber = levels.get(index);// set levelNumber
					 * to // whatever index was // randomly selected
					 */
				} // end else
			} // end if
		} // end else if
		else if (levelNumber == 2) {
			spawnTimer--;
			levelTimer--;
			handler.addObject(new LevelText(15, 600, "This is Level 2", ID.Levels1to10Text));
			if (tempCounter < 1) {
				levelTimer = 2000;
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
				spawnTimer = 80;
			} // end else if

			if (levelTimer == 0) {
				handler.clearEnemies();
				hud.setLevel(hud.getLevel() + 1);
				tempCounter = 0;
				if (levelsRemaining == 1) {
					levelNumber = 11;
				} // end if
				else {
					/*
					 * levels.remove(index); levelsRemaining--; index =
					 * r.nextInt(levelsRemaining); levelNumber =
					 * levels.get(index);
					 */
					list.getItem(2);
					levelNumber++;
				} // end else
			} // end if
		} // end else if
		else if (levelNumber == 3) {
			spawnTimer--;
			levelTimer--;
			handler.addObject(new LevelText(15, 600, "This is Level 3", ID.Levels1to10Text));
			if (tempCounter < 1) {
				levelTimer = 1500;
				tempCounter++;
			} // end if
			if (spawnTimer == 0) {
				handler.addObject(
						new EnemySmart(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), -5, ID.EnemySmart, handler));
				spawnTimer = 100;
			} // end if
			if (levelTimer == 0) {
				handler.clearEnemies();
				hud.setLevel(hud.getLevel() + 1);
				spawnTimer = 10;
				tempCounter = 0;
				if (levelsRemaining == 1) {
					levelNumber = 11;
				} // end if
				else {
					/*
					 * levels.remove(index); levelsRemaining--; index =
					 * r.nextInt(levelsRemaining); levelNumber =
					 * levels.get(index);
					 */
					list.getItem(3);
					levelNumber++;

				} // end else
			} // end if
		} // end else if
		else if (levelNumber == 4 && list.getItem(3) == 4) {
			levelTimer--;
			handler.addObject(new LevelText(15, 600, "This is Level 4", ID.Levels1to10Text));
			if (tempCounter < 1) {
				handler.addObject(new EnemyShooter(r.nextInt(Game.WIDTH) - 35, r.nextInt(Game.HEIGHT) - 75, 100, 100,
						-20, ID.EnemyShooter, this.handler));
				levelTimer = 1300;
				tempCounter++;
			} // end if

			if (levelTimer == 0) {
				handler.clearEnemies();
				hud.setLevel(hud.getLevel() + 1);
				spawnTimer = 10;
				tempCounter = 0;
				if (levelsRemaining == 1) {
					levelNumber = 11;
				} // end if
				else {
					/*
					 * levels.remove(index); levelsRemaining--; index =
					 * r.nextInt(levelsRemaining); levelNumber =
					 * levels.get(index);
					 */
					list.getItem(4);
					levelNumber++;
				} // end else
			} // end if
		} // end else if
		else if (levelNumber == 5 && list.getItem(4) == 5) {
			spawnTimer--;
			levelTimer--;
			handler.addObject(new LevelText(15, 600, "This is Level 5", ID.Levels1to10Text));
			if (tempCounter < 1) {
				levelTimer = 1400;
				tempCounter++;
			} // end if
			if (spawnTimer <= 0) {
				handler.addObject(new EnemyBurst(-200, 200, 50, 50, 200, side[r.nextInt(4)], ID.EnemyBurst, handler));
				spawnTimer = 180;
			} // end if

			if (levelTimer == 0) {
				handler.clearEnemies();
				hud.setLevel(hud.getLevel() + 1);
				spawnTimer = 10;
				tempCounter = 0;
				if (levelsRemaining == 1) {
					levelNumber = 11;
				} // end if
				else {
					/*
					 * levels.remove(index); levelsRemaining--; index =
					 * r.nextInt(levelsRemaining); levelNumber =
					 * levels.get(index);
					 */
					list.getItem(5);
					levelNumber++;
				} // end else
			} // end if
		} // end else if
		else if (levelNumber == 6 && list.getItem(5) == 6) {
			spawnTimer--;
			levelTimer--;
			handler.addObject(new LevelText(15, 600, "This is Level 6", ID.Levels1to10Text));
			if (tempCounter < 1) {
				levelTimer = 1500;
				tempCounter++;
			} // end if
			if (spawnTimer == 0) {
				handler.addObject(
						new EnemyBasic(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 7, 7, ID.EnemyBasic, handler));
				spawnTimer = 50;
			} // end if
			if (levelTimer == 0) {
				handler.clearEnemies();
				hud.setLevel(hud.getLevel() + 1);
				spawnTimer = 40;
				tempCounter = 0;
				if (levelsRemaining == 1) {
					levelNumber = 11;
				} // end if
				else {
					/*
					 * levels.remove(index); levelsRemaining--; index =
					 * r.nextInt(levelsRemaining); levelNumber =
					 * levels.get(index);
					 */
					list.getItem(6);
					levelNumber++;
				} // end else
			} // end if
		} // end else if
		else if (levelNumber == 7 && list.getItem(6) == 7) {
			spawnTimer--;
			levelTimer--;
			handler.addObject(new LevelText(15, 600, "This is Level 7", ID.Levels1to10Text));
			if (tempCounter < 1) {
				levelTimer = 1200;
				tempCounter++;
			} // end if
			if (spawnTimer == 35) {
				handler.addObject(
						new EnemySweep(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 25, 2, ID.EnemySweep, handler));
			} // end if
			else if (spawnTimer == 25) {
				handler.addObject(
						new EnemySweep(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 25, -2, ID.EnemySweep, handler));
			} // end else if
			else if (spawnTimer == 15) {
				handler.addObject(
						new EnemySweep(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 25, 4, ID.EnemySweep, handler));
			} // end if
			else if (spawnTimer == 0) {
				handler.addObject(
						new EnemySweep(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 25, -4, ID.EnemySweep, handler));
				spawnTimer = 100;
			} // end else if

			if (levelTimer == 0) {
				handler.clearEnemies();
				hud.setLevel(hud.getLevel() + 1);
				tempCounter = 0;
				if (levelsRemaining == 1) {
					levelNumber = 11;
				} // end if
				else {
					/*
					 * levels.remove(index); levelsRemaining--; index =
					 * r.nextInt(levelsRemaining); levelNumber =
					 * levels.get(index);
					 */
					list.getItem(7);
					levelNumber++;
				} // end else
			} // end if
		} // end else if
		else if (levelNumber == 8 && list.getItem(7) == 8) {
			spawnTimer--;
			levelTimer--;
			handler.addObject(new LevelText(15, 600, "This is Level 8", ID.Levels1to10Text));
			if (tempCounter < 1) {
				levelTimer = 1000;
				tempCounter++;
			} // end if
			if (spawnTimer == 0) {
				handler.addObject(
						new EnemySmart(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), -3, ID.EnemySmart, handler));
				spawnTimer = 50;
			} // end if
			if (levelTimer == 0) {
				handler.clearEnemies();
				hud.setLevel(hud.getLevel() + 1);
				spawnTimer = 10;
				tempCounter = 0;
				if (levelsRemaining == 1) {
					levelNumber = 11;
				} // end if
				else {
					/*
					 * levels.remove(index); levelsRemaining--; index =
					 * r.nextInt(levelsRemaining); levelNumber =
					 * levels.get(index);
					 */
					list.getItem(8);
					levelNumber++;
				} // end else
			} // end if
		} // end else if
		else if (levelNumber == 9 && list.getItem(8) == 9) {
			levelTimer--;
			handler.addObject(new LevelText(15, 600, "This is Level 9", ID.Levels1to10Text));
			if (tempCounter < 1) {
				handler.addObject(new EnemyShooter(r.nextInt(Game.WIDTH) - 35, r.nextInt(Game.HEIGHT) - 75, 200, 200,
						-15, ID.EnemyShooter, this.handler));
				levelTimer = 2500;
				tempCounter++;
			} // end if

			if (levelTimer == 0) {
				handler.clearEnemies();
				hud.setLevel(hud.getLevel() + 1);
				spawnTimer = 10;
				tempCounter = 0;
				if (levelsRemaining == 1) {
					levelNumber = 11;
				} // end if
				else {
					/*
					 * levels.remove(index); levelsRemaining--; index =
					 * r.nextInt(levelsRemaining); levelNumber =
					 * levels.get(index);
					 */
					list.getItem(9);
					levelNumber++;
				} // end else
			} // end if
		} // end else if
		else if (levelNumber == 10 && list.getItem(9) == 10) {
			spawnTimer--;
			levelTimer--;
			handler.addObject(new LevelText(15, 600, "This is Level 10", ID.Levels1to10Text));
			if (tempCounter < 1) {
				levelTimer = 1400;
				tempCounter++;
			} // end if
			if (spawnTimer <= 0) {
				handler.addObject(new EnemyBurst(-200, 200, 40, 40, 200, side[r.nextInt(4)], ID.EnemyBurst, handler));
				spawnTimer = 90;
			} // end if

			if (levelTimer == 0) {
				handler.clearEnemies();
				hud.setLevel(hud.getLevel() + 1);
				spawnTimer = 10;
				tempCounter = 0;
				if (levelsRemaining == 1) {
					levelNumber = 11;
				} // end if
				else {
					/*
					 * levels.remove(index); levelsRemaining--; index =
					 * r.nextInt(levelsRemaining); levelNumber =
					 * levels.get(index);
					 */
					list.getItem(10);
					levelNumber++;
				} // end else
			} // end if
		} // end else if

		else if (levelNumber == 11) {// arbitrary
										// number for
										// the boss
			handler.addObject(new LevelText(15, 600, "This is Level 11", ID.Levels1to10Text));
			if (tempCounter < 1) {
				handler.addObject(new EnemyBoss(ID.EnemyBoss, handler));
				tempCounter++;
			} // end if
			else if (tempCounter >= 1) {
				for (int i = 0; i < handler.object.size(); i++) {
					GameObject tempObject = handler.object.get(i);
					if (tempObject.getId() == ID.EnemyBoss) {
						if (tempObject.getHealth() <= 0) {
							handler.removeObject(tempObject);
							LEVEL_SET++;
							game.gameState = STATE.Upgrade;
						} // end ifhandler.addObject(new LevelText(15, 600,
							// "This is Level ", ID.Levels1to10Text));
					} // end if
				} // end for
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
			// levels.remove(index);
			levelsRemaining--;
			// System.out.println(levelsRemaining);
			// tempCounter = 0;
			// index = r.nextInt(levelsRemaining);
			// levelNumber = levels.get(index);
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

}// end Spawn1to10
