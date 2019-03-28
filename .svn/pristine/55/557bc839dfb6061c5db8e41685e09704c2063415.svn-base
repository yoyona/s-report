/*
 * 版权所有 2016 北航冠新。
 * 保留所有权利。
 */
package net.greatsoft.core.service.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.greatsoft.core.domain.model.system.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.greatsoft.application.common.domain.util.ObjectClone;
import net.greatsoft.core.DomainRuntimeException;
import net.greatsoft.core.repository.system.ResourceRepository;
import net.greatsoft.core.domain.mapper.ResourceMapper;
import net.greatsoft.core.domain.mapper.RoleMapper;
import net.greatsoft.core.domain.mapper.RoleScopeMapper;
import net.greatsoft.core.repository.system.RoleRepository;

@Service
public class RoleService {
	@Resource
	private RoleRepository roleRepository;

	@Resource
	private ResourceRepository resourceRepository;
	
	@Resource
	private RoleMapper roleMapper;
	
	@Resource
	private RoleScopeMapper roleScopeMapper;

	/**
	 * 保存角色和资源关系表
	 */
	public void addRole(Role role, List<String> resourceIdList,
			User currentUser) {
		if (roleRepository.findRoleByName(role.getName()) != null) {
			throw new DomainRuntimeException("角色名已存在");
		}

		role.setIsValid(1);
		//role.modifiedBy(currentUser);
		for (String resourceId : resourceIdList) {
			PermissionResource resource = resourceRepository
					.findOne(resourceId);
			role.getResources().add(resource);
		}
		roleRepository.save(role);
	}

	/**
	 * 修改角色
	 */
	public Role updateRole(Role roleParam, List<String> resourceIds,
			User currentUser) {
		Role role = roleRepository.findOne(roleParam.getId());
		ObjectClone.copy(role, roleParam);
		//role.modifiedBy(currentUser);
		role.getResources().clear();
		for (String resourceId : resourceIds) {
			PermissionResource resource = resourceRepository
					.findOne(resourceId);
			role.getResources().add(resource);
		}
		roleRepository.save(role);
		return role;
	}

	/**
	 * 是否启用
	 */
	public Role updateIsValid(String roleId, Integer isValid,
			User currentUser) {
		Role role = roleRepository.findOne(roleId);
		role.setIsValid(isValid);
		role.modifiedBy(currentUser);
		roleRepository.save(role);
		return role;
	}

	/**
	 * 根据角色 ID 删除角色
	 */
	public void deleteRole(String roleId) {
		Role role = roleRepository.findOne(roleId);
		roleRepository.delete(role);
	}

	/**
	 * 查询角色列表
	 */
	public List<Role> findAllRole() {
		return roleRepository.findAll();
	}
	
	/**
	 * 查询角色列表
	 */
	public List<Role> queryRoleList(String roleId) {
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleRepository.findRoleById(roleId));
		return roles;
	}

	/**
	 * 根据角色 ID 查询角色信息
	 */
	public Role findRoleById(String roleId) {
		return roleRepository.findOne(roleId);
	}

	/**
	 * 根据角色名称查询角色
	 */
	public Role findRoleByName(String name) {
		return roleRepository.findRoleByName(name);
	}
	
	/**
	 * 通过用户id查询角色列表 
	 * @param userId
	 * @return
	 */
	public List<Role> findRoleByUsersId(String userId){
		return roleMapper.findRoleByUsersId(userId);
	}
	
	public List<RoleScope> queryRoleScope(String id) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("roleId", id);
		return this.roleScopeMapper.query(param);
	}
	/**
	 * 保存角色范围
	 * @param roleScope
	 */
	public void insertRoleScope(RoleScope roleScope) {
		this.roleScopeMapper.insertRoleScope(roleScope);
	}
	
	public void deleteRoleScopeByRoleId(String roleId) {
		this.roleScopeMapper.deleteRoleScopeByRoleId(roleId);
	}

	public List<Role> queryRoleByRoleIdList(List<String> ids) {
		// 将list 转换数组
		Map<String,Object> param = new HashMap<String,Object>();
		if (ids != null && !ids.isEmpty()) {
			String[] array = new String[ids.size()];
			for (int i = 0; i < ids.size(); i++) {
				array[i] = ids.get(i);
			}
			param.put("ids", array);
		}
		return this.roleMapper.queryRoleByRoleIdList(param);
	}

} 
