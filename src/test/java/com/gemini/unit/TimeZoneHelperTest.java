package com.gemini.unit;

import com.gemini.helpers.TimeZoneHelper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class TimeZoneHelperTest {

    @Test
    @Disabled("Only used for development")
    public void shouldReturnCorrectTimesBasedOnTimeZones() {
        TimeZoneHelper timeZoneHelper1 = new TimeZoneHelper("09/05/2020 11:12", "MM/dd/yyyy HH:mm", TimeZoneHelper.TIMEZONE_DUBLIN);
        timeZoneHelper1.getRelativeDateTime();

        TimeZoneHelper timeZoneHelper2 = new TimeZoneHelper("09/05/2020 05:55", "MM/dd/yyyy HH:mm", TimeZoneHelper.TIMEZONE_AFRICA_JOHANNESBURG);
        timeZoneHelper2.getRelativeDateTime();

        TimeZoneHelper timeZoneHelper3 = new TimeZoneHelper("03/05/2020 12:03", "MM/dd/yyyy HH:mm", TimeZoneHelper.TIMEZONE_DUBLIN);
        timeZoneHelper3.getRelativeDateTime();
    }

}