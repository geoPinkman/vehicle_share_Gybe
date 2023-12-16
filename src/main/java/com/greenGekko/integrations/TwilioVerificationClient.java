package com.greenGekko.integrations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@AllArgsConstructor
public class TwilioVerificationClient {
    public static final String baseUrl = "https://verify.twilio.com/v2/Services/";

    private final String verificationId;
    private final String userLogin;
    private final String userPassword;

    @SneakyThrows(JsonProcessingException.class)
    public String sendVerificationToUser(String phoneNumber) {
        ObjectMapper mapper = new ObjectMapper();
        String url = baseUrl + verificationId + "/Verifications";

        HttpResponse<String> response = Unirest.post(url)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .basicAuth(userLogin, userPassword)
                .field("To", phoneNumber)
                .field("Channel", "sms")
                .asString();
        if (!response.isSuccess()) {
            throw new RuntimeException("Twilio failed: " + response.getStatus() + " " + response.getBody());
        }
        return mapper.readTree(response.getBody())
                .get("status")
                .asText();
    }
    @SneakyThrows(JsonProcessingException.class)
    public String getVerificationStatus(String phoneNumber, String code) {
        ObjectMapper mapper = new ObjectMapper();
        String url = baseUrl + verificationId + "/VerificationCheck";

        HttpResponse<String> response = Unirest.post(url)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .basicAuth(userLogin, userPassword)
                .field("To", phoneNumber)
                .field("Code", code)
                .asString();
        if (!response.isSuccess()) {
            throw new RuntimeException("Twilio failed: " + response.getStatus() + " " + response.getBody());
        }
        return mapper.readTree(response.getBody())
                .get("status")
                .asText();
    }
}
