package group1.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import group1.Team;
import group1.database_connectors.ExportPlayerData;
import group1.database_connectors.getTeamData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class ControlsDemoController implements Initializable {

	@FXML
	private Button btnAddTeam,btnExportData, btnEnterScore, btnRank,btnByPlayer,btnByTeam,btnEditTeam,btnSearchPlayerRank;
	@FXML
	private Label userFeedback;
	getTeamData getTeams;
	ArrayList<String> teamList;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		btnAddTeam.setOnAction((event) -> {
			Parent root;
			try {
				root = FXMLLoader.load(getClass().getClassLoader().getResource("group1/fxml/enter_team_name.fxml"));
				Stage stage = new Stage();
				stage.setTitle("Enter Team");
				stage.setScene(new Scene(root, 600, 450));
				stage.show();

			} catch (IOException e) {
				e.printStackTrace();
			}});

		btnExportData.setOnAction((event) -> {
			ExportPlayerData export = new ExportPlayerData();
			if(export.generateCSV()){
				userFeedback.setVisible(true);
				userFeedback.setText("Data exported successfully");
			}
			else{
				userFeedback.setVisible(true);
				userFeedback.setText("There was a problem creating the file");
			}

			});

		btnEnterScore.setOnAction((e)->{

			AddGameController cont = new AddGameController();
			cont.StartScoreStage();
			});

		btnRank.setOnAction((e)->{
			btnRank.setVisible(false);
			btnByPlayer.setVisible(true);
			btnByTeam.setVisible(true);


		});

		btnByPlayer.setOnAction((e)->{
			PlayerRankController controller = new PlayerRankController();
			controller.startStage();
			btnRank.setVisible(true);
			btnByPlayer.setVisible(false);
			btnByTeam.setVisible(false);
		});

		btnByTeam.setOnAction((e)->{
			TeamRankController controller = new TeamRankController();
			controller.startStage();
			btnRank.setVisible(true);
			btnByPlayer.setVisible(false);
			btnByTeam.setVisible(false);
		});

		btnEditTeam.setOnAction((e)->{
			EditTeamController controller = new EditTeamController();
			controller.StartEditTeam();
		});

		btnSearchPlayerRank.setOnAction((e)->{
			SearchPlayerRankController controller = new SearchPlayerRankController();
			controller.StartSearchPlayerRank();
		});



	}
}
			
		
	


	
