package net.greatsoft.core.web.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import net.greatsoft.core.domain.mapper.CustomSummaryMapper;
import net.greatsoft.core.domain.mapper.NoticeMapper;
import net.greatsoft.core.domain.mapper.OrgMapper;
import net.greatsoft.core.domain.mapper.TaskExecuteMapper;
import net.greatsoft.core.domain.model.system.CustomSummary;
import net.greatsoft.core.domain.model.system.CustomSummaryOrgRel;
import net.greatsoft.core.domain.model.system.Dic;
import net.greatsoft.core.domain.model.system.Org;
import net.greatsoft.core.domain.model.system.User;
import net.greatsoft.core.domain.model.task.Period;
import net.greatsoft.core.domain.model.task.Task;
import net.greatsoft.core.dto.system.UserDto;
import net.greatsoft.core.dto.task.OrgDto;
import net.greatsoft.core.repository.system.CustomSummaryOrgRelRepository;
import net.greatsoft.core.repository.system.CustomSummaryRepository;
import net.greatsoft.core.repository.system.DicRepository;
import net.greatsoft.core.repository.task.PeriodRepository;
import net.greatsoft.core.repository.task.TaskRepository;
import net.greatsoft.core.service.notice.NoticeService;
import net.greatsoft.core.service.system.OrgService;
import net.greatsoft.core.service.system.UserService;
import net.greatsoft.core.service.task.TaskExecuteService;
import net.greatsoft.core.util.DateUtil;
import net.greatsoft.core.util.constant.MessageConstant;
import net.greatsoft.core.web.BaseController;
import net.greatsoft.core.web.dto.HeaderResultDto;
import net.greatsoft.core.web.dto.ProgressDto;
import net.greatsoft.core.web.dto.ResultDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 任务执行流程相关功能
 * 
 * @author fxy
 *
 */
@RestController
@RequestMapping(value = "/task/execute")
public class TaskExecuteController extends BaseController {
	@Autowired
	private TaskExecuteService taskExecuteService;

	@Autowired
	private OrgService orgService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private PeriodRepository periodRepository;
	
	@Autowired
	private CustomSummaryRepository customSummaryRepository;
	
	@Autowired
	private CustomSummaryOrgRelRepository customSummaryOrgRelRepository;
	
	@Autowired
	private CustomSummaryMapper customSummaryMapper;
	
	@Autowired
	private NoticeMapper noticeMapper;
	
	@Autowired
	private OrgMapper orgMapper;
	
	@Autowired
	private TaskExecuteMapper taskExecuteMapper;
	
	

	@PostMapping(value = "/queryByOrgId/{pageNo}/{pageSize}")
	public ResultDto query(@RequestBody Map<String, Object> map,@PathVariable Integer pageNo, @PathVariable Integer pageSize,
			HttpServletRequest request) {
		Page<Task> page = PageHelper.startPage(pageNo, pageSize);
		UserDto user = getCachedUserDto(request);
		map.put("userId", user.getId());
		Object startDateO = map.get("startDate");
		Object endDateO = map.get("endDate");	
		Object statusO = map.get("status");
		Object home = map.get("home");
		if(startDateO != null){
			map.put("startDate",DateUtil.parse(String.valueOf(startDateO+" 00:00:00"), "yyyy-MM-dd HH:mm:ss"));
		}
		if(endDateO != null){
			map.put("endDate",DateUtil.parse(String.valueOf(endDateO)+" 23:59:59", "yyyy-MM-dd HH:mm:ss"));
		}
		if(statusO != null && !"".equals(statusO)){
			String s =String.valueOf(statusO);
			char[] charArray = s.toCharArray();
			String[]  status = new String[charArray.length];
			for (int i = 0; i < charArray.length; i++) {
				status[i] = String.valueOf(charArray[i]);
			}
			map.put("status",status);	
		}
		List<Task> taskList =this.taskExecuteService.queryTaskListByUserId(map);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("taskList", taskList);
		result.put("pageNo", page.getPageNum());
		result.put("pageSize", page.getPageSize());
		result.put("total", page.getTotal());
		result.put("rows", page.getResult());
		result.put("pages", page.getPages());
		return new ResultDto(ResultDto.CODE_SUCCESS, "", result);
	}

