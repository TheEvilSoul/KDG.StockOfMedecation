package live.tesnetwork.kdg.stockofmedication.logic;

import live.tesnetwork.kdg.stockofmedication.StockOfMedicationApplication;
import live.tesnetwork.kdg.stockofmedication.controller.DatabaseController;
import live.tesnetwork.kdg.stockofmedication.entity.User;
import live.tesnetwork.kdg.stockofmedication.enums.Views;
import live.tesnetwork.kdg.stockofmedication.utils.EncryptionHelper;
import live.tesnetwork.kdg.stockofmedication.view.LoginView;

public class LoginViewHandler {
    public static void login(LoginView view) {
        User user = DatabaseController.getInstance().getUserByUsername(view.getUsername());
        if (user != null && EncryptionHelper.checkPassword(view.getPassword(), user.getPassword())) {
            StockOfMedicationApplication.setUser(user);
            StockOfMedicationApplication.updateMedicationStockWithLastTaken();
            StockOfMedicationApplication.switchView(Views.MAIN);
        } else StockOfMedicationApplication.giveError("Invalid username or password", "There doesn't seem to be a user with that username and password. Please try again.");
    }

    public static void register(LoginView view) {
        if (DatabaseController.getInstance().createUser(view.getUsername(), view.getPassword())) {
            User user = DatabaseController.getInstance().getUserByUsername(view.getUsername());
            StockOfMedicationApplication.setUser(user);
            StockOfMedicationApplication.switchView(Views.MAIN);
        } else StockOfMedicationApplication.giveError("Registration failed", "That username is already taken. Please try again.");
    }

}
