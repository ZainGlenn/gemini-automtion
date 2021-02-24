package com.gemini.unit;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class SelenideTest {

    @Test
    @Disabled("This is for development")
    public void localSelenideTest(){
        Configuration.holdBrowserOpen=true;
        Configuration.remote = "http://127.0.0.1:4444/wd/hub";
        Configuration.browser = "chrome";
        Configuration.headless = true;
        System.setProperty("chromeoptions.prefs","intl.accept_languages=en");
        System.setProperty("chromeoptions.args", "--disable-gpu,--no-sandbox");
        open("http://www.reliply.org/tools/requestheaders.php");
    }
}
