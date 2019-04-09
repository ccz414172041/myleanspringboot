package com.ccz.shiroproject.controller;

import com.ccz.shiroproject.util.Result;
import com.ccz.shiroproject.util.ResultGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping(value = "helloWorld")
    public Result helloWorld(){
        return ResultGenerator.genSuccessResult("SUCCESS");
    }

}
