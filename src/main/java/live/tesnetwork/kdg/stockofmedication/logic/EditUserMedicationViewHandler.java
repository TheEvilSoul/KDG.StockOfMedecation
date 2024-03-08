package live.tesnetwork.kdg.stockofmedication.logic;

import javafx.event.ActionEvent;
import live.tesnetwork.kdg.stockofmedication.StockOfMedicationApplication;
import live.tesnetwork.kdg.stockofmedication.controller.MedicationController;
import live.tesnetwork.kdg.stockofmedication.entity.Medication;
import live.tesnetwork.kdg.stockofmedication.entity.User;
import live.tesnetwork.kdg.stockofmedication.entity.UserMedication;
import live.tesnetwork.kdg.stockofmedication.enums.Views;
import live.tesnetwork.kdg.stockofmedication.view.EditUserMedicationView;

public class EditUserMedicationViewHandler {
    public static void goBack(ActionEvent actionEvent) {
        StockOfMedicationApplication.switchView(Views.MAIN);
    }

    public static void changeMedicationObject(ActionEvent actionEvent) {
        StockOfMedicationApplication.switchView(Views.EDIT_MEDICATION);
    }

    public static void save(EditUserMedicationView view) {
        User user = StockOfMedicationApplication.getUser();
        Integer stock = 0;
        String med = view.getMedicationNameChoiceBox().getValue();
        String stockT = view.getMedicationStockTextField().getText();
        if (!stockT.isEmpty()) stock = Integer.parseInt(stockT);
        if (med == null || med.isEmpty()) {
            StockOfMedicationApplication.giveError("Medication not selected", "Please select a medication.");
            return;
        }
        Medication medication = MedicationController.getMedication(med);
        MedicationController.saveUserMedication(user.getUsername(), new UserMedication(medication, stock));
        StockOfMedicationApplication.givePopup("Medication saved", "The medication has been saved successfully.");
    }
}
