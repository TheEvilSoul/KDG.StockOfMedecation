package live.tesnetwork.kdg.stockofmedication.entity;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Medication {
    private static final List<Medication> cache = new ArrayList<>();

    private String name;

    protected Medication(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }


    public static Medication create(String name) {
        Medication medication = new Medication(name);
        cache.add(medication);
        return medication;

    }

    @Nullable
    public static Medication get(String name) {
        return cache.stream().filter(medication -> medication.getName().equals(name)).findFirst().orElse(null);
    }
}
