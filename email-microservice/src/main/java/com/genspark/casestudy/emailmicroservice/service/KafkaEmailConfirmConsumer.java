package com.genspark.casestudy.emailmicroservice.service;

import com.genspark.kafka.model.EmailConfirmation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaEmailConfirmConsumer {

    @Autowired
    private EmailService emailService;

    @Autowired
    private SimpleMailMessage template;

    @KafkaListener( topics="Email-Confirmation", groupId="Group1")
    public void consumeData(EmailConfirmation emailConfirm){

        log.info(String.format("Received message -> %s", emailConfirm));
        String text = String.format(template.getText(), emailConfirm.getOrderDate(),
                emailConfirm.getOrderConfirmation(), emailConfirm.getCustomerName(),
                emailConfirm.getCustomerEmail(),emailConfirm.getCustomerAddress(),
                emailConfirm.getOrderItemName(),emailConfirm.getOrderItemQty(),
                emailConfirm.getOrderItemPrice(),emailConfirm.getOrderTotal());

        emailService.sendSimpleMessage(emailConfirm.getCustomerEmail(),
                "Your Med-Bed order confirmation", text);

    }
}
