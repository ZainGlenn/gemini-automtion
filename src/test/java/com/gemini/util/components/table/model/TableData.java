package com.gemini.util.components.table.model;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;

public abstract class TableData {

    private ElementsCollection columns;

    public void setColumnElements(ElementsCollection columns) {
        this.columns = columns;
    }

    public ElementsCollection getColumnElements() {
        return columns;
    }

    public void clickOnFirstColumn() {
        columns.get(0).should(Condition.appear).click();
    }

    public void waitOnFirstColumn() {
        columns.get(0).should(Condition.appear);
    }
}
