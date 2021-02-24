package com.gemini.automation.ui.register;

import com.gemini.util.components.GaugeTable;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;

public class WebSteps {
    private final WebObject webObject = new WebObject();

    @Step("Validate Register page is displayed")
    public void validateRegisterIsDisplayed() {
        webObject.getTodoHeader()
                .waitForPageElement()
                .andExactText("Todo");
        webObject.getRegisterHeader()
                .waitForPageElement()
                .appearedOnPage();
        webObject.getEmailAddressLabel()
                .waitForPageElement()
                .appearedOnPage();
        webObject.getEmailAddressText()
                .waitForPageElement()
                .appearedOnPage();
        webObject.getPasswordLabel()
                .waitForPageElement()
                .appearedOnPage();
        webObject.getPasswordText()
                .waitForPageElement()
                .appearedOnPage();
        webObject.getRegisterButton()
                .waitForPageElement()
                .appearedOnPage();
    }

    @Step("Enter user registration details <registerDetails>")
    public void enterRegisterDetails(Table registerDetails) {
        GaugeTable gt = new GaugeTable(registerDetails);
        webObject.getEmailAddressText()
                .waitForPageElement()
                .enterText(gt.getValue("email"));

        webObject.getPasswordText()
                .waitForPageElement()
                .enterText(gt.getValue("password"));
    }

    @Step("Click Register button")
    public void clickRegisterButton() {
        webObject.getRegisterButton()
                .waitForPageElement()
                .click();
    }
}
