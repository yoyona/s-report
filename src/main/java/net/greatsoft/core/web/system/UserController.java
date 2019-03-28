/*
 * 版权所有 2017 北航冠新。
 * 保留所有权利。
 */
package net.greatsoft.core.web.system;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import net.greatsoft.core.domain.mapper.TaskUserMapper;
import net.greatsoft.core.domain.model.system.Org;
import net.greatsoft.core.domain.model.system.OrgHistory;
import net.greatsoft.core.domain.model.system.Role;
import net.greatsoft.core.domain.model.system.User;
import net.greatsoft.core.domain.model.system.UserAgent;
import net.greatsoft.core.domain.model.system.UserRole;
import net.greatsoft.core.domain.model.task.TaskUser;
import net.greatsoft.core.dto.system.SaveUserDto;
import net.greatsoft.core.dto.system.UserAgentDto;
import net.greatsoft.core.dto.system.UserDto;
import net.greatsoft.core.repository.system.OrgRepository;
import net.greatsoft.core.repository.system.UserRepository;
import net.greatsoft.core.service.system.RoleService;
import net.greatsoft.core.service.system.UserService;
import net.greatsoft.core.util.CommonUtils;
import net.greatsoft.core.util.DateUtil;
import net.greatsoft.core.util.ParamUtil;
import net.greatsoft.core.util.ProjectUtils;
import net.greatsoft.core.util.constant.SystemConstant;
import net.greatsoft.core.web.BaseController;
import net.greatsoft.core.web.dto.ResultDto;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;


