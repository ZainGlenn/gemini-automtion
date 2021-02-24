package com.gemini.helpers;

import com.gemini.util.components.WebAutomationObject;

import java.util.List;
import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;

public class ValidationHelper {
    public static boolean validateEquals(String field, String expected, String actual) {
        assertThat(messageValidationFormat(field, expected, actual), actual.equals(expected));
        return actual.equals(expected);
    }
    public static void validateDoesNotEquals(String field, String expected, String actual) {
        assertThat(messageValidationFormat(field, expected, actual), !actual.equals(expected));
    }

    public static void validateObjectEquals(String field, Object expected, Object actual) {
        assertThat(messageValidationFormat(field, expected.toString(), actual.toString()), actual.equals(expected));
    }

    public static void validateContains(String field, String expected, String actual) {
        boolean result = expected.contains(actual);
        assertThat(messageValidationFormat(field, expected, actual), result);
    }

    public static void anyMatchContains(String field, List<String> validationList, String actual) {
        boolean result = validationList.stream().anyMatch(s -> s.contains(actual));
        assertThat(messageValidationFormat(field, validationList.toString(), actual), result);
    }

    public static void validateNotNull(String field, String value) {
        assertThat(messageValidationFormatNull(field, value), !Objects.isNull(value));
    }

    public static String messageValidationFormat(String field, String expected, String actual) {
        return String.format("Failed to validate '%s' expected '%s' got '%s'", field, expected, actual);
    }

    public static String messageValidationFormatNull(String field, String value) {
        return String.format("Failed to validate '%s' is null actual value is '%s'", field, value);
    }

    public static void validateInputSkipIfEmpty(WebAutomationObject textBox, String data) {
        if (!skipIfEmpty(data)) {
            textBox.validateInputText(data);
        }
    }

    public static void validateLabelSkipIfEmpty(WebAutomationObject dropdown, String data) {
        if (!skipIfEmpty(data)) {
            dropdown.andContainsText(data);
        }
    }

    private static boolean skipIfEmpty(String value) {
        return value.length() < 1;
    }
}
