package group1.controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ControlsDemoMain extends Application {

    static Stage primaryStage;
    private BorderPane rootLayout;
    private AnchorPane controlsData;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ControlsDemoMain.primaryStage = primaryStage;
        ControlsDemoMain.primaryStage.setTitle("Welcome to the Outting!  Where all your golf tournament needs are served!");

        initializeRootLayout();
        showControlData();
    }

    private void showControlData() {
        // load
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ControlsDemoMain.class.getResource("../fxml/welcome.fxml"));
        try {
            controlsData = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        rootLayout.setCenter(controlsData);
        //primaryStage.show();


    }

    private void initializeRootLayout() {
        //load root layout form fxml file
        FXMLLoader csc2650loader = new FXMLLoader();
        csc2650loader.setLocation(ControlsDemoMain.class.getResource("../fxml/RootLayout.fxml"));
        try {
            rootLayout = csc2650loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //set scene
        Scene scene = new Scene(rootLayout, 600, 450);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //Method to open a new scene in the primary stage
    //to circumvent having too many new windows pop up
    //as the user navigates through the app
    public void newLayout(String resource) {
        //Clear out the current scene
        primaryStage.setScene(null);
        //load root layout form fxml file
        Parent root;
        try {
            //Loads the fxml file that the method was invoked with
            root = FXMLLoader.load(getClass().getClassLoader().getResource(resource));
            primaryStage.setScene(new Scene(root, 600, 450));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
