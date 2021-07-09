package com.epam.esm.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.TimeZone;

public class ServiceUtils {
    /**
     * @return current date in iso 8601 format
     */
    public static LocalDateTime getCurrentDateTime() {
        TimeZone tz = TimeZone.getTimeZone(ZoneId.systemDefault());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        df.setTimeZone(tz);
        return LocalDateTime.from(ZonedDateTime.parse(df.format(new Date())));
    }
}
