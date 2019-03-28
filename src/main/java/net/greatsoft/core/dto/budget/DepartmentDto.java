/*
 * 版权所有 2016 北航冠新。
 * 保留所有权利。
 */
package net.greatsoft.core.dto.budget;

import java.util.List;

/**
 * 
 */
public class DepartmentDto implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 单位ID
	 */
	private String departmentId;

	/**
	 * 单位主键
	 */
	private String organizationId;

	/**
	 * 科室名称
	 */
	private String departmentName;

	/**
	 * 科室编号
	 */
	private String departmentNo;

	/**
	 * 科室负责人
	 */
	private String departmentCharger;

	/**
	 * 科室主任
	 */
	private String director;

	/**
	 * 联系方式
	 */
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
	private Integer isLeafNode;

	/**
	 * 单位类型（ 1 卫生局处室 2 区属医院 3 区属社区 4 区属卫生）
	 */
	private String departmentType;

	/**
	 * 单位树级别(1 一级 2 二级 3 三级)
	 */
	private String departmentLevel;

	/**
	 * 顺序号
	 */
	private Integer serialNumber;

	/**
	 * 下一级
	 */
	private List<DepartmentDto> children;

	/**
	 * 获取 departmentId。
	 * 
	 * @return departmentId
	 */
	public String getDepartmentId() {
		return departmentId;
	}

	/**
	 * 设置 departmentId。
	 * 
	 * @param departmentId
	 *            departmentId
	 */
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * 获取 organizationId。
	 * 
	 * @return organizationId
	 */
	public String getOrganizationId() {
		return organizationId;
	}

	/**
	 * 设置 organizationId。
	 * 
	 * @param organizationId
	 *            organizationId
	 */
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	/**
	 * 获取 departmentName。
	 * 
	 * @return departmentName
	 */
	public String getDepartmentName() {
		return departmentName;
	}

	/**
	 * 设置 departmentName。
	 * 
	 * @param departmentName
	 *            departmentName
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	/**
	 * 获取 departmentNo。
	 * 
	 * @return departmentNo
	 */
	public String getDepartmentNo() {
		return departmentNo;
	}

	/**
	 * 设置 departmentNo。
	 * 
	 * @param departmentNo
	 *            departmentNo
	 */
	public void setDepartmentNo(String departmentNo) {
		this.departmentNo = departmentNo;
	}

	/**
	 * 获取 departmentCharger。
	 * 
	 * @return departmentCharger
	 */
	public String getDepartmentCharger() {
		return departmentCharger;
	}

	/**
	 * 设置 departmentCharger。
	 * 
	 * @param departmentCharger
	 *            departmentCharger
	 */
	public void setDepartmentCharger(String departmentCharger) {
		this.departmentCharger = departmentCharger;
	}

	/**
	 * 获取 director。
	 * 
	 * @return director
	 */
	public String getDirector() {
		return director;
	}

	/**
	 * 设置 director。
	 * 
	 * @param director
	 *            director
	 */
	public void setDirector(String director) {
		this.director = director;
	}

	/**
	 * 获取 contactWay。
	 * 
	 * @return contactWay
	 */
	public String getContactWay() {
		return contactWay;
	}

	/**
	 * 设置 contactWay。
	 * 
	 * @param contactWay
	 *            contactWay
	 */
	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}

	/**
	 * 获取 isCancel。
	 * 
	 * @return isCancel
	 */
	public Integer getIsCancel() {
		return isCancel;
	}

	/**
	 * 设置 isCancel。
	 * 
	 * @param isCancel
	 *            isCancel
	 */
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

	/**
	 * 获取 children。
	 * 
	 * @return children
	 */
	public List<DepartmentDto> getChildren() {
		return children;
	}

	/**
	 * 设置 children。
	 * 
	 * @param children
	 *            children
	 */
	public void setChildren(List<DepartmentDto> children) {
		this.children = children;
	}

}