	/**
	 * 获取任务的模板信息
	 * (审核机构有两套模板,一套是和填报一样的只不过readonly = true,另外的模板是)
	 * (普通模板分两套,一套审核模板,一套填报模板,区别在于readonly是否为true,若为true就是审核模板,这儿若机构是上报状态那么readonly必须为true)
	 * @return
	 */
	@PostMapping(value = "/getTempalte")
	public ResultDto getTemplateInfo(@RequestBody Map<String, Object> map, HttpServletRequest request) {
		Long taskId = JSON.parseObject(JSON.toJSONString(map.get("taskId")), Long.class);
		String orgId = JSON.parseObject(JSON.toJSONString(map.get("orgId")), String.class);
		Long periodId = JSON.parseObject(JSON.toJSONString(map.get("periodId")),Long.class);
		String isSummary = JSON.parseObject(JSON.toJSONString(map.get("isSummary")), String.class);
		String readOnly = JSON.parseObject(JSON.toJSONString(map.get("readOnly")), String.class);
		ResultDto result = this.taskExecuteService.getTemplateUrl(taskId,orgId,periodId,isSummary,readOnly);
		//增加返回当前机构统计信息
		Map<String,Object> datas= this.taskExecuteService.queryStatisticsData(taskId.toString(),periodId.toString(),orgId);
		List<HeaderResultDto> statisticsData = (List<HeaderResultDto>) datas.get("list");
		Map<String,Object> res = (Map<String,Object>)result.getResult();
		if(res == null){
			return result;
		}
		res.put("statistic", statisticsData);
		/*if (datas.get("AggregateType") != null) {
			res.put("AggregateType","1");
		} else {
			res.put("AggregateType","0");
		}*/
		res.put("AggregateType",datas.get("AggregateType"));
		result.setResult(res);
		return result;
	}

	/**
	 * 获取模板信息同时获取下级机构信息
	 * @param map
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/template/subordinate")
	public ResultDto getTemplateInfoWithSuborgs(@RequestBody Map<String, Object> map, HttpServletRequest request) {
		Long taskId = Long.valueOf(String.valueOf(map.get("taskId")));
		String orgId = String.valueOf(map.get("orgId"));
		Long periodId = Long.valueOf(String.valueOf(map.get("periodId")));
		String isSummary = String.valueOf(map.get("isSummary"));
		String readOnly = String.valueOf(map.get("readOnly"));
		ResultDto result = this.taskExecuteService.getTemplateUrl(taskId,orgId,periodId,isSummary,readOnly);
		// 获取下级机构信息
		List<Org> orgs = this.orgMapper.findCheckSubordinateTree(map);
		Map<String, Object> res = (Map<String,Object>)result.getResult();
		if (res != null) {
			res.put("orgs", orgs);
		}
		result.setResult(res);
		return result;
	}

	/**
	 * 上报
	 * @return
	 */
	@PostMapping(value = "/submit")
	public ResultDto submit(@RequestBody Map<String, Object> param,HttpServletRequest request) {
		String orgId     =  String.valueOf(param.get("orgId"));
		Long   perId     =  Long.valueOf(String.valueOf(param.get("perId")));
		Long   taskId    =  Long.valueOf(String.valueOf(param.get("taskId")));
		String isSummary =  String.valueOf(param.get("isSummary"));
		UserDto user = getCachedUserDto(request);
		return this.taskExecuteService.submit(orgId, perId, taskId,isSummary,user.getId());
	}

