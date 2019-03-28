/*
 * 版权所有 2017 冠新软件。
 * 保留所有权利。
 */
package net.greatsoft.core.domain.model.system;

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
 * 操作日志表
 */
@Entity
@Table(name = "OPERATION_LOG")
public class OperationLog implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 操作日志 ID
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "OPERATION_LOG_ID", nullable = false, length = 36)
	private String id;

	/**
	 * IP 地址
	 */
	@Column(name = "IP_ADDRESS", nullable = false, length = 30)
	private String ipAddress;

	/**
	 * 账户 ID
	 */
	@Column(name = "USER_ID", nullable = false, length = 36)
	private String userId;

	/**
	 * 账户名称
	 */
	@Column(name = "USER_NAME", nullable = false, length = 50)
	private String userName;

	/**
	 * 操作类型
	 */
	@Column(name = "OPERATION_TYPE", nullable = false, length = 20)
	private String operationType;

	/**
	 * 业务名称
	 */
	@Column(name = "BUSINESS_NAME", nullable = false, length = 50)
	private String businessName;

	/**
	 * 业务描述
	 */
	@Column(name = "BUSINESS_DESC", nullable = false, length = 200)
	private String businessDescription;

	/**
	 * 操作时间
	 */
	@Column(name = "OPERATE_TIME", nullable = false)
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date operateTime;

	/**
	 * 操作日志 ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * 操作日志 ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * IP 地址
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * IP 地址
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * 账户 ID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 账户 ID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 账户名称
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 账户名称
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 操作类型
	 */
	public String getOperationType() {
		return operationType;
	}

	/**
	 * 操作类型
	 */
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	/**
	 * 业务名称
	 */
	public String getBusinessName() {
		return businessName;
	}

	/**
	 * 业务名称
	 */
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	/**
	 * 业务描述
	 */
	public String getBusinessDescription() {
		return businessDescription;
	}

	/**
	 * 业务描述
	 */
	public void setBusinessDescription(String businessDescription) {
		this.businessDescription = businessDescription;
	}

	/**
	 * 操作时间
	 */
	public Date getOperateTime() {
		return operateTime;
	}

	/**
	 * 操作时间
	 */
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
}
