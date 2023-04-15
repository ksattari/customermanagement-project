package com.genspark.casestudy.emailmicroservice.config;

import com.genspark.casestudy.emailmicroservice.utility.EmailTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

@Configuration
public class EmailTemplateConfig {

    @Bean
    public SimpleMailMessage templateSimpleMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText(EmailTemplate.TEXT);
        return message;
    }
}
