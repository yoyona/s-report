package net.greatsoft.core.web.system;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import net.greatsoft.core.listener.SessionListener;
import net.greatsoft.core.repository.redis.CacheRedisDao;
import net.greatsoft.core.util.RedisUtil;
import net.greatsoft.core.web.dto.ResultDto;
/**
        * 心跳服务接口
    * @ClassName: HeartBeatController  
    * @Description:   
    * @author sea-ant  
    * @date 2019年1月13日  
    *
 */
@RestController
public class HeartBeatController {
	public static final String ONLINECOUNT = "onLine_totals";
	
	@Value("${system.config.redis.expireTime}")
	private int expireTime;
	
	@Value("${system.maxonlinesize}")
	private Long maxOnlineSize;
	
	@Autowired
	private CacheRedisDao cacheRedisDao;
	
	@Autowired
	private RedisUtil redisUtil;
	
	
	
	
	@GetMapping("/heartBeat/{token}")
	public void heartBeat(@PathVariable String token,HttpServletRequest request) {
		System.out.println(token);
		request.getSession().setAttribute(token, "1");
		System.out.println(request.getRequestedSessionId());
		request.getSession().setMaxInactiveInterval(expireTime);
		return;
	}
	
	@GetMapping("/healthData")
	public ResultDto getOnlineCount() {
		try {
			JSONObject object = new JSONObject();
			object.put("maxOnlineSize", maxOnlineSize);
			object.put("onLineTotal", redisUtil.get(SessionListener.ONLINECOUNT));
			return new ResultDto(ResultDto.CODE_SUCCESS, "", object);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultDto(ResultDto.CODE_FAIL, "", e);
		}
	}
	
}
