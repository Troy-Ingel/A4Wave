package mainGame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import mainGame.Game.STATE;

/**
 * Handles all mouse input
 * 
 * @author Brandon Loehle 5/30/16
 *
 */

public class MouseListener extends MouseAdapter {
	// HUD fixed

	private Game game;
	private Handler handler;
	private HUD hud;
	private Spawn1to10 spawner;
	private Spawn10to20 spawner2;
	private UpgradeScreen upgradeScreen;
	private Upgrades upgrades;
	private Player player;
	private String upgradeText;
	// public Leaderboard lb;

	public MouseListener(Game game, Handler handler, HUD hud, Spawn1to10 spawner, Spawn10to20 spawner2,
			UpgradeScreen upgradeScreen, Player player, Upgrades upgrades) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
		this.spawner = spawner;
		this.spawner2 = spawner2;
		this.upgradeScreen = upgradeScreen;
		this.player = player;
		this.upgrades = upgrades;
	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		if (game.gameState == STATE.GameOver) {
			handler.clearEnemies();
			handler.object.clear();
			// upgrades.resetUpgrades();
			// hud.health = 100;
			// hud.setScore(0);
			// hud.setLevel(1);
			// spawner.restart();
			// spawner.addLevels();
			// spawner2.restart();
			// spawner2.addLevels();
			// s Spawn1to10.LEVEL_SET = 1;
			HighscoreManager hm = new HighscoreManager();
			
			
			 JFrame frame = new JFrame("Enter Your Name");

			 // prompt the user to enter their name
			 String name = JOptionPane.showInputDialog(frame, "What's your name?");

			 // get the user's input. note that if they press Cancel, 'name' will be null
			 System.out.printf("The user's name is '%s'.\n", name);
			 hm.addScore(name, hud.getScore());
			System.out.println( " Name + " +  name + " hud = " + hud.getScore());
			
			game.gameState = STATE.Menu;
		}

		else if (game.gameState == STATE.Game) {

		}

		else if (game.gameState == STATE.Upgrade) {
			if (mouseOver(mx, my, Game.WIDTH / 2 - 375, Game.HEIGHT / 4, 750, 76)) {
				upgradeText = upgradeScreen.getPath(1);

				upgrades.activateUpgrade(upgradeText);

				upgradeScreen.removeUpgradeOption(1);

				game.gameState = STATE.Game;
			} else if (mouseOver(mx, my, Game.WIDTH / 2 - 375, Game.HEIGHT / 2, 750, 76)) {
				upgradeText = upgradeScreen.getPath(2);

				upgrades.activateUpgrade(upgradeText);

				upgradeScreen.removeUpgradeOption(2);

				game.gameState = STATE.Game;
			} else if (mouseOver(mx, my, Game.WIDTH / 2 - 375, 3 * Game.HEIGHT / 4, 750, 76)) {
				upgradeText = upgradeScreen.getPath(3);

				upgrades.activateUpgrade(upgradeText);

				upgradeScreen.removeUpgradeOption(3);

				game.gameState = STATE.Game;
			}

		}

		else if (game.gameState == STATE.Menu) {
			// Waves Button
			if (mouseOver(mx, my, 100, Game.HEIGHT / 6 - 65, Game.WIDTH - 200, 100)) {
				handler.object.clear();
				game.gameState = STATE.Game;
				handler.addObject(player);
				// handler.addPickup(new PickupHealth(100, 100, ID.PickupHealth,
				// "images/PickupHealth.png", handler));
			} else if (mouseOver(mx, my, Game.WIDTH / 2 + 25, Game.HEIGHT / 2 - 65, Game.WIDTH / 2 - 125, 100)) { // The
																								// leaderboard
																								// has
																								// been
																								// clicked
				System.out.println("Leaderboard Clicked?");
				game.gameState = STATE.Leaderboard;
				// lb.Display();

			}
			// Pick a Player! Button
			else if (mouseOver(mx, my, 100, Game.HEIGHT / 2 - 65, Game.WIDTH / 2 - 125, 100)) {
							game.gameState = STATE.PickPlayer;
							System.out.println("Pick a player");
			}

			// Help Button
			else if (mouseOver(mx, my, 100, Game.HEIGHT / 3 - 65, Game.WIDTH - 200, 100)) {
				game.gameState = STATE.Help;
			}

			// Credits
			else if (mouseOver(mx, my, 100, 2 * Game.HEIGHT / 3 - 65, Game.WIDTH - 200, 100)) {
				JOptionPane.showMessageDialog(game,
						"Made by Brandon Loehle for his "
								+ "final project in AP Computer Science senior year, 2015 - 2016."
								+ "\n\nThis game is grossly unfinished with minor bugs. However,"
								+ " it is 100% playable, enjoy!");
			}

			// Quit Button
			else if (mouseOver(mx, my, 100, 5 * Game.HEIGHT / 6 - 65, Game.WIDTH - 200, 100)) {
				System.exit(1);
			}
		}

		// Back Button for Help screen
		else if (game.gameState == STATE.Help) {
			if (mouseOver(mx, my, Game.WIDTH / 2 - 50, 315, 100, 50)) {
				game.gameState = STATE.Menu;
				return;
			}
		}
		
		// Back Button for Pick a Player! screen
				else if (game.gameState == STATE.PickPlayer) {
					if (mouseOver(mx, my, Game.WIDTH/5 - 100, Game.HEIGHT / 2 - 200, 200, 280)) {
						player.setCharacter(1);
						handler.object.clear();
						game.gameState = STATE.Game;
						handler.addObject(player);
						return;
					}
					if (mouseOver(mx, my, 2*Game.WIDTH/5 - 100, Game.HEIGHT / 2 - 200, 200, 280)) {
						player.setCharacter(2);
						handler.object.clear();
						game.gameState = STATE.Game;
						handler.addObject(player);
						return;
					}
					if (mouseOver(mx, my, 3*Game.WIDTH/5 - 100, Game.HEIGHT / 2 - 200, 200, 280)) {
						player.setCharacter(3);
						handler.object.clear();
						game.gameState = STATE.Game;
						handler.addObject(player);
						return;
					}
					if (mouseOver(mx, my, 4*Game.WIDTH/5 - 100, Game.HEIGHT / 2 - 200, 200, 280)) {
						player.setCharacter(4);
						handler.object.clear();
						game.gameState = STATE.Game;
						handler.addObject(player);
						return;
					}
					if (mouseOver(mx, my, Game.WIDTH / 2 - 50, 3 * Game.HEIGHT / 4 + 25, 100, 50)) {
						game.gameState = STATE.Menu;
						return;
					}
				}
		
		// Back Button for Leaderboard screen
				else if (game.gameState == STATE.Leaderboard) {
					if (mouseOver(mx, my, Game.WIDTH / 2 - 50, 3 * Game.HEIGHT / 4 + 25, 100, 50)) {
						game.gameState = STATE.Menu;
						return;
					}
				}
	}

	public void mouseReleased(MouseEvent e) {

	}

	/**
	 * Helper method to detect is the mouse is over a "button" drawn via
	 * Graphics
	 * 
	 * @param mx
	 *            mouse x position
	 * @param my
	 *            mouse y position
	 * @param x
	 *            button x position
	 * @param y
	 *            button y position
	 * @param width
	 *            button width
	 * @param height
	 *            button height
	 * @return boolean, true if the mouse is contained within the button
	 */
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			} else
				return false;
		} else
			return false;
	}
}
