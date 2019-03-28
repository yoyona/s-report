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
 * 注册管理单位
 */
@Entity
@Table(name = "ORG")
public class AdminOrganization implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id<br>
	 * PK
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "ADMIN_ORG_ID", nullable = false, length = 36)
	private String id;

	/**
	 * 行政区划id
	 */
	@Column(name = "AREA_ID", length = 36)
	private String areaId;

	/**
	 * 管理机构pid
	 */
	@Column(name = "ADMIN_ORG_PID", length = 36)
	private String parentId;

	/**
	 * 管理机构编号
	 */
	@Column(name = "ADMIN_ORG_CODE", length = 36)
	private String code;

	/**
	 * 管理机构名称
	 */
	@Column(name = "ADMIN_ORG_NAME", length = 50)
	private String name;

	/**
	 * 管理机构级别<br>
	 * 0：国家，1：省级，2：省直属，3：市级，4：市直属，5：县级，6：县直属
	 */
	@Column(name = "ADMIN_ORG_LV", length = 1)
	private Integer level;

	/**
	 * 管理机构类别<br>
	 * 1：主管机构 2：基层机构
	 */
	@Column(name = "ADMINT_ORG_TYPE", length = 1)
	private String type;

	/**
	 * 管理机构简称
	 */
	@Column(name = "ADMIN_ORG_SHORT", length = 50)
	private String shortName;

	/**
	 * 管理机构简介
	 */
	@Column(name = "ADMIN_ORG_INTRODUCT", length = 3000)
	private String introduction;

	/**
	 * 管理机构传真号
	 */
	@Column(name = "ADMIN_ORG_FAX", length = 20)
	private String fax;

	/**
	 * 管理机构邮箱
	 */
	@Column(name = "ADMIN_ORG_EMAIL", length = 50)
	private String email;

	/**
	 * 管理机构网址
	 */
	@Column(name = "ADMIN_ORG_URL", length = 200)
	private String url;

	/**
	 * 管理机构邮政编码
	 */
	@Column(name = "ADMIN_ORG_POSCODE", length = 6)
	private String postcode;

	/**
	 * 管理单位负责人
	 */
	@Column(name = "ADMIN_ORG_CHARGER", length = 36)
	private String charger;

	/**
	 * 管理单位电话
	 */
	@Column(name = "ADMIN_ORG_PHONE", length = 20)
	private String phone;

	/**
	 * 管理单位联系人
	 */
	@Column(name = "ADMIN_ORG_CONCAT", length = 36)
	private String contact;

	/**
	 * 管理单位联系电话
	 */
	@Column(name = "ADMIN_ORG_CONCAT_PHONE", length = 20)
	private String contactPhone;

	/**
	 * 管理单位地址
	 */
	@Column(name = "ADMIN_ORG_ADDRESS", length = 200)
	private String address;

	/**
	 * 是否上报<br>
	 * 0：否 1：是
	 */
	@Column(name = "IS_REPORT", length = 1)
	private String isReport;

	/**
	 * 信用代码
	 */
	@Column(name = "ADMIN_ORG_CREDIT_CODE", length = 36)
	private String creditCode;

	/**
	 * 备注
	 */
	@Column(name = "REMARKS", length = 100)
	private String remark;

	/**
	 * 描述
	 */
	@Column(name = "DESCRIPTION", length = 1000)
	private String description;

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

	/**
	 * 是否可用<br>
	 * 0：否 1：是
	 */
	@Column(name = "IS_VALID", length = 1)
	private Integer isValid;

	/**
	 * 是否删除<br>
	 * 0：否 1：是
	 */
	@Column(name = "IS_CANCEL", length = 1)
	private Integer isCancel;

	/**
	 * 主键id<br>
	 * PK
	 */
	public String getId() {
		return id;
	}

	/**
	 * 主键id<br>
	 * PK
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 行政区划id
	 */
	public String getAreaId() {
		return areaId;
	}

	/**
	 * 行政区划id
	 */
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	/**
	 * 管理机构pid
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * 管理机构pid
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * 管理机构编号
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 管理机构编号
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 管理机构名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 管理机构名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 管理机构级别<br>
	 * 0：国家，1：省级，2：省直属，3：市级，4：市直属，5：县级，6：县直属
	 */
	public Integer getLevel() {
		return level;
	}

	/**
	 * 管理机构级别<br>
	 * 0：国家，1：省级，2：省直属，3：市级，4：市直属，5：县级，6：县直属
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
	 * 管理机构类别<br>
	 * 1：主管机构 2：基层机构
	 */
	public String getType() {
		return type;
	}

	/**
	 * 管理机构类别<br>
	 * 1：主管机构 2：基层机构
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 管理机构简称
	 */
	public String getShortName() {
		return shortName;
	}

	/**
	 * 管理机构简称
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	/**
	 * 管理机构简介
	 */
	public String getIntroduction() {
		return introduction;
	}

	/**
	 * 管理机构简介
	 */
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	/**
	 * 管理机构传真号
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * 管理机构传真号
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * 管理机构邮箱
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 管理机构邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 管理机构网址
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 管理机构网址
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 管理机构邮政编码
	 */
	public String getPostcode() {
		return postcode;
	}

	/**
	 * 管理机构邮政编码
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	/**
	 * 管理单位负责人
	 */
	public String getCharger() {
		return charger;
	}

	/**
	 * 管理单位负责人
	 */
	public void setCharger(String charger) {
		this.charger = charger;
	}

	/**
	 * 管理单位电话
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 管理单位电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 管理单位联系人
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * 管理单位联系人
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

	/**
	 * 管理单位联系电话
	 */
	public String getContactPhone() {
		return contactPhone;
	}

	/**
	 * 管理单位联系电话
	 */
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	/**
	 * 管理单位地址
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 管理单位地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 是否上报<br>
	 * 0：否 1：是
	 */
	public String getIsReport() {
		return isReport;
	}

	/**
	 * 是否上报<br>
	 * 0：否 1：是
	 */
	public void setIsReport(String isReport) {
		this.isReport = isReport;
	}

	/**
	 * 信用代码
	 */
	public String getCreditCode() {
		return creditCode;
	}

	/**
	 * 信用代码
	 */
	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
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

	/**
	 * 是否可用<br>
	 * 0：否 1：是
	 */
	public Integer getIsValid() {
		return isValid;
	}

	/**
	 * 是否可用<br>
	 * 0：否 1：是
	 */
	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	/**
	 * 是否删除<br>
	 * 0：否 1：是
	 */
	public Integer getIsCancel() {
		return isCancel;
	}

	/**
	 * 是否删除<br>
	 * 0：否 1：是
	 */
	public void setIsCancel(Integer isCancel) {
		this.isCancel = isCancel;
	}
}
