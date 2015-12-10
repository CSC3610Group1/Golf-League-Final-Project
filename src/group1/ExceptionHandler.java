/**
 * Class to hold exception pop up messages
 */

package group1;

import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.util.stream.Collectors;

public class ExceptionHandler extends Exception{
    static JOptionPane frame;
    public static void sqlException(){
        JOptionPane.showMessageDialog( frame,
                "Could not connect to the Network, if problem persists please contact your administrator", "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void numberFormatException(String errorMessage){
        Alert exception = new Alert(Alert.AlertType.ERROR);
        exception.setTitle("Error");
        exception.setHeaderText("Invalid Input");
        exception.initStyle(StageStyle.UTILITY);
        exception.setContentText(errorMessage);
        exception.showAndWait();
    }





}
