/*
 * 版权所有 2017 冠新软件。
 * 保留所有权利。
 */
package net.greatsoft.core.domain.model.system;

import javax.persistence.Column;

/**
 * 消息用户关系
 * @since 2017-7-18
 */
public class NoticeUserRel {
	/**
	 * 消息id
	 */
	private String notId;

	/**
	 * 用户id
	 */
	private String userId;
	/**
	 * 是否已读,0:未读,1:已读
	 */
	private String isRead;
	
	/**
	 * 类型:0:消息,1:通知公告
	 */
	private String type;
	
	public String getIsRead() {
		return isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}

	public String getNotId() {
		return notId;
	}

	public void setNotId(String notId) {
		this.notId = notId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
