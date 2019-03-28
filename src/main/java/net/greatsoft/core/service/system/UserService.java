/*
 * 版权所有 2016 北航冠新。
 * 保留所有权利。
 */
package net.greatsoft.core.service.system;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import net.greatsoft.application.common.domain.util.ObjectClone;
import net.greatsoft.core.DomainRuntimeException;
import net.greatsoft.core.domain.mapper.*;
import net.greatsoft.core.domain.model.system.*;
import net.greatsoft.core.dto.system.UserDto;
import net.greatsoft.core.repository.system.OrgRepository;
import net.greatsoft.core.repository.system.RoleRepository;
import net.greatsoft.core.repository.system.UserRepository;
import net.greatsoft.core.util.CommonUtils;
import net.greatsoft.core.util.ProjectConstants;
import net.greatsoft.core.web.dto.ResultDto;
import net.greatsoft.core.web.login.LoginController;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 用户服务类
 */
@Service
public class UserService {

	@Resource
	private ResourceMapper resourceMapper;
	
	@Resource
	private UserMapper userMapper;
	
	@Resource
	private OrgMapper orgMapper;

	@Resource
	private UserRepository userRepository;

	@Resource
	private RoleRepository roleRepository;
	
	@Resource
	private OrgRepository orgRepository;
	
	@Resource
	private UserAgentMapper userAgentMapper;

	@Resource
	private RoleMapper roleMapper;
	
	/**
	 * 新增用户，同时新增用户角色关系
	 */
	public void addUser(User user, List<String> roleIdList, UserDto currentUser) {
		if (userRepository.findUserByName(user.getName()) != null) {
			throw new DomainRuntimeException("用户名已存在");
		}
		//TODO 待确认作用
		//user.setPlainPassword(user.getPassword());
		user.setIsValid(1);
		user.setIsCancel(0);
		//user.modifiedBy(currentUser);
		for (String roleId : roleIdList) {
			Role role = roleRepository.findOne(roleId);
			user.getRoles().add(role);
		}
		userRepository.save(user);
	}

	/**
	 * 编辑用户，同时编辑用户与角色关系
	 */
	public User updateUser(User userParam, List<String> roleIdList,
			UserDto currentUser) {
		User user = userRepository.findOne(userParam.getId());
		ObjectClone.copy(user, userParam);
		user.modifiedBy(currentUser);
		Set<Role> roles = user.getRoles();
		roles.clear();
		for (String roleId : roleIdList) {
			Role role = roleRepository.findOne(roleId);
			roles.add(role);
		}
		userRepository.save(user);
		return user;
	}

	/**
	 * 修改密码
	 */
	public ResultDto updatePassword(String userId, String oldPassword,
			String newPassword, UserDto currentUser, String passwordSalt) {
		Map<String,Object> r = new HashMap<String,Object>();
		User user = userRepository.findOne(userId);
		if (oldPassword!= null && !user.getPassword().equals(CommonUtils.encrypt(oldPassword))) {
			r.put("message", "旧密码输入错误");
			return new ResultDto(ResultDto.CODE_FAIL,"旧密码输入错误",r);
		}
		/*String password = User.hashPassword(oldPassword,
				user.getPasswordSalt());
		if (!password.equals(user.getPassword())) {
			throw new DomainRuntimeException("旧密码错误");
		}*/
		//user.setPlainPassword(newPassword);
		
		user.modifiedBy(currentUser);
		user.setPassword(CommonUtils.encrypt(newPassword));
		user.setPasswordSalt(passwordSalt);
		userRepository.save(user);
		r.put("user", user);
		return new ResultDto(ResultDto.CODE_SUCCESS,"修改成功",r);
	}

	/**
	 * 删除用户信息
	 */
	public void deleteUser(String userId, UserDto currentUser) {
		User user = userRepository.findOne(userId);
		user.setIsCancel(1);
		user.modifiedBy(currentUser);
		userRepository.save(user);
	}

	/**
	 * 查询用户信息
	 */
	public List<User> searchUser(Map<String, Object> params) {
		return userMapper.searchUser(params);
	}

	/**
	 * 根据用的 ID 查询用户信息
	 */
	public Map<String, Object> findUserMapById(String id) {
		Map<String, Object> result = new HashMap<>();

		User user = userRepository.findOne(id);
		List<Role> allRole = roleRepository.findAll();
		List<String> roleIds = new ArrayList<>();
		Set<Role> roles = user.getRoles();
		for (Role role : roles) {
			roleIds.add(role.getId());
		}
		//将用户的机构信息放入对象中
		Org org = orgRepository.getOrgByUniqueId(user.getAdminOrganizationId());
		user.setOrgName(org.getName());
		result.put("user", user);
		result.put("allRole", allRole);
		result.put("roleIds", roleIds);

		return result;
	}

