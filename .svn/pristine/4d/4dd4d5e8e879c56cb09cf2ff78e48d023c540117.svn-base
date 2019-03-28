/*
 * 版权所有 2019 冠新软件。
 * 保留所有权利。
 */
package net.greatsoft.core.service.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.greatsoft.core.domain.mapper.OrgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.greatsoft.application.common.domain.util.ObjectClone;
import net.greatsoft.core.domain.mapper.AuditRecordMapper;
import net.greatsoft.core.domain.mapper.TaskMapper;
import net.greatsoft.core.domain.mapper.TaskScopeMapper;
import net.greatsoft.core.domain.mapper.UserMapper;
import net.greatsoft.core.domain.model.system.Org;
import net.greatsoft.core.domain.model.system.User;
import net.greatsoft.core.domain.model.task.AuditRecord;
import net.greatsoft.core.domain.model.task.TaskScope;
import net.greatsoft.core.repository.system.UserRepository;
import net.greatsoft.core.util.constant.ExecuteConstant;
import org.springframework.transaction.annotation.Transactional;

/**
 * 同步接口相关服务
 * @since 2019-1-2
 */
@Service
public class SynchronizationService {

	@Autowired
	private TaskMapper taskMapper;

	@Autowired
	private OrgService orgService;

	@Autowired
	private TaskScopeMapper taskScopeMapper;

	@Autowired
	private AuditRecordMapper auditRecordMapper;

	@Autowired
	private OrgMapper orgMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;

