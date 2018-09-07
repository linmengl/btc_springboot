package com.blockchain.btc_springboot.demo.test;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 日期工具类
 */
@Slf4j
public class DateTimeUtil {

	private static final String TIME_FORMAT_SHORT = "yyyyMMddHHmmss";
	private static final String TIME_FORMAT_YMD = "yyyy/MM/dd HH:mm:ss";
	private static final String TIME_FORMAT_NORMAL = "yyyy-MM-dd HH:mm:ss";
	private static final String TIME_FORMAT_ENGLISH = "MM/dd/yyyy HH:mm:ss";
	private static final String TIME_FORMAT_CHINA = "yyyy年MM月dd日 HH时mm分ss秒";
	private static final String TIME_FORMAT_CHINA_S = "yyyy年M月d日 H时m分s秒";
	private static final String TIME_FORMAT_SHORT_S = "HH:mm:ss";

	private static final String DATE_FORMAT_SHORT = "yyyyMMdd";
	private static final String DATE_FORMAT_NORMAL = "yyyy-MM-dd";
	private static final String DATE_FORMAT_ENGLISH = "MM/dd/yyyy";
	private static final String DATE_FORMAT_CHINA = "yyyy年MM月dd日";
	private static final String DATE_FORMAT_CHINA_YEAR_MONTH = "yyyy年MM月";
	private static final String MONTH_FORMAT = "yyyyMM";
	private static final String YEAR_MONTH_FORMAT = "yyyy-MM";
	private static final String DATE_FORMAT_MINUTE = "yyyyMMddHHmm";
	private static final String MONTH_DAY_FORMAT = "MM-dd";
	private static final String MONTH = "MM月";

