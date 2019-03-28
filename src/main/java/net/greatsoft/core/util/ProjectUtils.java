/*
 * 版权所有 2016 冠新软件。
 * 保留所有权利。
 */
package net.greatsoft.core.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.ReadablePeriod;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.context.ApplicationContext;

import net.matrix.app.DefaultSystemContext;
import net.matrix.app.SystemContext;
import net.matrix.lang.ImpossibleException;
import net.matrix.lang.ReflectionRuntimeException;
import net.matrix.lang.Reflections;
import net.matrix.text.DateFormatHelper;

import net.greatsoft.application.common.domain.repository.system.SystemDao;
import net.greatsoft.application.common.domain.service.system.SystemService;
import net.greatsoft.application.common.domain.util.AgeCalculationMode;
import net.greatsoft.application.common.domain.util.CommonUtilsBase;
import net.greatsoft.application.common.domain.util.sn.IntegerSerialNumber;
import net.greatsoft.application.common.domain.util.sn.IntegerSerialNumberParser;
import net.greatsoft.application.common.domain.util.sn.SerialNumber;
import net.greatsoft.application.common.domain.util.sn.SerialNumberGenerationStrategy;
import net.greatsoft.core.codetable.CodedBeanInfo;
import net.greatsoft.core.codetable.CodedMemberInfo;
//import net.greatsoft.core.domain.model.system.CodeCommon;
//import net.greatsoft.core.domain.model.system.CodeSet;
//import net.greatsoft.core.domain.model.system.CodeType;
import net.greatsoft.util.JsonMapperHelper;

/**
 * 项目工具
 */
public class ProjectUtils {
	/**
	 * 阻止实例化。
	 */
	private ProjectUtils() {
	}

	public static void init(final ApplicationContext applicationContext) {
		SystemContext systemContext = new DefaultSystemContext();
		systemContext.registerObject(ApplicationContext.class,
				applicationContext);
		CommonUtilsBase.init(systemContext);
		//loadCode();
	}

	/**
	 * @return 系统环境
	 */
	public static SystemContext getSystemContext() {
		return CommonUtilsBase.getSystemContext();
	}

	/**
	 * @return Spring 应用环境
	 */
	public static ApplicationContext getApplicationContext() {
		return CommonUtilsBase.getApplicationContext();
	}

	/**
	 * 获取系统中注册的服务。
	 */
	public static <T> T getService(Class<T> requiredType) {
		return CommonUtilsBase.getService(requiredType);
	}

	/**
	 * 获取系统服务。
	 */
	public static SystemService getSystemService() {
		return CommonUtilsBase.getSystemService();
	}

	/**
	 * 获取当前系统时间。
	 * 
	 * @return 当前系统时间
	 */
	public static long getCurrentSystemMillis() {
		return CommonUtilsBase.getCurrentSystemMillis();
	}

	/**
	 * 获取当前系统时间。
	 * 
	 * @return 当前系统时间
	 */
	public static Date getCurrentSystemDate() {
		return CommonUtilsBase.getCurrentSystemDate();
	}

	/**
	 * 获取当前系统时间。
	 * 
	 * @return 当前系统时间
	 */
	public static Calendar getCurrentSystemCalendar() {
		return CommonUtilsBase.getCurrentSystemCalendar();
	}

	/**
	 * 获取当前系统时间。
	 * 
	 * @return 当前系统时间
	 */
	public static LocalDate getCurrentSystemLocalDate() {
		return CommonUtilsBase.getCurrentSystemLocalDate();
	}

	/**
	 * 获取当前系统时间。
	 * 
	 * @return 当前系统时间
	 */
	public static LocalDateTime getCurrentSystemLocalDateTime() {
		return CommonUtilsBase.getCurrentSystemLocalDateTime();
	}

	/**
	 * 根据当前系统时间计算年龄。
	 * 
	 * @param birthday
	 *            出生日期
	 * @param mode
	 *            计算模式
	 * @return 年龄
	 */
	public static int calculateAge(LocalDate birthday,
			AgeCalculationMode mode) {
		return CommonUtilsBase.calculateAge(birthday, mode);
	}

	/**
	 * 根据当前系统时间计算年龄。
	 * 
	 * @param birthday
	 *            出生日期
	 * @param mode
	 *            计算模式
	 * @return 年龄
	 */
	public static int calculateAge(Date birthday, AgeCalculationMode mode) {
		return CommonUtilsBase.calculateAge(birthday, mode);
	}

	/**
	 * 按年、月、周计算年龄。 大于或等于一岁返回类型为 <code>Years</code> 的年份，大于或等于一个月小于一岁返回
	 * <code>Months</code> 的月份，小于一个月返回 <code>Weeks</code> 的周数。
	 * 
	 * @param birthday
	 *            出生日期
	 * @return 年龄
	 */
	public static ReadablePeriod calculateBabyAge(LocalDate birthday) {
		return CommonUtilsBase.calculateBabyAge(birthday);
	}

	/**
	 * 按年、月、周计算年龄。 大于或等于一岁返回类型为 <code>Years</code> 的年份，大于或等于一个月小于一岁返回
	 * <code>Months</code> 的月份，小于一个月返回 <code>Weeks</code> 的周数。
	 * 
	 * @param birthday
	 *            出生日期
	 * @return 年龄
	 */
	public static ReadablePeriod calculateBabyAge(Date birthday) {
		return CommonUtilsBase.calculateBabyAge(birthday);
	}

