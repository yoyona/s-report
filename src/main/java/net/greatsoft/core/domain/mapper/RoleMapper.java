/*
 * 版权所有 2017 北航冠新。
 * 保留所有权利。
 */
package net.greatsoft.core.domain.mapper;

import java.util.List;
import java.util.Map;

import net.greatsoft.core.domain.model.system.Role;
import net.greatsoft.core.domain.model.system.User;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface RoleMapper {
	List<Role> findRoleByUsersId(String userId);
	List<Role> queryRoleByRoleIdList(Map<String,Object> param);

	/**
	 * 批量导入用户和角色的关联关系
	 * @param list
	 */
    public void insertBatchUsersRole(List<User> list);

	/**
	 * 删除所有关联数据
	 */
	public void deleteAllUsersRole();

    public void insertUsersRoleFromOpen();
}
