/*
 * 版权所有 2018 冠新软件。
 * 保留所有权利。
 */
package net.greatsoft.core.domain.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import net.greatsoft.core.domain.model.task.ParamInds;

/**
 * TODO 填写描述信息
 * @since 2018-12-6
 */
@Mapper
public interface QueryParamIndRelMapper {
	public void insertQueryParamIndRel(List<ParamInds> list);
	
	public Integer deleteQueryParamIndRelByParId(String parId);
	
	public List<Map<String,Object>> getQueryParamIndRelByParId(String parId);
}
