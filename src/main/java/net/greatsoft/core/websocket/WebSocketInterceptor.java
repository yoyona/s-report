package net.greatsoft.core.websocket;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

/*
    websocket拦截器
 */
public class WebSocketInterceptor implements HandshakeInterceptor {

    // 进去hander之前的拦截
    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        if (serverHttpRequest instanceof ServletServerHttpRequest) {
            String ID = serverHttpRequest.getURI().toString().split("ID=")[1];
            /*System.out.println("当前session的id是:" + ID);*/
            ServletServerHttpRequest request = (ServletServerHttpRequest) serverHttpRequest;
            HttpSession session = request.getServletRequest().getSession();
            map.put("WEBSOCKET_USERID", ID);
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
        /*System.out.println("进来webSocket的afterHandshake拦截器！");*/
    }
}
