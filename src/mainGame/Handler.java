// package
package mainGame;

// imports
import java.awt.Graphics; 
import java.util.ArrayList;

/**
 * Class used for containing every instance of GameObject. These include all
 * enemies and players
 * 
 * @author Team A4 | Last Edit Date: Dec. 11, 2017
 */

// class
public class Handler {

	ArrayList<GameObject> object = new ArrayList<GameObject>();
	private int timer = 0;

	/**
	 * Updates each entity in the game by looping through each ArrayList and calling
	 * the tick() function on each object
	 */
	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			if (tempObject.getId() == ID.Player || tempObject.getId() == ID.Trail
					|| tempObject.getId() == ID.EnemyBurstWarning) {// we don't want these to ever be frozen by the
																	// Screen Freeze ability

				// Every GameObject has a tick method, so this effectively updates every single
				// object
				tempObject.tick();

			} else {
				timer--;
				if (timer <= 0) {// if Screen Freeze power-up is unlocked, enemy ID's will pause for the length
									// of the timer, and not update
					tempObject.tick();
				}
			}
		}

	}

	/**
	 * Redraws each entity in the game by looping through each ArrayList and calling
	 * the tick() function on each object
	 */
	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}

	}

	// pause
	public void pause() {
		timer = 1000;
	}

	// add object
	public void addObject(GameObject object) {
		this.object.add(object);
	}

	// remove object
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}

	/**
	 * Clears all entities that have an ID of some sort of enemy
	 */
	public void clearEnemies() {
		for (int i = 0; i < this.object.size(); i++) {
			GameObject tempObject = this.object.get(i);
			if (tempObject.getId() != ID.Player) {
				this.removeObject(tempObject);
				i--; // Removing shrinks the array by 1, causing the loop to skip an enemy
			}
		}
	}
	
	// clear text
	public void clearText() {
		// J's method that clears level transitions
		for (int i = 0; i < this.object.size(); i++) {
			GameObject tempObject = this.object.get(i);
			if (tempObject.getId() == ID.Levels1to10Text) {
				this.removeObject(tempObject);
				i--;
			}
		}
	}
	
	// clear pickup health
	public void clearPickupHealth() {
		for (int i = 0; i < this.object.size(); i++) {
			GameObject tempObject = this.object.get(i);
			if (tempObject.getId() == ID.PickupHealth) {
				this.removeObject(tempObject);
				i--; // Removing shrinks the array by 1, causing the loop to skip a player (should
						// there be more than one)
			}
		}
	}
	
	// clear pickup speed
	public void clearPickupSpeed() {
		for (int i = 0; i < this.object.size(); i++) {
			GameObject tempObject = this.object.get(i);
			if (tempObject.getId() == ID.PickupSpeed) {
				this.removeObject(tempObject);
				i--; // Removing shrinks the array by 1, causing the loop to skip a player (should
						// there be more than one)
			}
		}
	}

	/**
	 * Clears all entities that have an ID of player
	 */
	public void clearPlayer() {
		for (int i = 0; i < this.object.size(); i++) {
			GameObject tempObject = this.object.get(i);
			if (tempObject.getId() == ID.Player) {
				this.removeObject(tempObject);
				i--; // Removing shrinks the array by 1, causing the loop to skip a player (should
						// there be more than one)
			}
		}
	}
}
