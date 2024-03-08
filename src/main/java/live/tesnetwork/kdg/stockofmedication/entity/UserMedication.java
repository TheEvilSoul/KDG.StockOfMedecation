package live.tesnetwork.kdg.stockofmedication.entity;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class UserMedication extends Medication {

    private static final List<UserMedication> cache = new ArrayList<>();

    protected UserMedication(Medication medication) {
        super(medication.getName());
    }

    public static UserMedication create(Medication medication) {
        return new UserMedication(medication);
    }

    @Nullable
    public static UserMedication get(String name) {
        return cache.stream().filter(medication -> medication.getName().equals(name)).findFirst().orElse(null);
    }
}
