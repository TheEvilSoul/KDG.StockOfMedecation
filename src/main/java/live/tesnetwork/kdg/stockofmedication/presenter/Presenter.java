package live.tesnetwork.kdg.stockofmedication.presenter;

import javafx.scene.Parent;
import live.tesnetwork.kdg.stockofmedication.StockOfMedicationApplication;
import live.tesnetwork.kdg.stockofmedication.controller.MedicationController;
import live.tesnetwork.kdg.stockofmedication.entity.Medication;
import live.tesnetwork.kdg.stockofmedication.entity.MedicationCategory;
import live.tesnetwork.kdg.stockofmedication.entity.User;
import live.tesnetwork.kdg.stockofmedication.entity.UserMedication;
import live.tesnetwork.kdg.stockofmedication.enums.TimeUnits;
import live.tesnetwork.kdg.stockofmedication.view.EditMedicationView;
import live.tesnetwork.kdg.stockofmedication.view.EditUserMedicationView;
import live.tesnetwork.kdg.stockofmedication.view.MainMenuView;
import live.tesnetwork.kdg.stockofmedication.view.ViewHelper;

import java.util.Arrays;

public class Presenter {
    public Presenter() {

    }

    public <T extends Parent & ViewHelper> void addDataTo(T view) {
        switch (view.getClass().getSimpleName()) {
            case "MainMenuView" -> MainView((MainMenuView) view);
            case "EditUserMedicationView" -> editUserMedication((EditUserMedicationView) view);
            case "EditMedicationView" -> editMedication((EditMedicationView) view);
        }
    }

    public MainMenuView MainView(MainMenuView mainMenuView) {
        User user = StockOfMedicationApplication.getUser();

        mainMenuView.setMedicationList(
                MedicationController.getUserMedications(user.getUsername())
                        .stream()
                        .map(UserMedication::getDetails)
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

    public void editUserMedication(EditUserMedicationView view) {
        view.setMedicationNameList(
                MedicationController.getMedications()
                        .stream()
                        .map(Medication::getFullName)
                        .toArray(String[]::new)
        );
    }

    public void editMedication(EditMedicationView view) {
        view.setMedicationCategoryList(
                Arrays.stream(MedicationController.getAllMedicationCategories())
                        .map(MedicationCategory::toString)
                        .toArray(String[]::new)
        );
    }
}
