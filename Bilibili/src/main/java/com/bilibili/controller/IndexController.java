package com.bilibili.controller;

import com.bilibili.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {

    @RequestMapping(method = RequestMethod.GET,value = "/test")
    public String index(@RequestParam("username") String name){

        User user = new User();
        System.out.println(name);
        return "hahaha";
    }

}
