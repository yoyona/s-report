package net.greatsoft.core.websocket;


import net.greatsoft.core.util.constant.MessageConstant;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class MyHandler implements WebSocketHandler {

    // 在线用户列表
    private static final Map<String, WebSocketSession> users;

    static {
        users = new HashMap<String, WebSocketSession>();
    }


    /*public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String payload = message.getPayload();
        Map<String, String> map = JSONObject.parseObject(payload, HashMap.class);
        System.out.println("=====接受到的数据"+map);
        session.sendMessage(new TextMessage("服务器返回收到的信息," + payload));
    }*/

    // 新增socket连接
    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        // System.out.println("成功建立连接");
        String ID = webSocketSession.getUri().toString().split("ID=")[1];
        // System.out.println(ID);
        if (ID != null) {
            users.put(ID, webSocketSession);
            String json = "{\"result\":\"成功建立socket连接\"}";
            webSocketSession.sendMessage(new TextMessage(json));
            // System.out.println(ID);
            // System.out.println(webSocketSession);
        }
        // System.out.println("当前在线人数: " + users.size());
    }

    // 接受socket信息
    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        try {
            JSONObject jsonobject = JSONObject.fromObject(webSocketMessage.getPayload());
            // System.out.println(jsonobject.get("id"));
            // System.out.println(jsonobject.get("message")+":来自"+(String)webSocketSession.getAttributes().get("WEBSOCKET_USERID")+"的消息");
            sendMessageToUser(jsonobject.get("id")+"",new TextMessage("服务器收到了，hello!"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送消息给指定用户
     * @param clientId
     * @param message
     * @return
     */
    public boolean sendMessageToUser(String clientId, TextMessage message) {
        if (users.get(clientId) == null) {
            return false;
        }
        WebSocketSession session = users.get(clientId);
        // System.out.println("sendMessage: " + session);
        if (!session.isOpen()) {
            return false;
        }
        try {
            session.sendMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean sendMessageToAllUsers(TextMessage message) {
        boolean allSendSuccess = true;
        Set<String> clientIds = users.keySet();
        WebSocketSession session = null;
        for (String clientId : clientIds) {
            session = users.get(clientId);
            if (session.isOpen()) {
                try {
                    session.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                    allSendSuccess = false;
                }
            }
        }
        return allSendSuccess;
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        if (webSocketSession.isOpen()) {
            webSocketSession.close();
        }
        // System.out.println("连接出错");
        users.remove(getClientId(webSocketSession));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        // System.out.println("连接已关闭: " + closeStatus);
        users.remove(getClientId(webSocketSession));
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 获取用户标识
     * @param session
     * @return
     */
    private Integer getClientId(WebSocketSession session) {
        try {
            Integer clientId = (Integer) session.getAttributes().get("WEBSOCKET_USERID");
            return clientId;
        } catch (Exception e) {
            return null;
        }
    }
}
