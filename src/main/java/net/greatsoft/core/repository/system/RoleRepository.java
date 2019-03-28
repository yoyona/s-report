/*
 * 版权所有 2016 北航冠新。
 * 保留所有权利。
 */
package net.greatsoft.core.repository.system;

import java.util.List;

import net.greatsoft.core.domain.model.system.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
	Role findRoleById(String roleId);

	Role findRoleByName(String roleName);

	List<Role> findRoleByType(int roleType);
	
}
