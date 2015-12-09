package group1;

import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;

/**
 *General methods for filtering text field input
 */
public final class Formatter {
    private Formatter() {}

    //creates filter for letter characters only
    public static final UnaryOperator<TextFormatter.Change> letterOnly = e -> {
        e.setText(e.getText().replaceAll("[^a-z]", ""));
        return e;
    };
    //creates filter for numeric characters only
    public static final UnaryOperator<TextFormatter.Change> numericOnlyFormatter = e -> {
        e.setText(e.getText().replaceAll("[^\\d]", ""));
        return e;
    };
}
