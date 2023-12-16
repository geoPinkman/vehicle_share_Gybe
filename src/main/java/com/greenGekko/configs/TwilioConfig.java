package com.greenGekko.configs;

import com.greenGekko.GreenGekkoApplication;
import com.greenGekko.integrations.TwilioSMSClient;
import com.greenGekko.integrations.TwilioVerificationClient;
import com.greenGekko.utils.ValidationPhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@Configuration
@ConditionalOnBean(GreenGekkoApplication.class)

public class TwilioConfig {

    @Bean("sms")
    public TwilioSMSClient twilioClient(
            @Value("${twilio.serviceId}")String serviceId,
            @Value("${twilio.userLogin}")String userLogin,
            @Value("${twilio.userPassword}")String userPassword) {
        return new TwilioSMSClient(serviceId, userLogin, userPassword);
    }
    @Bean("verification")
    public TwilioVerificationClient twilioVerificationClient(
            @Value("${twilio.verificationId}")String verificationId,
            @Value("${twilio.userLogin}")String userLogin,
            @Value("${twilio.userPassword}")String userPassword) {
        return new TwilioVerificationClient(verificationId, userLogin, userPassword);

    }

    @Bean("validation")
    public ValidationPhoneNumber validationPhoneNumber(
            @Value("${twilio.userLogin}")String userLogin,
            @Value("${twilio.userPassword}")String userPassword
    ) {
        return new ValidationPhoneNumber(userLogin, userPassword);
    }
}
