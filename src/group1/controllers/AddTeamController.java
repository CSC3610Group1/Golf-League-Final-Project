package group1.controllers;

import group1.Team;
import group1.database_connectors.getTeamData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by rnice01 on 11/11/2015.
 * This is the controller for the interface for adding the teams, pushes team name and default score of 0
 * to local database through pushTeamData method in the getTeamData class
 */
public class AddTeamController implements Initializable{
@FXML
    Label labelTeamExists;
@FXML
Button btnOK, btnCancel;
@FXML
    TextField EnterTeamTextField;
getTeamData getTeams;
    @Override
    public void initialize(URL location, ResourceBundle resources) {


        btnOK.setOnAction((event) -> {
            //Create a new team object, get the name from the field and default the team score to 0
            String name = EnterTeamTextField.getText();
            Team team = new Team(name , 0);


            try {
                //Create a new arraylist to add all of the current team names
                //from the database and compare them to the name given in the textfield
                //if name exists, give them an error, if not then continue
                ArrayList<String> compareTeamNames = new ArrayList<>();
                for(Team t: getTeams.getTeams()){
                   compareTeamNames.add(t.getTeamName());
                }
                if(!compareTeamNames.contains(name)){
                    //Pass the team object to the add player controller to later
                    //add both the team and the players to the database
                    AddPlayerController cont = new AddPlayerController();
                    cont.addPlayerController(team);
                }
                else{
                    //If the team name exists, clear the field and notify the user
                    EnterTeamTextField.setText(null);
                    labelTeamExists.setVisible(true);
                    labelTeamExists.setText("A team with that name already exists, please try another name");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


    }

    //handler to close the window
    public void closeWindow(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }


}
