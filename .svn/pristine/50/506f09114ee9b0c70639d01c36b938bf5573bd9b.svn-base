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

@Entity
@Table(name = "MESSAGE")
public class Message implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键id
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "MES_ID", nullable = false, length = 36)
	private String id;
	
	/**
	 * 标题
	 */
	@Column(name = "MES_TITLE", nullable = false, length = 100)
	private String title;
	
	/**
	 * 内容
	 */
	@Column(name = "MES_CONTEXT", nullable = false, length = 1000)
	private String context;
	
	/**
	 * 创建时间
	 */
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd hh-mm-ss")
	@Temporal(TemporalType.DATE)
	@Column(name="MES_CREATETIME")
	private Date createTime;
	/**
	 * 创建用户
	 */
	@Column(name = "MES_USERID", nullable = false, length = 36)
	private String createUserId;
	
	/**
	 * 创建用户机构
	 */
	@Column(name = "MES_ORGID", nullable = false, length = 36)
	private String createOrgId;
	
	/**
	 * 回答
	 */
	@Column(name = "MES_ANSWERCONTEXT", nullable = false, length = 1000)
	private String answerContext;
	
	/**
	 * 回答时间
	 */
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd hh-mm-ss")
	@Temporal(TemporalType.DATE)
	@Column(name="MES_ANSWERTIME")
	private Date answerTime;
	
	/**
	 * 回答用户id
	 */
	@Column(name = "MES_ANSWERUSERID", nullable = false, length = 36)
	private String answerUserId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateOrgId() {
		return createOrgId;
	}

	public void setCreateOrgId(String createOrgId) {
		this.createOrgId = createOrgId;
	}

	public String getAnswerContext() {
		return answerContext;
	}

	public void setAnswerContext(String answerContext) {
		this.answerContext = answerContext;
	}

	public Date getAnswerTime() {
		return answerTime;
	}

	public void setAnswerTime(Date answerTime) {
		this.answerTime = answerTime;
	}

	public String getAnswerUserId() {
		return answerUserId;
	}

	public void setAnswerUserId(String answerUserId) {
		this.answerUserId = answerUserId;
	}
	
	
}
