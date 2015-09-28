//This class defines the player object, including a player's name, score, rank, and handicap

package group1;

public class Player {
	
	private String firstName;
	private String lastName;
	private int playerScore;
	private int playerRank;
	private int handicap;
	private int timesPlayed;
	private double playerAverage;
	
	//Default constructor
	Player(){
		
	}
	
	//Constructor with parameters
	Player(String fname, String lname, int score, int rank, int hcap, int times, double average){
		firstName = fname;
		lastName = lname;
		playerScore = score;
		playerRank = rank;
		handicap = hcap;
		timesPlayed = times;
		playerAverage = average;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getPlayerScore() {
		return playerScore;
	}
	public void setPlayerScore(int playerScore) {
		this.playerScore = playerScore;
	}
	public int getPlayerRank() {
		return playerRank;
	}
	public void setPlayerRank(int playerRank) {
		this.playerRank = playerRank;
	}
	public int getHandicap() {
		return handicap;
	}
	public void setHandicap(int handicap) {
		this.handicap = handicap;
	}
	public int getTimesPlayed() {
		return timesPlayed;
	}
	public void setTimesPlayed(int timesPlayed) {
		this.timesPlayed = timesPlayed;
	}
	public double getPlayerAverage() {
		return playerAverage;
	}
	public void setPlayerAverage(double playerAverage) {
		this.playerAverage = playerAverage;
	}
	
}