	/**
	 * 根据用户名密码查询用户
	 */
	public User login(String username, String password, Integer isAgent) {
		User user = null;
		if (isAgent != null) {
			user = userRepository.findUserByNameAndIsAgent(username, isAgent);
		} else {
			user = userRepository.findUserByName(username);
		}
		/*String password = User.hashPassword(plainPassword,
				user.getPasswordSalt());*/
		if (user == null || !password.equals(user.getPassword())) {
			return null;
		}
		return user;
	}

	/**
	 * 获取人员配置的固定机构下面的人员树形列表
	 * @param orgId
	 * @param roleId
	 * @return
	 */
	public List<User> getUsersTree(String orgId,String roleId) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("orgId", orgId);
		param.put("roleId", roleId);
		return this.userMapper.getUsersTree(param);
	}
	
	public User findUsersByUserId(String usersId) {
		return userRepository.findUsersById(usersId);
	}
	
	/**
	 * 根据用户的 ID 查询该用户的权限资源
	 * 
	 * @param userId
	 *            用户的 ID
	 */
	public PermissionResource findLoginInfo(String userId,String roleType) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("userId", userId);
		param.put("roleType", roleType);
		// 查询改用户的所有资源角色
		List<PermissionResource> list = resourceMapper
				.findResourcesByUsersId(param);
		if(list == null || list.size() == 0){
			return null;
		}
		PermissionResource topMenu = resourceMapper
				.findByResourcesId(ProjectConstants.RESOURCE_TOP_ID);
		// topMenu = getChildren(topMenu, list);
		List<PermissionResource> treeList = new ArrayList<>();
		for (PermissionResource resources : list) {
			if (ProjectConstants.RESOURCE_TOP_ID.equals(resources.getParentId())
					&& !treeList.contains(resources)) {
				treeList.add(resources);
			}
			for (PermissionResource r : list) {
				if (resources.getId().equals(r.getParentId())) {
					if (resources.getChildren() == null) {
						List<PermissionResource> resourcesList = new ArrayList<>();
						resourcesList.add(r);
						resources.setChildren(resourcesList);
					} else {
						if (resources.getChildren().contains(r)) {
							continue;
						} else {
							resources.getChildren().add(r);
						}
					}
				}
			}
		}
		topMenu.setChildren(treeList);
		// 一级目录排序
		Collections.sort(topMenu.getChildren());
		// 二级目录排序
		for (PermissionResource resource : topMenu.getChildren()) {
			Collections.sort(resource.getChildren());
			// 三级目录排序
			for (PermissionResource r : resource.getChildren()) {
				Collections.sort(r.getChildren());

			}
		}
		return topMenu;
	}
	
	public List<Org> findUserAgentList(String userId){
		List<Org> agents = orgMapper.findUserAgentList(userId);
		return agents;
	}
	
	public User save(User user){
		return this.userRepository.saveAndFlush(user);
	}
	/**
	 * 批量导入代理机构
	 * @param uList
	 */
	public void saveUserAgentBatch(List<UserAgent> uList) {
		userAgentMapper.deleteUserAgentByUserId(uList.get(0).getUserId());
		userAgentMapper.saveUserAgentBatch(uList);
	}
	/**
	 * 通过机构唯一id数组查询人员信息
	 * @param uniqueIds
	 * @return
	 */
	public List<User> findUsersByUniqueIdArray(String[] uniqueIds) {
		Map<String,Object>  params = new HashMap<String,Object>();
		params.put("orgList", uniqueIds);
		List<User>  userList = this.userMapper.searchUser(params);
		return userList;
	}

	/**
	 * 删除所有用户数据
	 */
	@Transactional
	public void deleteAllUsersInfo() {
		this.userMapper.deleteAllUsersInfo();
	}




	/**
	 * 删除所有用户和角色的关联信息
	 * 方便重组
	 */
	@Transactional
	public void deleteAllUsersRole() {
		roleMapper.deleteAllUsersRole();
	}

	@Transactional
	public void insertOpenUsersInfo() {
		userMapper.insertOpenUsersInfo();
	}

	@Transactional
	public void insertUsersRoleFromOpen() {
		roleMapper.insertUsersRoleFromOpen();
	}

	/**
	 *
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> findUserAgentListWithAuditStatus(Map<String, Object> params) {
		return this.orgMapper.findUserAgentListWithAuditStatus(params);
	}
}
