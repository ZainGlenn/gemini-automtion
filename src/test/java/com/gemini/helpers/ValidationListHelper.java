package com.gemini.helpers;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.gemini.util.components.GaugeTable;
import com.gemini.util.DateUtils;
import com.gemini.util.Utils;
import com.thoughtworks.gauge.datastore.ScenarioDataStore;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$;
import static org.hamcrest.MatcherAssert.assertThat;


public class ValidationListHelper {

    private final ElementsCollection validationList;
    private final List<String> validationListTexts;
    private final String xpath;
    private boolean valid;

    /**
     * Provide an xpath that gets all the elements you want to validate.
     */
    public ValidationListHelper(String xpath) {
        this.xpath = xpath;
        this.validationList = $$(By.xpath(this.xpath));
        this.validationListTexts = validationList.texts();
        valid = false;
    }

    public ValidationListHelper(String xpath, int waitForListInSeconds) {
        this.xpath = xpath;
        Utils.isElementPresent(By.xpath(this.xpath), waitForListInSeconds);
        this.validationList = $$(By.xpath(this.xpath));
        this.validationListTexts = validationList.texts();
    }

    /**
     * When using this constructor, you won't be able to use methods that make use for the xpath variable. e.g. waitForListSizeToBeLessThan
     */
    public ValidationListHelper(ElementsCollection validationList) {
        this.xpath = null;
        this.validationList = validationList;
        this.validationListTexts = validationList.texts();
    }

    public boolean isValid() {
        return valid;
    }

    public int getSize() {
        return validationList.size();
    }

    public void validateTextAnyMatchEquals(String... stringToValidate) {
        for (String currentStringToVal : stringToValidate) {
            assertThat(String.format("Failed to validate %s", currentStringToVal), getTextList().stream().anyMatch(s -> s.equals(currentStringToVal)));
        }
    }

    public ValidationListHelper validateTextAnyMatchContains(String... stringToValidate) {
        for (String currentStringToVal : stringToValidate) {
            assertThat(String.format("Failed to validate %s", currentStringToVal), getTextList().stream().anyMatch(s -> s.toLowerCase().contains(currentStringToVal.toLowerCase())));
        }
        return this;
    }

    public boolean isTextFound(String... stringToValidate) {
        for (String currentStringToVal : stringToValidate) {
            boolean found = getTextList().stream().anyMatch(s -> s.toLowerCase().contains(currentStringToVal.toLowerCase()));
            if (!found) return false;
        }
        return true;
    }

    public boolean isTextFoundExactMatch(String... stringToValidate) {
        for (String currentStringToVal : stringToValidate) {
            boolean found = getTextList().stream().anyMatch(s -> s.equals(currentStringToVal));
            if (!found) return false;
        }
        return true;
    }

    public void validateAttributeAnyMatchEquals(String attribute, String... attributeToValidate) {
        List<String> attributeList = getAttributeList(attribute);
        for (String currentAttrToVal : attributeToValidate) {
            assertThat(String.format("Failed to validate %s", currentAttrToVal), attributeList.stream().anyMatch(s -> s.equals(currentAttrToVal)));
        }
    }

    public void validateAttributeAnyMatchContains(String attribute, String... attributeToValidate) {
        List<String> attributeList = getAttributeList(attribute);
        for (String currentAttrToVal : attributeToValidate) {
            assertThat(String.format("Failed to validate %s", currentAttrToVal), attributeList.stream().anyMatch(s -> s.toLowerCase().contains(currentAttrToVal.toLowerCase())));
        }
    }

    public void validateAttributeAllMatchEquals(String attribute, String attributeValToValidate) {
        getAttributeList(attribute).forEach(System.out::print);
        assertThat(String.format("Failed to validate %s", attributeValToValidate), getAttributeList(attribute).stream().allMatch(s -> s.equals(attributeValToValidate)));
    }

    public void validateTextAllMatchEquals(String stringToValidate) {
        assertThat(String.format("Failed to Validate %s", stringToValidate), getTextList().stream().allMatch(s -> s.equals(stringToValidate)));
    }

    public void validateTextAllMatchContains(String stringToValidate) {
        assertThat(String.format("Failed to Validate %s", stringToValidate), getTextList().stream().allMatch(s -> s.toLowerCase().contains(stringToValidate.toLowerCase())));
    }

    public List<String> getTextList() {
        return validationListTexts;
    }

    public List<String> getNewSortedTextListIn(String sortOrder) {
        if (sortOrder.toLowerCase().contains("asc")) {
            return getNewSortedTextListInAsc();
        } else if (sortOrder.toLowerCase().contains("desc")) {
            return getNewSortedTextListInDesc();
        } else {
            return null;
        }
    }

    private List<String> getNewSortedTextListInAsc() {
        List<String> textList = new ArrayList<>(getTextList());
        Collections.sort(textList);
        return textList;
    }

