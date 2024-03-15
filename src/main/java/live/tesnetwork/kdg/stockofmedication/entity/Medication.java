package live.tesnetwork.kdg.stockofmedication.entity;

import live.tesnetwork.kdg.stockofmedication.utils.Convertable;

import java.util.Map;

public class Medication implements Convertable {

    private final String name;
    private final MedicationCategory category;
    private final Integer mg;
    private final String recommendedDosage;

    public Medication(String name, MedicationCategory category, Integer mg, String recommendedDosage) {
        this.name=name;
        this.category = category;
        this.mg = mg;
        this.recommendedDosage = recommendedDosage;
    }

    public MedicationCategory getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getFullName() {
        return "%s %d mg".formatted(name, mg);
    }

    public Integer getMg() {
        return mg;
    }


    public String getRecommendedDosage() {
        return recommendedDosage;
    }

    public static Medication fromMap(Map<String, String> map) {
        return new Medication(
                map.get("name"),
                MedicationCategory.valueOf(map.get("category")),
                Integer.parseInt(map.get("mg")),
                map.get("recommendedDosage")
        );
    }

    @Override
    public Map<String, String> toMap() {
        return Map.of(
                "name", name,
                "category", category.name(),
                "mg", mg.toString(),
                "recommendedDosage", recommendedDosage
        );
    }
}
