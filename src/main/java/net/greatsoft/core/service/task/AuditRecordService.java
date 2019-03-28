package net.greatsoft.core.service.task;

import net.greatsoft.core.domain.mapper.AuditRecordMapper;
import net.greatsoft.core.domain.model.task.AuditRecord;
import net.greatsoft.core.repository.task.AuditRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class AuditRecordService {

    @Resource
    private AuditRecordMapper auditRecordMapper;

    @Autowired
    private AuditRecordRepository auditRecordRepository;

    public List<Map<String, Object>> findAuditRecordList(Map<String, Object> param) {
       return this.auditRecordMapper.findAuditRecordList(param);
    }

    @Transactional
    public AuditRecord save(AuditRecord auditRecord) {
        return this.auditRecordRepository.saveAndFlush(auditRecord);
    }

    public Integer deleteAuditRecord(Map<String, Object> param) {
        return this.auditRecordMapper.deleteAuditRecordByCondition(param);
    }

    public AuditRecord queryAuditRecord(Map<String, Object> param) {
        return this.auditRecordMapper.queryAuditRecord(param);
    }

    /*public List<Map<String, Object>> findFbList(Map<String, Object> param) {
        return this.auditRecordMapper.queryAuditRecord(param);
    }*/
}
