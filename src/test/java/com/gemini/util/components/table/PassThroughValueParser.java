package com.gemini.util.components.table;

import com.codeborne.selenide.SelenideElement;

public class PassThroughValueParser implements CustomValueParser {
    @Override
    public String transform(SelenideElement columnElement) {
        return columnElement.getText();
    }
}
