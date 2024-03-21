package live.tesnetwork.kdg.stockofmedication.controller;

import live.tesnetwork.kdg.stockofmedication.model.Medication;
import live.tesnetwork.kdg.stockofmedication.model.MedicationCategory;
import live.tesnetwork.kdg.stockofmedication.model.UserMedication;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MedicationController {
    private static final DatabaseController databaseController = DatabaseController.getInstance();
    private static final Map<String, Medication> medicationCache = new HashMap<>();
    private static final Map<String, UserMedication> userMedicationCache = new HashMap<>();

    public static boolean saveMedication(Medication medication) {
        return databaseController.saveMedication(medication);
    }

    public static boolean deleteMedication(String name) {
        medicationCache.remove(name);
        return databaseController.deleteMedication(name);
    }

    @Nullable
    public static Medication getMedication(String name) {
        Medication medication = medicationCache.getOrDefault(name, null);
        if (medication == null) {
            medication = databaseController.getMedication(name);
            if (medication != null) {
                medicationCache.put(medication.getName(), medication);
            }
        }
        return medication;
    }

    public static List<Medication> getMedications() {
        return databaseController.getMedications();
    }

    public static MedicationCategory[] getAllMedicationCategories() {
        return MedicationCategory.values();
    }

    public static UserMedication getUserMedication(String id, String name) {
        UserMedication userMedication = userMedicationCache.getOrDefault(name, null);
        if (userMedication == null) {
            userMedication = databaseController.getUserMedication(id, name);
            if (userMedication != null) {
                userMedicationCache.put(userMedication.getMedication().getName(), userMedication);
            }
        }
        return userMedication;
    }

    public static List<UserMedication> getUserMedications(String id) {
        return databaseController.getUserMedications(id);
    }

    public static boolean saveUserMedication(String id, UserMedication userMedication) {
        return databaseController.saveUserMedication(id, userMedication);
    }

    public static void deleteUserMedication(String Id, UserMedication userMedication) {
        databaseController.deleteUserMedication(Id, userMedication);
    }

    public static void updateStockFromTakeIn(String id, boolean canGoNegative) {
        databaseController.updateStockFromTakeIn(id, canGoNegative);
    }
}
