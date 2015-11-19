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
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


/**
 * Created by rnice01 on 11/13/2015.
 */
public class AddGameController implements Initializable {
    getPlayerData players;
    @FXML
    TextField fieldScore;
    @FXML
    Label labelTeamWarning, labelPlayerWarning, labelScoreWarning, labelUpdateSuccess;
    @FXML
    ComboBox<String> comboTeam, comboPlayer;
    Stage stage;
    //initialize method runs after stage is set

    public void StartScoreStage(){


        Parent root;
        try {


            root = FXMLLoader.load(getClass().getClassLoader().getResource("group1/fxml/add_game.fxml"));
            stage = new Stage();
            stage.setTitle("Enter scores for each player");
            stage.setScene(new Scene(root, 600, 450));

            stage.show();



        } catch (IOException ex) {
            ex.printStackTrace();
        }





    }

    public void submitScore(ActionEvent actionEvent) {
        PushPlayerData playerData = new PushPlayerData();

        //Check that fields are set before updating the player score
        if(comboTeam.getValue()==null){
            labelPlayerWarning.setVisible(false);
            labelScoreWarning.setVisible(false);
            labelTeamWarning.setVisible(true);
        }
        else if(comboPlayer.getValue() == null){
            labelPlayerWarning.setVisible(true);
            labelScoreWarning.setVisible(false);
            labelTeamWarning.setVisible(false);
        }
        else if(fieldScore.getText().isEmpty()){
            labelPlayerWarning.setVisible(false);
            labelScoreWarning.setVisible(true);
            labelTeamWarning.setVisible(false);
        }
        else{//If all the fields are filled in correctly, push the data to the database, method returns true if update runs successfully

            //parse the score value into an integer
            int score = Integer.parseInt(fieldScore.getText());
            //
            if(playerData.UpdatePlayerScore(comboPlayer.getValue(), comboTeam.getValue(),score )){
                labelUpdateSuccess.setVisible(true);
                fieldScore.setText(null);
            }
        }

    }

    public void closeWindow(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> teamList = new ArrayList<String>();
        getTeamData getTeams = new getTeamData();

        for(Team t: getTeams.getTeams()){
            //Only add team names that are not null
            if(!t.getTeamName().equals("")) {
                teamList.add(t.getTeamName());
            }
        }
        ObservableList<String> comboTeamList = FXCollections.observableList(teamList);
        comboTeam.getItems().addAll(comboTeamList);

        //When an item form the combo box is selected, get the team name and pass it to the
        //method to connect to the database and get the player names associated with the
        //team name
        comboTeam.setOnAction((e)->{
            //Clear the player combobox when a team is selected to prevent from player combo
            //filling with the wrong players
            comboPlayer.getItems().clear();
            ArrayList<String> playerList = new ArrayList<String>();
            players = new getPlayerData();
            playerList = players.getPlayersByTeam(comboTeam.getValue());



            ObservableList<String> comboPlayerList = FXCollections.observableList(playerList);
            comboPlayer.getItems().addAll(comboPlayerList);
        });


    }
}
