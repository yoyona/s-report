package net.greatsoft.core.domain.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import net.greatsoft.core.domain.model.system.OrgHistory;

@Mapper
public interface OrgHistoryMapper {
	/**
	 * 查询待批复的机构信息(org_history表里面待批复的机构记录)
	 * @param param
	 * @return
	 */
	public List<OrgHistory> list(Map<String,Object> param);
	
	/**
	 * 通过唯一ID删除
	 * @param uniqueId
	 * @return
	 */
	public Integer deleteByUniqueId(String uniqueId);
	/**
	 * 根据条件查询指定的个数
	 * @param param
	 * @return
	 */
	public Integer getCountByCondtion(Map<String,Object> param);
	/**
	 * 新增机构历史信息
	 * @param orgHistory
	 */
	public void   insertOrgHistory(OrgHistory orgHistory);
	/**
	 * 通过关联审核记录来查询指定的历史机构信息
	 * @param param
	 * @return
	 */
	public OrgHistory queryHistoryOrg(Map<String,Object> param);
	/**
	 * 新增机构历史信息,主键不用序列
	 * @param orgHistory
	 */
	public void insertOrgHistoryWithInId(OrgHistory orgHistory);
}
