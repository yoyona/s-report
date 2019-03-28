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
 * @since 2018-10-25
 */
@Mapper
public interface AnalysisMapper {
	/**
	 * 查询当前年度填报数据
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryCurrentYearDatas(Map<String,Object> map);
	/**
	 * 查询所有标准指标信息
	 * @param map
	 * @return
	 * @since 2018-10-23
	 */
	public List<Map<String,Object>> queryStandardIndicesAnalysis(Map<String,Object> map);
	public List<Map<String,Object>> queryTablesName(Map<String,Object> map);
	public List<Map<String,Object>> queryRowsName(Map<String,Object> map);
	public List<Map<String,Object>> queryColumnsName(Map<String,Object> map);
	public List<Map<String,Object>> queryIndsBySheetId(Map<String,Object> map);
	public List<Map<String,Object>> queryIndsSheetIds(Map<String,Object> map);
	public void insertDatas(Map<String,Object> map);
	public void deleteDatas(Map<String,Object> map);
	public void updateDatas(Map<String,Object> map);
	public List<Map<String,Object>> queryDatas(Map<String,Object> map);
	public List<Map<String,Object>> queryIndices(Map<String,Object> map);
	public List<Map<String,Object>> getQueryParam(Map<String,Object> map);
}
