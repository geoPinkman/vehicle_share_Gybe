package com.greenGekko.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@AllArgsConstructor
public class ValidationPhoneNumber {

    public static final String baseUrl = "https://lookups.twilio.com/v2/";

    private final String userLogin;
    private final String userPassword;

    public Boolean isValid(String phoneNumber) {
        ObjectMapper mapper = new ObjectMapper();
        String url = baseUrl + "PhoneNumbers/" + phoneNumber;

        try {
            HttpResponse<String> response = Unirest.get(url)
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .basicAuth(userLogin, userPassword)
                    .asString();
            if (!response.isSuccess()) {
                throw new RuntimeException("Twilio failed: " + response.getStatus() + " " + response.getBody());
            }
            return mapper.readTree(response.getBody())
                    .get("valid")
                    .asBoolean();

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}

