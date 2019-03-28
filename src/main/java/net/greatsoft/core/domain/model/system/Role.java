/*
 * 版权所有 2017 冠新软件。
 * 保留所有权利。
 */
package net.greatsoft.core.domain.model.system;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


import net.greatsoft.core.util.ProjectUtils;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 角色
 */
@Entity
@Table(name = "ROLE")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "ROLE_ID", nullable = false, length = 36)
	private String id;
	
	/**
	 * 上级角色id
	 */
	@Column(name = "ROLE_PID", nullable = false, length = 36)
	private String pId;

	/**
	 * 角色名称
	 */
	@Column(name = "ROLE_NAME", length = 50)
	private String name;

	/**
	 * 角色标识
	 */
	@Column(name = "ROLE_SLUG", length = 32)
	private String flag;

	/**
	 * 角色类型
	 */
	@Column(name = "ROLE_TYPE", length = 1)
	private Integer type;

	/**
	 * 备注
	 */
	@Column(name = "REMARKS", length = 200)
	private String remark;

	/**
	 * 描述
	 */
	@Column(name = "DESCRIPTION", length = 200)
	private String description;

	/**
	 * 是否启用
	 */
	@Column(name = "IS_VALID", length = 1)
	private Integer isValid;

	/**
	 * 创建人id
	 */
	@Column(name = "CREATE_USERS", length = 36)
	private String createUserId;

	/**
	 * 创建时间
	 */
	@Column(name = "CREATE_TIME")
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date createTime;

	/**
	 * 修改人id
	 */
	@Column(name = "UPDATE_USERS", length = 36)
	private String updateUserId;

	/**
	 * 修改时间
	 */
	@Column(name = "UPDATE_TIME")
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date updateTime;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ROLE_RESOURCES", joinColumns = {
			@JoinColumn(name = "ROLE_ID")}, inverseJoinColumns = {
					@JoinColumn(name = "RESOURCES_ID")})
	private List<PermissionResource> resources;
 
	public Role() {
		this.resources = new ArrayList<PermissionResource>();
	}

	/**
	 * 主键id
	 */
	public String getId() {
		return id;
	}

	/**
	 * 主键id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 角色名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 角色名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 角色标识
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * 角色标识
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * 角色类型
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 角色类型
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 描述
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 是否启用
	 */
	public Integer getIsValid() {
		return isValid;
	}

	/**
	 * 是否启用
	 */
	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	/**
	 * 创建人id
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * 创建人id
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 修改人id
	 */
	public String getUpdateUserId() {
		return updateUserId;
	}

	/**
	 * 修改人id
	 */
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	/**
	 * 修改时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<PermissionResource> getResources() {
		return resources;
	}

	public void setResources(List<PermissionResource> resources) {
		this.resources = resources;
	}
	
	/**
	 * 设置操作用户信息
	 */
	@Transient
	public void modifiedBy(User user) {
		if (this.createUserId == null) {
			this.createUserId = user.getId();
			this.createTime = ProjectUtils.getCurrentSystemDate();
		}
		this.updateUserId = user.getId();
		this.updateTime = ProjectUtils.getCurrentSystemDate();
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}
}
