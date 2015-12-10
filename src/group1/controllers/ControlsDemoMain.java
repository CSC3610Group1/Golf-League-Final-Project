//Class defines the elements for the welcome page of the league

package group1.controllers;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class ControlsDemoMain extends Application {

	static Stage primaryStage;
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
		Scene scene = new Scene(rootlayout, 600, 450);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	//Method to open a new scene in the primary stage
	//to circumvent having too many new windows pop up
	//as the user navigates through the app
	public void newLayout(String resource){
		//Clear out the current scene
		primaryStage.setScene(null);
		//load root layout form fxml file
		Parent root;
		try {
			//Loads the fxml file that the method was invoked with
			root = FXMLLoader.load(getClass().getClassLoader().getResource(resource));
			primaryStage.setScene(new Scene(root,600, 450));
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public static void main(String[] args) {
		launch(args);
	}
}
