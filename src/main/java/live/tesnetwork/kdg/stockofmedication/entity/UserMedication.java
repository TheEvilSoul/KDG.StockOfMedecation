package live.tesnetwork.kdg.stockofmedication.entity;

import java.util.Map;

public class UserMedication extends Medication {


    protected UserMedication(Medication medication) {
        super(medication.getName());
    }

    public static UserMedication create(Medication medication) {
        return new UserMedication(medication);
    }

    @Override
    public Map<String, String> toMap() {
        return Map.of(
                "name", getName()
        );
    }

    public static UserMedication fromMap(Map<String, String> map) {
        return new UserMedication(Medication.fromMap(map));
    }
}
