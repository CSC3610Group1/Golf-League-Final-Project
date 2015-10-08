//This class defines a team object, which is made up of 4 players and also has its own properties

package group1;

import java.util.ArrayList;

public class Team {
	
	private String teamName;
	private int teamScore;
	private int teamRank;
	
	//Default constructor
	Team(){
		
	}
	
	//Constructor with parameters
	Team(String name, int score, int rank){
		teamName = name;
		teamScore = score;
		teamRank = rank;
	}
	
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public int getTeamScore() {
		return teamScore;
	}
	public void setTeamScore(int teamScore) {
		this.teamScore = teamScore;
	}
	public int getTeamRank() {
		return teamRank;
	}
	public void setTeamRank(int teamRank) {
		this.teamRank = teamRank;
	}

	@Override
	public String toString() {
		return "Team: " + teamName + "\n" +
				"Score: " + teamScore + "\n" +
				"Rank: " + teamRank;
	}
}
