package net.greatsoft.core.repository.system;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.greatsoft.core.domain.model.system.OrgHistory;
/**
 * 机构批复历史信息表
 * @author Aptx4869
 * @DATE 2017-4-6
 */
public interface OrgHistoryRepository extends JpaRepository<OrgHistory,Long>{
	/**
	 * 通过机构id查询历史机构生效数据
	 * @param id
	 * @return
	 */
//	@Query("from org_history where org_status in ('0','1') and org_id = ?" )
	@Query("from OrgHistory where status in ('0','1') and id = ?" )
	public OrgHistory getOrgHistoryByOrgId(Long id);
	/**
	 * 通过机构唯一id查询历史机构生效数据
	 * @param uniqueId
	 * @return
	 */
//	@Query("from org_history where org_status in ('0','1') and org_unique_id = ?")
	@Query("from OrgHistory where status in ('0','1') and uniqueId = ?")
	public OrgHistory getOrgHistoryByUniqueId(String uniqueId);
	
	
	@Modifying
	@Query("update OrgHistory t set t.status = ?1 where t.id = ?2")
	public void deleteOrg(String status, Long id);
	
	
	
	
}
