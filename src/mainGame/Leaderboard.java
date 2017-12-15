// code for the leaderboard of the game
// @author Team A4 | Last Edit Date: Dec. 11, 2017

// package
package mainGame;

// class
public class Leaderboard {

	// instance variables
	String playerName;
	int playerscore;
	int[] allScores = new int[5];
	String[] allPlayers = new String[5];

	// constructor
	public Leaderboard(String name, int score) {
		playerName = name;
		playerscore = score;
		System.out.println("Leaderboard loaded");

	}

	// instance methods

	// display
	public void Display() {

	}

	// to get score
	public int getScore(int index) {
		return allScores[index];
	}

	// to set score
	public void setScore(int index, int passedScore) {
		allScores[index] = passedScore;
	}

	// to get play name
	public String getPlayerName(int index) {
		return allPlayers[index];
	}

	// to set play name
	public void setPlayerName(int index, String playerName) {
		allPlayers[index] = playerName;
	}
}
