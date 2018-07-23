package com.blockchain.btc_springboot.demo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 对json格式的活动日期进行处理
 */
public class Tt {

	//活动开始时间 7月1号
	//public static Long startTime = 1530374400000L;
	public static Long startTime = 1532448000000L;//25号
	//活动结束时间
	public static Long endTime = 1532880000000L;//30号
	//public static Long endTime = 1531584000000L;//15号

	//小活动每次活动对应开始时间   对数据有编辑时 设置ends=null，重新到库里查询
	public static List<Long> ends = new ArrayList<>(20);
	//小活动每次活动对应结束时间
	public static List<Long> starts = new ArrayList<>(20);


	public static void main(String[] args) throws Exception {
		//获取时间
		//String str = "[{\"dtOpenTime\":\"10:56:00\",\"dtCloseTime\":\"10:58:00\",\"week\":[\"1\",\"3\"]}" +
		//		",{\"dtOpenTime\":\"10:57:00\",\"dtCloseTime\":\"10:59:00\",\"week\":[\"1\",\"2\"]}" +
		//		",{\"dtOpenTime\":\"14:00:00\",\"dtCloseTime\":\"17:00:00\",\"week\":[\"2\",\"4\",\"5\"]}" +
		//		"{\"dtOpenTime\":\"13:00:00\",\"dtCloseTime\":\"18:00:00\",\"week\":[\"3\",\"4\"]}]";
		String str =  "[{\"dtOpenTime\":\"00:00:00\",\"dtCloseTime\":\"24:00:00\",\"week\":[\"1\",\"2\"]}]";
		JSONArray objects = JSONObject.parseArray(str);
		//格式化参数
		initParam(objects);
		System.out.println(starts);
		System.out.println(ends);


		List<Long> activityTime = getActivityTime();
		System.out.println(activityTime);
	}

