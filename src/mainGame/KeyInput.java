// package
package mainGame;

// imports
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import mainGame.Game.STATE;

/**
 * Handles key input from the user
 * 
 * @author @author Team A4 | Last Edit Date: Dec. 11, 2017
 *
 */

// class
public class KeyInput extends KeyAdapter {
	// instance variables
	private Handler handler;
	private boolean[] keyDown = new boolean[5];
	private int speed;
	private Game game;
	private HUD hud;
	private Player player;
	private Spawn1to10 spawner;
	private Upgrades upgrades;
	private String ability;
	// private UnorderedList list = new UnorderedList(10);

	// constructor
	// uses current handler created in Game as parameter
	public KeyInput(Handler handler, Game game, HUD hud, Player player, Spawn1to10 spawner, Upgrades upgrades) {
		this.handler = handler;
		this.speed = Player.playerSpeed;
		this.game = game;
		this.player = player;
		this.hud = hud;
		this.spawner = spawner;
		this.upgrades = upgrades;
		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
		keyDown[4] = false;

	}

	// instance methods
	// ket pressed
	public void keyPressed(KeyEvent e) {
		System.out.println("Event " + e.getKeyChar());
		int key = e.getKeyCode();
		this.speed = Player.playerSpeed;

		// finds what key strokes associate with Player
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			// using only if's allows multiple keys to be triggered at once
			if (tempObject.getId() == ID.Player) {// find the player object, as
													// he is the only one the
													// user can control
				// key events for player 1
				if (key == KeyEvent.VK_UP) {

					System.out.println("Called");

					tempObject.setVelY(-(this.speed));
					keyDown[0] = true;
				}
				if (key == KeyEvent.VK_LEFT) {
					tempObject.setVelX(-(this.speed));
					keyDown[1] = true;
				}
				if (key == KeyEvent.VK_DOWN) {
					tempObject.setVelY(this.speed);
					keyDown[2] = true;
				}
				if (key == KeyEvent.VK_RIGHT) {
					tempObject.setVelX(this.speed);
					keyDown[3] = true;
				}
				if (key == KeyEvent.VK_SPACE) { // comment the skip level out and reset health() back in
					upgrades.levelSkipAbility();
					// hud.resetHealth();
					// Spawn1to10.LEVEL_SET = 10;
					// handler.clearEnemies();
					// hud.setLevel(11);
					// Spawn1to10.LEVEL_SET = 11;
					// upgrades.levelSkipAbility();
					// hud.setLevel(11);
					;
				}
				if (key == KeyEvent.VK_ENTER) {
					ability = upgrades.getAbility();
					if (ability.equals("clearScreen")) {
						upgrades.clearScreenAbility();
					} else if (ability.equals("levelSkip")) {
						upgrades.levelSkipAbility();
					} else if (ability.equals("freezeTime")) {
						upgrades.freezeTimeAbility();
					}
				}
				if (key == KeyEvent.VK_P) {

					if (game.gameState == STATE.Game) { // GAME IS RUNNING
						game.gameState = STATE.Pause;
					} else { // GAME IS NOT RUNNING (so make it run)

						game.gameState = STATE.Game;
					}

					// if (key == KeyEvent.VK_ESCAPE) System.exit(1);
				}

			}

		}

	}

	// key released
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.Player) {
				// key events for player 1
				if (key == KeyEvent.VK_UP)
					keyDown[0] = false;// tempObject.setVelY(0);
				if (key == KeyEvent.VK_LEFT)
					keyDown[1] = false;// tempObject.setVelX(0);
				if (key == KeyEvent.VK_DOWN)
					keyDown[2] = false;// tempObject.setVelY(0);
				if (key == KeyEvent.VK_RIGHT) {
					keyDown[3] = false;// tempObject.setVelX(0);
					keyDown[4] = false;
				}

				// vertical movement
				if (!keyDown[0] && !keyDown[2])
					tempObject.setVelY(0);
				// horizontal movement
				if (!keyDown[1] && !keyDown[3])
					tempObject.setVelX(0);
			}

		}

		// if (key == KeyEvent.VK_ESCAPE) System.exit(1);
	}

}

