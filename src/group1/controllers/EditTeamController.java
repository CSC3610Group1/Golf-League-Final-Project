//Controller for the edit team page, to edit the players on a team

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
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class EditTeamController implements Initializable {

    getPlayerData players;
    @FXML
    Button btnSubmitScore;
    @FXML
    RestrictiveTextField fieldFirstName, fieldLastName;
    @FXML
    Label labelTeamWarning, labelPlayerWarning, labelFirstNameWarning, labelLastNameWarning,
            labelUpdateSuccess, labelFirstName, labelLastName, labelNewPlayer, labelSelectPlayer;
    @FXML
    ComboBox<String> comboTeam, comboPlayer;


    public void submitScore(ActionEvent actionEvent) {
        PushPlayerData playerData = new PushPlayerData();
        Boolean noErrors = false;
        try {
            List<String> validator = Validator.validatePlayer(new Player(fieldFirstName.getText().trim(),
                    fieldLastName.getText().trim(), 0, 0, 0, 0, 0)).getInvalidNames();
            if (!validator.isEmpty()) {
                ErrorDialogBox errorDialogBox = new ErrorDialogBox(validator.stream()
                        .collect(Collectors.joining("\n")));
            } else {
                noErrors = true;
            }

            if (noErrors) {//If all the fields are filled in correctly, push the data to the database, method returns true if update runs successfully

                //
                if (playerData.UpdatePlayer(comboPlayer.getValue(), comboTeam.getValue(), fieldFirstName.getText(), fieldLastName.getText())) {
                    Alert updated = new Alert(Alert.AlertType.INFORMATION);
                    updated.setTitle("");
                    updated.setHeaderText("Team Updated");
                    updated.setContentText("Player has been replaced");
                    updated.initStyle(StageStyle.UTILITY);
                    updated.showAndWait();
                    fieldFirstName.setText(null);
                    fieldLastName.setText(null);

                } else {//If the updatescore method returns false, inform the user
                    labelUpdateSuccess.setVisible(true);
                    labelUpdateSuccess.setText("Unable to update team with new player, please check your network connection");
                    fieldFirstName.setText(null);
                    fieldLastName.setText(null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //
    //handler to close the window
    public void closeWindow(ActionEvent actionEvent) {
        ControlsDemoMain main = new ControlsDemoMain();
        main.newLayout("group1/fxml/welcome.fxml");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //ArrayList for holding the team names from the database
        ArrayList<String> teamList = new ArrayList<>();
        getTeamData getTeams = new getTeamData();

        comboPlayer.disableProperty().bindBidirectional(labelSelectPlayer.disableProperty());
        comboPlayer.disableProperty().bind(comboTeam.valueProperty().isNull());
        //disables labels for first name,last name, and new player until fieldFirstName is enabled
        labelFirstName.disableProperty().bindBidirectional(labelLastName.disableProperty());
        labelFirstName.disableProperty().bindBidirectional(fieldFirstName.disableProperty());
        labelNewPlayer.disableProperty().bind(fieldFirstName.disableProperty());

        //disables text fields for first and last name until both combo boxes are selected
        fieldFirstName.disableProperty().bindBidirectional(
                fieldLastName.disableProperty());
        fieldFirstName.disableProperty().bind(
                comboTeam.valueProperty().isNull()
                        .or(comboPlayer.valueProperty().isNull()));

        //disables submit button until form is complete
        btnSubmitScore.disableProperty().bind(Bindings.isEmpty(fieldFirstName.textProperty())
                .or(Bindings.isEmpty(fieldLastName.textProperty())));

        //sets max length of text fields and filters input
        fieldFirstName.setMaxLength(25);
        fieldLastName.setMaxLength(25);
        fieldFirstName.setTextFormatter(new TextFormatter<>(Formatter.letterOnly));
        fieldLastName.setTextFormatter(new TextFormatter<>(Formatter.letterOnly));

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
