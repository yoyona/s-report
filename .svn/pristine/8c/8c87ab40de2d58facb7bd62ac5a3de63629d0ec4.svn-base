/*
 * 版权所有 2016 北航冠新。
 * 保留所有权利。
 */
package net.greatsoft.core.domain.mapper;

import java.util.List;
import java.util.Map;

import net.greatsoft.core.domain.model.system.PermissionResource;

import org.apache.ibatis.annotations.Mapper;


/**
 * 权限资源的 mapper
 */
@Mapper
public interface ResourceMapper {
	List<PermissionResource> findResoursesByRoleId(String roleId);

	/**
	 * 根据用户的id查询权限资源
	 */
	List<PermissionResource> findResourcesByUsersId(Map<String,Object> map);
	
	/**
	 * 根据权限资源的顶级id查询该条信息
	 */
	PermissionResource findByResourcesId(String resourcesId);
}
