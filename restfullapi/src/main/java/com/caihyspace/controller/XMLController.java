package com.caihyspace.controller;

import com.caihyspace.entity.User;
import com.caihyspace.exception.CustomException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/xml")
public class XMLController {

    @PostMapping(value = "/user",consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public User create(@RequestBody User user) {
        user.setName("xxxxx:" + user.getName());
        user.setAge(user.getAge() + 100);
        return user;
    }

}
