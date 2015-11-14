package group1.controllers;


import group1.Team;
import group1.database_connectors.getTeamData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by rnice01 on 11/13/2015.
 */
public class AddGameController {

    @FXML
    ComboBox<String> comboTeam, comboPlayer;
    Stage stage;
    public void StartScoreStage(){


        Parent root;
        try {


            root = FXMLLoader.load(getClass().getClassLoader().getResource("group1/fxml/add_game.fxml"));
            stage = new Stage();
            stage.setTitle("Enter scores for each player");
            stage.setScene(new Scene(root, 600, 450));

            stage.show();


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

        } catch (IOException ex) {
            ex.printStackTrace();
        }





    }

    public void submitScore(ActionEvent actionEvent) {

    }

    public void closeWindow(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void fillTeamCombo(ActionEvent actionEvent) {


    }
}
