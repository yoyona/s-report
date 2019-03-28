package net.greatsoft.core;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import redis.clients.jedis.HostAndPort;

import redis.clients.jedis.JedisCluster;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author HS
 * @date 2016年9月21日 下午5:18:59
 * @Description: redis配置
 *
 */
@Configuration
@PropertySource(value = "classpath:/redis.properties")
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

	@Value("${spring.redis.host}")
	private String host;
	@Value("${spring.redis.port}")
	private int port;
	@Value("${spring.redis.timeout}")
	private int timeout;

	@Bean
	public KeyGenerator wiselyKeyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object target, Method method, Object... params) {
				StringBuilder sb = new StringBuilder();
				sb.append(target.getClass().getName());
				sb.append(method.getName());
				for (Object obj : params) {
					sb.append(obj.toString());
				}
				return sb.toString();
			}
		};

	}

	@Bean
	public JedisConnectionFactory redisConnectionFactory() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setHostName(host);
		factory.setPort(port);
		factory.setTimeout(timeout); // 设置连接超时时间
		return factory;
	}
	
	/*@Bean
    public JedisCluster getJedisCluster() {
		String host = "172.16.0.120:6380,172.16.0.120:6381,172.16.0.120:6382,172.16.0.120:6383,172.16.0.120:6384,172.16.0.120:6385";
		//String host = "106.37.72.238:6380,106.37.72.238:6381,106.37.72.238:6382,106.37.72.238:6383,106.37.72.238:6384,106.37.72.238:6385";
        String[] serverArray = host.split(",");//获取服务器数组(这里要相信自己的输入，所以没有考虑空指针问题)
        Set<HostAndPort> nodes = new HashSet<>();
 
        for (String ipPort : serverArray) {
            String[] ipPortPair = ipPort.split(":");
            nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
        }
        return new JedisCluster(nodes, timeout);
        //return new JedisCluster(nodes,redisProperties.getCommandTimeout(),1000,1,redisProperties.getPassword() ,new GenericObjectPoolConfig());//需要密码连接的创建对象方式
    }*/

	@SuppressWarnings("rawtypes")
	@Bean
	public CacheManager cacheManager(RedisTemplate redisTemplate) {
		return new RedisCacheManager(redisTemplate);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
		StringRedisTemplate template = new StringRedisTemplate(factory);
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		template.setValueSerializer(jackson2JsonRedisSerializer);
		template.afterPropertiesSet();
		return template;
	}

}
