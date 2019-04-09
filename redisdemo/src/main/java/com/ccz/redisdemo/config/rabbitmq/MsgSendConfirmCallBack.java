package com.ccz.redisdemo.config.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.lang.Nullable;

@Slf4j
public class MsgSendConfirmCallBack implements RabbitTemplate.ConfirmCallback {
    @Override
    public void confirm(@Nullable CorrelationData correlationData, boolean ack, @Nullable String cause) {
        log.error("MsgSendConfirmCallBack, 回调ID：" + correlationData);
        if (ack)
            log.info("消息消费成功");
        else
            log.error("消息消费失败" + cause + "\t重新发送");
    }
}
