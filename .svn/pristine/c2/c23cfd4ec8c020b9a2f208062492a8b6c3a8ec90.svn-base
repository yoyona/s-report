package net.greatsoft.core.domain.model.system;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * @author Litian
 * @date 2017年2月14日 上午9:08:56
 * @Description: 机构信息表，存储机构信息及历史信息
 *
 */
@Entity
@Table(name="org_history")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"}) 
public class OrgHistory  implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 序列
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="seq_org")  
	@SequenceGenerator(name="seq_org", sequenceName="seq_org",allocationSize=1,initialValue=1) 
	@Column(name="ORG_ID")
	private Long id;
	/**
	 * 机构唯一标识
	 */
	@Column(name="ORG_UNIQUE_ID")
	private String uniqueId;
	/**
	 * 上级机构(区域划分)
	 */
	@Column(name="ORG_REGIONPID")
	private String regionPid;
	/**
	 * 上级机构(隶属关系)
	 */
	@Column(name="ORG_SUBJECTIONPID")
	private String subjectionPid;
	/**
	 * 机构名称
	 */
	@Column(name="ORG_NAME")
	private String name;
	/**
	 * 创建时间
	 */
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="ORG_CREATE_DATE")
	private Date createDate;
	/**
	 * 批复时间
	 */
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="ORG_REPLY_DATE")
	private Date replyDate;
	/**
	 * 状态（0有效1无效9待批复）
	 */
	@Column(name="ORG_STATUS")
	private String status;
	/**
	 * 机构地址
	 */
	@Column(name="ORG_ADDRESS")
	private String address;
	/**
	 * EMAIL
	 */
	@Column(name="ORG_EMAIL")
	private String email;
	/**
	 * 邮政编码
	 */
	@Column(name="ORG_POSTAL_CODE")
	private String postalCode;
	/**
	 * 联系电话
	 */
	@Column(name="ORG_TELEPHONE")
	private String telephone;
	/**
	 * 行政区划代码
	 */
	@Column(name="ORG_ADMINISTRATIVE_CODE")
	private String administrativeCode;
	/**
	 * 填报类型
	 */
	@Column(name="ORG_FILL_TYPE")
	private String fileType;
	/**
	 * 填报因素
	 */
	@Column(name="ORG_FILL_FACTOR")
	private String fillFactor;
	/**
	 * 经济类型代码
	 */
	@Column(name="ORG_ECONOMIC_TYPE_CODE")
	private String economicTypeCode;
	/**
	 * 预算管理级次
	 */
	@Column(name="ORG_BUDGET_MANAGEMENT_LEVEL")
	private String budgetManagementLevel;
	/**
	 * 机构类别
	 */
	@Column(name="ORG_CATEGORY")
	private String category;
	/**
	 * 单位性质
	 */
	@Column(name="ORG_QUALITY")
	private String quality;
	/**
	 * 机构分类管理代码
	 */
	@Column(name="ORG_CLASS_MANAGE_CODE")
	private String classManageCode;
	/**
	 * 医疗机构级别和等级
	 */
	@Column(name="ORG_HOSPITAL_GRADE")
	private String hospitalGrade;
	/**
	 * 汇总类型
	 */
	@Column(name="ORG_SUMMARY_TYPE")
	private String summaryType;
	/**
	 * 财政预算代码
	 */
	@Column(name="ORG_BUDGET_CODE")
	private String budgetCode;
	/**
	 * 单位负责人
	 */
	@Column(name="ORG_CEO")
	private String ceo;
	/**
	 * 总会计师
	 */
	@Column(name="ORG_CFO")
	private String cfo;
	/**
	 * 财务负责人
	 */
	@Column(name="ORG_FINANCIAL_MANAGER")
	private String financialManager;
	/**
	 * 填表人
	 */
	@Column(name="ORG_PREPARER")
	private String prepaper;
	/**
	 * 卫生机构分类代码
	 */
	@Column(name="ORG_HEALTH_CATEGORY")
	private String healthCategory;
	/**
	 * 试行收支两线
	 */
	@Column(name="ORG_INCOMING_AND_OUTGOINGS")
	private String incomingAndOutgoings;
	/**
	 * 组织机构代码
	 */
	@Column(name="ORG_CODE")
	private String code;
	/**
	 * 备用码
	 */
	@Column(name="ORG_RESERVE_CODE")
	private String reserveCode;
	/**
	 * 是否取消以药补医
	 */
	@Column(name="ORG_IS_CANCEL_MEDICINE")
	private String isCancelMedicine;
	/**
	 * 是否实行乡村（中心站）财务一体化
	 */
	@Column(name="ORG_IS_INTEGRATION")
	private String isInteGration;
	
	/**
	 * 是否删除
	 */
	@Column(name="ORG_IS_CANCEL")
	private Integer isCancel;
	/**
	 * 更新时间
	 */
	@Column(name="ORG_UPDATE_TIME")
	private Date updateTime;
	/**
	 * 更新用户id
	 */
	@Column(name="ORG_UPDATE_USER_ID")
	private String updateUserId;
	
	/**
	 * 是否为卫计委单位(1是0否)
	 */
	@Column(name="ORG_ISWJW")
	private String isWjw;
	/**
	 * 机构级别（按隶属关系）
	 */
	@Column(name="ORG_LEVEL")
	private String  orgLevel;
	/**
	 * 行政机构类型
	 */
	@Column(name="ORG_ADMINISTRATIVE_CATEGORY")
	private String administrativeCategory;
	/**
	 * 执行财务会计制度类型
	 */
	@Column(name="ORG_FINANCIAL_ACCOUNTING")
	private String financialAccounting;
	
	
	
	public String getOrgLevel() {
		return orgLevel;
	}
	public void setOrgLevel(String orgLevel) {
		this.orgLevel = orgLevel;
	}
	public String getAdministrativeCategory() {
		return administrativeCategory;
	}
	public void setAdministrativeCategory(String administrativeCategory) {
		this.administrativeCategory = administrativeCategory;
	}
	public String getFinancialAccounting() {
		return financialAccounting;
	}
	public void setFinancialAccounting(String financialAccounting) {
		this.financialAccounting = financialAccounting;
	}
