package com.gemini.util.components;

import com.codeborne.selenide.*;
import com.gemini.helpers.ClipboardHelper;
import com.gemini.helpers.ValidationListHelper;
import com.gemini.util.Utils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.and;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.fail;

public class WebAutomationObject {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private SelenideElement element;

    public WebAutomationObject(By locator) {
        this.element = $(locator);
    }

    private static void pollDelay() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {
        }
    }

    public void click() {
        element.should(Condition.appear).click();
    }

    public WebAutomationObject clickElement() {
        element.should(Condition.appear).click();
        return this;
    }

    public WebAutomationObject clickByJavascript() {
        Selenide.executeJavaScript("arguments[0].click();", element);
        return this;
    }

    public void scrollInside() {
        element.scrollIntoView("false");
    }

    public void validateClassAttribute(String attribute) {
        checkAttribute("class", attribute);
    }

    public void validateClassAttributeContains(String attribute) {
        String classAtt = element.getAttribute("class");
        if (Objects.isNull(classAtt)) {
            fail("Failed to retrieve class attribute");
        }

        boolean result = classAtt.contains(attribute);
        assertThat("Failed to validate class attribute contains " + attribute, result);
    }

    public void clickUntilObjectIsPresent(String xpath) {
        Selenide.executeJavaScript("arguments[0].click();", element);
        boolean displayed = Utils.isElementPresent(xpath, 1);
        while (!displayed) {
            Selenide.executeJavaScript("arguments[0].click();", element);
            displayed = Utils.isElementPresent(xpath, 1);
        }
    }

    public void clickUntilPresent(String xpath, String textToExpect) {
        element.click();
        boolean displayed = Utils.isElementPresent(xpath, 2);
        while (!displayed) {
            element.click();
            displayed = Utils.isElementPresent(xpath, 2);
            if(displayed){
                String text = new WebAutomationObject(By.xpath(xpath)).waitForPageElement().getElementText();
                if(!text.contains(textToExpect)){
                    displayed = false;
                }
            }
        }
    }

    public void waitUntilNotDisabled() {
        String disabled = this.getElementAttribute("disabled");
        boolean isDisabled = Objects.isNull(disabled) ? false : disabled.equals("disabled");
        while (isDisabled) {
            disabled = this.getElementAttribute("disabled");
            isDisabled = Objects.isNull(disabled) ? false : disabled.equals("disabled");
        }
    }

    public void doubleClick() {
        element.should(Condition.appear).doubleClick();
    }

    public void rightClick() {
        element.should(Condition.appear).contextClick();
    }

    public void rightClickUntilObjectIsPresent(String xpath) {
        element.should(Condition.appear).contextClick();
        int waitTime = 15;
        for (int count = 0; count < waitTime; count++) {
            if (Utils.isElementPresent(xpath, 1)) {
                return;
            }
            element.should(Condition.appear).contextClick();
        }
        fail("Failed to wait for element to be ready for interaction after " + waitTime + " seconds");
    }

    public void hoverUntilObjectIsPresent(String xpath) {
        element.should(Condition.appear).hover();
        int waitTime = 15;
        for (int count = 0; count < waitTime; count++) {
            if (Utils.isElementPresent(xpath, 1)) {
                return;
            }
            element.should(Condition.appear).hover();
        }
        fail("Failed to wait for element to be ready for interaction after " + waitTime + " seconds");
    }

    public WebAutomationObject appearedOnPage() {
        element.waitUntil(Condition.appear, 5000);
        return this;
    }

    public WebAutomationObject visibleOnPage() {
        element.waitUntil(Condition.visible, 5000);
        return this;
    }

    public WebAutomationObject disappearedFromPage() {
        element.waitUntil(Condition.disappears, 5000);
        return this;
    }

    public WebAutomationObject disappearedFromPage(int timeoutMilliseconds) {
        element.waitUntil(Condition.disappears, timeoutMilliseconds);
        return this;
    }

    public WebAutomationObject notAppearedOnPage() {
        element.shouldNotBe(Condition.appear);
        return this;
    }

    public WebAutomationObject doesNotExist() {
        element.shouldNotBe(Condition.exist);
        return this;
    }

    public WebAutomationObject isEnabled() {
        element.should(Condition.enabled);
        return this;
    }

    public WebAutomationObject isDisabled() {
        element.should(Condition.disabled);
        return this;
    }

    public WebAutomationObject andContainsText() {
        assertThat("Failed to Validate that element contains text", element.getText() != null || !element.getText().isEmpty());
        return this;
    }

    public WebAutomationObject andContainsText(String text) {
        element.waitUntil(Condition.appear, 5000).shouldHave(Condition.text(text));
        return this;
    }

    public WebAutomationObject andContainsTextWithSensitivity(String text) {
        element.waitUntil(Condition.appear, 5000).shouldHave(Condition.selectedText(text));
        return this;
    }

    public WebAutomationObject andExactText(String text) {
        element.shouldHave(Condition.exactText(text));
        return this;
    }

    public WebAutomationObject andDoesntContainsText(String text) {
        element.shouldNotHave(Condition.text(text));
        return this;
    }

    public WebAutomationObject scrollTo() {
        this.element.scrollTo();
        return this;
    }

    public WebAutomationObject hoverOver() {
        element.should(Condition.appear).hover();
        return this;
    }

    public WebAutomationObject isElementType(String type) {
        element.shouldBe(Condition.attribute("type", type));
        return this;
    }

    public WebAutomationObject checkAttribute(String attribute, String value) {
        element.shouldBe(Condition.attribute(attribute, value));
        return this;
    }

    public WebAutomationObject elementRoleIs(String role) {
        element.shouldBe(Condition.attribute("role", role));
        return this;
    }

    public WebAutomationObject sendKeyToElement(Keys key) {
        element.should(Condition.appear).sendKeys(key);
        return this;
    }

    public WebAutomationObject waitForPageElement() {
        //wait 10 seconds
        element.waitUntil(Condition.exist, 10000, 10);
        return this;
    }

    public WebAutomationObject waitForPageElement(int timeout) {
        //pass timeout in milliseconds
        element.waitUntil(Condition.exist, timeout, 10);
        return this;
    }

    public void innerTagWithText(String tag, String text) {
        element.find(By.tagName(tag)).should(Condition.exist).shouldHave(Condition.matchText(text));
    }

    public WebAutomationObject innerElementById(String id) {
        this.element = element.find(By.id(id)).should(Condition.exist);
        return this;
    }

    public SelenideElement getElement() {
        return element;
    }

    public SelenideElement getElementChild(String childXpath) {
        return element.find(By.xpath(childXpath));
    }

    public ElementsCollection getElementChildren(String childrenXpath) {
        return element.findAll(By.xpath(childrenXpath));
    }

    public String getElementText() {
        return element.getText();
    }

    public String getTextNonBlank() {
        String text = element.getText();
        while (text.isEmpty()) {
            pollDelay();
            text = element.getText();
        }
        return text;
    }

    public String getElementAttribute(String attribute) {
        return element.getAttribute(attribute);
    }

    public WebAutomationObject shouldHaveCss(String propertyName, String expectedValue) {
        element.shouldHave(Condition.cssValue(propertyName, expectedValue));
        return this;
    }

    public WebAutomationObject validateCssByHex(String hex) {
        element.shouldHave(Condition.cssValue("background-color", getRGBAColor(hex)));
        return this;
    }

    private String getRGBAColor(String hex) {
        Color color = Color.decode(hex);
        return "rgba(" + color.getRed() + ", " + color.getGreen() + ", " + color.getBlue() + ", 1)";
    }

    public WebAutomationObject checkAttributeContains(String attribute, String value) {
        element.getAttribute(attribute).contains(value);
        return this;
    }

    public WebAutomationObject sendKeys(String value) {
        element.sendKeys(value);
        return this;
    }

    public WebAutomationObject uploadFile(File file) {
        element.uploadFile(file);
        return this;
    }

    public WebAutomationObject isVisible(int timeout) {
        //pass timeout in milliseconds
        element.waitUntil(Condition.visible, timeout, 10);
        return this;
    }

    public WebAutomationObject input(String text) {
        this.element.should(Condition.appear).setValue(text);
        return this;
    }

    public WebAutomationObject waitForDisplayed(int timeoutInSeconds) {
        boolean isElementPresent = false;
        int counter = 0;
        timeoutInSeconds = timeoutInSeconds * 10;
        while (!isElementPresent && counter < timeoutInSeconds) {
            pollDelay();
            isElementPresent = this.element.isDisplayed();
            counter++;
        }
        if (!isElementPresent) {
            fail("Failed to validate element is diplayed");
        }
        return this;
    }

    public WebAutomationObject scrollIntoView() {
        this.element.scrollIntoView(true);
        return this;
    }

    public WebAutomationObject waitForClickable(By selector) {
        Condition clickable = and("can be clicked", Condition.visible, Condition.enabled);
        int waitTime = 400;
        for (int count = 0; count < waitTime; count++) {
            boolean ready = $$(selector).findBy(clickable).isDisplayed();
            if (ready) {
                return this;
            }
            pollDelay();
        }
        fail("Failed to wait for element to be ready for interaction after " + TimeUnit.MILLISECONDS.toSeconds(waitTime * 100));
        return this;
    }

    public WebAutomationObject waitUntilTextEqualTo(String textToMatch) {
        int waitTime = 300;
        for (int count = 0; count < waitTime; count++) {
            boolean ready = this.element.getText().equalsIgnoreCase(textToMatch);
            if (ready) {
                return this;
            }
            pollDelay();
        }
        fail("Failed to wait for element to be ready for interaction after " + TimeUnit.MILLISECONDS.toSeconds(waitTime * 100));
        return this;
    }

    public WebAutomationObject waitUntilTextNotEqualTo(String textToMatch) {
        int waitTime = 300;
        for (int count = 0; count < waitTime; count++) {
            boolean ready = this.element.getText().contains(textToMatch);
            if (!ready) {
                return this;
            }
            pollDelay();
        }
        fail("Failed to wait for element to be ready for interaction after " + TimeUnit.MILLISECONDS.toSeconds(waitTime * 100));
        return this;
    }

    public WebAutomationObject validateInputText(String expectedText) {
        String value = element.getAttribute("value");
        for (int count = 0; count < 20; count++) {
            if (value != null && value.length() > 0) {
                assertThat("Failed to validate Input Text - " + expectedText, value.equals(expectedText));
                break;
            } else {
                dynamicDelay();
                value = element.getAttribute("value");
            }
        }
        return this;
    }

    public void dynamicDelay() {
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void isToggled(boolean toggled) {
        this.element.shouldHave(Condition.attribute("aria-checked", String.valueOf(toggled)));
    }

    public void isScrollable() {
        String JS_ELEMENT_IS_SCROLLABLE =
                "return arguments[0].scrollHeight > arguments[0].offsetHeight;";
        Object isScrollable = Selenide.executeJavaScript(JS_ELEMENT_IS_SCROLLABLE, element);
        if (Objects.isNull(isScrollable)) {
            fail("Failed to execute script for method isScrollable()");
        }
        assertThat("Failed to validate element is scrollable", (Boolean) isScrollable);
    }

    public void isNotScrollable() {
        String JS_ELEMENT_IS_SCROLLABLE =
                "return arguments[0].scrollHeight > arguments[0].offsetHeight;";
        Object isScrollable = Selenide.executeJavaScript(JS_ELEMENT_IS_SCROLLABLE, element);
        if (Objects.isNull(isScrollable)) {
            fail("Failed to execute script for method isScrollable()");
        }
        Assertions.assertFalse((Boolean) isScrollable);
    }

    public String getValue() {
        String value = element.getAttribute("value");
        if (value != null && value.isEmpty()) return null;
        return value;
    }

    public void isSortable() {
        this.element.should(Condition.cssClass("sortable"));
    }

    public void isNotSortable() {
        this.element.shouldNot(Condition.cssClass("sortable"));
    }

    public WebAutomationObject scrollInsideDropdownToOption(String id) {
        $(By.id(id)).scrollIntoView("false");
        return this;
    }

    public WebAutomationObject openDropdown() {
        element.should(Condition.appear).parent().click();
        return this;
    }

    public WebAutomationObject closeDropdown() {
        this.sendKeyToElement(Keys.ESCAPE);
        return this;
    }

    public void containsAlertText(String text) {
        element.shouldHave(Condition.text(text));
    }

    public WebAutomationObject andCheckOptionsAppears(String... options) {
        $(By.className("menuable__content__active")).waitUntil(Condition.exist, 5000).find(By.className("v-list")).findAll(By.className("v-list-item")).shouldHave(CollectionCondition.textsInAnyOrder(options));
        return this;
    }

    public WebAutomationObject andCheckOneOfOptionIs(String option) {
        List<String> optionsList = $(By.className("menuable__content__active")).find(By.className("v-list")).findAll(By.className("v-list-item")).texts();
        assertThat(option + " is not among the options", optionsList.contains(option));
        return this;
    }

    public void andCheckOptionsAreInCertainOrder(String... options) {
        $(By.className("menuable__content__active")).find(By.className("v-list")).findAll(By.className("v-list-item")).shouldHave(CollectionCondition.exactTexts(options));
    }

    public WebAutomationObject andSelectOptionViaParent(String option) {
        $(By.id(option)).parent().click();
        return this;
    }

    public void andSelectOption(String option) {
        $(By.id(option)).click();
    }

    public WebAutomationObject andSelectOptionByText(String value) {
        String xpath = String.format("//*[contains(text(),'%s')]", value);
        $(By.xpath(xpath)).click();
        return this;
    }

    public void andSelectOptionByTextFocused(String value) {
        String xpath = String.format("//*[contains(@class, 'focused')]//*[text() = '%s']", value);
        $(By.xpath(xpath)).click();
    }

    public void clearValue() {
        element.find(By.tagName("i")).should(Condition.appear).click();
    }

    public WebAutomationObject findOptionByValue(String value) {
        this.openDropdown();
        try {
            element.getWrappedElement().sendKeys(value);
        } catch (Exception ignored) {
        }
        return this;
    }

    /**
     * Provide this method with a generic xpath to all the dropdown options.
     * Provide the option as it is visible on the website.
     */
    public WebAutomationObject selectDropdownOptionFromGenericXpath(String genericXpathToOptions, String option) {
        this.openDropdown();

        ValidationListHelper idList = new ValidationListHelper(genericXpathToOptions);

        if (idList.getSize() > 18) {
            //This is added due to large Lists not fully populating in the HTML until you scroll. then more gets populated.
            boolean isEndReached = false;
            int counter = 0;
            int listSize = idList.getSize();
            while (!isEndReached && counter < 5) {
                this.scrollInsideDropdownToOption(idList.getAttributeList("id").get(listSize - 1));
                pause(500);
                if (listSize == idList.getSize()) {
                    isEndReached = true;
                }
                listSize = idList.getSize();
                counter++;
            }
            idList = new ValidationListHelper(genericXpathToOptions);
        }

        String sanitisedOption = option.toLowerCase().replaceAll("[^A-Za-z0-9()]", "-");
        String optionID = idList.getAnyMatchContainsAttribute("id", sanitisedOption);

        if (optionID == null || optionID.isEmpty()) {
            assertThat("Failed matching Option Provided to Dropdown Option IDs " + option, false);
        }
        this.scrollInsideDropdownToOption(optionID);
        this.andSelectOptionViaParent(optionID);
        return this;
    }


    public WebAutomationObject searchAndSelectDropdownOption(String genericXpathToOptions, String optionValue) {
        this.findOptionByValue(optionValue);
        new ValidationListHelper(genericXpathToOptions, 2).
                waitForListSizeToBeMoreThan(0, 2).
                getElementAnyMatchContainsText(optionValue).click();
        return this;
    }

    public void pause(int ms) {
        try {
            TimeUnit.MILLISECONDS.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void toggle() {
        element.parent().click();
    }

    public void isVisible() {
        element.parent().should(Condition.appear);
    }

    public void ensureToggledStateIs(boolean isChecked) {
        boolean currentState = Boolean.parseBoolean(element.getAttribute("aria-checked"));
        if(currentState!=isChecked) {
            toggle();
            currentState = Boolean.parseBoolean(element.getAttribute("aria-checked"));
        }
        assertThat("Failed to ensure Button's Toggled State is ",currentState==isChecked);
    }

    public WebAutomationObject enterText(String text) {
        this.element.waitUntil(Condition.exist, 10000, 10).setValue(text);
        return this;
    }

    public WebAutomationObject clearTextField() {
        int InputLength = this.element.should(Condition.appear).getValue().length();
        for (int i = 0; i < InputLength; i++) {
            this.element.should(Condition.appear).sendKeys(Keys.BACK_SPACE);
        }
        return this;
    }

    public WebAutomationObject validateTextFieldEmpty() {
        String value = element.getAttribute("value");
        if (value.isEmpty()) return this;
        fail("Failed to validate field is empty : " + value);
        return this;
    }

    public String getElementValue() {
        String value = element.getAttribute("value");
        for (int count = 0; count < 20; count++) {
            if (value != null && value.length() > 0) {
                return value;
            } else {
                dynamicDelay();
                value = element.getAttribute("value");
            }
        }
        return null;
    }

    public WebAutomationObject validateInputTextContains(String expectedText) {
        String value = element.getAttribute("value");
        if (value != null && value.length() > 0) {
            assertThat(value, containsString(expectedText));
        } else {
            ClipboardHelper clipboardHelper = new ClipboardHelper();
            try {
                String text = clipboardHelper.retrieveTextUsingSelenium(element);
                assertThat(text, equalTo(expectedText));
            } catch (IOException | UnsupportedFlavorException e) {
                e.printStackTrace();
            }
        }
        return this;
    }
}