	/**
	 * 生成序列号。
	 * 
	 * @param persistenceId
	 *            持久化标识
	 * @param strategy
	 *            序列号生成策略
	 * @return 序列号
	 */
	public static <SN extends SerialNumber> SN generateSerialNumber(
			SystemDao.SerialNumberPersistenceId persistenceId,
			SerialNumberGenerationStrategy<SN> strategy) {
		return CommonUtilsBase.generateSerialNumber(persistenceId, strategy);
	}

	/**
	 * 生成序列号。
	 */
	public static String generateSerialNumber(final String code) {
		Date today = getCurrentSystemDate();

		String subcode = DateFormatHelper.format(today, "yyyyMMdd");
		SystemDao.SerialNumberPersistenceId persistenceId = new SystemDao.CodedSerialNumberPersistenceId(
				code, subcode);
		SerialNumberGenerationStrategy strategy = new SerialNumberGenerationStrategy(
				new IntegerSerialNumberParser(), new IntegerSerialNumber(1, 4));
		return code + subcode + generateSerialNumber(persistenceId, strategy);
	}

	//private static CodeService codeService;

	/**
	 * 获取代码服务。
	 */
	/*public static CodeService getCodeService() {
		if (codeService == null) {
			codeService = getService(CodeService.class);
		}
		return codeService;
	}*/

	/**
	 * 代码信息缓存
	 */
	//private static final Map<String, CodeSet> CODESET_MAP = new HashMap<>();

	/**
	 * 加载代码
	 */
	/*private static void loadCode() {
		List<CodeType> codeTypes = getCodeService().findAllCodeType();
		for (CodeType codeType : codeTypes) {
			List<CodeCommon> codes = getCodeService()
					.findCodeCommonByType(codeType.getType());
			CodeSet codeSet = new CodeSet(codeType, codes);

			ProjectUtils.CODESET_MAP.put(codeType.getType(), codeSet);
		}
	}*/

	/**
	 * 代码转中文。
	 * 
	 * @param object
	 *            待转对象
	 */
	public static void updateCodedText(Object object) {
		CodedBeanInfo beanInfo = CodedBeanInfo.get(object.getClass());
		for (CodedMemberInfo memberInfo : beanInfo.getMembers()) {
			String type = memberInfo.getType();
			Field codeField = memberInfo.getCodeField();
			Field textField = memberInfo.getTextField();
			try {
				Object code = codeField.get(object);
				if (code == null) {
					continue;
				}

//				CodeSet codeSet = CODESET_MAP.get(type);
//				if (codeSet == null) {
//					continue;
//				}

				//String text = codeSet.getValue(code.toString());
				//textField.set(object, text);
			} catch (IllegalAccessException e) {
				throw new ReflectionRuntimeException(e);
			}
		}
	}

	/**
	 * 代码转中文。
	 * 
	 * @param objects
	 *            待转对象集合
	 */
	public static void updateCodedText(Iterable<?> objects) {
		for (Object object : objects) {
			updateCodedText(object);
		}
	}

	private static final JsonMapperHelper JSON = new JsonMapperHelper();

	public static String toJson(Object object) {
		try {
			return JSON.toJson(object);
		} catch (IOException e) {
			throw new ImpossibleException(e);
		}
	}

	public static <T> T fromJson(String jsonString, Class<T> clazz) {
		try {
			return JSON.fromJson(jsonString, clazz);
		} catch (IOException e) {
			throw new ImpossibleException(e);
		}
	}

	// TODO ParamUtils
	public static LocalDate parseLocalDate(String value, String format) {
		if (StringUtils.isBlank(value)) {
			return null;
		}
		DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
		return formatter.parseLocalDate(value);
	}

	/**
	 * 把前台传过来的变化的内容复制到后台查询出来的对象里
	 *
	 * @param list
	 *            后台查询出来的集合
	 * @param updateList
	 *            前台传过来的集合
	 * @param idFieldName
	 *            主键
	 * @param updateFieldNames
	 *            如果主键相等，集合中需要更新的值 是个字符串数组
	 */
	public static void updateList(List list, List updateList,
			String idFieldName, String[] updateFieldNames) {
		// 如果原值是空的
		if (CollectionUtils.isEmpty(list)) {
			list.addAll(updateList);
			return;
		}

		// 如果传过来的值是空的
		if (CollectionUtils.isEmpty(updateList)) {
			list.clear();
			return;
		}

		// 新增的数据
		List addedList = new ArrayList();
		// 删除的数据
		List removedList = new ArrayList();

		// 删除的
		for (Object object : list) {
			Object id = Reflections.getFieldValue(object, idFieldName);
			boolean remove = true;
			for (Object updateObject : updateList) {
				Object updateId = Reflections.getFieldValue(updateObject,
						idFieldName);
				if (Objects.equals(id, updateId)) {
					// 更新具体的值
					for (String updateFieldName : updateFieldNames) {
						Object fieldValue = Reflections
								.getFieldValue(updateObject, updateFieldName);
						Reflections.setFieldValue(object, updateFieldName,
								fieldValue);
					}

					remove = false;
					break;
				}
			}
			if (remove) {
				removedList.add(object);
			}
		}

		// 新增的
		for (Object updateObject : updateList) {
			Object updateId = Reflections.getFieldValue(updateObject,
					idFieldName);
			boolean add = true;
			for (Object object : list) {
				Object id = Reflections.getFieldValue(object, idFieldName);
				if (Objects.equals(id, updateId)) {
					add = false;
					break;
				}
			}
			if (add) {
				addedList.add(updateObject);
			}
		}

		list.removeAll(removedList);
		list.addAll(addedList);
	}
}
