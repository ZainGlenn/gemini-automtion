package com.gemini.automation.ui.login;

import org.openqa.selenium.By;

public class WebSelectors {
    protected static final By registerXpath = By.xpath("//a[text() = 'Register']");
    protected static final By todoHeaderClass = By.className("navbar-brand");
    protected static final By loginLabelXpath = By.xpath("//h1[text() = 'Login']");
    protected static final By usernameTextXpath = By.xpath("//input[@name ='username']");
    protected static final By passwordTextXpath = By.xpath("//input[@name ='password']");
    protected static final By loginButton = By.xpath("//button[text() = 'Login']");
    protected static final By footerLabel = By.xpath("//button[text() = 'Login']/following-sibling::p");
    protected static final By successMessageXpath = By.xpath("//*[contains(@class, 'alert')]");
}
