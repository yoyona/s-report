package net.greatsoft.core.domain.model.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * @author AlphGo
 * @date 2016年9月20日 下午2:59:56
 * @Description: 科室
 *
 */
@Entity
public class Department implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 科室主键
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 36)
	private String departmentId;

	/**
	 * 单位主键
	 */
	@Column(length = 36)
	private String organizationId;

	/**
	 * 科室名称
	 */
	@Column(length = 100)
	private String departmentName;

	/**
	 * 科室编号
	 */
	@Column(length = 10)
	private String departmentNo;

	/**
	 * 科室负责人
	 */
	@Column(length = 100)
	private String departmentCharger;

	/**
	 * 科室主任
	 */
	@Column(length = 100)
	private String director;

	/**
	 * 联系方式
	 */
	@Column(length = 30)
	private String contactWay;

	/**
	 * 是否删除
	 */
	private Integer isCancel;

	/**
	 * 父节点
	 */
	private String parentId;

	/**
	 * 是否叶节点(0 否 1 是)
	 */
	@Column(name = "ISLEAFNODE")
	private Integer isLeafNode;

	/**
	 * 单位类型（ 1 卫生局处室 2 区属医院 3 区属社区 4 区属卫生）
	 */
	private String departmentType;

	/**
	 * 单位树级别(1 一级 2 二级 3 三级)
	 */
	@Column(name = "DEPARTMENT_LEVEL")
	private String departmentLevel;

	/**
	 * 顺序号
	 */
	@Column(name = "SERIAL_NUMBER")
	private Integer serialNumber = 0;

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentNo() {
		return departmentNo;
	}

	public void setDepartmentNo(String departmentNo) {
		this.departmentNo = departmentNo;
	}

	public String getDepartmentCharger() {
		return departmentCharger;
	}

	public void setDepartmentCharger(String departmentCharger) {
		this.departmentCharger = departmentCharger;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getContactWay() {
		return contactWay;
	}

	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}

	public Integer getIsCancel() {
		return isCancel;
	}

	public void setIsCancel(Integer isCancel) {
		this.isCancel = isCancel;
	}

	/**
	 * 获取 parentId。
	 * 
	 * @return parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * 设置 parentId。
	 * 
	 * @param parentId
	 *            parentId
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * 获取 isLeafNode。
	 * 
	 * @return isLeafNode
	 */
	public Integer getIsLeafNode() {
		return isLeafNode;
	}

	/**
	 * 设置 isLeafNode。
	 * 
	 * @param isLeafNode
	 *            isLeafNode
	 */
	public void setIsLeafNode(Integer isLeafNode) {
		this.isLeafNode = isLeafNode;
	}

	/**
	 * 获取 departmentType。
	 * 
	 * @return departmentType
	 */
	public String getDepartmentType() {
		return departmentType;
	}

	/**
	 * 设置 departmentType。
	 * 
	 * @param departmentType
	 *            departmentType
	 */
	public void setDepartmentType(String departmentType) {
		this.departmentType = departmentType;
	}

	/**
	 * 获取 departmentLevel。
	 * 
	 * @return departmentLevel
	 */
	public String getDepartmentLevel() {
		return departmentLevel;
	}

	/**
	 * 设置 departmentLevel。
	 * 
	 * @param departmentLevel
	 *            departmentLevel
	 */
	public void setDepartmentLevel(String departmentLevel) {
		this.departmentLevel = departmentLevel;
	}

	/**
	 * 获取 serialNumber。
	 * 
	 * @return serialNumber
	 */
	public Integer getSerialNumber() {
		return serialNumber;
	}

	/**
	 * 设置 serialNumber。
	 * 
	 * @param serialNumber
	 *            serialNumber
	 */
	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}

}
