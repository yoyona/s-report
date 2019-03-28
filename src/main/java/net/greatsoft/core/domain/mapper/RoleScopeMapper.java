package net.greatsoft.core.domain.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import net.greatsoft.core.domain.model.system.RoleScope;

@Mapper
public interface RoleScopeMapper {
	/**
	 * 新增角色范围
	 * @param roleScope
	 */
	public void insertRoleScope(RoleScope roleScope);
	/**
	 * 通过角色Id删除其所属的角色范围信息
	 * @param roleId
	 */
	public void deleteRoleScopeByRoleId(String roleId);
	/**
	 * 查询
	 * @param param
	 * @return
	 */
	public List<RoleScope> query(Map<String,Object> param);
}
