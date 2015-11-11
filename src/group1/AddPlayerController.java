package group1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by rnice01 on 11/11/2015.
 */
public class AddPlayerController implements Initializable{
@FXML
    TextField FirstNameField, LastNameField, TeamField,HandicapField;
@FXML
Button btnOK, btnCancel;
@FXML
    Label labelMaxPlayers;
    int count = 1;
    public void addPlayerController(String teamName) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("group1/fxml/add_teammate.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Enter Teammate");
            stage.setScene(new Scene(root, 600, 450));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }};



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btnOK.setOnAction((e)->{
            if(count <= 4) {
                int handi = Integer.parseInt(HandicapField.getText());
                Player player = new Player(FirstNameField.getText(), LastNameField.getText(), 0, 0, handi, 0, 0);
                System.out.println(player.getFirstName() + " " + player.getLastName() + " " + player.getHandicap());

                FirstNameField.setText(null);
                LastNameField.setText(null);
                HandicapField.setText(null);
            }
            else{
                FirstNameField.setVisible(false);
                LastNameField.setVisible(false);
                HandicapField.setVisible(false);
                labelMaxPlayers.setVisible(true);
            }
            count++;
        });
    }



}

