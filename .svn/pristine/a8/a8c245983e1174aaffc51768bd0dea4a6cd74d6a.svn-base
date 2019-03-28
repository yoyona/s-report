package net.greatsoft.core.service.redis;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import net.greatsoft.core.domain.model.system.User;
import net.greatsoft.core.dto.system.UserDto;
import net.greatsoft.core.repository.redis.CacheRedisDao;

@Service
public class CacheUsersService {

	 @Resource
	 private CacheRedisDao cacheRedisDao;

	 @Value("${spring.redis.expireTime}")
	 private Long expireTime;
	 
	/**
	 * 缓存用户信息
	 * @param users
	 */
   public void addOrUpdate(User users){
	   cacheRedisDao.set(users.getToken(), users);
   }
   public void addOrUpdate(String token,Object object){
	   cacheRedisDao.set(token, object, expireTime, TimeUnit.HOURS);
   }

   public UserDto findByToken(String token) {
		return (UserDto) cacheRedisDao.get(token);
   }
   
   /**
    * 查询用户信息
    * @param usersId
    * @return
    */
	public User findByUsersId(String usersId) {
		return (User) cacheRedisDao.get(usersId);
	}
	
	
	public void delByToken(String token) {
		cacheRedisDao.remove(token);
		
	}
	
	public Object getByToken(String token){
		Object object = cacheRedisDao.get(token);
		return object;
	}
}
