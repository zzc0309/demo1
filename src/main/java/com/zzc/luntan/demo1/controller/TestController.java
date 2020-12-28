package com.zzc.luntan.demo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
@ResponseBody
public class TestController {
    @RequestMapping("/1/1")
    public String test(){
        return "test1 ok";
    }

    @RequestMapping("/2/2")
    public String test2(){
        return "test2 ok";
    }
}
