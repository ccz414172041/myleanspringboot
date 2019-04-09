/*
package com.ccz.rabbitmq_demo.mq;

import com.ccz.rabbitmq_demo.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = RabbitConfig.QUEUE_A)
@Slf4j
public class MsgReceiver_B {


    @RabbitHandler
    public void process(String content) {
        log.info("MsgReceiver_B接收处理队列A当中的消息： " + content);
    }

}

*/
