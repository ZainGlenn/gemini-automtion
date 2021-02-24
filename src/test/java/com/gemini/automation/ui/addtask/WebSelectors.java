package com.gemini.automation.ui.addtask;

import org.openqa.selenium.By;

class WebSelectors {
    protected static By todoHeaderClass = By.className("navbar-brand");
    protected static By itemsNavLinkXpath = By.xpath("//a[text() = 'Items']");
    protected static By myProfileNavLinkXpath = By.xpath("//a[text() = 'My Profile']");
    protected static By logoutNavLinkXpath = By.xpath("//a[text() = 'Logout']");

    protected static By todoDetailHeaderXpath = By.xpath("//h1[text() = 'Todo Detail']");
    protected static By titleLabelXpath = By.xpath("//label[text() = 'Title']");
    protected static By titleTextBoxXpath = By.xpath("//input[@name = 'title']");
    protected static By descriptionLabelXpath = By.xpath("//label[text() = 'Description']");
    protected static By descriptionTexBoxXpath = By.xpath("//textarea[@name = 'description']");
    protected static By isCompleteCheckboxXpath = By.xpath("//input[@name = 'isComplete']");
    protected static By saveButtonXpath = By.xpath("//button[text() = 'Save']");
    protected static By cancelButtonXpath = By.xpath("//button[text() = 'Cancel']");
}
