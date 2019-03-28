package net.greatsoft.core.domain.model.system;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import net.greatsoft.core.domain.model.task.Attachment;

@Entity
@Table(name = "NOTICE")
public class Notice implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键id
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "NOT_ID", nullable = false, length = 36)
	private String id;
	
	/**
	 * 创建机构ID
	 */
	@Column(name = "NOT_ORGID", nullable = false, length = 20)
	private String orgId;
	
	/**
	 * 标题
	 */
	@Column(name = "NOT_TITLE", nullable = false, length = 120)
	private String title;
	
	/**
	 * 关联任务id
	 */
	@Column(name = "NOT_TASKID", nullable = false, length = 20)
	private String taskId;
	
	/**
	 * 通知类型
	 */
	@Column(name = "NOT_TYPE", nullable = false, length = 2)
	private String type;
	
	/**
	 * 状态
	 */
	@Column(name = "NOT_STATUS", nullable = false, length = 1)
	private String status;
	
	/**
	 * 类型(1公告0通知)
	 */
	@Column(name = "NOT_CATEGORY", nullable = false, length = 1)
	private String category;
	
	/**
	 * 创建用户id
	 */
	@Column(name = "NOT_CREATEUSERID", nullable = false, length = 20)
	private String createUserId;
	
	/**
	 * 创建时间
	 */
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="NOT_CREATETIME")
	private Date createTime;

	@Column(name="not_content")
	private String content;
	
	@Transient
	private String orgName;
	
	@Transient
	private String readStatus;
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="ATT_NOT_ID",insertable = true,updatable = true)
	private List<Attachment> attachments = new ArrayList<Attachment>();
	
	
	public String getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(String readStatus) {
		this.readStatus = readStatus;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}
	
}
