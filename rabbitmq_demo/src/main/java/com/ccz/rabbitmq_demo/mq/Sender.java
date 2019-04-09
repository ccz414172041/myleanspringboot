package com.ccz.rabbitmq_demo.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class Sender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String msg) {
        String context = "hello" + new Date();
        log.info("sender:" + msg + ",date" + context);
        rabbitTemplate.convertAndSend("hello",context);
    }

}
