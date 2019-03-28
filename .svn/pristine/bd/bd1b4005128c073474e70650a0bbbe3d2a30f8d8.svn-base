/*
 * 版权所有 2016 北航冠新。
 * 保留所有权利。
 */
package net.greatsoft.core.repository.system;

import java.util.List;

import net.greatsoft.core.domain.model.system.AdminOrganization;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


/**
 * @author limiao
 * @decription 注册管理中心dao
 */
@Repository
public interface AdminOrganizationRepository
		extends JpaRepository<AdminOrganization, String> {
	@Query("from AdminOrganization t where t.code = ?1 and t.isValid = 1 and t.isCancel = 0")
	AdminOrganization findAdminOrganizationByCode(String code);

	List<AdminOrganization> findAdminOrganizationByLevel(Integer level);
}
