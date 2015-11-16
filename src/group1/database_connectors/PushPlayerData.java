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

    public static ArrayList<Team> getTeams() {
        ArrayList<Team> teamList = new ArrayList<>();
        Team team;
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
            sql = "SELECT team_name, team_score FROM  teams";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                String name = rs.getString("team_name");
                int score = rs.getInt("team_score");




                //Create player object and add to player list
                team = new Team(name, score);
                teamList.add(team);

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
                ExceptionHandler.sqlException();
            }//end finally try
        }//end try


        return teamList;
    }
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
                        "(player_first_name, player_last_name, player_handicap, player_score, player_average, times_played) " +
                        "VALUES(?, ?, ?, 0, 0, 0)";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);

                preparedStatement.setString(1,p.getFirstName() );
                preparedStatement.setString(2, p.getLastName());
                preparedStatement.setInt(3, p.getHandicap());


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

    public boolean UpdatePlayerScore(String value, int score) {
        Connection conn = null;
        Statement stmt = null;

        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            stmt = conn.createStatement();
            String sql = "UPDATE players " +
                    "SET player_score = (player_score + " + score + ") WHERE player";
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
