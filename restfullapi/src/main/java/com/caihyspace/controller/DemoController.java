package com.caihyspace.controller;

import com.caihyspace.entity.User;
import com.ccz.starter.json.service.JsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Cai Haoyun
 * @Description:
 * @date 2021/5/21
 */
@RestController
@RequestMapping("demo")
public class DemoController {
    @Autowired
    JsonService jsonService;

    @GetMapping("/test")
    public String test() {
        User user = new User(1, "ccz", 20);
        return jsonService.object2MyJson(user);
    }

}
