package com.gemini.util;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.hamcrest.MatcherAssert.assertThat;

public class Utils {

    public static boolean isTextFoundInList(List<String> problemList, String actualProblem){
        boolean found = false;
        for(String problem : problemList){
            if(problem.equals(actualProblem)){
                found = true;
                break;
            }
        }
        return found;
    }

    public static void waitTillDisabledElementAreEnabled(int timeoutInSeconds, int size) {
        By disabled = By.xpath("//*[@disabled]");
        int counter = 0;
        timeoutInSeconds = timeoutInSeconds * 10;
        while (counter < timeoutInSeconds) {
            pollDelay();
            if ($$(disabled).size() <= size) {
                break;
            }
            counter++;
        }
    }

    public static boolean isElementPresent(By locator, int timeoutInSeconds) {
        boolean isElementPresent = false;
        int counter = 0;
        timeoutInSeconds = timeoutInSeconds * 10;
        while (!isElementPresent && counter < timeoutInSeconds) {
            pollDelay();
            isElementPresent = ((WebElement) $(locator)).isDisplayed();
            counter++;
        }
        return isElementPresent;
    }

    public static boolean isElementPresent(String xpath, int timeoutInSeconds) {
        boolean isElementPresent = false;
        int counter = 0;
        timeoutInSeconds = timeoutInSeconds * 10;
        while (!isElementPresent && counter < timeoutInSeconds) {
            pollDelay();
            isElementPresent = ((WebElement) $(By.xpath(xpath))).isDisplayed();
            counter++;
        }
        return isElementPresent;
    }

    public static void validateStringIsDateTime(String dateTime, String dateTimeFormat) {
        //List of all TimeZoneIDs: https://mkyong.com/java/java-display-list-of-timezone-with-gmt/
        try {
            Date date = new SimpleDateFormat(dateTimeFormat).parse(dateTime.trim());
        } catch (Exception e) {
            assertThat("Failed to Validate String (" + dateTime + ") is a Date Time - " + e.getMessage(), false);
        }
    }

    public static int getRandomSixDigitNumber() {
        Random rnd = new Random();
        return 100000 + rnd.nextInt(900000);
    }

    public static String getRandomSixDigitNumberStr() {
        return Integer.toString(getRandomSixDigitNumber());
    }

    public static String getRandomAlphabeticString(int targetStringLength) {
        //https://www.baeldung.com/java-random-string
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    public static String convertToTitleCaseIteratingChars(String text) {
        //https://www.baeldung.com/java-string-title-case
        if (text == null || text.isEmpty()) {
            return text;
        }

        StringBuilder converted = new StringBuilder();

        boolean convertNext = true;
        for (char ch : text.toCharArray()) {
            if (Character.isSpaceChar(ch)) {
                convertNext = true;
            } else if (convertNext) {
                ch = Character.toTitleCase(ch);
                convertNext = false;
            } else {
                ch = Character.toLowerCase(ch);
            }
            converted.append(ch);
        }

        return converted.toString();
    }


    public static int returnNumberOfTabs() {
        int numberOfTabs = 0;
        try {
            numberOfTabs = new ArrayList<>(getWebDriver().getWindowHandles()).size();

        } catch (Exception e) {
        }
        return numberOfTabs;
    }

    public static void sleepForever() {
        System.out.println("Starting Sleep forever");
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }

    private static void pollDelay() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {
        }
    }

    public static boolean isDate(String dateTime, String dateTimeFormat) {
        //List of all TimeZoneIDs: https://mkyong.com/java/java-display-list-of-timezone-with-gmt/
        try {
            Date date = new SimpleDateFormat(dateTimeFormat).parse(dateTime);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean yesNoToBool(String option) {
        return option.equalsIgnoreCase("Yes");
    }

    public static void switchWindow(int count) {
        switchTo().window(count);
    }

    public static void scrollToCenter(By locator){
        WebElement element = WebDriverRunner.getWebDriver().findElement(locator);
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
        ((JavascriptExecutor) WebDriverRunner.getWebDriver()).executeScript(scrollElementIntoMiddle, element);
    }
}
