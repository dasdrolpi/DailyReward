package de.drolpi.dailyreward.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtil {

    private final static DateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm");

    private DateUtil() {
        throw new UnsupportedOperationException();
    }

    public static String formatDate(long dateMillis) {
        var date = new Date(dateMillis);
        return DATE_FORMAT.format(date);
    }
}
