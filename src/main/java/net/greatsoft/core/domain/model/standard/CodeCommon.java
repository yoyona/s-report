package net.greatsoft.core.domain.model.standard;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CodeCommon implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private CodeCommonPrimaryKey codeCommonPrimaryKey;

	/**
	 * 中文含义
	 */
	private String value;

	/**
	 * 名称备注说明
	 */
	private String valueRemark;

	/**
	 * 代码顺序流水号
	 */
	private Integer serialNumber;

	/**
	 * 拼音码
	 */
	@Column(name = "SPELLING")
	private String spelling;

	/**
	 * 有效标志0无效1有效
	 */
	private Integer isValid;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValueRemark() {
		return valueRemark;
	}

	public void setValueRemark(String valueRemark) {
		this.valueRemark = valueRemark;
	}

	public Integer getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}

	/**
	 * 获取 spelling。
	 * 
	 * @return spelling
	 */
	public String getSpelling() {
		return spelling;
	}

	/**
	 * 设置 spelling。
	 * 
	 * @param spelling
	 *            spelling
	 */
	public void setSpelling(String spelling) {
		this.spelling = spelling;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public CodeCommonPrimaryKey getCodeCommonPrimaryKey() {
		return codeCommonPrimaryKey;
	}

	public void setCodeCommonPrimaryKey(
			CodeCommonPrimaryKey codeCommonPrimaryKey) {
		this.codeCommonPrimaryKey = codeCommonPrimaryKey;
	}

}
