package net.greatsoft.core.listener;

import java.text.SimpleDateFormat;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.greatsoft.core.util.RedisUtil;

//@Component
public class SessionListener implements HttpSessionListener {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final String ONLINECOUNT = "onLine_totals";
	@Autowired
	private RedisUtil redisUtil;

	@Override
	public void sessionCreated(HttpSessionEvent event) {

		logger.info("---sessionCreated----");
//		System.out.println("sessionCreated");
		String onLine_total = redisUtil.get(ONLINECOUNT);
		if (null == onLine_total || "".equals(onLine_total)) {
			redisUtil.set2(ONLINECOUNT, "1");
		} else {
			redisUtil.incr(ONLINECOUNT);
		}
		System.out.println("sessionCreated 完成");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) throws ClassCastException {
		logger.info("---sessionDestroyed----");
		HttpSession session = event.getSession();
		logger.info("deletedSessionId: " + session.getId());

		String onLine_total = redisUtil.get(ONLINECOUNT);
		if (!"0".equals(onLine_total) && null != onLine_total) {
			redisUtil.decr(ONLINECOUNT);
		}
	}

}
