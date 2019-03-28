package net.greatsoft.core.service.notice;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.greatsoft.core.domain.mapper.NoticeMapper;
import net.greatsoft.core.domain.model.system.Notice;
import net.greatsoft.core.domain.model.system.NoticeUserRel;
import net.greatsoft.core.domain.model.system.User;
import net.greatsoft.core.domain.model.task.Task;
import net.greatsoft.core.dto.system.UserDto;
import net.greatsoft.core.repository.system.NoticeRepository;
import net.greatsoft.core.util.constant.MessageConstant;

@Service
public class NoticeService {
	
	@Autowired
	private NoticeMapper noticeMapper;
	
	@Autowired
	private NoticeRepository noticeRepository;
	
	public List<Notice> findByCondition(Map<String,Object> param){
		return this.noticeMapper.findByCondition(param);
	}
	
	public Notice save(Notice notice) {
		return noticeRepository.saveAndFlush(notice);
	}
	
	public Notice findById(String id) {
		/*return this.noticeRepository.findOne(id);*/
		return this.noticeMapper.findById(id);
	}

	public Boolean deleteById(String id) {
		Integer i = this.noticeMapper.deleteById(id);
		return  i == 1 ? true : false; 
	}
	
	@Transactional
	public boolean sendMessage(Task task, String orgId, String content, List<String> userIds, UserDto user,String title) {
		try {
			Notice notice = new Notice();
			if(task != null){
				notice.setOrgId(task.getOrgId());
				notice.setTaskId(String.valueOf(task.getTaskId()));
			} else {
				notice.setOrgId(orgId);
			}
			notice.setTitle(title == null ? MessageConstant.TITLE : title);
			notice.setStatus(MessageConstant.VALID_STATUS);
			notice.setCategory(MessageConstant.NOTICE_CATEGORY);
			notice.setContent(content);
			notice.setCreateTime(new Date());
			notice.setCreateUserId(user.getId());
			Notice n = noticeRepository.saveAndFlush(notice);
			NoticeUserRel nur = null;
			List list = new ArrayList();
			for (int i = 0; i < userIds.size(); i++) {
				nur = new NoticeUserRel();
				nur.setNotId(n.getId());
				nur.setUserId((String) userIds.get(i));
				// 默认的是未读
				nur.setIsRead(MessageConstant.UNREAD);
				// 默认是消息
				nur.setType(MessageConstant.STATION_SEND_TYPE);
				list.add(nur);
			}
			if(list.size() > 0){
				noticeMapper.insertNoticeUserRel(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public List<Notice> findNoticeByUserId(String userId){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("userId", userId);
		List<Notice> noticeList = this.noticeMapper.findByUserId(param);
		return noticeList;
	}
	
	public Integer updateNoticeUserRel(Map<String,Object> param) {
		return this.noticeMapper.updateNoticeUserRel(param);
	}

	public Integer findUserNoticeRecordCount(String userId) {
		return this.noticeMapper.findUserNoticeRecordCount(userId);
	}

	/**
	 * 查询通知公告
	 * @param param
	 * @return
	 */
	public List<Notice> findNoticeRecord(Map<String,Object> param){
		return this.noticeMapper.findNoticeRecord(param);
	}

	/**
	 * 查询通知公告下载文件
	 * @param param
	 * @return
	 */
	public List<Notice> findNoticeRecordFile(Map<String,Object> param){
		return this.noticeMapper.findNoticeRecordFile(param);
	}

	/**
	 * 初始化通知公告关联用户信息数据
	 * @param notice
	 */
	@Transactional
	public void initNoticeReacordRel(Notice notice) {
		this.noticeMapper.initNoticeReacordRel(notice.getId());
	}
	
	@Transactional
	public boolean sendMessage(Task task, String orgId, String content, List<User> users, String usersId,String title) {
		try {
			Notice notice = new Notice();
			if(task != null){
				notice.setOrgId(task.getOrgId());
				notice.setTaskId(String.valueOf(task.getTaskId()));
			} else {
				notice.setOrgId(orgId);
			}
			notice.setTitle(title == null ? MessageConstant.TITLE : title);
			notice.setStatus(MessageConstant.VALID_STATUS);
			notice.setCategory(MessageConstant.NOTICE_CATEGORY);
			notice.setContent(content);
			notice.setCreateTime(new Date());
			notice.setCreateUserId(usersId);
			Notice n = noticeRepository.saveAndFlush(notice);
			NoticeUserRel nur = null;
			List list = new ArrayList();
			for (int i = 0; i < users.size(); i++) {
				User user = users.get(i);
				nur = new NoticeUserRel();
				nur.setNotId(n.getId());
				nur.setUserId(user.getId());
				// 默认的是未读
				nur.setIsRead(MessageConstant.UNREAD);
				nur.setType(MessageConstant.STATION_SEND_TYPE);
				list.add(nur);
			}
			if(list.size() > 0){
				noticeMapper.insertNoticeUserRel(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
