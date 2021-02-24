package com.gemini.automation.ui.home;

import com.gemini.util.components.WebAutomationObject;

class WebObject {
    private final WebAutomationObject todoHeader = new WebAutomationObject(WebSelectors.todoHeaderClass);
    private final WebAutomationObject itemsNavLink = new WebAutomationObject(WebSelectors.itemsNavLinkXpath);
    private final WebAutomationObject myProfileNavLink = new WebAutomationObject(WebSelectors.myProfileNavLinkXpath);
    private final WebAutomationObject logoutNavLink = new WebAutomationObject(WebSelectors.logoutNavLinkXpath);
    private final WebAutomationObject addTaskButton = new WebAutomationObject(WebSelectors.addTaskButtonXpath);

    public WebAutomationObject getTaskTitle(String row) {
        return new WebAutomationObject(WebSelectors.getCardTitle(Integer.parseInt(row)));
    }

    public WebAutomationObject getDeleteTaskButton(String row) {
        return new WebAutomationObject(WebSelectors.getDeleteTaskButton(Integer.parseInt(row)));
    }

    public WebAutomationObject getTaskDescription(String row) {
        return new WebAutomationObject(WebSelectors.getCardDescription(Integer.parseInt(row)));
    }

    public WebAutomationObject getAddTaskButton() {
        return addTaskButton;
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
