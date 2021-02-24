package com.gemini.automation.ui.addtask;

import com.gemini.util.Utils;
import com.gemini.util.components.GaugeTable;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;

public class WebSteps {
    private final WebObject webObject = new WebObject();

    @Step("Validate add task page is displayed")
    public void validateAddTaskPageIsDisplayed() {
        //nav bar
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

        //end of nav bar

        webObject.getTodoDetailHeader()
                .waitForPageElement()
                .appearedOnPage();

        webObject.getTitleLabel()
                .waitForPageElement()
                .appearedOnPage();

        webObject.getTitleTextBox()
                .waitForPageElement()
                .appearedOnPage();

        webObject.getDescriptionLabel()
                .waitForPageElement()
                .appearedOnPage();

        webObject.getDescriptionTexBox()
                .waitForPageElement()
                .appearedOnPage();

        webObject.getIsCompleteCheckbox()
                .waitForPageElement()
                .appearedOnPage();

        webObject.getSaveButton()
                .waitForPageElement()
                .appearedOnPage();

        webObject.getCancelButton()
                .waitForPageElement()
                .appearedOnPage();
    }

    @Step("Enter add task details <table>")
    public void enterAddTaskDetails(Table table) {
        GaugeTable taskDetailsTable = new GaugeTable(table);
        String title = taskDetailsTable.getValue("title");
        String description = taskDetailsTable.getValue("description");
        String isComplete = taskDetailsTable.getValue("is complete");
        boolean checkIsComplete = Utils.yesNoToBool(isComplete);

        webObject.getTitleTextBox()
                .waitForPageElement()
                .enterText(title);
        webObject.getDescriptionTexBox()
                .waitForPageElement()
                .enterText(description);

        if (checkIsComplete) {
            webObject.getIsCompleteCheckbox()
                    .waitForPageElement()
                    .click();
        }
    }

    @Step("Click save button on add task page")
    public void clickSaveButton() {
        webObject.getSaveButton()
                .waitForPageElement()
                .click();
    }
}
