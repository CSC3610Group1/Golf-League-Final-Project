//This class defines a team object, which is made up of 4 players and also has its own properties

package group1;

import java.util.Comparator;
import java.util.List;
public class Team implements Comparable<Team>{
	
	private String teamName;
	private int teamScore;

    private final static Comparator<Team> teamRankComparator =
            (t1, t2) -> Integer.compare(t1.getTeamScore(), t2.getTeamScore());

    private final static Comparator<Team> teamNameComparator =
            (t1, t2) -> t1.getTeamName().compareTo(t2.getTeamName());

	//Default constructor
        Team(){
		
	}
	
	//Constructor with parameters
	Team(String name, int score){
		teamName = name;
		teamScore = score;
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

	@Override
	public String toString() {
		return "Team: " + teamName + " | " +
				"Score: " + teamScore + "\n";
	}

	@Override
	public int compareTo(Team o) {
        if (this.teamScore > o.getTeamScore())
            return 1;
        if (this.teamScore < o.getTeamScore())
            return -1;
        if (this.teamScore == o.getTeamScore())
            return 0;
		return 0;
	}

    public static List<Team> sortByScore(List<Team> teams) {
        return Mergesort.mergeSort(teams, teamRankComparator);
    }

   public static List<Team> sortByName(List<Team> teams) {
        return Mergesort.mergeSort(teams, teamNameComparator);
    }


}
