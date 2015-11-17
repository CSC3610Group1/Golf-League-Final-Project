package group1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;

/**
 * Created by reesemodloff on 11/16/15.
 */
public class BinarySearch {

    public static void main(String[] args){

        //Array of 4 players for a team
        Player[] players = new Player[4];

        //Creates an arrayList for a team to have 4 players put in
        List<Player> teamMembers = new ArrayList<Player>();

        //Sample team members
        players[0] = new Player("John", "Apple", 1, 3, 4, 5, 6);
        players[1] = new Player("Keith", "Apple", 4, 6, 7, 1, 2);
        players[2] = new Player("James", "Apple", 8, 1, 2, 6, 7);
        players[3] = new Player("Hote", "Apple", 6, 2, 8, 4, 3);

        teamMembers.addAll(Arrays.asList(players));

        //Sample search key
        Player searchKey = new Player("Hote", "Apple", 6, 2, 8, 4, 3);

        //Sorts team members by rank
        Collections.sort(teamMembers, Player.playerRankComparator);

        //Prints out the rank of team member searched for
        System.out.println("The rank of the player you searched for is " + (Collections.binarySearch(teamMembers, searchKey, new PlayerRankComp()) + 1));

    }

    //Player rank comparator
    static class PlayerRankComp implements Comparator<Player>{

        public int compare(Player p1, Player p2) {
            if(p1.getPlayerRank() == p2.getPlayerRank()){
                return 0;
            } else {
                return -1;
            }
        }
    }

}
