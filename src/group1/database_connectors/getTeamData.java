package group1.database_connectors;

import group1.ExceptionHandler;
import group1.Team;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by rnice01 on 10/15/2015.
 */
public class getTeamData {
    DatabaseConnector connector;

    public ArrayList<Team> getTeams() {
        ArrayList<Team> teamList = new ArrayList<>();
        Team team;
        connector = new DatabaseConnector();
        try {
           connector.MakeConnection();

            String sql;
            sql = "SELECT team_name, team_score FROM  teams";
            ResultSet rs = connector.returnStatement().executeQuery(sql);

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


        return teamList;
    }

    public boolean pushTeamData(Team team) throws SQLException {
       connector = new DatabaseConnector();

        try {
          connector.MakeConnection();

            String sql = "INSERT INTO teams " +
                    "(team_name, team_score) " +
                    "VALUES(?, ?)";
            PreparedStatement preparedStatement = connector.returnConnection().prepareStatement(sql);

            preparedStatement.setString(1, team.getTeamName());
           preparedStatement.setInt(2, team.getTeamScore());


// execute insert SQL stetement
            preparedStatement.executeUpdate();
            return true;

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
        return false;
    }
}
