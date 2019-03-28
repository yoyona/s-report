package net.greatsoft.core.repository.task;

import org.springframework.data.jpa.repository.JpaRepository;

import net.greatsoft.core.domain.model.task.AuditRecord;

/**
 * @author Litian
 * @date 2017年3月2日 上午11:15:28
 * @Description: 审核记录Repository
 * 
 */
public interface AuditRecordRepository extends JpaRepository<AuditRecord, Long>{
	
}
