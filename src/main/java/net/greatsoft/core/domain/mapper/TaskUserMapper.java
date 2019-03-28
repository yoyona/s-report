package net.greatsoft.core.domain.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import net.greatsoft.core.domain.model.task.TaskUser;
/**
 * 任务分配(任务-人员关联关系表)
 * @author litian
 * @since 2017-3-13
 */
@Mapper
public interface TaskUserMapper {
	/**
	 * 通过任务id,删除记录
	 * @param taskId
	 */
	public void deleteByTaskId(Long taskId);
	/**
	 * 批量导入任务分配的信息-->页面的提交操作
	 * @param list
	 */
	public void insertTaskUserBatch(List<TaskUser> list);
	/**
	 * 查询已选任务分配信息
	 * @param taskId
	 * @return
	 */
	public List<Map<String,Object>> getACTaskUser(Map<String,Object> map);
}
