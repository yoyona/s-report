/*
 * 版权所有 2018 冠新软件。
 * 保留所有权利。
 */
package net.greatsoft.core.domain.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * TODO 填写描述信息
 * @since 2018-10-12
 */
@Mapper
public interface CustomSummaryMapper {
	public List<Map<String,Object>> findCustomSummaryByOrgId(Map<String,Object> map);
}
