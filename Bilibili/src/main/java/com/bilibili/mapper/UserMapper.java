package com.bilibili.mapper;

import com.bilibili.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> queryUserList();
    User queryUserById(int UUID);
    int addUser(User user);
    int updateUser(User user);
    int deleteUser(int UUID);
    User queryUserByPhone(String UserPhone);
}
