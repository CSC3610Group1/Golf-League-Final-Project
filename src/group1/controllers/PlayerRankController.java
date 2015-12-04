package group1.controllers;

import group1.Player;
import group1.database_connectors.getPlayerData;
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
public class PlayerRankController implements Initializable{
@FXML
    TableColumn<Player, String> colRank, colFirstName, colHandicap, colScore, colTeam,colLastName;
@FXML
    TableView tableRanks;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getPlayerData data = new getPlayerData();


        //Set the values for what the table columns should display based on their object fields
        colHandicap.setCellValueFactory(new PropertyValueFactory<Player, String>("handicap"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<Player, String>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<Player, String>("lastName"));
        colScore.setCellValueFactory(new PropertyValueFactory<Player, String>("playerScore"));
        colTeam.setCellValueFactory(new PropertyValueFactory<Player, String>("team"));

        //Get all the players
        tableRanks.getItems().addAll(data.getPlayers());
    }

    //handler to close the window
    public void closeWindow(ActionEvent actionEvent) {
        ControlsDemoMain main = new ControlsDemoMain();
        main.newLayout("group1/fxml/welcome.fxml");
    }
}
