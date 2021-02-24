package com.gemini.automation.ui.home;

import com.gemini.util.components.GaugeTable;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;

public class WebSteps {
    private final WebObject webObject = new WebObject();

    @Step("Validate user is on home/tasks page")
    public void validateUserIsOnHomePage() {
        webObject.getTodoHeader()
                .waitForPageElement()
                .andExactText("Todo");

        webObject.getItemsNavLink()
                .waitForPageElement()
                .appearedOnPage();

        webObject.getMyProfileNavLink()
                .waitForPageElement()
                .appearedOnPage();

        webObject.getLogoutNavLink()
                .waitForPageElement()
                .appearedOnPage();


        webObject.getAddTaskButton()
                .waitForPageElement()
                .appearedOnPage();
    }


    @Step("Click add task button")
    public void clickAddTaskButton() {
        webObject.getAddTaskButton()
                .waitForPageElement()
                .click();
    }

    @Step("Validate task is created and displayed as required <table>")
    public void validateTaskWithDetailsIsCreated(Table table) {
        GaugeTable taskDetailsTable = new GaugeTable(table);
        String row = taskDetailsTable.getValue("row");
        String title = taskDetailsTable.getValue("title");
        String description = taskDetailsTable.getValue("description");

        webObject.getTaskTitle(row)
                .waitForPageElement()
                .andExactText(title)
                .click();
        webObject.getTaskDescription(row)
                .waitForPageElement()
                .andExactText(description);
    }

    @Step("Validate task is deleted <table>")
    public void validateTaskIsDeleted(Table table) {
        GaugeTable taskDetailsTable = new GaugeTable(table);
        String row = taskDetailsTable.getValue("row");
        String title = taskDetailsTable.getValue("title");
        String description = taskDetailsTable.getValue("description");

        webObject.getTaskTitle(row)
                .waitForPageElement()
                .andDoesntContainsText(title);
        webObject.getTaskDescription(row)
                .waitForPageElement()
                .andDoesntContainsText(description);
    }

    @Step("Logout of todo application")
    public void clickLogoutButton() {
        webObject.getLogoutNavLink()
                .waitForPageElement()
                .click();
    }

    @Step("Delete task at row <row>")
    public void implementation1(String row) {
        webObject.getDeleteTaskButton(row)
                .waitForPageElement()
                .click();
    }
}
