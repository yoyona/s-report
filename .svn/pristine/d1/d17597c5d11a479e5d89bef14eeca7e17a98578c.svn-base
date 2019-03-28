package net.greatsoft.core.domain.model.task;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * @author Litian
 * @date 2017年2月14日 上午9:08:56
 * @Description: 任务填报范围，存储任务需要哪些机构进行填报
 *
 */
public class TaskScope implements Serializable {
	private static final long serialVersionUID = 1L;
	/*
	@Id
	private TaskScopeKey taskScopeKey;*/
	/**
	 * 上级机构（区域划分）
	 */
	private String regionPid;
	/**
	 * 上级机构（隶属关系）
	 */
	private String subjectionPid;
	/**
	 * 上级机构（自定义）
	 */
	private String pid;
	
	private String taskId;
	
	private String orgId;
	
	
	
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	/*
	public TaskScopeKey getTaskScopeKey() {
		return taskScopeKey;
	}
	public void setTaskScopeKey(TaskScopeKey taskScopeKey) {
		this.taskScopeKey = taskScopeKey;
	}*/
	public String getRegionPid() {
		return regionPid;
	}
	public void setRegionPid(String regionPid) {
		this.regionPid = regionPid;
	}
	public String getSubjectionPid() {
		return subjectionPid;
	}
	public void setSubjectionPid(String subjectionPid) {
		this.subjectionPid = subjectionPid;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	
}
