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
    private final int playerScore; //contains positive integer
    private final int playerRank; //contains number between 1-4
    //compares rank of two players
    public static Comparator<Player> playerRankComparator = (p1, p2) -> {
        int playerRank1 = p1.getPlayerRank();
        int playerRank2 = p2.getPlayerRank();

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
        this.playerAverage = playerScore/timesPlayed;
        joinedTeam = Calendar.getInstance().getTime();
    }

    public String getFirstName() {
        return firstName;
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
        return playerAverage;
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
