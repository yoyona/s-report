package net.greatsoft.core.repository.task;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.greatsoft.core.domain.model.task.Attachment;


public interface AttachmentRepository extends JpaRepository<Attachment, Long>{
	
	@Query("from Attachment where indicatorId = ?1 and type = ?2 and orgId = ?3")
	public List<Attachment> findByCondition(String indicatorId,String type,String orgId);
	
	@Query("from Attachment where  orgId = ?1 and type = ?2")
	public List<Attachment> findByCondition(String orgId,String type);
	
	@Query("from Attachment where  orgId = ?1")
	public List<Attachment> findByOrgId(String uniqueId);
	
	@Query("from Attachment where  orgId = ?1 and category in ('0', '1', '2') order by category")
	public List<Attachment> findByOrgId2(String orgId);
	
	@Query("from Attachment where  category in ('0', '1', '2')")
	public List<Attachment> findUploadFileAll();
	
	public Attachment findById(Long id);
	
	@Query("from Attachment where orgId = ?1 and category = ?2")
	public Attachment findByCondition2(String orgId, String category);
}
