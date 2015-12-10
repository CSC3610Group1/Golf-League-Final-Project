//Controller class for adding a player

package group1.controllers;

import group1.Player;
import group1.Team;
import group1.database_connectors.PushPlayerData;
import group1.database_connectors.getTeamData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddPlayerController implements Initializable{
@FXML
    TextField FirstNameField, LastNameField, TeamField,HandicapField;
@FXML
Button btnOK, btnCancel, btnClose;
@FXML
    Label labelMaxPlayers;
static Team team;
    ArrayList<Player> playerList = new ArrayList<>();

    //Method to start the stage and take the team name passed from the Add Team stage
    //Team is made static to allow access of object when pushing the players and team
    //to the database
    public void addPlayerController(Team team) {
        //Team object is set here
      this.team = team;
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("group1/fxml/add_teammate.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Enter Teammate");
            stage.setScene(new Scene(root, 600, 450));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {



    }

    //Closes the stage if cancel button is clicked
    //Check if a full team has been added before cancellation
    public void closeWindow(ActionEvent actionEvent) {
        if(playerList.size() < 3){
            PlayerWarningController warn = new PlayerWarningController();
            //Pop up window to warn users that team will not be added if
            //enough players have been added
            warn.addPlayerWarning();

        }else{
            Node source = (Node)  actionEvent.getSource();
            Stage stage  = (Stage) source.getScene().getWindow();
            stage.close();
        }

    }


    public void addPlayer(ActionEvent actionEvent) {
        if(playerList.size() == 3) {
            //Create and add the last player object to the object arraylist before pushing the data to DB
            int handi = Integer.parseInt(HandicapField.getText());
            Player player = new Player(FirstNameField.getText(), LastNameField.getText(), 0, 0, handi, 0, 0,team.getTeamName());
            playerList.add(player);
            //If all 4 players have been added, clear the fields and push the data
            FirstNameField.setVisible(false);
            LastNameField.setVisible(false);
            HandicapField.setVisible(false);

            //Creating instances of database connecting classes to access their methods
            PushPlayerData push = new PushPlayerData();
            getTeamData pushTeam = new getTeamData();
            try {
                //If both JDBC methods return true
                //Show label to confirm with user
                if(push.pushPlayerData(playerList) && pushTeam.pushTeamData(team) ){
                    labelMaxPlayers.setVisible(true);
                    labelMaxPlayers.setText("All players and team have been added successfully");

                    //Change available buttons to user once update is successful
                    btnCancel.setVisible(false);
                    btnOK.setVisible(false);
                    btnClose.setVisible(true);
                }
                else{
                    labelMaxPlayers.setVisible(true);
                    labelMaxPlayers.setText("There was a networking error, please contact your administrator if problem persists");
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            catch(ClassNotFoundException ex2){
                ex2.printStackTrace();
            }
        }
        else{
            //Get the data from the fields and create a new player object
            int handi = Integer.parseInt(HandicapField.getText());
            Player player = new Player(FirstNameField.getText(), LastNameField.getText(), 0, 0, handi, 0, 0,team.getTeamName());
            System.out.println(player.getFirstName() + " " + player.getLastName() + " " + player.getHandicap());
            System.out.println(playerList.size());
            //Add the new player object to the arraylist for player objects
            playerList.add(player);

            FirstNameField.setText(null);
            LastNameField.setText(null);
            HandicapField.setText(null);

        }

    }
}

