package net.greatsoft.core.domain.model.task;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @author Litian
 * @date 2017年2月14日 上午9:08:56
 * @Description: 附件信息表，用于存储提交任务时相关附加文件的信息
 *
 */
@Entity(name="file_additional")
public class FileAdditional implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	/*@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "assigned")*/
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="file_seq")  
	@SequenceGenerator(name="file_seq", sequenceName="seq_file_additional",allocationSize=1,initialValue=1) 
	@Column(name="file_id")
	private Long fileId;
	/**
	 * 任务id
	 */
	@Column(name="file_taskid")
	private String taskId;
	/**
	 * 附件名称
	 */
	@Column(name="file_name")
	private String fileName;
	/**
	 * 附件地址
	 */
	@Column(name="file_url")
	private String fileUrl;
	/**
	 * 创建时间
	 */
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="file_createdate")
	private Date createDate;
	/**
	 * 所属机构id
	 */
	@Column(name="file_orgid")
	private String orgId;
	/**
	 * 状态（0有效1失效）
	 */
	@Column(name="file_status")
	private String fileStatus;
	/**
	 * 文件存储名称(唯一)
	 */
	@Column(name="file_uuid")
	private String uuid;
	
	/**
	 * 多对一的维持
	 * @return
	 *//*
	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name="file_taskid", referencedColumnName="task_id",insertable=false,updatable=false)//外键为id，与task中的taskId关联
	@JoinTable(name="task")
	private Task task;
	
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}*/
	/*@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name="file_taskid",referencedColumnName="task_id",insertable=false,updatable=false)
	private Task task;
	
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}*/
	
	public Date getCreateDate() {
		return createDate;
	}
	
	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public String getFileStatus() {
		return fileStatus;
	}
	public void setFileStatus(String fileStatus) {
		this.fileStatus = fileStatus;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	@Override
	public String toString() {
		return "FileAdditional [fileId=" + fileId + ", taskId=" + taskId + ", fileName=" + fileName + ", fileUrl="
				+ fileUrl + ", createDate=" + createDate + ", orgId=" + orgId + ", fileStatus=" + fileStatus + ", uuid="
				+ uuid + "]";
	}

	
	
}
