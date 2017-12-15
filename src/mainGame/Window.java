//package
package mainGame;

// imports
import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * Loads the window of the game, and sets the proper dimensions
 * 
 * @author Team A4 | Last Edit Date: Dec. 11, 2017
 */

// class
public class Window extends Canvas {

	// instance variable
	private static final long serialVersionUID = 1L;

	// constructor
	// window regarding the frame, sizing, etc.
	public Window(int width, int height, String title, Game game) {
		JFrame frame = new JFrame(title);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		// Important change: will take frame to maximize the x and y so that game is
		// full screen no matter the device
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.add(game);
		frame.setVisible(true);
		game.start();
	}

}
