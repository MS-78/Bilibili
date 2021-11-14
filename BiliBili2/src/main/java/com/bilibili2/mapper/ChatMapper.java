package com.bilibili2.mapper;



import com.bilibili2.pojo.Chat;

import java.util.List;


public interface ChatMapper {
    List<Chat> queryListByVideoID(int UUID);
    int addChat(Chat chat);

}
