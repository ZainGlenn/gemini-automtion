package com.gemini.util;

import com.gemini.helpers.TimeZoneHelper;
import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class DateUtils {

    public static String todayDateWithTime(String date) {
        String[] datime = date.split(" ");
        String aDate = datime[0];
        if (aDate.equalsIgnoreCase("today")) {
            aDate = DateUtils.getDateNow();
        }

        String newDate = aDate + " " + datime[1];
        TimeZoneHelper getTime = new TimeZoneHelper(newDate, "MM/dd/yyyy HH:mm", TimeZoneHelper.TIMEZONE_DUBLIN);

        return getTime.getRelativeDateTime().strip();
    }
    public static String convertToday(String date) {
        if (date.equalsIgnoreCase("today")) {
            return DateUtils.getDateNow();
        }
        return date;
    }

    public static int determineDifference(String actualAge, String expectedAge) {
        String age = StringUtils.substringBetween(actualAge, "(", ")");
        age = "(" + age + ")";
        String yearActual = StringUtils.substringBetween(age, "(", "y");
        String monthActual = StringUtils.substringBetween(age, " ", "m");
        if (Objects.isNull(monthActual)) {
            monthActual = "0";
        }

        int actualMonths = (Integer.parseInt(yearActual) * 12) + Integer.parseInt(monthActual);

        String yearExpected = StringUtils.substringBetween(expectedAge, "(", "y");
        String monthExpected = StringUtils.substringBetween(expectedAge, "y ", "m");

        int expectedMonths = (Integer.parseInt(yearExpected) * 12) + Integer.parseInt(monthExpected);

        return expectedMonths - actualMonths;
    }

    public static String parseDobToYearsAndMonths(LocalDate dateOfBirth) {
        LocalDate now = LocalDate.now();
        Period period = Period.between(dateOfBirth, now);
        return period.getYears() + "y " + period.getMonths() + "m";
    }

    public static String getDateNow() {
        String pattern = "MM/dd/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date());
    }

    public static String getDayWithOfSet(int offset) {
        return DateUtils.getCurrentDateTimeByDayOffset(offset, "MM/dd/yyyy", TimeZoneHelper.TIMEZONE_DUBLIN);
    }

    private static Date yesterday() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    private static Date tomorrow() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }

    public static String getYesterday(String datePattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
        return simpleDateFormat.format(yesterday());
    }

    public static String getTomorrow(String datePattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
        return simpleDateFormat.format(tomorrow());
    }

    public static String getDateNow(String dateFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        return simpleDateFormat.format(new Date());
    }

    public static String getTZDate() {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date());
    }

    public static String getDateNowUTC() {
        String pattern = "MM-dd-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date());
    }

    public static String getTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static String getTime(String format) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static String convertDateFormat(String dob) throws ParseException {
        //Instantiating the SimpleDateFormat class
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        //Parsing the given String to Date object
        Date date = formatter.parse(dob);
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    /**
     * This method will return a string datetime in the format you choose.
     * Using today's date, it will either move day(s) forward or backward depending on positive or negative values.
     * Call TimezoneIDs from the TimeZoneHelper class or find yours here: https://mkyong.com/java/java-display-list-of-timezone-with-gmt/
     */
    public static String getCurrentDateTimeByDayOffset(int dayOffset, String dateTimeFormat, String timeZoneID) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(dateTimeFormat);
        Date dt = new Date();

        System.out.println("\nToday's date: " + new SimpleDateFormat(dateTimeFormat).format(dt));
        System.out.println("Day offset of: " + dayOffset);

        ZoneId zoneId = ZoneId.of(timeZoneID);
        LocalDateTime ldt = LocalDateTime.from(dt.toInstant().atZone(zoneId)).plusDays(dayOffset);

        String offsetDateTime = format.format(ldt);
        System.out.println("Updated date with offset: " + offsetDateTime);
        return offsetDateTime;
    }

    public static String getDateTime() {
        String tomorrowsDate = DateUtils.getCurrentDateTimeByDayOffset(0, "yyyy-MM-dd", TimeZoneHelper.TIMEZONE_DUBLIN);
        String timeUTC = DateUtils.getTime();
        return tomorrowsDate + "T" + timeUTC + ":00.000Z";
    }

    //05:00
    public static String getDateTime(String time) {
        String tomorrowsDate = DateUtils.getCurrentDateTimeByDayOffset(0, "yyyy-MM-dd", TimeZoneHelper.TIMEZONE_DUBLIN);
        return tomorrowsDate + "T" + time + ":00.000Z";
    }

    public static String getDateTimeWithoutDate(String oldDate) {
        final DateTimeFormatter OLD_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        final DateTimeFormatter NEW_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate date = LocalDate.parse(oldDate, OLD_FORMATTER);
        String newString = date.format(NEW_FORMATTER);

        String timeUTC = DateUtils.getTime();
        return newString + "T" + timeUTC + ":00.000Z";
    }

    public static long getDifferenceDays(String date1, String date2) {
        try {
            Date d1 = new SimpleDateFormat("MM/dd/yyyy").parse(date1);
            Date d2 = new SimpleDateFormat("MM/dd/yyyy").parse(date2);
            long diff = d2.getTime() - d1.getTime();
            return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }

    }

    public static String getAgeWithMonths(String dob) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate dateOfBirth = LocalDate.parse(dob, formatter);
        return DateUtils.parseDobToYearsAndMonths(dateOfBirth);
    }

    public static String getAge(String dob) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate dateOfBirth = LocalDate.parse(dob, formatter);
        String parsedAge = DateUtils.parseDobToYearsAndMonths(dateOfBirth);
        if (DateUtils.parseDobToYearsAndMonths(dateOfBirth).contains(" 0m")) {
            parsedAge = parsedAge.replace(" 0m", "");
        }
        return parsedAge;
    }

}
