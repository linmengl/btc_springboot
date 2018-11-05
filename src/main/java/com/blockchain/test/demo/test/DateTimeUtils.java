package com.blockchain.test.demo.test;


import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTimeUtils {

	public static void main(String[] args) throws Exception {
		//Date date = getThisWeekMonday();
		//System.out.println(getDateStr(2));

		String str = "2018-07-19 12:00:00";
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		calendar.setTime(sdf.parse(str));
		int i = calendar.get(Calendar.DAY_OF_WEEK) - 1;

	}


	/**
	 * 得到本周周一凌晨毫秒值
	 *
	 * @return
	 */
	public static Long getMondayOfThisWeek() throws Exception {
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");

		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + 1);
		String ymd = sm.format(c.getTime());
		return sm.parse(ymd).getTime();
	}

	/**
	 * 得到本周某一天凌晨毫秒值
	 *
	 * @return
	 */
	public static Long getDayMills(int dayOfWeek) throws Exception {
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");

		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + dayOfWeek);
		String ymd = sm.format(c.getTime());
		return sm.parse(ymd).getTime();
	}

	/**
	 * 得到本周某一天日期字符串
	 *
	 * @return
	 */
	public static String getDateStr(int dayOfWeek) {
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");

		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + dayOfWeek);
		return sm.format(c.getTime());
	}


	/**
	 * 得到本周周日晚上12点整毫秒值
	 *
	 * @return yyyy-MM-dd
	 */
	public static Long getSundayOfThisWeek() throws Exception {
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + 8);
		String ymd = sm.format(c.getTime());
		return sm.parse(ymd).getTime();
	}
}
