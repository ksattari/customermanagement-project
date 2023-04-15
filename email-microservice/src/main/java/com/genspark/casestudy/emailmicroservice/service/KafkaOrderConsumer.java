package com.genspark.casestudy.emailmicroservice.service;

import com.genspark.kafka.model.EmailConfirmation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaOrderConsumer {


    @KafkaListener( topics="Email-Confirmation", groupId="Group1")
    public void consumeData(EmailConfirmation emailConfirm){

        log.info(String.format("Received message -> %s", emailConfirm));

    }
}
