package net.greatsoft.core.domain.mapper;

import java.util.List;
import java.util.Map;

import net.greatsoft.core.domain.model.system.Notice;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeMapper {
	public List<Notice> findByCondition(Map<String,Object> param);

	public Integer deleteById(String id);
	
	public void insertNoticeUserRel(List rels);
	
	public Notice findById(String id);
	
	/**
	 * 根据任务id查询任务所属范围内各机构用户列表
	 * @param taskId
	 * @return
	 * @since 2017-7-18
	 */
	public List<String> findUsersByTaskId(String taskId);
	/**
	 * 根据机构id查询机构管理员用户
	 * @param orgId
	 * @return
	 * @since 2017-7-19
	 */
	public List<String> findUsersByOrgId(String orgId);
	/**
	 * 通过用户id来查询消息列表(已读/未读) 
	 * @param param
	 * @return
	 */
	public List<Notice> findByUserId(Map<String, Object> param);
	/**
	 * 已读
	 * @param param
	 * @return
	 */
	public Integer updateNoticeUserRel(Map<String,Object> param);

	/**
	 * 查询未读通知公告个数
	 * @param userId
	 * @return
	 */
	public Integer findUserNoticeRecordCount(String userId);

	public List<Notice> findNoticeRecord(Map<String, Object> param);

	public List<Notice> findNoticeRecordFile(Map<String, Object> param);

	public void initNoticeReacordRel(String id);
}
