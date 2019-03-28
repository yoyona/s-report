package net.greatsoft.core.domain.model.task;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * TaskScope联合主键类 (需要重写hashcode和equls)
 * @author Tiantian
 * 目前废弃
 *
 */
@Embeddable
public class TaskScopeKey implements Serializable{
	private static final long serialVersionUID = -3304319243957837925L;
	@Column(name="SCO_TASKID")
	private String taskId;
	@Column(name="SCO_ORGID")
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
	/**
	 * 重写hashcode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((taskId == null) ? 0 : taskId.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof TaskScopeKey){  
			TaskScopeKey key = (TaskScopeKey)obj ;  
            if(this.taskId == key.getTaskId()&& this.orgId.equals(key.getOrgId())){  
                return true ;  
            }  
        }  
        return false ;  
	}
}
