package net.greatsoft.core;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.Assert;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

@Configuration
@PropertySource(value = "classpath:/redis-online.properties")
public class RedisConfigOnline {
	private Logger logger = LoggerFactory.getLogger(RedisConfig.class);
	@Value("${system.config.redis.host}")
	private String host;
	@Value("${system.config.redis.port}")
	private String port = "6379";
	@Value("${system.config.redis.maxActive}")
	private int maxActive = 1000;
	@Value("${system.config.redis.maxWait}")
	private long maxWait = 300;
	@Value("${system.config.redis.maxIdle}")
	private int maxIdle = 200;
	@Value("${system.config.redis.testOnBorrow}")
	private boolean testOnBorrow = true;
	@Value("${system.config.redis.commandTimeout}")
	private int commandTimeout = 1800;
	
	public JedisPoolConfig getJedisPoolConfig() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		if(maxActive != 0)
			jedisPoolConfig.setMaxTotal(maxActive);
		if(maxIdle != 0)
			jedisPoolConfig.setMaxIdle(maxIdle);
		if(maxWait != 0)
			jedisPoolConfig.setMaxWaitMillis(maxWait);
		jedisPoolConfig.setTestOnBorrow(testOnBorrow);
		return jedisPoolConfig;
	}

	public JedisShardInfo getJedisShardInfo() {
		Assert.notNull(host, "redis host 不能为空，请在redis.properties 配置文件中设置system.config.redis.host值");
		Assert.notNull(port, "redis port 不能为空，请在redis.properties 配置文件中设置system.config.redis.port值");
		JedisShardInfo jedisShardInfo = new JedisShardInfo(host, port);
		return jedisShardInfo;
	}

	@Bean(name = "myShardedJedisPool")
	public ShardedJedisPool getShardedJedisPool() {
		logger.info("redis 配置：" + this.toString());

		List<JedisShardInfo> list = new ArrayList<JedisShardInfo>();
		list.add(this.getJedisShardInfo());
		ShardedJedisPool shardedJedisPool = new ShardedJedisPool(this.getJedisPoolConfig(), list);
		return shardedJedisPool;
	}


	

	@Override
	public String toString() {
		return "RedisConfig [host=" + host + ", port=" + port + ", maxActive=" + maxActive + ", maxWait=" + maxWait
				+ ", maxIdle=" + maxIdle + ", testOnBorrow=" + testOnBorrow + ", commandTimeout=" + commandTimeout;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public int getMaxActive() {
		return maxActive;
	}

	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}

	public long getMaxWait() {
		return maxWait;
	}

	public void setMaxWait(long maxWait) {
		this.maxWait = maxWait;
	}

	public int getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}

	public boolean isTestOnBorrow() {
		return testOnBorrow;
	}

	public void setTestOnBorrow(boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}

	public int getCommandTimeout() {
		return commandTimeout;
	}

	public void setCommandTimeout(int commandTimeout) {
		this.commandTimeout = commandTimeout;
	}

}
