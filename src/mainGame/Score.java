// package
package mainGame;

/**
 * @author Team A4 | Last Edit Date: Dec. 11, 2017
 *
 */

//import
import java.io.Serializable;

// class
public class Score implements Serializable {

	// instance variables
	private int score;
	private String naam;

	// get score
	public int getScore() {
		return score;
	}

	// get Naam
	public String getNaam() {
		return naam;
	}

	// constructor
	public Score(String naam, int score) {
		this.score = score;
		this.naam = naam;
	}
}