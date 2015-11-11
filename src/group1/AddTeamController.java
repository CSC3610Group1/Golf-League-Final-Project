package group1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by rnice01 on 11/11/2015.
 * This is the controller for the interface for adding the teams, pushes team name and default score of 0
 * to local database through pushTeamData method in the getTeamData class
 */
public class AddTeamController implements Initializable{
@FXML
Button btnOK;
@FXML
    TextField EnterTeamTextField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        btnOK.setOnAction((event) -> {
            String name = EnterTeamTextField.getText();
            Team team = new Team(name , 0);
            try {
                System.out.print(team.getTeamName());
                //getTeamData.pushTeamData(team) replace with true to insert data to database
                if(true){
                    AddPlayerController cont = new AddPlayerController();
                    cont.addPlayerController(team.getTeamName());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }


}
