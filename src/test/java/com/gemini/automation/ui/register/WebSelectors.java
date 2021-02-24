package com.gemini.automation.ui.register;

import org.openqa.selenium.By;

public class WebSelectors {
    protected static final By todoHeaderClass = By.className("navbar-brand");
    protected static final By  registerHeaderXpath = By.xpath("//h1[text() = 'Register']");
    protected static final By emailAddressLabelXpath = By.xpath("//label[text() = 'Email address']");
    protected static final By emailAddressTextXpath = By.xpath("//input[@name = 'username']");
    protected static final By passwordLabelXpath = By.xpath("//label[text() = 'Password']");
    protected static final By passwordTextXpath = By.xpath("//input[@name = 'password']");
    protected static final By registerButtonXpath = By.xpath("//button[text() = 'Register']");
}
