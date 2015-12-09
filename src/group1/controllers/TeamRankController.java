package group1.controllers;

import group1.Team;
import group1.database_connectors.getTeamData;
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

/**
 * Created by rnice01 on 11/17/2015.
 */
public class TeamRankController implements Initializable{
@FXML
    TableColumn<Team, String> colRank, colName, colScore;
@FXML
    TableView tableRanks;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getTeamData data = new getTeamData();

        ArrayList<Team> teamList = new ArrayList<Team>();
        teamList.addAll(data.getTeams());

        ArrayList<Team> teamsWithScore = new ArrayList<>();
        //Only get players with a score
        for(Team team: teamList){
            if(team.getTeamScore() > 0){
                teamsWithScore.add(team);
            }

        }

        Collections.sort(teamList, Team.teamScoreComparator);

        int rank = 1;
        for(Team team: teamsWithScore){
            team.setRank(rank);
            rank++;
        }

        //Set the values for columns to display by Team object field name
        colName.setCellValueFactory(new PropertyValueFactory<Team, String>("teamName"));
        colRank.setCellValueFactory(new PropertyValueFactory<Team, String>("teamRank"));
        colScore.setCellValueFactory(new PropertyValueFactory<Team, String>("teamScore"));

        //Add the Team object list from the getTeams method
        tableRanks.getItems().addAll(teamsWithScore);
    }

    //handler to close the window
    public void closeWindow(ActionEvent actionEvent) {
        ControlsDemoMain main = new ControlsDemoMain();
        main.newLayout("group1/fxml/welcome.fxml");
    }
}
