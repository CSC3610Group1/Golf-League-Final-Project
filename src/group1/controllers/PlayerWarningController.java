package group1.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by rnice01 on 11/13/2015.
 * This is the controller class for the pop up window to warn users if they try canceling out of adding
 * 4 players to a team. A team can not be made without 4 playres so the window/controller is meant to handle that.
 */
public class PlayerWarningController implements Initializable{
    @FXML
    Button btnOk, btnCancel;

    public void addPlayerWarning() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("group1/fxml/add_player_warning.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Enter Teammate");
            stage.setScene(new Scene(root, 500, 250));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}
