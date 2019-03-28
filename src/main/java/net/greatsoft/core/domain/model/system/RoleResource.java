/*
 * 版权所有 2017 冠新软件。
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
 * 角色权限资源中间表
 */
@Entity
@Table(name = "ROLE_RESOURCES")
public class RoleResource implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 中间表id
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "ID", nullable = false, length = 36)
	private String id;

	/**
	 * 角色id
	 */
	@Column(name = "ROLE_ID", nullable = false, length = 36)
	private String roleId;

	/**
	 * 权限资源id
	 */
	@Column(name = "RESOURCES_ID", nullable = false, length = 36)
	private String resourceId;

	/**
	 * 中间表id
	 */
	public String getId() {
		return id;
	}

	/**
	 * 中间表id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 角色id
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * 角色id
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * 权限资源id
	 */
	public String getResourceId() {
		return resourceId;
	}

	/**
	 * 权限资源id
	 */
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
}
