package net.greatsoft.core.repository.task;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.greatsoft.core.domain.model.task.Attachment;
import net.greatsoft.core.domain.model.task.Task;
/**
 * @author Litian
 * @date 2017年2月14日 下午1:33:28
 * @Description: 任务Repository
 * 
 */
public interface TaskRepository extends JpaRepository<Task, String>{
	
	public Task findTaskByTaskId(Long taskId);
	
	public Task findTaskByTaskName(String name);
	
	public Integer deleteTaskByTaskId(Long taskId);
	
	@Query("from Task where status = ?1")
	public List<Task> findByStatus(String status);
}
