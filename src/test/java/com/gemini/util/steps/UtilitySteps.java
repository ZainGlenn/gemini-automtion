package com.gemini.util.steps;

import com.codeborne.selenide.WebDriverRunner;
import com.gemini.helpers.FramesHelper;
import com.gemini.util.Utils;
import com.thoughtworks.gauge.Step;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.back;

public class UtilitySteps {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Step({"Sleep forever"})
    public void sleepForever() {
        logger.info("starting sleep forever");
        Utils.sleepForever();
    }

    @Step({"Go Back"})
    public void goBack(){
        back();
    }

    @Step({"Shut down browser"})
    public void shutDownBrowser() {
        WebDriverRunner.driver().close();
    }

}
