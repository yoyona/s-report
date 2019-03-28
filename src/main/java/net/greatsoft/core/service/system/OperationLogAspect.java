/*
 * 版权所有 2016 北航冠新。
 * 保留所有权利。
 */
package net.greatsoft.core.service.system;

import java.lang.reflect.Method;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.greatsoft.core.domain.model.system.Operation;
import net.greatsoft.core.domain.model.system.OperationLog;
import net.greatsoft.core.domain.model.system.User;
import net.greatsoft.core.repository.system.OperationLogRepository;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class OperationLogAspect {
	@Resource
	private OperationLogRepository operationLogRepository;

	@Before("execution(public * net.greatsoft.core.web.*.*Controller.*(..))")
	public void operationLogBefore(JoinPoint joinPoint) {
		operationLog(joinPoint);
	}

	@After("execution(public * net.greatsoft.core.web.login.LoginController.login(..))")
	public void operationLogAfter(JoinPoint joinPoint) {
		operationLog(joinPoint);
	}

	private void operationLog(JoinPoint joinPoint) {
		Signature signature = joinPoint.getSignature();
		if (!(signature instanceof MethodSignature)) {
			return;
		}

		MethodSignature methodSignature = (MethodSignature) signature;
		Method method = methodSignature.getMethod();
		Operation operation = method.getAnnotation(Operation.class);
		if (operation == null) {
			return;
		}

		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("User");
		if (user == null) {
			return;
		}

		OperationLog log = new OperationLog();
		log.setIpAddress(getIpAddress(request));
		log.setUserId(user.getId());
		log.setUserName(user.getName());
		log.setOperationType(operation.type());
		log.setBusinessName(operation.name());
		log.setBusinessDescription(operation.description());
		log.setOperateTime(new Date());
		operationLogRepository.save(log);
	}

	private String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
