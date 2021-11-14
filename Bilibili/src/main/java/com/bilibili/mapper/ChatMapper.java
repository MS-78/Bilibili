package com.bilibili.mapper;

import com.bilibili.pojo.Chat;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatMapper {
    List<Chat> queryListByVideoID(int UUID);
    int addChat(Chat chat);

}
