package live.tesnetwork.kdg.stockofmedication.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Helper {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public static String formatLocalDateTime(LocalDateTime localDateTime) {
        return localDateTime.format(DATE_TIME_FORMATTER);
    }

    public static LocalDateTime parseLocalDateTime(String string) {
        return LocalDateTime.parse(string, DATE_TIME_FORMATTER);
    }

    public static LocalDateTime removeTimeAfterSecondes(LocalDateTime now) {
        return now.withSecond(0).withNano(0);
    }
}
