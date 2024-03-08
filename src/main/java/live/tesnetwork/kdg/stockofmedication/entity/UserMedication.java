package live.tesnetwork.kdg.stockofmedication.entity;

import live.tesnetwork.kdg.stockofmedication.controller.MedicationController;
import live.tesnetwork.kdg.stockofmedication.utils.Convertable;

import java.util.Map;

public class UserMedication implements Convertable {


    private final Medication medication;

    private final Integer stock;

    public UserMedication(Medication medication, Integer stock) {
        this.medication = medication;
        this.stock = stock;
    }

    public Integer getStock() {
        return stock;
    }

    public Medication getMedication() {
        return medication;
    }

    @Override
    public String getFullName() {
        return medication.getFullName();
    }

    public String getDetails() {
        return medication.getFullName() + " | " + stock;
    }

    @Override
    public Map<String, String> toMap() {
        return Map.of(
                "fullName", medication.getFullName(),
                "stock", stock.toString()
        );
    }

    public static UserMedication fromMap(Map<String, String> map) {
        return new UserMedication(
                MedicationController.getMedication(map.get("fullName")),
                Integer.parseInt(map.get("stock"))
        );
    }
}
