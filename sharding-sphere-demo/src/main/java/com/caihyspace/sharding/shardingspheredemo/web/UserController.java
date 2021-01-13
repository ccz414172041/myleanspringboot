package com.caihyspace.sharding.shardingspheredemo.web;

import com.caihyspace.sharding.shardingspheredemo.entity.User;
import com.caihyspace.sharding.shardingspheredemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/select")
    public List<User> select() {
        return userService.getUserList();
    }

    @GetMapping("/insert")
    public void insert(@RequestBody User user) {
        userService.save(user);
    }



}
