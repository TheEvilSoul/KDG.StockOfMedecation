package live.tesnetwork.kdg.stockofmedication.entity;

import live.tesnetwork.kdg.stockofmedication.enums.TimeUnits;
import live.tesnetwork.kdg.stockofmedication.utils.Helper;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserMedicationTest {
    private static final LocalDateTime now = Helper.removeTimeAfterSecondes(LocalDateTime.now());
    private static final Medication medication = new Medication("TestMedication", MedicationCategory.ALL, 10, "1 pill per day");
    private static final UserMedication userMedication = new UserMedication(medication, 10, TimeUnits.DAYS, 2, 1, now);


    @Test
    public void createUserMedicationSuccessfully() {
        assertNotNull(userMedication);
    }

    @Test
    public void getStockReturnsCorrectStock() {
        assertEquals(10, userMedication.getStock());
    }

    @Test
    public void getMedicationReturnsCorrectMedication() {
        assertEquals(medication, userMedication.getMedication());
    }

    @Test
    public void getFullNameReturnsCorrectFullName() {
        assertEquals("TestMedication 10 mg", userMedication.getFullName());
    }

    @Test
    public void getDetailsReturnsCorrectDetails() {
        assertEquals("TestMedication 10 mg | 10", userMedication.getDetails());
    }

    @Test
    public void fromMapCreatesCorrectUserMedication() {
        Map<String, String> map = Map.of(
                "fullName", "testMedication 10 mg",
                "stock", "10",
                "timeUnit", "DAYS",
                "amountOfTimeUnit", "2",
                "amountPerTimeUnit", "1",
                "lastTaken", Helper.formatLocalDateTime(now)
        );
        UserMedication userMedication = UserMedication.fromMap(map);
        assertEquals("testMedication 10 mg", userMedication.getFullName());
        assertEquals(10, userMedication.getStock());
        assertEquals(TimeUnits.DAYS, userMedication.getTimeUnit());
        assertEquals(2, userMedication.getAmountOfTimeUnit());
        assertEquals(1, userMedication.getAmountPerTimeUnit());
        assertEquals(now, userMedication.getLastTaken());
    }

    @Test
    public void toMapReturnsCorrectMap() {
        Map<String, String> map = userMedication.toMap();
        assertEquals("TestMedication 10 mg", map.get("fullName"));
        assertEquals("10", map.get("stock"));
        assertEquals("DAYS", map.get("timeUnit"));
        assertEquals("2", map.get("amountOfTimeUnit"));
        assertEquals("1", map.get("amountPerTimeUnit"));
        assertEquals(Helper.formatLocalDateTime(now), map.get("lastTaken"));
    }
}