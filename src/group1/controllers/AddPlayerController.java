package group1.controllers;

import group1.Player;
import group1.Team;
import group1.database_connectors.PushPlayerData;
import group1.database_connectors.getTeamData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

/**
 * Created by rnice01 on 11/11/2015.
 */
public class AddPlayerController implements Initializable{
@FXML
    TextField FirstNameField, LastNameField, TeamField,HandicapField;
@FXML
Button btnOK, btnCancel;
@FXML
    Label labelMaxPlayers;
Team team;
    ArrayList<Player> playerList = new ArrayList<>();
    public void addPlayerController(Team team) {
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

        btnOK.setOnAction((e)->{
            if(playerList.size() <= 4) {
                //Get the data from the fields and create a new player object
                int handi = Integer.parseInt(HandicapField.getText());
                Player player = new Player(FirstNameField.getText(), LastNameField.getText(), 0, 0, handi, 0, 0);
                System.out.println(player.getFirstName() + " " + player.getLastName() + " " + player.getHandicap());

                //Add the new player object to the arraylist for player objects
                playerList.add(player);

                FirstNameField.setText(null);
                LastNameField.setText(null);
                HandicapField.setText(null);
            }
            else{
                //If all 4 players have been added, remove the fields and push the data
                FirstNameField.setVisible(false);
                LastNameField.setVisible(false);
                HandicapField.setVisible(false);


                PushPlayerData push = new PushPlayerData();
                getTeamData pushTeam = new getTeamData();
                try {
                    //If the data has successfully been pushed to the database, set visibility of label
                    //to true to confirm
                    if(push.pushPlayerData(playerList) && pushTeam.pushTeamData(team) ){
                        labelMaxPlayers.setVisible(true);
                        labelMaxPlayers.setText("All players have been added successfully");
                    }
                    else{
                        labelMaxPlayers.setVisible(true);
                        labelMaxPlayers.setText("There was a networking error, please contact your administrator if problem persists");
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }

        });

        btnCancel.setOnAction((event) -> {
            if(playerList.size() < 4){
               PlayerWarningController warn = new PlayerWarningController();
                warn.addPlayerWarning();
                //Warn user that there are not enough players are entered for the team

            }
        });
    }



}

