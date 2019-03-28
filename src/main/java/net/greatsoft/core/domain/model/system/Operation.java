/*
 * 版权所有 2017 冠新软件。
 * 保留所有权利。
 */
package net.greatsoft.core.domain.model.system;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 操作信息
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Operation {
	String type();

	String name();

	String description();
}
