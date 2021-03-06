/** Creates error dialog box with default parameters
 */

package group1;

import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

public class ErrorDialogBox extends Alert {

    public ErrorDialogBox(String contentText) {
        super(AlertType.ERROR);
        Alert errorDialogBox = new Alert(Alert.AlertType.ERROR);
        errorDialogBox.setTitle("Error");
        errorDialogBox.setHeaderText("Invalid Input");
        errorDialogBox.initStyle(StageStyle.UTILITY);
        errorDialogBox.setContentText(contentText);
        errorDialogBox.showAndWait();


    }

}
