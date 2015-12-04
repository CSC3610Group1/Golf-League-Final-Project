//This class defines a team object, which is made up of 4 players and also has its own properties

package group1;


import java.util.Comparator;

public class Team {
	private final String teamName;
	private final int teamScore;
	private int teamRank;

	public static Comparator<Team> teamScoreComparator = (p1, p2) -> {
		int teamRank1 = p1.getTeamScore();
		int teamRank2 = p2.getTeamScore();

		return teamRank1 - teamRank2;
	};


	//Constructor with parameters
	public Team(String name, int score){
		this.teamName = name;
		this.teamScore = score;
	}

    public String getTeamName() {
        return teamName;
    }

    public int getTeamScore() {
        return teamScore;
    }

    @Override
	public String toString() {
		return "Team: " + teamName + "\n" +
				"Score: " + teamScore + "\n";
	}

	public void setRank(int rank) {
		this.teamRank = rank;
	}
}
