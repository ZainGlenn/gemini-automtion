package com.gemini.automation.ui.register;

import com.gemini.util.components.WebAutomationObject;

public class WebObject {
    private final WebAutomationObject todoHeader = new WebAutomationObject(WebSelectors.todoHeaderClass);
    private final WebAutomationObject registerHeader = new WebAutomationObject(WebSelectors.registerHeaderXpath);
    private final WebAutomationObject emailAddressLabel = new WebAutomationObject(WebSelectors.emailAddressLabelXpath);
    private final WebAutomationObject emailAddressText = new WebAutomationObject(WebSelectors.emailAddressTextXpath);
    private final WebAutomationObject passwordLabel = new WebAutomationObject(WebSelectors.passwordLabelXpath);
    private final WebAutomationObject passwordText = new WebAutomationObject(WebSelectors.passwordTextXpath);
    private final WebAutomationObject registerButton = new WebAutomationObject(WebSelectors.registerButtonXpath);

    public WebAutomationObject getTodoHeader() {
        return todoHeader;
    }

    public WebAutomationObject getRegisterHeader() {
        return registerHeader;
    }

    public WebAutomationObject getEmailAddressLabel() {
        return emailAddressLabel;
    }

    public WebAutomationObject getEmailAddressText() {
        return emailAddressText;
    }

    public WebAutomationObject getPasswordLabel() {
        return passwordLabel;
    }

    public WebAutomationObject getPasswordText() {
        return passwordText;
    }

    public WebAutomationObject getRegisterButton() {
        return registerButton;
    }
}
