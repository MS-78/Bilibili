package com.bilibili.mapper;

import com.bilibili.pojo.Video;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VideoMapper {
    List<Video> queryVideoList();
    Video queryByID(int UUID);
    int addVideo(Video video);
    int updateVideo(Video video);
    int deleteUser(int UUID);
}
