package live.tesnetwork.kdg.stockofmedication.enums;

import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;


public enum TimeUnits {
    HOURS(60, ChronoUnit.HOURS),
    DAYS(24 * HOURS.minutes, ChronoUnit.DAYS),
    WEEKS(7 * DAYS.minutes, ChronoUnit.WEEKS),
    MONTHS(30 * DAYS.minutes, ChronoUnit.MONTHS);

    private final int minutes;
    private final TemporalUnit unit;

    TimeUnits(int minutes, TemporalUnit unit) {
        this.minutes = minutes;
        this.unit = unit;
    }

    public int getMinutes() {
        return minutes;
    }

    public TemporalUnit getUnit() {
        return unit;
    }
}
