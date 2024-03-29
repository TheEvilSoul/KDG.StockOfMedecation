package live.tesnetwork.kdg.stockofmedication.logic;

import javafx.event.ActionEvent;
import live.tesnetwork.kdg.stockofmedication.StockOfMedicationApplication;
import live.tesnetwork.kdg.stockofmedication.controller.MedicationController;
import live.tesnetwork.kdg.stockofmedication.model.Medication;
import live.tesnetwork.kdg.stockofmedication.model.MedicationCategory;
import live.tesnetwork.kdg.stockofmedication.model.User;
import live.tesnetwork.kdg.stockofmedication.model.UserMedication;
import live.tesnetwork.kdg.stockofmedication.enums.TimeUnits;
import live.tesnetwork.kdg.stockofmedication.enums.Views;
import live.tesnetwork.kdg.stockofmedication.view.MainMenuView;

import java.time.LocalDateTime;

public class MainMenuViewHandler {

    private static final String SHOW_MEDICATION = "Switch to Medication";

    private MainMenuViewHandler() {
    }
    public static void search(MainMenuView view) {
        int stock = -1;
        int time;
        String catT = view.getCategoryChoiceBox().getSelectionModel().getSelectedItem();
        if (catT == null || catT.isEmpty()) catT = "ALL";
        MedicationCategory cat = MedicationCategory.valueOf(catT);
        String stockT = view.getTextFieldShowOnlyMedicationBelow().getText();
        if (!stockT.isEmpty()) {
            stock = Integer.parseInt(stockT);
            if (stock < -1) stock = -1;
        }
        String timeT = view.getTextFieldShowOnlyMedicationToTakeIn().getText();
        String timeUnitT = view.getChoiceBoxShowOnlyMedicationToTakeIn().getValue();
        if (timeUnitT == null || timeUnitT.isEmpty()) timeUnitT = "HOURS";
        TimeUnits timeUnit = TimeUnits.valueOf(timeUnitT);
        if (!timeT.isEmpty()) time = Integer.parseInt(timeT);
        else {
            time = -1;
        }
        if (view.getButtonToggleMedicationObject().getText().equals(SHOW_MEDICATION)) {
            User user = StockOfMedicationApplication.getUser();
            Integer finalStock = stock;
            LocalDateTime now = LocalDateTime.now();
            view.setMedicationList(
                    MedicationController.getUserMedications(user.getUsername())
                            .stream()
                            .filter(userMedication -> cat.equals(MedicationCategory.ALL) || userMedication.getMedication().getCategory().equals(cat))
                            .filter(userMedication -> finalStock == -1 || userMedication.getStock() <= finalStock)
                            .filter(userMedication -> time == -1 || userMedication.hasToBeTakenIn(time, timeUnit, now))
                            .map(UserMedication::getDetails)
                            .toArray(String[]::new)
            );
        } else {
            view.setMedicationList(
                    MedicationController.getMedications()
                            .stream()
                            .filter(medication -> cat.equals(MedicationCategory.ALL) || medication.getCategory().equals(cat))
                            .map(Medication::getFullName)
                            .toArray(String[]::new)
            );
        }
    }

    public static void addMedication(ActionEvent actionEvent) {
        StockOfMedicationApplication.switchView(Views.EDIT_USER_MEDICATION);
    }

    public static void logout(ActionEvent actionEvent) {
        StockOfMedicationApplication.setUser(null);
        StockOfMedicationApplication.switchView(Views.LOGIN);
    }

    public static void editMedication(MainMenuView mainMenuView) {
        User user = StockOfMedicationApplication.getUser();
        String id = mainMenuView.getMedicationListView().getSelectionModel().getSelectedItem();
        if (id == null) {
            StockOfMedicationApplication.giveError("No medication selected", "Please select a medication to edit.");
        } else {
            if (mainMenuView.getButtonToggleMedicationObject().getText().equals(SHOW_MEDICATION)) {
                UserMedication userMedication = MedicationController.getUserMedication(user.getUsername(), id.split(" \\| ")[0]);
                StockOfMedicationApplication.switchView(Views.EDIT_USER_MEDICATION, userMedication);
            } else {
                Medication medication = MedicationController.getMedication(id);
                StockOfMedicationApplication.switchView(Views.EDIT_MEDICATION, medication);
            }
        }
    }

    public static void deleteMedication(MainMenuView view) {
        User user = StockOfMedicationApplication.getUser();
        String id = view.getMedicationListView().getSelectionModel().getSelectedItem();
        if (id == null) {
            StockOfMedicationApplication.giveError("No medication selected", "Please select a medication to delete.");
        } else {
            if (view.getButtonToggleMedicationObject().getText().equals(SHOW_MEDICATION)) {
                UserMedication userMedication = MedicationController.getUserMedication(user.getUsername(), id.split(" \\| ")[0]);
                MedicationController.deleteUserMedication(user.getUsername(), userMedication);
                view.getMedicationListView().getItems().remove(id);
            } else {
                MedicationController.deleteMedication(id);
                view.getMedicationListView().getItems().remove(id);
            }
        }
    }

    public static void toggleMedicationObject(MainMenuView view) {
        if (view.getButtonToggleMedicationObject().getText().equals(SHOW_MEDICATION)) {
            view.getButtonToggleMedicationObject().setText("Switch to UserMedication");
            view.getTextFieldShowOnlyMedicationBelow().setDisable(true);
            view.getTextFieldShowOnlyMedicationToTakeIn().setDisable(true);
            StockOfMedicationApplication.setTitle("Medication view");
            view.getMedicationListLabel().setText("Medication list:");
            view.getMedicationListView().setStyle("-fx-background-color: red;");
            search(view);
        } else {
            view.getButtonToggleMedicationObject().setText(SHOW_MEDICATION);
            view.getTextFieldShowOnlyMedicationBelow().setDisable(false);
            view.getTextFieldShowOnlyMedicationToTakeIn().setDisable(false);
            StockOfMedicationApplication.setTitle("User medication view");
            view.getMedicationListLabel().setText("User medication list:");
            view.getMedicationListView().setStyle("-fx-background-color: blue;");
            search(view);
        }
    }
}
