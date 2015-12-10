//Controller to add players to the league

package group1.controllers;

import group1.*;
import group1.database_connectors.PushPlayerData;
import group1.database_connectors.getTeamData;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AddPlayerController implements Initializable {

    static Team team;
    @FXML
    RestrictiveTextField firstNameField;
    @FXML
    RestrictiveTextField lastNameField;
    @FXML
    RestrictiveTextField handicapField;

    @FXML
    TextField TeamField;
    @FXML
    Button btnOK, btnCancel, btnClose;
    @FXML
    Label labelMaxPlayers;
    ArrayList<Player> playerList = new ArrayList<>();
    private Image imageIcon = new Image("golf-green.png");

    //Method to start the stage and take the team name passed from the Add Team stage
    //Team is made static to allow access of object when pushing the players and team
    //to the database
    public void addPlayerController(Team team) {
        //Team object is set here
        this.team = team;
        Parent root;
        try {

            root = FXMLLoader.load(getClass().getClassLoader().getResource("group1/fxml/add_teammate.fxml"));
            Stage stage = new Stage();
            stage.getIcons().add(imageIcon);
            stage.setTitle("Enter Teammate");
            stage.setScene(new Scene(root, 600, 450));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btnOK.disableProperty().bind(
                Bindings.isEmpty(firstNameField.textProperty())
                        .or(Bindings.isEmpty(lastNameField.textProperty())
                                .or(Bindings.isEmpty(handicapField.textProperty()))));

        btnOK.defaultButtonProperty().bind(
                btnOK.focusedProperty()
                        .or(firstNameField.focusedProperty())
                        .or(lastNameField.focusedProperty())
                        .or(handicapField.focusedProperty()));

        lastNameField.setMaxLength(20);
        firstNameField.setMaxLength(20);
        handicapField.setMaxLength(2);

        lastNameField.setTextFormatter(new TextFormatter<>(Formatter.letterOnly));
        firstNameField.setTextFormatter(new TextFormatter<>(Formatter.letterOnly));
        handicapField.setTextFormatter(new TextFormatter<>(Formatter.numericOnlyFormatter));

    }

    //Closes the stage if cancel button is clicked
    //Check if a full team has been added before cancellation
    public void closeWindow(ActionEvent actionEvent) {
        if (playerList.size() < 3) {
            PlayerWarningController warn = new PlayerWarningController();
            //Pop up window to warn users that team will not be added if
            //enough players have been added
            warn.addPlayerWarning();

        } else {
            Node source = (Node) actionEvent.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }

    }


    public void addPlayer(ActionEvent actionEvent) {

        if (playerList.size() == 3) {
            //Create and add the last player object to the object arraylist before pushing the data to DB
            int handi = Integer.parseInt(handicapField.getText().trim());
            Player player = new Player(firstNameField.getText().trim(), lastNameField.getText().trim(), 0, 0, handi, 0, 0, team.getTeamName());
            playerList.add(player);
            //If all 4 players have been added, clear the fields and push the data
            firstNameField.setVisible(false);
            lastNameField.setVisible(false);
            handicapField.setVisible(false);

            //Creating instances of database connecting classes to access their methods
            PushPlayerData push = new PushPlayerData();
            getTeamData pushTeam = new getTeamData();
            try {
                //If both JDBC methods return true
                //Show label to confirm with user
                if (push.pushPlayerData(playerList) && pushTeam.pushTeamData(team)) {
                    labelMaxPlayers.setVisible(true);
                    labelMaxPlayers.setText("All players and team have been added successfully");

                    //Change available buttons to user once update is successful
                    btnCancel.setVisible(false);
                    btnOK.setVisible(false);
                    btnClose.setVisible(true);
                } else {
                    labelMaxPlayers.setVisible(true);
                    labelMaxPlayers.setText("There was a networking error, please contact your administrator if problem persists");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            //Get the data from the fields and create a new player object

            try {
                int handi = Integer.parseInt(handicapField.getText().trim());
                Player player = new Player(firstNameField.getText().trim(),
                        lastNameField.getText().trim(), 0, 0, handi, 0, 0, team.getTeamName());
                //validates the player object and returns a list of errors
                List<String> validator = Validator.validatePlayer(player).getAllInvalid();
                if (!validator.isEmpty()) {
                    //sets error dialog box to contain all errors from validator
                    new ErrorDialogBox(validator.stream().collect(Collectors.joining("\n -")));

                } else {

                    System.out.println(player.getFirstName() + " " + player.getLastName()
                            + " " + player.getHandicap());
                    System.out.println(playerList.size());
                    //Add the new player object to the arraylist for player objects
                    playerList.add(player);
                    firstNameField.setText(null);
                    lastNameField.setText(null);
                    handicapField.setText(null);
                }
            } catch (Exception e) {
                ExceptionHandler.numberFormatException("Handicap must be a positive number");
            }

        }

    }
}

