package net.greatsoft.core.domain.model.task;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Litian
 * @date 2017年2月20日 15:55:59
 * @Description: 模板信息表,为任务指定对应的亿信华辰模板名称
 *
 */
@Entity
@Table(name="TEMPLATE_INFO")
public class TemplateInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 序列Id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="period_seq")  
	@SequenceGenerator(name="period_seq", sequenceName="seq_template",allocationSize=1,initialValue=1) 
	@Column(name="tem_id")
	private Long id;
	/**
	 * 模板唯一ID
	 */
	@Column(name="tem_templateid")
	private String templateId;
	/**
	 * 任务Id
	 */
	@Column(name="tem_taskid")
	private String taskId;
	/**
	 * 机构Id
	 */
	/*@Column(name="tem_orgid")
	private String orgId;*/
	/**
	 * 是否只读（0是1否）
	 */
	/*@Column(name="tem_readonly")
	private String readOnly;*/
	/**
	 * 模板类型（1填报2审核3汇总）
	 */
	@Column(name="tem_type")
	private String templateType;
	/**
	 * 模板对应表名称
	 */
	/*@Column(name="tem_tablename")
	private String templateTableName;*/
	
	@Column(name="tem_proceduresname")
	private String proceduresName;
	
	@Column(name="tem_diagnosisid")
	private String diagnosisId;
	
	@Column(name="tem_analysisid")
	private String analysisId;
	
	@Transient
	private String templateName;
	
	
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	/*public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getReadOnly() {
		return readOnly;
	}
	public void setReadOnly(String readOnly) {
		this.readOnly = readOnly;
	}*/
	public String getTemplateType() {
		return templateType;
	}
	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}
	/*public String getTemplateTableName() {
		return templateTableName;
	}
	public void setTemplateTableName(String templateTableName) {
		this.templateTableName = templateTableName;
	}*/
	public String getProceduresName() {
		return proceduresName;
	}
	public void setProceduresName(String proceduresName) {
		this.proceduresName = proceduresName;
	}
	public String getDiagnosisId() {
		return diagnosisId;
	}
	public void setDiagnosisId(String diagnosisId) {
		this.diagnosisId = diagnosisId;
	}
	public String getAnalysisId() {
		return analysisId;
	}
	public void setAnalysisId(String analysisId) {
		this.analysisId = analysisId;
	}
	
}
