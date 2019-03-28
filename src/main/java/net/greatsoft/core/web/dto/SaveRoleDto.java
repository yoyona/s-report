/*
 * 版权所有 2017 北航冠新。
 * 保留所有权利。
 */
package net.greatsoft.core.web.dto;

import java.util.List;

import net.greatsoft.core.domain.model.system.Role;
import net.greatsoft.core.domain.model.system.RoleScope;


public class SaveRoleDto {
	private Role role;

	private List<String> resourceIds;

	private List<String> roleScopeList;
	// 上级角色Id
	private String highierRole;
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<String> getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(List<String> resourceIds) {
		this.resourceIds = resourceIds;
	}

	public List<String> getRoleScopeList() {
		return roleScopeList;
	}

	public void setRoleScopeList(List<String> roleScopeList) {
		this.roleScopeList = roleScopeList;
	}

	public String getHighierRole() {
		return highierRole;
	}

	public void setHighierRole(String highierRole) {
		this.highierRole = highierRole;
	}

	
	
	
}
