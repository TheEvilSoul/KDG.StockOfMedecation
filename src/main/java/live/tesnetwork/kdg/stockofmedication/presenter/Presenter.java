package live.tesnetwork.kdg.stockofmedication.presenter;

import javafx.scene.Parent;
import live.tesnetwork.kdg.stockofmedication.StockOfMedicationApplication;
import live.tesnetwork.kdg.stockofmedication.controller.MedicationController;
import live.tesnetwork.kdg.stockofmedication.entity.Medication;
import live.tesnetwork.kdg.stockofmedication.entity.MedicationCategory;
import live.tesnetwork.kdg.stockofmedication.entity.User;
import live.tesnetwork.kdg.stockofmedication.entity.UserMedication;
import live.tesnetwork.kdg.stockofmedication.enums.TimeUnits;
import live.tesnetwork.kdg.stockofmedication.logic.MainMenuViewHandler;
import live.tesnetwork.kdg.stockofmedication.utils.Convertable;
import live.tesnetwork.kdg.stockofmedication.view.EditMedicationView;
import live.tesnetwork.kdg.stockofmedication.view.EditUserMedicationView;
import live.tesnetwork.kdg.stockofmedication.view.MainMenuView;
import live.tesnetwork.kdg.stockofmedication.view.ViewHelper;

import java.util.Arrays;

public class Presenter {
    public Presenter() {

    }

    public <T extends Parent & ViewHelper> void addDataTo(T view, Convertable data) {
        switch (view.getClass().getSimpleName()) {
            case "MainMenuView" -> MainView((MainMenuView) view, data);
            case "EditUserMedicationView" -> editUserMedication((EditUserMedicationView) view, data);
            case "EditMedicationView" -> editMedication((EditMedicationView) view, data);
        }
    }

    public MainMenuView MainView(MainMenuView mainMenuView, Convertable data) {
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
        mainMenuView.setTimeChoices(Arrays.stream(TimeUnits.values()).map(TimeUnits::toString).toArray(String[]::new));
        mainMenuView.updateListView();
        MainMenuViewHandler.toggleMedicationObject(mainMenuView);
        return mainMenuView;
    }

    public void editUserMedication(EditUserMedicationView view, Convertable data) {
        view.setMedicationNameList(
                MedicationController.getMedications()
                        .stream()
                        .map(Medication::getFullName)
                        .toArray(String[]::new)
        );
        view.setMedicationTimeUnitList(
                Arrays.stream(TimeUnits.values())
                        .map(TimeUnits::toString)
                        .toArray(String[]::new)
        );
        if (data != null && data instanceof UserMedication) {
            UserMedication userMedication = (UserMedication) data;
            view.getMedicationNameChoiceBox().setValue(userMedication.getMedication().getFullName());
            view.getMedicationStockTextField().setText(userMedication.getStock().toString());
            view.getMedicationAmountPerTimeUnitTextField().setText(userMedication.getAmountPerTimeUnit().toString());
            view.getMedicationAmountOfTimeUnitTextField().setText(userMedication.getAmountOfTimeUnit().toString());
            view.getMedicationTimeUnitChoiceBox().setValue(userMedication.getTimeUnit().toString());
        }
    }

    public void editMedication(EditMedicationView view, Convertable data) {
        view.setMedicationCategoryList(
                Arrays.stream(MedicationController.getAllMedicationCategories())
                        .map(MedicationCategory::toString)
                        .toArray(String[]::new)
        );
        if (data != null && data instanceof Medication) {
            Medication medication = (Medication) data;
            view.getMedicationNameTextField().setText(medication.getName());
            view.getMedicationCategoryChoiceBox().setValue(medication.getCategory().toString());
            view.getMedicationMgTextField().setText(medication.getMg().toString());
            view.getMedicationRecommendedDoseTextField().setText(medication.getRecommendedDosage());
        }
    }
}
