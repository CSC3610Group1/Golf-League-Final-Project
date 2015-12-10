/**
 * Class to hold exception pop up messages
 */

package group1;

import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

public class ExceptionHandler extends Exception {
    public static void sqlException() {
        new ErrorDialogBox("Could not connect to the Network, if problem persists please contact your administrator");
    }

    public static void numberFormatException(String errorMessage) {
        Alert exception = new Alert(Alert.AlertType.ERROR);
        exception.setTitle("Error");
        exception.setHeaderText("Invalid Input");
        exception.initStyle(StageStyle.UTILITY);
        exception.setContentText(errorMessage);
        exception.showAndWait();
    }


}
