package net.greatsoft.core.service.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.greatsoft.core.domain.mapper.AuditRecordMapper;
import net.greatsoft.core.domain.mapper.FileAdditionalMapper;
import net.greatsoft.core.domain.mapper.OrgHistoryMapper;
import net.greatsoft.core.domain.mapper.OrgMapper;
import net.greatsoft.core.domain.mapper.TaskMapper;
import net.greatsoft.core.domain.mapper.TaskScopeMapper;
import net.greatsoft.core.domain.model.system.Org;
import net.greatsoft.core.domain.model.system.OrgHistory;
import net.greatsoft.core.domain.model.task.FileAdditional;
import net.greatsoft.core.dto.task.OrgDto;
import net.greatsoft.core.repository.redis.CacheRedisDao;
import net.greatsoft.core.repository.system.OrgHistoryRepository;
import net.greatsoft.core.repository.system.OrgRepository;
import net.greatsoft.core.repository.task.FileAdditionalRepository;

/**
 * 机构服务层
 * @author GoingToSick
 * @Date  2017-02-22 16:40
 */
@Service
public class OrgService {
	
	@Autowired
	private OrgMapper orgMapper;
	
	@Autowired
	private OrgRepository orgRepository;
	
	@Autowired
	private CacheRedisDao cacheRedisDao;
	
	@Autowired
	private OrgHistoryRepository orgHistoryRepository;
	
	@Autowired
	private OrgHistoryMapper orgHistoryMapper;
	
	@Autowired
	private FileAdditionalRepository fileAdditionalRepository;
	
	@Autowired
	private FileAdditionalMapper fileAdditionalMapper;
	
	@Autowired
	private AuditRecordMapper auditRecordMapper;
	
	@Autowired
	private TaskMapper taskMapper;
	
	@Autowired
	private TaskScopeMapper taskScopeMapper;

	/**
	 * 机构树
	 * @return
	 */
	public List<Org> showTree(Map<String,Object> param) {
		param.put("status","0");
		List<Org> orgDtoList = this.orgMapper.getOrgByCondition(param);
		return orgDtoList;
	}
	
	/**
	 * 通过唯一id来获取机构的单一记录
	 * @param uniqueId
	 * @return
	 */
	public Org getOrgByUniqueId(String uniqueId){
		return orgRepository.getOrgByUniqueId(uniqueId);
	}

	/**
	 * 通过机构id来获取单条机构记录
	 * @param orgId
	 * @return
	 */
	public Org getOrgById(String orgId){
		return orgRepository.getOrgById(orgId);
	}
	
	/**
	 * 获取审核机构树
	 * @param periodId 
	 * @param orgId 
	 * @return
	 */
	public List<OrgDto> getCheckTree(Map<String, Object> param){
//		Map<String,Object> param = new HashMap<String,Object>();
//		param.put("orgId", orgId);
//		param.put("periodId", periodId);
//		param.put("auditType", auditType);
//		param.put("taskId",taskId);
//		param.put("uniqueId",uniqueId);
		List<OrgDto> checkTree = orgMapper.getCheckTree(param);
		return checkTree;
	}

	/**
	 * 修改机构信息
	 * @param org
	 * @return
	 */
	public Org update(Org org) {
		Org o = this.orgRepository.saveAndFlush(org);
		return o;
	}

	/**
	 * 新增机构信息
	 * @param org
	 */
	public Org add(Org org) {
		this.orgRepository.saveAndFlush(org);
		return org;
	}

	/**
	 * 通过机构名称来获取机构信息
	 * @param name
	 * @return
	 */
	public Org getOrgByName(String name){
		return this.orgRepository.getOrgByName(name);
	}

	/**
	 * 删除机构(修改机构状态)
	 * @param org
	 * @return
	 */
	public Org delete(Org org) {
		// TODO Auto-generated method stub
		this.orgRepository.save(org);
		return org;
	}

	/**
	 * 查询指定机构下面是否有下级机构
	 * @param subjectionPid
	 * @return
	 */
	public Integer getCountBySubjectionPid(String subjectionPid){
		return this.orgRepository.getCountBySubjectionPid(subjectionPid);
	}

