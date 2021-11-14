package com.bilibili2.controller;

import com.bilibili2.mapper.VideoMapper;
import com.bilibili2.pojo.Video;
import com.bilibili2.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/Video")
public class VideoController {

    SqlSession sqlSession = MybatisUtil.getSqlSession();
    private VideoMapper videoMapper = sqlSession.getMapper(VideoMapper.class);

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
