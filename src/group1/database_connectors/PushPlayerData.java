package group1.database_connectors;

import group1.ExceptionHandler;
import group1.Player;
import group1.Team;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by rnice01 on 11/11/2015.
 */
public class PushPlayerData {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/golf_final";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";


    //Get the arraylist of players and push the first name, last name, and handicap to the database
    //The other fields are defaulted to 0 since these will be new players to the team
    public static boolean pushPlayerData(ArrayList<Player> player) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            stmt = conn.createStatement();
            for(Player p: player) {
                String sql = "INSERT INTO players " +
                        "(first_name, last_name, handicap, score, average, times_played, team) " +
                        "VALUES(?, ?, ?, 0, 0, 0, ?)";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);

                preparedStatement.setString(1,p.getFirstName() );
                preparedStatement.setString(2, p.getLastName());
                preparedStatement.setInt(3, p.getHandicap());
                preparedStatement.setString(4, p.getTeam());


                // execute insert SQL stetement
                preparedStatement.executeUpdate();
            }
            return true;

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
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
                ExceptionHandler.sqlException();
            }//end finally try
        }//end try
        return false;
    }

    //Updates player's score and times played in database by their name and team
    public boolean UpdatePlayerScore(String playerName, String teamName, int score) {
        Connection conn = null;
        Statement stmt = null;

            //playerName string needs to be unconcatenated
            int position = playerName.indexOf(" ") + 1;
            int end = playerName.length();
            String firstName = playerName.substring(0, position);
            String lastName = playerName.substring(position,end);
            //Get rid of any white spaces
            firstName.trim();
            lastName.trim();
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            stmt = conn.createStatement();
            //Update the score and times played
            String sql = "UPDATE players " +
                    "SET score = (score + " + score + "), times_played = (times_played + 1) WHERE team = '"+
                    teamName + "' AND first_name = '" + firstName + "' AND last_name = '" + lastName + "'";
            stmt.executeUpdate(sql);
            return true;

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
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
                ExceptionHandler.sqlException();
            }//end finally try
        }//end try
        return false;
    }
}
