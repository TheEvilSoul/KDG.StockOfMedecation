package live.tesnetwork.kdg.stockofmedication.presenter;

import live.tesnetwork.kdg.stockofmedication.controller.MedicationController;
import live.tesnetwork.kdg.stockofmedication.entity.MedicationCategory;
import live.tesnetwork.kdg.stockofmedication.entity.UserMedication;
import live.tesnetwork.kdg.stockofmedication.enums.TimeUnits;
import live.tesnetwork.kdg.stockofmedication.view.MainMenuView;

import java.util.List;

public class Presenter {
    public static void MainView(List<UserMedication> userMedicationList, MainMenuView mainMenuView) {
        mainMenuView.setMedicationList(userMedicationList.stream()
                .map(UserMedication::getName)
                .toArray(String[]::new)
        );
        mainMenuView.setCategoryChoices(MedicationController
                .getAllMedicationCategories()
                .stream()
                .map(MedicationCategory::toString)
                .toArray(String[]::new)
        );
        mainMenuView.setTimeChoices(TimeUnits.getAllAsString().toArray(String[]::new));
    }
}
