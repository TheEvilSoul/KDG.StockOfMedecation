package live.tesnetwork.kdg.stockofmedication.entity;

import live.tesnetwork.kdg.stockofmedication.utils.Convertable;

import java.util.Map;

public class Medication implements Convertable {

    private String name;

    public Medication(String name) {
        this.name=name;
    }

    public static Medication fromMap(Map<String, String> map) {
        return new Medication(map.get("name"));
    }

    public String getName() {
        return name;
    }


    public static Medication create(String name) {
        return new Medication(name);

    }

    @Override
    public Map<String, String> toMap() {
        return Map.of(
                "name", name
        );
    }
}