	/**
	 * 审核或者驳回接口
	 * @param param
	 * @return
	 */
	@PostMapping(value="/passOrReject")
	public ResultDto passOrReject(@RequestBody Map<String, Object> param,HttpServletRequest request){
		Long perId = JSON.parseObject(JSON.toJSONString(param.get("perId")), Long.class);
		Long taskId = JSON.parseObject(JSON.toJSONString(param.get("taskId")), Long.class);
		String status = JSON.parseObject(JSON.toJSONString(param.get("status")), String.class);
		//当前登录用户
		UserDto user = getCachedUserDto(request);
		String reason = "";
		if(param.get("reason") != null){
			reason = JSON.parseObject(JSON.toJSONString(param.get("reason")), String.class);
		}
		List<String> orgListStr = (List<String>) param.get("orgList");
		List<Org> orgList = new ArrayList<Org>();
		if (orgListStr != null && !orgListStr.isEmpty()) {
			for (int i = 0; i < orgListStr.size(); i++) {
				String string = JSON.toJSONString(orgListStr.get(i));
				if(StringUtils.isEmpty(string)){
					continue;
				}
				Org org = JSON.parseObject(string,Org.class);
				orgList.add(org);
			}
		}
		return this.taskExecuteService.passOrReject(orgList, perId, taskId, status,reason,user.getAdminOrganizationId(),user.getId());
	}
	
	/**
	 * 任务审核
	 * 
	 * @param orgId
	 * @param periodId
	 * @return
	 */
	@PostMapping(value = "/audit")
	public ResultDto audit(@RequestParam String orgId, @RequestParam String periodId, @RequestParam String status) {
		Map<String, Object> result = new HashMap<String, Object>();
		Integer returnStatus = this.taskExecuteService.audit(orgId, periodId, status);
		result.put("returnStatus", returnStatus);
		return new ResultDto(ResultDto.CODE_SUCCESS, "操作成功", result);
	}

	/**
	 * 任务汇总
	 * 
	 * @param orgId
	 * @param periodId
	 * @return
	 */
	@PostMapping(value = "/summary")
	public ResultDto summary(@RequestBody Map<String, Object> param,HttpServletRequest request) {
		String orgId = String.valueOf(param.get("orgId")); 
		String uniqueId = String.valueOf(param.get("uniqueId"));
		String taskId = String.valueOf(param.get("taskId"));
		String periodId = String.valueOf(param.get("periodId"));
		UserDto userDto = getCachedUserDto(request);
		Map<String, Object> result = this.taskExecuteService.summary(orgId, uniqueId, taskId, periodId,userDto.getId());
		return new ResultDto(ResultDto.CODE_SUCCESS, "", result);
	}
	
	/**
	 * 查询传入地区的卫计委机构，并返回机构树
	 * @param param
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@PostMapping(value = "/area")
	public ResultDto queryOrgsByAreaId(@RequestBody Map<String, Object> param) {
		String areaId = (String)param.get("areaId");
		String orgId = String.valueOf(param.get("orgId"));
		if(areaId != null){
			Org orgWjw = this.taskExecuteService.queryOrgsByAreaId(areaId);
			orgId = orgWjw.getId();
			param.clear();
		}
		param.put("orgId", orgId);
		List<Org> orgs = orgService.showTree(param);
		return new ResultDto(ResultDto.CODE_SUCCESS, "", orgs);
	}
	
	/**
	 * 根据填报类型查询汇总数据
	 * @param param
	 * @return
	 */
	@PostMapping(value = "/progress")
	public ResultDto progress(@RequestBody Map<String, Object> param) {
		String taskId   = String.valueOf(param.get("taskId"));
		String perId    = String.valueOf(param.get("periodId"));
		String orgId    = String.valueOf(param.get("orgId"));
		String uniqueId = String.valueOf(param.get("uniqueId"));
		Map<String,Object> result = this.taskExecuteService.queryProgressByFillType(taskId, perId, orgId,uniqueId);
		// 
		Integer number = getCurrentUserNumber();
		result.put("currentUserNumber", number);
		return new ResultDto(ResultDto.CODE_SUCCESS, "", result);
	}
	
