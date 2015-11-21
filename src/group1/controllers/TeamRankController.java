package group1.controllers;

import group1.Player;
import group1.Team;
import group1.database_connectors.getPlayerData;
import group1.database_connectors.getTeamData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by rnice01 on 11/17/2015.
 */
public class TeamRankController implements Initializable{
@FXML
    TableColumn<Team, String> colRank, colName, colScore;
@FXML
    TableView tableRanks;
Stage stage;
    public void startStage(){

        Parent root;
        try {


            root = FXMLLoader.load(getClass().getClassLoader().getResource("group1/fxml/team_ranks.fxml"));
            stage = new Stage();
            stage.setTitle("Teams by Rank");
            stage.setScene(new Scene(root, 600, 450));

            stage.show();



        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getTeamData data = new getTeamData();


        //Set the values for columns to display by Team object field name
        colName.setCellValueFactory(new PropertyValueFactory<Team, String>("teamName"));
        colRank.setCellValueFactory(new PropertyValueFactory<Team, String>("rank"));
        colScore.setCellValueFactory(new PropertyValueFactory<Team, String>("teamScore"));

        //Add the Team object list from the getTeams method
        tableRanks.getItems().addAll(data.getTeams());
    }

    public void closeWindow(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
