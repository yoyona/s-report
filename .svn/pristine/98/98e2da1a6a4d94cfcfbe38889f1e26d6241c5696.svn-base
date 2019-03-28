package net.greatsoft.core.domain.model.system;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
/**
 * 行政区划
 * @author fxy
 *
 */
@Entity
@Table(name = "DIC_AREA_CODE")
public class DicArea implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="task_seq")  
	@SequenceGenerator(name="task_seq", sequenceName="seq_task",allocationSize=1,initialValue=1)
	@Column(name = "DIC_ID", nullable = false, length = 10)
	private String id;
	
	@Column(name = "DIC_NAME", nullable = false, length = 100)
	private String name;
	
	@Column(name = "DIC_PID", length = 10)
	private String pId;
	
	@Column(name = "DIC_DES", length = 120)
	private String des;
	
	@Column(name = "DIC_ORDER", length = 20)
	private String order;
	
	@Column(name = "DIC_ISVALID", nullable = false, length = 1)
	private String isValid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
}
