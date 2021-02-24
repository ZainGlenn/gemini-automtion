package com.gemini.unit;

import com.gemini.helpers.SelectorHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class SelectorHelperTests {
    @Test
    @Disabled
    public void anyText() throws IOException {
        String selector = SelectorHelper.findByText("Zain Glenn").toString();
        Assertions.assertEquals(selector, "By.xpath: //*[text() = 'Zain Glenn']");
    }

    @Test
    @Disabled
    public void anyContainsText() {
        String selector = SelectorHelper.findByContainsText("Zain Glenn").toString();
        Assertions.assertEquals(selector, "By.xpath: //*[contains(text(),'Zain Glenn')]");
    }

    @Test
    @Disabled
    public void divContainsText() {
        String selector = SelectorHelper.findByContainsText("div", "Zain Glenn").toString();
        Assertions.assertEquals(selector, "By.xpath: //div[contains(text(),'Zain Glenn')]");
    }

    @Test
    @Disabled
    public void divText() {
        String selector = SelectorHelper.findByText("div", "Zain Glenn").toString();
        Assertions.assertEquals(selector, "By.xpath: //div[text() = 'Zain Glenn']");
    }
}
