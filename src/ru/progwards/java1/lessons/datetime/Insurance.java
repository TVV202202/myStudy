package ru.progwards.java1.lessons.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAmount;

import static java.time.format.DateTimeFormatter.*;
import static ru.progwards.java1.lessons.datetime.Insurance.FormatStyle.*;

public class Insurance {
    public enum FormatStyle {SHORT, LONG, FULL}

    private ZonedDateTime start; // дата-время начала действия страховки.
    private Duration duration; //  продолжительность действия.

    public Insurance(ZonedDateTime start) {
        this.start = start;
    }

    public Insurance(String strStart, FormatStyle style) {
        if (style == FormatStyle.SHORT) {
            LocalDate ld = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(strStart.substring(0,10)));
            start = ld.atStartOfDay(ZoneId.systemDefault());
        } else if (style == FormatStyle.LONG) {
            LocalDateTime localDateTime = LocalDateTime.from(DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse(strStart.substring(0,26)));
            start = localDateTime.atZone(ZoneId.systemDefault());
        } else
            start = ZonedDateTime.from(DateTimeFormatter.ISO_ZONED_DATE_TIME.parse(strStart));
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public void setDuration(ZonedDateTime expiration) {
        duration = Duration.between(start, expiration);
    }

    public void setDuration(int months, int days, int hours) {
        ZonedDateTime zdt1 = ZonedDateTime.now();
        ZonedDateTime zdt2 = zdt1.plusMonths(months).plusDays(days).minusHours(hours);
        duration = Duration.between(zdt1,zdt2);
    }

    public void setDuration(String strDuration, FormatStyle style) {
        if (style == SHORT) {
            duration = Duration.ofMillis(Long.parseLong(strDuration));
        } else if (style == LONG) {
            LocalDateTime ldt1 = LocalDateTime.parse(strDuration);
            LocalDateTime ldt2 = LocalDateTime.now();
            LocalDateTime ldt3 = ldt2.plusYears(ldt1.getYear());
            ldt3 = ldt3.plusMonths(ldt1.getMonthValue());
            ldt3 = ldt3.plusDays(ldt1.getDayOfMonth());
            ldt3 = ldt3.plusHours(ldt1.getHour());
            ldt3 = ldt3.plusMinutes(ldt1.getMinute());
            ldt3 = ldt3.plusSeconds(ldt1.getSecond());
            duration = Duration.between(ldt2,ldt3);
        } else {
            duration = Duration.parse(strDuration);
        }
    }

    public boolean checkValid(ZonedDateTime dateTime) {
        return duration == null || Duration.between(start, dateTime).compareTo(duration) <= 0;
    }

    @Override
    public String toString() {
        String validStr;
        if (checkValid(ZonedDateTime.now()))
            validStr = " is valid";
        else
            validStr = " is not valid";
        return  "Insurance issued on " +
                start + validStr;
    }

    public static void main(String[] args) {
        String str = "2021-05-18T15:07:36.161103+03:00[Europe/Moscow]";
        Duration duration1 = Duration.ofDays(366);
        Insurance test1=new Insurance(str, SHORT);

        test1.setDuration(duration1);
        System.out.println(test1.duration);
        System.out.println(test1);
        //System.out.println(LocalDate.now());
    }
}
