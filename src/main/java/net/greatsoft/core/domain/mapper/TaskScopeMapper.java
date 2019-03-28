package net.greatsoft.core.domain.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import net.greatsoft.core.domain.model.task.TaskScope;
import net.greatsoft.core.dto.task.OrgDto;

@Mapper
public interface TaskScopeMapper {
	/**
	 * 批量导入任务范围信息
	 * @param list
	 */
	public void insertTaskScopeBatch(List<TaskScope> list);
	/**
	 * 通过任务Id删除任务范围的信息
	 * @param taskId
	 */
	public Integer deleteTaskScopeByTaskId(String taskId);
	/**
	 * 通过机构Id删除任务范围的信息
	 * @param orgId
	 */
	public Integer deleteTaskScopeByOrgId(String orgId);
	
	/**
	 * 通过任务Id来查询任务范围的信息
	 * @param taskId
	 * @return
	 */
	public List<TaskScope>  getScopeByTaskId(Long taskId);
	/**
	 * 批量删除任务范围信息
	 * @param list
	 */
	void deleteTaskScopeByBatch(List<TaskScope> list);
	/**
	 * 获取已选择任务范围的树形列表
	 * @param param
	 * @return
	 */
	public List<OrgDto> getACOrgList(Map<String, Object> param);
	/**
	 * 通过任务ID 查询出表期和任务范围信息
	 * @param taskId
	 * @return
	 */
	public List<Map<String,Object>> getPeriodAndTaskScope(Long taskId);
	/**
	 * 批复成功后,更新对应的任务范围的记录的机构id
	 * @param param
	 */
	public void doChange (Map<String,Object> param);
	
	
	public List<Map<String, Object>> getUsersAndTaskScope(Map<String,Object> param);

	public List<String> getScopeOrgIdsByTaskId(Long taskId);

	/**
	 * 删除任务范围信息
	 * @param param
	 * @return
	 */
	public Integer deleteTaskScope(Map<String,Object> param);
}
