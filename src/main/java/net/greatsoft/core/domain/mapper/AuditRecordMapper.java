package net.greatsoft.core.domain.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import net.greatsoft.core.domain.model.task.AuditRecord;

@Mapper
public interface AuditRecordMapper {

	/**
	 * 通过任务的id查询对应的审核记录信息
	 * @param taskId
	 * @return
	 */
	public Integer getCountByTaskId(Long taskId);

	/**
	 * 修改审核记录信息
	 * @param auditRecord
	 * @return
	 */
	public Integer updateAuditRecord(AuditRecord auditRecord);

	/**
	 * 关联历史机构表查询审核记录信息,为了配合机构批复
	 * @param param
	 * @return
	 */
	public AuditRecord queryAuditRecord(Map<String,Object> param);

	/**
	 * 根据机构id、表期id更新机构审核记录
	 * @param orgId
	 * @param perId
	 * @return
	 */
	public Integer updateAuditRecordByParam(Map<String,Object> param);
	
	/**
	 * 查看某个机构下面的各种审核情况
	 */
	public Integer checkSituationBelow(Map<String,Object> param);

	/**
	 * 删除任务范围里面指定任务的机构的审核记录信息
	 * @param taskId
	 * @return
	 */
	public Integer deleteAuditRecordByTaskId(Long taskId);

	/**
	 * 批量新增审核记录信息
	 * @param list
	 */
	public void insertAuditRecordBatch(List<AuditRecord> list);

	/**
	 * 批量更新状态(按照机构id和表期id)
	 * @param param
	 */
//	public void updateStatusInBatch(Map<String,Object> param);
	public void updateStatusInBatch(List<AuditRecord> list);
	/**
	 * 页面地图统计填报数
	 * @return
	 */
	public List<Map<String,Object>> countByProvince(Map<String,Object> param);

	/**
	 * 通过机构id查询相关任务和表期列表
	 * @param orgId
	 * @return
	 */
	public List<Map<String,Object>> queryTP(String orgId);

	/**
	 * 机构批复成功后更新审核记录相应的数据
	 * @param param
	 */
	public void doChange (Map<String,Object> param);

	/**
	 * 通过表期和机构id来查询审核记录信息
	 */
	public AuditRecord queryAudit(Map<String,Object> param);

	/**
	 * 删除审核记录信息
	 * @param param
	 * @return
	 */
	public Integer deleteAuditRecordByCondition(Map<String,Object> param);

	List<Map<String,Object>> findAuditRecordList(Map<String, Object> param);

	/**
	 * 根据机构id删除审核记录
	 * @param param
	 * @since 2019-1-3
	 */
	public Integer deleteAuditRecordByOrgId(String orgId);
	
}
