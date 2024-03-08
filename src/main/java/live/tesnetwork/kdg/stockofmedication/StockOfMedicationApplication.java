package live.tesnetwork.kdg.stockofmedication;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import live.tesnetwork.kdg.stockofmedication.entity.User;
import live.tesnetwork.kdg.stockofmedication.enums.Views;
import live.tesnetwork.kdg.stockofmedication.view.ErrorView;
import live.tesnetwork.kdg.stockofmedication.view.LoginView;
import live.tesnetwork.kdg.stockofmedication.view.MainMenuView;
import live.tesnetwork.kdg.stockofmedication.view.ViewHelper;
import org.jetbrains.annotations.Nullable;

public class StockOfMedicationApplication extends Application {

    private static Stage stage;

    @Nullable
    private static User user;

    public static void setUser(User user) {
        StockOfMedicationApplication.user = user;
    }

    @Nullable
    public static User getUser() {
        return user;
    }

    public static void switchView(Views views) {
        if (getUser() == null) views = Views.LOGIN;
        switch (views) {
            case LOGIN:
                setView(new LoginView());
                break;
            case MAIN:
                setView(new MainMenuView(user));
                break;
        }
    }

    @Override
    public void start(Stage stage) {
        this.stage=stage;
        this.setView(new LoginView());
    }

    public static <T extends Parent & ViewHelper> void setView(T view) {
        try {
            view.initialize();
        } catch (Exception e) {
            setView(new ErrorView("view initialization", "An error occurred while initializing the view. Please try again."));
            return;
        }


        // Creating a Scene and adding the view to it
        Scene scene = new Scene(view, view.getSceneWidth(), view.getSceneHeight());

        // Setting the title of the stage
        stage.setTitle(view.getTitle());

        // Setting the scene to the stage
        stage.setScene(scene);

        // Showing the stage
        stage.show();
    }

    public static void giveError(String name, String body) {
        // Create an Alert window
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(stage);
        alert.setTitle("Error: " + name);
        alert.setHeaderText(name);
        alert.setContentText(body);

        // Set the modality of the Alert window to APPLICATION_MODAL
        alert.initModality(Modality.APPLICATION_MODAL);

        // Show the Alert window
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}