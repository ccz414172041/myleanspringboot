package com.ccz.redisdemo.mq;

import com.ccz.redisdemo.config.rabbitmq.RabbitMqConfig;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Sender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送order给mq
     *
     * @param obj
     */
    public void setObject(Object obj) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE, RabbitMqConfig.ROUTINGKEY2, obj, correlationData);
    }

    public void setObj2(Object obj2) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE, RabbitMqConfig.ROUTINGKEY1, obj2, correlationData);
    }

}
