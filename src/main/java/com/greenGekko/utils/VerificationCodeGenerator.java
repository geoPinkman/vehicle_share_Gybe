package com.greenGekko.utils;

public class VerificationCodeGenerator {

    public static String codeGenerator(int lengthString) {
        if (lengthString > 16) {
            lengthString = 16;
        }
        String stringOfNumbers = String.valueOf(Math.random());
        int startIndex = 3;
        return stringOfNumbers.substring(startIndex, startIndex + lengthString);
    }

}
