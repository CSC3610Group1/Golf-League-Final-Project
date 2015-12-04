package group1.controllers;


import group1.Player;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;

/**
 * Created by reesemodloff on 12/2/15.
 */
    public class SearchPlayerRankController implements Initializable {
        getPlayerData players;
        @FXML
        Label labelTeamWarning, labelPlayerWarning, labelPlayerRank;
        @FXML
        ComboBox<String> comboTeam, comboPlayer;
        @FXML
        Button btnGetRank;
        Stage stage;
        int rank;

    public void StartSearchPlayerRank(){


            Parent root;
            try {


                root = FXMLLoader.load(getClass().getClassLoader().getResource("group1/fxml/search_player_rank.fxml"));
                stage = new Stage();
                stage.setTitle("Find a players rank");
                stage.setScene(new Scene(root, 600, 450));

                stage.show();


            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }


        public void searchRank() {

            //Check that fields are set before searching player
            if(comboTeam.getValue()==null){
                labelPlayerWarning.setVisible(false);
                labelTeamWarning.setVisible(true);
            }
            else if(comboPlayer.getValue() == null){
                labelPlayerWarning.setVisible(true);
                labelTeamWarning.setVisible(false);
            }
            else{//If all the fields are filled in correctly, searches for the player's rank
                ArrayList<Player> playerList = new ArrayList<>();
                players = new getPlayerData();
                playerList = players.getPlayers();
                ArrayList<Player> playersWithScore = new ArrayList<>();

                for(Player player: playerList){
                    if(player.getPlayerScore() > 0){
                        playersWithScore.add(player);
                    }
                }
                Player searchKey = null;

                if(playersWithScore.size() < 2){
                    labelPlayerRank.setText("Not enough players have a score yet!");
                }
                else {
                    for (int i = 0; i < playersWithScore.size(); i++) {

                        Player temp = playersWithScore.get(i);

                        if (comboPlayer.getValue().equals(temp.getFirstName() + " " + temp.getLastName())) {
                            searchKey = temp;
                            break;
                        }
                        else{
                            labelPlayerRank.setText("Did not find player you were looking for");
                        }

                    }

                    Collections.sort(playersWithScore, Player.playerScoreComparator);

                    rank = (Collections.binarySearch(playersWithScore, searchKey, new PlayerRankComp()) + 1);

                    labelPlayerRank.setText("The rank of the player you searched for is " + rank );
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
            //ArrayList for holding the team names from the database
            ArrayList<String> teamList = new ArrayList<>();
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
                ArrayList<String> playerList = new ArrayList<>();
                players = new getPlayerData();
                playerList = players.getPlayersByTeam(comboTeam.getValue());

                ObservableList<String> comboPlayerList = FXCollections.observableList(playerList);
                comboPlayer.getItems().addAll(comboPlayerList);
            });

            btnGetRank.setOnAction((e)->{
                searchRank();
            });

        }

    }
