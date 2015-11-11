package group1;

import javax.swing.*;

/**
 * Created by rnice01 on 11/4/2015.
 */
public class MessageBoxes {
    public static void returnMessage(String message, String type){
        JOptionPane frame = new JOptionPane();

        if(type.equals("confirmation")) {
            JOptionPane.showMessageDialog(frame,
                    message, "Success",
                    JOptionPane.OK_OPTION);
        }
        else if(type.equals("error")){
            JOptionPane.showMessageDialog(frame,
                    message, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
