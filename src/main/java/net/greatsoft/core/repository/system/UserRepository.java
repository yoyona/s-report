/*
 * 版权所有 2016 冠新软件。
 * 保留所有权利。
 */
package net.greatsoft.core.repository.system;

import net.greatsoft.core.domain.model.system.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


/**
 * 用户 Repository
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
	@Override
	User save(User user);

	// TODO findOne
	User findUserById(String userId);
	
	/**
	 * 根据开放平台用户id查询用户信息
	 * @param empId
	 * @return
	 */
	public User findUsersByEmpId(String empId);

	// TODO isCancel
	@Query("from User t where t.name = ?1 and t.password = ?2 and t.isCancel = 0")
	User findUserByUsernameAndPassword(String username, String password);
	
	public User findUsersById(String userId);
	
	/**
	 * 根据用户名查询用户
	 */
	@Query("from User t where t.name = ?1 and t.isValid = 1 and t.isCancel = 0")
	User findUserByName(String username);

	/**
	 * 根据用户名查询用户
	 */
	@Query("from User t where t.name = ?1 and t.isAgent = ?2 and t.isValid = 1 and t.isCancel = 0")
	User findUserByNameAndIsAgent(String username, Integer isAgent);


	@Query("from User t where t.adminOrganizationId = ?1 and t.type = ?2")
	User findUserByOrgId(String orgId,Integer roleType);
	
	@Query("from User t where t.adminOrganizationId = ?1")
	List<User> findUserByOrgId(String uniqueId);
	
}
