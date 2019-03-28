package net.greatsoft.core.web.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.greatsoft.core.service.system.UserService;
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

import net.greatsoft.core.domain.model.system.User;
import net.greatsoft.core.domain.model.task.FileAdditional;
import net.greatsoft.core.domain.model.task.Period;
import net.greatsoft.core.domain.model.task.Task;
import net.greatsoft.core.domain.model.task.TaskScope;
import net.greatsoft.core.domain.model.task.TaskUser;
import net.greatsoft.core.domain.model.task.TemplateInfo;
import net.greatsoft.core.dto.system.UserDto;
import net.greatsoft.core.dto.task.OrgDto;
import net.greatsoft.core.repository.task.TemplateInfoRepository;
import net.greatsoft.core.service.task.TaskService;
import net.greatsoft.core.util.DateUtil;
import net.greatsoft.core.web.BaseController;
import net.greatsoft.core.web.dto.ResultDto;


/**
 * @date 2017年2月14日 下午1:42:22
 * @author Litian
 * @Description:任务控制器
 *
 */
@RestController
@RequestMapping(value="/task")
public class TaskController extends BaseController{
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private TemplateInfoRepository templateInfoRepository;

	@Autowired
	private UserService userService;

	/**
	 * 通过条件查询任务
	 * @param map
	 * @return
	 */
	@PostMapping(value="/query/{pageNo}/{pageSize}")
	public ResultDto query(@RequestBody(required = false) Map<String, Object> map,@PathVariable Integer pageNo,@PathVariable Integer pageSize){
		Object startDateO = map.get("startDate");
		Object endDateO = map.get("endDate");
		Object statusO = map.get("status");
		if(startDateO != null && !"".equals(startDateO)){
			map.put("startDate",DateUtil.parse(String.valueOf(startDateO+" 00:00:00"), "yyyy-MM-dd HH:mm:ss"));
		}
		if(endDateO != null && !"".equals(endDateO)){
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
     	Page<Task> page = PageHelper.startPage(pageNo, pageSize);
		List<Task> allTasks = this.taskService.findAllTasks(map);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("rows", allTasks);
		result.put("pageNo", page.getPageNum());
		result.put("pageSize",page.getPageSize());
		result.put("total", page.getTotal());
		result.put("rows", page.getResult());
		result.put("pages", page.getPages());
		return new ResultDto(ResultDto.CODE_SUCCESS, "", result);
	}
	/**
	 * 保存任务信息
	 * @param map
	 * @return
	 */
	@PostMapping(value="/add")
	public ResultDto save(@RequestBody(required = false) Map<String, Object> map,HttpServletRequest request){
		String taskStr = JSON.toJSONString(map.get("task"));
		Task task = JSON.parseObject(taskStr,Task.class);
		//将登陆的用户信息放入任务中
		//User user = getCurrentUser(request.getSession());
		UserDto user = getCachedUserDto(request);
		task.setCreateUserId(user.getId());
		List<String> fileListStr = (List<String>) map.get("fileList");
		List<FileAdditional> fileList =  new ArrayList<FileAdditional>();
		if(fileListStr != null && fileListStr.size() > 0){
			for (int i = 0; i < fileListStr.size(); i++) {
				String string = JSON.toJSONString(fileListStr.get(i));
				if(StringUtils.isEmpty(string)){
					continue;
				}
				FileAdditional fileAdditional = JSON.parseObject(string,FileAdditional.class);
				fileList.add(fileAdditional);
			}
		}
		List<Period> periodList = new ArrayList<Period>();
		List<String> periodListStr = (List<String>) map.get("periodList");
		if(periodListStr != null && periodListStr.size() > 0){
			for (int i = 0; i < periodListStr.size(); i++) {
				String string =JSON.toJSONString(periodListStr.get(i));
				if(StringUtils.isEmpty(string)){
					continue;
				}
				Period period = JSON.parseObject(string,Period.class);
				periodList.add(period);
			}
		}
		task.setFileAdditionals(fileList);
		task.setPeriods(periodList);
		task.setCreateTime(new Date());
		Task add = this.taskService.add(task);
		return new ResultDto(ResultDto.CODE_SUCCESS,"保存成功",add);
	}
	
	/**
	 * 修改任务信息
	 * @param map
	 * @return
	 */
	@PostMapping(value="/edit")
	public ResultDto update(@RequestBody(required = false) Map<String, Object> map){
		String taskStr = JSON.toJSONString(map.get("task"));
		Task task = JSON.parseObject(taskStr,Task.class);
		List<String> fileListStr = (List<String>) map.get("fileList");
		List<FileAdditional> fileList =  new ArrayList<FileAdditional>();
		if(fileListStr != null && fileListStr.size() > 0){
			for (int i = 0; i < fileListStr.size(); i++) {
				String string = JSON.toJSONString(fileListStr.get(i));
				if(StringUtils.isEmpty(string)){
					continue;
				}
				FileAdditional fileAdditional = JSON.parseObject(string,FileAdditional.class);
				fileList.add(fileAdditional);
			}
		}
		List<Period> periodList = new ArrayList<Period>();
		List<String> periodListStr = (List<String>) map.get("periodList");
		if(periodListStr != null && periodListStr.size() > 0){
			for (int i = 0; i < periodListStr.size(); i++) {
				String string =JSON.toJSONString(periodListStr.get(i));
				if(StringUtils.isEmpty(string)){
					continue;
				}
				Period period = JSON.parseObject(string,Period.class);
				periodList.add(period);
			}
		}
		task.setFileAdditionals(fileList);
		task.setPeriods(periodList);
		return this.taskService.update(task);
//		return new ResultDto(ResultDto.CODE_SUCCESS,"修改成功",task);
	}
	/**
	 * 查询单条任务信息
	 * @param id
	 * @return
	 */
	@GetMapping(value="/get/{id}")
	public ResultDto get(@PathVariable Long id){
		Map<String, Object> map = this.taskService.get(id);
		return new ResultDto(ResultDto.CODE_SUCCESS,"",map);
	}
	/**
	 * 任务模板绑定(为任务指定对应的亿信华辰模板名称)
	 * @param map
	 * @return
	 */
	@PostMapping(value="/binding")
	public ResultDto binding(@RequestBody Map<String, Object> map){
		List<String> templateListStr = (List<String>) map.get("tempList");
		List<TemplateInfo> templateList = new ArrayList<TemplateInfo>();
		if(templateListStr != null && templateListStr.size() > 0){
			for (int i = 0; i < templateListStr.size(); i++) {
				String string = JSON.toJSONString(templateListStr.get(i));
				if(StringUtils.isEmpty(string)){
					continue;
				}
				TemplateInfo templateInfo = JSON.parseObject(string,TemplateInfo.class);
				templateInfo.setTemplateId(templateInfo.getTemplateId().trim());
				templateInfo.setDiagnosisId(templateInfo.getDiagnosisId().trim());
				templateList.add(templateInfo);
			}
		}
		TemplateInfo infos = templateInfoRepository.getTmeplateInfoByTaskIdAndTemplateType(templateList.get(0).getTaskId(), templateList.get(0).getTemplateType());
		if(infos != null){
			return new ResultDto(ResultDto.CODE_FAIL,"已绑定此类型模板",null);
		}
		List<TemplateInfo>   templateInfo = taskService.binding(templateList);
		return new ResultDto(ResultDto.CODE_SUCCESS,"",templateInfo);
	}
	/**
	 * 查询已经绑定的模版信息
	 * @param taskId
	 * @return
	 */
	@GetMapping(value="binding/list/{taskId}")
	public ResultDto bindingList(@PathVariable Long taskId){
		List<TemplateInfo>   templateInfo = taskService.bindingList(taskId);
		return new ResultDto(ResultDto.CODE_SUCCESS,"",templateInfo);
	}
	
	/**
	 * 任务范围(每个任务有那些个机构能够操作)
	 * @param map
	 * @return
	 */
	@PostMapping(value="/scope")
	public ResultDto scope(@RequestBody(required = false) Map<String, Object> map){
		List<String> taskScopesStr = (List<String>) map.get("taskScopeList");
		List<TaskScope> taskScopes = new ArrayList<TaskScope>();
		if(taskScopesStr != null && taskScopesStr.size() > 0){
			for (int i = 0; i < taskScopesStr.size(); i++) {
				String s = JSON.toJSONString(taskScopesStr.get(i));
				if(StringUtils.isEmpty(s)){
					continue;
				}
				TaskScope taskScope = JSON.parseObject(s,TaskScope.class);
				taskScopes.add(taskScope);
			}
		}
		List<String>   uniqueIdList = (List<String>) map.get("uniqueIdList");
		Long taskId  = JSON.parseObject(JSON.toJSONString(map.get("taskId")),Long.class);
		this.taskService.scope(taskScopes,taskId,uniqueIdList);
		return new ResultDto(ResultDto.CODE_SUCCESS,"操作成功",null);
	}

	/**
	 * 查询任务对应的范围信息
	 * @param map
	 * @return
	 */
	@GetMapping("/scope/list/{taskId}")
	public ResultDto scopeList(@PathVariable Long taskId){
		List<OrgDto> orgList = this.taskService.getTaskScope(taskId);
		return new ResultDto(ResultDto.CODE_SUCCESS,"",orgList);
	}
	
	/**			
	 * 删除单条任务信息
	 * @param id
	 * @return
	 */
	@GetMapping(value="/delete/{id}")
	public ResultDto deleteTask(@PathVariable Long id){
		 Integer result = this.taskService.delete(id);
		 Map<String,Object> r = new HashMap<String,Object>();
		 r.put("code", 0);
		 r.put("message","删除成功");
		 return new ResultDto(ResultDto.CODE_SUCCESS,"删除成功",r);
	}

	/**
	 * 发布任务
	 * @param taskId
	 * @return
	 */
	@GetMapping(value="/release/{taskId}/{status}/{type}")
	public ResultDto release(@PathVariable Long taskId,@PathVariable String status,@PathVariable String type,HttpServletRequest request){
		Map<String,Object> result = new HashMap<String,Object>();
		UserDto user = getCachedUserDto(request);
		Integer release = this.taskService.release(taskId,status,type,user);
		result.put("flag", release == 1 ? true : false);
		return new ResultDto(release == 1 ?  ResultDto.CODE_SUCCESS : ResultDto.CODE_FAIL,release == 1 ? "操作成功" : "操作失败",result);
	}

	/**
	 * 任务开关
	 * @param taskId
	 * @return
	 */
	@GetMapping(value="/switch/{taskId}/{status}")
	public ResultDto switchTask(@PathVariable Long taskId,@PathVariable String status,HttpServletRequest request){
		UserDto user = getCachedUserDto(request);
		Map<String,Object> result = new HashMap<String,Object>();
		Integer switchTask = this.taskService.switchTask(taskId,status,user);
		result.put("flag", switchTask == 1 ? true : false);
		return new ResultDto(switchTask == 1 ? ResultDto.CODE_SUCCESS : ResultDto.CODE_FAIL,switchTask == 1 ? "操作成功" : "操作失败",result);
	}

	/**
	 * 根据用户所属机构查询首页任务各类型数据量
	 * @param orgId
	 * @param request
	 * @return
	 */
	@PostMapping(value="/getTaskCategoryList")
	public ResultDto getTaskCategoryList(HttpServletRequest request){
		//User user = getCurrentUser(request);
		Map<String,Integer> taskCategoryList = taskService.getTaskCategoryList("1");//user.getAdminOrganizationId()
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("taskCategoryMap", taskCategoryList);
		return new ResultDto(ResultDto.CODE_SUCCESS,"",result);
	}

	/**
	 * 根据机构来查询其下所有机构的人员信息
	 * 任务分配树
	 * @param taskId
	 * @param request
	 * @return
	 */
	@PostMapping(value="/allocationTree")
	public ResultDto allocationTree(Long taskId,String orgId,HttpServletRequest request){
		UserDto user = getCachedUserDto(request);
		Map<String,Object> r = new HashMap<String,Object>();
		
		List<User> taskUserList = this.taskService.allocationTree(user.getAdminOrganizationId());
		r.put("taskUserList", taskUserList);
		return new ResultDto(ResultDto.CODE_SUCCESS,"提交成功",r);
	}
	
	/**
	 * 任务分配提交操作
	 */
	@PostMapping(value="/allocation")
	public ResultDto allocation(@RequestBody(required = false) Map<String, Object> map){
		List<TaskUser> taskUsers = new ArrayList<TaskUser>();
		for (int i = 0; i < 11; i++) {
			TaskUser t = new TaskUser();
			t.setTaskId("1");
			t.setUserId(String.valueOf(i));
			taskUsers.add(t);
		}
		this.taskService.allocation(taskUsers);
		return new ResultDto(ResultDto.CODE_SUCCESS,"提交成功",null);
	}

	/**
	 * 查询已选任务分配信息
	 * @param taskId
	 * @return
	 */
	@GetMapping(value="/allocation/list")
	public ResultDto allocationList(Long taskId){
		Map<String,Object> r = new HashMap<String,Object>();
		List<User> taskUserList = this.taskService.allocationList(taskId);
		r.put("userList", taskUserList);
		return new ResultDto(ResultDto.CODE_SUCCESS,"提交成功",r);
	}

	/**
	 * 删除任务附件信息(目前支持单个删除)
	 * @param fileId
	 * @return
	 */
	@GetMapping(value="/fileAdditional/delete/{fileId}")
	public ResultDto deleteFileAdditional(@PathVariable Long fileId){
		this.taskService.deleteFileAdditional(fileId);
		Map<String,Object> r = new HashMap<String,Object>();
		r.put("message", "操作成功");
		return new ResultDto(ResultDto.CODE_SUCCESS,"提交成功",r);
	}

	/**
	 * 删除单个模版信息
	 * @param id
	 * @return
	 */
	@GetMapping(value="/template/delete/{id}")
	public ResultDto deleteTmeplateInfo(@PathVariable Long id){
		this.taskService.deleteTemplateInfo(id);
		Map<String,Object> r = new HashMap<String,Object>();
		r.put("message", "操作成功");
		return new ResultDto(ResultDto.CODE_SUCCESS,"删除成功",r); 
	}

	/**
	 * 废弃任务
	 * @param map
	 * @return
	 */
	@PostMapping(value="/task/abandonTasks")
	public ResultDto abandonTasks(@RequestBody(required = false) Map<String, Object> map){
		List<String> taskListStr = (List<String>) map.get("taskList");
		
		if(taskListStr != null && taskListStr.size() > 0){
			for (int i = 0; i < taskListStr.size(); i++) {
				String s = JSON.toJSONString(taskListStr.get(i));
				if(StringUtils.isEmpty(s)){
					continue;
				}
				Task task = JSON.parseObject(s,Task.class);
				this.taskService.abandon(task);
			}
		}
		Map<String,Object> r = new HashMap<String,Object>();
		return new ResultDto(ResultDto.CODE_SUCCESS,"废弃成功",r);
	}

	/**
	 * 动态增加任务范围数据并且新增审核记录以及任务用户管理数据
	 * @param map
	 * @param request
	 * @return
	 */
	@PostMapping(value="/scope/dynamic")
	public ResultDto dynamicTaskScope(@RequestBody(required = false) Map<String, Object> map, HttpServletRequest request) {
		UserDto user = getCachedUserDto(request);
		List<String> uniqueIdList = (List<String>) map.get("uniqueIdList");
		String taskId = String.valueOf(map.get("taskId"));
		this.taskService.dynamicTaskScope(uniqueIdList, taskId, user);
		return new ResultDto(ResultDto.CODE_SUCCESS, "操作成功", null);
	}

	/**
	 * 切换任务绑定任务信息到用户里面
	 * @param map
	 * @return
	 */
	@PostMapping(value = "/user/set")
	public ResultDto setTaskToUser(@RequestBody Map<String, Object> map) {
		String userId = String.valueOf(map.get("userId"));
		String taskId = String.valueOf(map.get("taskId"));
		String perId = String.valueOf(map.get("periodId"));
		User user = userService.findUsersByUserId(userId);
		user.setTaskId(taskId);
		user.setPerId(perId);
		this.userService.save(user);
		return new ResultDto(ResultDto.CODE_SUCCESS, "", null);
	}


}
