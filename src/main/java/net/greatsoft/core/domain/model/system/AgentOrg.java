/*
 * 版权所有 2018 冠新软件。
 * 保留所有权利。
 */
package net.greatsoft.core.domain.model.system;

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
 * 无机构代理人信息
 * @since 2018-12-13
 */
@Entity
@Table(name = "AGENT_ORG")
public class AgentOrg {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "AO_ID", nullable = false, length = 36)
	private String id;
	
	@Column(name = "AO_NAME", nullable = false, length = 50)
	private String name;
	
	@Column(name = "AO_STATUS", nullable = false, length = 50)
	private String status;
	
	@Column(name = "AO_CREATETIME")
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private Date createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
