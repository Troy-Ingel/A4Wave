// package
package mainGame;

// imports
import java.util.*;
import java.io.*;

// class
public class HighscoreManager {
	// An arraylist of the type "score" we will use to work with the scores
	// inside the class
	private ArrayList<Score> scores;

	// The name of the file where the highscores will be saved
	private static final String HIGHSCORE_FILE = "scores.dat";

	// Initialising an in and outputStream for working with the file
	ObjectOutputStream outputStream = null;
	ObjectInputStream inputStream = null;

	public HighscoreManager() {
		// initializing the scores-array list
		scores = new ArrayList<Score>();
	}

	// get scores
	public ArrayList<Score> getScores() {
		loadScoreFile();
		sort();
		return scores;
	}

	// sort scores
	private void sort() {
		ScoreComparator comparator = new ScoreComparator();
		Collections.sort(scores, comparator);
	}

	// to add score
	public void addScore(String name, int score) {
		loadScoreFile();
		scores.add(new Score(name, score));
		updateScoreFile();
	}

	// to load the score file
	public void loadScoreFile() {
		try {
			inputStream = new ObjectInputStream(new FileInputStream(HIGHSCORE_FILE));
			scores = (ArrayList<Score>) inputStream.readObject();
		} catch (FileNotFoundException e) {
			System.out.println("[Laad] FNF Error: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("[Laad] IO Error: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("[Laad] CNF Error: " + e.getMessage());
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException e) {
				System.out.println("[Laad] IO Error: " + e.getMessage());
			}
		}
	}

	// to update the score file
	public void updateScoreFile() {
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream(HIGHSCORE_FILE));
			outputStream.writeObject(scores);
		} catch (FileNotFoundException e) {
			System.out.println("[Update] FNF Error: " + e.getMessage() + ",the program will try and make a new file");
		} catch (IOException e) {
			System.out.println("[Update] IO Error: " + e.getMessage());
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException e) {
				System.out.println("[Update] Error: " + e.getMessage());
			}
		}
	}

	// get high score
	public String getHighscoreString(int index) {
		String highscoreString = "";
		int max = 5;

		ArrayList<Score> scores;
		scores = getScores();

		int x = scores.size();
		if (x > max) {
			x = max;
		}
		highscoreString += (index + 1) + ".\t" + scores.get(index).getNaam() + "\t\t" + scores.get(index).getScore()
				+ "\n";

		return highscoreString;
	}
}