    private List<String> getNewSortedTextListInDesc() {
        List<String> textList = new ArrayList<>(getTextList());
        Collections.sort(textList);
        textList.sort(Collections.reverseOrder());
        return textList;
    }

    public List<String> getAttributeList(String attribute) {
        List<String> attributeList = new ArrayList<>();
        validationList.forEach(s -> attributeList.add(s.getAttribute(attribute)));
        return attributeList;
    }

    public ElementsCollection getValidationList() {
        return validationList;
    }

    public List<Date> convertToDateList(String dateFormat) {
        List<Date> dateList = new ArrayList<>();
        getTextList().forEach(s -> {
            Date date = null;
            try {
                date = new SimpleDateFormat(dateFormat).parse(s.trim());
            } catch (ParseException e) {
                e.printStackTrace();
                System.out.println("Failed to perform convertToDateList() in ValidationListHelper class - " + e.getMessage());
            }
            dateList.add(date);
        });
        return dateList;
    }

    public void validateListSizeIsMoreThan(int num) {
        assertThat(String.format("Failed to Validate List size is more than %s", num), validationList.size() > num);
    }

    public void validateListSizeIsLessThan(int num) {
        assertThat(String.format("Failed to Validate List size is less than %s", num), validationList.size() < num);
    }

    public ValidationListHelper validateListSizeIsEqual(int num) {
        validationList.shouldHaveSize(num);
        return this;
    }

    public ValidationListHelper validateTextAllMatchIsNotEmptyOrNull() {
        assertThat("Failed to Validate elements are not empty or null", getTextList().stream().allMatch(s -> s != null && !s.isEmpty()));
        return this;
    }

    public ValidationListHelper validateTextAllMatchIsEqual() {
        List<String> valList = getTextList();
        assertThat("Failed to Validate elements are all equal", valList.stream().allMatch(valList.get(0)::equals)); //https://www.baeldung.com/java-list-all-equal
        return this;
    }

    public void validateTextAllMatchDoesNotContains(String stringToValidate) {
        assertThat(String.format("Failed to Validate - %s", stringToValidate), getTextList().stream().noneMatch(s -> s.toLowerCase().contains(stringToValidate.toLowerCase())));
    }

    public void validateTextAnyMatchDoesNotContain(String... stringToValidate) {
        for (String currentStringToVal : stringToValidate) {
            boolean result = getTextList().stream().anyMatch(s -> s.strip().equals(currentStringToVal));
            Assertions.assertFalse(result);
        }
    }

    public ValidationListHelper validateListIsNotEmpty() {
        assertThat("Failed to validate that there's any content present", !validationList.isEmpty());
        return this;
    }

    public List<String> formatList(String regex, String replaceWith) {
        List<String> valList = new ArrayList<>();
        getTextList().forEach(l -> valList.add(l.replaceAll(regex, replaceWith).trim()));
        return valList;
    }

    public String getAnyMatchContainsAttribute(String attribute, String value) {
        validateAttributeAnyMatchContains(attribute, value);
        return getAttributeList(attribute).stream().filter(s -> s.toLowerCase().contains(value.toLowerCase())).collect(Collectors.toList()).get(0);
    }

    public void validateTextAnyMatchEquals(List<String> stringToValidate) {
        for (String currentProblem : stringToValidate) {
            assertThat("Failed to validate elements are all equal at - " + currentProblem
                    , getTextList().stream().anyMatch(s -> s.equals(currentProblem)));
        }
    }

    public void validateTextAnyMatchContains(List<String> stringToValidate) {
        for (String currentProblem : stringToValidate) {
            assertThat("Failed to validate elements contain at - " + currentProblem
                    , getTextList().stream().anyMatch(s -> s.toLowerCase().contains(currentProblem.toLowerCase())));
        }
    }

    public String getFullTextFromPartial(String patialText) {
        validateTextAnyMatchContains(patialText);
        return validationList.stream().filter(s -> s.getText().toLowerCase().contains(patialText.toLowerCase())).collect(Collectors.toList()).get(0).getText();
    }

    public SelenideElement getElementAnyMatchContainsText(String text) {
        validateTextAnyMatchContains(text);
        return validationList.stream().filter(s -> s.getText().toLowerCase().contains(text.toLowerCase())).collect(Collectors.toList()).get(0);
    }

    public List<SelenideElement> getElementsAnyMatchContainsText(String text) {
        validateTextAnyMatchContains(text);
        return validationList.stream().filter(s -> s.getText().toLowerCase().contains(text.toLowerCase())).collect(Collectors.toList());
    }

    public List<SelenideElement> getElementsAnyMatchContainsText(String subXpath, String... text) {
        return validationList.stream().filter(s -> {
            boolean found = false;
            List<String> subListText = s.findAll(By.xpath("." + subXpath)).texts();

            for (String currentText : text) {
                found = subListText.stream().anyMatch(g -> g.toLowerCase().contains(currentText.toLowerCase()));
                if (!found) return false;
            }
            return found;
        }).collect(Collectors.toList());
    }

