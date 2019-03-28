package net.greatsoft.core.domain.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import net.greatsoft.core.domain.model.task.AuditRecord;
import net.greatsoft.core.domain.model.task.Task;
import net.greatsoft.core.web.dto.HeaderResultDto;
import net.greatsoft.core.web.dto.ProgressDto;

@Mapper
public interface TaskExecuteMapper {
	
	/**
	 * 更新审核状态
	 * @param AuditRecord
	 * @return
	 */
	AuditRecord updateAudit(AuditRecord ar);
	
	/**
	 * 通过用户id查询待填报任务列表
	 * @param userId
	 * @return
	 */
	public List<Task> queryTaskListByUserId(Map<String,Object> param);
	
	/**
	 * 登录用户查询任务信息 带出表期
	 * @param param
	 * @return
	 */
	public List<Task> findLoginTask(Map<String,Object> param);
	
	public List<Task> queryTaskListByExcuteStatus(Map<String,Object> param);
	
	/**
	 * 汇总机构下级数据
	 * @param map
	 * @return
	 */
	public Integer summaryTaskData(Map<String,Object> map);
	
	/**
	 * 查询机构下属机构任务执行进度
	 * @param map
	 * @return
	 */
	public List<ProgressDto> queryProgressByOrgId(Map<String,Object> map);
	
	/**
	 * 查询催报机构列表
	 * @param map
	 * @return
	 */
	public List<ProgressDto> getUrgeList(Map<String,Object> map);
	
	/**
	 * 通过填报类型汇总数据
	 * @param map
	 * @return
	 */
	public List<ProgressDto> queryProgressByFillType(Map<String,Object> map);
	
	/**
	 * 固定条件查询填报类型字典列表
	 * @return
	 */
	public List<ProgressDto> queryDicByIds();
	
	/**
	 * 查询首页数据
	 * @param map
	 * @return
	 */
	public List<HeaderResultDto> queryHeaderCount(Map<String,Object> map);
	
	/**
	 * 查询超级管理员首页数据
	 * @param map
	 * @return
	 */
	public List<HeaderResultDto> queryAllTaskCount(Map<String,Object> map);
	
	/**
	 * 统计填报单位所属地区填报情况
	 * @param map
	 * @return
	 */
	public List<HeaderResultDto> queryStatisticsData(Map<String,Object> map);
	/**
	 * 
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> findHeaderInfo(Map<String,Object> map);
	
 	public void importData();

 	/**
 	 * 获取填报进度百分比
 	 * @param map
 	 * @return
 	 */
	public Map<String, Object> findProgressPercent(Map<String, Object> map);
	
	/**
	 * 查询首页任务信息
	 * @param map
	 * @return
	 */
	public List<Task> findHomepageTask(Map<String, Object> map);


	public Integer zdySummary(Map<String, Object> map);

	/**
	 * 查询单个任务信息附带表期
	 * @param taskId
	 * @return
	 */
	public Task findTaskById(String taskId);
}
