package net.greatsoft.core.domain.model.system;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * Dic联合主键类 (需要重写hashcode和equls)
 * @author fxy
 */
@Embeddable
public class DicKey implements Serializable{
	private static final long serialVersionUID = -3304319243957837925L;
	@Column(name="DIC_ID")
	private String id;
	@Column(name="DIC_TYPE")
	private String type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 重写hashcode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof DicKey){  
			DicKey key = (DicKey)obj ;  
            if(this.id == key.getId()&& this.type.equals(key.getType())){  
                return true ;  
            }  
        }  
        return false ;  
	}
}

