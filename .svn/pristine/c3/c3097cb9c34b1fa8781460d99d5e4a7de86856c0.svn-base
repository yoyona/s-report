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
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * @author Litian
 * @date 2017年2月14日 上午9:08:56
 * @Description: 报表期，根据任务类型创建对应报表期数据，
 * 如：月报创建12条报表期数据，季报创建4条报表期数据
 *
 */
@Entity
public class Period implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="period_seq")  
	@SequenceGenerator(name="period_seq", sequenceName="seq_period",allocationSize=1,initialValue=1) 
	@Column(name="per_id")
	private Long periodId;
	/**
	 * 任务Id
	 */
	@Column(name="per_taskid")
	private String taskId;
	/**
	 * 表期名称
	 */
	@Column(name="per_name")
	private String periodName;
	/**
	 * 描述
	 */
	@Column(name="per_description")
	private String periodDescription;
	/**
	 * 状态
	 */
	@Column(name="per_status")
	private String status;
	/**
	 * 开始时间
	 */
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="per_startDate")
	private Date   startDate;
	/**
	 * 结束时间
	 */
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="per_endDate")
	private Date   endDate;
	/**
	 * 所属机构id
	 */
	@Column(name="per_orgid")
	private String orgId;
	
	/**
	 * 参数名称
	 */
	@Column(name="per_paramname")
	private String paramName;
	
	
	/*
	*//**
	 * 多对一的维持
	 * @return
	 *//*
	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name="per_taskid", referencedColumnName="task_id",insertable=false,updatable=false)//外键为id，与task中的taskId关联
	private Task task;
	
	
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}*/
	/*@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name="per_taskid",insertable=false,referencedColumnName="task_id",updatable=false)
	private Task task;
	
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}*/
	
	
	
	public String getStatus() {
		return status;
	}
	
	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public Long getPeriodId() {
		return periodId;
	}

	public void setPeriodId(Long periodId) {
		this.periodId = periodId;
	}

	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getPeriodName() {
		return periodName;
	}
	public void setPeriodName(String periodName) {
		this.periodName = periodName;
	}
	public String getPeriodDescription() {
		return periodDescription;
	}
	public void setPeriodDescription(String periodDescription) {
		this.periodDescription = periodDescription;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
}
