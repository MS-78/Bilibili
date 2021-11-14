package com.bilibili2;

import com.alibaba.fastjson.JSONObject;
import com.bilibili2.mapper.UserMapper;
import com.bilibili2.pojo.Chat;
import com.bilibili2.pojo.User;
import com.bilibili2.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class BiliBili2ApplicationTests {

    @Test
    void contextLoads() {
        Chat chat = new Chat(1,1,1,"hah",0);
        System.out.println(JSONObject.toJSONString(chat));
    }

}
