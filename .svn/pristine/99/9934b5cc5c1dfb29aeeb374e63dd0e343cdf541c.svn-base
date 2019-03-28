/*
 * $Id: CommonUtils.java 1878 2016-09-08 06:35:57Z huangshuang $
 * 版权所有 2016 冠新软件。
 * 保留所有权利。
 */
package net.greatsoft.core.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * @author HS
 * @date 2016年6月18日 下午3:58:54
 * @Description: 通用工具类
 * 
 */
public class CommonUtils extends StringUtils {

	/**
	 * 
	 * @author HS
	 * @date 2016年6月18日 下午3:59:06
	 * @Description: 判断对象是否为空或空字符串
	 *
	 * @param object
	 * @return
	 */
	public static boolean isBlankOrEmpty(Object object) {
		if (null == object) {
			return true;
		}
		if (object.toString().equals("")) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @author HS
	 * @date 2016年6月24日 下午4:44:27
	 * @Description: 判断集合是否为空
	 *
	 * @param object
	 * @return
	 */
	public static boolean isCollectionBlankOrEmpty(Collection<?> object) {
		if (null == object) {
			return true;
		}
		if (object.size() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @author HS
	 * @date 2016年6月23日 上午11:30:34
	 * @Description: 根据日期计算年龄
	 *
	 * @param birthday
	 * @param endDate
	 * @param calculateType
	 * @return
	 */
	public static Integer getAge(final Date birthday, final Date endDate, int calculateType) {
		switch (calculateType) {
		case 1: {
			return new Integer(DateUtil.beforeYears(birthday, endDate) + 1);
		}
		case 2: {
			Calendar birthdayCalendar = Calendar.getInstance();
			birthdayCalendar.setTime(birthday);
			Calendar endDateCalendar = Calendar.getInstance();
			endDateCalendar.setTime(endDate);
			if (birthdayCalendar.get(Calendar.MONTH) <= endDateCalendar.get(Calendar.MONTH)
					&& birthdayCalendar.get(Calendar.DAY_OF_MONTH) <= endDateCalendar.get(Calendar.DAY_OF_MONTH)) {
				return new Integer(DateUtil.beforeYears(birthday, endDate) + 1);
			} else {
				return new Integer(DateUtil.beforeYears(birthday, endDate));
			}
		}
		default: {
			return new Integer(DateUtil.beforeYears(birthday, endDate) + 1);
		}
		}
	}

	/**
	 * 
	 * @author AlphGo
	 * @date 2016年8月23日 下午7:19:32 
	 * @Description: 比较两个日期的年份是否相同
	 *
	 * @param oldDate
	 * @param newDate
	 * @return
	 */
	public static boolean differentYearOfDate(Date oldDate,Date newDate){
		Calendar oldOne = Calendar.getInstance();
		oldOne.setTime(oldDate);
		Integer oldYear = oldOne.get(Calendar.YEAR);
		Calendar newOne = Calendar.getInstance();
		newOne.setTime(newDate);
		Integer newYear = newOne.get(Calendar.YEAR);
		if(oldYear.equals(newYear)){
			return false;
		}
		return true;
	}
	/**
	 * 
	 * @author HS
	 * @date 2016年6月23日 下午1:23:01
	 * @Description: 一对多关联，把前台传过来的多的一方变化的copy的到后台查询出来的里面
	 *
	 * @param persistent
	 *            数据库中查询出来的集合
	 * @param detached
	 *            前台传过来的集合
	 * @param primaryKey
	 *            主键
	 * @param updateArrs
	 *            如果主键相等，集合中需要更新的值 是个字符串数组
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void copyListForUpdate(List persistent, List detached, String primaryKey, String[] updateArrs) {
		// 存储新增的数据
		List addList = new ArrayList();
		// 存储删除的数据
		List removeList = new ArrayList();

		// 如果原值是空的
		if (CommonUtils.isCollectionBlankOrEmpty(persistent)) {
			if (!CommonUtils.isCollectionBlankOrEmpty(detached)) {
				persistent.addAll(detached);
			}
			return;
		}

		// 如果传过来的是空的
		if (CommonUtils.isCollectionBlankOrEmpty(detached)) {
			if (!CommonUtils.isCollectionBlankOrEmpty(persistent)) {
				persistent.clear();
			}
			return;
		}

		// 删除的
		for (Object object : persistent) {
			if (!detached.contains(object)) {
				removeList.add(object);
			}
		}

		// 新增的
		for (Object object : detached) {
			if (!persistent.contains(object)) {
				addList.add(object);
			}
		}

		// 更新的
		for (Object detachedObject : detached) {
			for (Object persistentObject : persistent) {
				// 主键相等
				if (Reflections.getFieldValue(persistentObject, primaryKey)
						.equals(Reflections.getFieldValue(detachedObject, primaryKey))) {
					// 更新具体的值
					for (String updateFieldName : updateArrs) {
						Reflections.setFieldValue(persistentObject, updateFieldName,
								Reflections.getFieldValue(detachedObject, updateFieldName));
					}
					break;
				}
			}
		}
		persistent.removeAll(removeList);
		persistent.addAll(addList);
	}
	
	public static String encrypt(String password) {
	    String re_md5 = new String();
	    password += "gop";
	    try {
	      MessageDigest md = MessageDigest.getInstance("MD5");
	      md.update(password.getBytes());
	      byte b[] = md.digest();
	      int i;
	      StringBuffer buf = new StringBuffer("");
	      for (int offset = 0; offset < b.length; offset++) {
	        i = b[offset];
	        if (i < 0)
	          i += 256;
	        if (i < 16)
	          buf.append("0");
	        buf.append(Integer.toHexString(i));
	      }
	      re_md5 = buf.toString();
	    } catch (NoSuchAlgorithmException e) {
	      e.printStackTrace();
	    }
	    return re_md5;
	  }
}
