package com.gemini.automation.ui.login;

import com.gemini.util.components.WebAutomationObject;

public class WebObject {
    private final WebAutomationObject registerButton = new WebAutomationObject(WebSelectors.registerXpath);
    private final WebAutomationObject todoHeader = new WebAutomationObject(WebSelectors.todoHeaderClass);
    private final WebAutomationObject loginLabel = new WebAutomationObject(WebSelectors.loginLabelXpath);
    private final WebAutomationObject usernameText = new WebAutomationObject(WebSelectors.usernameTextXpath);
    private final WebAutomationObject passwordText = new WebAutomationObject(WebSelectors.passwordTextXpath);
    private final WebAutomationObject loginButton = new WebAutomationObject(WebSelectors.loginButton);
    private final WebAutomationObject footerLabel = new WebAutomationObject(WebSelectors.footerLabel);
    private final WebAutomationObject successMessage = new WebAutomationObject(WebSelectors.successMessageXpath);


    public WebAutomationObject getSuccessMessage() {
        return successMessage;
    }

    public WebAutomationObject getRegisterButton() {
        return registerButton;
    }

    public WebAutomationObject getTodoHeader() {
        return todoHeader;
    }

    public WebAutomationObject getLoginLabel() {
        return loginLabel;
    }

    public WebAutomationObject getUsernameText() {
        return usernameText;
    }

    public WebAutomationObject getPasswordText() {
        return passwordText;
    }

    public WebAutomationObject getLoginButton() {
        return loginButton;
    }

    public WebAutomationObject getFooterLabel() {
        return footerLabel;
    }
}
