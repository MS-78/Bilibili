package com.bilibili.controller;

import com.bilibili.mapper.UserMapper;
import com.bilibili.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/queryUserList")
    public List<User> queryUserList(){
        List<User> users = userMapper.queryUserList();
        for (User user : users) {
            System.out.println(user);
        }
        return users;
    }

    @PostMapping("/Login")
    public boolean Login(@RequestParam("UserPhone") String UserPhone,
                         @RequestParam("UserPwd") String UserPwd) {
        User user = userMapper.queryUserByPhone(UserPhone);
        if (user == null)
            return false;
        if (UserPwd.equals(user.getUserPwd())) {
            return true;
        } else {
            return false;
        }
    }
}
