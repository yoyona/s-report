package net.greatsoft.core.repository.task;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.greatsoft.core.domain.model.task.FileAdditional;
/**
 * @author Litian
 * @date 2017年2月14日 下午1:33:28
 * @Description: 附件信息表Repository(用于存储提交任务时相关附加文件的信息)
 * 
 */
public interface FileAdditionalRepository extends JpaRepository<FileAdditional,Long>{
	
	public List<FileAdditional> findAdditionalByTaskId(String taskId);
	/**
	 * 查询机构批复里面机构历史表里的对应的上传文件列表
	 * @param orgId
	 * @return
	 */
	@Query("from  file_additional where file_orgId = ? and file_taskId is null")
	public List<FileAdditional> findAdditionlByOrgId(String orgId);

}
