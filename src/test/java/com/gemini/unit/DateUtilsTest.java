package com.gemini.unit;

import com.gemini.helpers.TimeZoneHelper;
import com.gemini.util.DateUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class DateUtilsTest {

    @Test
    @Disabled("Only used for development")
    public void validateMonths() {
        int diff = DateUtils.determineDifference("(31y 1m)","(31y 0m)");
        assertThat("Failed to validate difference in age is 1 month",diff == 1);
    }

    @Test
    @Disabled("Only used for development")
    public void shouldReturnYearsAndMonthsFromDob() {
        LocalDate dateOfBirth = LocalDate.of(1953, 11, 5);
        assertThat(DateUtils.parseDobToYearsAndMonths(dateOfBirth), equalTo("66y 5m"));
    }

    @Test
    @Disabled("Only used for development")
    public void getTomorrowsDate() {
        String tomorrowsDate = DateUtils.getCurrentDateTimeByDayOffset(1, "dd MMMM yyyy", TimeZoneHelper.TIMEZONE_DUBLIN);
        assertThat("Failed to get Tomorrow's Date - " + tomorrowsDate, tomorrowsDate != null && !tomorrowsDate.isEmpty());
    }

    @Test
//    @Disabled("Only used for development")
    public void code() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate dateOfBirth = LocalDate.parse("10/10/1992", formatter);
        String parsedAge =  DateUtils.parseDobToYearsAndMonths(dateOfBirth);
        if (DateUtils.parseDobToYearsAndMonths(dateOfBirth).contains(" 0m")) {
            parsedAge = parsedAge.replace(" 0m", "");
        }
        System.out.println(parsedAge);

    }

}