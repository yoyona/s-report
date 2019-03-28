/*
 * 版权所有 2018 冠新软件。
 * 保留所有权利。
 */
package net.greatsoft.core.web.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import net.greatsoft.core.domain.mapper.AgentMapper;
import net.greatsoft.core.domain.mapper.TaskMapper;
import net.greatsoft.core.domain.mapper.TaskUserMapper;
import net.greatsoft.core.domain.mapper.UserAgentMapper;
import net.greatsoft.core.domain.mapper.UserMapper;
import net.greatsoft.core.domain.model.system.AgentOrg;
import net.greatsoft.core.domain.model.system.Org;
import net.greatsoft.core.domain.model.system.Role;
import net.greatsoft.core.domain.model.system.User;
import net.greatsoft.core.domain.model.system.UserAgent;
import net.greatsoft.core.domain.model.task.Task;
import net.greatsoft.core.domain.model.task.TaskUser;
import net.greatsoft.core.dto.system.UserDto;
import net.greatsoft.core.repository.system.AgentOrgRepository;
import net.greatsoft.core.repository.system.UserRepository;
import net.greatsoft.core.repository.task.TaskRepository;
import net.greatsoft.core.service.system.AgentService;
import net.greatsoft.core.service.system.UserService;
import net.greatsoft.core.web.BaseController;
import net.greatsoft.core.web.dto.ResultDto;

/**
 * TODO 填写描述信息
 * @since 2018-12-13
 */
@RestController
@RequestMapping(value="/agent")
public class AgentController extends BaseController{
	@Autowired
	AgentService agentService;
	@Autowired
	AgentOrgRepository agentOrgRepository;
	@Autowired
	AgentMapper agentMapper;
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserAgentMapper userAgentMapper;
	@Autowired
	UserMapper userMapper;
	@Autowired
	UserService userService;
	@Autowired
	TaskUserMapper taskUserMapper;
	@Autowired
	TaskMapper taskMapper;
	@Autowired
	TaskRepository taskRepository;
	
	/**
	 * 获取虚拟代理结构列表
	 * @param map
	 * @return
	 * @since 2018-12-13
	 */
	@PostMapping(value="/queryAgentOrgList")
	public ResultDto queryAgentOrgList(@RequestBody Map<String, Object> map){
		List<AgentOrg> list = agentOrgRepository.findAll();
		return success(list);
	}
	
	/**
	 * 查询代理用户列表
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @since 2018-12-14
	 */
	@PostMapping(value="/queryAgentUserList/{pageNo}/{pageSize}")
	public ResultDto queryAgentUserList(@RequestBody(required = false) Map<String, Object> map, @PathVariable Integer pageNo, @PathVariable Integer pageSize){
		Page<Task> page = PageHelper.startPage(pageNo, pageSize);
		List<Map<String,Object>> list = agentMapper.queryAgentUserList(map);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("list", list);
		result.put("pageNo", page.getPageNum());
		result.put("pageSize",page.getPageSize());
		result.put("total", page.getTotal());
		result.put("rows", page.getResult());
		result.put("pages", page.getPages());
		return new ResultDto(ResultDto.CODE_SUCCESS, "", result);
	}
	
	/**
	 * 新增修改代理用户信息
	 * @param map
	 * @return
	 * @since 2018-12-14
	 */
	@PostMapping(value="/editAgentUser")
	@Transactional
	public ResultDto editAgentUser(@RequestBody(required = false) Map<String, Object> map){
		String orgId = (String)map.get("orgId");
		String orgName = (String)map.get("orgName");
		String userId = (String)map.get("userId");
		String userName = (String)map.get("userName");
		String mobile = (String)map.get("mobile");
		String realName = (String)map.get("realName");
		// 判断重复的问题
		Map<String,Object> result = new HashMap<String,Object>();

		List<Map<String,Object>> uaMap = (List)map.get("userAgents");
		
		AgentOrg ao =  new AgentOrg();
		List<UserAgent> uaList = new ArrayList();
		User user =  new User();
		if(orgId == null || "".equals(orgId)){
			ao.setName(orgName);
			ao.setCreateTime(new Date());
			ao.setStatus("0");
			ao = agentOrgRepository.save(ao);
		}
		
		if(userId != null && !"".equals(userId)){
			user.setId(userId);
		} else {
			User u = this.userRepository.findUserByNameAndIsAgent(userName, 1);
			if (u != null) {
				result.put("repeat", true);
				return new ResultDto(ResultDto.CODE_FAIL, "该用户已经存在(用户名重复)", result);
			}
		}
		user.setAdminOrganizationId(orgId != null && !"".equals(orgId) ? orgId : ao.getId());
		user.setName(userName);
		user.setRealName(realName);
		user.setMobilePhone(mobile);
		user.setCreateTime(new Date());
		user.setPassword("9711d33bdf119a4e85b2b64d5df9abdc");
		user.setPasswordSalt("123456");
		user.setIsValid(1);
		user.setIsCancel(0);
		user.setIsAgent(1);
		Role role = new Role();
		role.setId("3");
		Set<Role> sr = new HashSet();
		sr.add(role);
		user.setRoles(sr);
		user = userRepository.save(user);
		UserAgent ua = null;
		for (int i = 0; i < uaMap.size(); i++) {
			ua = new UserAgent();
			ua.setOrgId((String)uaMap.get(i).get("orgId"));
			ua.setUserId(user.getId());
			uaList.add(ua);
		}
		
		userAgentMapper.deleteUserAgentByUserId(userId);
		userAgentMapper.saveUserAgentBatch(uaList);
		List<Task> list = taskRepository.findByStatus("2");
		List<TaskUser> tuList = new ArrayList();
		Task task = null;
		TaskUser tu = null;
		for (int i = 0; i < list.size(); i++) {
			task = list.get(i);
			tu = new TaskUser();
			tu.setTaskId(String.valueOf(task.getTaskId()));
			tu.setUserId(user.getId());
			
			tuList.add(tu);
		}
		taskUserMapper.insertTaskUserBatch(tuList);

		return new ResultDto(ResultDto.CODE_SUCCESS, "", result);
	}
	
	/**
	 * 查询用户代理信息
	 * @param userId
	 * @param request
	 * @return
	 * @since 2018-12-18
	 */
	@PostMapping(value="/queryAgentUser")
	public ResultDto queryAgentUser(String userId,HttpServletRequest request){
		List<Map<String,Object>> map = userMapper.queryAgentUser(userId);
		List<Org> agents = userService.findUserAgentList(userId);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("user", map);
		result.put("agents", agents);
		return new ResultDto(ResultDto.CODE_SUCCESS,"提交成功",result);
	}
	
	/**
	 * 根据机构id查询下属用户列表
	 * @param orgId
	 * @param request
	 * @return
	 * @since 2018-12-18
	 */
	@PostMapping(value="/userList")
	public ResultDto userList(String orgId,HttpServletRequest request){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("orgId", orgId);
		param.put("isValid", 1);
		param.put("isCancel", 0);
		List<User> taskUserList = userMapper.getAllocationTree(param);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("taskUserList", taskUserList);
		return new ResultDto(ResultDto.CODE_SUCCESS,"提交成功",result);
	}
	/**
	 * 用户停用
	 * @param userId
	 * @param request
	 * @return
	 * @since 2018-12-19
	 */
	@PostMapping(value="/updateUser")
	public ResultDto updateUser(String userId,HttpServletRequest request){
		User user = userService.findUsersByUserId(userId);
		//停用
		user.setIsValid(0);
		userService.save(user);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("user", user);
		return new ResultDto(ResultDto.CODE_SUCCESS,"提交成功",result);
	}
}
