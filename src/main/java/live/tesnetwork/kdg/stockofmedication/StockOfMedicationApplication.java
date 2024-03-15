package live.tesnetwork.kdg.stockofmedication;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import live.tesnetwork.kdg.stockofmedication.controller.MedicationController;
import live.tesnetwork.kdg.stockofmedication.entity.User;
import live.tesnetwork.kdg.stockofmedication.enums.Views;
import live.tesnetwork.kdg.stockofmedication.presenter.Presenter;
import live.tesnetwork.kdg.stockofmedication.utils.Convertable;
import live.tesnetwork.kdg.stockofmedication.view.*;
import org.jetbrains.annotations.Nullable;

public class StockOfMedicationApplication extends Application {
    private static Stage stage;
    private static Presenter presenter = new Presenter();
    @Nullable
    private static User user;
    private static boolean canGoNegative = false;

    public static void setUser(User user) {
        StockOfMedicationApplication.user = user;
    }

    public static void setCanGoNegative(boolean canGoNegative) {
        StockOfMedicationApplication.canGoNegative = canGoNegative;
    }

    @Nullable
    public static User getUser() {
        return user;
    }

    public static void switchView(Views views) {
        switchView(views, null);
    }
    public static void switchView(Views views, Convertable data) {
        if (user == null) views = Views.LOGIN;
        switch (views) {
            case LOGIN -> setView(new LoginView(), data);
            case MAIN -> setView(new MainMenuView(), data);
            case EDIT_USER_MEDICATION -> setView(new EditUserMedicationView(), data);
            case EDIT_MEDICATION -> setView(new EditMedicationView(), data);
        }
    }

    @Override
    public void start(Stage stage) {
        this.stage=stage;
        this.setView(new LoginView());
    }

    public static void updateMedicationStockWithLastTaken() {
        MedicationController.updateStockFromTakeIn(user.getUsername(), canGoNegative);
    }

    public static void setTitle(String title) {
        stage.setTitle(title);
    }

    public static <T extends Parent & ViewHelper> void setView(T view) {
        setView(view, null);
    }
    public static <T extends Parent & ViewHelper> void setView(T view, Convertable data) {
        try {
            view.initialize();
            presenter.addDataTo(view, data);
        } catch (Exception e) {
            setView(new ErrorView("view initialization", "An error occurred while initializing the view. Please try again."));
            e.printStackTrace();
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

    public static void givePopup(String name, String body) {
        // Create an Alert window
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(stage);
        alert.setTitle(name);
        alert.setHeaderText(name);
        alert.setContentText(body);

        // Set the modality of the Alert window to APPLICATION_MODAL
        alert.initModality(Modality.APPLICATION_MODAL);

        // Show the Alert window
        alert.showAndWait();
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