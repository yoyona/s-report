package net.greatsoft.core.web.task;

import net.greatsoft.core.domain.model.task.AuditRecord;
import net.greatsoft.core.service.task.AuditRecordService;
import net.greatsoft.core.util.constant.ExecuteConstant;
import net.greatsoft.core.web.dto.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 审核记录控制器
 */
@RestController
@RequestMapping(value = "/audit")
public class AuditRecordController {

    @Autowired
    private AuditRecordService auditRecordService;

    @PostMapping(value = "/dy/save")
    public ResultDto saveDynamic(@RequestBody Map<String, Object> param) {
        // 查询是否有这条审核记录确认没有才新增
        /*Map<String, Object> param = new HashMap<String, Object>();
        param.put("taskId", taskId);
        param.put("periodId", periodId);
        param.put("uniqueId", orgId);*/
        String orgId = String.valueOf(param.get("orgId"));
        String perId = String.valueOf(param.get("perId"));
        String taskId = String.valueOf(param.get("taskId"));
        AuditRecord auditRecord = this.auditRecordService.queryAuditRecord(param);
        if (auditRecord != null) {
            return new ResultDto(ResultDto.CODE_FAIL, "该机构审核记录已经存在", null);
        }
        AuditRecord audit = new AuditRecord();
        audit.setOrgId(orgId);
        audit.setPerId(String.valueOf(perId));
        audit.setStatus(ExecuteConstant.CHECK_STATUS_INIT);
        audit.setSummaryStatus(ExecuteConstant.CHECK_SUMMARY_STATUS_INIT);
        audit.setChangeTime("0");
        audit.setTaskId(taskId);
        AuditRecord save = this.auditRecordService.save(audit);
        param.put("audit", save);
        return new ResultDto(ResultDto.CODE_SUCCESS, "保存成功", param);
    }

    @PostMapping(value = "/dy/list")
    public ResultDto findAuditRecordList(@RequestBody Map<String, Object> param) {
        List<Map<String, Object>> auditRecordList = this.auditRecordService.findAuditRecordList(param);
        param.put("auditRecordList", auditRecordList);
        return new ResultDto(ResultDto.CODE_SUCCESS, "", param);
    }

}
