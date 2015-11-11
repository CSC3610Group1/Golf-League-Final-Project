package group1;

import javax.swing.*;

/**
 * Created by rnice01 on 11/3/2015.
 * Class to hold exception pop up messages
 */
public class ExceptionHandler extends Exception{
    static JOptionPane frame;
    public static void sqlException(){
        JOptionPane.showMessageDialog( frame,
                "Could not connect to the Network, if problem persists please contact your administrator", "Error",
                JOptionPane.ERROR_MESSAGE);
    }




}
