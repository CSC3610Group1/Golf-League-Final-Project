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
Stage stage;
ArrayList<Player> playerList;
    public void startStage(){

        Parent root;
        try {


            root = FXMLLoader.load(getClass().getClassLoader().getResource("group1/fxml/player_ranks.fxml"));
            stage = new Stage();
            stage.setTitle("Players by Rank");
            stage.setScene(new Scene(root, 600, 450));

            stage.show();



        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playerList = new ArrayList<>();
        getPlayerData data = new getPlayerData();

        playerList.addAll(data.getPlayers());

        colHandicap.setCellValueFactory(new PropertyValueFactory<Player, String>("handicap"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<Player, String>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<Player, String>("lastName"));
        colScore.setCellValueFactory(new PropertyValueFactory<Player, String>("playerScore"));
        colTeam.setCellValueFactory(new PropertyValueFactory<Player, String>("team"));

        tableRanks.getItems().addAll(data.getPlayers());
    }

    public void closeWindow(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
