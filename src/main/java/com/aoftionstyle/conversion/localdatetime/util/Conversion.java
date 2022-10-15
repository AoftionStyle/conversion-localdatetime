package com.aoftionstyle.conversion.localdatetime.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class Conversion {
    public static final String BANGKOK_ZONEID = "Asia/Bangkok";

    public static DateTimeFormatter toDateTimeFormatter(String pattern) {
        return DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.of(BANGKOK_ZONEID));
    }

    public static final ZonedDateTime toZoneDateTime(String strDatetime) {
        return ZonedDateTime.parse(strDatetime);
    }

    public static final ZonedDateTime toZoneDateTime(String pattern, String strDatetime) {
        DateTimeFormatter formatter = toDateTimeFormatter(pattern);
        return ZonedDateTime.parse(strDatetime, formatter.withResolverStyle(ResolverStyle.STRICT));
    }

    public static final Instant toInstant(ZonedDateTime zdt) {
        return zdt.toLocalDateTime()
            .atZone(ZoneId.of(BANGKOK_ZONEID))
            .toInstant();
    }

    public static final Instant toInstant(String strDatetime) {
        ZonedDateTime zdt = toZoneDateTime(strDatetime);
        return toInstant(zdt);
    }

    public static Instant toInstant(String pattern, String strDatetime) {
        ZonedDateTime zdt = toZoneDateTime(pattern, strDatetime);
        return toInstant(zdt);
    }
    
    public static Instant toInstant(LocalDateTime ldt) {
        return ldt.toInstant( ZoneOffset.ofHoursMinutesSeconds(ldt.getHour(), ldt.getMinute(), ldt.getSecond()) );
    }

    public static LocalDateTime toLocalDateTime(String pattern, String strDatetime) {
        Instant instant = toInstant(pattern, strDatetime);
        return LocalDateTime.ofInstant(instant, ZoneId.of(BANGKOK_ZONEID));
    }

    public static LocalDateTime toLocalDateTime(Instant instant) {
        return LocalDateTime.ofInstant(instant, ZoneId.of(BANGKOK_ZONEID));
    }

    public static String toString(String pattern, LocalDateTime ldt) {
        DateTimeFormatter formatter = toDateTimeFormatter(pattern);
        return ldt.format(formatter);
    }

    public static String toString(String pattern, Instant instant) {
        DateTimeFormatter formatter = toDateTimeFormatter(pattern);
        return formatter.format( instant );
    }

}
