/*
 * 版权所有 2018 冠新软件。
 * 保留所有权利。
 */
package net.greatsoft.core.domain.model.task;

/**
 * 查询条件与指标对应关系表
 * @since 2018-12-6
 */
public class ParamInds {
	String parId;
	String indId;
	Integer order;
	
	public String getParId() {
		return parId;
	}
	public void setParId(String parId) {
		this.parId = parId;
	}
	public String getIndId() {
		return indId;
	}
	public void setIndId(String indId) {
		this.indId = indId;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
}
