package com.bilibili2.controller;

import com.bilibili2.mapper.UserMapper;
import com.bilibili2.pojo.User;
import com.bilibili2.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    SqlSession sqlSession = MybatisUtil.getSqlSession();
    private UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

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

    @PostMapping("/Register")
    public User Register(@RequestParam("UserPhone") String UserPhone,
                            @RequestParam("UserPwd") String UserPwd) {
        User user = userMapper.queryUserByPhone(UserPhone);
        if (user != null) {
            if (UserPwd.equals(user.getUserPwd()))
                return user;
            else
                return null;
        } else {
            User user1 = new User(0,UserPhone,null,UserPwd);
            userMapper.addUser(user1);
            user1 = userMapper.queryUserByPhone(UserPhone);
            return user1;
        }

    }
}
