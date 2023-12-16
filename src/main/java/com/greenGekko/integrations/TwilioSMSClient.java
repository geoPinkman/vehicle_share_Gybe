package com.greenGekko.integrations;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TwilioSMSClient {

    public static final String baseUrl = "https://api.twilio.com/2010-04-01/Accounts/";

    private final String serviceId;
    private final String userLogin;
    private final String userPassword;

    public String sendSMessage(String recipient, String verificationCode) {
        String url = baseUrl + userLogin + "/Messages.json";
        String body = "your verification code is: " + verificationCode;

        HttpResponse<String> response = Unirest.post(url)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .basicAuth(userLogin, userPassword)
                .field("To",recipient)
                .field("MessagingServiceSid", serviceId)
                .field("Body", body)
                .asString();
        if (!response.isSuccess()) {
            throw new RuntimeException("Twilio failed: " + response.getStatus() + " " + response.getBody());
        }
        return response.getBody();
    }

}
