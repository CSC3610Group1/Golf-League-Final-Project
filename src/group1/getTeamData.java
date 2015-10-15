package group1;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by rnice01 on 10/15/2015.
 */
public class getTeamData {


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


        return teamList;
    }

    public static void pushTeamData(Team team) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            stmt = conn.createStatement();

            String sql = "INSERT INTO teams " +
                    "(team_name, team_score) " +
                    "VALUES(?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, team.getTeamName());
            preparedStatement.setInt(2, team.getTeamScore());


// execute insert SQL stetement
            preparedStatement.executeUpdate();


        } catch (SQLException se) {
            //Handle errors for JDBC
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
            }//end finally try
        }//end try
    }
}
