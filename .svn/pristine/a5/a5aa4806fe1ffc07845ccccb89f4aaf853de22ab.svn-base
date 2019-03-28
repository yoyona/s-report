package net.greatsoft.core.service.system;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.greatsoft.application.common.domain.util.ObjectClone;
import net.greatsoft.core.domain.mapper.BankOrganizationMapper;
import net.greatsoft.core.domain.model.system.Org;
import net.greatsoft.core.domain.model.system.User;
import net.greatsoft.core.repository.system.BankOrganizationRepository;
import net.greatsoft.core.util.ProjectConstants;

/**
 * 
 */
@Service
public class BankOrganizationService {
	@Resource
	private BankOrganizationRepository bankOrganizationRepository;

	@Resource
	private BankOrganizationMapper bankOrganizationMapper;

	public void saveBankOrganization(Org bankOrganization,
			User user) {
		bankOrganization.setIsCancel(ProjectConstants.IS_CANCEL_NO);
		bankOrganization.setStatus(ProjectConstants.IS_VALID_YES.toString());
		bankOrganizationRepository.save(bankOrganization);
	}

	/*public Org updateBankOrganization(
			Org bankOrganization, User currentUser) {
		Org bankOrganization1 = bankOrganizationRepository
				.findOne(bankOrganization.getId());
		ObjectClone.copy(bankOrganization1, bankOrganization);
		bankOrganization1.setUpdateTime(new Date());
		bankOrganization1.setUpdateUserId(currentUser.getId());
		return bankOrganizationRepository.save(bankOrganization1);
	}

	public void deleteBankOrganization(String id) {
		Org bankOrganization1 = bankOrganizationRepository
				.findOne(id);
		bankOrganization1.setIsCancel(ProjectConstants.IS_CANCEL_YES);
		bankOrganization1.setStatus(ProjectConstants.IS_VALID_NO.toString());
		bankOrganizationRepository.save(bankOrganization1);
	}*/

	public List<Org> findBankOrganization(
			Map<String, Object> param) {
		return bankOrganizationMapper.selectBankOrganization(param);
	}

	public List<Org> findAllBankOrg() {
		return bankOrganizationMapper.findAllBankOrg();
	}
}
