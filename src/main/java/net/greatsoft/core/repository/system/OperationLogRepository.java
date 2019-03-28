/*
 * 版权所有 2016 冠新软件。
 * 保留所有权利。
 */
package net.greatsoft.core.repository.system;

import net.greatsoft.core.domain.model.system.OperationLog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationLogRepository
		extends JpaRepository<OperationLog, String> {
}
