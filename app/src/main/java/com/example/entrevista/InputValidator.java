package com.example.entrevista;

public final class InputValidator {
    private InputValidator() { }
    public static boolean isPhoneValid(String v) { return v != null && v.matches("[0-9]{1,8}"); }
    public static boolean isIdentityValid(String v) { return v != null && v.matches("[0-9]{1,10}"); }
    public static boolean isComplementValid(String v) { return v != null && v.matches("[a-zA-Z0-9]{0,2}"); }
}
