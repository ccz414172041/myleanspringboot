package com.caihyspace.statictest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Cai Haoyun
 * @Description: 自定义责任链配置注解
 * @date 2021/4/26
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FilterConfig {

    /**
     * 在链中的顺序
     * @return
     */
    int order() default 0;

    /**
     * 责任链所属key
     * @return
     */
    String chainKey();

}
