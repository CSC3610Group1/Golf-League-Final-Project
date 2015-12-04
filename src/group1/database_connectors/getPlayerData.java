package group1.database_connectors;
import group1.ExceptionHandler;
import group1.Player;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Rob on 10/7/2015.
 */
public class getPlayerData {

    DatabaseConnector connector;

    public ArrayList<Player> getPlayers() {
        ArrayList<Player> playerList = new ArrayList<>();
        Player player;
        connector = new DatabaseConnector();

        try {
        connector.MakeConnection();
            String sql;
            sql = "SELECT first_name, last_name, handicap, score, rank, times_played, average, team FROM players";
            ResultSet rs = connector.returnStatement().executeQuery(sql);

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                String fName = rs.getString("first_name");
                String lName = rs.getString("last_name");
                int handicap = rs.getInt("handicap");
                int score = rs.getInt("score");
                int rank = rs.getInt("rank");
                int timesPlayed = rs.getInt("times_played");
                int average = rs.getInt("average");
                String team = rs.getString("team");



                //Create player object and add to player list
                player = new Player(fName, lName, score, rank, handicap, timesPlayed, average, team);
                playerList.add(player);

            }


            //STEP 6: Clean-up environment
            rs.close();



        } catch (SQLException se) {
            //Handle errors for JDBC
            ExceptionHandler.sqlException();
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        finally{
            connector.CloseConnection();
        }


        return playerList;
    }

    //Return only the player names by the team that is passed to the method
    public ArrayList<String> getPlayersByTeam(String team) {
        ArrayList<String> playerList = new ArrayList<>();
        connector = new DatabaseConnector();

        try {
         connector.MakeConnection();
            String sql;
            sql = "SELECT concat(first_name,' ', last_name) as name FROM players WHERE players.team = '" + team + "'";
            ResultSet rs = connector.returnStatement().executeQuery(sql);

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                String playerName = rs.getString("name");

                playerList.add(playerName);

            }


            //STEP 6: Clean-up environment
            rs.close();


        } catch (SQLException se) {
            //Handle errors for JDBC
            ExceptionHandler.sqlException();
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {

            connector.CloseConnection();
        }//end try

        return playerList;

    }

    public boolean UpdatePlayerScore(String value, int score) {

        return false;
    }
}//end FirstExample

