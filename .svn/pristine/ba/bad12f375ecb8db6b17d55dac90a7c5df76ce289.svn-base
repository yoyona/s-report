package net.greatsoft.core.repository.task;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.greatsoft.core.domain.model.task.TemplateInfo;

/**
 * @author Litian
 * @date 2017年2月20日 16:09:00
 * @Description: 模板信息表Repository,为任务指定对应的亿信华辰模板名称
 * 
 */
public interface TemplateInfoRepository extends JpaRepository<TemplateInfo,Long>{
	/**
	 * 通过任务ID来获取对应的模板
	 * @param taskId
	 * @return
	 */
	@Query("from TemplateInfo t where t.taskId = ?1 and t.templateType = ?2")
	public TemplateInfo getTmeplateInfoByTaskIdAndTemplateType(String taskId,String templateType);
	/**
	 * 查询已经绑定的模板信息
	 * @param taskId
	 * @return
	 */
	public List<TemplateInfo> getTemplateInfoByTaskId(String taskId);
}