	/**
	 * 任务进度查询
	 * @param orgId
	 * @return
	 */
	@PostMapping(value = "/progress/detail/{pageNo}/{pageSize}")
	public ResultDto progress(@RequestBody Map<String, Object> param,@PathVariable Integer pageNo,@PathVariable Integer pageSize) {
		String orgId = String.valueOf(param.get("orgId") == null ? "" : param.get("orgId"));
		String uniqueId = String.valueOf(param.get("uniqueId") == null ? "" : param.get("uniqueId"));
		String taskId = String.valueOf(param.get("taskId") == null ? "" : param.get("taskId"));
		String perId = String.valueOf(param.get("periodId") == null ? "" : param.get("periodId"));
		String status = String.valueOf(param.get("status") == null ? "" : param.get("status"));
		String areaId = String.valueOf(param.get("areaId") == null ? "" : param.get("areaId"));
		Map<String, Object> result = this.taskExecuteService.progress(orgId,taskId,perId,status,uniqueId,areaId,pageNo,pageSize);
		return new ResultDto(ResultDto.CODE_SUCCESS, "", result);
	}

	/**
	 * 进度催报列表
	 * @param param
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@PostMapping(value = "/progress/urge/{pageNo}/{pageSize}")
	public ResultDto progressUrge(@RequestBody Map<String, Object> param,@PathVariable Integer pageNo,@PathVariable Integer pageSize) {
		String orgId = String.valueOf(param.get("orgId") == null ? "" : param.get("orgId"));
		String uniqueId = String.valueOf(param.get("uniqueId") == null ? "" : param.get("uniqueId"));
		String taskId = String.valueOf(param.get("taskId") == null ? "" : param.get("taskId"));
		String perId = String.valueOf(param.get("periodId") == null ? "" : param.get("periodId"));
		String status = String.valueOf(param.get("status") == null ? "" : param.get("status"));
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orgId", orgId);
		map.put("taskId", taskId);
		map.put("perId", perId);
		map.put("status", status);
		map.put("uniqueId", uniqueId);
		Page<Task> page = PageHelper.startPage(pageNo, pageSize);
		List<ProgressDto> l = this.taskExecuteService.urge(map);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("urgeList", l);
		result.put("pageNo", page.getPageNum());
		result.put("pageSize",page.getPageSize());
		result.put("total", page.getTotal());
		result.put("rows", page.getResult());
		result.put("pages", page.getPages());
		return new ResultDto(ResultDto.CODE_SUCCESS, "", result);
	}
	
	/**
	 * 统计查询首页所需数据
	 * @param param
	 * @return
	 */
	@PostMapping(value = "/header")
	public ResultDto header(@RequestBody Map<String, Object> param,HttpServletRequest request) {
		String userId = (String)param.get("userId");
		User user = userService.findUsersByUserId(userId);
		List<HeaderResultDto> list = this.taskExecuteService.queryHeaderCount(userId,user.getType());
		Map<String,Object> result = new HashMap();
		result.put("list", list);
		List notices = noticeService.findNoticeByUserId(userId);
		result.put("noticeSize", notices.size());
		return new ResultDto(ResultDto.CODE_SUCCESS, "", result);
	}

	/**
	 * 
	 * @return
	 */
	@PostMapping(value="/getMapData")
	public ResultDto getMapData(@RequestBody Map<String, Object> map){
		List<Map<String,Object>> data = this.taskExecuteService.getMapData(map);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("data", data);
		return new ResultDto(ResultDto.CODE_SUCCESS, "", result);
	}
	
	/**
	 * 统计查询下-> 报表任务查询
	 */
	@PostMapping(value = "/findHeaderInfo/{pageNo}/{pageSize}")
	public ResultDto findHeaderInfo(@RequestBody Map<String, Object> map,@PathVariable Integer pageNo, @PathVariable Integer pageSize, HttpServletRequest request) {
		Page<Task> page = PageHelper.startPage(pageNo, pageSize);
		// 处理传过来的时间
		map.put("startDate", map.get("startDate") != null ? DateUtil.parse(String.valueOf(map.get("startDate") + " 00:00:00"), "yyyy-MM-dd HH:mm:ss") : null);
		map.put("endDate", map.get("endDate") != null ? DateUtil.parse(String.valueOf(map.get("endDate")+" 00:00:00"), "yyyy-MM-dd HH:mm:ss") : null);
		List<Map<String,Object>> infoList = this.taskExecuteService.findHeaderInfo(map);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("infoList", infoList);
		result.put("pageNo", page.getPageNum());
		result.put("pageSize", page.getPageSize());
		result.put("total", page.getTotal());
		result.put("rows", page.getResult());
		result.put("pages", page.getPages());
		return new ResultDto(ResultDto.CODE_SUCCESS, "", result);
	}
	
