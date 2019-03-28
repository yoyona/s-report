package net.greatsoft.core.websocket;

import net.greatsoft.core.web.system.WebsocketController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer{

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myHandler(), "myHandler/{ID}").setAllowedOrigins("*");
    }


    public WebSocketHandler myHandler() {
        return new MyHandler();
    }

    /**
     *
     * @return
     */
//    @Bean
//    public ServerEndpointExporter serverEndpointExporter() {
//        return new ServerEndpointExporter();
//    }
//
//    @Autowired
//    public void setMessageService(MyHandler myHandler) {
//        WebsocketController.myHandler = myHandler;
//    }
}
