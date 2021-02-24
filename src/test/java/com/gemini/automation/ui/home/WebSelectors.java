package com.gemini.automation.ui.home;

import org.openqa.selenium.By;

class WebSelectors {
    protected static By todoHeaderClass = By.className("navbar-brand");
    protected static By itemsNavLinkXpath = By.xpath("//a[text() = 'Items']");
    protected static By myProfileNavLinkXpath  = By.xpath("//a[text() = 'My Profile']");
    protected static By logoutNavLinkXpath  = By.xpath("//a[text() = 'Logout']");
    protected static By addTaskButtonXpath  = By.xpath("//a[*[contains(@class, 'fa-plus')]]");

    private static  String getCardBase(int row){
        return "(//*[@class = 'card'])["+row+"]";
    }

    public static By getCardTitle(int row){
        return By.xpath(getCardBase(row) + "/*[@class = 'card-header']");
    }

    public static By getDeleteTaskButton(int row){
        return By.xpath(getCardBase(row) + "//*[contains(@class, 'fa-trash')]");
    }

    public static By getCardDescription(int row){
        return By.xpath(getCardBase(row) + "/*[@class = 'card-body']/p");
    }

    //to prevent instantiation of class
    private WebSelectors(){}

}
