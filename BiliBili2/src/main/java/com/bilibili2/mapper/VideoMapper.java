package com.bilibili2.mapper;



import com.bilibili2.pojo.Video;

import java.util.List;


public interface VideoMapper {
    List<Video> queryVideoList();
    Video queryByID(int UUID);
    int addVideo(Video video);
    int updateVideo(Video video);
    int deleteUser(int UUID);
}