	/**
	 * 未上报机构催报，发送信息给选中机构下管理员用户
	 * @param map
	 * @param request
	 * @return
	 * @since 2017-7-20
	 */
	@PostMapping(value = "/progress/urgeDo")
	public ResultDto urge(@RequestBody Map<String, Object> map, HttpServletRequest request) {
		UserDto user = getCachedUserDto(request);
		String sendType = (String)map.get("sendType");
		List<Map> orgIds = (List)map.get("orgIds");
		List userIds = new ArrayList();
		List<String> uIds = null;
		for (int i = 0; i < orgIds.size(); i++) {
			Map object = orgIds.get(i);
			Org org = orgService.getOrgById((String)object.get("orgId"));
			if(org != null){
				uIds = noticeMapper.findUsersByOrgId(org.getUniqueId());
				userIds.addAll(uIds);
			}
		}
		String taskId = (String)map.get("taskId");
		String perid = (String)map.get("periodId");
		Task task = taskRepository.findTaskByTaskId(Long.valueOf(taskId));
		Period per = periodRepository.findPeriodByPeriodId(Long.valueOf(perid));
		Map<String, Object> result = new HashMap();
		boolean flag = false;
		//发送站内消息
		if (MessageConstant.STATION_SEND_TYPE.equals(sendType)) {
			flag = noticeService
					.sendMessage(task, user.getAdminOrganizationId(), "任务："
							+ task.getTaskName() + " 表期：" + per.getPeriodName()
							+ MessageConstant.URGE_ORG_MSG, userIds, user, null);
		}
		result.put("result", flag);
		return new ResultDto(ResultDto.CODE_SUCCESS, "", result);
	}
	
	@PostMapping(value="/getZsData")
	public ResultDto getZsData(@RequestBody Map<String, Object> map){
		List<Map<String,Object>> data = this.taskExecuteService.getMapData(map);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("data", data);
		return new ResultDto(ResultDto.CODE_SUCCESS, "", result);
	}
	
	/**
	 * 获取直属机构列表
	 * @param map
	 * @return
	 * @since 2018-9-29
	 */
	@PostMapping(value="/getDirectlyUnderOrgs/{pageNo}/{pageSize}")
	public ResultDto getDirectlyUnderOrgs(@RequestBody Map<String, Object> map,@PathVariable Integer pageNo, @PathVariable Integer pageSize, HttpServletRequest request){
		Page<OrgDto> page = PageHelper.startPage(pageNo, pageSize);
		//获取直属汇总单位下的直属单位列表，orgId+2
		map.put("orgId", map.get("orgId")+"2");
		List<Map<String,Object>> orgs = orgMapper.directlyUnderOrgs(map);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("orgs", orgs);
		result.put("pageNo", page.getPageNum());
		result.put("pageSize", page.getPageSize());
		result.put("total", page.getTotal());
		result.put("rows", page.getResult());
		result.put("pages", page.getPages());
		return new ResultDto(ResultDto.CODE_SUCCESS, "", result);
	}
	