	/**
	 * 从缓存中获取树的信息
	 * @return
	 */
	public List<Org> getCacheTree() {
		return (List<Org>) cacheRedisDao.get("orgs");
	}

	/**
	 * 延迟加载树
	 * @param orgId
	 * @return
	 */
	public List<Org> getDelayTree(String orgId) {
		//获取传入机构信息
		Org org = this.orgRepository.getOrgById(orgId);
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("uniqueId", org.getUniqueId());
		param.put("orgId", orgId);
		return this.orgMapper.getDelayTree(param);
	}

	/**
	 * 延迟加载树
	 * @return
	 */
	public List<Org> getDelayTree(Map<String,Object> param) {
		//获取传入机构信息
		String orgId = String.valueOf(param.get("orgId"));
		Org org = this.orgRepository.getOrgById(orgId);
		param.put("uniqueId", org.getUniqueId());
		return this.orgMapper.getDelayTree(param);
	}

	/**
	 * 延迟加载机构审核树
	 * @return
	 */
	public List<Org> findCheckTreeTop(Map<String,Object> param) {
		//获取传入机构信息
		String orgId = String.valueOf(param.get("orgId"));
		Org org = this.orgRepository.getOrgById(orgId);
		param.put("uniqueId", org.getUniqueId());
		return this.orgMapper.findCheckTreeTop(param);
	}


	/**
	 * 获取指定机构的下级机构信息
	 * @param uniqueId
	 * @return
	 */
	public List<Org> getSubordinateData(String uniqueId) {
		return this.orgRepository.getOrgsBySubjectionPid(uniqueId);
	}
	
	public List<Org> getSubordinateData(Map<String, Object> map) {
		return this.orgMapper.getSubordinateData(map);
	}

	/**
	 * 新增机构信息
	 * @param org
	 */
	@Transactional
	public OrgHistory add(OrgHistory org) {
		// this.orgHistoryRepository.save(org);
		OrgHistory saveAndFlush = this.orgHistoryRepository.saveAndFlush(org);
		return saveAndFlush;
	}

	/**
	 * 查询待批复的机构信息(org_history表里面待批复的机构记录)
	 * @param map
	 * @return
	 */
	public List<OrgHistory> list(Map<String, Object> map) {
		return this.orgHistoryMapper.list(map);
	}

	/**
	 * 保存附件
	 * @param file
	 */
	public void saveFile(FileAdditional file) {
		fileAdditionalRepository.save(file);
	}

