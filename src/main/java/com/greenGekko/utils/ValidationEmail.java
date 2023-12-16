package com.greenGekko.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.EmailValidator;
@Slf4j
public class ValidationEmail {

    public static boolean isValid(String email) {
        EmailValidator validator = EmailValidator.getInstance();
        return validator.isValid(email);
    }

}
