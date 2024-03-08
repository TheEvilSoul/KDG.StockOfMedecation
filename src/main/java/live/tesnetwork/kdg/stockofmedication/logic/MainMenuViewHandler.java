package live.tesnetwork.kdg.stockofmedication.logic;

import javafx.event.ActionEvent;
import live.tesnetwork.kdg.stockofmedication.StockOfMedicationApplication;
import live.tesnetwork.kdg.stockofmedication.enums.Views;
import live.tesnetwork.kdg.stockofmedication.view.MainMenuView;

public class MainMenuViewHandler {
    public static void search(MainMenuView mainMenuView) {

    }

    public static void addMedication(ActionEvent actionEvent) {
        StockOfMedicationApplication.switchView(Views.EDIT_USER_MEDICATION);
    }

    public static void logout(ActionEvent actionEvent) {
        StockOfMedicationApplication.setUser(null);
        StockOfMedicationApplication.switchView(Views.LOGIN);
    }
}
