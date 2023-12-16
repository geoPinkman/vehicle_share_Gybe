package com.greenGekko.configs;

import com.greenGekko.GreenGekkoApplication;
import com.greenGekko.integrations.MailClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@Configuration
@ConditionalOnBean(GreenGekkoApplication.class)

public class MailConfig {

    @Bean("mail")
    public MailClient mailClient(
            @Value("${mailgun.apiKey}") String apiKey,
            @Value("${mailgun.domain}") String domain,
            @Value("${mailgun.senderEmail}")String senderEmail
    ) {
        return new MailClient(apiKey, domain, senderEmail);
    }

}
