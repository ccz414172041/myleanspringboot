package com.ccz.rabbitmq_demo.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Receiver {

    @RabbitListener(queues = "hello")
    @RabbitHandler
    public void receive(String text) {
        log.info("text" + text);
    }

}
