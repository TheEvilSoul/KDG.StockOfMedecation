package live.tesnetwork.kdg.stockofmedication.utils;

import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;

public class Filter {
    private static final UnaryOperator<TextFormatter.Change> ONLY_NUMBERS = change -> {
        String newText = change.getControlNewText();
        if (newText.matches("\\d*")) {
            return change;
        }
        return null;
    };

    public static final TextFormatter OnlyAllowNumbers() {
        return new TextFormatter<>(ONLY_NUMBERS);
    }
}
