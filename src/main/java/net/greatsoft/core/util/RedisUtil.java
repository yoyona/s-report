package net.greatsoft.core.util;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * 
    * @ClassName: RedisUtil  
    * @Description:   
    * @author sea-ant  
    * @date 2019年1月13日  
    *
 */
@Repository
public class RedisUtil {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
    @Qualifier("myShardedJedisPool")
	private ShardedJedisPool shardedJedisPool;
	/*@Autowired
	private RedisConfig redisConfig;*/
	
	/**
	 * 自增
	 * @param key
	 */
	public void incr(String key){
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			jedis.incr(key);
		} catch (Exception e) {
			logger.warn(e.getMessage());
			shardedJedisPool.returnBrokenResource(jedis);
			throw new RuntimeException("缓存服务器异常，请联系管理员！");
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
	}
	/**
	 * 自减
	 * @param key
	 */
	public void decr(String key){
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			jedis.decr(key);
		} catch (Exception e) {
			logger.warn(e.getMessage());
			shardedJedisPool.returnBrokenResource(jedis);
			throw new RuntimeException("缓存服务器异常，请联系管理员！");
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
	}
	/**
	 * set key value
	 * @param key
	 * @param value
	 * @param second 过期时间
	 */
	public void set(String key, String value, int second) {

		ShardedJedis jedis = null;

		try {
			jedis = shardedJedisPool.getResource();
			jedis.set(key, value);
			jedis.expire(key, second);
		} catch (Exception e) {
			logger.warn(e.getMessage());
			shardedJedisPool.returnBrokenResource(jedis);
			throw new RuntimeException("缓存服务器异常，请联系管理员！");
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
	}
	
	/*public int getKeyCount() {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			Set<String> set = jedis.hkeys("*");
			
			return jedis.hkeys(redisConfig.getKeySize()).size();
		} catch (Exception e) {
			logger.warn(e.getMessage());
			jedis.close();
			throw new RuntimeException("缓存服务器异常，请联系管理员！");
		} finally {
			jedis.close();
		}
	}*/
	
	public void set2(String key, String value) {

		ShardedJedis jedis = null;

		try {
			jedis = shardedJedisPool.getResource();
			jedis.set(key, value);
		} catch (Exception e) {
			logger.warn(e.getMessage());
			jedis.close();
			throw new RuntimeException("缓存服务器异常，请联系管理员！");
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
	}

	public void set(byte[] key, byte[] value, int second) {

		ShardedJedis jedis = null;

		try {
			jedis = shardedJedisPool.getResource();
			jedis.set(key, value);
			jedis.expire(key, second);
		} catch (Exception e) {
			logger.warn(e.getMessage());
			shardedJedisPool.returnBrokenResource(jedis);
			throw new RuntimeException("缓存服务器异常，请联系管理员！");
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
	}
	/**
	  * 获取key
	 * @param key
	 * @return
	 */
	public String get(String key) {

		ShardedJedis jedis = null;
		String value = "";
		try {
			jedis = shardedJedisPool.getResource();
			value = jedis.get(key);

			return value;
		} catch (Exception e) {
			logger.warn(e.getMessage());
			shardedJedisPool.returnBrokenResource(jedis);
			throw new RuntimeException("缓存服务器异常，请联系管理员！");
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
	}

	/**
	 * 封装了redis的hset方法
	 *
	 * @param key
	 * @param seconds
	 * @param field
	 * @param value
	 */
	public void hset(String key, int seconds, String field, String value) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			jedis.hset(key, field, value);
			jedis.expire(key, seconds);
		} catch (Exception e) {
			logger.warn(e.getMessage());
			jedis.close();
			throw new RuntimeException("缓存服务器异常，请联系管理员！");
		} finally {
			jedis.close();
		}
	}

	/**
	 * 封装了redis的hset方法
	 *
	 * @param key
	 * @param seconds
	 * @param map
	 */
	private void hset(String key, int seconds, Map<String, String> map) {
		ShardedJedis jedis = null;

		try {
			jedis = shardedJedisPool.getResource();
			for (String field : map.keySet()) {
				jedis.hset(key, field, map.get(field));
			}
			jedis.expire(key, seconds);
		} catch (Exception e) {
			logger.warn(e.getMessage());
			shardedJedisPool.returnBrokenResource(jedis);
			throw new RuntimeException("缓存服务器异常，请联系管理员！");
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
	}

	/**
	 * 封装了redis的hget方法
	 *
	 * @param key
	 * @return value
	 */
	private String hget(String key, String field) {
		String value = null;
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			value = jedis.hget(key, field);
		} catch (Exception e) {
			logger.warn(e.getMessage());
			shardedJedisPool.returnBrokenResource(jedis);
			throw new RuntimeException("缓存服务器异常，请联系管理员！");
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
		return value;
	}

	/**
	 * 封装了redis的del方法
	 *
	 * @param key
	 */
	public void del(String key) {
		ShardedJedis jedis = null;

		try {
			jedis = shardedJedisPool.getResource();
			jedis.del(key);
		} catch (Exception e) {
			logger.warn(e.getMessage());
			shardedJedisPool.returnBrokenResource(jedis);
			throw new RuntimeException("缓存服务器异常，请联系管理员！");
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
	}

	public byte[] hget(byte[] key) {
		byte[] value = null;
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			value = jedis.get(key);
		} catch (Exception e) {
			logger.warn(e.getMessage());
			shardedJedisPool.returnBrokenResource(jedis);
			throw new RuntimeException("缓存服务器异常，请联系管理员！");
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
		return value;
	}

	public byte[] hDel(byte[] key, byte[] field) {
		byte[] value = null;
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			value = jedis.hget(key, field);
		} catch (Exception e) {
			logger.warn(e.getMessage());
			shardedJedisPool.returnBrokenResource(jedis);
			throw new RuntimeException("缓存服务器异常，请联系管理员！");
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
		return value;
	}

}