	private static final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NORMAL);

	/**
	 * 改变时间 譬如： changeDate(new Date(),Calendar.DATE, 2) 就是把当前时间加两天
	 *
	 * @param originDate 原始时间
	 * @param field      改变类型
	 * @param distance   长度
	 * @return 改变后的时间
	 * @since 0.1
	 */
	public static Date changeDate(Date originDate, int field, int distance) {
		Calendar c = Calendar.getInstance(Locale.CHINA);
		c.setTime(originDate);
		c.add(field, distance);
		return c.getTime();
	}

	/**
	 * 把日期字符串转换为日期类型
	 *
	 * @param dateStr 日期字符串
	 * @return 日期
	 * @since 0.1
	 */
	public static Date convertAsDate(String dateStr) {
		if (dateStr == null || "".equals(dateStr)) {
			return null;
		}
		DateFormat fmt = null;
		if (dateStr.matches("\\d{14}")) {
			fmt = new SimpleDateFormat(TIME_FORMAT_SHORT);
		} else if (dateStr.matches("\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}")) {
			fmt = new SimpleDateFormat(TIME_FORMAT_NORMAL);
		} else if (dateStr.matches("\\d{1,2}/\\d{1,2}/\\d{4} \\d{1,2}:\\d{1,2}:\\d{1,2}")) {
			fmt = new SimpleDateFormat(TIME_FORMAT_ENGLISH);
		} else if (dateStr.matches("\\d{4}年\\d{1,2}月\\d{1,2}日 \\d{1,2}时\\d{1,2}分\\d{1,2}秒")) {
			fmt = new SimpleDateFormat(TIME_FORMAT_CHINA);
		} else if (dateStr.matches("\\d{8}")) {
			fmt = new SimpleDateFormat(DATE_FORMAT_SHORT);
		} else if (dateStr.matches("\\d{4}-\\d{1,2}-\\d{1,2}")) {
			fmt = new SimpleDateFormat(DATE_FORMAT_NORMAL);
		} else if (dateStr.matches("\\d{1,2}/\\d{1,2}/\\d{4}")) {
			fmt = new SimpleDateFormat(DATE_FORMAT_ENGLISH);
		} else if (dateStr.matches("\\d{4}年\\d{1,2}月\\d{1,2}日")) {
			fmt = new SimpleDateFormat(DATE_FORMAT_CHINA);
		} else if (dateStr.matches("\\d{4}\\d{1,2}\\d{1,2}\\d{1,2}\\d{1,2}")) {
			fmt = new SimpleDateFormat(DATE_FORMAT_MINUTE);
		} else if (dateStr.matches("\\d{1,2}:\\d{1,2}:\\d{1,2}")) {
			fmt = new SimpleDateFormat(TIME_FORMAT_SHORT_S);
		}
		try {
			return fmt.parse(dateStr);
		} catch (ParseException e) {
			log.error(e.getMessage(), e);
			throw new IllegalArgumentException("Date or Time String is invalid.");
		}
	}

	/**
	 * Description: 将字符串转换为Date yyyy-mm-dd
	 *
	 * @param origin
	 * @return
	 * @Author yubin
	 * @Create Date: 2012-12-21 下午04:12:49
	 */
	public static Date convertAsDateString(String origin) {
		try {
			return sdf.parse(origin);
		} catch (ParseException e) {
			log.error(e.getMessage(), e);
			throw new IllegalArgumentException("Date or Time String is invalid.");
		}
	}

	/**
	 * 把 2010-02-04 12:12:34 这样的时间转换为 20100204121234
	 *
	 * @param origin 原始时间字符串
	 * @return 转换后的字符串
	 * @since 0.1
	 */
	public static String convertAsShortDateString(String origin) {
		return origin == null ? null : origin.replaceAll("[-|:|\\s|年|月|日|时|分|秒|/]", "");
	}

	/**
	 * 把 20100204121234 这样的时间转换为 2010-02-04 12:12:34
	 *
	 * @param origin 原始时间字符串
	 * @return 转换后的字符串
	 * @since 0.1
	 */
	public static String convertAsNormalDateString(String origin) {
		if (origin == null)
			return null;
		try {
			if (origin.matches("\\d{8}")) {
				SimpleDateFormat fmt = new SimpleDateFormat(DATE_FORMAT_SHORT);
				return getDateNormalString(fmt.parse(origin));
			} else if (origin.matches("\\d{14}")) {
				SimpleDateFormat fmt = new SimpleDateFormat(TIME_FORMAT_SHORT);
				return getTimeNormalString(fmt.parse(origin));
			}
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
		throw new IllegalArgumentException(origin + "is invalid,String format is yyyyMMddHHmmss or yyyyMMdd");
	}

	/**
	 * 得到日期字符串,格式为 yyyy年MM月dd日
	 *
	 * @param date 待格式化日期
	 * @return 返回格式化后的日期字符串
	 * @since 0.1
	 */
	public static String getDateChinaString(Date date) {
		DateFormat fmt = new SimpleDateFormat(DATE_FORMAT_CHINA);
		return fmt.format(date);
	}

	public static String getDateChinaStringYearMonth(Date date) {
		DateFormat fmt = new SimpleDateFormat(DATE_FORMAT_CHINA_YEAR_MONTH);
		return fmt.format(date);
	}

	/**
	 * 得到日期字符串,格式为 MM/dd/yyyy
	 *
	 * @param date 待格式化日期
	 * @return 返回格式化后的日期字符串
	 * @since 0.1
	 */
	public static String getDateEnglishString(Date date) {
		DateFormat fmt = new SimpleDateFormat(DATE_FORMAT_ENGLISH);
		return fmt.format(date);
	}

	/**
	 * 得到日期字符串,格式为 yyyy-MM-dd
	 *
	 * @param date 待格式化日期
	 * @return 返回格式化后的日期字符串
	 * @since 0.1
	 */
	public static String getDateNormalString(Date date) {
		DateFormat fmt = new SimpleDateFormat(DATE_FORMAT_NORMAL);
		return fmt.format(date);
	}

	/**
	 * Description: 获得时间戳
	 *
	 * @return
	 * @Author lijie
	 * @Create Date: 2014-4-18 下午04:26:58
	 */
	public static String getTimestamp() {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_SHORT);
		return sdf.format(Calendar.getInstance().getTime());
	}

	/**
	 * 得到日期字符串,格式为 MM-dd
	 *
	 * @param date 待格式化日期
	 * @return 返回格式化后的日期字符串
	 * @since 0.1
	 */
	public static String getMonthDayDateNormalString(Date date) {
		DateFormat fmt = new SimpleDateFormat(MONTH_DAY_FORMAT);
		return fmt.format(date);
	}

	/**
	 * Description: 得到日期字符串，格式为yyyy-MM
	 *
	 * @param date
	 * @return
	 * @Author yubin Create Date: 2012-8-29 上午11:20:31
	 */
	public static String getYearMonthString(Date date) {
		DateFormat fmt = new SimpleDateFormat(YEAR_MONTH_FORMAT);
		return fmt.format(date);
	}

	/**
	 * Description: 得到当前日期字符串，格式为yyyy-MM
	 *
	 * @return
	 * @Author yubin Create Date: 2012-8-29 上午11:28:27
	 */
	public static String getNowYearMonthString() {
		return getYearMonthString(new Date());
	}

	/**
	 * 得到日期字符串,格式为 yyyyMMdd
	 *
	 * @param date 待格式化日期
	 * @return 返回格式化后的日期字符串
	 * @since 0.1
	 */
	public static String getDateShortString(Date date) {
		DateFormat fmt = new SimpleDateFormat(DATE_FORMAT_SHORT);
		return fmt.format(date);
	}

	/**
	 * 得到日期字符串,格式为 yyyy年MM月dd日
	 *
	 * @return 返回当前日期的字符串
	 */
	public static String getNowDateChinaString() {
		return getDateChinaString(new Date());
	}

	/**
	 * 得到日期字符串,格式为 MM/dd/yyyy
	 *
	 * @return 返回当前日期的字符串
	 */
	public static String getNowDateEnglishString() {
		return getDateEnglishString(new Date());
	}

	/**
	 * 得到日期字符串,格式为 yyyy-MM-dd
	 *
	 * @return 返回当前日期的字符串
	 */
	public static String getNowDateNormalString() {
		return getDateNormalString(new Date());
	}

	/**
	 * 得到日期字符串,格式为 yyyy-MM-dd
	 *
	 * @param interval 间隔天数
	 * @return
	 */
	public static String getNowDateNormalString(int interval) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, interval);
		return getDateNormalString(c.getTime());
	}

	/**
	 * 获得指定日期的字符串，格式为MM-dd
	 *
	 * @param interval
	 * @return
	 */
	public static String getNowMonthNormalString(int interval) {
		return getNowDateNormalString(interval).substring(5, 10);
	}

	/**
	 * 根据输入日期向后累加interval天
	 *
	 * @param day
	 * @param interval
	 * @return
	 * @throws ParseException
	 */
	public static String getIntervalDaysLater(String day, int interval) {
		if (day == null || "".equals(day)) {
			return "";
		}
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(day));
		} catch (ParseException e) {
			log.error(e.getMessage());
		}
		c.add(Calendar.DATE, interval);
		return getDateNormalString(c.getTime());
	}

	/**
	 * Description: 得到当前日期累加interval天
	 *
	 * @param interval
	 * @return
	 * @Author yubin Create Date: 2012-8-29 下午04:41:43
	 */
	public static String getNowDateIntervalDaysLater(int interval) {
		return getIntervalDaysLater(getNowDateNormalString(), interval);
	}

	/**
	 * Description: 月份累加interval
	 *
	 * @param day
	 * @param interval
	 * @return
	 * @throws ParseException
	 * @Author yubin Create Date: 2012-8-28 上午11:51:41
	 */
	public static String getIntervalMonthLater(String day, int interval) {
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(day));
		} catch (ParseException e) {
			log.error(e.getMessage());
		}
		c.add(Calendar.MONTH, interval);
		return getDateNormalString(c.getTime());
	}

	/**
	 * 得到日期字符串,格式为 MM-dd
	 *
	 * @param interval 间隔天数
	 * @return
	 */
	public static String getNowMonthDayDateNormalString(int interval) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, interval);
		return getMonthDayDateNormalString(c.getTime());
	}

	/**
	 * 得到日期字符串,格式为 yyyyMMdd
	 *
	 * @return 返回当前日期的字符串
	 */
	public static String getNowDateShortString() {
		return getDateShortString(new Date());
	}

	/**
	 * 得到时间字符串,格式为 yyyy年MM月dd日 HH时mm分ss秒
	 *
	 * @return 返回当前时间的字符串
	 */
	public static String getNowTimeChinaString() {
		return getTimeChinaString(new Date());
	}

	/**
	 * Description: 得到时间字符串,格式为 HH:mm:ss
	 *
	 * @return
	 * @Author yubin Create Date: 2012-8-29 上午11:34:35
	 */
	public static String getNowTimeNormalStringS() {
		return getTimeNormalStringS(new Date());
	}

	/**
	 * 得到时间字符串,格式为 MM/dd/yyyy HH:mm:ss
	 *
	 * @return 返回当前时间的字符串
	 */
	public static String getNowTimeEnglishString() {
		return getTimeEnglishString(new Date());
	}

	/**
	 * 得到时间字符串,格式为 yyyy-MM-dd HH:mm:ss
	 *
	 * @return 返回当前时间的字符串
	 */
	public static String getNowTimeNormalString() {
		return getTimeNormalString(new Date());
	}

	/**
	 * 得到时间字符串,格式为 yyyyMMddHHmmss
	 *
	 * @return 返回当前时间的字符串
	 */
	public static String getNowTimeShortString() {
		return getTimeShortString(new Date());
	}

	/**
	 * 得到时间字符串,格式为 yyyy年MM月dd日 HH:mm:ss
	 *
	 * @param date 待格式化日期
	 * @return 返回格式化后的时间字符串
	 * @since 0.1
	 */
	public static String getTimeChinaString(Date date) {
		DateFormat fmt = new SimpleDateFormat(TIME_FORMAT_CHINA);
		return fmt.format(date);
	}

	public static String getTimeChinaStringS(Date date) {
		DateFormat fmt = new SimpleDateFormat(TIME_FORMAT_CHINA_S);
		return fmt.format(date);
	}

	/**
	 * 得到时间字符串,格式为 MM/dd/yyyy HH:mm:ss
	 *
	 * @param date 待格式化日期
	 * @return 返回格式化后的时间字符串
	 * @since 0.1
	 */
	public static String getTimeEnglishString(Date date) {
		DateFormat fmt = new SimpleDateFormat(TIME_FORMAT_ENGLISH);
		return fmt.format(date);
	}

	/**
	 * 得到时间字符串,格式为 yyyy-MM-dd HH:mm:ss
	 *
	 * @param date 待格式化日期
	 * @return 返回格式化后的时间字符串
	 * @since 0.1
	 */
	public static String getTimeNormalString(Date date) {
		DateFormat fmt = new SimpleDateFormat(TIME_FORMAT_NORMAL);
		return fmt.format(date);
	}

	/**
	 * 得到时间字符串,格式为 yyyy/MM/dd HH:mm:ss
	 *
	 * @param date 待格式化日期
	 * @return 返回格式化后的时间字符串
	 * @since 0.1
	 */
	public static String getTimeNormalSprit(Date date) {
		DateFormat fmt = new SimpleDateFormat(TIME_FORMAT_YMD);
		return fmt.format(date);
	}

	/**
	 * Description: 得到时间字符串，格式为HH:mm:ss
	 *
	 * @param date
	 * @return
	 * @Author yubin Create Date: 2012-8-29 上午11:21:20
	 */
	public static String getTimeNormalStringS(Date date) {
		DateFormat fmt = new SimpleDateFormat(TIME_FORMAT_SHORT_S);
		return fmt.format(date);
	}

	/**
	 * 得到时间字符串,格式为 yyyyMMddHHmmss
	 *
	 * @param date 待格式化日期
	 * @return 返回格式化后的时间字符串
	 * @since 0.1
	 */
	public static String getTimeShortString(Date date) {
		DateFormat fmt = new SimpleDateFormat(TIME_FORMAT_SHORT);
		return fmt.format(date);
	}

	/**
	 * 得到时间字符串,格式为 yyyyMM
	 *
	 * @param date
	 * @return
	 * @since 0.1
	 */
	public static String getMonthString(Date date) {
		DateFormat fmt = new SimpleDateFormat(MONTH_FORMAT);
		return fmt.format(date);
	}

	/**
	 * 得到时间字符串,格式为 M月d日
	 *
	 * @param day 相隔几天
	 * @return
	 * @since 0.1
	 */
	public static String getMonthDay(int day) {
		DateFormat fmt = new SimpleDateFormat("M月d日");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, day);
		return fmt.format(c.getTime());
	}

	/**
	 * 获得日期集合
	 *
	 * @param beginDate 开始日期
	 * @param endDate   结束日期
	 * @return
	 * @throws ParseException
	 */
	public static List<String> getDateList(String beginDate, String endDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NORMAL);
		List<String> list = new ArrayList<String>();
		Calendar c1 = Calendar.getInstance(Locale.CHINA);
		c1.setTime(sdf.parse(beginDate));
		Calendar c2 = Calendar.getInstance(Locale.CHINA);
		c2.setTime(sdf.parse(endDate));
		long l1 = c1.getTimeInMillis();
		long l2 = c2.getTimeInMillis();
		int interval = (int) ((l2 - l1) / (1000 * 60 * 60 * 24));
		for (int i = 0; i <= interval; i++) {
			Calendar c = Calendar.getInstance();
			c.setTime(c1.getTime());
			c.add(Calendar.DATE, i);
			list.add(sdf.format(c.getTime()));
		}
		return list;
	}

	/**
	 * 获得输入日期所在周的第一天（周一为第一天）
	 *
	 * @param day
	 * @return
	 * @throws ParseException
	 */
	public static String getFirstDayOfWeek(String day) throws ParseException {
		Calendar c1 = Calendar.getInstance(Locale.CHINA);
		c1.setTime(sdf.parse(day));
		c1.setFirstDayOfWeek(Calendar.MONDAY);
		c1.set(Calendar.DAY_OF_WEEK, c1.getFirstDayOfWeek());
		return sdf.format(c1.getTime());
	}

	/**
	 * 获得输入日期所在周的最后一天（周日为最后一天）
	 *
	 * @param day
	 * @return
	 * @throws ParseException
	 */
	public static String getLastDayOfWeek(String day) throws ParseException {
		Calendar c1 = Calendar.getInstance(Locale.CHINA);
		c1.setTime(sdf.parse(day));
		c1.setFirstDayOfWeek(Calendar.MONDAY);
		c1.set(Calendar.DAY_OF_WEEK, c1.getFirstDayOfWeek() + 6);
		return sdf.format(c1.getTime());
	}

	/**
	 * 计算输入日期所在月的最后一天
	 *
	 * @param day
	 * @return
	 * @throws ParseException
	 */
	public static String getLastDayOfMonth(String day) throws ParseException {
		Calendar c1 = Calendar.getInstance(Locale.CHINA);
		c1.setTime(sdf.parse(day));
		c1.set(Calendar.DAY_OF_MONTH, 1);
		c1.add(Calendar.MONTH, 1);
		c1.add(Calendar.DAY_OF_MONTH, -1);
		return sdf.format(c1.getTime());
	}

	/**
	 * 计算输入日期所在月的最后一天
	 *
	 * @param day
	 * @return
	 * @throws ParseException
	 */
	public static Date getLastDayOfMonth(Date day) throws ParseException {
		Calendar c1 = Calendar.getInstance(Locale.CHINA);
		c1.setTime(day);
		c1.set(Calendar.DAY_OF_MONTH, 1);
		c1.add(Calendar.MONTH, 1);
		c1.add(Calendar.DAY_OF_MONTH, -1);
		return c1.getTime();
	}

	/**
	 * Description: 计算当前日期上一个月的最后一天
	 *
	 * @return yyyy年MM月dd日
	 * @throws Exception
	 * @Author lijie
	 * @Create Date: 2013-9-28 下午01:47:24
	 */
	public static String getLastDayOfMonthChina() throws Exception {
		return getDateChinaString(convertAsDate(getLastDayOfMonth(getLastDate(DATE_FORMAT_NORMAL))));
	}

	/**
	 * 计算输入日期所在月的第一天
	 *
	 * @param day
	 * @return
	 * @throws ParseException
	 */
	public static String getFirstDayOfMonth(String day) throws ParseException {
		Calendar c1 = Calendar.getInstance(Locale.CHINA);
		c1.setTime(sdf.parse(day));
		c1.set(Calendar.DAY_OF_MONTH, 1);
		return sdf.format(c1.getTime());
	}

	public static Date getFirstDayOfMonth(Date day) throws ParseException {
		Calendar c1 = Calendar.getInstance(Locale.CHINA);
		c1.setTime(day);
		c1.set(Calendar.DAY_OF_MONTH, 1);
		return c1.getTime();
	}

	/**
	 * Description: 计算当前日期上一个月的第一天
	 *
	 * @return yyyy年MM月dd日
	 * @throws Exception
	 * @Author lijie
	 * @Create Date: 2013-9-28 下午01:39:21
	 */
	public static String getFirstDayOfMonthChina() throws Exception {
		return getDateChinaString(convertAsDate(getFirstDayOfMonth(getLastDate(DATE_FORMAT_NORMAL))));
	}

	/**
	 * 是否是当月的第一天
	 *
	 * @param day
	 * @return
	 * @throws ParseException
	 */
	public static boolean isFirstDayOfMonth(String day) throws ParseException {
		return day.equals(getFirstDayOfMonth(day)) ? true : false;
	}

	/**
	 * 是否是当月的最后一天
	 *
	 * @param day
	 * @return
	 * @throws ParseException
	 */
	public static boolean isLastDayOfMonth(String day) throws ParseException {
		return day.equals(getLastDayOfMonth(day)) ? true : false;
	}

	/**
	 * 是否是当前周的第一天
	 *
	 * @param day
	 * @return
	 * @throws ParseException
	 */
	public static boolean isFirstDayOfWeek(String day) throws ParseException {
		return day.equals(getFirstDayOfWeek(day)) ? true : false;
	}

	/**
	 * 是否是当前周的最后一天
	 *
	 * @param day
	 * @return
	 * @throws ParseException
	 */
	public static boolean isLastDayOfWeek(String day) throws ParseException {
		return day.equals(getLastDayOfWeek(day)) ? true : false;
	}

	/**
	 * 计算输入日期所在周是否跨月
	 *
	 * @param day
	 * @return
	 * @throws ParseException
	 */
	public static boolean isDayBetweenMonthAndWeek(String day) throws ParseException {
		String firstDayOfMonth = getFirstDayOfMonth(day);
		String firstDayOfWeek = getFirstDayOfWeek(day);
		Pattern p = Pattern.compile("\\d{4}-\\d{2}-01");
		Matcher m = p.matcher(day);
		if (firstDayOfWeek.compareTo(firstDayOfMonth) < 0 && !m.matches()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 输入的日期是否为时间格式
	 *
	 * @param input
	 * @return
	 */
	public static boolean isInputDate(String input) {
		return input.matches("\\d{4}-\\d{1,2}-\\d{1,2}");
	}

	public static boolean isInputDateNormalString(String input) {
		return input.matches("\\d{4}-\\d{2}-\\d{2}");
	}

	/**
	 * 将日期字符串格式化为yyyy-MM-dd
	 *
	 * @param input
	 * @return
	 */
	public static String formatDateString(String input) throws IllegalArgumentException {
		String month = "";
		String day = "";
		try {
			if (!isInputDate(input)) {
				throw new IllegalArgumentException("日期格式不合法");
			}
			if (isInputDateNormalString(input)) {
				return input;
			}
			month = input.substring(input.indexOf("-") + 1, input.lastIndexOf("-"));
			if (month.length() == 1 && month.indexOf("0") != 0)
				month = "0" + month;
			day = input.substring(input.lastIndexOf("-") + 1, input.length());
			if (day.length() == 1 && day.indexOf("0") != 0)
				day = "0" + day;
		} catch (Exception e) {
			//logger.error(e);
			return input;
		}
		return input.substring(0, 4) + "-" + month + "-" + day;
	}

	/**
	 * 组装当前时间的开始时间戳
	 */
	public static String getDateNowStr() {

		Calendar c1 = Calendar.getInstance();
		int year = c1.get(Calendar.YEAR);
		// 当前月
		int month = (c1.get(Calendar.MONTH)) + 1;
		// 当前月的第几天：即当前日
		int day_of_month = c1.get(Calendar.DAY_OF_MONTH);
		String time = year + "-" + month + "-" + day_of_month + " 00:00:00";
		return time;
	}

	/**
	 * 组装当前时间的结束时间戳
	 */
	public static String getDateNowStrend() {

		Calendar c1 = Calendar.getInstance();
		int year = c1.get(Calendar.YEAR);
		// 当前月
		int month = (c1.get(Calendar.MONTH)) + 1;
		// 当前月的第几天：即当前日
		int day_of_month = c1.get(Calendar.DAY_OF_MONTH);
		String time = year + "-" + month + "-" + day_of_month + " 23:59:59";
		return time;
	}

	/**
	 * 获取上月的年月日
	 *
	 * @param toFormat 日期格式
	 * @return
	 */
	public static String getLastDate(String toFormat) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		SimpleDateFormat format = new SimpleDateFormat(toFormat);
		String time = format.format(c.getTime());
		return time;
	}

	/**
	 * 获取本月的年月日
	 *
	 * @param toFormat 日期格式
	 * @return
	 */
	public static String getDate(String toFormat) {
		SimpleDateFormat format = new SimpleDateFormat(toFormat);
		String time = format.format(new Date());
		return time;
	}

	/**
	 * 获取上月的年
	 *
	 * @param toFormat 日期格式
	 * @return
	 */
	public static String getLastYearMonth() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		SimpleDateFormat format = new SimpleDateFormat(YEAR_MONTH_FORMAT);
		String time = format.format(c.getTime());
		return time;
	}

	/**
	 * 获取上月的月
	 *
	 * @param toFormat 日期格式
	 * @return
	 */
	public static String getLastMonth() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		SimpleDateFormat format = new SimpleDateFormat(MONTH);
		String time = format.format(c.getTime());
		return time;
	}

	/**
	 * 获取本月的年月
	 *
	 * @param toFormat 日期格式
	 * @return
	 */
	public static String getYearMonth() {
		SimpleDateFormat format = new SimpleDateFormat(YEAR_MONTH_FORMAT);
		String time = format.format(new Date());
		return time;
	}

	/**
	 * 获取上月的年
	 *
	 * @param toFormat 日期格式
	 * @return
	 */
	public static String getLastYearMonth(String toFormat) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		SimpleDateFormat format = new SimpleDateFormat(toFormat);
		String time = format.format(c.getTime());
		return time;
	}

	/**
	 * 取得当月天数
	 */
	public static int getThisMonthDays() {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	/**
	 * 得到指定月的天数
	 */
	public static int getLastMonthDays(int year, int month) {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	/**
	 * 获取上月天数
	 *
	 * @return
	 */
	public static int getLastMonthDays() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		int maxDate = cal.get(Calendar.DAY_OF_MONTH);
		return maxDate;
	}

	public static String getStrDatetosimp(String date) {
		SimpleDateFormat fmt = new SimpleDateFormat(DATE_FORMAT_SHORT);
		try {
			return getDateShortString(fmt.parse(date));
		} catch (ParseException e) {
			//logger.error(e);
		}
		return null;
	}

	/**
	 * 指定时间前几天
	 */
	public static String getDateBeforeday(Date dd, int day) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dd);
		calendar.add(Calendar.DATE, -day); // 得到前一天
		Date date = calendar.getTime();
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		return df.format(date);
	}

	/**
	 * 指定时间前几天
	 */
	public static String getDateBeforeday2(Date dd, int day) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dd);
		calendar.add(Calendar.DATE, -day); // 得到前一天
		Date date = calendar.getTime();
		return sdf.format(date);
	}

	/**
	 * Description: 得到当前Date类型日期,格式为 yyyy-MM-dd
	 *
	 * @return
	 * @throws ParseException
	 * @Author ouyangjin
	 * @Create Date: 2013-10-9 下午03:21:07
	 */
	public static Date getCurrentTime() throws ParseException {
		return sdf.parse(getNowDateNormalString());
	}


	/**
	 * 取两日期相差月数
	 *
	 * @param beginningDate
	 * @param endingDate
	 * @return
	 */
	public static final int getMonthsDifference(Date beginningDate, Date endingDate) {
		Calendar startCalendar = new GregorianCalendar();
		startCalendar.setTime(beginningDate);
		Calendar endCalendar = new GregorianCalendar();
		endCalendar.setTime(endingDate);

		int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
		int diffMonth = diffYear * 12 + (endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH));
		return diffMonth;
	}

	/**
	 * 是否为同年月
	 *
	 * @param beginningDate
	 * @param endingDate
	 * @return
	 */
	public static final boolean isSameYearMonth(Date beginningDate, Date endingDate) {
		Calendar startCalendar = new GregorianCalendar();
		startCalendar.setTime(beginningDate);
		Calendar endCalendar = new GregorianCalendar();
		endCalendar.setTime(endingDate);
		if ((endCalendar.get(Calendar.MONTH) == startCalendar.get(Calendar.MONTH)) && (endCalendar.get(Calendar.YEAR) == startCalendar.get(Calendar.YEAR)))
			return true;
		else
			return false;
	}

	/**
	 * 是否为同月
	 *
	 * @param beginningDate
	 * @param endingDate
	 * @return
	 */
	public static final boolean isSameMonth(Date beginningDate, Date endingDate) {
		Calendar startCalendar = new GregorianCalendar();
		startCalendar.setTime(beginningDate);
		Calendar endCalendar = new GregorianCalendar();
		endCalendar.setTime(endingDate);
		if (endCalendar.get(Calendar.MONTH) == startCalendar.get(Calendar.MONTH))
			return true;
		else
			return false;
	}

	/**
	 * 是否为同月同日
	 *
	 * @param beginningDate
	 * @param endingDate
	 * @return
	 */
	public static final boolean isSameMonthAndDay(Date beginningDate, Date endingDate) {
		Calendar startCalendar = new GregorianCalendar();
		startCalendar.setTime(beginningDate);
		Calendar endCalendar = new GregorianCalendar();
		endCalendar.setTime(endingDate);
		if ((endCalendar.get(Calendar.MONTH) == startCalendar.get(Calendar.MONTH)) && (endCalendar.get(Calendar.DATE) == startCalendar.get(Calendar.DATE)))
			return true;
		else
			return false;
	}

	/**
	 * parse Date to String
	 *
	 * @param date
	 * @return
	 */
	public static String parseDate(Date date) {
		if (date == null)
			return "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		return dateFormat.format(date);
	}

	/**
	 * parse Date to String
	 *
	 * @param date
	 * @return
	 */
	public static String parseDate(Date date, String pattern) {
		if (date == null)
			return "";
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(date);
	}

	/*
	 * parse Text to Date
	 *
	 * @param text
	 *
	 * @return Date
	 */
	public static Date parseDate(String text) throws ParseException {
		Date date = null;
		if (text == null || text.isEmpty())
			return null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		date = dateFormat.parse(text);
		return date;
	}

	public static Date parseDate(String text, String pattern) throws ParseException {
		Date date = null;
		if (text == null || text.isEmpty() || pattern == null || pattern.isEmpty())
			return null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		date = dateFormat.parse(text);
		return date;
	}

	/**
	 * @title :获得当前时间 前多少毫秒的时间
	 * 如获得前5分钟时间
	 */
	public static String getDateNowTime(long time) {
		Date date = new Date();
		Date nowDate = new Date(date.getTime() - time);
		SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_FORMAT_NORMAL);
		return dateFormat.format(nowDate);
	}

	/**
	 * 计算两个日期之间相差的天数
	 *
	 * @param smdate 较小的时间
	 * @param bdate  较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate, Date bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 获取某起止日期间的时间段
	 */
	public static String[] getDueByStartEndDate(String strStartDate, String strEndDate) {
		try {
			String[] re = null;
			if (strStartDate.equals(strEndDate)) {
				re = new String[1];
				re[0] = strStartDate;
				return re;
			}
			Date dtSD = sdf.parse(strStartDate);
			Date dtED = sdf.parse(strEndDate);
			if (dtSD.compareTo(dtED) > 0) {
				return null;
			}
			int nCount = daysBetween(dtSD, dtED) + 1;
			if (nCount < 1)
				return null;
			re = new String[nCount];
			re[0] = strStartDate;
			re[nCount - 1] = strEndDate;
			for (int i = 1; i < (nCount - 1); i++) {
				re[i] = getOnlyDateLaterday(dtSD, i);
			}
			return re;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 指定时间后几天
	 */
	public static String getOnlyDateLaterday(Date dd, int day) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dd);
		calendar.add(Calendar.DATE, day); // 得到前一天
		Date date = calendar.getTime();
		DateFormat df = new SimpleDateFormat(DATE_FORMAT_NORMAL);
		return df.format(date);
	}

	/**
	 * 指定时间后几天
	 */
	public static String getDateLaterday(Date dd, int day) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dd);
		calendar.add(Calendar.DATE, day); // 得到前一天
		Date date = calendar.getTime();
		DateFormat df = new SimpleDateFormat(TIME_FORMAT_NORMAL);
		return df.format(date);
	}

	/**
	 * 指定时间后几天
	 */
	public static String getDateLaterday(String dd, int day) {
		Date ddd = convertAsDate(dd);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(ddd);
		calendar.add(Calendar.DATE, day); // 得到前一天
		Date date = calendar.getTime();
		DateFormat df = new SimpleDateFormat(TIME_FORMAT_NORMAL);
		return df.format(date);
	}

	/**
	 * 指定时间后多少分钟
	 */
	public static String getDateLaterMinute(Date dd, int minute) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dd);
		calendar.add(Calendar.MINUTE, minute);
		Date date = calendar.getTime();
		DateFormat df = new SimpleDateFormat(TIME_FORMAT_NORMAL);
		return df.format(date);
	}
}
