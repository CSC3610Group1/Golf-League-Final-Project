package group1.controllers;

import group1.database_connectors.ExportPlayerData;
import group1.database_connectors.getTeamData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class ControlsDemoController implements Initializable {

    getTeamData getTeams;
    ArrayList<String> teamList;
    @FXML
    private Button btnAddTeam, btnExportData, btnEnterScore, btnRank, btnByPlayer, btnByTeam, btnEditTeam, btnSearchPlayerRank;
    @FXML
    private Label userFeedback;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //allows enter key to be used on the currently focused button
        btnAddTeam.defaultButtonProperty().bind(btnAddTeam.focusedProperty());
        btnRank.defaultButtonProperty().bind(btnRank.focusedProperty());
        btnSearchPlayerRank.defaultButtonProperty().bind(btnSearchPlayerRank.focusedProperty());
        btnByPlayer.defaultButtonProperty().bind(btnByPlayer.focusedProperty());
        btnByTeam.defaultButtonProperty().bind(btnByTeam.focusedProperty());
        btnEditTeam.defaultButtonProperty().bind(btnEditTeam.focusedProperty());
        btnEnterScore.defaultButtonProperty().bind(btnEnterScore.focusedProperty());
        btnExportData.defaultButtonProperty().bind(btnExportData.focusedProperty());

        btnAddTeam.setOnAction((event) -> {
            ControlsDemoMain main = new ControlsDemoMain();
            main.newLayout("group1/fxml/enter_team_name.fxml");
        });

        btnExportData.setOnAction((event) -> {
            ExportPlayerData export = new ExportPlayerData();
            if (export.generateCSV()) {
                userFeedback.setVisible(true);
                userFeedback.setText("Data exported successfully");
            } else {
                userFeedback.setVisible(true);
                userFeedback.setText("There was a problem creating the file");
            }

        });

        btnEnterScore.setOnAction((e) -> {
            ControlsDemoMain main = new ControlsDemoMain();
            main.newLayout("group1/fxml/add_game.fxml");

        });

        btnRank.setOnAction((e) -> {
            btnRank.setVisible(false);
            btnByPlayer.setVisible(true);
            btnByTeam.setVisible(true);


        });

        btnByPlayer.setOnAction((e) -> {
            ControlsDemoMain main = new ControlsDemoMain();
            main.newLayout("group1/fxml/player_ranks.fxml");
            btnRank.setVisible(true);
            btnByPlayer.setVisible(false);
            btnByTeam.setVisible(false);
        });

        btnByTeam.setOnAction((e) -> {
            ControlsDemoMain main = new ControlsDemoMain();
            main.newLayout("group1/fxml/team_ranks.fxml");
            btnRank.setVisible(true);
            btnByPlayer.setVisible(false);
            btnByTeam.setVisible(false);
        });

        btnEditTeam.setOnAction((e) -> {
            ControlsDemoMain main = new ControlsDemoMain();
            main.newLayout("group1/fxml/edit_team.fxml");
        });

        btnSearchPlayerRank.setOnAction((e) -> {
            SearchPlayerRankController controller = new SearchPlayerRankController();
            controller.StartSearchPlayerRank();
        });


    }
}

		
	


	
