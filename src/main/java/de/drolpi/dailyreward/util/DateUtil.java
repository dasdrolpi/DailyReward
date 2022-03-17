package de.drolpi.dailyreward.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private DateUtil() {
        throw new UnsupportedOperationException();
    }

    public static String formatDate(long dateMillis) {
        var date = new Date(dateMillis);
        var timeZoneDate = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        return timeZoneDate.format(date);
    }
}
