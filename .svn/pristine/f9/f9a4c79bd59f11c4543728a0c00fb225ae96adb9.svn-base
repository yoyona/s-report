/*
 * $Id: codetemplates.xml 2918 2014-01-22 15:29:26Z hn $
 * 版权所有 2016 冠新软件。
 * 保留所有权利。
 */
package net.greatsoft.core.repository.system;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.greatsoft.core.domain.model.system.Department;

/**
 * @author hn
 * @date 2016年9月21日 上午11:21:40
 * @Description: DepartmentRepository
 * 
 */
@Repository
public interface DepartmentRepository
		extends JpaRepository<Department, String> {

	@Query("from Department d where d.isCancel = 0 and d.departmentId = ?1 ")
	public Department findDepartmentByDepartmentId(String departmentId);

	@Query("from Department d where d.organizationId = ?1 and d.isCancel = 0")
	public List<Department> findDepartmentByOrganizationId(
			String organizationId);

	@Query("from Department d where d.isCancel = 0 and (d.isLeafNode = 1 or d.departmentType = 0)")
	public List<Department> findAllDepartments();

	@Query("from Department d where d.isCancel = 0 and d.departmentType in (2,3,4) and d.isLeafNode = 1")
	public List<Department> findNotWSJCSDepartments();

	@Query("from Department d where d.isCancel = 0 and d.departmentType = 1 and d.isLeafNode = 1")
	public List<Department> findWSJCSDepartments();
}
