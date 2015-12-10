/**
 * This class will used methods to sort the players and teams by score and return their rankings
 */

package group1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class setRanks {

    public static Comparator<Player> playerScoreComparator = (p1, p2) -> {
        int playerRank1 = p1.getPlayerScore();
        int playerRank2 = p2.getPlayerScore();

        return playerRank1 - playerRank2;
    };

    public ArrayList<Player> sortPlayers(ArrayList<Player> players){


        Collections.sort(players, playerScoreComparator);
        return players;
    }

    public ArrayList<Team> sortTeams(ArrayList<Team> teams){


        Collections.sort(teams, Team.teamScoreComparator);
        return teams;
    }

}
