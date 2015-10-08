package group1;
import java.sql.*;
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

        public static void main(String[] args) {
            ArrayList<Player> playerList = new ArrayList<>();
            Player player;
            Connection conn = null;
            Statement stmt = null;
            try {
                //STEP 2: Register JDBC driver
                Class.forName("com.mysql.jdbc.Driver");

                //STEP 3: Open a connection
                System.out.println("Connecting to database...");
                conn = DriverManager.getConnection(DB_URL, USER, PASS);

                //STEP 4: Execute a query
                System.out.println("Creating statement...");
                stmt = conn.createStatement();
                String sql;
                sql = "SELECT first_name, last_name, handicap, score, rank, times_played, average FROM players";
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



                    //Create player object and add to player list
                    player = new Player(fName, lName, score, rank, handicap, timesPlayed, average);
                    playerList.add(player);

                }

                for(Player p: playerList){
                    System.out.println("Player name: " + p.getFirstName() + " " + " " + p.getLastName() + "\nHandicap: " + p.getHandicap());
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
            System.out.println("Goodbye!");

        }

    }//end FirstExample

