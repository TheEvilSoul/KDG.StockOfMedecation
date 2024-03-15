package live.tesnetwork.kdg.stockofmedication.controller;

import live.tesnetwork.kdg.stockofmedication.entity.Medication;
import live.tesnetwork.kdg.stockofmedication.entity.User;
import live.tesnetwork.kdg.stockofmedication.entity.UserMedication;
import live.tesnetwork.kdg.stockofmedication.utils.Config;

import java.util.List;

public interface DatabaseController {
    DatabaseController INSTANCE = new FileDatabaseController(new Config());

    static DatabaseController getInstance() {
        return INSTANCE;
    }

    boolean saveMedication(Medication medication);

    boolean deleteMedication(String name);

    Medication getMedication(String name);

    List<Medication> getMedications();

    UserMedication getUserMedication(String id, String name);

    List<UserMedication> getUserMedications(String id);

    boolean saveUserMedication(String id, UserMedication userMedication);
    boolean deleteUserMedication(String id, UserMedication userMedication);

    void close();

    User getUserByUsername(String username);

    boolean createUser(String username, String password);

    void updateStockFromTakeIn(String id, boolean canGoNegative);

    boolean deleteUser(String newUser);
}
