package com.ccz.redisdemo.mq;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer {


    @RabbitListener(queues = {"second-queue"},containerFactory = "rabbitListenerContainerFactory")
    public void handlerMessage(String message){
        log.info("consumer——msg:"+message);
    }

}
