package live.tesnetwork.kdg.stockofmedication.logic;

import javafx.event.ActionEvent;
import live.tesnetwork.kdg.stockofmedication.StockOfMedicationApplication;
import live.tesnetwork.kdg.stockofmedication.controller.MedicationController;
import live.tesnetwork.kdg.stockofmedication.entity.Medication;
import live.tesnetwork.kdg.stockofmedication.entity.User;
import live.tesnetwork.kdg.stockofmedication.entity.UserMedication;
import live.tesnetwork.kdg.stockofmedication.enums.TimeUnits;
import live.tesnetwork.kdg.stockofmedication.enums.Views;
import live.tesnetwork.kdg.stockofmedication.view.EditUserMedicationView;

import java.time.LocalDateTime;

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
        Integer amountPerTimeUnit = 0;
        Integer amountOfTimeUnit = 0;
        TimeUnits timeUnit = TimeUnits.MONTHS;
        String med = view.getMedicationNameChoiceBox().getValue();
        String stockT = view.getMedicationStockTextField().getText();
        String timeUnitT = view.getMedicationTimeUnitChoiceBox().getValue();
        String amountPerTimeUnitT = view.getMedicationAmountPerTimeUnitTextField().getText();
        String amountOfTimeUnitT = view.getMedicationAmountOfTimeUnitTextField().getText();
        boolean updateLastTakenToNow = view.getMedicationUpdateToNowCheckBox().isSelected();
        if (!amountPerTimeUnitT.isEmpty()) amountPerTimeUnit = Integer.parseInt(amountPerTimeUnitT);
        if (!amountOfTimeUnitT.isEmpty()) amountOfTimeUnit = Integer.parseInt(amountOfTimeUnitT);
        if (timeUnitT != null && !timeUnitT.isEmpty() && timeUnitT.equals("none")) timeUnit = TimeUnits.valueOf(timeUnitT);
        if (!stockT.isEmpty()) stock = Integer.parseInt(stockT);
        if (med == null || med.isEmpty()) {
            StockOfMedicationApplication.giveError("Medication not selected", "Please select a medication.");
            return;
        }

        UserMedication userMedication = MedicationController.getUserMedication(user.getUsername(), med);
        if (userMedication == null) {
            Medication medication = MedicationController.getMedication(med);
            userMedication = new UserMedication(medication, stock, timeUnit, amountOfTimeUnit, amountPerTimeUnit, LocalDateTime.now());
        } else {
            userMedication
                    .setStock(stock)
                    .setAmountPerTimeUnit(amountPerTimeUnit)
                    .setAmountOfTimeUnit(amountPerTimeUnit)
                    .setTimeUnit(timeUnit);
            if (updateLastTakenToNow) userMedication.setLastTaken(LocalDateTime.now());
        }

        MedicationController.saveUserMedication(user.getUsername(), userMedication);
        StockOfMedicationApplication.givePopup("Medication saved", "The medication has been saved successfully.");
    }
}
