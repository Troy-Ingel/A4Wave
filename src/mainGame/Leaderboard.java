package mainGame;

public class Leaderboard {
	String playerName;
	int playerscore;
	int[] allScores = new int[5];
	String[] allPlayers = new String[5];

	public Leaderboard(String name, int score) {
		playerName = name;
		playerscore = score;
		System.out.println("Leaderboard loaded");

	}

	public void Display() {

	}

	public int getScore(int index) {
		return allScores[index];
	}

	public void setScore(int index, int passedScore) {
		allScores[index] = passedScore;
	}

	public String getPlayerName(int index) {
		return allPlayers[index];
	}

	public void setPlayerName(int index, String playerName) {
		allPlayers[index] = playerName;
	}
}
