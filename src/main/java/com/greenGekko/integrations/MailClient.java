package com.greenGekko.integrations;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MailClient {
    public static final String baseUrl = "https://api.mailgun.net/v3/";

    private final String apiKey;
    private final String domain;
    private final String senderEmail;

    public String sendMessage(String recipient, String subject, String text) {
        String url = baseUrl + domain + "/messages";
        HttpResponse<String> response = Unirest.post(url)
                .basicAuth("api", apiKey)
                .field("from", senderEmail)
                .field("to", recipient)
                .field("subject", subject)
                .field("text", text)
                .asString();

        if (!response.isSuccess()) {
            throw new RuntimeException("Mailgun failed: " + response.getStatus() + " " + response.getBody());
        }
        return response.getBody();
    }
}

