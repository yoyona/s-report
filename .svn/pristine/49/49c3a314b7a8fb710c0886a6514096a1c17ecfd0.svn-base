/*
 * 版权所有 2017 北航冠新。
 * 保留所有权利。
 */
package net.greatsoft.core.web.system;

import java.util.List;

import javax.annotation.Resource;

import net.greatsoft.core.domain.model.system.PermissionResource;
import net.greatsoft.core.service.system.ResourceService;
import net.greatsoft.core.web.BaseController;
import net.greatsoft.core.web.dto.ResultDto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 权限资源管理
 */
@RestController
@RequestMapping("/resource")
public class ResourceController extends BaseController {
	@Resource
	private ResourceService resourceService;

	/**
	 * 新增权限
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResultDto add(@RequestBody PermissionResource resource) {
		PermissionResource result = resourceService.addResource(resource);
		return success(result);
	}

	/**
	 * 更新权限信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResultDto update(@RequestBody PermissionResource resource) {
		PermissionResource result = resourceService.updateResource(resource);
		return success(result);
	}

	/**
	 * 删除权限
	 */
	@RequestMapping(value = "/delete/{resouceId}", method = RequestMethod.DELETE)
	public ResultDto delete(@PathVariable String resouceId) {
		resourceService.deleteResource(resouceId);
		return success("success");
	}

	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public ResultDto findAll() {
		List<PermissionResource> result = resourceService.findAllResource();
		return success(result);
	}

	/**
	 * 查询所有（树形结构）
	 */
	@RequestMapping(value = "/findAllTree", method = RequestMethod.GET)
	public ResultDto findAllTree() {
		List<PermissionResource> result = resourceService.findAllTree();
		return success(result);
	}

	/**
	 * 根据角色id查詢
	 */
	@RequestMapping(value = "/findByRoleId/{roleId}", method = RequestMethod.GET)
	public ResultDto findByRoleId(@PathVariable String roleId) {
		List<PermissionResource> result = resourceService
				.findResourseByRoleId(roleId);
		return success(result);
	}
	
	/**
	 * 根据角色id查詢(返回树形结构列表)
	 */
	@GetMapping(value = "/findTreeByRoleId/{roleId}")
	public ResultDto findTreeByRoleId(@PathVariable String roleId) {
		List<PermissionResource> result = resourceService
				.findResourseTreeByRoleId(roleId);
		return success(result);
	}
}
