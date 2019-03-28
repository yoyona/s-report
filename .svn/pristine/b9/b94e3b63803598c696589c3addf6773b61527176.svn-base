/*
 * 版权所有 2017 北航冠新。
 * 保留所有权利。
 */
package net.greatsoft.core.web.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import net.greatsoft.core.domain.model.system.PermissionResource;
import net.greatsoft.core.domain.model.system.Role;
import net.greatsoft.core.domain.model.system.RoleScope;
import net.greatsoft.core.domain.model.system.User;
import net.greatsoft.core.repository.system.UserRepository;
import net.greatsoft.core.service.system.ResourceService;
import net.greatsoft.core.service.system.RoleService;
import net.greatsoft.core.util.ProjectUtils;
import net.greatsoft.core.util.constant.SystemConstant;
import net.greatsoft.core.web.BaseController;
import net.greatsoft.core.web.dto.ResultDto;
import net.greatsoft.core.web.dto.SaveRoleDto;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;


@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {
	@Resource
	private RoleService roleService;

	@Resource
	private UserRepository userRepository;

	/**
	 * 新增角色信息
	 */
	@Transactional
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResultDto add(@RequestBody SaveRoleDto params, HttpSession session) {
		User currentUser = getCurrentUser(session);

		Role role = params.getRole();
		List<String> resourceIdList = params.getResourceIds();
		roleService.addRole(role, resourceIdList, currentUser);
		// 保存角色范围
		/*List<String> roleList = params.getRoleScopeList();
		if (roleList != null && !roleList.isEmpty()) {
			if (roleList != null && !roleList.isEmpty()) {
				for (String string : roleList) {
					RoleScope roleScope = new RoleScope();
					roleScope.setRoleId(role.getId());
					roleScope.setRoleIdEd(string);
					this.roleService.insertRoleScope(roleScope);
				}
			}
		}*/
		RoleScope roleScope = new RoleScope();
		roleScope.setRoleId(role.getpId());
		roleScope.setRoleIdEd(role.getId());
		this.roleService.insertRoleScope(roleScope);
		return success("success");
	}

	/**
	 * 修改角色信息
	 */
	@Transactional
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResultDto update(@RequestBody SaveRoleDto params,
			HttpSession session) {
		User currentUser = getCurrentUser(session);

		Role role = params.getRole();
		List<String> resourceIds = params.getResourceIds();
		roleService.updateRole(role, resourceIds, currentUser);
		// 保存角色范围
//		this.roleService.deleteRoleScopeByRoleId(role.getId());
//		List<String> roleList = params.getRoleScopeList();
//		if (roleList != null && !roleList.isEmpty()) {
//			for (String string : roleList) {
//				RoleScope roleScope = new RoleScope();
//				roleScope.setRoleId(role.getId());
//				roleScope.setRoleIdEd(string);
//				this.roleService.insertRoleScope(roleScope);
//			}
//		}
		return success("success");
	}

	/**
	 * 更新是否启用
	 */
	@Transactional
	@RequestMapping(value = "/updateIsValid/{roleId}/{isValid}", method = RequestMethod.PUT)
	public ResultDto updateIsValid(@PathVariable String roleId,
			@PathVariable String isValid, HttpSession session) {
		User currentUser = getCurrentUser(session);

		if (StringUtils.isBlank(roleId) || StringUtils.isBlank(isValid)) {
			return fail(null, null);
		}
		Role role = roleService.updateIsValid(roleId, Integer.valueOf(isValid),
				currentUser);
		return success(role);
	}

	/**
	 * 删除角色
	 */
	@Transactional
	@RequestMapping(value = "/delete/{roleId}", method = RequestMethod.DELETE)
	public ResultDto delete(@PathVariable String roleId) {
		roleService.deleteRole(roleId);
		return success(null);
	}

	/**
	 * 查询角色列表
	 */
	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public ResultDto findAll() {
		List<Map<String, Object>> roleMapList = new ArrayList<>();

		List<Role> roleList = roleService.findAllRole();
		for (Role role : roleList) {
			List<String> resourceNameList = new ArrayList<>();
			List<PermissionResource> resourceList = role.getResources();
			for (PermissionResource resource : resourceList) {
				resourceNameList.add(resource.getName());
			}

			Map<String, Object> roleMap = new HashMap<>();

			if (null != role.getCreateUserId()) {
				User createUser = userRepository
						.findOne(role.getCreateUserId());
				roleMap.put("createUser", createUser.getRealName());
			}
			if (null != role.getUpdateUserId()) {
				User updateUser = userRepository
						.findOne(role.getUpdateUserId());
				roleMap.put("updateUser", updateUser.getRealName());
			}

			roleMap.put("id", role.getId());
			roleMap.put("pId", role.getpId());
			roleMap.put("name", role.getName());
			roleMap.put("flag", role.getFlag());
			roleMap.put("type", role.getType());
			roleMap.put("remarks", role.getRemark());
			roleMap.put("description", role.getDescription());
			roleMap.put("isValid", role.getIsValid());
			roleMap.put("resources", StringUtils.join(resourceNameList, "，"));
			roleMap.put("createTime", role.getCreateTime());
			roleMap.put("updateTime", role.getUpdateTime());
			roleMapList.add(roleMap);
		}

		return success(roleMapList);
	}

	@RequestMapping(value = "/query/{fillType}", method = RequestMethod.GET)
	public ResultDto queryRoleList(@PathVariable String fillType) {
		List<Role> roleList = null;
		if("01".equals(fillType)){
			roleList = roleService.queryRoleList("5");//主管填报用户
		}else{
			roleList = roleService.queryRoleList("3");//基层填报用户
		}
		return success(roleList);
	}
	
	/**
	 * 根据角色的 ID 查询
	 */
	@RequestMapping(value = "/get/{roleId}", method = RequestMethod.GET)
	public ResultDto findById(@PathVariable String roleId) {
		Role role = roleService.findRoleById(roleId);

		List<String> idList = new ArrayList<>();
		List<PermissionResource> resources = role.getResources();
		for (PermissionResource resource : resources) {
			idList.add(resource.getId());
		}
		// 查询角色范围
		List<RoleScope> roleScopeList =this.roleService.queryRoleScope(roleId);
		Map<String, Object> result = new HashMap<>();
		result.put("role", role);
		result.put("idList", idList);
		result.put("roleScopeList", roleScopeList);
		List<Role> rList = new ArrayList<Role>();
		// 拼出所选角色范围的角色列表
		if (roleScopeList != null && !roleScopeList.isEmpty()) {
		   List<Role> roleList = this.roleService.findAllRole();
		   for (Role role2 : roleList) {
			 for (int i = 0; i < roleScopeList.size(); i++) {
				 RoleScope roleScope = roleScopeList.get(i);
				 if (roleScope.getRoleIdEd().equals(role2.getId())){
					 rList.add(role2); 
				 }
			 }   
		   }
		} 
		result.put("rList", rList);
		return success(result);
	}

	/**
	 * 验证角色名称是否已存在
	 */
	@RequestMapping(value = "/checkExistName", method = RequestMethod.POST)
	public ResultDto checkExistName(@RequestBody Map<String, Object> params) {
		String name = (String) params.get("name");
		String id = (String) params.get("id");
		Role role = roleService.findRoleByName(name);
		if (role == null) {
			return success(null);
		}
		if (role.getId().equals(id)) {
			return success(null);
		}

		role.setResources(null);
		return success(role);
	}
	/**
	 * 通过角色Id列表来查询所含角色范围的角色列表信息
	 * @param params
	 * @return
	 */
	@PostMapping(value="/getRoleByRoleScope")
	public ResultDto getRoleByRoleScope(@RequestBody Map<String,Object> params){
		Map<String,Object> result = new HashMap<String,Object>();
		List<String> ids = (List<String>) params.get("ids");
		List<Role> roleList = this.roleService.queryRoleByRoleIdList(ids);
		result.put("roleList", roleList);
		return success(result);
	}
	/**
	 * 保存任务范围
	 * @param params
	 * @return
	 */
	@PostMapping(value="/saveRoleScope")
	public ResultDto saveRoleScope(@RequestBody SaveRoleDto saveRoleDto){
		Map<String,Object> result = new HashMap<String,Object>();
		String highierRole = saveRoleDto.getHighierRole();
		this.roleService.deleteRoleScopeByRoleId(highierRole);
		List<String> roleList = saveRoleDto.getRoleScopeList();
		if (roleList != null && !roleList.isEmpty()) {
			for (String string : roleList) {
				RoleScope roleScope = new RoleScope();
				roleScope.setRoleId(highierRole);
				roleScope.setRoleIdEd(string);
				this.roleService.insertRoleScope(roleScope);
			}
		}
		result.put("message", "保存成功");
		return success(result);
	}
}
