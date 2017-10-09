package mainGame;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * Loads the window of the game, and sets the proper dimensions
 * 
 * @author Brandon Loehlee 5/30/16
 */

public class Window extends Canvas {

	private static final long serialVersionUID = 1L;

	public Window(int width, int height, String title, Game game) {
		JFrame frame = new JFrame(title);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		// EVAN CHANGES: will take frame to maximize the x and y so that game is
		// full screeen not matter the device
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.add(game);
		frame.setVisible(true);
		game.start();
	}

}
