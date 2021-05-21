package com.ccz.starter.json.config;

import com.ccz.starter.json.service.JsonService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Cai Haoyun
 * @Description:
 * @date 2021/5/19
 */
@Configuration // 标识配置类
@ConditionalOnClass(JsonService.class) // 表示只有指定的class在classpath上时才能被注册
@EnableConfigurationProperties(MyJsonProperties.class)
public class MyJsonConfiguration {

    private MyJsonProperties myJsonProperties;

    public MyJsonConfiguration(MyJsonProperties myJsonProperties) {
        this.myJsonProperties = myJsonProperties;
    }

    @Bean
    @ConditionalOnClass(JsonService.class)
    public JsonService myJsonService() {
        JsonService myJsonService = new JsonService();
        myJsonService.setPrefixName(myJsonProperties.getPrefixName());
        myJsonService.setSuffixName(myJsonProperties.getSuffixName());
        return myJsonService;
    }

}
