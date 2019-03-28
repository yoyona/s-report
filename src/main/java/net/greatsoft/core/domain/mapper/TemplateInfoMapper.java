package net.greatsoft.core.domain.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import net.greatsoft.core.domain.model.task.TemplateInfo;

@Mapper
public interface TemplateInfoMapper {
	
	/**
	 * 通过任务来删除对应的模板信息,用于模板绑定的时候的统一操作
	 * @param taskId
	 * @param orgId
	 */
	public Integer deleteByTaskIdAndOrgId(Map<String,Object> param);
	/**
	 * 根据条件查询模板信息
	 * @param param
	 * @return
	 */
	public TemplateInfo getTemplateByCondition(Map<String,Object> param);
	/**
	 * 根据条件查询模板信息 (返回集合)
	 */
	public List<TemplateInfo> list(Map<String,Object> param);
	
	
}
