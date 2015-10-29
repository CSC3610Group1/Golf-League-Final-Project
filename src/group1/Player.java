//This class defines the player object, including a player's name, score, rank, and handicap

package group1;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Player implements Comparable<Player> {

    private String firstName;
    private String lastName;
    private static Comparator<Player> playerLastNameComparator =
            (t1, t2) -> t1.getLastName().compareTo(t2.getLastName());
    private int playerScore;
    private int playerRank;
    private static Comparator<Player> playerRankComparator =
            (t1, t2) -> Integer.compare(t1.getPlayerRank(), t2.getPlayerRank());
    public static Comparator<Player> PlayerRankComparator = (p1, p2) -> {
        int playerRank1 = p1.getPlayerRank();
        int playerRank2 = p2.getPlayerRank();

        return playerRank1 - playerRank2;
    };
    private int handicap;
    private int timesPlayed;
    private int playerAverage;
    private Date joinedTeam;

    //Default constructor
    Player() {

    }

    //Constructor with parameters
    Player(String fname, String lname, int score, int rank, int hcap, int times, int average) {
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

    public void setPlayerAverage(int playerAverage) {
        this.playerAverage = playerAverage;
    }

    public Date getJoinedTeam() {
        return joinedTeam;
    }

    public void setJoinedTeam(Date date) {
        this.joinedTeam = date;
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
                ", joinedTeam=" + joinedTeam +
                '}';
    }

    @Override
    public int compareTo(Player o) {
        if (this.lastName.compareTo(o.getLastName()) == 0)
            return 0;
        if (this.lastName.compareTo(o.getLastName()) < 0)
            return -1;
        if (this.lastName.compareTo(o.getLastName()) > 0)
            return 1;
        return 0;
    }

    public static List<Player> sortByScore(List<Player> players) {
        return Mergesort.mergeSort(players, playerRankComparator);
    }

    public static List<Player> sortByLastName(List<Player> players) {
        return Mergesort.mergeSort(players, playerLastNameComparator);
    }
}
