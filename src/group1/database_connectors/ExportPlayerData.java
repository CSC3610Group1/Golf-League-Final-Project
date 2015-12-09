package group1.database_connectors;

import group1.Player;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by rnice01 on 12/3/2015.
 * This class holds a method to export some data stored in a local database by connecting with a JDBC driver connection
 * and creating an ArrayList of Player objects, the getters are used on the player objects to append information
 * about them to a CSV file
 */
public class ExportPlayerData {

    //Method returns a boolean value for calling button action to
    //handle feedback to user
    public Boolean generateCSV(){
        Boolean result = null;
        ArrayList<Player> playerList = new ArrayList<>();

        //Use a database connecter already written to get an ArrayList of player objects
        getPlayerData getData = new getPlayerData();

        //Add all of the player objects to new list
        playerList.addAll(getData.getPlayers());

        try{
            //Create a FileWriter object with a static filename
            FileWriter writer = new FileWriter("PlayerData.csv");

            //Append the headers for the CSV file
            writer.append("Player Name");
            writer.append(", ");
            writer.append("Team");
            writer.append("\n");

            //Loop through the ArrayList of player objects and only append their names and teams they play on
            for(Player player: playerList){
                writer.append(player.getFirstName() + " " + player.getLastName());
                writer.append(", ");
                writer.append(player.getTeam());
                writer.append("\n");
            }
            //Close the writer to save the information
            writer.close();
            result = true;//Sets the returned result to true if there were no problems
        }
        catch(IOException ex){
            ex.printStackTrace();
            result = false;//Set the returned result to false if an exception was caught
        }
        return result;//Will return null if the file was not written properly and an exception was not caught
    }
}
