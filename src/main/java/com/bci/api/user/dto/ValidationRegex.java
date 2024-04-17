package com.bci.api.user.dto;

public class ValidationRegex {

    public static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    public static final String PASSWORD_REGEX = "^[a-zA-Z0-9@#$%^&+=]{6,}$";
}