	/**
	 * 查询批复机构的上传文件列表
	 * @param orgId
	 * @return
	 */
	public List<FileAdditional> replyFileListByUniqueId(String uniqueId) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("uniqueId", uniqueId);
		//return this.fileAdditionalRepository.findAdditionlByOrgId(orgId);
		return this.fileAdditionalMapper.getUploadFileList(param);
	}

	/**
	 * 删除org_history表里面的数据
	 * @param orgHistory
	 */
	public void delete(OrgHistory orgHistory) {
		this.orgHistoryMapper.deleteByUniqueId(orgHistory.getUniqueId());;
	}

	/**
	 * 查询org_history表里面指定条件的记录
	 * @param param
	 * @return
	 */
	public Integer getCountOfHistory(Map<String, Object> param) {
		return orgHistoryMapper.getCountByCondtion(param);
	}

	/**
	 * 根据机构历史记录的机构id来查询附件列表
	 * @param orgId
	 * @return
	 */
	public List<FileAdditional> replyFileListByOrgId(String orgId) {
		return this.fileAdditionalRepository.findAdditionlByOrgId(orgId);
	}
	
	/**
	 * 使用mybatis新增机构历史信息
	 * @param orgHistory
	 * @return
	 */
	public OrgHistory insertOrgHistory(OrgHistory orgHistory){
		this.orgHistoryMapper.insertOrgHistory(orgHistory);
		return orgHistory;
	}

	/**
	 * 通过历史机构数据的唯一id查询生效的历史机构记录
	 * @param uniqueId
	 * @return
	 */
	public OrgHistory getOrgHistoryByUniqueId(String uniqueId){
		return this.orgHistoryRepository.getOrgHistoryByUniqueId(uniqueId);
	}
	
	/**
	 * 判断该机构是否包含任务,如果有提示信息
	 * @param id
	 * @return
	 */
	public String queryTP(String orgId) {
		List<Map<String, Object>> queryTP = this.auditRecordMapper.queryTP(orgId);
		String message = "修改时将会清空任务";
		if (queryTP != null && !queryTP.isEmpty()) {
			for (Map<String, Object> map : queryTP) {
				if (map == null) {
					continue;
				}
				String taskName = (String) map.get("TASKNAME");
				String perName = (String) map.get("PERNAME");
				message += taskName+"，表期为"+perName+"下所有数据，<br>";
			}
			if (message.equals("修改时将会清空任务")) {
				return null;
			}
			message = message.substring(0,message.lastIndexOf("<br>"));
			message+="是否修改?";
			return message;
		}
		return null;
	}

	/**
	 * 批复成功后更新任务表,审核记录表,任务范围表
	 * 每次批复成功后,更新三张表的机构id的参数应该是历史记录中对应生效的数据
	 * @param orgid 更新的机构id
	 * @param uniqueId 被更新的机构唯一id
	 */
	public void doChange(Long orgid, String uniqueId) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("orgId", orgid);
		param.put("uniqueId", uniqueId);
		// 先更新任务表
		this.taskMapper.doChange(param);
		// 更新审核记录
		this.auditRecordMapper.doChange(param);
		// 更新任务范围
		this.taskScopeMapper.doChange(param);
	}
	/**
	 * 通过mybatis来修改机构信息
	 * @param org
	 * @param id
	 */
	public void updateOrg(Org org) {
		this.orgMapper.updateOrg(org);
	}

	public List<Org> getOrgsByAdministrativeCode(String areaCode) {
		
		return this.orgRepository.getOrgsByAdministrativeCode(areaCode);
	}

	public List<Org> getOrgsByProvinces(String substring) {
		return orgMapper.getOrgsByProvinces(substring);
	}

	public OrgHistory getOrgHistoryByOrgId(Long orgId) {
		return this.orgHistoryRepository.getOne(orgId);
	}

	public List<Map<String,Object>> statisticsScopeTree(Map<String,Object> param){
		return this.orgMapper.statisticsScopeTree(param);
	}
	/**
	 * 新增历史机构信息.包含主键不使用序列
	 * @param orgHistory
	 */
	public void insertOrgHistoryWithInId(OrgHistory orgHistory) {
		this.orgHistoryMapper.insertOrgHistoryWithInId(orgHistory);
	}

	public List<Org> findOrgAgentTree(Map<String, Object> param) {
		return this.orgMapper.findOrgAgentTree(param);
	}

	@Transactional
	public void deleteOrg(String status,Long id) {
		this.orgHistoryRepository.deleteOrg(status,id);
		this.orgRepository.deleteOrg(status,id);
	}

	/**
	 * 删除所有的数据
	 */
	@Transactional
	public void deleteAllOrg() {
		this.orgMapper.deleteAllOrg();
	}

	public List<Org> findOpenRegistryOrgsProvince() {
		return this.orgMapper.findOpenRegistryOrgsProvince();
	}

	@Transactional
	public void updateOpenFollowOrg(Map<String, Object> param) {
		this.orgMapper.updateOpenFollowOrg(param);
	}

	public void insertOpenRegistryOrgs() {
		this.orgMapper.insertOpenRegistryOrgs();
	}

    public List<Map<String, Object>> findHistoryDetailTree(String orgId, String year) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("orgId", orgId);
		param.put("year", year);
		return this.orgMapper.findHistoryDetailTree(param);
	}

	/**
	 *
	 * @param map
	 * @return
	 */
	public List<Org> findCheckSubordinateTree(Map<String, Object> map) {
		return this.orgMapper.findCheckSubordinateTree(map);
	}
}
