package com.ccz.rabbitmq_demo;

import com.ccz.rabbitmq_demo.mq.MsgProduct;
import com.ccz.rabbitmq_demo.mq.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqDemoApplicationTests {

    @Autowired
    private Sender sender;
    @Autowired
    private MsgProduct msgProduct;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testHello() throws Exception {
        sender.send("abc");
    }

    @Test
    public void testQueueA() throws Exception {
        for (int i = 0; i < 10; i++) {
            msgProduct.sendMsg("这是第" + i + "条消息A");
        }
    }

}
