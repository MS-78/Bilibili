package com.bilibili.service;

import cn.hutool.json.JSONObject;
import com.bilibili.util.WebSocketMapUtil;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@Component
@ServerEndpoint(value = "/websocket/service")
public class WebSocketServer {
    private Session session;

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
        //获取服务端到客户端的通道
        for (String uid : WebSocketMapUtil.webSocketMap.keySet()) {
            WebSocketServer myWebSocket = WebSocketMapUtil.get(uid);
            //返回消息给Web Socket客户端（浏览器）
            myWebSocket.sendMessage(1,"成功！", result);
        }

    }

    public void sendMessage(int code, String msg, Object data) throws IOException {
        JSONObject result = new JSONObject();
        result.put("code", code);
        result.put("msg", msg);
        result.put("data", data);
        System.out.println("sendMessage" + result.toString());
        this.session.getBasicRemote().sendText(result.toString());
    }

    /**
     * 向指定客户端发送消息
     * @param uid 客户端id
     */
    public synchronized void send(String uid){
        try {
            WebSocketServer myWebSocket = WebSocketMapUtil.get(uid);
            myWebSocket.sendMessage(1,"ok","null");
        }catch (Exception e){
            e.getMessage();
        }
    }

    public synchronized void sendAll(String result) {
        try {
            for (String uid:WebSocketMapUtil.webSocketMap.keySet()) {
                WebSocketServer myWebSocket = WebSocketMapUtil.get(uid);
                myWebSocket.sendMessage(1,"ok","null");
            }
        } catch (Exception e) {
            e.getMessage();
        }

    }
}
