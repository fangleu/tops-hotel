package com.travelzen.tops.hotel.elong.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.logging.LogFactory;

import com.travelzen.framework.util.DateUtils;

/**
 * 源于{@link com.travelzen.framework.util.DateUtils}
 * 由于DateUtils中采用数字来判断日期格式，可辨识度不高,每次要查看源码，因此加入枚举，来重新包装
 * 由于对格式的命名较困难，因而采用format｛i｝的方式，日期的格式体现在注释上，调用的时候根据显示的注视来判断日期的格式化的格式 
 *	@author tangtianjiang
 *	@version 2013年9月27日上午10:35:46
 *
 */
public class DateUtil{
	/**
	 * 一天的毫秒
	 */
	public static long ONEDAYTIME = 1000*3600*24;
	public static long ONEMINUTE = 1000*60;
	/**
	 * 日期格式化的个格式
	 * @author ipolaris
	 *
	 */
	public enum DateForamtTypes{
		/**
		 * yyyy'??'MM'??'dd'??' H:mm:ss.S
		 */
		FORMAT_1,
		/**
		 * yyyy'-'MM'-'dd H:mm:ss.S
		 */
		FORMAT_2,
		/**
		 * yyyy'??'MM'??'dd'??'
		 */
		FORMAT_3,
		/**
		 * yyyy'-'MM'-'dd
		 */
		FORMAT_4,
		/**
		 * H:mm:ss
		 */
		FORMAT_5,
		/**
		 * K:mm:ss a
		 */
		FORMAT_6,
		/**
		 * yyyy'??'MM'??'dd'??' H:mm:ss
		 */
		FORMAT_7,
		/**
		 * yyyy'??'MM'??'dd'??' K:mm:ss a
		 */
		FORMAT_8,
		/**
		 * yyyy-MM-dd H:mm:ss
		 */
		FORMAT_9,
		/**
		 * yyyy-MM-dd K:mm:ss a
		 */
		FORMAT_10,
		/**
		 * H:mm:ss.S
		 */
		FORMAT_11,
		/**
		 * K:mm:ss.S a
		 */
		FORMAT_12,
		/**
		 * H:mm
		 */
		FORMAT_13,
		/**
		 * K:mm a
		 */
		FORMAT_14,
		/**
		 * yyyy-MM-dd H:mm
		 */
		FORMAT_15,
		/**
		 * yyyyMMddHHmmssS
		 */
		FORMAT_16,
		/**
		 * yyyyMMdd
		 */
		FORMAT_17,
		/**
		 * yyyy/MM/dd
		 */
		FORMAT_18,
		/**
		 * yyyy/MM/dd H:mm:ss
		 */
		FORMAT_19,
		/**
		 * yyyy-MM-dd
		 */
		FORMAT_20
	}
	
	
	/**
	 * 日期转字符串，屏蔽异常
	 * @param date 需要转的日期
	 * @param defaultDate 当转失败时，默认给的日期，可为空
	 * @return 日期转换后的字符串，当所给日期和所给默认日期都为空时，返回null
	 */
	public static String dateToStringWithoutCatch(Date date ,Date defaultDate){
		String dateResultStr = null;
		try {
			dateResultStr = DateFormat.getDateInstance().format(date);
		} catch (Exception e) {
			if (null != defaultDate) {
				try {
					dateResultStr = DateFormat.getDateInstance().format(defaultDate);
				} catch (Exception e2) {
					return null;
				}
			}else{
				return null;
			}
		}
		return dateResultStr;
	}
	/**
	 * 字符串转日期
	 * @param dateStr 日期字符串
	 * @param formart 格式 如：yyyy-MM-dd
	 * @return
	 */
	public static Date stringToDateWithFormat(String dateStr, String formart){
		DateFormat format =new SimpleDateFormat(formart);
		Date date = null;
		try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
			LogFactory.getLog(Date.class).debug(e.getMessage());
		}
		return date;
	}
	
	/**
	 * 日期转字符串，屏蔽异常
	 * @param date 需要转的日期
	 * @param formart 格式
	 * @author zhengzhichao
	 */
	public static String dateToStringWithFormart(Date date ,String formart) throws Exception{
		SimpleDateFormat dateFormat = new SimpleDateFormat(formart);
		return dateFormat.format(date);
	}
	/**
	 * 获取中文大写星期  DAY OF WEEK
	 * @author zhengzhichao
	 * @param cal
	 * @return
	 */
	public static String getDayOfWeek(Calendar cal){
		String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
		return weekDays[cal.get(Calendar.DAY_OF_WEEK)-1];
	}
	
	/**
	 * 获取中文大写星期  DAY OF WEEK
	 * @author zhengzhichao
	 * @param date
	 * @return
	 */
	public static String getDayOfWeek(Date date){
		Calendar  cal = Calendar.getInstance();
		cal.setTime(date);
		String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
		return weekDays[cal.get(Calendar.DAY_OF_WEEK)-1];
	}
	
	/**
	 * 获取中文大写星期  DAY OF WEEK
	 * @author zhengzhichao
	 * @param dateStr
	 * @param formatStr
	 * @return
	 * @throws ParseException 
	 */
	public static String getDayOfWeek(String dateStr,String formatStr) throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat(formatStr);
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateFormat.parse(dateStr));
		String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
		return weekDays[cal.get(Calendar.DAY_OF_WEEK)-1];
	}
	
	
	/**
	 *
	 * @param sDate
	 * @return
	 */
	public static Date getDate(String sDate) {
		return DateUtils.getDate(sDate);
	}

	/**
	 *
	 * @param sDate
	 * @return
	 */
	public static Date getJustDate(String sDate) {
		return DateUtils.getJustDate(sDate);
	}

	/**
	 *
	 * @param lDate
	 * @return
	 */
	public static Date getDate(long lDate) {
		return DateUtils.getDate(lDate);
	}

	/**
	 *
	 * @param lDate
	 * @return
	 */
	public static Date getJustDate(long lDate) {
		return DateUtils.getJustDate(lDate);
	}

	public static Date getDate(long lDate, String sFormat) {
		return DateUtils.getDate(lDate, sFormat);
	}
	public static Date getDate(String sDate, String sFormat) {
		return DateUtils.getDate(sDate, sFormat);
	}
	public static Date getDate(String sDate, DateForamtTypes dateForamtTypes){
		return getDate(sDate, DateUtils.findFormat(dateForamtTypes.ordinal()).toString());
	}
	public static String formatDate(long sDate, String sFormat) {
		return DateUtils.formatDate(sDate, sFormat);
	}

	public static String formatDate(long lDate) {
		return DateUtils.formatDate(lDate);
	}

	public static String formatJustDate(long lDate) {
		return DateUtils.formatJustDate(lDate);
	}

	public static String formatDate(Date date, String sFormat) {
		return DateUtils.formatDate(date, sFormat);
	}

	public static String formatDate(Date date) {
		return DateUtils.formatDate(date);
	}

	public static String formatDate(Date date, TimeZone timeZone) {
		return formatDate(date, timeZone, "yyyy-MM-dd");
	}

	public static String formatDate(Date date, TimeZone timeZone, String sFormat) {
		return DateUtils.formatDate(date, timeZone, sFormat);
	}

	public static String simplifyDate(String date) {
		return DateUtils.simplifyDate(date);
	}

	public static String getTodayStr() {
		return DateUtils.getTodayStr();
	}

	public static String getDateStamp(DateForamtTypes formatType) {
		return DateUtils.getDateStamp(formatType.ordinal());
	}

	public static final int TIMESTAMPTYPE_UNIX = 2;

	/**
	 * 将日期对象转为指定格式日期字符串
	 *
	 * @param date
	 * @param intFormat
	 * @return
	 */
	public static String format(Date date, DateForamtTypes foramtType) {
		return DateUtils.format(date, foramtType.ordinal());
	}

	
	public static String formatDateStr(String dateStr, int newtimestampType,
			int oldtimestampType) throws ParseException {
		return DateUtils.formatDateStr(dateStr, newtimestampType, oldtimestampType);
	}

	/**
	 * 判断字符日期是否合法，根据给定时间戳格式
	 *
	 * @param strDate
	 * @param timestampType
	 * @return
	 */
	public static boolean isValdateDate(String strDate, int timestampType) {
		return DateUtils.isValdateDate(strDate, timestampType);
	}

	public static List<String> getDateList(String startTime, String endTime)
			throws Exception {
		return DateUtils.getDateList(startTime, endTime);
	}

	/**
	 * 通过起始时间和结束时间获得期间有多少分钟
	 *
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws ParseException
	 */
	public static long findDateSpaceInMinuties(String startTime, String endTime)
			throws ParseException {

		return DateUtils.findDateSpaceInMinuties(startTime, endTime);
	}

	/**
	 * 通过起始时间和结束时间，将当前时间范围分割为参数（divisor）代表的份数，
	 * 并将每一份转化为对应的日期封装到集合中返回。注：divisor必须为偶数， 返回的日期格式为标准格式，yyyy-MM-dd H:mm:ss
	 *
	 * @param startTime
	 * @param endTime
	 * @param divisor
	 * @return
	 * @throws ParseException
	 */
	public static List<String> findDateScopeStandardFormat(String startTime,
			String endTime, int divisor, String strFormat)
			throws ParseException {
		return DateUtils.findDateScopeStandardFormat(startTime, endTime, divisor, strFormat);
	}

	public static long getDateTime(String date, String format) {
		return DateUtils.getDateTime(date, format);
	}

	/**
	 * 从日期对象得到该日期零点的时间戳
	 *
	 * @param date
	 * @param timestampType
	 * @return
	 */
	public static long getZeroTimeStampOfDay(Date date, int timestampType) {
		return DateUtils.getZeroTimeStampOfDay(date, timestampType);
	}

	/**
	 * 获取日期对应的时间
	 *
	 * @param date
	 * @param timestampType
	 * @return
	 */
	public static long getDateTimestamp(Date date, int timestampType) {
		return getDateTimestamp(date, timestampType);
	}

	public static String findYestarday(int timestampType) {
		return DateUtils.findYestarday(timestampType);
	}

	public static Date formatDate(String date, DateForamtTypes foramtType) throws Exception {
		return DateUtils.formatDate(date, foramtType.ordinal());
	}

	public static String addDate(String dateStr, DateForamtTypes foramtType, int addNum)
			throws Exception {
		return DateUtils.addDate(dateStr, foramtType.ordinal(), addNum);
	}

	public static Date toDate(String param, String format) throws ParseException {
		return DateUtils.toDate(param, format);
	}

	public static Date computeDate(Date date, int day) {
		return DateUtils.computeDate(date, day);
	}
	
	public static Date todayTrim(Date date) {
		return DateUtils.todayTrim(date);
	}
	/**
	 * 
	 * TODO 日期之间的天数间隔
	 * @author TangTianJiang
	 * @date 2013年12月25日 下午7:37:21
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception 
	 */
	public static int durDay(String start, String end) throws Exception{
		Date startDate = getDate(start, "yyyy-MM-dd");
		Date endDate = getDate(end, "yyyy-MM-dd");
		long timeRange = endDate.getTime()-startDate.getTime();
		int dayRange = (int) (timeRange/DateUtil.ONEDAYTIME);
		return dayRange;
	}
	/**
	 * 
	 * TODO
	 * @author TangTianJiang
	 * @date 2013年12月26日 下午1:32:45
	 * @param timeStr
	 * @return
	 */
	public static long computeLongTime(String timeStr){
		String[] timeHHmm = timeStr.split(":");
		try{
			if (timeHHmm.length > 1) {
				return Long.parseLong(timeHHmm[0])*60+Long.parseLong(timeHHmm[1]);
			}else if (timeHHmm.length == 1) {
				return Long.parseLong(timeHHmm[0]);
			}else{
				return 0;
			}
		}catch (Exception e) {
			return 0;
		}
	}
	
	public static float computeTimeToFloatNum(String timeStr){
		float timeLong = computeLongTime(timeStr);
		return timeLong/60;
	}
	
	
}
