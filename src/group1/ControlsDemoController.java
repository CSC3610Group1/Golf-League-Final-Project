package group1;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class ControlsDemoController {

	@FXML
	private Button btnAddTeam;
	@FXML
	private Button btnViewTeams;
	@FXML
	private Button btnRank;

	
	@FXML
	private void intialize() {
		// handle Button Event
		btnAddTeam.setOnAction((event) -> {
			Parent root;
	        try {
	            root = FXMLLoader.load(getClass().getClassLoader().getResource("gui_package/enter_team_name.fxml"));
	            Stage stage = new Stage();
	            stage.setTitle("Enter Team");
	            stage.setScene(new Scene(root, 600, 450));
	            stage.show();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }});
		
	
	
	
	
	
	}}
			
		
	


	
