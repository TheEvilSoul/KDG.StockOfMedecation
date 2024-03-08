package live.tesnetwork.kdg.stockofmedication.enums;

import java.util.List;

public enum TimeUnits {
    HOURS,
    DAYS,
    WEEKS,
    MONTHS;

    public static List<String> getAllAsString() {
        return List.of("Hours", "Days", "Weeks", "Months");
    }
}
