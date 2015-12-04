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
    DatabaseConnector connector;


    //Get the arraylist of players and push the first name, last name, and handicap to the database
    //The other fields are defaulted to 0 since these will be new players to the team
    public  boolean pushPlayerData(ArrayList<Player> player) throws SQLException, ClassNotFoundException {
    connector = new DatabaseConnector();

     try {
         connector.MakeConnection();

         for (Player p : player) {
             String sql = "INSERT INTO players " +
                     "(first_name, last_name, handicap, score, average, times_played, team) " +
                     "VALUES(?, ?, ?, 0, 0, 0, ?)";
             PreparedStatement preparedStatement = connector.returnConnection().prepareStatement(sql);

             preparedStatement.setString(1, p.getFirstName());
             preparedStatement.setString(2, p.getLastName());
             preparedStatement.setInt(3, p.getHandicap());
             preparedStatement.setString(4, p.getTeam());


             // execute insert SQL stetement
             preparedStatement.executeUpdate();
         }
         connector.CloseConnection();
         return true;


     }
     catch(Exception ex){
         connector.CloseConnection();
         return false;
     }

    }

    //Updates player's score and times played in database by their name and team
    public boolean UpdatePlayerScore(String playerName, String teamName, int score) {
        connector = new DatabaseConnector();

            //playerName string needs to be unconcatenated
            int position = playerName.indexOf(" ") + 1;
            int end = playerName.length();
            String firstName = playerName.substring(0, position);
            String lastName = playerName.substring(position,end);
            //Get rid of any white spaces
            firstName.trim();
            lastName.trim();
        try {
           connector.MakeConnection();
            //Update the score and times played
            String sql = "UPDATE players " +
                    "SET score = (score + " + score + "), times_played = (times_played + 1) WHERE team = '"+
                    teamName + "' AND first_name = '" + firstName + "' AND last_name = '" + lastName + "'";
            connector.returnStatement().executeUpdate(sql);
            return true;

        } catch (SQLException se) {
            connector.CloseConnection();
            //Handle errors for JDBC
            ExceptionHandler.sqlException();
            se.printStackTrace();
            return false;
        }

        catch(ClassNotFoundException ex2){
            connector.CloseConnection();
            return false;
        }

    }

    //Updates team's score by team name
    public boolean UpdateTeamScore(String teamName, int score) {
        connector = new DatabaseConnector();


        try {
            connector.MakeConnection();
            //Update the score and times played
            String sql = "UPDATE teams " +
                    "SET team_score = (team_score + " + score + ") WHERE team_name = '"+
                    teamName + "'";
            connector.returnStatement().executeUpdate(sql);
            return true;

        } catch (SQLException se) {
            connector.CloseConnection();
            //Handle errors for JDBC
            ExceptionHandler.sqlException();
            se.printStackTrace();
            return false;
        }

        catch(ClassNotFoundException ex2){
            connector.CloseConnection();
            return false;
        }

    }

    //Method for editing the players on selected team, score and times played are reset as these are new players for the team
    public boolean UpdatePlayer(String oldPLayer, String team, String newPlayerFirstName, String newPlayerLastName) {
        connector = new DatabaseConnector();
        //old player name string needs to be unconcatenated
        int position = oldPLayer.indexOf(" ") + 1;
        int end = oldPLayer.length();
        String firstName = oldPLayer.substring(0, position);
        String lastName = oldPLayer.substring(position,end);
        //Get rid of any white spaces
        firstName.trim();
        lastName.trim();

        try {
            connector.MakeConnection();
            //New player to the team gets a score and times played to 0
            String sql = "UPDATE players " +
                    "SET score = 0, times_played = 0, first_name = '" + newPlayerFirstName + "', last_name = '" + newPlayerLastName +
                    "' WHERE team = '"+ team + "' AND first_name = '" + firstName + "' AND last_name = '" + lastName + "'";
            connector.returnStatement().executeUpdate(sql);
            return true;

        }
        catch(SQLException ex){

        }
        catch(ClassNotFoundException ex2){

        }
        finally{
            connector.CloseConnection();
        }

        return false;
    }
}
