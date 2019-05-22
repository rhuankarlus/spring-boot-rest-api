package br.com.rk.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for date manipulation for tests
 *
 * @author Rhuan Karlus
 * @since 5/22/19
 */
public class DateTimeUtil {

    public static long getMilliseconds(final String dateStr) {
        return getMilliseconds(dateStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public static long getMilliseconds(final LocalDateTime dateTime) {
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static long getMilliseconds(final String dateStr, final DateTimeFormatter dateTimeFormatter) {
        return LocalDateTime.parse(dateStr, dateTimeFormatter).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

}
