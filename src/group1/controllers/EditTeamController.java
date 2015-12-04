package group1.controllers;


import group1.Team;
import group1.database_connectors.PushPlayerData;
import group1.database_connectors.getPlayerData;
import group1.database_connectors.getTeamData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


/**
 * Created by rnice01 on 11/13/2015.
 */
public class EditTeamController implements Initializable {
    getPlayerData players;
    @FXML
    TextField fieldFirstName, fieldLastName;
    @FXML
    Label labelTeamWarning, labelPlayerWarning, labelFirstNameWarning, labelLastNameWarning, labelUpdateSuccess;
    @FXML
    ComboBox<String> comboTeam, comboPlayer;

    public void submitScore(ActionEvent actionEvent) {
        PushPlayerData playerData = new PushPlayerData();

        //Check that fields are set before updating the edited player
        if(comboTeam.getValue()==null){
            labelPlayerWarning.setVisible(false);
            labelFirstNameWarning.setVisible(false);
            labelLastNameWarning.setVisible(false);
            labelTeamWarning.setVisible(true);
        }
        else if(comboPlayer.getValue() == null){
            labelPlayerWarning.setVisible(true);
            labelFirstNameWarning.setVisible(false);
            labelLastNameWarning.setVisible(false);
            labelTeamWarning.setVisible(false);
        }
        else if(fieldFirstName.getText().isEmpty()){
            labelPlayerWarning.setVisible(false);
            labelFirstNameWarning.setVisible(true);
            labelLastNameWarning.setVisible(false);
            labelTeamWarning.setVisible(false);
        }
        else if(fieldLastName.getText().isEmpty()){
            labelPlayerWarning.setVisible(false);
            labelFirstNameWarning.setVisible(false);
            labelLastNameWarning.setVisible(true);
            labelTeamWarning.setVisible(false);
        }
        else{//If all the fields are filled in correctly, push the data to the database, method returns true if update runs successfully


            //
            if(playerData.UpdatePlayer(comboPlayer.getValue(), comboTeam.getValue(), fieldFirstName.getText(), fieldLastName.getText())){
                labelUpdateSuccess.setVisible(true);
                fieldFirstName.setText(null);
                fieldLastName.setText(null);

            }
            else{//If the updatescore method returns false, inform the user
                labelUpdateSuccess.setVisible(true);
                labelUpdateSuccess.setText("Unable to update team with new player, please check your network connection");
                fieldFirstName.setText(null);
                fieldLastName.setText(null);
            }
        }

    }

    //
    //handler to close the window
    public void closeWindow(ActionEvent actionEvent) {
        ControlsDemoMain main = new ControlsDemoMain();
        main.newLayout("group1/fxml/welcome.fxml");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //ArrayList for holding the team names from the database
        ArrayList<String> teamList = new ArrayList<String>();
        getTeamData getTeams = new getTeamData();

        for(Team t: getTeams.getTeams()){
            //Only add team names that are not null
            if(!t.getTeamName().equals("")) {
                teamList.add(t.getTeamName());
            }
    }
        //Add all the teams to the combobox
        ObservableList<String> comboTeamList = FXCollections.observableList(teamList);
        comboTeam.getItems().addAll(comboTeamList);


        //Handle combobox actions for selecting teams
        comboTeam.setOnAction((e)->{
            //Clear the player combobox when a team is selected to prevent from player combo
            //filling with the wrong players
            comboPlayer.getItems().clear();

            //When an item from this combo box is selected, get the team name and pass it to the
            //method to connect to the database and get the player names associated with the
            //team name
            ArrayList<String> playerList = new ArrayList<String>();
            players = new getPlayerData();
            playerList = players.getPlayersByTeam(comboTeam.getValue());



            ObservableList<String> comboPlayerList = FXCollections.observableList(playerList);
            comboPlayer.getItems().addAll(comboPlayerList);
        });


    }
}
