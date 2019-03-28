package net.greatsoft.core.domain.model.task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.OrderColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import net.greatsoft.core.domain.model.system.User;
/**
 * 
 * @author Litian
 * @date 2017年2月13日 下午5:44:56
 * @Description: 任务
 *
 */
@Entity
@Table(name="task")
public class Task implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 任务id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="task_seq")  
	@SequenceGenerator(name="task_seq", sequenceName="seq_task",allocationSize=1,initialValue=1)  
	private Long taskId;
	/**
	 * 所属机构id
	 */
	@Column(name="task_orgid")
	private String orgId;
	/**
	 * 任务名称
	 */
	@Column(name="task_name")
	private String taskName;
	/**
	 * 任务开始时间
	 */
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="task_startdate")
	private Date startDate;
	/**
	 * 任务结束时间
	 */
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="task_enddate")
	private Date endDate;
	/**
	 * 分配方式(0统一分配 1逐级分配)
	 */
	@Column(name="task_allocation_type")
	private String allocationType;
	/**
	 * 描述
	 */
	@Column(name="task_des")
	private String des;
	/**
	 * 任务类型(0月报,1季报,2年报,9自定义)
	 */
	@Column(name="task_type")
	private String type;
	/**
	 * 状态(0:发布,1:暂存,2:开启,9: 删除)
	 */
	@Column(name="task_status")
	private String status;
	
	/**
	 * 创建时间 
	 */
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="task_createtime")
	private Date createTime;
	/**
	 * 创建用户id
	 */
	@Column(name="task_createuserid")
	private String createUserId;
	/**
	 * 上报规则(0区域划分,1隶属关系,9自定义)
	 */
	@Column(name="task_audittype")
	private String auditType;
	/**
	 * 发起机构
	 */
	@Column(name="task_orgname")
	private String orgName;
	/**
	 * 绑定存储过程的名字
	 */
	@Column(name="TASK_PROCEDURESNAME")
	private String proceduresName;
	/**
	 * 填报范围开始时间
	 */
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="TASK_RANGE_STARTDATE")
	private Date rangeStartDate;
	/**
	 * 填报范围结束时间
	 */
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="TASK_RANGE_ENDDATE")
	private Date rangeEndDate;
	/**
	 * 汇总类型
	 */
	@Column(name="TASK_AGGREGATE_TYPE")
	private String aggregateType;
	
	@Transient
	private Integer scopeCount;
	
	@Transient
	private Integer tempCount;
	
	
	
	
	public Integer getScopeCount() {
		return scopeCount;
	}
	public void setScopeCount(Integer scopeCount) {
		this.scopeCount = scopeCount;
	}
	public Integer getTempCount() {
		return tempCount;
	}
	public void setTempCount(Integer tempCount) {
		this.tempCount = tempCount;
	}
	public String getAggregateType() {
		return aggregateType;
	}
	public void setAggregateType(String aggregateType) {
		this.aggregateType = aggregateType;
	}
	public Date getRangeStartDate() {
		return rangeStartDate;
	}
	public void setRangeStartDate(Date rangeStartDate) {
		this.rangeStartDate = rangeStartDate;
	}
	public Date getRangeEndDate() {
		return rangeEndDate;
	}
	public void setRangeEndDate(Date rangeEndDate) {
		this.rangeEndDate = rangeEndDate;
	}
	/**
	 * 一对多的关系的维持
	 * @return
	 */
	@OneToMany(/*mappedBy = "task",targetEntity=FileAdditional.class,*/cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="file_taskid",insertable = true,updatable = true)
	private List<FileAdditional> fileAdditionals = new ArrayList<FileAdditional>();
	
	@OneToMany(/*mappedBy = "task",targetEntity=Period.class,*/cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="per_taskid",insertable = true,updatable = true)
	private List<Period> periods = new ArrayList<Period>();
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="tem_taskid",insertable = true,updatable = true)
	@OrderBy("tem_type asc")
	private List<TemplateInfo> tempList = new ArrayList<TemplateInfo>();
	
	
	
	public List<FileAdditional> getFileAdditionals() {
		return fileAdditionals;
	}
	public void setFileAdditionals(List<FileAdditional> fileAdditionals) {
		this.fileAdditionals = fileAdditionals;
	}
	public List<Period> getPeriods() {
		return periods;
	}
	public void setPeriods(List<Period> periods) {
		this.periods = periods;
	}
	
	
	
	
	
	public String getProceduresName() {
		return proceduresName;
	}
	public void setProceduresName(String proceduresName) {
		this.proceduresName = proceduresName;
	}
	public List<TemplateInfo> getTempList() {
		return tempList;
	}
	public void setTempList(List<TemplateInfo> tempList) {
		this.tempList = tempList;
	}
	public String getAuditType() {
		return auditType;
	}
	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}
	public String getOrgId() {
		return orgId;
	}
	public Long getTaskId() {
		return taskId;
	}
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getAllocationType() {
		return allocationType;
	}
	public void setAllocationType(String allocationType) {
		this.allocationType = allocationType;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	
}
