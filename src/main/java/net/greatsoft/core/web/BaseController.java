/*
 * 版权所有 2016 冠新软件。
 * 保留所有权利。
 */
package net.greatsoft.core.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import net.matrix.servlet.Servlets;

import net.greatsoft.application.common.domain.util.ObjectClone;
import net.greatsoft.core.domain.model.system.User;
import net.greatsoft.core.dto.system.UserDto;
import net.greatsoft.core.service.redis.CacheUsersService;
import net.greatsoft.core.util.ParamUtil;
import net.greatsoft.core.util.ProjectUtils;
import net.greatsoft.core.util.constant.SystemConstant;
import net.greatsoft.core.web.dto.ResultCode;
import net.greatsoft.core.web.dto.ResultDto;

/**
 * 父级 controller，所有的 controller 继承这个类
 */
public class BaseController {
	private static final Logger LOG = LoggerFactory
			.getLogger(BaseController.class);
	
	@Autowired
	private CacheUsersService cacheUsersService;

	@ExceptionHandler(value = Throwable.class)
	@ResponseBody
	public ResultDto handleException(Throwable t) {
		LOG.error("转换异常消息", t);

		return fail(ResultCode.SYSTEM_ERROR, "系统错误，请联系冠新公司客服人员 ",
				t.getLocalizedMessage());
	}

	protected ResultDto success(Object data) {
		return new ResultDto(ResultCode.SUCCESS, "", data);
	}

	protected ResultDto fail(String message, Object data) {
		return new ResultDto(ResultCode.FAIL, message, data);
	}

	protected ResultDto fail(Integer code, String message, Object data) {
		return new ResultDto(code, message, data);
	}

	/**
	 * 登录成功后缓存用户信息
	 */
	public User setCacheUsers(User users) {
		User usersDest = new User();
		ParamUtil.copy(usersDest, users);
		// 生成登录token
		String token = UUID.randomUUID().toString();
		usersDest.setToken(token);
		UserDto userDto = new UserDto();
		users.setToken(token);
		ParamUtil.copy(userDto, users);
		// 将token缓存到redise中,token-cacheUsersDto存储
		cacheUsersService.addOrUpdate(users.getToken(),userDto);
		return usersDest;
	}
	
	/**
	 * 登录成功后缓存用户信息
	 */
	protected User setCurrentUser(HttpSession session, User user) {
		User cacheUser = new User();
		ObjectClone.copy(cacheUser, user);
		cacheUser.setToken(session.getId());
		session.setAttribute("User", cacheUser);
		// 缓存用户在线人数
		Object number = cacheUsersService.getByToken(SystemConstant.CURREN_USERS_NUMBER);
		Integer currentNumber = number == null ? 0 : Integer.valueOf(String.valueOf(number)) + 1;
		cacheUsersService.addOrUpdate(SystemConstant.CURREN_USERS_NUMBER, currentNumber);
		return cacheUser;
	}

	/**
	 * 查询登录用户的缓存信息
	 */
	/*protected User getCurrentUser(HttpServletRequest request) {
		String token = request.getHeader("token");
		User user = cacheUsersService.findByUsersId(token);
		return user;
	}*/
	protected User getCurrentUser(HttpSession session) {
		return (User) session.getAttribute("User");
	}

	protected User getCachedUser(HttpServletRequest request){
		String token = request.getHeader("token");
		User user = cacheUsersService.findByUsersId(token);
		return user;
	}
	
	protected UserDto getCachedUserDto(HttpServletRequest request){
		String token = request.getHeader("token");
		UserDto user = cacheUsersService.findByToken(token);
		return user;
	}
	/**
	 * 清除缓存用户信息
	 */
	protected void clearCurrentUser(HttpSession session) {
		session.removeAttribute("User");
	}
	
	/**
	 * 清除用户的token
	 */
	public void delCacheUsersDto(HttpServletRequest request) {
		String token = request.getHeader("token");
		cacheUsersService.delByToken(token);
		// 将用户在线人数减一
		Object number = cacheUsersService.getByToken(SystemConstant.CURREN_USERS_NUMBER);
		Integer currentNumber =  Integer.valueOf(String.valueOf(number)) - 1;
		cacheUsersService.addOrUpdate(SystemConstant.CURREN_USERS_NUMBER, currentNumber);
	}

	protected void parseDateString(Map<String, Object> params, String key,
			String format) {
		LocalDate date = ProjectUtils.parseLocalDate((String) params.get(key),
				format);
		if (date == null) {
			params.remove(key);
		} else {
			params.put(key, date.toDate());
		}
	}

	protected void parseDateStringPlus1(Map<String, Object> params, String key,
			String format) {
		LocalDate date = ProjectUtils.parseLocalDate((String) params.get(key),
				format);
		if (date == null) {
			params.remove(key);
		} else {
			date = date.plusDays(1);
			params.put(key, date.toDate());
		}
	}

	/**
	 * 下载 Excel 文件。
	 */
	protected void downloadExcelFile(HttpServletResponse response,
			Resource filePath, String displayFilename) throws IOException {
		downloadFile(response, "application/vnd.ms-excel", filePath,
				displayFilename);
	}

	/**
	 * 下载压缩文件。
	 */
	protected void downloadZipFile(HttpServletResponse response,
			Resource filePath, String displayFilename) throws IOException {
		downloadFile(response, "application/zip", filePath, displayFilename);
	}

	protected void downloadFile(HttpServletResponse response,
			String contentType, Resource filePath, String displayFilename)
			throws IOException {
		response.reset();
		response.setContentType(contentType);
		response.setContentLengthLong(filePath.contentLength());
		Servlets.setFileDownloadHeader(response, displayFilename);
		try (InputStream is = filePath.getInputStream();
				OutputStream os = response.getOutputStream()) {
			IOUtils.copy(is, os);
			os.flush();
		}
	}
	// 获取用户在线人数
	public Integer getCurrentUserNumber () {
		Object byToken = this.cacheUsersService.getByToken(SystemConstant.CURREN_USERS_NUMBER);
		return Integer.valueOf(String.valueOf(byToken == null ? "0" : byToken));
	}
}
