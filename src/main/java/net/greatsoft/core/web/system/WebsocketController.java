package net.greatsoft.core.web.system;

import net.greatsoft.core.util.constant.MessageConstant;
import net.greatsoft.core.web.dto.ResultDto;
import net.greatsoft.core.websocket.MyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.TextMessage;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/websocket")
public class WebsocketController {

    // public static MyHandler myHandler;

    @Autowired
    private  MyHandler myHandler;

    /**
     * 发送紧急消息
     * @param title
     * @param content
     * @return
     */
    @GetMapping(value = "/sendAllMessage")
    public ResultDto sendAllMessage(String title, String content) {
        // 拼接JSON字符串
        String json = "{\"title\":\""+title+"\",\"code\":\""+MessageConstant.STOP_SERVICE+"\",\"content\":\"" + content + "\"}";
        Map<String, Object> result = new HashMap<String, Object>();
        boolean b = myHandler.sendMessageToAllUsers(new TextMessage(json));
        result.put("b", b);
        return new ResultDto(ResultDto.CODE_SUCCESS, "发送成功", result);
    }


}
