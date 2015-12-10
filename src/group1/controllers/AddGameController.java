//Controller class for adding a game (score)

package group1.controllers;

import group1.*;
import group1.database_connectors.getPlayerData;
import group1.database_connectors.getTeamData;
import group1.database_connectors.PushPlayerData;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddGameController implements Initializable {
    getPlayerData players;
    @FXML
    RestrictiveTextField fieldScore;
    @FXML
    Label labelTeamWarning, labelPlayerWarning, labelScoreWarning, labelUpdateSuccess,
            labelSelectScore, labelSelectPlayer;
    @FXML
    ComboBox<String> comboTeam, comboPlayer;
    @FXML
    Button btnSubmit;


    public void submitScore(ActionEvent actionEvent) {
        PushPlayerData playerData = new PushPlayerData();
        Boolean noErrors = false;
        int score;

        //Check that fields are set before updating the player score

        try {
            String validator;
            validator = Validator.isScoreValid(fieldScore.getText().trim());
            if (!validator.isEmpty()) {
                //sets error dialog box to contain all errors from validator
                ErrorDialogBox errorDialogBox = new ErrorDialogBox(validator);
                fieldScore.setText(null);
            } else {
               noErrors = true;

            }
            //If all the fields are filled in correctly, push the data to the database, method returns true if update runs successfully
            if (noErrors) {

                //parse the score value from string to integer
                score = Integer.parseInt(fieldScore.getText().trim());

                //Updates the player's individual score and the the team's score
                if (playerData.UpdatePlayerScore(comboPlayer.getValue(), comboTeam.getValue(), score)
                        && playerData.UpdateTeamScore(comboTeam.getValue(), score)) {
                    Alert updated = new Alert(Alert.AlertType.INFORMATION);
                    updated.setTitle("");
                    updated.setHeaderText("Score Updated");
                    updated.setContentText("Player's score has been updated");
                    updated.initStyle(StageStyle.UTILITY);
                    updated.showAndWait();
                    fieldScore.setText(null);

                } else {//If the updatescore method returns false, inform the user
                    labelUpdateSuccess.setVisible(true);
                    labelUpdateSuccess.setText("Unable to update score, please check your network connection");
                    fieldScore.setText(null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            ExceptionHandler.numberFormatException("Number Format Exception");


        }

    }


    //handler to close the window
    public void closeWindow(ActionEvent actionEvent) {
        ControlsDemoMain main = new ControlsDemoMain();
        main.newLayout("group1/fxml/welcome.fxml");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //sets submit button as default button when it or field score is selected
        btnSubmit.defaultButtonProperty().bind(btnSubmit.focusedProperty()
                .or(fieldScore.focusedProperty()));
        //binds player label and combo box and disables them
        comboPlayer.disableProperty().bindBidirectional(labelSelectPlayer.disableProperty());

        //keeps the player combo box and label disabled until a team is selected
        comboPlayer.disableProperty().bind(
                comboTeam.valueProperty().isNull());

        //disables score text field and label
        fieldScore.disableProperty().bindBidirectional(
                labelSelectScore.disableProperty());

        //disables score text box until a player and team are selected
        fieldScore.disableProperty().bind(
                comboTeam.valueProperty().isNull().
                        or(comboPlayer.valueProperty().isNull()));

        btnSubmit.disableProperty().bind(Bindings.isEmpty(fieldScore.textProperty()));

        fieldScore.setMaxLength(2);
        fieldScore.setTextFormatter(new TextFormatter<>(Formatter.numericOnlyFormatter));
        //ArrayList for holding the team names from the database
        ArrayList<String> teamList = new ArrayList<String>();
        getTeamData getTeams = new getTeamData();

        for (Team t : getTeams.getTeams()) {
            //Only add team names that are not null
            if (!t.getTeamName().equals("")) {
                teamList.add(t.getTeamName());
            }
        }
        //Add all the teams to the combobox
        ObservableList<String> comboTeamList = FXCollections.observableList(teamList);
        comboTeam.getItems().addAll(comboTeamList);


        //Handle combobox actions for selecting teams
        comboTeam.setOnAction((e) -> {
            //Clear the player combobox when a team is selected to prevent from player combo
            //filling with the wrong players
            comboPlayer.getItems().clear();

            //When an item from this combo box is selected, get the team name and pass it to the
            //method to connect to the database and get the player names associated with the
            //team name
            ArrayList<String> playerList = new ArrayList<String>();
            players = new getPlayerData();
            playerList = players.getPlayersByTeam(comboTeam.getValue());


            ObservableList<String> comboPlayerList = FXCollections.observableList(playerList);
            comboPlayer.getItems().addAll(comboPlayerList);
        });


    }
}
