package com.yasho.bugtrackingservice.util;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {

    public static Date getDate(int year, Month month, int dayOfMonth) {
        return Date.from(
                LocalDate.of(year, month, dayOfMonth)
                        .atStartOfDay(ZoneId.systemDefault())
                        .toInstant());
    }
}
