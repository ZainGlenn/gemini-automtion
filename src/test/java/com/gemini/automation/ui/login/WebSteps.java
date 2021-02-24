package com.gemini.automation.ui.login;

import com.gemini.util.TestUtil;
import com.thoughtworks.gauge.Step;

import static com.codeborne.selenide.Selenide.open;

public class WebSteps {
    private static final String SERVER_URL_PROPERTY = "server.url";
    private final WebObject webObject = new WebObject();

    @Step("User goes to Todo Login Page")
    public void openToLoginPage() {
        TestUtil testUtils = TestUtil.getInstance();
        String url = testUtils.getProperty(SERVER_URL_PROPERTY);
        open(url);
    }

    @Step("Validate Todo Login page displays as required")
    public void validateToDisplaysAsRequired() {
        webObject.getTodoHeader()
                .waitForPageElement()
                .andExactText("Todo");
        webObject.getRegisterButton()
                .waitForPageElement()
                .appearedOnPage();
        webObject.getLoginLabel()
                .waitForPageElement()
                .appearedOnPage();
        webObject.getUsernameText()
                .waitForPageElement()
                .appearedOnPage();
        webObject.getPasswordText()
                .waitForPageElement()
                .appearedOnPage();
    }


    @Step("Click Register button on Login Page")
    public void clickRegisterButton() {
        webObject.getRegisterButton().waitForPageElement().click();
    }

    @Step("Validate successful registration message is displayed - <message>")
    public void validateSuccessMessage(String message) {
        webObject.getSuccessMessage()
                .waitForPageElement()
                .andExactText(message);
    }

    @Step("Enter username <username> and password <password>")
    public void enterUserDetails(String username, String password) {
        webObject.getUsernameText()
                .waitForPageElement()
                .enterText(username);
        webObject.getPasswordText()
                .waitForPageElement()
                .enterText(password);
    }

    @Step("Click Login Button")
    public void clickLogin() {
        webObject.getLoginButton()
                .waitForPageElement()
                .click();
    }
}
