/*
 * $Id: CacheRedisDao.java 85 2016-09-23 09:24:55Z qina $
 * 版权所有 2016 冠新软件。
 * 保留所有权利。
 */
package net.greatsoft.core.repository.redis;


import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

/**
 * @author HS
 * @date 2016年9月21日 下午5:27:45
 * @Description: 缓存通用dao
 * 
 */
@Repository
public class CacheRedisDao extends RedisBaseDao {
	
	/**
	 * 缓存登录的验证码
	 * @param key
	 * @param checkCode
	 * @author qn
	 */
	public void add(String key, String checkCode) {
	  ValueOperations<String, String> valueOper = stringRedisTemplate.opsForValue();
	  valueOper.set(key, checkCode, 30, TimeUnit.MINUTES);
	}

}
