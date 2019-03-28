/*
 * 版权所有 2018 冠新软件。
 * 保留所有权利。
 */
package net.greatsoft.core.domain.model.system;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * CUSTOM_SUMMARY_ORG_REL对象
 * @since 2018-10-19
 */
@Entity
@Table(name = "CUSTOM_SUMMARY_ORG_REL")
public class CustomSummaryOrgRel implements Serializable{
	/**
	 * @since 2018-10-19
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "REL_ID", nullable = false, length = 36)
	private String id;
	
	@Column(name="REL_CUS_ID")
	private String cusId;
	
	@Column(name="REL_ORG_ID")
	private String orgId;
	
	public String getCusId() {
		return cusId;
	}
	public void setCusId(String cusId) {
		this.cusId = cusId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
