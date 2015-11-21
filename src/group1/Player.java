/**
 * This class defines the player object, including a player's name, score, rank, and handicap
 **/

package group1;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

public class Player {

    private final String firstName; //contains only letters
    private final String lastName; //contains only letter
    private String team;
    private final int playerScore; //contains positive integer
    private final int playerRank; //contains number between 1-4
    //compares rank of two players
    public static Comparator<Player> playerRankComparator = (p1, p2) -> {
        int playerRank1 = p1.getPlayerRank();
        int playerRank2 = p2.getPlayerRank();

        return playerRank1 - playerRank2;
    };

    //compare the average score of two players
    public Comparator<Player> playerScoreComparator = (p1, p2) ->{
        int playerRank1 = p1.getPlayerAverage();
        int playerRank2 = p2.getPlayerAverage();

        return playerRank1 - playerRank2;
    };
    private final int handicap; //contains positive integer
    private final int timesPlayed;//contains positive integer
    /*should probably change to double*/
    private final int playerAverage;//contains positive integer
    private final Date joinedTeam;
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss z");

    //Constructor with parameters
    public Player(String firstName, String lastName, int playerScore, int playerRank,
                  int handicap, int timesPlayed, int playerAverage) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.playerScore = playerScore;
        this.playerRank = playerRank;
        this.handicap = handicap;
        this.timesPlayed = timesPlayed;
        this.playerAverage = getPlayerAverage();
        joinedTeam = Calendar.getInstance().getTime();
    }

    //Constructor for players to set all fields along with Team name
    public Player(String firstName, String lastName, int playerScore, int playerRank,
                  int handicap, int timesPlayed, int playerAverage, String team) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.playerScore = playerScore;
        this.playerRank = playerRank;
        this.handicap = handicap;
        this.timesPlayed = timesPlayed;
        this.team = team;
        this.playerAverage = getPlayerAverage();
        joinedTeam = Calendar.getInstance().getTime();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getTeam() {
        return team;
    }

    public String getLastName() {
        return lastName;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public int getPlayerRank() {
        return playerRank;
    }

    public int getHandicap() {
        return handicap;
    }

    public int getTimesPlayed() {
        return timesPlayed;
    }

    public int getPlayerAverage() {

        if(timesPlayed == 0) {
            return 0;
        }
        else if(timesPlayed >= 1){
            return playerScore/timesPlayed;
        }
        return 0;
    }

    public Date getJoinedTeam() {
        return joinedTeam;
    }

    @Override
    public String toString() {
        return "Player{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", playerScore=" + playerScore +
                ", playerRank=" + playerRank +
                ", handicap=" + handicap +
                ", timesPlayed=" + timesPlayed +
                ", playerAverage=" + playerAverage +
                ", joinedTeam=" + simpleDateFormat.format(joinedTeam) +
                '}';
    }
}
