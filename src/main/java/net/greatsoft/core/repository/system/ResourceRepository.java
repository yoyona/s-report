/*
 * 版权所有 2017 北航冠新。
 * 保留所有权利。
 */
package net.greatsoft.core.repository.system;

import java.util.List;

import net.greatsoft.core.domain.model.system.PermissionResource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ResourceRepository
		extends JpaRepository<PermissionResource, String> {
	PermissionResource findPermissionResourceById(String id);

	List<PermissionResource> findPermissionResourceByName(String name);
}
