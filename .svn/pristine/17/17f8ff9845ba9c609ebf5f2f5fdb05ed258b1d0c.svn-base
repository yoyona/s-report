package net.greatsoft.core.domain.model.task;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * @Description 审核记录表
 * @author litian
 * @Date   2017-2-23 17:36 
 */
@Entity
@Table(name="AUDIT_RECORD")
public class AuditRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="audit_seq")  
	@SequenceGenerator(name="audit_seq", sequenceName="seq_audit_record",allocationSize=1,initialValue=1) 
	@Column(name="AUD_ID")
	private Long id;
	/**
	 * 表期Id
	 */
	@Column(name="AUD_PERID")
	private String perId;
	/**
	 * 机构ID
	 */
	@Column(name="AUD_ORGID")
	private String orgId;
	/**
	 * 状态（0通过1未通过9待审核）
	 */
	@Column(name="AUD_STATUS")
	private String status;
	/**
	 * 提交用户id
	 */
	@Column(name="AUD_SUBMIT_USERID")
	private String submitUserId;
	/**
	 * 提交时间
	 */
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="AUD_SUBMIT_TIME")
	private Date submitTime;
	/**
	 * 审核用户id
	 */
	@Column(name="AUD_CHECK_USERID")
	private String checkUserId;
	/**
	 * 审核时间
	 */
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="AUD_CHECK_TIME")
	private Date checkTime;
	/**
	 * 原因
	 */
	@Column(name="AUD_REASON")
	private String reason;
	/**
	 * 汇总状态
	 */
	@Column(name="AUD_SUMMARY_STATUS")
	private String summaryStatus;
	/**
	 * 任务id
	 */
	@Column(name="AUD_TASKID")
	private String taskId;
	/**
	 * 驳回次数
	 */
	@Column(name="AUD_CHANGETIME")
	private String changeTime;
	
	@Column(name="AUD_SUMMARY_USERID")
	private String summaryUserId;
	
	
	
	public String getSummaryUserId() {
		return summaryUserId;
	}
	public void setSummaryUserId(String summaryUserId) {
		this.summaryUserId = summaryUserId;
	}
	public String getChangeTime() {
		return changeTime;
	}
	public void setChangeTime(String changeTime) {
		this.changeTime = changeTime;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPerId() {
		return perId;
	}
	public void setPerId(String perId) {
		this.perId = perId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSubmitUserId() {
		return submitUserId;
	}
	public void setSubmitUserId(String submitUserId) {
		this.submitUserId = submitUserId;
	}
	public Date getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}
	public String getCheckUserId() {
		return checkUserId;
	}
	public void setCheckUserId(String checkUserId) {
		this.checkUserId = checkUserId;
	}
	public Date getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getSummaryStatus() {
		return summaryStatus;
	}
	public void setSummaryStatus(String summaryStatus) {
		this.summaryStatus = summaryStatus;
	}
	
	
	
}
