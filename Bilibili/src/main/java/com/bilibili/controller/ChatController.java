package com.bilibili.controller;

import com.bilibili.pojo.Chat;
import com.bilibili.service.WebSocketServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Resource
    private WebSocketServer myWebSocket;

    /**
     * 向客户端发送消息
     * @param uid 客户端连接id
     * @return
     */
//    @GetMapping("/send")
//    public String send(Chat chat){
//        myWebSocket.send(uid);
//        System.out.println("@GetMapping(\"/send\")"+uid);
//        return "OK";
//    }
}
