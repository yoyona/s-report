package net.greatsoft.core.repository.system;

import net.greatsoft.core.domain.model.system.Org;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


/**
 * 
 */
@Repository
public interface BankOrganizationRepository
		extends JpaRepository<Org, String> {
	@Query("from Org t where t.code = ?1 and t.status = 1 and t.isCancel = 0")
	Org findBankOrganizationByCode(String code);
}
