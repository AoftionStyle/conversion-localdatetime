package com.aoftionstyle.conversion.localdatetime.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class ConversionTest {
    @Test
    void testToDateTimeFormatter() {
        DateTimeFormatter dateTimeFormatter = Conversion.toDateTimeFormatter("dd/MM/uuuu");
        String actual = dateTimeFormatter.format(LocalDate.of(2022, 10, 12));
        assertEquals("12/10/2022", actual);
    }

    @Test
    void testToInstantZoneDateTime() {
        ZonedDateTime zdt = ZonedDateTime.of(2022, 10, 12, 18, 30, 0, 0, ZoneId.of("Asia/Bangkok"));
        assertEquals("2022-10-12T11:30:00Z", Conversion.toInstant(zdt).toString());
    }

    @Test
    void testToInstantString() {
        assertEquals("2022-10-12T04:30:00Z", Conversion.toInstant("2022-10-12T11:30:00Z").toString());
    }

    @Test
    void testToInstantLocalDatetime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        LocalDateTime localDateTime = LocalDate.parse("12/10/2022", dtf).atStartOfDay();
        assertEquals("2022-10-12T00:00:00Z", Conversion.toInstant(localDateTime).toString());
    }

    @Test
    void testToLocalDateTimeInstant() {
        LocalDateTime ldt = LocalDateTime.of(2022, 10, 12, 0, 0, 0);
        assertEquals(ldt, Conversion.toLocalDateTime(Conversion.toInstant("2022-10-12T00:00:00Z")));
    }

    @Test
    void testToLocalDateTimePattern() {
        LocalDateTime ldt = LocalDateTime.of(2022, 10, 12, 0, 0, 0);
        assertEquals(ldt, Conversion.toLocalDateTime("dd/MM/uuuu HH:mm:ss", "12/10/2022 00:00:00"));
    }

    @Test
    void testToStringPatternInstant() {
        LocalDateTime ldt = LocalDateTime.of(2022, 10, 12, 0, 0, 0);
        ldt.toInstant( ZoneOffset.ofHoursMinutesSeconds(ldt.getHour(), ldt.getMinute(), ldt.getSecond()) );
        assertEquals("12/10/2022", Conversion.toString("dd/MM/uuuu", ldt.toInstant( ZoneOffset.ofHoursMinutesSeconds(ldt.getHour(), ldt.getMinute(), ldt.getSecond()) ) ) );
    }

    @Test
    void testToStringPatternLocalDateTime() {
        assertEquals("12/10/2022 00:00:00", Conversion.toString("dd/MM/uuuu HH:mm:ss", LocalDateTime.of(2022, 10, 12, 0, 0, 0)));
    }

    @Test
    void testToZoneDateTime() {
        assertEquals("2022-10-12T00:00:01Z", Conversion.toZoneDateTime("2022-10-12T00:00:01Z").toString());
    }

    @Test
    void testToZoneDateTimePattern() {
        assertEquals("2022-10-12T00:00:01+07:00[Asia/Bangkok]", Conversion.toZoneDateTime("dd/MM/uuuu HH:mm:ss", "12/10/2022 00:00:01").toString());
    }
}
