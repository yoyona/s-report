/*
 * 版权所有 2017 冠新软件。
 * 保留所有权利。
 */
package net.greatsoft.core.domain.model.system;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import net.greatsoft.core.util.ProjectUtils;

import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 权限资源表
 */
@Entity
@Table(name = "RESOURCES")
public class PermissionResource
		implements Serializable, Comparable<PermissionResource> {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "RESOURCES_ID", nullable = false, length = 36)
	private String id;

	/**
	 * 权限资源PID
	 */
	@Column(name = "RESOURCES_PID", nullable = false, length = 36)
	private String parentId;

	/**
	 * 权限资源名称
	 */
	@Column(name = "RESOURCES_NAME", nullable = false, length = 50)
	private String name;

	/**
	 * 权限资源标识
	 */
	@Column(name = "IS_VALID", length = 1)
	private String isValid;

	/**
	 * 权限资源URL
	 */
	@Column(name = "RESOURCES_URL", length = 100)
	private String url;

	/**
	 * 权限图片地址
	 */
	@Column(name = "RESOURCES_IMG", length = 100)
	private String image;

	/**
	 * 备注
	 */
	@Column(name = "REMARKS", length = 100)
	private String remark;

	/**
	 * 资源序号
	 */
	@Column(name = "SERIAL_NO", length = 6)
	private Integer serialNo;

	/**
	 * 创建时间
	 */
	@Column(name = "CREATE_TIME")
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date createTime;

	/**
	 * 更新时间
	 */
	@Column(name = "UPDATE_TIME")
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date updateTime;

	@Transient
	private List<PermissionResource> children;

	public PermissionResource() {
		this.children = new ArrayList<>();
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
	 * 权限资源PID
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * 权限资源PID
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * 权限资源名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 权限资源名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 权限资源标识
	 */
	public String getIsValid() {
		return isValid;
	}

	/**
	 * 权限资源标识
	 */
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	/**
	 * 权限资源URL
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 权限资源URL
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 权限图片地址
	 */
	public String getImage() {
		return image;
	}

	/**
	 * 权限图片地址
	 */
	public void setImage(String image) {
		this.image = image;
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
	 * 资源序号
	 */
	public Integer getSerialNo() {
		return serialNo;
	}

	/**
	 * 资源序号
	 */
	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
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
	 * 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<PermissionResource> getChildren() {
		return children;
	}

	public void setChildren(List<PermissionResource> children) {
		this.children = children;
	}

	@Override
	public int compareTo(PermissionResource o) {
		return ObjectUtils.compare(serialNo, o.serialNo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (obj.getClass() != this.getClass()) {
			return false;
		}
		PermissionResource other = (PermissionResource) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	/**
	 * 设置操作信息
	 */
	@Transient
	public void modified() {
		if (this.createTime == null) {
			this.createTime = ProjectUtils.getCurrentSystemDate();
		}
		this.updateTime = ProjectUtils.getCurrentSystemDate();
	}
}
