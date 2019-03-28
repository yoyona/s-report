/*
 * 版权所有 2018 冠新软件。
 * 保留所有权利。
 */
package net.greatsoft.core.domain.model.task;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 数据条件查询
 * @since 2018-12-6
 */
@Entity
@Table(name="QUERY_PARAM")
public class QueryParam implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2") // assigned
	@Column(name="PAR_ID")
	private String id;
	
	@Column(name="PAR_NAME")
	private String parName;
	
	@Column(name="PAR_ORGID")
	private String orgId;
	
	@Column(name="PAR_DES")
	private String des;
	
	@Column(name="PAR_CREATEUSERID")
	private String createUserId;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd mm:hh:ss")
	@Temporal(TemporalType.DATE)
	@Column(name="PAR_CREATETIME")
	private Date createTime;
	
	@Column(name="PAR_ISPUBLIC")
	private String isPublic;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParName() {
		return parName;
	}

	public void setParName(String parName) {
		this.parName = parName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
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

	public String getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
	}
}
