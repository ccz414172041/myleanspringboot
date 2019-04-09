package com.ccz.rabbitmq_demo.mq;

import com.ccz.rabbitmq_demo.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class MsgReceiver {


    @RabbitListener(queues = RabbitConfig.QUEUE_A)
    @RabbitHandler
    public void process_a(String content) {
        log.info("a_接收处理队列A当中的消息： " + content);
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_A)
    @RabbitHandler
    public void process_b(String content) {
        log.info("b_接收处理队列A当中的消息： " + content);
    }

}
