/*
 * 版权所有 2018 冠新软件。
 * 保留所有权利。
 */
package net.greatsoft.core.domain.model.system;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import net.greatsoft.core.domain.model.task.Period;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 自定义汇总信息表
 * @since 2018-9-30
 */
@Entity
@Table(name = "CUSTOM_SUMMARY")
public class CustomSummary implements Serializable{
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "CUS_ID", nullable = false, length = 36)
	private String id;

	/**
	 * 名称
	 */
	@Column(name = "CUS_NAME", nullable = false, length = 100)
	private String name;

	/**
	 * 任务id
	 */
	@Column(name = "CUS_TASKID", nullable = false, length = 20)
	private String taskId;

	/**
	 * 表期id
	 */
	@Column(name = "CUS_PERIODID", nullable = false, length = 20)
	private String periodId;
	
	/**
	 * 创建机构id
	 */
	@Column(name = "CUS_ORGID", nullable = false, length = 20)
	private String orgId;
	
	/**
	 * 备注
	 */
	@Column(name = "cus_des", nullable = false, length = 1000)
	private String des;
	
	/**
	 * 创建时间
	 */
	@Column(name = "CUS_CREATETIME", nullable = false)
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private Date createTime;
	
	/*@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="REL_CUS_ID",referencedColumnName="CUS_ID")
	//@JoinTable(name="CUSTOM_SUMMARY_ORG_REL",joinColumns={@JoinColumn(name="CUS_ID",referencedColumnName="CUS_ID")})
//	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//	@JoinTable(name = "CUSTOM_SUMMARY_ORG_REL", joinColumns = {
//			@JoinColumn(name = "CUS_ID")}, inverseJoinColumns = {
//					@JoinColumn(name = "REL_CUS_ID")})
	private List<CustomSummaryOrgRel> orgs;*/
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getPeriodId() {
		return periodId;
	}

	public void setPeriodId(String periodId) {
		this.periodId = periodId;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	/*public List<CustomSummaryOrgRel> getOrgs() {
		return orgs;
	}

	public void setOrgs(List<CustomSummaryOrgRel> orgs) {
		this.orgs = orgs;
	}*/

}