    public void validateTextAllMatchEqualsGaugeTableValues(com.thoughtworks.gauge.Table valueList) {
        GaugeTable gtable = new GaugeTable(valueList);
        List<String> gtableValues = gtable.getKeyListFromGaugeTable();
        List<String> valList = getTextList();
        for (String gtableValue : gtableValues) {
            if (gtableValue.equals("Check Out Time")) {
                gtableValue = (String) ScenarioDataStore.get("checkOutTime");
            }
            if(gtableValue.equals("Today")){
                gtableValue = DateUtils.getDateNow();
            }
            String finalGtableValue = gtableValue;
            System.out.println("finalGtableValue -> " + finalGtableValue);
            assertThat("Failed to validate elements are all equal."
                    , valList.stream().anyMatch(s -> s.toLowerCase().contains(finalGtableValue.toLowerCase())));
        }
    }

    public ValidationListHelper waitForListSizeToBeEqualTo(int size, int timeoutInSeconds) {
        boolean isCorrectSizeFound = false;
        int counter = 0;

        while (!isCorrectSizeFound && counter < timeoutInSeconds) {
            delayForResponse();
            isCorrectSizeFound = new ValidationListHelper(this.xpath).getSize() == size;
            counter++;
        }
        assertThat("Failed to wait for List to be the correct size - " + size, isCorrectSizeFound);
        return new ValidationListHelper(this.xpath);
    }

    public ValidationListHelper waitForListSizeToBeMoreThan(int size, int timeoutInSeconds) {
        boolean isCorrectSizeFound = false;
        int counter = 0;

        while (!isCorrectSizeFound && counter < timeoutInSeconds) {
            delayForResponse();
            isCorrectSizeFound = new ValidationListHelper(this.xpath).getSize() > size;
            counter++;
        }
        assertThat("Failed to wait for List to be the More Than size - " + size, isCorrectSizeFound);
        return new ValidationListHelper(this.xpath);
    }

    public ValidationListHelper waitForListSizeToBeMoreThanStripBlank(int size, int timeoutInSeconds) {
        boolean isCorrectSizeFound = false;
        int counter = 0;

        while (!isCorrectSizeFound && counter < timeoutInSeconds) {
            delayForResponse();
            ValidationListHelper listHelper = new ValidationListHelper(this.xpath);
            List<String> list = listHelper.getTextList();
            list.removeAll(Arrays.asList("", null));
            isCorrectSizeFound = list.size() > size;
            counter++;
        }
        assertThat("Failed to wait for List to be the More Than size - " + size, isCorrectSizeFound);
        return new ValidationListHelper(this.xpath);
    }

    public ValidationListHelper waitForListSizeToBeMoreThanOrEqualTo(int size, int timeoutInSeconds) {
        boolean isCorrectSizeFound = false;
        int counter = 0;

        while (!isCorrectSizeFound && counter < timeoutInSeconds) {
            delayForResponse();
            isCorrectSizeFound = new ValidationListHelper(this.xpath).getSize() >= size;
            counter++;
        }
        assertThat("Failed to wait for List to be the More Than size - " + size, isCorrectSizeFound);
        return new ValidationListHelper(this.xpath);
    }

    public boolean waitForListNoAssert(int size, int timeoutInSeconds) {
        int counter = 0;

        while (counter < timeoutInSeconds) {
            delayForResponse();
            List<String> list = new ValidationListHelper(this.xpath).getTextList();
            if (list.size() >= size) {
                if (checkIfListHadEmptyOrNull(list)) {
                    return true;
                }
            }
            counter++;
        }
        return false;
    }

    private boolean checkIfListHadEmptyOrNull(List<String> list) {
        for (String item : list) {
            if (Objects.isNull(item)) {
                return false;
            }
            if (item.length() < 1) {
                return false;
            }
        }
        return true;
    }

    public ValidationListHelper waitForListSizeToBeLessThan(int size, int timeoutInSeconds) {
        boolean isCorrectSizeFound = false;
        int counter = 0;

        while (!isCorrectSizeFound && counter < timeoutInSeconds) {
            delayForResponse();
            isCorrectSizeFound = new ValidationListHelper(this.xpath).getSize() < size;
            counter++;
        }
        assertThat("Failed to wait for List to be the Less Than size - " + size, isCorrectSizeFound);
        return new ValidationListHelper(this.xpath);
    }

    public void waitForListSizeToIncludeText(String text, int timeoutInSeconds) {
        boolean isTextFound = false;
        int counter = 0;

        while (!isTextFound && counter < timeoutInSeconds) {
            delayForResponse();
            isTextFound = new ValidationListHelper(this.xpath).getTextList().stream().anyMatch(s -> s.toLowerCase().contains(text.toLowerCase()));
            counter++;
        }
        assertThat("Failed to wait for List to include Text - " + text, isTextFound);
        new ValidationListHelper(this.xpath);
    }

    private void delayForResponse() {
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

