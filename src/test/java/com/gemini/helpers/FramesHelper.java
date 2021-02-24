package com.gemini.helpers;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;

public class FramesHelper {

    public WebDriver switchToIframe(String id) {
        return WebDriverRunner.getWebDriver().switchTo().frame(id);
    }
    public void switchToIframeXpath(String xpath) {
        WebDriverRunner.getWebDriver().switchTo().frame($(By.xpath(xpath)));
    }

    public WebDriver switchToIframe(By selector) {
        WebElement frameElement = WebDriverRunner.getWebDriver().findElement(selector);
        return WebDriverRunner.getWebDriver().switchTo().frame(frameElement);
    }

    public void switchBack() {
        WebDriverRunner.getWebDriver().switchTo().defaultContent();
    }
}