//	public String getId() {
//		return id;
//	}
//	public void setId(String id) {
//		this.id = id;
//	}
	
	public String getUniqueId() {
		return uniqueId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	
	public String getRegionPid() {
		return regionPid;
	}
	public void setRegionPid(String regionPid) {
		this.regionPid = regionPid;
	}
	public String getSubjectionPid() {
		return subjectionPid;
	}
	public void setSubjectionPid(String subjectionPid) {
		this.subjectionPid = subjectionPid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getReplyDate() {
		return replyDate;
	}
	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getAdministrativeCode() {
		return administrativeCode;
	}
	public void setAdministrativeCode(String administrativeCode) {
		this.administrativeCode = administrativeCode;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getFillFactor() {
		return fillFactor;
	}
	public void setFillFactor(String fillFactor) {
		this.fillFactor = fillFactor;
	}
	public String getEconomicTypeCode() {
		return economicTypeCode;
	}
	public void setEconomicTypeCode(String economicTypeCode) {
		this.economicTypeCode = economicTypeCode;
	}
	public String getBudgetManagementLevel() {
		return budgetManagementLevel;
	}
	public void setBudgetManagementLevel(String budgetManagementLevel) {
		this.budgetManagementLevel = budgetManagementLevel;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public String getClassManageCode() {
		return classManageCode;
	}
	public void setClassManageCode(String classManageCode) {
		this.classManageCode = classManageCode;
	}
	public String getHospitalGrade() {
		return hospitalGrade;
	}
	public void setHospitalGrade(String hospitalGrade) {
		this.hospitalGrade = hospitalGrade;
	}
	public String getSummaryType() {
		return summaryType;
	}
	public void setSummaryType(String summaryType) {
		this.summaryType = summaryType;
	}
	public String getBudgetCode() {
		return budgetCode;
	}
	public void setBudgetCode(String budgetCode) {
		this.budgetCode = budgetCode;
	}
	public String getCeo() {
		return ceo;
	}
	public void setCeo(String ceo) {
		this.ceo = ceo;
	}
	public String getCfo() {
		return cfo;
	}
	public void setCfo(String cfo) {
		this.cfo = cfo;
	}
	public String getFinancialManager() {
		return financialManager;
	}
	public void setFinancialManager(String financialManager) {
		this.financialManager = financialManager;
	}
	public String getPrepaper() {
		return prepaper;
	}
	public void setPrepaper(String prepaper) {
		this.prepaper = prepaper;
	}
	public String getHealthCategory() {
		return healthCategory;
	}
	public void setHealthCategory(String healthCategory) {
		this.healthCategory = healthCategory;
	}
	public String getIncomingAndOutgoings() {
		return incomingAndOutgoings;
	}
	public void setIncomingAndOutgoings(String incomingAndOutgoings) {
		this.incomingAndOutgoings = incomingAndOutgoings;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getReserveCode() {
		return reserveCode;
	}
	public void setReserveCode(String reserveCode) {
		this.reserveCode = reserveCode;
	}
	public String getIsCancelMedicine() {
		return isCancelMedicine;
	}
	public void setIsCancelMedicine(String isCancelMedicine) {
		this.isCancelMedicine = isCancelMedicine;
	}
	public String getIsInteGration() {
		return isInteGration;
	}
	public void setIsInteGration(String isInteGration) {
		this.isInteGration = isInteGration;
	}
	public Integer getIsCancel() {
		return isCancel;
	}
	public void setIsCancel(Integer isCancel) {
		this.isCancel = isCancel;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}
	public String getIsWjw() {
		return isWjw;
	}
	public void setIsWjw(String isWjw) {
		this.isWjw = isWjw;
	}
	
}
