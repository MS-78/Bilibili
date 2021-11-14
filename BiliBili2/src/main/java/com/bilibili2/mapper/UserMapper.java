package com.bilibili2.mapper;



import com.bilibili2.pojo.User;

import java.util.List;


public interface UserMapper {
    List<User> queryUserList();
    User queryUserById(int UUID);
    int addUser(User user);
    int updateUser(User user);
    int deleteUser(int UUID);
    User queryUserByPhone(String UserPhone);
}
