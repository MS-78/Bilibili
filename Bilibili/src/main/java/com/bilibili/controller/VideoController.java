package com.bilibili.controller;

import com.bilibili.mapper.VideoMapper;
import com.bilibili.pojo.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/Video")
public class VideoController {
    @Autowired
    private VideoMapper videoMapper;

    @PostMapping("/GetImgURL")
    public String GetImgURL(@RequestParam("VideoID") int VideoID) {
        Video video = videoMapper.queryByID(VideoID);
        return "URL"+video.getImgName();
    }

    @GetMapping("/GetAllVideo")
    public List<Video> GetAllVideo() {
        return videoMapper.queryVideoList();
    }
}
