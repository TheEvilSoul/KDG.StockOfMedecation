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
    private static final Map<String, MedicationCategory> medicationCategoryCache = new HashMap<>();
    private static final Map<String, UserMedication> userMedicationCache = new HashMap<>();

    public static boolean createMedication(String name, String category) {
        return databaseController.createMedication(name, category);
    }

    public static boolean createMedicationCategory(String name) {
        return databaseController.createMedicationCategory(name);
    }

    public static boolean deleteMedication(String name) {
        medicationCache.remove(name);
        return databaseController.deleteMedicationByName(name);
    }

    public static void deleteMedicationCategory(String name) {
        medicationCategoryCache.remove(name);
        databaseController.deleteMedicationCategoryByName(name);
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

    public static MedicationCategory getMedicationCategory(String name) {
        MedicationCategory medicationCategory = medicationCategoryCache.getOrDefault(name, null);
        if (medicationCategory == null) {
            medicationCategory = databaseController.getMedicationCategoryByName(name);
            if (medicationCategory != null) {
                medicationCategoryCache.put(medicationCategory.getName(), medicationCategory);
            }
        }
        return medicationCategory;
    }

    public static List<MedicationCategory> getAllMedicationCategories() {
        return databaseController.getAllMedicationCategories();
    }

    public static UserMedication getUserMedication(String name) {
        UserMedication userMedication = userMedicationCache.getOrDefault(name, null);
        if (userMedication == null) {
            userMedication = databaseController.getUserMedication(name);
            if (userMedication != null) {
                userMedicationCache.put(userMedication.getName(), userMedication);
            }
        }
        return userMedication;
    }

    public static List<UserMedication> getUserMedications(Integer id) {
        return databaseController.getUserMedications(id);
    }

    public static boolean updateUserMedication(Integer id, UserMedication userMedication) {
        return databaseController.updateUserMedication(id, userMedication);
    }
}
