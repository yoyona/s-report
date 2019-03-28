package net.greatsoft.core.domain.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import net.greatsoft.core.domain.model.system.Department;
import net.greatsoft.core.dto.budget.DepartmentDto;

@Mapper
public interface DepartmentMapper {
	List<Department> findByParams();

	List<DepartmentDto> findDepartmentTree(Map<String, Object> map);

	List<DepartmentDto> findAllDepartments(Map<String, Object> param);
}
