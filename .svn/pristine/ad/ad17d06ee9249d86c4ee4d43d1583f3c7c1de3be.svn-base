/*
 * $Id: SystemSettings.java 1946 2016-09-30 10:06:49Z huliangyin $
 * 版权所有 2016 冠新软件。
 * 保留所有权利。
 */

package net.greatsoft.core.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author HS
 * @date 2016年6月17日 下午1:07:40
 * @Description: 系统参数设置类
 * 
 */
@ConfigurationProperties(prefix = "system")
@PropertySource(value = "classpath:/system.properties")
@Component
public class SystemSettings {
	
	/**
	 * 开放平台根据token获取用户的地址
	 */
	private String openplatformTokenUrl;
	
	/**
	 * 将base64编码的字符串变为图片的接口地址
	 */
	private String facePicUrl;
	
	private String openplatformSessionUrl;
	
	private String indexUrl;
	
	private String redirectUrl;
	
	private String usersUrl;
	
	private String getToken;

	public String getOpenplatformTokenUrl(String code) {
		openplatformTokenUrl += "&code="+code;
		System.out.println(openplatformTokenUrl+"========================================");
		return openplatformTokenUrl;
	}

	public void setOpenplatformTokenUrl(String openplatformTokenUrl) {
		this.openplatformTokenUrl = openplatformTokenUrl;
	}

	public String getFacePicUrl() {
		return facePicUrl;
	}

	public void setFacePicUrl(String facePicUrl) {
		this.facePicUrl = facePicUrl;
	}

	public String getOpenplatformSessionUrl() {
		return openplatformSessionUrl;
	}

	public void setOpenplatformSessionUrl(String openplatformSessionUrl) {
		this.openplatformSessionUrl = openplatformSessionUrl;
	}

	public String getIndexUrl() {
		return indexUrl;
	}

	public void setIndexUrl(String indexUrl) {
		this.indexUrl = indexUrl;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public String getGetToken() {
		return getToken;
	}

	public void setGetToken(String getToken) {
		this.getToken = getToken;
	}

	public String getOpenplatformTokenUrl() {
		return openplatformTokenUrl;
	}

	public String getUsersUrl() {
		return usersUrl;
	}

	public void setUsersUrl(String usersUrl) {
		this.usersUrl = usersUrl;
	}
}
