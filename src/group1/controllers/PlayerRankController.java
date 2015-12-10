//This is the controller class for displaying ranked players in the league

package group1.controllers;

import group1.Player;
import group1.database_connectors.getPlayerData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class PlayerRankController implements Initializable{
@FXML
    TableColumn<Player, String> colRank, colFirstName, colHandicap, colScore, colTeam,colLastName;
@FXML
    TableView tableRanks;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getPlayerData data = new getPlayerData();

        ArrayList<Player> playerList = new ArrayList<Player>();
        playerList.addAll(data.getPlayers());

        ArrayList<Player> playersWithScore = new ArrayList<>();
        //Only get players with a score
        for(Player player: playerList){
            if(player.getPlayerScore() > 0){
                playersWithScore.add(player);
            }

        }

        Collections.sort(playersWithScore, Player.playerScoreComparator);
        int rank = 1;
        for(Player player: playersWithScore){
            player.setRank(rank);
            rank++;
        }

        //Set the values for what the table columns should display based on their object fields
        colRank.setCellValueFactory(new PropertyValueFactory<Player, String>("playerRank"));
        colHandicap.setCellValueFactory(new PropertyValueFactory<Player, String>("handicap"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<Player, String>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<Player, String>("lastName"));
        colScore.setCellValueFactory(new PropertyValueFactory<Player, String>("playerScore"));
        colTeam.setCellValueFactory(new PropertyValueFactory<Player, String>("team"));

        //Get all the players
        tableRanks.getItems().addAll(playersWithScore);
    }

    //handler to close the window
    public void closeWindow(ActionEvent actionEvent) {
        ControlsDemoMain main = new ControlsDemoMain();
        main.newLayout("group1/fxml/welcome.fxml");
    }
}
