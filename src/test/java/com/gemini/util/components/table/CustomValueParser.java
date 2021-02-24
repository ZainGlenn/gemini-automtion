package com.gemini.util.components.table;

import com.codeborne.selenide.SelenideElement;

public interface CustomValueParser {

    String transform(SelenideElement columnElement);
}
