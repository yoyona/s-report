package net.greatsoft.core.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

	/**
	 * yyyy-MM-dd
	 */
	public static final String YEAR_MONTH_DAY_PATTERN = "yyyy-MM-dd";

	/**
	 * HH:mm:ss
	 */
	public static final String HOUR_MINUTE_SECOND_PATTERN = "HH:mm:ss";

	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static final String YMDHMS_PATTERN = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 根据传入的日期格式化pattern将传入的日期格式化成字符串。
	 * 
	 * @param date
	 *            要格式化的日期对象
	 * @param pattern
	 *            日期格式化pattern
	 * @return 格式化后的日期字符串
	 */
	public static String format(final Date date, final String pattern) {
		DateFormat df = new SimpleDateFormat(pattern);
		if (date == null)
			return null;
		return df.format(date);
	}

	/**
	 * 
	 * @param valueStr
	 * @param datePattern
	 * @return
	 * @author:
	 * @createDate:
	 * @modifiedBy:黄双
	 * @modifiedContent:new SimpleDateFormat(datePattern,Locale.ENGLISH);
	 *                      本来是写死的，用的是：YEAR_MONTH_DAY_PATTERN，而不是用的入参datePattern
	 * @modifiedDate:2011-9-11
	 */
	public static Date parse(String valueStr, String datePattern) {
		SimpleDateFormat df = new SimpleDateFormat(datePattern, Locale.ENGLISH);
		// df.setTimeZone(TimeZone.getTimeZone("GMT+08"));
		Date date = null;
		try {
			date = df.parse(valueStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 返回给定的beforeDate比afterDate早的年数。如果beforeDate晚于afterDate，则 返回负数。
	 * 
	 * @param beforeDate
	 *            要比较的早的日期
	 * @param afterDate
	 *            要比较的晚的日期
	 * @return beforeDate比afterDate早的年数，负数表示晚。
	 */
	public static int beforeYears(final Date beforeDate, final Date afterDate) {
		Calendar beforeCalendar = Calendar.getInstance();
		beforeCalendar.setTime(beforeDate);
		beforeCalendar.set(Calendar.MONTH, 1);
		beforeCalendar.set(Calendar.DATE, 1);
		beforeCalendar.set(Calendar.HOUR, 0);
		beforeCalendar.set(Calendar.SECOND, 0);
		beforeCalendar.set(Calendar.MINUTE, 0);
		Calendar afterCalendar = Calendar.getInstance();
		afterCalendar.setTime(afterDate);
		afterCalendar.set(Calendar.MONTH, 1);
		afterCalendar.set(Calendar.DATE, 1);
		afterCalendar.set(Calendar.HOUR, 0);
		afterCalendar.set(Calendar.SECOND, 0);
		afterCalendar.set(Calendar.MINUTE, 0);
		boolean positive = true;
		if (beforeDate.after(afterDate))
			positive = false;
		int beforeYears = 0;
		while (true) {
			boolean yearEqual = beforeCalendar.get(Calendar.YEAR) == afterCalendar.get(Calendar.YEAR);
			if (yearEqual) {
				break;
			} else {
				if (positive) {
					beforeYears++;
					beforeCalendar.add(Calendar.YEAR, 1);
				} else {
					beforeYears--;
					beforeCalendar.add(Calendar.YEAR, -1);
				}
			}
		}
		return beforeYears;
	}

	/**
	 * 日期加数字天数之后得到新的日期
	 * 
	 * @param d
	 *            传进来的日期参数 eg:Thu Dec 16 15:19:25 CST 2010。
	 * @param day
	 *            天数 eg:12
	 * @return eg:Tue Dec 28 15:19:25 CST 2010。
	 */
	public static Date addDate(Date d, long day) {

		long time = d.getTime();
		day = day * 24 * 60 * 60 * 1000;
		time += day;
		return new Date(time);
	}

	/**
	 * 将日期String转换为java.util.Calendar
	 * 
	 * @param stt
	 * @return calendar
	 */
	public static Calendar toCalendar(String str) {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);
		return calendar;
	}

	/**
	 * 
	 * @author hn
	 * @date 2016年7月18日 上午11:34:41
	 * @Description: 获取天数
	 *
	 * @param beginDate
	 *            起始日期
	 * @param endDate
	 *            结束日期
	 * @return 天数
	 */
	public static int getDays(Date beginDate, Date endDate) {
		SimpleDateFormat sdf = new SimpleDateFormat(YEAR_MONTH_DAY_PATTERN);
		String sBeginDate = sdf.format(beginDate);
		String sEndDate = sdf.format(endDate);
		int times = 0;
		try {
			times = (int) ((sdf.parse(sEndDate).getTime() - sdf.parse(sBeginDate).getTime()) / (1000 * 60 * 60 * 24));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return times;
	}

	/**
	 * 
	 * @author hn
	 * @date 2016年7月18日 下午6:50:10
	 * @Description: TODO
	 *
	 * @param beginDate
	 *            起始日期
	 * @param endDate
	 *            结束日期
	 * @param datePattern
	 *            格式化日期字符串
	 * @return beginDate小于endDate时返回true，反之返回false
	 */
	public static boolean compareDate(Date beginDate, Date endDate, String datePattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
		String sBeginDate = sdf.format(beginDate);
		String sEndDate = sdf.format(endDate);
		long times = 0;
		boolean isBigger = false;
		try {
			times = sdf.parse(sEndDate).getTime() - sdf.parse(sBeginDate).getTime();
			if (times >= 0) {
				isBigger = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isBigger;
	}

	/**
	 * 获取某年第一天日期
	 * 
	 * @param year
	 *            年份
	 * @return Date
	 */
	public static Date getCurrYearFirst(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		Date currYearFirst = calendar.getTime();
		return currYearFirst;
	}

	/**
	 * 获取某年最后一天日期
	 * 
	 * @param year
	 *            年份
	 * @return Date
	 */
	public static Date getCurrYearLast(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();
		return currYearLast;
	}

	/**
	 * 
	 * @author HS
	 * @date 2016年10月23日 下午8:51:23
	 * @Description: 本周第一天
	 *
	 * @return
	 */
	public static String getStartWeekDay() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 获取本周一的日期
		return format(cal.getTime(), YEAR_MONTH_DAY_PATTERN);
	}

	/**
	 * 
	 * @author HS
	 * @date 2016年10月23日 下午8:55:19
	 * @Description: 本周最后一天
	 *
	 * @return
	 */
	public static String getEndWeekDay() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		return format(cal.getTime(), YEAR_MONTH_DAY_PATTERN);
	}

	/**
	 * 
	 * @author HS
	 * @date 2016年10月23日 下午8:59:51
	 * @Description: 本月第一天
	 *
	 * @return
	 */
	public static String getStartMonthDay() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		return format(c.getTime(), YEAR_MONTH_DAY_PATTERN);
	}

	/**
	 * 
	 * @author HS
	 * @date 2016年10月23日 下午8:59:51
	 * @Description: 本月最后一天
	 *
	 * @return
	 */
	public static String getEndMonthDay() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return format(c.getTime(), YEAR_MONTH_DAY_PATTERN);
	}

	/**
	 * 
	 * @author HS
	 * @date 2016年10月23日 下午9:15:45
	 * @Description: 返回指定日期的季的第一天
	 *
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfQuarter(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return getFirstDayOfQuarter(calendar.get(Calendar.YEAR), getQuarterOfYear(calendar));
	}

	/**
	 * 
	 * @author HS
	 * @date 2016年10月23日 下午9:07:53
	 * @Description: 返回指定年季的季的第一天
	 *
	 * @param year
	 * @param quarter
	 * @return
	 */
	public static Date getFirstDayOfQuarter(Integer year, Integer quarter) {
		Calendar calendar = Calendar.getInstance();
		Integer month = new Integer(0);
		if (quarter == 1) {
			month = 1 - 1;
		} else if (quarter == 2) {
			month = 4 - 1;
		} else if (quarter == 3) {
			month = 7 - 1;
		} else if (quarter == 4) {
			month = 10 - 1;
		} else {
			month = calendar.get(Calendar.MONTH);
		}
		return getFirstDayOfMonth(year, month);
	}

	/**
	 * 
	 * @author HS
	 * @date 2016年10月23日 下午9:11:57
	 * @Description: 返回指定日期的月的第一天
	 *
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
		return calendar.getTime();
	}

	/**
	 * 
	 * @author HS
	 * @date 2016年10月23日 下午9:08:19
	 * @Description: 返回指定年月的月的第一天
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getFirstDayOfMonth(Integer year, Integer month) {
		Calendar calendar = Calendar.getInstance();
		if (year == null) {
			year = calendar.get(Calendar.YEAR);
		}
		if (month == null) {
			month = calendar.get(Calendar.MONTH);
		}
		calendar.set(year, month, 1);
		return calendar.getTime();
	}

	/**
	 * 
	 * @author HS
	 * @date 2016年10月23日 下午9:08:52
	 * @Description: 返回指定日期的季的最后一天
	 *
	 * @return
	 */
	public static Date getLastDayOfQuarter(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return getLastDayOfQuarter(calendar.get(Calendar.YEAR), getQuarterOfYear(calendar));
	}

	/**
	 * 
	 * @author HS
	 * @date 2016年10月23日 下午9:09:39
	 * @Description: 返回指定年季的季的最后一天
	 *
	 * @param year
	 * @param quarter
	 * @return
	 */
	public static Date getLastDayOfQuarter(Integer year, Integer quarter) {
		Calendar calendar = Calendar.getInstance();
		Integer month = new Integer(0);
		if (quarter == 1) {
			month = 3 - 1;
		} else if (quarter == 2) {
			month = 6 - 1;
		} else if (quarter == 3) {
			month = 9 - 1;
		} else if (quarter == 4) {
			month = 12 - 1;
		} else {
			month = calendar.get(Calendar.MONTH);
		}
		return getLastDayOfMonth(year, month);
	}

	/**
	 * 
	 * @author HS
	 * @date 2016年10月23日 下午9:09:55
	 * @Description: 返回指定日期的月的最后一天
	 *
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
		calendar.roll(Calendar.DATE, -1);
		return calendar.getTime();
	}

	/**
	 * 
	 * @author HS
	 * @date 2016年10月23日 下午9:10:31
	 * @Description: 返回指定年月的月的最后一天
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getLastDayOfMonth(Integer year, Integer month) {
		Calendar calendar = Calendar.getInstance();
		if (year == null) {
			year = calendar.get(Calendar.YEAR);
		}
		if (month == null) {
			month = calendar.get(Calendar.MONTH);
		}
		calendar.set(year, month, 1);
		calendar.roll(Calendar.DATE, -1);
		return calendar.getTime();
	}

	/**
	 * 
	 * @author HS
	 * @date 2016年10月23日 下午9:03:32
	 * @Description: 返回指定日期的季度
	 *
	 * @param date
	 * @return
	 */
	public static int getQuarterOfYear(Calendar calendar) {
		return calendar.get(Calendar.MONTH) / 3 + 1;
	}

	/**
	 * 获取当年的第一天
	 * 
	 * @param year
	 * @return
	 */
	public static Date getCurrYearFirst() {
		Calendar currCal = Calendar.getInstance();
		int currentYear = currCal.get(Calendar.YEAR);
		return getYearFirst(currentYear);
	}

	/**
	 * 获取当年的最后一天
	 * 
	 * @param year
	 * @return
	 */
	public static Date getCurrYearLast() {
		Calendar currCal = Calendar.getInstance();
		int currentYear = currCal.get(Calendar.YEAR);
		return getYearLast(currentYear);
	}

	/**
	 * 获取某年第一天日期
	 * 
	 * @param year
	 *            年份
	 * @return Date
	 */
	public static Date getYearFirst(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		Date currYearFirst = calendar.getTime();
		return currYearFirst;
	}

	/**
	 * 获取某年最后一天日期
	 * 
	 * @param year
	 *            年份
	 * @return Date
	 */
	public static Date getYearLast(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();

		return currYearLast;
	}

	/**
	 * 
	 * @author AlphGo
	 * @date 2016年7月26日 下午4:53:11
	 * @Description: 计算年龄
	 *
	 * @param birthday
	 * @return
	 */
	public static Integer getAgeByBirthday(Date birthday) {
		if (birthday == null) {
			return 0;
		}
		if (birthday.after(new Date())) {
			return 0;
		}
		Calendar now = Calendar.getInstance();
		Integer nowYear = now.get(Calendar.YEAR);
		Integer nowMonth = now.get(Calendar.MONTH);
		Calendar birthCal = Calendar.getInstance();
		birthCal.setTime(birthday);
		Integer birthYear = birthCal.get(Calendar.YEAR);
		Integer birthMonth = birthCal.get(Calendar.MONTH);
		Integer age = nowYear - birthYear;
		if (nowYear > birthYear && nowMonth < birthMonth) {
			age--;
		}
		return age;
	}
}
