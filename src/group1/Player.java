/**This class defines the player object, including a player's name, score, rank, and handicap
 * Uses internal builder class to create object
**/

package group1;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

public class Player {

    private String firstName;
    private String lastName;
    private int playerScore;
    private int playerRank;
    private int handicap;
    private int timesPlayed;
    private int playerAverage;
    private Date joinedTeam;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss z");

    //Constructor with parameters
    private Player(final String firstName, final String lastName, final int playerScore
            , final int playerRank, final int handicap, final int timesPlayed, final int playerAverage) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.playerScore = playerScore;
        this.playerRank = playerRank;
        this.handicap = handicap;
        this.timesPlayed = timesPlayed;
        this.playerAverage = playerAverage;
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

    //compares rank of two players
    public static Comparator<Player> PlayerRankComparator = new Comparator<Player>() {
        public int compare(Player p1, Player p2) {
            int playerRank1 = p1.getPlayerRank();
            int playerRank2 = p2.getPlayerRank();

            return playerRank1 - playerRank2;
        }
    };
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

    //builds a player object
    public static final class PlayerBuilder {
        private String firstName;
        private String lastName;
        private int playerScore;
        private int playerRank;
        private int handicap;
        private int timesPlayed;
        private int playerAverage;

        public PlayerBuilder withFirstName(final String firstName) {
            this.firstName = firstName;
            return this;

        }

        public PlayerBuilder withLastName(final String lastName) {
            this.lastName = lastName;
            return this;
        }

        public PlayerBuilder withPlayerScore(final int playerScore) {
            this.playerScore = playerScore;
            return this;
        }

        public PlayerBuilder withPlayerRank(final int playerRank) {
            this.playerRank = playerRank;
            return this;
        }

        public PlayerBuilder withHandicap(final int handicap) {
            this.handicap = handicap;
            return this;
        }

        public PlayerBuilder withTimesPlayed(final int timesPlayed) {
            this.timesPlayed = timesPlayed;
            return this;
        }

        public PlayerBuilder withPlayerAverage(final int playerAverage) {
            this.playerAverage = playerAverage;
            return this;
        }

        public Player buildPlayer() {
            return new Player(firstName, lastName, playerScore, playerRank, handicap,
                    timesPlayed, playerAverage);
        }
    }
}