@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

	@Resource
	private UserService userService;

	@Resource
	private OrgRepository orgRepository;

	@Resource
	private UserRepository userRepository;
	
	@Resource
	private RoleService roleSevice;
	/**
	 * 保存新用户
	 */
	@Transactional
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResultDto add(@RequestBody SaveUserDto params, HttpSession session,HttpServletRequest request) {
		UserDto currentUser = getCachedUserDto(request);
		User user = params.getUser();
		// 加密密码
		user.setPassword(ParamUtil.encrypt(user.getPassword()));
		user.setCreateTime(new Date());
		// 资源菜单的 id
		List<String> roleIdList = params.getRoleIds();
		userService.addUser(user, roleIdList, currentUser);

		return success("success");
	}

	/**
	 * 编辑用户信息
	 */
	@Transactional
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResultDto update(@RequestBody SaveUserDto params,
			HttpSession session,HttpServletRequest request) {
		UserDto currentUser = getCachedUserDto(request);

		User user = params.getUser();
		List<String> roleIdList = params.getRoleIds();
		User newUser = userService.updateUser(user, roleIdList, currentUser);

		return success(newUser);
	}

	/**
	 * 修改密码
	 */
	@Transactional
	@PostMapping(value = "/changePassword")
	public ResultDto changePassword(@RequestBody Map<String, String> params,HttpServletRequest request) {
		UserDto currentUser = getCachedUserDto(request);
		String userId = params.get("id");
		String oldPassword = params.get("oldPassword");
		String newPassword = params.get("newPassword");
		String passwordSalt = params.get("passwordSalt");// 密码盐 存放明文
		return userService.updatePassword(userId, oldPassword,newPassword, currentUser,passwordSalt);
	}

	/**
	 * 删除用户信息
	 */
	@Transactional
	@RequestMapping(value = "/delete/{userId}", method = RequestMethod.DELETE)
	public ResultDto delete(@PathVariable String userId, HttpSession session,HttpServletRequest request) {
		UserDto currentUser = /*getCurrentUser(session)*/getCachedUserDto(request);
		userService.deleteUser(userId, currentUser);
		return success("success");
	}

	/**
	 * 查询
	 */
	@RequestMapping(value = "/searchUser/{pageNo}/{pageSize}", method = RequestMethod.POST)
	public ResultDto searchUser(
			@RequestBody(required = false) Map<String, Object> params,@PathVariable Integer pageNo,@PathVariable Integer pageSize,HttpServletRequest request) {
		UserDto currentUser = getCachedUserDto(request);
		params.put("pid",currentUser.getAdminOrganizationId());
		// 查询当前用户的角色信息
		List<Role> roles = roleSevice.findRoleByUsersId(currentUser.getId());
		for (Role role : roles) {
			if (SystemConstant.ADMIN_USER_ID.equals(role.getId())) {
				params.put("admin","1");
				break;
			}
		}
		List<Map<String, Object>> userMapList = new ArrayList<>();
		Page<User> page = PageHelper.startPage(pageNo, pageSize);
		List<User> userList = userService.searchUser(params);
		for (User user : userList) {
			List<String> roleNameList = new ArrayList<>();
			Set<Role> roleList = userRepository.findOne(user.getId())
					.getRoles();
			for (Role role : roleList) {
				roleNameList.add(role.getName());
			}
			Org org = new Org();
			org = orgRepository
					.getOrgByUniqueId(user.getAdminOrganizationId());
			Map<String, Object> userMap = new HashMap<>();
			userMap.put("id", user.getId());
			userMap.put("name", user.getName());
			userMap.put("realName", user.getRealName());
			userMap.put("orgName", org.getName());
			userMap.put("roleNames", StringUtils.join(roleNameList, "，"));
			userMap.put("isValid", user.getIsValid());
			userMap.put("createTime", DateUtil.format(user.getCreateTime(), DateUtil.YMDHMS_PATTERN));
			userMap.put("updateTime", DateUtil.format(user.getUpdateTime(), DateUtil.YMDHMS_PATTERN));
			userMap.put("orgId", org.getId());;
			userMapList.add(userMap);
		}
		
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("pageNo", page.getPageNum());
		result.put("pageSize",page.getPageSize());
		result.put("total", page.getTotal());
		result.put("rows", userMapList);
		result.put("pages", page.getPages());
		return success(result);
	}
	
	/**
	 * 查询
	 */
	@RequestMapping(value = "/searchUser", method = RequestMethod.POST)
	public ResultDto searchUser(
			@RequestBody(required = false) Map<String, Object> params) {
		// 获取当前登录的用户信息
		List<Map<String, Object>> userMapList = new ArrayList<>();
		List<User> userList = userService.searchUser(params);
		
		for (User user : userList) {
			List<String> roleNameList = new ArrayList<>();
			Set<Role> roleList = userRepository.findOne(user.getId())
					.getRoles();
			for (Role role : roleList) {
				roleNameList.add(role.getName());
			}
			Org org = new Org();
			org = orgRepository
					.getOrgByUniqueId(user.getAdminOrganizationId());
			Map<String, Object> userMap = new HashMap<>();
			userMap.put("id", user.getId());
			userMap.put("name", user.getName());
			userMap.put("realName", user.getRealName());
			userMap.put("orgName", org.getName());
			userMap.put("roleNames", StringUtils.join(roleNameList, "，"));
			userMap.put("isValid", user.getIsValid());
			userMap.put("createTime", DateUtil.format(user.getCreateTime(), DateUtil.YMDHMS_PATTERN));
			userMap.put("updateTime", DateUtil.format(user.getUpdateTime(), DateUtil.YMDHMS_PATTERN));
			userMapList.add(userMap);
		}
		return success(userMapList);
	}

	/**
	 * 根据用户 ID 查询用户信息
	 */
	@RequestMapping(value = "/get/{userId}", method = RequestMethod.GET)
	public ResultDto findById(@PathVariable String userId) {
		Map<String, Object> result = userService.findUserMapById(userId);
		if (result == null) {
			return fail("用户不存在", result);
		}
		return success(result);
	}
	/**
	 * 保存代理机构信息
	 * @param params
	 * @return
	 */
	@PostMapping(value="/agent/save")
	public ResultDto saveUserAgent(@RequestBody SaveUserDto params){
		Map<String,Object> result = new HashMap<>();
		List<UserAgent> usreAgentList = params.getUsreAgentList();
		// 批量导入
		this.userService.saveUserAgentBatch(usreAgentList);
		result.put("message", "导入成功");
		return new ResultDto(ResultDto.CODE_SUCCESS,"导入成功",result);
	}
	/**
	 * 通过机构唯一id查询用户列表
	 * @param uniqueId
	 * @return
	 */
	@PostMapping(value="/findUserByUniqueIds")
	public ResultDto findUserByUniqueId(@RequestBody String[] uniqueIds){
		Map<String,Object> result = new HashMap<>();
		List<User> userList = this.userService.findUsersByUniqueIdArray(uniqueIds);
		// List<User> userList = this.userRepository.findUserByOrgId(uniqueId);
		result.put("userList",userList);
		return new ResultDto(ResultDto.CODE_SUCCESS,"成功",result);
	}

	/**
	 * 查询用户代理的机构列表附带审核状态信息
	 * @param params
	 * @return
	 */
	@PostMapping(value = "/agent/list/status")
	public ResultDto findUserAgentListWithAuditStatus(@RequestBody Map<String, Object> params) {
		List<Map<String, Object>> result = this.userService.findUserAgentListWithAuditStatus(params);
		return new ResultDto(ResultDto.CODE_SUCCESS,"成功",result);
	}
	
	/**
	 * 支撑平台修改用户密码同步方法
	 * @param map
	 * @param request
	 * @return
	 * @since 2019-1-2
	 */
	@PostMapping(value = "/syn/user")
    public Map<String, Object> synUser(@RequestBody Map<String, Object> map,HttpServletRequest request) {
		String userId = (String)map.get("userId");
		String newPassword = (String)map.get("password");
		User user = userRepository.findOne(userId);
		user.setPassword(CommonUtils.encrypt(newPassword));
		userRepository.save(user);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("status", 0);
		result.put("message", "操作成功");
        return result;
	}
}
