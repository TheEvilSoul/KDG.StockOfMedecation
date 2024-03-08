package live.tesnetwork.kdg.stockofmedication.view;

import javafx.scene.layout.Pane;

public class ErrorView extends Pane implements ViewHelper {

    private final String name;
    private final String body;

    public ErrorView(String name, String body) {
        this.name = name;
        this.body = body;
    }
    @Override
    public String getTitle() {
        return "Error: " + name;
    }

    @Override
    public void initialize() {

    }
}
