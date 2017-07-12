package com.example.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = RabbitConfig.QU_P2P_HELLO)
public class Receiver {

    @RabbitHandler
    public void process(String msg) {
        System.out.println("******************Receiver : " + msg);
    }
}