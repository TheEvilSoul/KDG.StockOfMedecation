package live.tesnetwork.kdg.stockofmedication.controller;

import live.tesnetwork.kdg.stockofmedication.entity.Medication;
import live.tesnetwork.kdg.stockofmedication.entity.MedicationCategory;
import live.tesnetwork.kdg.stockofmedication.entity.User;
import live.tesnetwork.kdg.stockofmedication.entity.UserMedication;
import live.tesnetwork.kdg.stockofmedication.enums.TimeUnits;
import live.tesnetwork.kdg.stockofmedication.utils.Config;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileDatabaseControllerTest {
    private static DatabaseController controller;
    private static Config config;
    private static UserMedication userMedication;
    private static Medication medication;
    private static String existingUser = "ExistingUser";
    private static String nonExistingUser = "NonExistingUser";
    private static String testMedicationName = "testMedication";
    private static String testNonExistingMedicationName = "testNonExistingMedication";

    @BeforeAll
    public static void setUp() {
        config = new Config();
        controller = new FileDatabaseController(config);
        medication = new Medication(testMedicationName, MedicationCategory.ALL, 10, "test med");
        userMedication = new UserMedication(medication, 5, TimeUnits.DAYS, 2, 1, LocalDateTime.now());
    }

    @AfterAll
    public static void tearDown() {
        controller.deleteMedication(testMedicationName);
    }

    @Test
    public void saveMedicationSuccessfully() {
        assertTrue(controller.saveMedication(medication));
    }

    @Test
    public void deleteExistingMedication() {
        controller.saveMedication(medication);
        assertTrue(controller.deleteMedication(testMedicationName));
    }

    @Test
    public void deleteNonExistingMedication() {
        assertTrue(controller.deleteMedication(testNonExistingMedicationName));
    }

    @Test
    public void getExistingMedication() {
        controller.saveMedication(medication);
        assertNotNull(controller.getMedication(testMedicationName));
        controller.deleteMedication(testMedicationName);
    }

    @Test
    public void getNonExistingMedication() {
        assertNull(controller.getMedication(testNonExistingMedicationName));
    }

    @Test
    public void getUserMedicationForExistingUserAndMedication() {
        controller.saveUserMedication(existingUser, userMedication);
        assertNotNull(controller.getUserMedication(existingUser, testMedicationName));
        controller.deleteUserMedication(existingUser, userMedication);
    }

    @Test
    public void getUserMedicationForNonExistingUserOrMedication() {
        assertNull(controller.getUserMedication(nonExistingUser, testNonExistingMedicationName));
    }

    @Test
    public void saveUserMedicationSuccessfully() {
        assertTrue(controller.saveUserMedication(existingUser, userMedication));
    }

    @Test
    public void deleteUserMedicationSuccessfully() {
        controller.saveUserMedication(existingUser, userMedication);
        assertTrue(controller.deleteUserMedication(existingUser, userMedication));
    }

    @Test
    public void getUserByUsernameExistingUser() {
        assertNotNull(controller.getUserByUsername(existingUser));
    }

    @Test
    public void getUserByUsernameNonExistingUser() {
        assertNull(controller.getUserByUsername(nonExistingUser));
    }

    @Test
    public void createUserSuccessfully() {
        assertTrue(controller.createUser("NewUser", "NewPassword"));
        controller.deleteUser("NewUser");
    }

    @Test
    public void createUserWithExistingUsername() {
        assertFalse(controller.createUser(existingUser, existingUser));
    }
}