package com.example.entrevista;

import org.junit.Test;
import static org.junit.Assert.*;

public class InputValidatorTest {
    @Test public void phoneAcceptsOnlyOneToEightDigits() {
        assertTrue(InputValidator.isPhoneValid("0"));
        assertTrue(InputValidator.isPhoneValid("71234567"));
        for (String value : new String[]{"", "123456789", "+7123456", "12 34", "abc", "１２３", "123\n"})
            assertFalse(value, InputValidator.isPhoneValid(value));
        assertFalse(InputValidator.isPhoneValid(null));
    }
    @Test public void identityAcceptsOnlyOneToTenDigits() {
        assertTrue(InputValidator.isIdentityValid("0012345678"));
        for (String value : new String[]{"", "12345678901", "12-34", "a123", " 123"})
            assertFalse(value, InputValidator.isIdentityValid(value));
    }
    @Test public void complementIsOptionalAndAsciiAlphanumeric() {
        for (String value : new String[]{"", "A", "1D", "a9", "00"})
            assertTrue(value, InputValidator.isComplementValid(value));
        for (String value : new String[]{"ABC", "ñ", "A-", " ", "1\n"})
            assertFalse(value, InputValidator.isComplementValid(value));
    }
    @Test public void demoRejectsInvalidInformation() {
        assertTrue(DemoService.submitInformation("71234567", "412345", ""));
        assertFalse(DemoService.submitInformation("", "412345", "1D"));
        assertFalse(DemoService.submitInformation("71234567", "bad", "1D"));
        assertFalse(DemoService.submitInformation("71234567", "412345", "!!!"));
    }
}
