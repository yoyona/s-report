/*
 * 版权所有 2018 冠新软件。
 * 保留所有权利。
 */
package net.greatsoft.core.repository.system;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.greatsoft.core.domain.model.system.AgentOrg;

/**
 * TODO 填写描述信息
 * @since 2018-12-13
 */
@Repository
public interface AgentOrgRepository extends JpaRepository<AgentOrg,String>{
	
}
