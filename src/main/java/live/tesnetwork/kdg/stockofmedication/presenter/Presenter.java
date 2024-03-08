package live.tesnetwork.kdg.stockofmedication.presenter;

import javafx.scene.Parent;
import live.tesnetwork.kdg.stockofmedication.controller.DatabaseController;
import live.tesnetwork.kdg.stockofmedication.controller.MedicationController;
import live.tesnetwork.kdg.stockofmedication.entity.MedicationCategory;
import live.tesnetwork.kdg.stockofmedication.entity.User;
import live.tesnetwork.kdg.stockofmedication.entity.UserMedication;
import live.tesnetwork.kdg.stockofmedication.enums.TimeUnits;
import live.tesnetwork.kdg.stockofmedication.view.MainMenuView;
import live.tesnetwork.kdg.stockofmedication.view.ViewHelper;

import java.util.Arrays;

public class Presenter {
    public Presenter() {

    }

    public <T extends Parent & ViewHelper> void addDataTo(T view) {
        switch (view.getClass().getSimpleName()) {
            case "MainMenuView" -> MainView((MainMenuView) view);
        }
    }

    public MainMenuView MainView(MainMenuView mainMenuView) {
        User user = mainMenuView.getUser();

        mainMenuView.setMedicationList(
                MedicationController.getUserMedications(user.getUsername())
                        .stream()
                        .map(UserMedication::getName)
                        .toArray(String[]::new)
        );

        mainMenuView.setCategoryChoices(
                Arrays.stream(MedicationController.getAllMedicationCategories())
                        .map(MedicationCategory::toString)
                        .toArray(String[]::new)
        );
        mainMenuView.setTimeChoices(TimeUnits.getAllAsString().toArray(String[]::new));
        return mainMenuView;
    }
}
