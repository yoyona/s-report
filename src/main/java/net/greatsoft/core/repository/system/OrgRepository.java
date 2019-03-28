package net.greatsoft.core.repository.system;

import java.util.List;

import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.greatsoft.core.domain.model.system.Org;
import net.greatsoft.core.domain.model.system.OrgHistory;
/**
 * @author Litian
 * @date 2017年2月14日 下午1:33:28
 * @Description: 机构信息表Repository
 * 
 */
public interface OrgRepository extends JpaRepository<Org,String>{
	/**
	 * 通过唯一Id来查询机构
	 * @param uniqueId
	 * @return
	 */
	public Org getOrgByUniqueId(String uniqueId);
	
	/**
	 * 通过机构ID 来查询单条机构
	 * @param orgId
	 * @return
	 */
	public Org getOrgById(String orgId);
	
	/**
	 * 通过上级机构查询机构列表
	 * @param subjectionPid
	 * @return
	 */
	public List<Org> getOrgsBySubjectionPid(String subjectionPid);
	
	/**
	 * 通过机构名称来查询机构的信息
	 * @param name
	 * @return
	 */
	public Org getOrgByName(String name);
	/**
	 * 查询该机构上是否有下级机构
	 * @param subjectionPid
	 * @return
	 */
	@Query("select count(1) from Org where subjectionPid = ?")
	public Integer getCountBySubjectionPid(String subjectionPid);
	
	@Query("from Org t where t.administrativeCode like ? and t.isWjw = 1")
	public List<Org> getOrgByAreaId(String areaId);
	
	@Query("from Org t where t.administrativeCode = ? order by t.fileType,t.orgLevel")
	public List<Org> getOrgsByAdministrativeCode(String administrativeCode);
	
	
	@Modifying 
	@Query("update Org t set t.status = ?1 where t.id = ?2")
	public void deleteOrg(String status, Long id);
	
	@Query("from Org where status in ('0','1') and id = ?" )
	public Org getOrgByOrgId(String id);
}
