package net.greatsoft.core.domain.model.task;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * 附件模型类
 * @author Aptx4869
 * @Date 2017-09-13 This is new day for a new iPhone
 */
@Entity
@Table(name="ATTACHMENT")
public class Attachment implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_ATTACHMENT")  
	@SequenceGenerator(name="SEQ_ATTACHMENT", sequenceName="SEQ_ATTACHMENT",allocationSize=1,initialValue=1) 
	@Column(name="ATT_ID")
	private Long id;
	
	@Column(name="ATT_ORGID")
	private String orgId;
	
	@Column(name="ATT_CREATE_USERID")
	private String createUserId;
	
	@Column(name="ATT_CREATE_TIME")
	private Date createTime;
	
	@Column(name="ATT_FILE_URL")
	private String fileUrl;
	
	@Column(name="ATT_FILE_NAME")
	private String fileName;
	
	@Column(name="ATT_STATUS")
	private String status;
	
	@Column(name="ATT_INDICATOR_ID")
	private String indicatorId;
	
	@Column(name="ATT_YEAR")
	private String year;
	
	@Column(name="ATT_CATEGORY")
	private String category;

	@Column(name="ATT_Type")
	private String type;

	@Column(name="ATT_NOT_ID")
	private String notId;
	

	public String getNotId() {
		return notId;
	}

	@Transient
	private String createDate;

	@Transient
	private String orgName;
	
	
	
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public void setNotId(String notId) {
		this.notId = notId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIndicatorId() {
		return indicatorId;
	}

	public void setIndicatorId(String indicatorId) {
		this.indicatorId = indicatorId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
