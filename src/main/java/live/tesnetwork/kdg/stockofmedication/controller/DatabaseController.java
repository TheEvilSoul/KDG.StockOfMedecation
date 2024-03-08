package live.tesnetwork.kdg.stockofmedication.controller;

import live.tesnetwork.kdg.stockofmedication.entity.MedicationCategory;
import live.tesnetwork.kdg.stockofmedication.entity.User;
import live.tesnetwork.kdg.stockofmedication.utils.Config;

public interface DatabaseController {
    DatabaseController INSTANCE = new MySQLDatabaseController(new Config());

    static DatabaseController getInstance() {
        return INSTANCE;
    }

    void close();

    User getUserByUsername(String username);

    default boolean createUser(String name, String password) {
        return createUser(name, password, false);
    }
    boolean createUser(String name, String password, boolean isAHash);

    boolean deleteUserByUsername(String name);

    boolean createMedicationCategory(String medicationCategoryName);

    MedicationCategory getMedicationCategoryByName(String medicationCategoryName);
}
