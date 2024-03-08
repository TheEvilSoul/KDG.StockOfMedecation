package live.tesnetwork.kdg.stockofmedication.entity;

public class MedicationCategory {
    public static final MedicationCategory ALL = new MedicationCategory(0, "All");
    private final Integer id;
    private String name;

    public MedicationCategory(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MedicationCategory) {
            return ((MedicationCategory) obj).getName().equals(getName());
        }
        return false;
    }
}
