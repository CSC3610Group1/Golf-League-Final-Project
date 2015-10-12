//This class defines a team object, which is made up of 4 players and also has its own properties

package group1;

import java.util.ArrayList;

public class Team {
	private final String teamName;
	private final int teamScore;
	private final int teamRank;//positive int

	//Constructor with parameters
	public Team(String name, int score, int rank){
		this.teamName = name;
		this.teamScore = score;
		this.teamRank = rank;
	}

    public String getTeamName() {
        return teamName;
    }

    public int getTeamScore() {
        return teamScore;
    }

    public int getTeamRank() {
        return teamRank;
    }

    @Override
	public String toString() {
		return "Team: " + teamName + "\n" +
				"Score: " + teamScore + "\n" +
				"Rank: " + teamRank;
	}
}
