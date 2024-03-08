package live.tesnetwork.kdg.stockofmedication.controller;

import live.tesnetwork.kdg.stockofmedication.entity.Medication;
import live.tesnetwork.kdg.stockofmedication.entity.MedicationCategory;
import live.tesnetwork.kdg.stockofmedication.entity.UserMedication;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MedicationController {
    private static final DatabaseController databaseController = DatabaseController.getInstance();
    private static final Map<String, Medication> medicationCache = new HashMap<>();
    private static final Map<String, UserMedication> userMedicationCache = new HashMap<>();

    public static boolean createMedication(Medication medication) {
        return databaseController.createMedication(medication);
    }

    public static boolean deleteMedication(String name) {
        medicationCache.remove(name);
        return databaseController.deleteMedicationByName(name);
    }

    @Nullable
    public static Medication getMedication(String name) {
        Medication medication = medicationCache.getOrDefault(name, null);
        if (medication == null) {
            medication = databaseController.getMedicationByName(name);
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
                userMedicationCache.put(userMedication.getName(), userMedication);
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
}
