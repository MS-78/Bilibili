package com.bilibili.controller;

import com.bilibili.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {

    @RequestMapping(method = RequestMethod.GET,value = "/test")
    public String index(@RequestParam("username") String name){

        User user = new User();
        System.out.println(name);
        return "hahaha";
    }

}
/**
 * 各位晚上好，今天的情况是电脑没有音响，也没有麦克风
 * 所以我今晚就是中琰
 * 1.面瘫
 *  结题时间暂定于11月31日前
 *      （0）.前端聊天界面展示问题
 *      （1）.前后端连接问题
 *      （2）.apk上线应用商店
 * 2.管理系统
 *  后端大佬张富玮同学已经完成了
 *  前端vue,jsp,h5
 * 3.好停车
 *  赵李灏同学大致已经了解了业务流程，这个星期天之前做出架构
 *  前端先做PC端
 * 4.易途杯
 * 5.关于项目的未来发展
 */
