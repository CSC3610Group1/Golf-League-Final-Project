package group1.database_connectors;
import group1.ExceptionHandler;
import group1.Player;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;

/**
 * Created by Rob on 10/7/2015.
 */
public class getPlayerData {



    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/golf_final";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";

    public static ArrayList<Player> getPlayers() {
        ArrayList<Player> playerList = new ArrayList<>();
        Player player;
        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT first_name, last_name, handicap, score, rank, times_played, average, team FROM players";
            ResultSet rs = stmt.executeQuery(sql);

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
            stmt.close();
            conn.close();


        } catch (SQLException se) {
            //Handle errors for JDBC
            ExceptionHandler.sqlException();
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try


        return playerList;
    }

    //Return only the player names by the team that is passed to the method
    public ArrayList<String> getPlayersByTeam(String team) {
        ArrayList<String> playerList = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT concat(first_name,' ', last_name) as name FROM players WHERE players.team = '" + team + "'";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                String playerName = rs.getString("name");

                playerList.add(playerName);

            }


            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();


        } catch (SQLException se) {
            //Handle errors for JDBC
            ExceptionHandler.sqlException();
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try

        return playerList;

    }

    public boolean UpdatePlayerScore(String value, int score) {

        return false;
    }
}//end FirstExample

