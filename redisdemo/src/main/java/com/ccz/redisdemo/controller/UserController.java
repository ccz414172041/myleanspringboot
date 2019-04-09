package com.ccz.redisdemo.controller;

import com.ccz.redisdemo.entity.User;
import com.ccz.redisdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UserController {



    @Autowired
    private UserService userService;

    @GetMapping("/users")
    @ResponseBody
    public List<Object> users() {
        return userService.list();
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public User findUserById(@PathVariable("id") Long id) {
        return userService.findUserById(id);
    }

    @GetMapping("/upuser/{id}")
    @ResponseBody
    public User upuser(@PathVariable("id") Long id) {
        return userService.upuser(id);
    }


    @GetMapping("/user/{id}/{name}")
    @ResponseBody
    public String update(@PathVariable("id") Long id, @PathVariable("name") String name) {
        User user = userService.findUserById(id);
        user.setName(name);
        userService.update(user);
        return "ok";
    }

}