	/**
	 * 获取活动时间
	 *
	 * @return
	 * @throws Exception
	 */
	private static List<Long> getActivityTime() throws Exception {

		List<Long> timeList = new ArrayList<>(2);

		//当前相对于周一凌晨的毫秒值
		Long time = System.currentTimeMillis() - DateTimeUtils.getMondayOfThisWeek();

		//活动结束
		if (time >= endTime || starts.size() == 0 || ends.size() == 0) {
			timeList.add(startTime);
			timeList.add(endTime);
			return timeList;
		}
		//当前还不到活动期间
		if (System.currentTimeMillis() < startTime) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date(startTime));
			//开始时间是周几
			int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
			Long tempTime = DateTimeUtils.getDayMills(week) - DateTimeUtils.getMondayOfThisWeek();
			//获取最近活动的时间
			SimpleDateFormat sdfOne = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date(startTime);
			String str = sdfOne.format(date);
			Long zeroTime = sdfOne.parse(str).getTime() - DateUtils.MILLIS_PER_DAY * (week - 1);
			if (starts.size() == 1){
				if (tempTime <= starts.get(0)) {
					timeList.add(starts.get(0) + zeroTime);
					timeList.add(ends.get(0) + zeroTime);
				}else if (tempTime > starts.get(0) && tempTime < ends.get(0)){
					timeList.add(startTime);
					timeList.add(ends.get(0) + zeroTime);
				}else {
					//活动开始后的下个周一
					Long nTime = DateUtils.MILLIS_PER_DAY * 7 + zeroTime;
					timeList.add(nTime + starts.get(0));
					timeList.add(nTime + ends.get(0));
				}
			}else {
				for (int a = 0; a < starts.size() - 1; a++) {
					if (tempTime < starts.get(0)) {
						timeList.add(starts.get(0) + zeroTime);
						timeList.add(ends.get(0) + zeroTime);
						break;
					} else if (tempTime >= starts.get(a - 1) && tempTime < starts.get(a)) {
						timeList.add(starts.get(a) + zeroTime);
						timeList.add(ends.get(a) + zeroTime);
						break;
					}
				}
				if (tempTime >= ends.get(ends.size() - 1)){
					//活动开始后的下个周一
					Long nTime = DateTimeUtils.getSundayOfThisWeek() + zeroTime;
					timeList.add(nTime + starts.get(ends.size() - 1));
					timeList.add(nTime + ends.get(ends.size() - 1));
				}
			}
		} else if (time >= ends.get(ends.size() - 1)) {
			//当前在活动期间，本周活动结束
			//下次活动开始时间
			Long nextStartTime = DateTimeUtils.getSundayOfThisWeek() + starts.get(0);
			Long nextEndTime = DateTimeUtils.getSundayOfThisWeek() + ends.get(0);
			timeList.add(nextStartTime);
			timeList.add(nextEndTime);
		} else {
			//当前在活动期间，本周活动未结束
			for (int a = 0; a < starts.size() - 1; a++) {
				if (time > starts.get(a) && time < starts.get(a + 1)) {
					if (time < ends.get(a)) {
						timeList.add(starts.get(a) + DateTimeUtils.getMondayOfThisWeek());
						timeList.add(ends.get(a) + DateTimeUtils.getMondayOfThisWeek());
						break;
					}
				} else if (time > starts.get(starts.size() - 1)) {
					timeList.add(starts.get(starts.size() - 1) + DateTimeUtils.getMondayOfThisWeek());
					timeList.add(ends.get(ends.size() - 1) + DateTimeUtils.getMondayOfThisWeek());
					break;
				}
			}
		}
		//校验时间
		if (timeList.size() > 0) {
			//小活动的开始时间大于大活动的结束时间
			if (timeList.get(0) > endTime) {
				timeList.clear();
				timeList.add(startTime);
				timeList.add(endTime);
				//活动结束 清空缓存
				starts = null;
				ends = null;
			} else if (timeList.get(1) > endTime) {//小活动结束时间 超过 大活动结束时间
				timeList.remove(1);
				timeList.add(endTime);
			}
			if (timeList.get(0).equals(timeList.get(1))){
				timeList.clear();
				timeList.add(startTime);
				timeList.add(endTime);
			}
		}
		return timeList;
	}


	/**
	 * 格式化json数据
	 *
	 * @param objects
	 * @throws Exception
	 */
	private static void initParam(JSONArray objects) throws Exception {
		for (Object object : objects) {
			JSONObject jsonObject = JSONObject.parseObject(object.toString());
			String dtOpenTimeSta = (String) jsonObject.get("dtOpenTime");
			String dtCloseTimeSta = (String) jsonObject.get("dtCloseTime");
			List<String> weeks = (List<String>) jsonObject.get("week");
			for (String week : weeks) {
				Long start = getTime(week, dtOpenTimeSta);
				Long end = getTime(week, dtCloseTimeSta);
				if (starts.size() == 0) {
					starts.add(start);
					ends.add(end);
					continue;
				}
				if (end < starts.get(0)) {
					starts.add(0, start);
					ends.add(0, end);
				} else if (start > ends.get(ends.size() - 1)) {
					starts.add(start);
					ends.add(end);
				} else if (start.equals(ends.get(ends.size() - 1))){
					ends.remove(ends.size() - 1);
					ends.add(end);
				}else if (start <= starts.get(0) && end >= ends.get(ends.size() - 1)) {
					starts.clear();
					starts.add(start);
					ends.clear();
					ends.add(end);
				} else {
					out:
					for (int a = 0; a < starts.size(); a++) {
						if (start < starts.get(a)) {
							if (end < starts.get(a)) {
								starts.add(a, start);
								ends.add(a, end);
								break;
							} else if (end >= starts.get(a) && end <= ends.get(a)) {
								starts.remove(a);
								starts.add(a, start);
								break;
							} else if (end > ends.get(a)) {
								starts.remove(a);
								starts.add(a, start);
								for (int b = a + 1; b < starts.size(); b++) {
									if (end < starts.get(b)) {
										ends.remove(a);
										ends.add(a, end);
										break out;
									} else if (end >= starts.get(b) && end <= ends.get(b)) {
										ends.remove(a);
										starts.remove(b);
										break out;
									} else {
										continue;
									}
								}
							}
						} else if (start == starts.get(a)) {
							if (end > ends.get(a)) {
								for (int b = a + 1; b < starts.size(); b++) {
									if (end < starts.get(b)) {
										ends.remove(a);
										ends.add(a, end);
										break out;
									} else if (end >= starts.get(b) && end <= ends.get(b)) {
										ends.remove(a);
										starts.remove(b);
										break out;
									} else {
										continue;
									}
								}
							}
						} else if (start > starts.get(a) && start < ends.get(a)) {
							if (end > ends.get(a)) {
								for (int b = a + 1; b < starts.size(); b++) {
									if (end < starts.get(b)) {
										ends.remove(a);
										ends.add(a, end);
										break out;
									} else if (end >= starts.get(b) && end <= ends.get(b)) {
										ends.remove(a);
										starts.remove(b);
										break out;
									} else {
										continue;
									}
								}
							}
						} else if (start == ends.get(a)) {
							ends.remove(a);
							for (int b = a + 1; b < starts.size(); b++) {
								if (end < starts.get(b)) {
									ends.add(a, end);
									break out;
								} else if (end >= starts.get(b) && end <= ends.get(b)) {
									starts.remove(b);
									break out;
								} else {
									continue;
								}
							}
						} else {
							continue;
						}
					}
				}
			}
		}
	}

	/**
	 * 获取相对周一凌晨的毫秒值
	 *
	 * @param week
	 * @param dtOpenTimeSta
	 * @return
	 */
	private static Long getTime(String week, String dtOpenTimeSta) throws Exception {
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String actStart = DateTimeUtils.getDateStr(Integer.parseInt(week)).concat(" ").concat(dtOpenTimeSta);
		return sm.parse(actStart).getTime() - DateTimeUtils.getMondayOfThisWeek();
	}

}
