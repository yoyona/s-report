package net.greatsoft.core.domain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.greatsoft.core.domain.model.system.UserAgent;

@Mapper
public interface UserAgentMapper {
	/**
	 * 批量导入
	 */
	public void saveUserAgentBatch(List<UserAgent> userList);
	
	public void deleteUserAgentByUserId(String userId);
	
}
