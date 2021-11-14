package com.bilibili2.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bilibili2.mapper.ChatMapper;
import com.bilibili2.mapper.VideoMapper;
import com.bilibili2.pojo.Chat;
import com.bilibili2.pojo.Video;
import com.bilibili2.util.MybatisUtil;
import com.bilibili2.util.VideoWatching;
import com.bilibili2.util.WebSocketMapUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;

@Component
@ServerEndpoint(value = "/websocket/service")
public class WebSocketServer {
    private Session session;


    SqlSession sqlSession = MybatisUtil.getSqlSession();
    private VideoMapper videoMapper = sqlSession.getMapper(VideoMapper.class);
    private ChatMapper chatMapper = sqlSession.getMapper(ChatMapper.class);

    /**
     * 连接建立后触发的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        WebSocketMapUtil.put(session.getId(), this);
        System.out.println(session.getId());
    }

    /**
     *连接关闭后触发的方法
     */
    @OnClose
    public void onClose() {
        //从map中删除
        WebSocketMapUtil.remove(session.getId());
    }

    /**
     *接收到客户端消息时触发的方法
     */
    @OnMessage
    public void onMessage(String params, Session session) throws Exception {
        String result = "收到来自" + session.getId() + "的消息" + params;
        System.out.println(result);

        Chat chat = JSONObject.parseObject(params,Chat.class);
        Video video = videoMapper.queryByID(chat.getTo_videoID());
        System.out.println(video.toString());
        System.out.println(chat.getUUID());
        if (chat.getUUID() == 1) {  //请求视频的websocket信息
            VideoWatching.Watching.put(session.getId(),chat.getTo_videoID());
            List<Chat> chats = chatMapper.queryListByVideoID(video.getUUID());
            this.sendVideoURL(session.getId(),
                    "http://www.zlhpro.online/"+video.getName(),
                    JSONArray.parseArray(JSON.toJSONString(chats)));
            video.setPlayCount(video.getPlayCount()+1);
            videoMapper.updateVideo(video);
        } else if(chat.getUUID() == 2) {
            for (String uid : WebSocketMapUtil.webSocketMap.keySet()) {
                if (uid != session.getId())
                    if (VideoWatching.Watching.get(uid) == video.getUUID())  //正在看此弹幕的视频
                        this.sendChat(uid,chat);
            }
            chatMapper.addChat(chat); //向数据库内保存chat
        } else {
            for (String uid : WebSocketMapUtil.webSocketMap.keySet())
                this.sendTest(uid);
        }


    }

    public void sendMessage(int code, String msg, String data) throws IOException {
        JSONObject result = new JSONObject();
        result.put("code", code);
        result.put("msg", msg);
        result.put("data", data);
        this.session.getBasicRemote().sendText(result.toString());

    }


    /**
     * 向指定客户端发送消息
     * @param uid 客户端id
     */
    public synchronized void sendTest(String uid){
        try {
            WebSocketServer myWebSocket = WebSocketMapUtil.get(uid);
            myWebSocket.sendMessage(0,"Test","null");
        }catch (Exception e){
            e.getMessage();
        }
    }

    /**
     * 此函数用于向请求视频播放的客户端发送视频URL和视频时间与弹幕映射表
     * @param uid 向谁发送
     * @param URL video的URL
     * @param jsonArray 弹幕的JSON数组
     */
    public synchronized void sendVideoURL(String uid, String URL, JSONArray jsonArray){
        try {
            WebSocketServer socketServer = WebSocketMapUtil.get(uid);
            socketServer.sendMessage(1,URL,jsonArray.toJSONString());
        } catch (Exception e) {
            String message = e.getMessage();
            System.out.println(message);
            e.printStackTrace();
        }
    }

    /**
     * 此函数用于向
     * @param uid
     * @param chat
     */
    public synchronized void sendChat(String uid,Chat chat) {
        try {
            WebSocketServer socketServer = WebSocketMapUtil.get(uid);
            socketServer.sendMessage(2,"Chat",JSONObject.toJSONString(chat));
        } catch (Exception e) {
            String message = e.getMessage();
            System.out.println(message);
            e.printStackTrace();
        }
    }
}