	@Transactional
	public Map<String,Object> sysOrg(String type,List<Map<String,Object>> orgList){
		Map<String,Object> result = new HashMap<String,Object>();
		Org org = null;
		//新增
		if("1".equals(type)){
			//查询任务和表期信息，新增机构自动关联开启的任务和表期
			List<Map<String,Object>> resultMap = taskMapper.queryTaskPeriodList();
			List<Map<String,Object>> tasks = taskMapper.queryTaskList();
			//任务范围数据
			List<TaskScope> taskScopes = null;
			//审核记录数据
			List<AuditRecord> list = null;
			for (int i = 0; i < orgList.size(); i++) {
				synchronized (this) {
					org = new Org();
					org.setId((String)orgList.get(i).get("orgId"));
					org.setUniqueId((String)orgList.get(i).get("orgId"));
					//isSuperOrgSum 0全部汇总1直属汇总
					if((Integer)orgList.get(i).get("isSuperOrgSum") == 1){
						org.setSubjectionPid((String)orgList.get(i).get("orgPid") + "2");
						org.setRegionPid((String)orgList.get(i).get("orgPid") + "2");
					}else{
						org.setSubjectionPid((String)orgList.get(i).get("orgPid"));
						org.setRegionPid((String)orgList.get(i).get("orgPid"));
					}
					org.setName((String)orgList.get(i).get("orgName"));
					org.setCreateDate(new Date());
					org.setStatus("0");
					org.setAddress((String)(orgList.get(i).get("orgAddress")==null?"":orgList.get(i).get("orgAddress")));
					org.setEmail((String)(orgList.get(i).get("email")==null?"":orgList.get(i).get("email")));
					org.setTelephone((String)(orgList.get(i).get("orgPhone")==null?"":orgList.get(i).get("orgPhone")));
					org.setAdministrativeCode((String)(orgList.get(i).get("districtCode")==null?"":orgList.get(i).get("districtCode")));
					org.setFileType((String)(orgList.get(i).get("reportTypeCode")==null?"":orgList.get(i).get("reportTypeCode")));
					org.setFillFactor((String)(orgList.get(i).get("reportReason")==null?"":orgList.get(i).get("reportReason")));
					org.setEconomicTypeCode((String)(orgList.get(i).get("economicType")==null?"":orgList.get(i).get("economicType")));
					org.setBudgetManagementLevel((String)(orgList.get(i).get("budgetLevel")==null?"":orgList.get(i).get("budgetLevel")));
					org.setCategory((String)(orgList.get(i).get("orgTypeCode")==null?"":orgList.get(i).get("orgTypeCode")));
					org.setQuality((String)(orgList.get(i).get("orgProp")==null?"":orgList.get(i).get("orgProp")));
					org.setClassManageCode((String)(orgList.get(i).get("managmentType")==null?"":orgList.get(i).get("managmentType")));
					org.setHospitalGrade((String)(orgList.get(i).get("hosLevel")==null?"":orgList.get(i).get("hosLevel")));
					//org.setSummaryType();
					org.setBudgetCode((String)(orgList.get(i).get("budgetCode")==null?"":orgList.get(i).get("budgetCode")));
					org.setCeo((String)(orgList.get(i).get("orgCharger")==null?"":orgList.get(i).get("orgCharger")));
					org.setCfo((String)(orgList.get(i).get("accountant")==null?"":orgList.get(i).get("accountant")));
					org.setFinancialManager((String)(orgList.get(i).get("orgAcharger")==null?"":orgList.get(i).get("orgAcharger")));
					org.setPrepaper((String)(orgList.get(i).get("dealer")==null?"":orgList.get(i).get("dealer")));
					org.setHealthCategory((String)(orgList.get(i).get("orgFullCode")==null?"":orgList.get(i).get("orgFullCode")));
					org.setIncomingAndOutgoings(String.valueOf(orgList.get(i).get("isIncomeTwoLines") == null ? "":orgList.get(i).get("isIncomeTwoLines")));
					org.setCode((String)(orgList.get(i).get("orgCode")==null?"":orgList.get(i).get("orgCode")));
					org.setIsCancelMedicine(String.valueOf(orgList.get(i).get("isCacelSuppleMedical")==null?"":orgList.get(i).get("isCacelSuppleMedical")));
					org.setIsInteGration(String.valueOf(orgList.get(i).get("isFinancialIntegration")==null?"":orgList.get(i).get("isFinancialIntegration")));
					org.setFinancialAccounting(String.valueOf(orgList.get(i).get("orgFinancialAccounting")==null?"":orgList.get(i).get("orgFinancialAccounting")));
					org.setIsCancel(0);
					
					org = orgService.add(org);
					String orgId = org.getId();
					//存储新增机构信息，为插入审核记录表数据做准备
					List<Org> orgs = new ArrayList();
					orgs.add(org);
					// 封皮信息
					taskScopes = new ArrayList<TaskScope>();
					list = new ArrayList<AuditRecord>();
					
					if("01".equals(org.getFileType())){
						//虚拟直属汇总机构信息
						Org org2 = new Org();
						//org2 = org;
						ObjectClone.copy(org2, org);
						org2.setId(orgId + "2");
						org2.setUniqueId(org2.getId());
						org2.setName(org.getName() + "直属汇总");
						org2.setSubjectionPid(orgId);
						org2.setRegionPid(orgId);
						org2 = orgService.add(org2);
						orgs.add(org2);
						//虚拟本级机构信息
						Org org3 = new Org(); 
						//org3 = org;
						ObjectClone.copy(org3, org);
						org3.setId(orgId + "3");
						org3.setUniqueId(org3.getId());
						org3.setName(org.getName() + "本级");
						org3.setSubjectionPid(orgId + "2");
						org3.setRegionPid(orgId + "2");
						org3 = orgService.add(org3);
						orgs.add(org3);
					}
					
					
					TaskScope taskScope = null;
					Org orgRecord = null;
					for (int t = 0; t < orgs.size(); t++) {
						orgRecord = orgs.get(t);
						//组织任务范围数据
						for (int z = 0; z < tasks.size(); z++) {
							taskScope = new TaskScope();
							taskScope.setOrgId(orgs.get(t).getId());
							taskScope.setTaskId((String)tasks.get(z).get("TASK_ID"));
							taskScopes.add(taskScope);
						}
						//组织审核记录数据
						for (int j = 0; j < resultMap.size(); j++) {
							Object taskIdObject = resultMap.get(j).get("TASK_ID");
							Object perIdObject = resultMap.get(j).get("PER_ID");
							if (taskIdObject == null || perIdObject == null) {
								continue;
							}
							String taskId = String.valueOf(taskIdObject);
							String perId = String.valueOf(perIdObject);
							AuditRecord auditRecord = new AuditRecord();
							auditRecord.setOrgId(orgRecord.getId());
							auditRecord.setPerId(perId);
							auditRecord.setStatus(ExecuteConstant.CHECK_STATUS_INIT);
							auditRecord.setSummaryStatus(ExecuteConstant.CHECK_SUMMARY_STATUS_INIT);
							auditRecord.setChangeTime("0");
							auditRecord.setTaskId(taskId);
							list.add(auditRecord);
						}
					}
					
					// 插入任务范围数据
					if(taskScopes.size() > 0 && taskScopes != null){
						int size = taskScopes.size();
						if (size < 200) {
							taskScopeMapper.insertTaskScopeBatch(taskScopes);
						} else {
							int re = 0 ;
							for(int x = 0;x < size ; x= x + 200 ){
								re = x + 200;
								if(re > size){
									re = size;
								}
								List<TaskScope> subList = taskScopes.subList(x, re);
								taskScopeMapper.insertTaskScopeBatch(subList);
							}
						}
					}
					
					//插入审核记录数据
					int size = list.size();
					if(size < 200){
						auditRecordMapper.insertAuditRecordBatch(list);
					}else{
						int res = 0 ;
						for(int j = 0;j < size ; j= j + 200 ){
							res = i + 200;
							if(res > size){
								res = size;
							}
							List<AuditRecord> subList = list.subList(i, res);
							auditRecordMapper.insertAuditRecordBatch(subList);
						}
					}
				}
			}
		}else if("2".equals(type)){//修改
			for (int i = 0; i < orgList.size(); i++) {
				org = new Org();
				org.setId((String)orgList.get(i).get("orgId"));
				org.setUniqueId((String)orgList.get(i).get("orgId"));
				//isSuperOrgSum 0全部汇总1直属汇总
				if((Integer)orgList.get(i).get("isSuperOrgSum") == 1){
					org.setSubjectionPid((String)orgList.get(i).get("orgPid") + "2");
					org.setRegionPid((String)orgList.get(i).get("orgPid") + "2");
				}else{
					org.setSubjectionPid((String)orgList.get(i).get("orgPid"));
					org.setRegionPid((String)orgList.get(i).get("orgPid"));
				}
				org.setName((String)orgList.get(i).get("orgName"));
				org.setCreateDate(new Date());
				org.setStatus((String)orgList.get(i).get("orgStatus"));
				org.setAddress((String)(orgList.get(i).get("orgAddress")==null?"":orgList.get(i).get("orgAddress")));
				org.setEmail((String)(orgList.get(i).get("email")==null?"":orgList.get(i).get("email")));
				org.setTelephone((String)(orgList.get(i).get("orgPhone")==null?"":orgList.get(i).get("orgPhone")));
				org.setAdministrativeCode((String)(orgList.get(i).get("districtCode")==null?"":orgList.get(i).get("districtCode")));
				org.setFileType((String)(orgList.get(i).get("reportTypeCode")==null?"":orgList.get(i).get("reportTypeCode")));
				org.setFillFactor((String)(orgList.get(i).get("reportReason")==null?"":orgList.get(i).get("reportReason")));
				org.setEconomicTypeCode((String)(orgList.get(i).get("economicType")==null?"":orgList.get(i).get("economicType")));
				org.setBudgetManagementLevel((String)(orgList.get(i).get("budgetLevel")==null?"":orgList.get(i).get("budgetLevel")));
				org.setCategory((String)(orgList.get(i).get("orgTypeCode")==null?"":orgList.get(i).get("orgTypeCode")));
				org.setQuality((String)(orgList.get(i).get("orgProp")==null?"":orgList.get(i).get("orgProp")));
				org.setClassManageCode((String)(orgList.get(i).get("managmentType")==null?"":orgList.get(i).get("managmentType")));
				org.setHospitalGrade((String)(orgList.get(i).get("hosLevel")==null?"":orgList.get(i).get("hosLevel")));
				//org.setSummaryType();
				org.setBudgetCode((String)(orgList.get(i).get("budgetCode")==null?"":orgList.get(i).get("budgetCode")));
				org.setCeo((String)(orgList.get(i).get("orgCharger")==null?"":orgList.get(i).get("orgCharger")));
				org.setCfo((String)(orgList.get(i).get("accountant")==null?"":orgList.get(i).get("accountant")));
				org.setFinancialManager((String)(orgList.get(i).get("orgAcharger")==null?"":orgList.get(i).get("orgAcharger")));
				org.setPrepaper((String)(orgList.get(i).get("dealer")==null?"":orgList.get(i).get("dealer")));
				org.setHealthCategory((String)(orgList.get(i).get("orgFullCode")==null?"":orgList.get(i).get("orgFullCode")));
				org.setIncomingAndOutgoings(String.valueOf(orgList.get(i).get("isIncomeTwoLines") == null ? "":orgList.get(i).get("isIncomeTwoLines")));
				org.setCode((String)(orgList.get(i).get("orgCode")==null?"":orgList.get(i).get("orgCode")));
				org.setIsCancelMedicine(String.valueOf(orgList.get(i).get("isCacelSuppleMedical")==null?"":orgList.get(i).get("isCacelSuppleMedical")));
				org.setIsInteGration(String.valueOf(orgList.get(i).get("isFinancialIntegration")==null?"":orgList.get(i).get("isFinancialIntegration")));
				org.setIsCancel(Integer.valueOf(String.valueOf(orgList.get(i).get("isDeleted"))));
				org.setFinancialAccounting(String.valueOf(orgList.get(i).get("orgFinancialAccounting")==null?"":orgList.get(i).get("orgFinancialAccounting")));
				//处理机构填报类型切换主管与普通机构，生成或删除虚拟机构的问题
				// 查询之前机构是不是主管机构
				Org orgOld = this.orgService.getOrgById(org.getId());
				
				List<User> users = userRepository.findUserByOrgId(org.getId());
				User user = null;
				Map<String,Object> params = null;
				for (int j = 0; j < users.size(); j++) {
					user = users.get(i);
					params = new HashMap();
					if("01".equals(org.getFileType())){
						//暂时写死，
						if("1".equals(org.getBudgetManagementLevel())){
							params.put("roleId", "5");
						}else if("2".equals(org.getBudgetManagementLevel())){
							params.put("roleId", "1226a527-e6f0-42cf-9f95-3642ed913d98");//省级
						}else{
							params.put("roleId", "85a928a5-fd15-4161-93a0-0a8821edce78");//市县
						}
					}else{
						params.put("roleId", "3");//基层填报角色
					}
					params.put("userId", user.getId());
					userMapper.updateUserRoleByUserId(params);
				}
				
				if(!"01".equals(org.getFileType())){
					if ("01".equals(orgOld.getFileType())) {
						// 之前机构是主管单位 删除直属汇总和本级机构 将之前处于下级的全部更新
						this.orgMapper.deleteOrg(org.getId() + "2");
						this.orgMapper.deleteOrg(org.getId() + "3");
						Map<String, Object> param = new HashMap<String, Object>();
						param.put("id", org.getId());
						param.put("pid", org.getId() + "2");
						//删除直属汇总机构后更新下级机构的上级机构id
						this.orgMapper.updateOpenFollowOrgBack(param);
						// 删除任务范围数据
						taskScopeMapper.deleteTaskScopeByOrgId(org.getId() + "2");
						taskScopeMapper.deleteTaskScopeByOrgId(org.getId() + "3");
						// 删除审核记录信息
						auditRecordMapper.deleteAuditRecordByOrgId(org.getId() + "2");
						auditRecordMapper.deleteAuditRecordByOrgId(org.getId() + "3");
					}
				}else{
				//主管机构同步更新对应虚拟机构
				//if("01".equals(org.getFileType())){
					//基层单位变主管单位
					if(!"01".equals(orgOld.getFileType())){
						Org org2 = new Org();
						ObjectClone.copy(org2, org);
						org2.setId(org.getId() + "2");
						org2.setUniqueId(org2.getId());
						org2.setName(org.getName() + "直属汇总");
						org2.setSubjectionPid(org.getId());
						org2.setRegionPid(org.getId());
						orgService.update(org2);

						Org org3 = new Org();
						ObjectClone.copy(org3, org);
						org3.setId(org.getId() + "3");
						org3.setUniqueId(org3.getId());
						org3.setName(org.getName() + "本级");
						org3.setSubjectionPid(org.getId() + "2");
						org3.setRegionPid(org.getId() + "2");
						org3 = orgService.add(org3);

						// update 本来下级机构的上级id为直属汇总
						/*Map<String, Object> param = new HashMap<String, Object>();
						param.put("pid", org.getId());
						param.put("aid", org.getId() + "2");
						param.put("id", org.getId() + "2");
						this.orgMapper.updateOpenFollowOrg(param);*/
						
						
						
						// 生成任务范围数据
						List<Map<String,Object>> tasks = taskMapper.queryTaskList();
						List<TaskScope> scopeLIst = new ArrayList<TaskScope>();
						for (int j = 0; j < tasks.size(); j++) {
							TaskScope taskScope = new TaskScope();
							taskScope.setOrgId(org.getId() + "2");
							taskScope.setTaskId((String)tasks.get(j).get("TASK_ID"));
							scopeLIst.add(taskScope);
							taskScope = new TaskScope();
							taskScope.setOrgId(org.getId() + "3");
							taskScope.setTaskId((String)tasks.get(j).get("TASK_ID"));
							scopeLIst.add(taskScope);
						}
						// 插入任务范围数据
						if(scopeLIst.size() > 0 && scopeLIst != null){
							int size = scopeLIst.size();
							if (size < 200) {
								taskScopeMapper.insertTaskScopeBatch(scopeLIst);
							} else {
								int re = 0 ;
								for(int x = 0;x < size ; x= x + 200 ){
									re = x + 200;
									if(re > size){
										re = size;
									}
									List<TaskScope> subList = scopeLIst.subList(x, re);
									taskScopeMapper.insertTaskScopeBatch(subList);
								}
							}
						}
						// 生成审核记录的信息
						List<Map<String,Object>> resultMap = taskMapper.queryTaskPeriodList();
						List<AuditRecord> list = new ArrayList<AuditRecord>();
						// 组织审核记录数据
						for (int j = 0; j < resultMap.size(); j++) {
							Object taskIdObject = resultMap.get(j).get("TASK_ID");
							Object perIdObject = resultMap.get(j).get("PER_ID");
							if (taskIdObject == null || perIdObject == null) {
								continue;
							}
							String taskId = String.valueOf(taskIdObject);
							String perId = String.valueOf(perIdObject);
							AuditRecord auditRecord = new AuditRecord();
							auditRecord.setOrgId(org.getId() + "2");
							auditRecord.setPerId(perId);
							auditRecord.setStatus(ExecuteConstant.CHECK_STATUS_INIT);
							auditRecord.setSummaryStatus(ExecuteConstant.CHECK_SUMMARY_STATUS_INIT);
							auditRecord.setChangeTime("0");
							auditRecord.setTaskId(taskId);
							list.add(auditRecord);

							auditRecord = new AuditRecord();
							auditRecord.setOrgId(org.getId() + "3");
							auditRecord.setPerId(perId);
							auditRecord.setStatus(ExecuteConstant.CHECK_STATUS_INIT);
							auditRecord.setSummaryStatus(ExecuteConstant.CHECK_SUMMARY_STATUS_INIT);
							auditRecord.setChangeTime("0");
							auditRecord.setTaskId(taskId);
							list.add(auditRecord);
						}

						// 插入审核记录数据
						int size = list.size();
						if(size < 200){
							auditRecordMapper.insertAuditRecordBatch(list);
						}else{
							int res = 0 ;
							for(int j = 0;j < size ; j= j + 200 ){
								res = i + 200;
								if(res > size){
									res = size;
								}
								List<AuditRecord> subList = list.subList(i, res);
								auditRecordMapper.insertAuditRecordBatch(subList);
							}
						}
					} else {
						//更新直属汇总信息
						Org org2 = new Org();
						ObjectClone.copy(org2, org);
						org2.setId(org.getId() + "2");
						org2.setUniqueId(org.getId() + "2");
						org2.setSubjectionPid(org.getId());
						org2.setRegionPid(org.getId());
						org2.setName(org.getName() + "直属汇总");
						orgService.update(org2);
						//更新虚拟本机信息
						Org org3 = new Org();
						ObjectClone.copy(org3, org);
						org3.setId(org.getId() + "3");
						org3.setUniqueId(org.getId() + "3");
						org3.setSubjectionPid(org.getId() + "2");
						org3.setRegionPid(org.getId() + "2");
						org3.setName(org.getName() + "本级");
						orgService.update(org3);
					}
				}
				orgService.update(org);
			}
		}else if("3".equals(type)){//注销
			for (int i = 0; i < orgList.size(); i++) {
				org = orgService.getOrgById((String)orgList.get(i).get("orgId"));
				if(org == null){
					result.put("status", 1);
					result.put("message", "机构不存在");
			        return result;
				}
				org.setStatus("1");
				org.setIsCancel(1);
				orgService.delete(org);//逻辑删除
				if("01".equals(org.getFileType())){
					//直属汇总
					Org org2 = orgService.getOrgById((String)orgList.get(i).get("orgId") + "2");
					org2.setStatus("1");
					org2.setIsCancel(1);
					orgService.delete(org2);
					//本级
					Org org3 = orgService.getOrgById((String)orgList.get(i).get("orgId") + "3");
					org3.setStatus("1");
					org3.setIsCancel(1);
					orgService.delete(org3);
				}
				
				if("01".equals(org.getFileType())){
					taskScopeMapper.deleteTaskScopeByOrgId(org.getId());
					taskScopeMapper.deleteTaskScopeByOrgId(org.getId() + "2");
					taskScopeMapper.deleteTaskScopeByOrgId(org.getId() + "3");
				}else{
					taskScopeMapper.deleteTaskScopeByOrgId(org.getId());
				}
			}
		}
		result.put("status", 0);
		result.put("message", "操作成功");
		return result;
	}

