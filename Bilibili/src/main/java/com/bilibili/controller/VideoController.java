package com.bilibili.controller;

import org.apache.catalina.Session;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/Video")
public class VideoController {
    @RequestMapping(method = RequestMethod.GET,value = "/Play")
    public boolean Play(HttpServletRequest request) {
        System.out.println(request.getSession().getId());
        return true;
    }
}
