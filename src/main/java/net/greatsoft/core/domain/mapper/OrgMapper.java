package net.greatsoft.core.domain.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import net.greatsoft.core.domain.model.system.Org;
import net.greatsoft.core.domain.model.system.OrgHistory;
import net.greatsoft.core.dto.task.OrgDto;

@Mapper
public interface OrgMapper {

	/**
	 * 通过条件查询机构(目前作用于获取机构树所有的机构)
	 * @param map
	 * @return
	 */
	public List<Org>  getOrgByCondition(Map<String,Object> map);

	/**
	 * 获取审核机构树
	 * @param map
	 * @return
	 */
	public List<OrgDto> getCheckTree(Map<String,Object> map);

	/**
	 * 延迟加载树(未完成)
	 * @param map
	 * @return
	 */
	public List<OrgDto> showTree(Map<String,Object> map);

	/**
	 * 机构模板自审
	 * @param map
	 * @return
	 */
	public Integer checkStatus(Map<String,Object> map);

	/**
	 * 延迟加载树的第一次访问
	 * @param param
	 * @return
	 */
	public List<Org> getDelayTree(Map<String, Object> param);
	
	/**
	 * 查询用户代理结构列表
	 * @param userId
	 * @return
	 */
	List<Org> findUserAgentList(String userId);
	
	/**
	 * 查询下级及本机机构列表
	 * @param param
	 * @return
	 */
	List<Org> getOrgsByPid(Map<String, Object> param);

	/**
	 * 获取指定省级下面的机构列表
	 * @param administrativeCode
	 * @return
	 */
	List<Org> getOrgsByProvinces(String administrativeCode);

	/**
	 * 修改机构信息,机构批复应用中产生需要修改整条机构信息
	 * @param o
	 */
	public void updateOrg(Org o);

	/**
	 * 延迟加载树的点击事件,包含条件加载树节点的下级列表
	 * @param map
	 * @return
	 */
	public List<Org> getSubordinateData(Map<String, Object> map);
	/**
	 * 统计任务范围加载树之后的机构列表里面根据条件有多少的填报类型以及其对应的个数
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> statisticsScopeTree(Map<String, Object> map);
	
	public List<Org> findOrgAgentTree(Map<String, Object> param);
	
	/**
	 * 获取直属医院列表
	 * @param map
	 * @return
	 * @since 2018-9-29
	 */
	public List<Map<String,Object>> directlyUnderOrgs(Map<String, Object> map);

	/**
	 * 删除所有机构数据
	 */
	public void deleteAllOrg();

	public List<Org> findOpenRegistryOrgsProvince();

	public void updateOpenFollowOrg(Map<String, Object> map);

	public void insertOpenRegistryOrgs();
	
	/**
	 * 通过关联审核记录来查询指定的机构信息
	 * @param param
	 * @return
	 */
	public Org queryOrg(Map<String,Object> param);

	/**
	 * 历史档案数据查询
	 * @param param
	 * @return
	 */
	public List<Map<String, Object>> findHistoryDetailTree(Map<String, Object> param);

	/**
	 * 延迟加载审核机构树第一层
	 * @param param
	 * @return
	 */
	public List<Org> findCheckTreeTop(Map<String, Object> param);

	/**
	 * 延迟加载审核机构树下级
	 * @param map
	 * @return
	 */
	public List<Org> findCheckSubordinateTree(Map<String, Object> map);

	/**
	 * 查询代理机构附带审核记录信息
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> findUserAgentListWithAuditStatus(Map<String, Object> params);

	public void  updateOpenFollowOrgBack(Map<String, Object> map);

	public Integer deleteOrg(String orgId);

	public Integer findHzNb(String orgId);
	/**
	 * 查询机构模板地址
	 * @param params
	 * @return
	 * @since 2019-1-13
	 */
	public String getTemplateIp(Map<String, Object> params);
	
}