	private void insertFbData(Org org, String type) {
		String fillType = org.getFileType();
		String uniqueId = org.getUniqueId();
		if ("01".equals(fillType) && (uniqueId.endsWith("1") || uniqueId.endsWith("12") || uniqueId.endsWith("13"))) {
			// 年报汇总封皮/快报汇总封皮
			if ("1".equals(type)) {
				// 查询数据是否存在
				String orgId = org.getId().toUpperCase();
				Integer count = this.orgMapper.findHzNb(orgId);
			} else if ("2".equals(type)) {

			} else if ("3".equals(type)) {

			}
		} else if ("01".equals(fillType) && (uniqueId.endsWith("1") || uniqueId.endsWith("2") || uniqueId.endsWith("12") || uniqueId.endsWith("13"))) {
			// 快报汇总封皮
			if ("1".equals(type)) {

			} else if ("2".equals(type)) {

			} else if ("3".equals(type)) {

			}
		} else if ("0202".equals(fillType.substring(0, 5))) {
   			// 基层封皮
			if ("1".equals(type)) {

			} else if ("2".equals(type)) {

			} else if ("3".equals(type)) {

			}
		} else if ("03".equals(fillType.substring(0, 2)) || ("01".equals(fillType.substring(0, 2)) && (uniqueId.endsWith("3") && !uniqueId.endsWith("13"))) ) {
			// 卫生封皮
			if ("1".equals(type)) {

			} else if ("2".equals(type)) {

			} else if ("3".equals(type)) {

			}
		} else if ("0201".equals(fillType.substring(0, 5))) {
			// 医院封皮
			if ("1".equals(type)) {

			} else if ("2".equals(type)) {

			} else if ("3".equals(type)) {

			}
		}

	}

	public void updateFbData() {

	}


}
