package com.ccz.redisdemo;

import com.ccz.redisdemo.entity.User;
import com.ccz.redisdemo.mq.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisdemoApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private Sender sender;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testTemplate() {
        User user = (User) redisTemplate.opsForValue().get("user");
        List<User> userList = null;
        if (user == null) {
            System.out.println("见来一个");
            User user1 = new User();
            user1.setAge(10);
            user1.setName("蔡皓云");
            redisTemplate.opsForValue().set("user", user1);
        } else {
            System.out.println(user.getName());
        }
    }

    @Test
    public void testRabbitMqFirst(){
        sender.setObject("abcdefg");
    }

    @Test
    public void testRabbitMqFirst2(){
        sender.setObj2("12344");
    }

}
