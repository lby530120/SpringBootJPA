package com.example.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Sender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String str) {
        String context = str  + new Date();
        System.out.println("------------------Sender : " + context);
        this.rabbitTemplate.convertAndSend(RabbitConfig.QU_P2P_HELLO, context);
    }
}