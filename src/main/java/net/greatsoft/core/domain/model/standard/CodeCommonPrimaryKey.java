package net.greatsoft.core.domain.model.standard;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CodeCommonPrimaryKey implements Serializable {

	private static final long serialVersionUID = 1L;

	private String type;

	private String code;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
