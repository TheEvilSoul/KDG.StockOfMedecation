package live.tesnetwork.kdg.stockofmedication.utils;

import live.tesnetwork.kdg.stockofmedication.entity.Medication;
import live.tesnetwork.kdg.stockofmedication.entity.User;
import live.tesnetwork.kdg.stockofmedication.entity.UserMedication;

import java.util.Map;

public interface Convertable {
    Map<String, String> toMap();

    static <T extends Convertable> T to(Map<String, String> map, Class<T> object) {
        switch (object.getSimpleName()) {
            case "Medication":
                return (T) Medication.fromMap(map);
            case "User":
                return (T) User.fromMap(map);
            case "UserMedication":
                return (T) UserMedication.fromMap(map);
            default:
                throw new IllegalArgumentException("Invalid class type provided");
        }
    }
}
