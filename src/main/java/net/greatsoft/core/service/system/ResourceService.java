/*
 * 版权所有 2016 北航冠新。
 * 保留所有权利。
 */
package net.greatsoft.core.service.system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import net.greatsoft.application.common.domain.util.ObjectClone;
import net.greatsoft.core.domain.model.system.PermissionResource;
import net.greatsoft.core.domain.mapper.ResourceMapper;
import net.greatsoft.core.repository.system.ResourceRepository;
import net.greatsoft.core.util.ProjectConstants;

/**
 * 权限资源相关
 */
@Service
public class ResourceService {
	private static final Logger LOG = LoggerFactory
			.getLogger(ResourceService.class);

	@Resource
	private ResourceMapper resourceMapper;

	@Resource
	private ResourceRepository resourceRepository;

	/**
	 * 新增权限
	 */
	public PermissionResource addResource(PermissionResource resource) {
		resource.setIsValid("1");
		//resource.modified();
		return resourceRepository.save(resource);
	}

	/**
	 * 更新权限
	 */
	public PermissionResource updateResource(PermissionResource resource) {
		PermissionResource oldResource = resourceRepository
				.findOne(resource.getId());
		ObjectClone.copy(oldResource, resource);
		//resource.modified();
		resourceRepository.save(oldResource);
		return oldResource;
	}

	/**
	 * 删除权限
	 */
	public void deleteResource(String resouceId) {
		PermissionResource resource = resourceRepository.findOne(resouceId);
		resourceRepository.delete(resource);
	}

	/**
	 * 查询权限信息
	 */
	public List<PermissionResource> findAllResource() {
		return resourceRepository.findAll();
	}

	/**
	 * 查询所有（树形）
	 */
	public List<PermissionResource> findAllTree() {
		List<PermissionResource> treeList = new ArrayList<>();

		List<PermissionResource> resourceList = resourceRepository.findAll();
		/*Map<String, PermissionResource> resourceMap = new HashMap<>();
		for (PermissionResource resource : resourceList) {
			resourceMap.put(resource.getId(), resource);
		}
		for (PermissionResource resource : resourceList) {
			String parentId = resource.getParentId();
			if (ProjectConstants.RESOURCE_TOP_ID.equals(parentId)) {
				treeList.add(resource);
			} else {
				PermissionResource parentResource = resourceMap.get(parentId);
				if (parentResource == null) {
					LOG.warn("权限 {} 的上级权限 {} 未找到", resource.getId(), parentId);
					continue;
				}
				parentResource.getChildren().add(resource);
			}
		}*/

		return resourceList;
	}

	public List<PermissionResource> findResourseByRoleId(String roleId) {
		return resourceMapper.findResoursesByRoleId(roleId);
	}
	/**
	 * 根据角色id查询权限列表
	 * @param roleId
	 * @return
	 */
	public List<PermissionResource> findResourseTreeByRoleId(String roleId){
		List<PermissionResource> treeList = new ArrayList<>();
		List<PermissionResource> resourceList = resourceMapper.findResoursesByRoleId(roleId);
		Map<String, PermissionResource> resourceMap = new HashMap<>();
		for (PermissionResource resource : resourceList) {
			resourceMap.put(resource.getId(), resource);
		}
		for (PermissionResource resource : resourceList) {
			String parentId = resource.getParentId();
			if (ProjectConstants.RESOURCE_TOP_ID.equals(parentId)) {
				treeList.add(resource);
			} else {
				PermissionResource parentResource = resourceMap.get(parentId);
				if (parentResource == null) {
					LOG.warn("权限 {} 的上级权限 {} 未找到", resource.getId(), parentId);
					continue;
				}
				parentResource.getChildren().add(resource);
			}
		}
		return treeList;
	}
	
	/**
	 * 根据用户的 ID 查询该用户的权限资源
	 * 
	 * @param userId
	 *            用户的 ID
	 */
	/*public PermissionResource findUserTree(String userId) {
		// 查询该用户的所有资源
		List<PermissionResource> resourceList = resourceMapper
				.findResourcesByUsersId(userId);
		Map<String, PermissionResource> resourceMap = new HashMap<>();
		for (PermissionResource resource : resourceList) {
			resourceMap.put(resource.getId(), resource);
		}

		List<PermissionResource> treeList = new ArrayList<>();
		for (PermissionResource resource : resourceList) {
			String parentId = resource.getParentId();
			if (ProjectConstants.RESOURCE_TOP_ID.equals(parentId)) {
				treeList.add(resource);
			} else {
				PermissionResource parentResource = resourceMap.get(parentId);
				if (parentResource == null) {
					LOG.warn("权限 {} 的上级权限 {} 未找到", resource.getId(), parentId);
					continue;
				}
				parentResource.getChildren().add(resource);
			}
		}

		PermissionResource topMenu = new PermissionResource();
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
	}*/
}
