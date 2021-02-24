package com.gemini.automation.ui.addtask;

import com.gemini.util.components.WebAutomationObject;

class WebObject {
    private final WebAutomationObject todoHeader = new WebAutomationObject(WebSelectors.todoHeaderClass);
    private final WebAutomationObject itemsNavLink = new WebAutomationObject(WebSelectors.itemsNavLinkXpath);
    private final WebAutomationObject myProfileNavLink = new WebAutomationObject(WebSelectors.myProfileNavLinkXpath);
    private final WebAutomationObject logoutNavLink = new WebAutomationObject(WebSelectors.logoutNavLinkXpath);

    private final WebAutomationObject todoDetailHeader = new WebAutomationObject(WebSelectors.todoDetailHeaderXpath);
    private final WebAutomationObject titleLabel = new WebAutomationObject(WebSelectors.titleLabelXpath);
    private final WebAutomationObject titleTextBox = new WebAutomationObject(WebSelectors.titleTextBoxXpath);
    private final WebAutomationObject descriptionLabel = new WebAutomationObject(WebSelectors.descriptionLabelXpath);
    private final WebAutomationObject descriptionTexBox = new WebAutomationObject(WebSelectors.descriptionTexBoxXpath);
    private final WebAutomationObject isCompleteCheckbox = new WebAutomationObject(WebSelectors.isCompleteCheckboxXpath);
    private final WebAutomationObject saveButton = new WebAutomationObject(WebSelectors.saveButtonXpath);
    private final WebAutomationObject cancelButton = new WebAutomationObject(WebSelectors.cancelButtonXpath);


    public WebAutomationObject getTodoDetailHeader() {
        return todoDetailHeader;
    }

    public WebAutomationObject getTitleLabel() {
        return titleLabel;
    }

    public WebAutomationObject getTitleTextBox() {
        return titleTextBox;
    }

    public WebAutomationObject getDescriptionLabel() {
        return descriptionLabel;
    }

    public WebAutomationObject getDescriptionTexBox() {
        return descriptionTexBox;
    }

    public WebAutomationObject getIsCompleteCheckbox() {
        return isCompleteCheckbox;
    }

    public WebAutomationObject getSaveButton() {
        return saveButton;
    }

    public WebAutomationObject getCancelButton() {
        return cancelButton;
    }

    public WebAutomationObject getTodoHeader() {
        return todoHeader;
    }

    public WebAutomationObject getItemsNavLink() {
        return itemsNavLink;
    }

    public WebAutomationObject getMyProfileNavLink() {
        return myProfileNavLink;
    }

    public WebAutomationObject getLogoutNavLink() {
        return logoutNavLink;
    }
}
