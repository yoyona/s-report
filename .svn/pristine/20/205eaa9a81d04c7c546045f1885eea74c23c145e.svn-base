/*
 * 版权所有 2016 北航冠新。
 * 保留所有权利。
 */
package net.greatsoft.core.service.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.greatsoft.application.common.domain.util.ObjectClone;
import net.greatsoft.core.domain.mapper.AdminOrganizationMapper;
import net.greatsoft.core.domain.model.system.AdminOrganization;
import net.greatsoft.core.domain.model.system.User;
import net.greatsoft.core.repository.system.AdminOrganizationRepository;
import net.greatsoft.core.util.ProjectConstants;
import net.greatsoft.core.web.dto.AdminOrganizationDto;

/**
 * @author limiao
 * @description 注册管理中心Service
 * @date 207-01-03
 */
@Service
public class AdminOrganizationService {
	@Resource
	private AdminOrganizationRepository adminOrgRepository;

	@Resource
	private AdminOrganizationMapper adminOrgMapper;

	public void saveAdminOrg(AdminOrganization adminOrg, User users) {
		adminOrg.setCreateUserId(users.getId());
		adminOrg.setIsValid(ProjectConstants.IS_VALID_YES);
		adminOrg.setIsCancel(ProjectConstants.IS_CANCEL_NO);
		adminOrgRepository.save(adminOrg);
	}

	public AdminOrganization updateAdminOrg(AdminOrganization adminOrg,
			User user) {
		AdminOrganization adminOrg1 = adminOrgRepository
				.findOne(adminOrg.getId());
		ObjectClone.copy(adminOrg1, adminOrg);
		Date now = new Date();
		adminOrg1.setUpdateTime(now);
		adminOrg1.setUpdateUserId(user.getId());
		return adminOrgRepository.save(adminOrg1);
	}

	public void deleteAdminOrg(String adminOrgId) {
		AdminOrganization adminOrg1 = adminOrgRepository.findOne(adminOrgId);
		adminOrg1.setIsValid(ProjectConstants.IS_VALID_NO);
		adminOrg1.setIsCancel(ProjectConstants.IS_CANCEL_YES);
		adminOrgRepository.save(adminOrg1);
	}

	public AdminOrganization findById(String adminOrganizationId) {
		return adminOrgRepository.findOne(adminOrganizationId);
	}

	public List<AdminOrganizationDto> showTree() {
		List<AdminOrganizationDto> treeList = new ArrayList<>();

		// 查出全部节点
		List<AdminOrganizationDto> allAdminOrg = adminOrgMapper
				.selectAdminOrg();
		if (allAdminOrg.isEmpty()) {
			return treeList;
		}

		// 查出所有顶级节点
		List<AdminOrganizationDto> topAdminOrg = findTopList(allAdminOrg);
		for (AdminOrganizationDto admins : topAdminOrg) {
			treeList.add(admins);
			for (AdminOrganizationDto ad : allAdminOrg) {
				for (AdminOrganizationDto a : allAdminOrg) {
					if (a.getParentId().equals(ad.getId())) {
						if (ad.getChildren() == null) {
							List<AdminOrganizationDto> list = new ArrayList<>();
							list.add(a);
							ad.setChildren(list);
						} else {
							ad.getChildren().add(a);
						}
					}
				}
			}
		}

		return treeList;
	}

	// 查出所有顶级节点
	public List<AdminOrganizationDto> findTopList(
			List<AdminOrganizationDto> adList) {
		List<AdminOrganizationDto> topAdminOrg = new ArrayList<>();
		for (AdminOrganizationDto ad : adList) {
			// 若为顶级节点
			if ("0".equals(ad.getParentId())) {
				topAdminOrg.add(ad);
			}
		}
		return topAdminOrg;
	}

	// 根据父id查询子节点
	public List<AdminOrganizationDto> findChildList(String pId,
			List<AdminOrganizationDto> allAdminOrg) {
		List<AdminOrganizationDto> childList = new ArrayList<>();
		for (AdminOrganizationDto ad : allAdminOrg) {
			if (pId.equals(ad.getParentId())) {
				childList.add(ad);
			}
		}
		return childList;
	}

	/**
	 * 根据注册机构等级查询注册机构
	 */
	public List<AdminOrganization> finAdminOrgByLevel(Integer level) {
		return adminOrgRepository.findAdminOrganizationByLevel(level);
	}
}
