package live.tesnetwork.kdg.stockofmedication.entity;

import live.tesnetwork.kdg.stockofmedication.model.Medication;
import live.tesnetwork.kdg.stockofmedication.model.MedicationCategory;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MedicationTest {

    private static final Medication medication = new Medication("TestMedication", MedicationCategory.ALL, 10, "1 pill per day");

    @Test
    public void createMedicationSuccessfully() {
        assertNotNull(medication);
    }

    @Test
    public void getFullNameReturnsCorrectFormat() {
        assertEquals("TestMedication 10 mg", medication.getFullName());
    }

    @Test
    public void fromMapCreatesCorrectMedication() {
        Map<String, String> map = Map.of(
                "name", "TestMedication",
                "category", "ALL",
                "mg", "10",
                "recommendedDosage", "1 pill per day"
        );
        Medication medication = Medication.fromMap(map);
        assertEquals("TestMedication", medication.getName());
        assertEquals(MedicationCategory.ALL, medication.getCategory());
        assertEquals(10, medication.getMg());
        assertEquals("1 pill per day", medication.getRecommendedDosage());
    }

    @Test
    public void toMapReturnsCorrectMap() {
        Map<String, String> map = medication.toMap();
        assertEquals("TestMedication", map.get("name"));
        assertEquals("ALL", map.get("category"));
        assertEquals("10", map.get("mg"));
        assertEquals("1 pill per day", map.get("recommendedDosage"));
    }
}