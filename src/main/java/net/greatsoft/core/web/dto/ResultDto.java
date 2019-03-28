/*
 * 版权所有 2016 冠新软件。
 * 保留所有权利。
 */
package net.greatsoft.core.web.dto;

import java.io.Serializable;

/**
 * 后台给前台返回的结果
 */
public class ResultDto implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 成功返回码 0
	 */
	public static Integer CODE_SUCCESS = 0;
	/**
	 * 失败返回码 1
	 */
	public static Integer CODE_FAIL = 1;
	
	/**
	 * token无效的返回码
	 */
	public static Integer TOKEN_FAIL = 2;
	
	/**
	 * 成功或失败代码 成功是0 失败是非0
	 */
	private Integer code;

	/**
	 * 后台需要给前台的信息提示，如失败后，应该设置失败的具体原因
	 */
	private String message;

	/**
	 * 成功后返回的结果
	 */
	private Object result;

	/**
	 * @param code
	 *            成功或失败代码 成功是0 失败是非0
	 * @param message
	 *            后台需要给前台的信息提示，如失败后，应该设置失败的具体原因
	 * @param result
	 *            成功后返回的结果
	 */
	public ResultDto(Integer code, String message, Object result) {
		this.code = code;
		this.message = message;
		this.result = result;
	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public Object getResult() {
		return result;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setResult(Object result) {
		this.result = result;
	}
}
