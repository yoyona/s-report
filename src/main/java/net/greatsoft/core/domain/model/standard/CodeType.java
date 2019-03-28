
package net.greatsoft.core.domain.model.standard;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CodeType implements Serializable {
	private static final long serialVersionUID = 1L;

	private String type;

	private String remark;

	@Id
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
