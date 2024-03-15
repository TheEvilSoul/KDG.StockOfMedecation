package live.tesnetwork.kdg.stockofmedication.logic;

import javafx.event.ActionEvent;
import live.tesnetwork.kdg.stockofmedication.StockOfMedicationApplication;
import live.tesnetwork.kdg.stockofmedication.controller.MedicationController;
import live.tesnetwork.kdg.stockofmedication.entity.Medication;
import live.tesnetwork.kdg.stockofmedication.entity.MedicationCategory;
import live.tesnetwork.kdg.stockofmedication.enums.Views;
import live.tesnetwork.kdg.stockofmedication.view.EditMedicationView;

public class EditMedicationViewHandler {
    public static void goBack(ActionEvent actionEvent) {
        StockOfMedicationApplication.switchView(Views.MAIN);
    }

    public static void changeMedicationObject(ActionEvent actionEvent) {
        StockOfMedicationApplication.switchView(Views.EDIT_USER_MEDICATION);
    }

    public static void save(EditMedicationView view) {
        Integer mg = -1;
        String name = view.getMedicationNameTextField().getText();
        String cat = view.getMedicationCategoryChoiceBox().getValue();
        String mgText = view.getMedicationMgTextField().getText();
        String recommendedDose = view.getMedicationRecommendedDoseTextField().getText();
        if (mgText != null && !mgText.isEmpty()) {
            mg = Integer.parseInt(mgText);
        }

        if (name.isEmpty() || cat.isEmpty() || cat.equals("Select a category")) {
            StockOfMedicationApplication.giveError("Empty fields", "Please fill in all fields.");
        } else {
            Medication medication = new Medication(name, MedicationCategory.valueOf(cat), mg, recommendedDose);
            if (MedicationController.saveMedication(medication)) {
                StockOfMedicationApplication.givePopup("Success", "The medication has been saved successfully.");
            } else {
                StockOfMedicationApplication.giveError("Error", "An error occurred while saving the medication. Please try again.");
            }

        }
    }
}
