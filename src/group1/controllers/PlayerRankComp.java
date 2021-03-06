//Defines comparator to rank players

package group1.controllers;

import group1.Player;

import java.util.Comparator;

//Player rank comparator
public class PlayerRankComp implements Comparator<Player> {

    public int compare(Player p1, Player p2) {
        if(p1.getPlayerScore() == p2.getPlayerScore()){
            return 0;
        } else {
            return -1;
        }
    }
}