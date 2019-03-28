package net.greatsoft.core.domain.model.task;
/**
 * 任务用户关联关系中间表
 * @author litian
 *
 */
public class TaskUser {
	private String taskId;
	private String userId;
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
