/*
 * 版权所有 2016 冠新软件。
 * 保留所有权利。
 */
package net.greatsoft.core.domain.mapper;

import java.util.List;
import java.util.Map;

import net.greatsoft.core.domain.model.system.User;

import org.apache.ibatis.annotations.Mapper;


/**
 * 用户
 */
@Mapper
public interface UserMapper {
	List<User> findAllUsers(Map<String, Object> params);
	/**
	 * 获取人员配置的树形列表
	 * @param param
	 * @return
	 */
	List<User> getUsersTree(Map<String,Object> param);
	
	List<User> searchUser(Map<String, Object> params);
	/**
	 * 获取指定机构下的人员信息
	 * @param params
	 * @return
	 */
	List<User> getAllocationTree(Map<String, Object> params);
	/**
	 * 查询已选任务分配人员信息
	 * @param params
	 * @return
	 */
	List<User> getAllocationList(Map<String, Object> params);

	/**
	 * 删除所有传递过来的开放平台的用户数据
	 */
	public void deleteAllUsersInfo();

    public void insertOpenUsersInfo();
    
    public List<Map<String,Object>> queryAgentUser(String userId);
    
    public void updateUserRoleByUserId(Map<String, Object> params);
}