	@PostMapping(value="/getCustomSummarys/{pageNo}/{pageSize}")
	public ResultDto getCustomSummarys(@RequestBody Map<String, Object> map,@PathVariable Integer pageNo, @PathVariable Integer pageSize, HttpServletRequest request){
		Page<CustomSummary> page = PageHelper.startPage(pageNo, pageSize);
		List<Map<String,Object>> css = customSummaryMapper.findCustomSummaryByOrgId(map);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("css", css);
		result.put("pageNo", page.getPageNum());
		result.put("pageSize", page.getPageSize());
		result.put("total", page.getTotal());
		result.put("rows", page.getResult());
		result.put("pages", page.getPages());
		return new ResultDto(ResultDto.CODE_SUCCESS, "", result);
	}
	
	/**
	 * 新增自定义汇总信息
	 * @param map
	 * @return
	 * @since 2018-10-10
	 */
	@PostMapping(value="/zdySummary/add")
	@Transactional
	public ResultDto addZdySummary(@RequestBody Map<String, Object> map){
		CustomSummary cs = new CustomSummary();
		cs.setName((String)map.get("name"));
		cs.setTaskId((String)map.get("taskId"));
		cs.setPeriodId((String)map.get("periodId"));
		cs.setDes((String)map.get("des"));
		cs.setCreateTime(new Date());
		cs.setOrgId(String.valueOf(map.get("orgId")));
		List<String> orgs = (List<String>)map.get("orgs");
		cs = customSummaryRepository.save(cs);
		
		List<CustomSummaryOrgRel> os = new ArrayList<>();
		CustomSummaryOrgRel csor = null;
		for (int i = 0; i < orgs.size(); i++) {
			csor = new CustomSummaryOrgRel();
			csor.setOrgId(orgs.get(i));
			csor.setCusId(cs.getId());
			os.add(csor);
		}
		customSummaryOrgRelRepository.save(os);
		return success(cs);
	}

	@PostMapping(value = "/queryByOrgId")
	public ResultDto query(@RequestBody Map<String, Object> map, HttpServletRequest request) {
		UserDto user = getCachedUserDto(request);
		map.put("userId", user.getId());
		map.put("isAgent", user.getIsAgent());
		map.put("uniqueId", user.getAdminOrganizationId());
		List<Task> taskList = this.taskExecuteService.findHomepageTask(map);
		Map<String, Object> r = this.taskExecuteService.findProgressPercent(map);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("taskList", taskList);
		result.putAll(r);
		return new ResultDto(ResultDto.CODE_SUCCESS, "", result);
	}
	
	@PostMapping(value = "/importData")
	public ResultDto importData(@RequestBody Map<String, Object> map, HttpServletRequest request) {
		taskExecuteMapper.importData();
		Map<String, Object> result = new HashMap<String, Object>();
		return new ResultDto(ResultDto.CODE_SUCCESS, "", result);
	}

	/**
	 * 自定义汇总
	 * @param map
	 * @return
	 */
	@PostMapping(value="/zdySummary")
	public ResultDto zdySummary(@RequestBody Map<String, Object> map) {
		Integer integer = this.taskExecuteService.zdySummary(map);
		return new ResultDto(ResultDto.CODE_SUCCESS, "", integer);
	}

	//进度明细获取上一级机构信息
	@PostMapping(value="/progress/getUpperOrg")
	public ResultDto getUpperOrg(@RequestBody Map<String, Object> map) {
		String orgId = (String)map.get("orgId");
		Org org = orgService.getOrgById(orgId);
//		if(!org.getSubjectionPid().equals("1111111111111111")){
//			Org upperOrg = orgService.getOrgById(org.getSubjectionPid());
//		}
		//System.out.println("id-名称-pid-名称 >>>> " + orgId + "--" + org.getName() + "--" + org.getSubjectionPid());
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("orgId", orgId);
		result.put("orgName", org.getName());
		result.put("orgPId", org.getSubjectionPid());
		//result.put("orgPName", upperOrg.getName());
		
		return new ResultDto(ResultDto.CODE_SUCCESS, "", result);
	}

	/**
	 * 查询单个任务对象(级联表期信息)
	 * @param taskId
	 * @return
	 */
	@GetMapping(value = "find/{taskId}")
	public Task findTaskById(@PathVariable  String taskId) {
		return this.taskExecuteService.findTaskById(taskId);
	}
}
