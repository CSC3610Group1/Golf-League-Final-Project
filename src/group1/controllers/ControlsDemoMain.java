package group1.controllers;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;





public class ControlsDemoMain extends Application {

	private Stage primaryStage;
	private BorderPane rootlayout;
	private AnchorPane controlsData;
	
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Welcome to the Outting!  Where all your golf tournament needs are served!");
		
		initializeRootlLayout();
		showControlData();
	}

	private void showControlData() {
		// load
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ControlsDemoMain.class.getResource("../fxml/welcome.fxml"));
		try{
			controlsData = (AnchorPane) loader.load();
		}catch(IOException e){
			e.printStackTrace();
		}
	rootlayout.setCenter(controlsData);
	//primaryStage.show();
	
	
	
	}

	private void initializeRootlLayout() {
		//load root layout form fxml file
		FXMLLoader csc2650loader = new FXMLLoader();
		csc2650loader.setLocation(ControlsDemoMain.class.getResource("../fxml/RootLayout.fxml"));
		try{
			rootlayout = (BorderPane) csc2650loader.load();
		}catch(IOException e){
			e.printStackTrace();
		}
		//set scene 
		Scene scene = new Scene(rootlayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
