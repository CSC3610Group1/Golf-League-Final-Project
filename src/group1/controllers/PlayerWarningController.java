package group1.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by rnice01 on 11/13/2015.
 * This is the controller class for the pop up window to warn users if they try canceling out of adding
 * 4 players to a team. A team can not be made without 4 playres so the window/controller is meant to handle that.
 */
public class PlayerWarningController {
    @FXML
    Button btnOk, btnCancel;
    Stage stage;
    Parent root;
    ActionEvent event;
    public void addPlayerWarning() {

        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("group1/fxml/add_player_warning.fxml"));
            this.stage = new Stage();
            this.stage.setTitle("Enter Teammate");
            this.stage.setScene(new Scene(root, 500, 250));
            this.stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void closeWindow(ActionEvent actionEvent) {
        Node  source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    //This event will currently exit the application entirely but would like to find a way to exit
    //to the main screen
    public void abortAddPlayer(ActionEvent actionEvent) {
       System.exit(0);

    }
}
