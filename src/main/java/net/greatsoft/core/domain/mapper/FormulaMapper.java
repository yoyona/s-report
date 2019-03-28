/*
 * 版权所有 2019 冠新软件。
 * 保留所有权利。
 */
package net.greatsoft.core.domain.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 合理性建议功能相关
 * @since 2019-2-1
 */
@Mapper
public interface FormulaMapper {
	public List<Map<String,Object>> getFormulaList(Map<String,Object> map);
	
	public Map<String,Object> getFormulaById(Map<String,Object> map);
	
	public List<Map<String,Object>> getFormulaDetail(Map<String,Object> map);
	
	public List<Map<String,Object>> getFormulaDatas(Map<String,Object> map);
	
	public Map<String,Object> getRationalityTable(Map<String,Object> map);
	
	public List<Map<String,Object>> getFormulaByType(Map<String,Object> map);
	
}
