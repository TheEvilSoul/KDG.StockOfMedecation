package live.tesnetwork.kdg.stockofmedication.entity;

import live.tesnetwork.kdg.stockofmedication.controller.MedicationController;
import live.tesnetwork.kdg.stockofmedication.enums.TimeUnits;
import live.tesnetwork.kdg.stockofmedication.utils.Convertable;
import live.tesnetwork.kdg.stockofmedication.utils.Helper;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.util.Map;

public class UserMedication implements Convertable {
    private final Medication medication;
    private Integer stock;
    @Nullable
    private TimeUnits timeUnit;
    private Integer amountOfTimeUnit;
    private Integer amountPerTimeUnit;
    private LocalDateTime lastTaken;

    public UserMedication(Medication medication, Integer stock, TimeUnits timeUnit, Integer amountPerTimeUnit, Integer amountOfTimeUnit) {
        this(medication, stock, timeUnit, amountOfTimeUnit, amountPerTimeUnit, LocalDateTime.now());
    }
    public UserMedication(Medication medication, Integer stock, TimeUnits timeUnit, Integer amountOfTimeUnit, Integer amountPerTimeUnit, LocalDateTime lastTaken) {
        this.medication = medication;
        this.stock = stock;
        this.timeUnit = timeUnit;
        this.amountOfTimeUnit = amountOfTimeUnit;
        this.amountPerTimeUnit = amountPerTimeUnit;
        this.lastTaken = lastTaken;
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

    public Integer getAmountPerTimeUnit() {
        return amountPerTimeUnit;
    }

    public TimeUnits getTimeUnit() {
        return timeUnit;
    }

    public LocalDateTime getLastTaken() {
        return lastTaken;
    }

    public UserMedication setStock(Integer stock) {
        this.stock = stock;
        return this;
    }

    public UserMedication setAmountPerTimeUnit(Integer amountPerTimeUnit) {
        this.amountPerTimeUnit = amountPerTimeUnit;
        return this;
    }

    public UserMedication setTimeUnit(TimeUnits timeUnit) {
        this.timeUnit = timeUnit;
        return this;
    }

    public UserMedication setLastTakenToNow() {
        return setLastTaken(LocalDateTime.now());
    }

    public Integer getAmountOfTimeUnit() {
        return amountOfTimeUnit;
    }

    public UserMedication setLastTaken(LocalDateTime lastTaken) {
        this.lastTaken = lastTaken;
        return this;
    }

    public UserMedication setAmountOfTimeUnit(Integer amountOfTimeUnit) {
        this.amountOfTimeUnit = amountOfTimeUnit;
        return this;
    }

    @Override
    public Map<String, String> toMap() {
        if (timeUnit == null) {
            return Map.of(
                    "fullName", medication.getFullName(),
                    "stock", stock.toString(),
                    "amountPerTimeUnit", amountPerTimeUnit.toString(),
                    "lastTaken", Helper.formatLocalDateTime(lastTaken)
            );
        }
        return Map.of(
                "fullName", medication.getFullName(),
                "stock", stock.toString(),
                "timeUnit", timeUnit.toString(),
                "amountOfTimeUnit", amountOfTimeUnit.toString(),
                "amountPerTimeUnit", amountPerTimeUnit.toString(),
                "lastTaken", Helper.formatLocalDateTime(lastTaken)
        );
    }

    public static UserMedication fromMap(Map<String, String> map) {
        TimeUnits timeUnits = null;
        Integer amountOfTimeUnit = 0;
        if (map.containsKey("timeUnit")) timeUnits = TimeUnits.valueOf(map.get("timeUnit"));
        if (map.containsKey("amountOfTimeUnit")) amountOfTimeUnit = Integer.parseInt(map.get("amountOfTimeUnit"));
        return new UserMedication(
                MedicationController.getMedication(map.get("fullName")),
                Integer.parseInt(map.get("stock")),
                timeUnits,
                amountOfTimeUnit,
                Integer.parseInt(map.get("amountPerTimeUnit")),
                Helper.parseLocalDateTime(map.get("lastTaken")));
    }

    @Override
    public String toString() {
        return toMap().toString();
    }

    public boolean hasToBeTakenIn(Integer time, TimeUnits timeUnit, LocalDateTime now) {
        LocalDateTime lastTaken = this.lastTaken.plus(amountOfTimeUnit, this.timeUnit.getUnit());
        return lastTaken.isBefore(now.plus(time, timeUnit.getUnit())) && lastTaken.isAfter(now);
    }
}
