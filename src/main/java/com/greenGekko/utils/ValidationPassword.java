package com.greenGekko.utils;

import java.util.regex.Pattern;

public class ValidationPassword {

    private static final String PASSWORD_REGEX =
            "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}$";

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile(PASSWORD_REGEX);

    public static boolean isValid(String password) {
        return PASSWORD_PATTERN.matcher(password).matches();
    }
}
