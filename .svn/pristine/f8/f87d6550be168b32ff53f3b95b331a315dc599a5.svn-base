package net.greatsoft.core.domain.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import net.greatsoft.core.domain.model.system.NoticeUserRel;
import net.greatsoft.core.domain.model.task.Task;
import net.greatsoft.core.dto.task.TaskCategoryDto;

@Mapper
public interface TaskMapper {

	/**
	 * 查询所有的任务信息
	 * @param map
	 * @return
	 */
	List<Task> findAllTasks(Map<String, Object> map);

	/**
	 * 修改单条任务信息数据
	 * @param task
	 * @return
	 */
	public Integer updateTask(Task task);
	
	/**
	 * 通过用户id查询待填报任务列表
	 * @param userId
	 * @return
	 */
	public List<Task> queryTaskListByOrgId(String orgId);
	
	/**
	 * 根据用户所属机构查询首页任务各类型数据量
	 * @param orgId
	 * @return
	 */
	public List<TaskCategoryDto> getTaskCategoryList(String orgId);

	/**
	 * 批复成功更新相应的任务记录
	 * @param param
	 */
	public void doChange (Map<String,Object> param);
	
	/**
	 * 查询开启中任务表期列表
	 * @param param
	 * @return
	 * @since 2018-12-19
	 */
	public List<Map<String,Object>> queryTaskPeriodList();
	
	/**
	 * 查询开启中任务表期列表
	 * @param param
	 * @return
	 * @since 2018-12-19
	 */
	public List<Map<String,Object>> queryTaskList();


}
