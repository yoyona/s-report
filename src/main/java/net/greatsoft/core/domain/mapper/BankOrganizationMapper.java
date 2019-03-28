/*
 * 版权所有 2017 北航冠新。
 * 保留所有权利。
 */
package net.greatsoft.core.domain.mapper;

import java.util.List;
import java.util.Map;

import net.greatsoft.core.domain.model.system.Org;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface BankOrganizationMapper {
	List<Org> selectBankOrganization(Map<String, Object> param);

	List<Org> findAllBankOrg();
}
